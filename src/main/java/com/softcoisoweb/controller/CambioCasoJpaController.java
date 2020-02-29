/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.CambioCaso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class CambioCasoJpaController implements Serializable {

    public CambioCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CambioCaso cambioCaso) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = cambioCaso.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            CambioCaso oldCambioCasoOfCasoPersona = casoPersonaOrphanCheck.getCambioCaso();
            if (oldCambioCasoOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type CambioCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = cambioCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                cambioCaso.setCasoPersona(casoPersona);
            }
            EstadoCaso estadoCasoCodigoEstado = cambioCaso.getEstadoCasoCodigoEstado();
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado = em.getReference(estadoCasoCodigoEstado.getClass(), estadoCasoCodigoEstado.getCodigoEstado());
                cambioCaso.setEstadoCasoCodigoEstado(estadoCasoCodigoEstado);
            }
            Usuario usuarioCedula = cambioCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                cambioCaso.setUsuarioCedula(usuarioCedula);
            }
            em.persist(cambioCaso);
            if (casoPersona != null) {
                casoPersona.setCambioCaso(cambioCaso);
                casoPersona = em.merge(casoPersona);
            }
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado.getCambioCasoCollection().add(cambioCaso);
                estadoCasoCodigoEstado = em.merge(estadoCasoCodigoEstado);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getCambioCasoCollection().add(cambioCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCambioCaso(cambioCaso.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("CambioCaso " + cambioCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CambioCaso cambioCaso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CambioCaso persistentCambioCaso = em.find(CambioCaso.class, cambioCaso.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentCambioCaso.getCasoPersona();
            CasoPersona casoPersonaNew = cambioCaso.getCasoPersona();
            EstadoCaso estadoCasoCodigoEstadoOld = persistentCambioCaso.getEstadoCasoCodigoEstado();
            EstadoCaso estadoCasoCodigoEstadoNew = cambioCaso.getEstadoCasoCodigoEstado();
            Usuario usuarioCedulaOld = persistentCambioCaso.getUsuarioCedula();
            Usuario usuarioCedulaNew = cambioCaso.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                CambioCaso oldCambioCasoOfCasoPersona = casoPersonaNew.getCambioCaso();
                if (oldCambioCasoOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type CambioCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                cambioCaso.setCasoPersona(casoPersonaNew);
            }
            if (estadoCasoCodigoEstadoNew != null) {
                estadoCasoCodigoEstadoNew = em.getReference(estadoCasoCodigoEstadoNew.getClass(), estadoCasoCodigoEstadoNew.getCodigoEstado());
                cambioCaso.setEstadoCasoCodigoEstado(estadoCasoCodigoEstadoNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                cambioCaso.setUsuarioCedula(usuarioCedulaNew);
            }
            cambioCaso = em.merge(cambioCaso);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setCambioCaso(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setCambioCaso(cambioCaso);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (estadoCasoCodigoEstadoOld != null && !estadoCasoCodigoEstadoOld.equals(estadoCasoCodigoEstadoNew)) {
                estadoCasoCodigoEstadoOld.getCambioCasoCollection().remove(cambioCaso);
                estadoCasoCodigoEstadoOld = em.merge(estadoCasoCodigoEstadoOld);
            }
            if (estadoCasoCodigoEstadoNew != null && !estadoCasoCodigoEstadoNew.equals(estadoCasoCodigoEstadoOld)) {
                estadoCasoCodigoEstadoNew.getCambioCasoCollection().add(cambioCaso);
                estadoCasoCodigoEstadoNew = em.merge(estadoCasoCodigoEstadoNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getCambioCasoCollection().remove(cambioCaso);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getCambioCasoCollection().add(cambioCaso);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cambioCaso.getCasoPersonaIdCaso();
                if (findCambioCaso(id) == null) {
                    throw new NonexistentEntityException("The cambioCaso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CambioCaso cambioCaso;
            try {
                cambioCaso = em.getReference(CambioCaso.class, id);
                cambioCaso.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cambioCaso with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = cambioCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setCambioCaso(null);
                casoPersona = em.merge(casoPersona);
            }
            EstadoCaso estadoCasoCodigoEstado = cambioCaso.getEstadoCasoCodigoEstado();
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado.getCambioCasoCollection().remove(cambioCaso);
                estadoCasoCodigoEstado = em.merge(estadoCasoCodigoEstado);
            }
            Usuario usuarioCedula = cambioCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getCambioCasoCollection().remove(cambioCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(cambioCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CambioCaso> findCambioCasoEntities() {
        return findCambioCasoEntities(true, -1, -1);
    }

    public List<CambioCaso> findCambioCasoEntities(int maxResults, int firstResult) {
        return findCambioCasoEntities(false, maxResults, firstResult);
    }

    private List<CambioCaso> findCambioCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CambioCaso.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CambioCaso findCambioCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CambioCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCambioCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CambioCaso> rt = cq.from(CambioCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

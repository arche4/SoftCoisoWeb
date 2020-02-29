/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class FlujoCasoJpaController implements Serializable {

    public FlujoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FlujoCaso flujoCaso) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = flujoCaso.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            FlujoCaso oldFlujoCasoOfCasoPersona = casoPersonaOrphanCheck.getFlujoCaso();
            if (oldFlujoCasoOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type FlujoCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = flujoCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                flujoCaso.setCasoPersona(casoPersona);
            }
            EstadoCaso estadoCasoCodigoEstado = flujoCaso.getEstadoCasoCodigoEstado();
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado = em.getReference(estadoCasoCodigoEstado.getClass(), estadoCasoCodigoEstado.getCodigoEstado());
                flujoCaso.setEstadoCasoCodigoEstado(estadoCasoCodigoEstado);
            }
            Usuario usuarioCedula = flujoCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                flujoCaso.setUsuarioCedula(usuarioCedula);
            }
            em.persist(flujoCaso);
            if (casoPersona != null) {
                casoPersona.setFlujoCaso(flujoCaso);
                casoPersona = em.merge(casoPersona);
            }
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado.getFlujoCasoCollection().add(flujoCaso);
                estadoCasoCodigoEstado = em.merge(estadoCasoCodigoEstado);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getFlujoCasoCollection().add(flujoCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFlujoCaso(flujoCaso.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("FlujoCaso " + flujoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FlujoCaso flujoCaso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FlujoCaso persistentFlujoCaso = em.find(FlujoCaso.class, flujoCaso.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentFlujoCaso.getCasoPersona();
            CasoPersona casoPersonaNew = flujoCaso.getCasoPersona();
            EstadoCaso estadoCasoCodigoEstadoOld = persistentFlujoCaso.getEstadoCasoCodigoEstado();
            EstadoCaso estadoCasoCodigoEstadoNew = flujoCaso.getEstadoCasoCodigoEstado();
            Usuario usuarioCedulaOld = persistentFlujoCaso.getUsuarioCedula();
            Usuario usuarioCedulaNew = flujoCaso.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                FlujoCaso oldFlujoCasoOfCasoPersona = casoPersonaNew.getFlujoCaso();
                if (oldFlujoCasoOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type FlujoCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                flujoCaso.setCasoPersona(casoPersonaNew);
            }
            if (estadoCasoCodigoEstadoNew != null) {
                estadoCasoCodigoEstadoNew = em.getReference(estadoCasoCodigoEstadoNew.getClass(), estadoCasoCodigoEstadoNew.getCodigoEstado());
                flujoCaso.setEstadoCasoCodigoEstado(estadoCasoCodigoEstadoNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                flujoCaso.setUsuarioCedula(usuarioCedulaNew);
            }
            flujoCaso = em.merge(flujoCaso);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setFlujoCaso(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setFlujoCaso(flujoCaso);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (estadoCasoCodigoEstadoOld != null && !estadoCasoCodigoEstadoOld.equals(estadoCasoCodigoEstadoNew)) {
                estadoCasoCodigoEstadoOld.getFlujoCasoCollection().remove(flujoCaso);
                estadoCasoCodigoEstadoOld = em.merge(estadoCasoCodigoEstadoOld);
            }
            if (estadoCasoCodigoEstadoNew != null && !estadoCasoCodigoEstadoNew.equals(estadoCasoCodigoEstadoOld)) {
                estadoCasoCodigoEstadoNew.getFlujoCasoCollection().add(flujoCaso);
                estadoCasoCodigoEstadoNew = em.merge(estadoCasoCodigoEstadoNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getFlujoCasoCollection().remove(flujoCaso);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getFlujoCasoCollection().add(flujoCaso);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = flujoCaso.getCasoPersonaIdCaso();
                if (findFlujoCaso(id) == null) {
                    throw new NonexistentEntityException("The flujoCaso with id " + id + " no longer exists.");
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
            FlujoCaso flujoCaso;
            try {
                flujoCaso = em.getReference(FlujoCaso.class, id);
                flujoCaso.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flujoCaso with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = flujoCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setFlujoCaso(null);
                casoPersona = em.merge(casoPersona);
            }
            EstadoCaso estadoCasoCodigoEstado = flujoCaso.getEstadoCasoCodigoEstado();
            if (estadoCasoCodigoEstado != null) {
                estadoCasoCodigoEstado.getFlujoCasoCollection().remove(flujoCaso);
                estadoCasoCodigoEstado = em.merge(estadoCasoCodigoEstado);
            }
            Usuario usuarioCedula = flujoCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getFlujoCasoCollection().remove(flujoCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(flujoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FlujoCaso> findFlujoCasoEntities() {
        return findFlujoCasoEntities(true, -1, -1);
    }

    public List<FlujoCaso> findFlujoCasoEntities(int maxResults, int firstResult) {
        return findFlujoCasoEntities(false, maxResults, firstResult);
    }

    private List<FlujoCaso> findFlujoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FlujoCaso.class));
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

    public FlujoCaso findFlujoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FlujoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlujoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FlujoCaso> rt = cq.from(FlujoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

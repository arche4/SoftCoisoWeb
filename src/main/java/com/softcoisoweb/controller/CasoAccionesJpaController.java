/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.CasoAcciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class CasoAccionesJpaController implements Serializable {

    public CasoAccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoAcciones casoAcciones) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = casoAcciones.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            CasoAcciones oldCasoAccionesOfCasoPersona = casoPersonaOrphanCheck.getCasoAcciones();
            if (oldCasoAccionesOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type CasoAcciones whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = casoAcciones.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                casoAcciones.setCasoPersona(casoPersona);
            }
            Usuario usuarioCedula = casoAcciones.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                casoAcciones.setUsuarioCedula(usuarioCedula);
            }
            em.persist(casoAcciones);
            if (casoPersona != null) {
                casoPersona.setCasoAcciones(casoAcciones);
                casoPersona = em.merge(casoPersona);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getCasoAccionesCollection().add(casoAcciones);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCasoAcciones(casoAcciones.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("CasoAcciones " + casoAcciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoAcciones casoAcciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoAcciones persistentCasoAcciones = em.find(CasoAcciones.class, casoAcciones.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentCasoAcciones.getCasoPersona();
            CasoPersona casoPersonaNew = casoAcciones.getCasoPersona();
            Usuario usuarioCedulaOld = persistentCasoAcciones.getUsuarioCedula();
            Usuario usuarioCedulaNew = casoAcciones.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                CasoAcciones oldCasoAccionesOfCasoPersona = casoPersonaNew.getCasoAcciones();
                if (oldCasoAccionesOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type CasoAcciones whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                casoAcciones.setCasoPersona(casoPersonaNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                casoAcciones.setUsuarioCedula(usuarioCedulaNew);
            }
            casoAcciones = em.merge(casoAcciones);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setCasoAcciones(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setCasoAcciones(casoAcciones);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getCasoAccionesCollection().remove(casoAcciones);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getCasoAccionesCollection().add(casoAcciones);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = casoAcciones.getCasoPersonaIdCaso();
                if (findCasoAcciones(id) == null) {
                    throw new NonexistentEntityException("The casoAcciones with id " + id + " no longer exists.");
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
            CasoAcciones casoAcciones;
            try {
                casoAcciones = em.getReference(CasoAcciones.class, id);
                casoAcciones.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoAcciones with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = casoAcciones.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setCasoAcciones(null);
                casoPersona = em.merge(casoPersona);
            }
            Usuario usuarioCedula = casoAcciones.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getCasoAccionesCollection().remove(casoAcciones);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(casoAcciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoAcciones> findCasoAccionesEntities() {
        return findCasoAccionesEntities(true, -1, -1);
    }

    public List<CasoAcciones> findCasoAccionesEntities(int maxResults, int firstResult) {
        return findCasoAccionesEntities(false, maxResults, firstResult);
    }

    private List<CasoAcciones> findCasoAccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoAcciones.class));
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

    public CasoAcciones findCasoAcciones(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CasoAcciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoAccionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoAcciones> rt = cq.from(CasoAcciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

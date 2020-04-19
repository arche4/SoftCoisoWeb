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
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class ProcesoCalificacionJpaController implements Serializable {

    public ProcesoCalificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoCalificacion procesoCalificacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = procesoCalificacion.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            ProcesoCalificacion oldProcesoCalificacionOfCasoPersona = casoPersonaOrphanCheck.getProcesoCalificacion();
            if (oldProcesoCalificacionOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type ProcesoCalificacion whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = procesoCalificacion.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                procesoCalificacion.setCasoPersona(casoPersona);
            }
            Usuario usuarioCedula = procesoCalificacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                procesoCalificacion.setUsuarioCedula(usuarioCedula);
            }
            em.persist(procesoCalificacion);
            if (casoPersona != null) {
                casoPersona.setProcesoCalificacion(procesoCalificacion);
                casoPersona = em.merge(casoPersona);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getProcesoCalificacionCollection().add(procesoCalificacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesoCalificacion(procesoCalificacion.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("ProcesoCalificacion " + procesoCalificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoCalificacion procesoCalificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoCalificacion persistentProcesoCalificacion = em.find(ProcesoCalificacion.class, procesoCalificacion.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentProcesoCalificacion.getCasoPersona();
            CasoPersona casoPersonaNew = procesoCalificacion.getCasoPersona();
            Usuario usuarioCedulaOld = persistentProcesoCalificacion.getUsuarioCedula();
            Usuario usuarioCedulaNew = procesoCalificacion.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                ProcesoCalificacion oldProcesoCalificacionOfCasoPersona = casoPersonaNew.getProcesoCalificacion();
                if (oldProcesoCalificacionOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type ProcesoCalificacion whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                procesoCalificacion.setCasoPersona(casoPersonaNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                procesoCalificacion.setUsuarioCedula(usuarioCedulaNew);
            }
            procesoCalificacion = em.merge(procesoCalificacion);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setProcesoCalificacion(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setProcesoCalificacion(procesoCalificacion);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getProcesoCalificacionCollection().remove(procesoCalificacion);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getProcesoCalificacionCollection().add(procesoCalificacion);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = procesoCalificacion.getCasoPersonaIdCaso();
                if (findProcesoCalificacion(id) == null) {
                    throw new NonexistentEntityException("The procesoCalificacion with id " + id + " no longer exists.");
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
            ProcesoCalificacion procesoCalificacion;
            try {
                procesoCalificacion = em.getReference(ProcesoCalificacion.class, id);
                procesoCalificacion.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoCalificacion with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = procesoCalificacion.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setProcesoCalificacion(null);
                casoPersona = em.merge(casoPersona);
            }
            Usuario usuarioCedula = procesoCalificacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getProcesoCalificacionCollection().remove(procesoCalificacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(procesoCalificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcesoCalificacion> findProcesoCalificacionEntities() {
        return findProcesoCalificacionEntities(true, -1, -1);
    }

    public List<ProcesoCalificacion> findProcesoCalificacionEntities(int maxResults, int firstResult) {
        return findProcesoCalificacionEntities(false, maxResults, firstResult);
    }

    private List<ProcesoCalificacion> findProcesoCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcesoCalificacion.class));
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

    public ProcesoCalificacion findProcesoCalificacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcesoCalificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcesoCalificacion> rt = cq.from(ProcesoCalificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

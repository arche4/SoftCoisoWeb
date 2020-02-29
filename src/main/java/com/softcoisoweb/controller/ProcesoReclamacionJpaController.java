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
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class ProcesoReclamacionJpaController implements Serializable {

    public ProcesoReclamacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoReclamacion procesoReclamacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = procesoReclamacion.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            ProcesoReclamacion oldProcesoReclamacionOfCasoPersona = casoPersonaOrphanCheck.getProcesoReclamacion();
            if (oldProcesoReclamacionOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type ProcesoReclamacion whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = procesoReclamacion.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                procesoReclamacion.setCasoPersona(casoPersona);
            }
            Usuario usuarioCedula = procesoReclamacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                procesoReclamacion.setUsuarioCedula(usuarioCedula);
            }
            em.persist(procesoReclamacion);
            if (casoPersona != null) {
                casoPersona.setProcesoReclamacion(procesoReclamacion);
                casoPersona = em.merge(casoPersona);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getProcesoReclamacionCollection().add(procesoReclamacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesoReclamacion(procesoReclamacion.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("ProcesoReclamacion " + procesoReclamacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoReclamacion procesoReclamacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoReclamacion persistentProcesoReclamacion = em.find(ProcesoReclamacion.class, procesoReclamacion.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentProcesoReclamacion.getCasoPersona();
            CasoPersona casoPersonaNew = procesoReclamacion.getCasoPersona();
            Usuario usuarioCedulaOld = persistentProcesoReclamacion.getUsuarioCedula();
            Usuario usuarioCedulaNew = procesoReclamacion.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                ProcesoReclamacion oldProcesoReclamacionOfCasoPersona = casoPersonaNew.getProcesoReclamacion();
                if (oldProcesoReclamacionOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type ProcesoReclamacion whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                procesoReclamacion.setCasoPersona(casoPersonaNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                procesoReclamacion.setUsuarioCedula(usuarioCedulaNew);
            }
            procesoReclamacion = em.merge(procesoReclamacion);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setProcesoReclamacion(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setProcesoReclamacion(procesoReclamacion);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getProcesoReclamacionCollection().remove(procesoReclamacion);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getProcesoReclamacionCollection().add(procesoReclamacion);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = procesoReclamacion.getCasoPersonaIdCaso();
                if (findProcesoReclamacion(id) == null) {
                    throw new NonexistentEntityException("The procesoReclamacion with id " + id + " no longer exists.");
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
            ProcesoReclamacion procesoReclamacion;
            try {
                procesoReclamacion = em.getReference(ProcesoReclamacion.class, id);
                procesoReclamacion.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoReclamacion with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = procesoReclamacion.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setProcesoReclamacion(null);
                casoPersona = em.merge(casoPersona);
            }
            Usuario usuarioCedula = procesoReclamacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getProcesoReclamacionCollection().remove(procesoReclamacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(procesoReclamacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcesoReclamacion> findProcesoReclamacionEntities() {
        return findProcesoReclamacionEntities(true, -1, -1);
    }

    public List<ProcesoReclamacion> findProcesoReclamacionEntities(int maxResults, int firstResult) {
        return findProcesoReclamacionEntities(false, maxResults, firstResult);
    }

    private List<ProcesoReclamacion> findProcesoReclamacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcesoReclamacion.class));
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

    public ProcesoReclamacion findProcesoReclamacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcesoReclamacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoReclamacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcesoReclamacion> rt = cq.from(ProcesoReclamacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

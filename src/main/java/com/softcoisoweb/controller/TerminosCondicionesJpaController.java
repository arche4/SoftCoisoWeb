/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.TerminosCondiciones;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author manue
 */
public class TerminosCondicionesJpaController implements Serializable {

    public TerminosCondicionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TerminosCondiciones terminosCondiciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(terminosCondiciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TerminosCondiciones terminosCondiciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            terminosCondiciones = em.merge(terminosCondiciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = terminosCondiciones.getIdTerminosCondiciones();
                if (findTerminosCondiciones(id) == null) {
                    throw new NonexistentEntityException("The terminosCondiciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TerminosCondiciones terminosCondiciones;
            try {
                terminosCondiciones = em.getReference(TerminosCondiciones.class, id);
                terminosCondiciones.getIdTerminosCondiciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The terminosCondiciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(terminosCondiciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TerminosCondiciones> findTerminosCondicionesEntities() {
        return findTerminosCondicionesEntities(true, -1, -1);
    }

    public List<TerminosCondiciones> findTerminosCondicionesEntities(int maxResults, int firstResult) {
        return findTerminosCondicionesEntities(false, maxResults, firstResult);
    }

    private List<TerminosCondiciones> findTerminosCondicionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TerminosCondiciones.class));
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

    public TerminosCondiciones findTerminosCondiciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TerminosCondiciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getTerminosCondicionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TerminosCondiciones> rt = cq.from(TerminosCondiciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

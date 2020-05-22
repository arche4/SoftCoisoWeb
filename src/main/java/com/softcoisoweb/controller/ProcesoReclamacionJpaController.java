/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.ProcesoReclamacion;
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
public class ProcesoReclamacionJpaController implements Serializable {

    public ProcesoReclamacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoReclamacion procesoReclamacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(procesoReclamacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoReclamacion procesoReclamacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            procesoReclamacion = em.merge(procesoReclamacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procesoReclamacion.getCodigo();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoReclamacion procesoReclamacion;
            try {
                procesoReclamacion = em.getReference(ProcesoReclamacion.class, id);
                procesoReclamacion.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoReclamacion with id " + id + " no longer exists.", enfe);
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

    public ProcesoReclamacion findProcesoReclamacion(Integer id) {
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

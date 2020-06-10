/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.SeguimientoCaso;
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
public class SeguimientoCasoJpaController implements Serializable {

    public SeguimientoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeguimientoCaso seguimientoCaso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(seguimientoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeguimientoCaso seguimientoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seguimientoCaso = em.merge(seguimientoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seguimientoCaso.getCodigo();
                if (findSeguimientoCaso(id) == null) {
                    throw new NonexistentEntityException("The seguimientoCaso with id " + id + " no longer exists.");
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
            SeguimientoCaso seguimientoCaso;
            try {
                seguimientoCaso = em.getReference(SeguimientoCaso.class, id);
                seguimientoCaso.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguimientoCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(seguimientoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeguimientoCaso> findSeguimientoCasoEntities() {
        return findSeguimientoCasoEntities(true, -1, -1);
    }

    public List<SeguimientoCaso> findSeguimientoCasoEntities(int maxResults, int firstResult) {
        return findSeguimientoCasoEntities(false, maxResults, firstResult);
    }

    private List<SeguimientoCaso> findSeguimientoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeguimientoCaso.class));
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

    public SeguimientoCaso findSeguimientoCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeguimientoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguimientoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeguimientoCaso> rt = cq.from(SeguimientoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CambioEstadoHistorico;
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
public class CambioEstadoHistoricoJpaController implements Serializable {

    public CambioEstadoHistoricoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CambioEstadoHistorico cambioEstadoHistorico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cambioEstadoHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CambioEstadoHistorico cambioEstadoHistorico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cambioEstadoHistorico = em.merge(cambioEstadoHistorico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cambioEstadoHistorico.getIdCambioEstado();
                if (findCambioEstadoHistorico(id) == null) {
                    throw new NonexistentEntityException("The cambioEstadoHistorico with id " + id + " no longer exists.");
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
            CambioEstadoHistorico cambioEstadoHistorico;
            try {
                cambioEstadoHistorico = em.getReference(CambioEstadoHistorico.class, id);
                cambioEstadoHistorico.getIdCambioEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cambioEstadoHistorico with id " + id + " no longer exists.", enfe);
            }
            em.remove(cambioEstadoHistorico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CambioEstadoHistorico> findCambioEstadoHistoricoEntities() {
        return findCambioEstadoHistoricoEntities(true, -1, -1);
    }

    public List<CambioEstadoHistorico> findCambioEstadoHistoricoEntities(int maxResults, int firstResult) {
        return findCambioEstadoHistoricoEntities(false, maxResults, firstResult);
    }

    private List<CambioEstadoHistorico> findCambioEstadoHistoricoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CambioEstadoHistorico.class));
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

    public CambioEstadoHistorico findCambioEstadoHistorico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CambioEstadoHistorico.class, id);
        } finally {
            em.close();
        }
    }

    public int getCambioEstadoHistoricoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CambioEstadoHistorico> rt = cq.from(CambioEstadoHistorico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

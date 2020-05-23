/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoAcciones;
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
public class CasoAccionesJpaController implements Serializable {

    public CasoAccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoAcciones casoAcciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(casoAcciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoAcciones casoAcciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            casoAcciones = em.merge(casoAcciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = casoAcciones.getCodigo();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoAcciones casoAcciones;
            try {
                casoAcciones = em.getReference(CasoAcciones.class, id);
                casoAcciones.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoAcciones with id " + id + " no longer exists.", enfe);
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

    public CasoAcciones findCasoAcciones(Integer id) {
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

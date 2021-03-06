/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Arl;
import com.softcoisoweb.model.Persona;
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
public class ArlJpaController implements Serializable {

    public ArlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arl arl) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(arl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArl(arl.getCodigoArl()) != null) {
                throw new PreexistingEntityException("Arl " + arl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arl arl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            arl = em.merge(arl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = arl.getCodigoArl();
                if (findArl(id) == null) {
                    throw new NonexistentEntityException("The arl with id " + id + " no longer exists.");
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
            Arl arl;
            try {
                arl = em.getReference(Arl.class, id);
                arl.getCodigoArl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arl with id " + id + " no longer exists.", enfe);
            }
            em.remove(arl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arl> findArlEntities() {
        return findArlEntities(true, -1, -1);
    }

    public List<Arl> findArlEntities(int maxResults, int firstResult) {
        return findArlEntities(false, maxResults, firstResult);
    }

    private List<Arl> findArlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arl.class));
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

    public Arl findArl(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arl.class, id);
        } finally {
            em.close();
        }
    }

    public int getArlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arl> rt = cq.from(Arl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Persona> arlXpersona(String codigo) {
        EntityManager em = null;
        List<Persona> arlXpersona = null;
        try {
            String QuerySelect = "select * from persona where arl_codigo_arl = '" + codigo + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            arlXpersona = em.createNativeQuery(QuerySelect, Persona.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return arlXpersona;
    }
    
}

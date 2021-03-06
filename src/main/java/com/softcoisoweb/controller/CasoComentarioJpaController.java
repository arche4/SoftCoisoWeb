/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoComentario;
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
public class CasoComentarioJpaController implements Serializable {

    public CasoComentarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoComentario casoComentario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(casoComentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoComentario casoComentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            casoComentario = em.merge(casoComentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = casoComentario.getCodigo();
                if (findCasoComentario(id) == null) {
                    throw new NonexistentEntityException("The casoComentario with id " + id + " no longer exists.");
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
            CasoComentario casoComentario;
            try {
                casoComentario = em.getReference(CasoComentario.class, id);
                casoComentario.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoComentario with id " + id + " no longer exists.", enfe);
            }
            em.remove(casoComentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoComentario> findCasoComentarioEntities() {
        return findCasoComentarioEntities(true, -1, -1);
    }

    public List<CasoComentario> findCasoComentarioEntities(int maxResults, int firstResult) {
        return findCasoComentarioEntities(false, maxResults, firstResult);
    }

    private List<CasoComentario> findCasoComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoComentario.class));
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

    public CasoComentario findCasoComentario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CasoComentario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoComentario> rt = cq.from(CasoComentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CasoComentario> casoXcomentario(String codigoCaso) {
        EntityManager em = null;
        List<CasoComentario> listComentarios = null;
        try {
            String QuerySelect = "select * from caso_comentario where codigo_caso   =  '" + codigoCaso + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            listComentarios = em.createNativeQuery(QuerySelect, CasoComentario.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return listComentarios;
    }
    
}

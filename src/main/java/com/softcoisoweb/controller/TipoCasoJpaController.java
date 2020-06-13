/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.TipoCaso;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CacheStoreMode;
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
public class TipoCasoJpaController implements Serializable {

    public TipoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoCaso tipoCaso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoCaso(tipoCaso.getCodigoTipoCaso()) != null) {
                throw new PreexistingEntityException("TipoCaso " + tipoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoCaso tipoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoCaso = em.merge(tipoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoCaso.getCodigoTipoCaso();
                if (findTipoCaso(id) == null) {
                    throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.");
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
            TipoCaso tipoCaso;
            try {
                tipoCaso = em.getReference(TipoCaso.class, id);
                tipoCaso.getCodigoTipoCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoCaso> findTipoCasoEntities() {
        return findTipoCasoEntities(true, -1, -1);
    }

    public List<TipoCaso> findTipoCasoEntities(int maxResults, int firstResult) {
        return findTipoCasoEntities(false, maxResults, firstResult);
    }

    private List<TipoCaso> findTipoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoCaso.class));
            Query q = em.createQuery(cq);
            q.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoCaso findTipoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoCaso> rt = cq.from(TipoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CasoPersona> tipoXCaso(String codigo) {
        EntityManager em = null;
        List<CasoPersona> tipoXCaso = null;
        try {
            String QuerySelect = "select * from caso_persona where tipo_caso_codigo_tipo_caso = '" + codigo + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            tipoXCaso = em.createNativeQuery(QuerySelect, CasoPersona.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return tipoXCaso;
    }
    
}

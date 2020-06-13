/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
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
public class EstadoCasoJpaController implements Serializable {

    public EstadoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoCaso estadoCaso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoCaso(estadoCaso.getCodigoEstado()) != null) {
                throw new PreexistingEntityException("EstadoCaso " + estadoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoCaso estadoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoCaso = em.merge(estadoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estadoCaso.getCodigoEstado();
                if (findEstadoCaso(id) == null) {
                    throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.");
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
            EstadoCaso estadoCaso;
            try {
                estadoCaso = em.getReference(EstadoCaso.class, id);
                estadoCaso.getCodigoEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoCaso> findEstadoCasoEntities() {
        return findEstadoCasoEntities(true, -1, -1);
    }

    public List<EstadoCaso> findEstadoCasoEntities(int maxResults, int firstResult) {
        return findEstadoCasoEntities(false, maxResults, firstResult);
    }

    private List<EstadoCaso> findEstadoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoCaso.class));
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

    public EstadoCaso findEstadoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            em.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
            return em.find(EstadoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoCaso> rt = cq.from(EstadoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<FlujoCaso> EstadoxCasos(String codigo) {
        EntityManager em = null;
        List<FlujoCaso> estadoXcaso = null;
        try {
            String QuerySelect = "select * from flujo_caso where estado_caso_codigo_estado = '" + codigo + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            estadoXcaso = em.createNativeQuery(QuerySelect, FlujoCaso.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estadoXcaso;
    }

}

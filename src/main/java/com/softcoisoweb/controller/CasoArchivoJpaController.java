/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoArchivo;
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
public class CasoArchivoJpaController implements Serializable {

    public CasoArchivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoArchivo casoArchivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(casoArchivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoArchivo casoArchivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            casoArchivo = em.merge(casoArchivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = casoArchivo.getCodigo();
                if (findCasoArchivo(id) == null) {
                    throw new NonexistentEntityException("The casoArchivo with id " + id + " no longer exists.");
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
            CasoArchivo casoArchivo;
            try {
                casoArchivo = em.getReference(CasoArchivo.class, id);
                casoArchivo.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoArchivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(casoArchivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoArchivo> findCasoArchivoEntities() {
        return findCasoArchivoEntities(true, -1, -1);
    }

    public List<CasoArchivo> findCasoArchivoEntities(int maxResults, int firstResult) {
        return findCasoArchivoEntities(false, maxResults, firstResult);
    }

    private List<CasoArchivo> findCasoArchivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoArchivo.class));
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

    public CasoArchivo findCasoArchivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CasoArchivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoArchivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoArchivo> rt = cq.from(CasoArchivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CasoArchivo> casoXarchivo(String codigoCaso) {
        EntityManager em = null;
        List<CasoArchivo> ListArchivos = null;
        try {
            String QuerySelect = "select * from caso_archivo where codigo_caso   =  '" + codigoCaso + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            ListArchivos = em.createNativeQuery(QuerySelect, CasoArchivo.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ListArchivos;
    }
}

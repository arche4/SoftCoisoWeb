/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
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
public class FlujoCasoJpaController implements Serializable {

    public FlujoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FlujoCaso flujoCaso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(flujoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFlujoCaso(flujoCaso.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("FlujoCaso " + flujoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FlujoCaso flujoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flujoCaso = em.merge(flujoCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = flujoCaso.getCasoPersonaIdCaso();
                if (findFlujoCaso(id) == null) {
                    throw new NonexistentEntityException("The flujoCaso with id " + id + " no longer exists.");
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
            FlujoCaso flujoCaso;
            try {
                flujoCaso = em.getReference(FlujoCaso.class, id);
                flujoCaso.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flujoCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(flujoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FlujoCaso> findFlujoCasoEntities() {
        return findFlujoCasoEntities(true, -1, -1);
    }

    public List<FlujoCaso> findFlujoCasoEntities(int maxResults, int firstResult) {
        return findFlujoCasoEntities(false, maxResults, firstResult);
    }

    private List<FlujoCaso> findFlujoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FlujoCaso.class));
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

    public FlujoCaso findFlujoCaso(String id) {
        EntityManager em = getEntityManager();
        em.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        try {
            return em.find(FlujoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlujoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FlujoCaso> rt = cq.from(FlujoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void actualizarFlujoCaso(String casoId, String fechaActulización, String estado, String usuarioCedula) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            String Query = "UPDATE coiso_BDpdn.flujo_caso set fecha_actualizacion = '" + fechaActulización + "',"
                    + " usuario_cedula = '" + usuarioCedula + "',  estado_caso_codigo_estado = '" + estado + "' WHERE caso_persona_id_caso = '" + casoId + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(Query).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new NonexistentEntityException("Se presento inconvenientes al actualizar  el caso " + casoId + "El eror es: ", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}

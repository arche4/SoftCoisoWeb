/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.OrganizacionSindical;
import com.softcoisoweb.model.Persona;
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
public class OrganizacionSindicalJpaController implements Serializable {

    public OrganizacionSindicalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganizacionSindical organizacionSindical) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(organizacionSindical);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrganizacionSindical(organizacionSindical.getCodigoOrganizacion()) != null) {
                throw new PreexistingEntityException("OrganizacionSindical " + organizacionSindical + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrganizacionSindical organizacionSindical) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            organizacionSindical = em.merge(organizacionSindical);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = organizacionSindical.getCodigoOrganizacion();
                if (findOrganizacionSindical(id) == null) {
                    throw new NonexistentEntityException("The organizacionSindical with id " + id + " no longer exists.");
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
            OrganizacionSindical organizacionSindical;
            try {
                organizacionSindical = em.getReference(OrganizacionSindical.class, id);
                organizacionSindical.getCodigoOrganizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizacionSindical with id " + id + " no longer exists.", enfe);
            }
            em.remove(organizacionSindical);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrganizacionSindical> findOrganizacionSindicalEntities() {
        return findOrganizacionSindicalEntities(true, -1, -1);
    }

    public List<OrganizacionSindical> findOrganizacionSindicalEntities(int maxResults, int firstResult) {
        return findOrganizacionSindicalEntities(false, maxResults, firstResult);
    }

    private List<OrganizacionSindical> findOrganizacionSindicalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrganizacionSindical.class));
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

    public OrganizacionSindical findOrganizacionSindical(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrganizacionSindical.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacionSindicalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrganizacionSindical> rt = cq.from(OrganizacionSindical.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Persona> sindicatoXpersona(String codigo) {
        EntityManager em = null;
        List<Persona> SindicatoXpersona = null;
        try {
            String QuerySelect = "select * from persona where organizacion_sindical_codigo_organizacion = '" + codigo + "'";
            em = getEntityManager();
            em.getTransaction().begin();
            SindicatoXpersona = em.createNativeQuery(QuerySelect, Persona.class).getResultList();
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return SindicatoXpersona;
    }
    
}

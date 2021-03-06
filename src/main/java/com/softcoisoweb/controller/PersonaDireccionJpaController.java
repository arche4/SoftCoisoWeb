/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.PersonaDireccion;
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
public class PersonaDireccionJpaController implements Serializable {

    public PersonaDireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonaDireccion personaDireccion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personaDireccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonaDireccion(personaDireccion.getPersonaCedula()) != null) {
                throw new PreexistingEntityException("PersonaDireccion " + personaDireccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonaDireccion personaDireccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personaDireccion = em.merge(personaDireccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personaDireccion.getPersonaCedula();
                if (findPersonaDireccion(id) == null) {
                    throw new NonexistentEntityException("The personaDireccion with id " + id + " no longer exists.");
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
            PersonaDireccion personaDireccion;
            try {
                personaDireccion = em.getReference(PersonaDireccion.class, id);
                personaDireccion.getPersonaCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaDireccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(personaDireccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonaDireccion> findPersonaDireccionEntities() {
        return findPersonaDireccionEntities(true, -1, -1);
    }

    public List<PersonaDireccion> findPersonaDireccionEntities(int maxResults, int firstResult) {
        return findPersonaDireccionEntities(false, maxResults, firstResult);
    }

    private List<PersonaDireccion> findPersonaDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonaDireccion.class));
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

    public PersonaDireccion findPersonaDireccion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonaDireccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonaDireccion> rt = cq.from(PersonaDireccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Formacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class FormacionJpaController implements Serializable {

    public FormacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formacion formacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioCedula = formacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                formacion.setUsuarioCedula(usuarioCedula);
            }
            em.persist(formacion);
            if (usuarioCedula != null) {
                usuarioCedula.getFormacionCollection().add(formacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormacion(formacion.getIdFormacion()) != null) {
                throw new PreexistingEntityException("Formacion " + formacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formacion formacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formacion persistentFormacion = em.find(Formacion.class, formacion.getIdFormacion());
            Usuario usuarioCedulaOld = persistentFormacion.getUsuarioCedula();
            Usuario usuarioCedulaNew = formacion.getUsuarioCedula();
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                formacion.setUsuarioCedula(usuarioCedulaNew);
            }
            formacion = em.merge(formacion);
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getFormacionCollection().remove(formacion);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getFormacionCollection().add(formacion);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formacion.getIdFormacion();
                if (findFormacion(id) == null) {
                    throw new NonexistentEntityException("The formacion with id " + id + " no longer exists.");
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
            Formacion formacion;
            try {
                formacion = em.getReference(Formacion.class, id);
                formacion.getIdFormacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioCedula = formacion.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getFormacionCollection().remove(formacion);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(formacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formacion> findFormacionEntities() {
        return findFormacionEntities(true, -1, -1);
    }

    public List<Formacion> findFormacionEntities(int maxResults, int firstResult) {
        return findFormacionEntities(false, maxResults, firstResult);
    }

    private List<Formacion> findFormacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formacion.class));
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

    public Formacion findFormacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formacion> rt = cq.from(Formacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

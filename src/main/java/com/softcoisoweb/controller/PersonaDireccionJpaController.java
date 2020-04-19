/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.PersonaDireccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public void create(PersonaDireccion personaDireccion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Persona personaOrphanCheck = personaDireccion.getPersona();
        if (personaOrphanCheck != null) {
            PersonaDireccion oldPersonaDireccionOfPersona = personaOrphanCheck.getPersonaDireccion();
            if (oldPersonaDireccionOfPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Persona " + personaOrphanCheck + " already has an item of type PersonaDireccion whose persona column cannot be null. Please make another selection for the persona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona = personaDireccion.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getCedula());
                personaDireccion.setPersona(persona);
            }
            em.persist(personaDireccion);
            if (persona != null) {
                persona.setPersonaDireccion(personaDireccion);
                persona = em.merge(persona);
            }
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

    public void edit(PersonaDireccion personaDireccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonaDireccion persistentPersonaDireccion = em.find(PersonaDireccion.class, personaDireccion.getPersonaCedula());
            Persona personaOld = persistentPersonaDireccion.getPersona();
            Persona personaNew = personaDireccion.getPersona();
            List<String> illegalOrphanMessages = null;
            if (personaNew != null && !personaNew.equals(personaOld)) {
                PersonaDireccion oldPersonaDireccionOfPersona = personaNew.getPersonaDireccion();
                if (oldPersonaDireccionOfPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Persona " + personaNew + " already has an item of type PersonaDireccion whose persona column cannot be null. Please make another selection for the persona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getCedula());
                personaDireccion.setPersona(personaNew);
            }
            personaDireccion = em.merge(personaDireccion);
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setPersonaDireccion(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.setPersonaDireccion(personaDireccion);
                personaNew = em.merge(personaNew);
            }
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
            Persona persona = personaDireccion.getPersona();
            if (persona != null) {
                persona.setPersonaDireccion(null);
                persona = em.merge(persona);
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

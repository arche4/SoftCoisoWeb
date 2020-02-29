/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Afp;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class AfpJpaController implements Serializable {

    public AfpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Afp afp) throws PreexistingEntityException, Exception {
        if (afp.getPersonaCollection() == null) {
            afp.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : afp.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getCedula());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            afp.setPersonaCollection(attachedPersonaCollection);
            em.persist(afp);
            for (Persona personaCollectionPersona : afp.getPersonaCollection()) {
                Afp oldAfpCodigoAfpOfPersonaCollectionPersona = personaCollectionPersona.getAfpCodigoAfp();
                personaCollectionPersona.setAfpCodigoAfp(afp);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldAfpCodigoAfpOfPersonaCollectionPersona != null) {
                    oldAfpCodigoAfpOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldAfpCodigoAfpOfPersonaCollectionPersona = em.merge(oldAfpCodigoAfpOfPersonaCollectionPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAfp(afp.getCodigoAfp()) != null) {
                throw new PreexistingEntityException("Afp " + afp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Afp afp) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp persistentAfp = em.find(Afp.class, afp.getCodigoAfp());
            Collection<Persona> personaCollectionOld = persistentAfp.getPersonaCollection();
            Collection<Persona> personaCollectionNew = afp.getPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaCollectionOldPersona + " since its afpCodigoAfp field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
            for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
                personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getCedula());
                attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
            }
            personaCollectionNew = attachedPersonaCollectionNew;
            afp.setPersonaCollection(personaCollectionNew);
            afp = em.merge(afp);
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    Afp oldAfpCodigoAfpOfPersonaCollectionNewPersona = personaCollectionNewPersona.getAfpCodigoAfp();
                    personaCollectionNewPersona.setAfpCodigoAfp(afp);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldAfpCodigoAfpOfPersonaCollectionNewPersona != null && !oldAfpCodigoAfpOfPersonaCollectionNewPersona.equals(afp)) {
                        oldAfpCodigoAfpOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldAfpCodigoAfpOfPersonaCollectionNewPersona = em.merge(oldAfpCodigoAfpOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = afp.getCodigoAfp();
                if (findAfp(id) == null) {
                    throw new NonexistentEntityException("The afp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp afp;
            try {
                afp = em.getReference(Afp.class, id);
                afp.getCodigoAfp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The afp with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persona> personaCollectionOrphanCheck = afp.getPersonaCollection();
            for (Persona personaCollectionOrphanCheckPersona : personaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Afp (" + afp + ") cannot be destroyed since the Persona " + personaCollectionOrphanCheckPersona + " in its personaCollection field has a non-nullable afpCodigoAfp field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(afp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Afp> findAfpEntities() {
        return findAfpEntities(true, -1, -1);
    }

    public List<Afp> findAfpEntities(int maxResults, int firstResult) {
        return findAfpEntities(false, maxResults, firstResult);
    }

    private List<Afp> findAfpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Afp.class));
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

    public Afp findAfp(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Afp.class, id);
        } finally {
            em.close();
        }
    }

    public int getAfpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Afp> rt = cq.from(Afp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

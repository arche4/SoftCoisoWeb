/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Eps;
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
public class EpsJpaController implements Serializable {

    public EpsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eps eps) throws PreexistingEntityException, Exception {
        if (eps.getPersonaCollection() == null) {
            eps.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : eps.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getCedula());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            eps.setPersonaCollection(attachedPersonaCollection);
            em.persist(eps);
            for (Persona personaCollectionPersona : eps.getPersonaCollection()) {
                Eps oldEpsCodigoEpsOfPersonaCollectionPersona = personaCollectionPersona.getEpsCodigoEps();
                personaCollectionPersona.setEpsCodigoEps(eps);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldEpsCodigoEpsOfPersonaCollectionPersona != null) {
                    oldEpsCodigoEpsOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldEpsCodigoEpsOfPersonaCollectionPersona = em.merge(oldEpsCodigoEpsOfPersonaCollectionPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEps(eps.getCodigoEps()) != null) {
                throw new PreexistingEntityException("Eps " + eps + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eps eps) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Eps persistentEps = em.find(Eps.class, eps.getCodigoEps());
            Collection<Persona> personaCollectionOld = persistentEps.getPersonaCollection();
            Collection<Persona> personaCollectionNew = eps.getPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaCollectionOldPersona + " since its epsCodigoEps field is not nullable.");
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
            eps.setPersonaCollection(personaCollectionNew);
            eps = em.merge(eps);
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    Eps oldEpsCodigoEpsOfPersonaCollectionNewPersona = personaCollectionNewPersona.getEpsCodigoEps();
                    personaCollectionNewPersona.setEpsCodigoEps(eps);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldEpsCodigoEpsOfPersonaCollectionNewPersona != null && !oldEpsCodigoEpsOfPersonaCollectionNewPersona.equals(eps)) {
                        oldEpsCodigoEpsOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldEpsCodigoEpsOfPersonaCollectionNewPersona = em.merge(oldEpsCodigoEpsOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = eps.getCodigoEps();
                if (findEps(id) == null) {
                    throw new NonexistentEntityException("The eps with id " + id + " no longer exists.");
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
            Eps eps;
            try {
                eps = em.getReference(Eps.class, id);
                eps.getCodigoEps();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eps with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persona> personaCollectionOrphanCheck = eps.getPersonaCollection();
            for (Persona personaCollectionOrphanCheckPersona : personaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Eps (" + eps + ") cannot be destroyed since the Persona " + personaCollectionOrphanCheckPersona + " in its personaCollection field has a non-nullable epsCodigoEps field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(eps);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eps> findEpsEntities() {
        return findEpsEntities(true, -1, -1);
    }

    public List<Eps> findEpsEntities(int maxResults, int firstResult) {
        return findEpsEntities(false, maxResults, firstResult);
    }

    private List<Eps> findEpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eps.class));
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

    public Eps findEps(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eps.class, id);
        } finally {
            em.close();
        }
    }

    public int getEpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eps> rt = cq.from(Eps.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Arl;
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
public class ArlJpaController implements Serializable {

    public ArlJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arl arl) throws PreexistingEntityException, Exception {
        if (arl.getPersonaCollection() == null) {
            arl.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : arl.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getCedula());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            arl.setPersonaCollection(attachedPersonaCollection);
            em.persist(arl);
            for (Persona personaCollectionPersona : arl.getPersonaCollection()) {
                Arl oldArlCodigoArlOfPersonaCollectionPersona = personaCollectionPersona.getArlCodigoArl();
                personaCollectionPersona.setArlCodigoArl(arl);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldArlCodigoArlOfPersonaCollectionPersona != null) {
                    oldArlCodigoArlOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldArlCodigoArlOfPersonaCollectionPersona = em.merge(oldArlCodigoArlOfPersonaCollectionPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArl(arl.getCodigoArl()) != null) {
                throw new PreexistingEntityException("Arl " + arl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arl arl) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Arl persistentArl = em.find(Arl.class, arl.getCodigoArl());
            Collection<Persona> personaCollectionOld = persistentArl.getPersonaCollection();
            Collection<Persona> personaCollectionNew = arl.getPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaCollectionOldPersona + " since its arlCodigoArl field is not nullable.");
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
            arl.setPersonaCollection(personaCollectionNew);
            arl = em.merge(arl);
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    Arl oldArlCodigoArlOfPersonaCollectionNewPersona = personaCollectionNewPersona.getArlCodigoArl();
                    personaCollectionNewPersona.setArlCodigoArl(arl);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldArlCodigoArlOfPersonaCollectionNewPersona != null && !oldArlCodigoArlOfPersonaCollectionNewPersona.equals(arl)) {
                        oldArlCodigoArlOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldArlCodigoArlOfPersonaCollectionNewPersona = em.merge(oldArlCodigoArlOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = arl.getCodigoArl();
                if (findArl(id) == null) {
                    throw new NonexistentEntityException("The arl with id " + id + " no longer exists.");
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
            Arl arl;
            try {
                arl = em.getReference(Arl.class, id);
                arl.getCodigoArl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arl with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persona> personaCollectionOrphanCheck = arl.getPersonaCollection();
            for (Persona personaCollectionOrphanCheckPersona : personaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Arl (" + arl + ") cannot be destroyed since the Persona " + personaCollectionOrphanCheckPersona + " in its personaCollection field has a non-nullable arlCodigoArl field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(arl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arl> findArlEntities() {
        return findArlEntities(true, -1, -1);
    }

    public List<Arl> findArlEntities(int maxResults, int firstResult) {
        return findArlEntities(false, maxResults, firstResult);
    }

    private List<Arl> findArlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arl.class));
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

    public Arl findArl(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arl.class, id);
        } finally {
            em.close();
        }
    }

    public int getArlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arl> rt = cq.from(Arl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

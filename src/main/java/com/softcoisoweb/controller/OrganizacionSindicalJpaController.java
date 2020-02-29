/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.OrganizacionSindical;
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
public class OrganizacionSindicalJpaController implements Serializable {

    public OrganizacionSindicalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganizacionSindical organizacionSindical) throws PreexistingEntityException, Exception {
        if (organizacionSindical.getPersonaCollection() == null) {
            organizacionSindical.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : organizacionSindical.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getCedula());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            organizacionSindical.setPersonaCollection(attachedPersonaCollection);
            em.persist(organizacionSindical);
            for (Persona personaCollectionPersona : organizacionSindical.getPersonaCollection()) {
                OrganizacionSindical oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionPersona = personaCollectionPersona.getOrganizacionSindicalCodigoOrganizacion();
                personaCollectionPersona.setOrganizacionSindicalCodigoOrganizacion(organizacionSindical);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionPersona != null) {
                    oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionPersona = em.merge(oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionPersona);
                }
            }
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

    public void edit(OrganizacionSindical organizacionSindical) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrganizacionSindical persistentOrganizacionSindical = em.find(OrganizacionSindical.class, organizacionSindical.getCodigoOrganizacion());
            Collection<Persona> personaCollectionOld = persistentOrganizacionSindical.getPersonaCollection();
            Collection<Persona> personaCollectionNew = organizacionSindical.getPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaCollectionOldPersona + " since its organizacionSindicalCodigoOrganizacion field is not nullable.");
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
            organizacionSindical.setPersonaCollection(personaCollectionNew);
            organizacionSindical = em.merge(organizacionSindical);
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    OrganizacionSindical oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona = personaCollectionNewPersona.getOrganizacionSindicalCodigoOrganizacion();
                    personaCollectionNewPersona.setOrganizacionSindicalCodigoOrganizacion(organizacionSindical);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona != null && !oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona.equals(organizacionSindical)) {
                        oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona = em.merge(oldOrganizacionSindicalCodigoOrganizacionOfPersonaCollectionNewPersona);
                    }
                }
            }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Persona> personaCollectionOrphanCheck = organizacionSindical.getPersonaCollection();
            for (Persona personaCollectionOrphanCheckPersona : personaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrganizacionSindical (" + organizacionSindical + ") cannot be destroyed since the Persona " + personaCollectionOrphanCheckPersona + " in its personaCollection field has a non-nullable organizacionSindicalCodigoOrganizacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
    
}

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
import com.softcoisoweb.model.TipoContrato;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class TipoContratoJpaController implements Serializable {

    public TipoContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoContrato tipoContrato) throws PreexistingEntityException, Exception {
        if (tipoContrato.getPersonaCollection() == null) {
            tipoContrato.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : tipoContrato.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getCedula());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            tipoContrato.setPersonaCollection(attachedPersonaCollection);
            em.persist(tipoContrato);
            for (Persona personaCollectionPersona : tipoContrato.getPersonaCollection()) {
                TipoContrato oldTipoContratoCodigoTipoContratoOfPersonaCollectionPersona = personaCollectionPersona.getTipoContratoCodigoTipoContrato();
                personaCollectionPersona.setTipoContratoCodigoTipoContrato(tipoContrato);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldTipoContratoCodigoTipoContratoOfPersonaCollectionPersona != null) {
                    oldTipoContratoCodigoTipoContratoOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldTipoContratoCodigoTipoContratoOfPersonaCollectionPersona = em.merge(oldTipoContratoCodigoTipoContratoOfPersonaCollectionPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoContrato(tipoContrato.getCodigoTipoContrato()) != null) {
                throw new PreexistingEntityException("TipoContrato " + tipoContrato + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoContrato tipoContrato) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoContrato persistentTipoContrato = em.find(TipoContrato.class, tipoContrato.getCodigoTipoContrato());
            Collection<Persona> personaCollectionOld = persistentTipoContrato.getPersonaCollection();
            Collection<Persona> personaCollectionNew = tipoContrato.getPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaCollectionOldPersona + " since its tipoContratoCodigoTipoContrato field is not nullable.");
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
            tipoContrato.setPersonaCollection(personaCollectionNew);
            tipoContrato = em.merge(tipoContrato);
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    TipoContrato oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona = personaCollectionNewPersona.getTipoContratoCodigoTipoContrato();
                    personaCollectionNewPersona.setTipoContratoCodigoTipoContrato(tipoContrato);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona != null && !oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona.equals(tipoContrato)) {
                        oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona = em.merge(oldTipoContratoCodigoTipoContratoOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoContrato.getCodigoTipoContrato();
                if (findTipoContrato(id) == null) {
                    throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.");
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
            TipoContrato tipoContrato;
            try {
                tipoContrato = em.getReference(TipoContrato.class, id);
                tipoContrato.getCodigoTipoContrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoContrato with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persona> personaCollectionOrphanCheck = tipoContrato.getPersonaCollection();
            for (Persona personaCollectionOrphanCheckPersona : personaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoContrato (" + tipoContrato + ") cannot be destroyed since the Persona " + personaCollectionOrphanCheckPersona + " in its personaCollection field has a non-nullable tipoContratoCodigoTipoContrato field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoContrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoContrato> findTipoContratoEntities() {
        return findTipoContratoEntities(true, -1, -1);
    }

    public List<TipoContrato> findTipoContratoEntities(int maxResults, int firstResult) {
        return findTipoContratoEntities(false, maxResults, firstResult);
    }

    private List<TipoContrato> findTipoContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoContrato.class));
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

    public TipoContrato findTipoContrato(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoContrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoContrato> rt = cq.from(TipoContrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

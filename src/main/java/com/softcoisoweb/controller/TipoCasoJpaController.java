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
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.TipoCaso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class TipoCasoJpaController implements Serializable {

    public TipoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoCaso tipoCaso) throws PreexistingEntityException, Exception {
        if (tipoCaso.getCasoPersonaCollection() == null) {
            tipoCaso.setCasoPersonaCollection(new ArrayList<CasoPersona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CasoPersona> attachedCasoPersonaCollection = new ArrayList<CasoPersona>();
            for (CasoPersona casoPersonaCollectionCasoPersonaToAttach : tipoCaso.getCasoPersonaCollection()) {
                casoPersonaCollectionCasoPersonaToAttach = em.getReference(casoPersonaCollectionCasoPersonaToAttach.getClass(), casoPersonaCollectionCasoPersonaToAttach.getIdCaso());
                attachedCasoPersonaCollection.add(casoPersonaCollectionCasoPersonaToAttach);
            }
            tipoCaso.setCasoPersonaCollection(attachedCasoPersonaCollection);
            em.persist(tipoCaso);
            for (CasoPersona casoPersonaCollectionCasoPersona : tipoCaso.getCasoPersonaCollection()) {
                TipoCaso oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionCasoPersona = casoPersonaCollectionCasoPersona.getTipoCasoCodigoTipoCaso();
                casoPersonaCollectionCasoPersona.setTipoCasoCodigoTipoCaso(tipoCaso);
                casoPersonaCollectionCasoPersona = em.merge(casoPersonaCollectionCasoPersona);
                if (oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionCasoPersona != null) {
                    oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionCasoPersona.getCasoPersonaCollection().remove(casoPersonaCollectionCasoPersona);
                    oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionCasoPersona = em.merge(oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionCasoPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoCaso(tipoCaso.getCodigoTipoCaso()) != null) {
                throw new PreexistingEntityException("TipoCaso " + tipoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoCaso tipoCaso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoCaso persistentTipoCaso = em.find(TipoCaso.class, tipoCaso.getCodigoTipoCaso());
            Collection<CasoPersona> casoPersonaCollectionOld = persistentTipoCaso.getCasoPersonaCollection();
            Collection<CasoPersona> casoPersonaCollectionNew = tipoCaso.getCasoPersonaCollection();
            List<String> illegalOrphanMessages = null;
            for (CasoPersona casoPersonaCollectionOldCasoPersona : casoPersonaCollectionOld) {
                if (!casoPersonaCollectionNew.contains(casoPersonaCollectionOldCasoPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CasoPersona " + casoPersonaCollectionOldCasoPersona + " since its tipoCasoCodigoTipoCaso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CasoPersona> attachedCasoPersonaCollectionNew = new ArrayList<CasoPersona>();
            for (CasoPersona casoPersonaCollectionNewCasoPersonaToAttach : casoPersonaCollectionNew) {
                casoPersonaCollectionNewCasoPersonaToAttach = em.getReference(casoPersonaCollectionNewCasoPersonaToAttach.getClass(), casoPersonaCollectionNewCasoPersonaToAttach.getIdCaso());
                attachedCasoPersonaCollectionNew.add(casoPersonaCollectionNewCasoPersonaToAttach);
            }
            casoPersonaCollectionNew = attachedCasoPersonaCollectionNew;
            tipoCaso.setCasoPersonaCollection(casoPersonaCollectionNew);
            tipoCaso = em.merge(tipoCaso);
            for (CasoPersona casoPersonaCollectionNewCasoPersona : casoPersonaCollectionNew) {
                if (!casoPersonaCollectionOld.contains(casoPersonaCollectionNewCasoPersona)) {
                    TipoCaso oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona = casoPersonaCollectionNewCasoPersona.getTipoCasoCodigoTipoCaso();
                    casoPersonaCollectionNewCasoPersona.setTipoCasoCodigoTipoCaso(tipoCaso);
                    casoPersonaCollectionNewCasoPersona = em.merge(casoPersonaCollectionNewCasoPersona);
                    if (oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona != null && !oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona.equals(tipoCaso)) {
                        oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona.getCasoPersonaCollection().remove(casoPersonaCollectionNewCasoPersona);
                        oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona = em.merge(oldTipoCasoCodigoTipoCasoOfCasoPersonaCollectionNewCasoPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoCaso.getCodigoTipoCaso();
                if (findTipoCaso(id) == null) {
                    throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.");
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
            TipoCaso tipoCaso;
            try {
                tipoCaso = em.getReference(TipoCaso.class, id);
                tipoCaso.getCodigoTipoCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CasoPersona> casoPersonaCollectionOrphanCheck = tipoCaso.getCasoPersonaCollection();
            for (CasoPersona casoPersonaCollectionOrphanCheckCasoPersona : casoPersonaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoCaso (" + tipoCaso + ") cannot be destroyed since the CasoPersona " + casoPersonaCollectionOrphanCheckCasoPersona + " in its casoPersonaCollection field has a non-nullable tipoCasoCodigoTipoCaso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoCaso> findTipoCasoEntities() {
        return findTipoCasoEntities(true, -1, -1);
    }

    public List<TipoCaso> findTipoCasoEntities(int maxResults, int firstResult) {
        return findTipoCasoEntities(false, maxResults, firstResult);
    }

    private List<TipoCaso> findTipoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoCaso.class));
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

    public TipoCaso findTipoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoCaso> rt = cq.from(TipoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

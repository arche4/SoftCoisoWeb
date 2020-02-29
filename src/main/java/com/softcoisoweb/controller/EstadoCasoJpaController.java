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
import com.softcoisoweb.model.FlujoCaso;
import java.util.ArrayList;
import java.util.Collection;
import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.EstadoCaso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class EstadoCasoJpaController implements Serializable {

    public EstadoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoCaso estadoCaso) throws PreexistingEntityException, Exception {
        if (estadoCaso.getFlujoCasoCollection() == null) {
            estadoCaso.setFlujoCasoCollection(new ArrayList<FlujoCaso>());
        }
        if (estadoCaso.getCambioCasoCollection() == null) {
            estadoCaso.setCambioCasoCollection(new ArrayList<CambioCaso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FlujoCaso> attachedFlujoCasoCollection = new ArrayList<FlujoCaso>();
            for (FlujoCaso flujoCasoCollectionFlujoCasoToAttach : estadoCaso.getFlujoCasoCollection()) {
                flujoCasoCollectionFlujoCasoToAttach = em.getReference(flujoCasoCollectionFlujoCasoToAttach.getClass(), flujoCasoCollectionFlujoCasoToAttach.getCasoPersonaIdCaso());
                attachedFlujoCasoCollection.add(flujoCasoCollectionFlujoCasoToAttach);
            }
            estadoCaso.setFlujoCasoCollection(attachedFlujoCasoCollection);
            Collection<CambioCaso> attachedCambioCasoCollection = new ArrayList<CambioCaso>();
            for (CambioCaso cambioCasoCollectionCambioCasoToAttach : estadoCaso.getCambioCasoCollection()) {
                cambioCasoCollectionCambioCasoToAttach = em.getReference(cambioCasoCollectionCambioCasoToAttach.getClass(), cambioCasoCollectionCambioCasoToAttach.getCasoPersonaIdCaso());
                attachedCambioCasoCollection.add(cambioCasoCollectionCambioCasoToAttach);
            }
            estadoCaso.setCambioCasoCollection(attachedCambioCasoCollection);
            em.persist(estadoCaso);
            for (FlujoCaso flujoCasoCollectionFlujoCaso : estadoCaso.getFlujoCasoCollection()) {
                EstadoCaso oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionFlujoCaso = flujoCasoCollectionFlujoCaso.getEstadoCasoCodigoEstado();
                flujoCasoCollectionFlujoCaso.setEstadoCasoCodigoEstado(estadoCaso);
                flujoCasoCollectionFlujoCaso = em.merge(flujoCasoCollectionFlujoCaso);
                if (oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionFlujoCaso != null) {
                    oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionFlujoCaso.getFlujoCasoCollection().remove(flujoCasoCollectionFlujoCaso);
                    oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionFlujoCaso = em.merge(oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionFlujoCaso);
                }
            }
            for (CambioCaso cambioCasoCollectionCambioCaso : estadoCaso.getCambioCasoCollection()) {
                EstadoCaso oldEstadoCasoCodigoEstadoOfCambioCasoCollectionCambioCaso = cambioCasoCollectionCambioCaso.getEstadoCasoCodigoEstado();
                cambioCasoCollectionCambioCaso.setEstadoCasoCodigoEstado(estadoCaso);
                cambioCasoCollectionCambioCaso = em.merge(cambioCasoCollectionCambioCaso);
                if (oldEstadoCasoCodigoEstadoOfCambioCasoCollectionCambioCaso != null) {
                    oldEstadoCasoCodigoEstadoOfCambioCasoCollectionCambioCaso.getCambioCasoCollection().remove(cambioCasoCollectionCambioCaso);
                    oldEstadoCasoCodigoEstadoOfCambioCasoCollectionCambioCaso = em.merge(oldEstadoCasoCodigoEstadoOfCambioCasoCollectionCambioCaso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoCaso(estadoCaso.getCodigoEstado()) != null) {
                throw new PreexistingEntityException("EstadoCaso " + estadoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoCaso estadoCaso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoCaso persistentEstadoCaso = em.find(EstadoCaso.class, estadoCaso.getCodigoEstado());
            Collection<FlujoCaso> flujoCasoCollectionOld = persistentEstadoCaso.getFlujoCasoCollection();
            Collection<FlujoCaso> flujoCasoCollectionNew = estadoCaso.getFlujoCasoCollection();
            Collection<CambioCaso> cambioCasoCollectionOld = persistentEstadoCaso.getCambioCasoCollection();
            Collection<CambioCaso> cambioCasoCollectionNew = estadoCaso.getCambioCasoCollection();
            List<String> illegalOrphanMessages = null;
            for (FlujoCaso flujoCasoCollectionOldFlujoCaso : flujoCasoCollectionOld) {
                if (!flujoCasoCollectionNew.contains(flujoCasoCollectionOldFlujoCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FlujoCaso " + flujoCasoCollectionOldFlujoCaso + " since its estadoCasoCodigoEstado field is not nullable.");
                }
            }
            for (CambioCaso cambioCasoCollectionOldCambioCaso : cambioCasoCollectionOld) {
                if (!cambioCasoCollectionNew.contains(cambioCasoCollectionOldCambioCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CambioCaso " + cambioCasoCollectionOldCambioCaso + " since its estadoCasoCodigoEstado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<FlujoCaso> attachedFlujoCasoCollectionNew = new ArrayList<FlujoCaso>();
            for (FlujoCaso flujoCasoCollectionNewFlujoCasoToAttach : flujoCasoCollectionNew) {
                flujoCasoCollectionNewFlujoCasoToAttach = em.getReference(flujoCasoCollectionNewFlujoCasoToAttach.getClass(), flujoCasoCollectionNewFlujoCasoToAttach.getCasoPersonaIdCaso());
                attachedFlujoCasoCollectionNew.add(flujoCasoCollectionNewFlujoCasoToAttach);
            }
            flujoCasoCollectionNew = attachedFlujoCasoCollectionNew;
            estadoCaso.setFlujoCasoCollection(flujoCasoCollectionNew);
            Collection<CambioCaso> attachedCambioCasoCollectionNew = new ArrayList<CambioCaso>();
            for (CambioCaso cambioCasoCollectionNewCambioCasoToAttach : cambioCasoCollectionNew) {
                cambioCasoCollectionNewCambioCasoToAttach = em.getReference(cambioCasoCollectionNewCambioCasoToAttach.getClass(), cambioCasoCollectionNewCambioCasoToAttach.getCasoPersonaIdCaso());
                attachedCambioCasoCollectionNew.add(cambioCasoCollectionNewCambioCasoToAttach);
            }
            cambioCasoCollectionNew = attachedCambioCasoCollectionNew;
            estadoCaso.setCambioCasoCollection(cambioCasoCollectionNew);
            estadoCaso = em.merge(estadoCaso);
            for (FlujoCaso flujoCasoCollectionNewFlujoCaso : flujoCasoCollectionNew) {
                if (!flujoCasoCollectionOld.contains(flujoCasoCollectionNewFlujoCaso)) {
                    EstadoCaso oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso = flujoCasoCollectionNewFlujoCaso.getEstadoCasoCodigoEstado();
                    flujoCasoCollectionNewFlujoCaso.setEstadoCasoCodigoEstado(estadoCaso);
                    flujoCasoCollectionNewFlujoCaso = em.merge(flujoCasoCollectionNewFlujoCaso);
                    if (oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso != null && !oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso.equals(estadoCaso)) {
                        oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso.getFlujoCasoCollection().remove(flujoCasoCollectionNewFlujoCaso);
                        oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso = em.merge(oldEstadoCasoCodigoEstadoOfFlujoCasoCollectionNewFlujoCaso);
                    }
                }
            }
            for (CambioCaso cambioCasoCollectionNewCambioCaso : cambioCasoCollectionNew) {
                if (!cambioCasoCollectionOld.contains(cambioCasoCollectionNewCambioCaso)) {
                    EstadoCaso oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso = cambioCasoCollectionNewCambioCaso.getEstadoCasoCodigoEstado();
                    cambioCasoCollectionNewCambioCaso.setEstadoCasoCodigoEstado(estadoCaso);
                    cambioCasoCollectionNewCambioCaso = em.merge(cambioCasoCollectionNewCambioCaso);
                    if (oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso != null && !oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso.equals(estadoCaso)) {
                        oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso.getCambioCasoCollection().remove(cambioCasoCollectionNewCambioCaso);
                        oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso = em.merge(oldEstadoCasoCodigoEstadoOfCambioCasoCollectionNewCambioCaso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estadoCaso.getCodigoEstado();
                if (findEstadoCaso(id) == null) {
                    throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.");
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
            EstadoCaso estadoCaso;
            try {
                estadoCaso = em.getReference(EstadoCaso.class, id);
                estadoCaso.getCodigoEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<FlujoCaso> flujoCasoCollectionOrphanCheck = estadoCaso.getFlujoCasoCollection();
            for (FlujoCaso flujoCasoCollectionOrphanCheckFlujoCaso : flujoCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoCaso (" + estadoCaso + ") cannot be destroyed since the FlujoCaso " + flujoCasoCollectionOrphanCheckFlujoCaso + " in its flujoCasoCollection field has a non-nullable estadoCasoCodigoEstado field.");
            }
            Collection<CambioCaso> cambioCasoCollectionOrphanCheck = estadoCaso.getCambioCasoCollection();
            for (CambioCaso cambioCasoCollectionOrphanCheckCambioCaso : cambioCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoCaso (" + estadoCaso + ") cannot be destroyed since the CambioCaso " + cambioCasoCollectionOrphanCheckCambioCaso + " in its cambioCasoCollection field has a non-nullable estadoCasoCodigoEstado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoCaso> findEstadoCasoEntities() {
        return findEstadoCasoEntities(true, -1, -1);
    }

    public List<EstadoCaso> findEstadoCasoEntities(int maxResults, int firstResult) {
        return findEstadoCasoEntities(false, maxResults, firstResult);
    }

    private List<EstadoCaso> findEstadoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoCaso.class));
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

    public EstadoCaso findEstadoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoCaso> rt = cq.from(EstadoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

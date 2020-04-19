/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Medicamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.MedicamentosCaso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class MedicamentosJpaController implements Serializable {

    public MedicamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicamentos medicamentos) throws PreexistingEntityException, Exception {
        if (medicamentos.getMedicamentosCasoCollection() == null) {
            medicamentos.setMedicamentosCasoCollection(new ArrayList<MedicamentosCaso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<MedicamentosCaso> attachedMedicamentosCasoCollection = new ArrayList<MedicamentosCaso>();
            for (MedicamentosCaso medicamentosCasoCollectionMedicamentosCasoToAttach : medicamentos.getMedicamentosCasoCollection()) {
                medicamentosCasoCollectionMedicamentosCasoToAttach = em.getReference(medicamentosCasoCollectionMedicamentosCasoToAttach.getClass(), medicamentosCasoCollectionMedicamentosCasoToAttach.getCasoPersonaIdCaso());
                attachedMedicamentosCasoCollection.add(medicamentosCasoCollectionMedicamentosCasoToAttach);
            }
            medicamentos.setMedicamentosCasoCollection(attachedMedicamentosCasoCollection);
            em.persist(medicamentos);
            for (MedicamentosCaso medicamentosCasoCollectionMedicamentosCaso : medicamentos.getMedicamentosCasoCollection()) {
                Medicamentos oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionMedicamentosCaso = medicamentosCasoCollectionMedicamentosCaso.getMedicamentosCodigoMedicamento();
                medicamentosCasoCollectionMedicamentosCaso.setMedicamentosCodigoMedicamento(medicamentos);
                medicamentosCasoCollectionMedicamentosCaso = em.merge(medicamentosCasoCollectionMedicamentosCaso);
                if (oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionMedicamentosCaso != null) {
                    oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionMedicamentosCaso.getMedicamentosCasoCollection().remove(medicamentosCasoCollectionMedicamentosCaso);
                    oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionMedicamentosCaso = em.merge(oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionMedicamentosCaso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicamentos(medicamentos.getCodigoMedicamento()) != null) {
                throw new PreexistingEntityException("Medicamentos " + medicamentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medicamentos medicamentos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medicamentos persistentMedicamentos = em.find(Medicamentos.class, medicamentos.getCodigoMedicamento());
            Collection<MedicamentosCaso> medicamentosCasoCollectionOld = persistentMedicamentos.getMedicamentosCasoCollection();
            Collection<MedicamentosCaso> medicamentosCasoCollectionNew = medicamentos.getMedicamentosCasoCollection();
            List<String> illegalOrphanMessages = null;
            for (MedicamentosCaso medicamentosCasoCollectionOldMedicamentosCaso : medicamentosCasoCollectionOld) {
                if (!medicamentosCasoCollectionNew.contains(medicamentosCasoCollectionOldMedicamentosCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MedicamentosCaso " + medicamentosCasoCollectionOldMedicamentosCaso + " since its medicamentosCodigoMedicamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<MedicamentosCaso> attachedMedicamentosCasoCollectionNew = new ArrayList<MedicamentosCaso>();
            for (MedicamentosCaso medicamentosCasoCollectionNewMedicamentosCasoToAttach : medicamentosCasoCollectionNew) {
                medicamentosCasoCollectionNewMedicamentosCasoToAttach = em.getReference(medicamentosCasoCollectionNewMedicamentosCasoToAttach.getClass(), medicamentosCasoCollectionNewMedicamentosCasoToAttach.getCasoPersonaIdCaso());
                attachedMedicamentosCasoCollectionNew.add(medicamentosCasoCollectionNewMedicamentosCasoToAttach);
            }
            medicamentosCasoCollectionNew = attachedMedicamentosCasoCollectionNew;
            medicamentos.setMedicamentosCasoCollection(medicamentosCasoCollectionNew);
            medicamentos = em.merge(medicamentos);
            for (MedicamentosCaso medicamentosCasoCollectionNewMedicamentosCaso : medicamentosCasoCollectionNew) {
                if (!medicamentosCasoCollectionOld.contains(medicamentosCasoCollectionNewMedicamentosCaso)) {
                    Medicamentos oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso = medicamentosCasoCollectionNewMedicamentosCaso.getMedicamentosCodigoMedicamento();
                    medicamentosCasoCollectionNewMedicamentosCaso.setMedicamentosCodigoMedicamento(medicamentos);
                    medicamentosCasoCollectionNewMedicamentosCaso = em.merge(medicamentosCasoCollectionNewMedicamentosCaso);
                    if (oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso != null && !oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso.equals(medicamentos)) {
                        oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso.getMedicamentosCasoCollection().remove(medicamentosCasoCollectionNewMedicamentosCaso);
                        oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso = em.merge(oldMedicamentosCodigoMedicamentoOfMedicamentosCasoCollectionNewMedicamentosCaso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = medicamentos.getCodigoMedicamento();
                if (findMedicamentos(id) == null) {
                    throw new NonexistentEntityException("The medicamentos with id " + id + " no longer exists.");
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
            Medicamentos medicamentos;
            try {
                medicamentos = em.getReference(Medicamentos.class, id);
                medicamentos.getCodigoMedicamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamentos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<MedicamentosCaso> medicamentosCasoCollectionOrphanCheck = medicamentos.getMedicamentosCasoCollection();
            for (MedicamentosCaso medicamentosCasoCollectionOrphanCheckMedicamentosCaso : medicamentosCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medicamentos (" + medicamentos + ") cannot be destroyed since the MedicamentosCaso " + medicamentosCasoCollectionOrphanCheckMedicamentosCaso + " in its medicamentosCasoCollection field has a non-nullable medicamentosCodigoMedicamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medicamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medicamentos> findMedicamentosEntities() {
        return findMedicamentosEntities(true, -1, -1);
    }

    public List<Medicamentos> findMedicamentosEntities(int maxResults, int firstResult) {
        return findMedicamentosEntities(false, maxResults, firstResult);
    }

    private List<Medicamentos> findMedicamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medicamentos.class));
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

    public Medicamentos findMedicamentos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medicamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medicamentos> rt = cq.from(Medicamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

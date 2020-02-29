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
import com.softcoisoweb.model.Medicamentos;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class MedicamentosCasoJpaController implements Serializable {

    public MedicamentosCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedicamentosCaso medicamentosCaso) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = medicamentosCaso.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            MedicamentosCaso oldMedicamentosCasoOfCasoPersona = casoPersonaOrphanCheck.getMedicamentosCaso();
            if (oldMedicamentosCasoOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type MedicamentosCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = medicamentosCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                medicamentosCaso.setCasoPersona(casoPersona);
            }
            Medicamentos medicamentosCodigoMedicamento = medicamentosCaso.getMedicamentosCodigoMedicamento();
            if (medicamentosCodigoMedicamento != null) {
                medicamentosCodigoMedicamento = em.getReference(medicamentosCodigoMedicamento.getClass(), medicamentosCodigoMedicamento.getCodigoMedicamento());
                medicamentosCaso.setMedicamentosCodigoMedicamento(medicamentosCodigoMedicamento);
            }
            Usuario usuarioCedula = medicamentosCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                medicamentosCaso.setUsuarioCedula(usuarioCedula);
            }
            em.persist(medicamentosCaso);
            if (casoPersona != null) {
                casoPersona.setMedicamentosCaso(medicamentosCaso);
                casoPersona = em.merge(casoPersona);
            }
            if (medicamentosCodigoMedicamento != null) {
                medicamentosCodigoMedicamento.getMedicamentosCasoCollection().add(medicamentosCaso);
                medicamentosCodigoMedicamento = em.merge(medicamentosCodigoMedicamento);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getMedicamentosCasoCollection().add(medicamentosCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicamentosCaso(medicamentosCaso.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("MedicamentosCaso " + medicamentosCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicamentosCaso medicamentosCaso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicamentosCaso persistentMedicamentosCaso = em.find(MedicamentosCaso.class, medicamentosCaso.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentMedicamentosCaso.getCasoPersona();
            CasoPersona casoPersonaNew = medicamentosCaso.getCasoPersona();
            Medicamentos medicamentosCodigoMedicamentoOld = persistentMedicamentosCaso.getMedicamentosCodigoMedicamento();
            Medicamentos medicamentosCodigoMedicamentoNew = medicamentosCaso.getMedicamentosCodigoMedicamento();
            Usuario usuarioCedulaOld = persistentMedicamentosCaso.getUsuarioCedula();
            Usuario usuarioCedulaNew = medicamentosCaso.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                MedicamentosCaso oldMedicamentosCasoOfCasoPersona = casoPersonaNew.getMedicamentosCaso();
                if (oldMedicamentosCasoOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type MedicamentosCaso whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                medicamentosCaso.setCasoPersona(casoPersonaNew);
            }
            if (medicamentosCodigoMedicamentoNew != null) {
                medicamentosCodigoMedicamentoNew = em.getReference(medicamentosCodigoMedicamentoNew.getClass(), medicamentosCodigoMedicamentoNew.getCodigoMedicamento());
                medicamentosCaso.setMedicamentosCodigoMedicamento(medicamentosCodigoMedicamentoNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                medicamentosCaso.setUsuarioCedula(usuarioCedulaNew);
            }
            medicamentosCaso = em.merge(medicamentosCaso);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setMedicamentosCaso(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setMedicamentosCaso(medicamentosCaso);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (medicamentosCodigoMedicamentoOld != null && !medicamentosCodigoMedicamentoOld.equals(medicamentosCodigoMedicamentoNew)) {
                medicamentosCodigoMedicamentoOld.getMedicamentosCasoCollection().remove(medicamentosCaso);
                medicamentosCodigoMedicamentoOld = em.merge(medicamentosCodigoMedicamentoOld);
            }
            if (medicamentosCodigoMedicamentoNew != null && !medicamentosCodigoMedicamentoNew.equals(medicamentosCodigoMedicamentoOld)) {
                medicamentosCodigoMedicamentoNew.getMedicamentosCasoCollection().add(medicamentosCaso);
                medicamentosCodigoMedicamentoNew = em.merge(medicamentosCodigoMedicamentoNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getMedicamentosCasoCollection().remove(medicamentosCaso);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getMedicamentosCasoCollection().add(medicamentosCaso);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = medicamentosCaso.getCasoPersonaIdCaso();
                if (findMedicamentosCaso(id) == null) {
                    throw new NonexistentEntityException("The medicamentosCaso with id " + id + " no longer exists.");
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
            MedicamentosCaso medicamentosCaso;
            try {
                medicamentosCaso = em.getReference(MedicamentosCaso.class, id);
                medicamentosCaso.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamentosCaso with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = medicamentosCaso.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setMedicamentosCaso(null);
                casoPersona = em.merge(casoPersona);
            }
            Medicamentos medicamentosCodigoMedicamento = medicamentosCaso.getMedicamentosCodigoMedicamento();
            if (medicamentosCodigoMedicamento != null) {
                medicamentosCodigoMedicamento.getMedicamentosCasoCollection().remove(medicamentosCaso);
                medicamentosCodigoMedicamento = em.merge(medicamentosCodigoMedicamento);
            }
            Usuario usuarioCedula = medicamentosCaso.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getMedicamentosCasoCollection().remove(medicamentosCaso);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(medicamentosCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicamentosCaso> findMedicamentosCasoEntities() {
        return findMedicamentosCasoEntities(true, -1, -1);
    }

    public List<MedicamentosCaso> findMedicamentosCasoEntities(int maxResults, int firstResult) {
        return findMedicamentosCasoEntities(false, maxResults, firstResult);
    }

    private List<MedicamentosCaso> findMedicamentosCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicamentosCaso.class));
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

    public MedicamentosCaso findMedicamentosCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicamentosCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentosCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicamentosCaso> rt = cq.from(MedicamentosCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

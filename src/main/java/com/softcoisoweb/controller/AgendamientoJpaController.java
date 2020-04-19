/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.controller;

import com.softcoisoweb.controller.exceptions.IllegalOrphanException;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.controller.exceptions.PreexistingEntityException;
import com.softcoisoweb.model.Agendamiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class AgendamientoJpaController implements Serializable {

    public AgendamientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agendamiento agendamiento) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        CasoPersona casoPersonaOrphanCheck = agendamiento.getCasoPersona();
        if (casoPersonaOrphanCheck != null) {
            Agendamiento oldAgendamientoOfCasoPersona = casoPersonaOrphanCheck.getAgendamiento();
            if (oldAgendamientoOfCasoPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CasoPersona " + casoPersonaOrphanCheck + " already has an item of type Agendamiento whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona casoPersona = agendamiento.getCasoPersona();
            if (casoPersona != null) {
                casoPersona = em.getReference(casoPersona.getClass(), casoPersona.getIdCaso());
                agendamiento.setCasoPersona(casoPersona);
            }
            Usuario usuarioCedula = agendamiento.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula = em.getReference(usuarioCedula.getClass(), usuarioCedula.getCedula());
                agendamiento.setUsuarioCedula(usuarioCedula);
            }
            em.persist(agendamiento);
            if (casoPersona != null) {
                casoPersona.setAgendamiento(agendamiento);
                casoPersona = em.merge(casoPersona);
            }
            if (usuarioCedula != null) {
                usuarioCedula.getAgendamientoCollection().add(agendamiento);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAgendamiento(agendamiento.getCasoPersonaIdCaso()) != null) {
                throw new PreexistingEntityException("Agendamiento " + agendamiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agendamiento agendamiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agendamiento persistentAgendamiento = em.find(Agendamiento.class, agendamiento.getCasoPersonaIdCaso());
            CasoPersona casoPersonaOld = persistentAgendamiento.getCasoPersona();
            CasoPersona casoPersonaNew = agendamiento.getCasoPersona();
            Usuario usuarioCedulaOld = persistentAgendamiento.getUsuarioCedula();
            Usuario usuarioCedulaNew = agendamiento.getUsuarioCedula();
            List<String> illegalOrphanMessages = null;
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                Agendamiento oldAgendamientoOfCasoPersona = casoPersonaNew.getAgendamiento();
                if (oldAgendamientoOfCasoPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CasoPersona " + casoPersonaNew + " already has an item of type Agendamiento whose casoPersona column cannot be null. Please make another selection for the casoPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoPersonaNew != null) {
                casoPersonaNew = em.getReference(casoPersonaNew.getClass(), casoPersonaNew.getIdCaso());
                agendamiento.setCasoPersona(casoPersonaNew);
            }
            if (usuarioCedulaNew != null) {
                usuarioCedulaNew = em.getReference(usuarioCedulaNew.getClass(), usuarioCedulaNew.getCedula());
                agendamiento.setUsuarioCedula(usuarioCedulaNew);
            }
            agendamiento = em.merge(agendamiento);
            if (casoPersonaOld != null && !casoPersonaOld.equals(casoPersonaNew)) {
                casoPersonaOld.setAgendamiento(null);
                casoPersonaOld = em.merge(casoPersonaOld);
            }
            if (casoPersonaNew != null && !casoPersonaNew.equals(casoPersonaOld)) {
                casoPersonaNew.setAgendamiento(agendamiento);
                casoPersonaNew = em.merge(casoPersonaNew);
            }
            if (usuarioCedulaOld != null && !usuarioCedulaOld.equals(usuarioCedulaNew)) {
                usuarioCedulaOld.getAgendamientoCollection().remove(agendamiento);
                usuarioCedulaOld = em.merge(usuarioCedulaOld);
            }
            if (usuarioCedulaNew != null && !usuarioCedulaNew.equals(usuarioCedulaOld)) {
                usuarioCedulaNew.getAgendamientoCollection().add(agendamiento);
                usuarioCedulaNew = em.merge(usuarioCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = agendamiento.getCasoPersonaIdCaso();
                if (findAgendamiento(id) == null) {
                    throw new NonexistentEntityException("The agendamiento with id " + id + " no longer exists.");
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
            Agendamiento agendamiento;
            try {
                agendamiento = em.getReference(Agendamiento.class, id);
                agendamiento.getCasoPersonaIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agendamiento with id " + id + " no longer exists.", enfe);
            }
            CasoPersona casoPersona = agendamiento.getCasoPersona();
            if (casoPersona != null) {
                casoPersona.setAgendamiento(null);
                casoPersona = em.merge(casoPersona);
            }
            Usuario usuarioCedula = agendamiento.getUsuarioCedula();
            if (usuarioCedula != null) {
                usuarioCedula.getAgendamientoCollection().remove(agendamiento);
                usuarioCedula = em.merge(usuarioCedula);
            }
            em.remove(agendamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agendamiento> findAgendamientoEntities() {
        return findAgendamientoEntities(true, -1, -1);
    }

    public List<Agendamiento> findAgendamientoEntities(int maxResults, int firstResult) {
        return findAgendamientoEntities(false, maxResults, firstResult);
    }

    private List<Agendamiento> findAgendamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agendamiento.class));
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

    public Agendamiento findAgendamiento(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agendamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgendamientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agendamiento> rt = cq.from(Agendamiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

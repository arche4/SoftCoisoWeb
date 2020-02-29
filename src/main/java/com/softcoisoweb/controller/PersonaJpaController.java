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
import com.softcoisoweb.model.Afp;
import com.softcoisoweb.model.Arl;
import com.softcoisoweb.model.Empresa;
import com.softcoisoweb.model.Eps;
import com.softcoisoweb.model.OrganizacionSindical;
import com.softcoisoweb.model.TipoContrato;
import com.softcoisoweb.model.PersonaDireccion;
import com.softcoisoweb.model.CasoPersona;
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
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getCasoPersonaCollection() == null) {
            persona.setCasoPersonaCollection(new ArrayList<CasoPersona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp afpCodigoAfp = persona.getAfpCodigoAfp();
            if (afpCodigoAfp != null) {
                afpCodigoAfp = em.getReference(afpCodigoAfp.getClass(), afpCodigoAfp.getCodigoAfp());
                persona.setAfpCodigoAfp(afpCodigoAfp);
            }
            Arl arlCodigoArl = persona.getArlCodigoArl();
            if (arlCodigoArl != null) {
                arlCodigoArl = em.getReference(arlCodigoArl.getClass(), arlCodigoArl.getCodigoArl());
                persona.setArlCodigoArl(arlCodigoArl);
            }
            Empresa empresaCodigoEmpresa = persona.getEmpresaCodigoEmpresa();
            if (empresaCodigoEmpresa != null) {
                empresaCodigoEmpresa = em.getReference(empresaCodigoEmpresa.getClass(), empresaCodigoEmpresa.getCodigoEmpresa());
                persona.setEmpresaCodigoEmpresa(empresaCodigoEmpresa);
            }
            Eps epsCodigoEps = persona.getEpsCodigoEps();
            if (epsCodigoEps != null) {
                epsCodigoEps = em.getReference(epsCodigoEps.getClass(), epsCodigoEps.getCodigoEps());
                persona.setEpsCodigoEps(epsCodigoEps);
            }
            OrganizacionSindical organizacionSindicalCodigoOrganizacion = persona.getOrganizacionSindicalCodigoOrganizacion();
            if (organizacionSindicalCodigoOrganizacion != null) {
                organizacionSindicalCodigoOrganizacion = em.getReference(organizacionSindicalCodigoOrganizacion.getClass(), organizacionSindicalCodigoOrganizacion.getCodigoOrganizacion());
                persona.setOrganizacionSindicalCodigoOrganizacion(organizacionSindicalCodigoOrganizacion);
            }
            TipoContrato tipoContratoCodigoTipoContrato = persona.getTipoContratoCodigoTipoContrato();
            if (tipoContratoCodigoTipoContrato != null) {
                tipoContratoCodigoTipoContrato = em.getReference(tipoContratoCodigoTipoContrato.getClass(), tipoContratoCodigoTipoContrato.getCodigoTipoContrato());
                persona.setTipoContratoCodigoTipoContrato(tipoContratoCodigoTipoContrato);
            }
            PersonaDireccion personaDireccion = persona.getPersonaDireccion();
            if (personaDireccion != null) {
                personaDireccion = em.getReference(personaDireccion.getClass(), personaDireccion.getPersonaCedula());
                persona.setPersonaDireccion(personaDireccion);
            }
            Collection<CasoPersona> attachedCasoPersonaCollection = new ArrayList<CasoPersona>();
            for (CasoPersona casoPersonaCollectionCasoPersonaToAttach : persona.getCasoPersonaCollection()) {
                casoPersonaCollectionCasoPersonaToAttach = em.getReference(casoPersonaCollectionCasoPersonaToAttach.getClass(), casoPersonaCollectionCasoPersonaToAttach.getIdCaso());
                attachedCasoPersonaCollection.add(casoPersonaCollectionCasoPersonaToAttach);
            }
            persona.setCasoPersonaCollection(attachedCasoPersonaCollection);
            em.persist(persona);
            if (afpCodigoAfp != null) {
                afpCodigoAfp.getPersonaCollection().add(persona);
                afpCodigoAfp = em.merge(afpCodigoAfp);
            }
            if (arlCodigoArl != null) {
                arlCodigoArl.getPersonaCollection().add(persona);
                arlCodigoArl = em.merge(arlCodigoArl);
            }
            if (empresaCodigoEmpresa != null) {
                empresaCodigoEmpresa.getPersonaCollection().add(persona);
                empresaCodigoEmpresa = em.merge(empresaCodigoEmpresa);
            }
            if (epsCodigoEps != null) {
                epsCodigoEps.getPersonaCollection().add(persona);
                epsCodigoEps = em.merge(epsCodigoEps);
            }
            if (organizacionSindicalCodigoOrganizacion != null) {
                organizacionSindicalCodigoOrganizacion.getPersonaCollection().add(persona);
                organizacionSindicalCodigoOrganizacion = em.merge(organizacionSindicalCodigoOrganizacion);
            }
            if (tipoContratoCodigoTipoContrato != null) {
                tipoContratoCodigoTipoContrato.getPersonaCollection().add(persona);
                tipoContratoCodigoTipoContrato = em.merge(tipoContratoCodigoTipoContrato);
            }
            if (personaDireccion != null) {
                Persona oldPersonaOfPersonaDireccion = personaDireccion.getPersona();
                if (oldPersonaOfPersonaDireccion != null) {
                    oldPersonaOfPersonaDireccion.setPersonaDireccion(null);
                    oldPersonaOfPersonaDireccion = em.merge(oldPersonaOfPersonaDireccion);
                }
                personaDireccion.setPersona(persona);
                personaDireccion = em.merge(personaDireccion);
            }
            for (CasoPersona casoPersonaCollectionCasoPersona : persona.getCasoPersonaCollection()) {
                Persona oldPersonaCedulaOfCasoPersonaCollectionCasoPersona = casoPersonaCollectionCasoPersona.getPersonaCedula();
                casoPersonaCollectionCasoPersona.setPersonaCedula(persona);
                casoPersonaCollectionCasoPersona = em.merge(casoPersonaCollectionCasoPersona);
                if (oldPersonaCedulaOfCasoPersonaCollectionCasoPersona != null) {
                    oldPersonaCedulaOfCasoPersonaCollectionCasoPersona.getCasoPersonaCollection().remove(casoPersonaCollectionCasoPersona);
                    oldPersonaCedulaOfCasoPersonaCollectionCasoPersona = em.merge(oldPersonaCedulaOfCasoPersonaCollectionCasoPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getCedula()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getCedula());
            Afp afpCodigoAfpOld = persistentPersona.getAfpCodigoAfp();
            Afp afpCodigoAfpNew = persona.getAfpCodigoAfp();
            Arl arlCodigoArlOld = persistentPersona.getArlCodigoArl();
            Arl arlCodigoArlNew = persona.getArlCodigoArl();
            Empresa empresaCodigoEmpresaOld = persistentPersona.getEmpresaCodigoEmpresa();
            Empresa empresaCodigoEmpresaNew = persona.getEmpresaCodigoEmpresa();
            Eps epsCodigoEpsOld = persistentPersona.getEpsCodigoEps();
            Eps epsCodigoEpsNew = persona.getEpsCodigoEps();
            OrganizacionSindical organizacionSindicalCodigoOrganizacionOld = persistentPersona.getOrganizacionSindicalCodigoOrganizacion();
            OrganizacionSindical organizacionSindicalCodigoOrganizacionNew = persona.getOrganizacionSindicalCodigoOrganizacion();
            TipoContrato tipoContratoCodigoTipoContratoOld = persistentPersona.getTipoContratoCodigoTipoContrato();
            TipoContrato tipoContratoCodigoTipoContratoNew = persona.getTipoContratoCodigoTipoContrato();
            PersonaDireccion personaDireccionOld = persistentPersona.getPersonaDireccion();
            PersonaDireccion personaDireccionNew = persona.getPersonaDireccion();
            Collection<CasoPersona> casoPersonaCollectionOld = persistentPersona.getCasoPersonaCollection();
            Collection<CasoPersona> casoPersonaCollectionNew = persona.getCasoPersonaCollection();
            List<String> illegalOrphanMessages = null;
            if (personaDireccionOld != null && !personaDireccionOld.equals(personaDireccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PersonaDireccion " + personaDireccionOld + " since its persona field is not nullable.");
            }
            for (CasoPersona casoPersonaCollectionOldCasoPersona : casoPersonaCollectionOld) {
                if (!casoPersonaCollectionNew.contains(casoPersonaCollectionOldCasoPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CasoPersona " + casoPersonaCollectionOldCasoPersona + " since its personaCedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (afpCodigoAfpNew != null) {
                afpCodigoAfpNew = em.getReference(afpCodigoAfpNew.getClass(), afpCodigoAfpNew.getCodigoAfp());
                persona.setAfpCodigoAfp(afpCodigoAfpNew);
            }
            if (arlCodigoArlNew != null) {
                arlCodigoArlNew = em.getReference(arlCodigoArlNew.getClass(), arlCodigoArlNew.getCodigoArl());
                persona.setArlCodigoArl(arlCodigoArlNew);
            }
            if (empresaCodigoEmpresaNew != null) {
                empresaCodigoEmpresaNew = em.getReference(empresaCodigoEmpresaNew.getClass(), empresaCodigoEmpresaNew.getCodigoEmpresa());
                persona.setEmpresaCodigoEmpresa(empresaCodigoEmpresaNew);
            }
            if (epsCodigoEpsNew != null) {
                epsCodigoEpsNew = em.getReference(epsCodigoEpsNew.getClass(), epsCodigoEpsNew.getCodigoEps());
                persona.setEpsCodigoEps(epsCodigoEpsNew);
            }
            if (organizacionSindicalCodigoOrganizacionNew != null) {
                organizacionSindicalCodigoOrganizacionNew = em.getReference(organizacionSindicalCodigoOrganizacionNew.getClass(), organizacionSindicalCodigoOrganizacionNew.getCodigoOrganizacion());
                persona.setOrganizacionSindicalCodigoOrganizacion(organizacionSindicalCodigoOrganizacionNew);
            }
            if (tipoContratoCodigoTipoContratoNew != null) {
                tipoContratoCodigoTipoContratoNew = em.getReference(tipoContratoCodigoTipoContratoNew.getClass(), tipoContratoCodigoTipoContratoNew.getCodigoTipoContrato());
                persona.setTipoContratoCodigoTipoContrato(tipoContratoCodigoTipoContratoNew);
            }
            if (personaDireccionNew != null) {
                personaDireccionNew = em.getReference(personaDireccionNew.getClass(), personaDireccionNew.getPersonaCedula());
                persona.setPersonaDireccion(personaDireccionNew);
            }
            Collection<CasoPersona> attachedCasoPersonaCollectionNew = new ArrayList<CasoPersona>();
            for (CasoPersona casoPersonaCollectionNewCasoPersonaToAttach : casoPersonaCollectionNew) {
                casoPersonaCollectionNewCasoPersonaToAttach = em.getReference(casoPersonaCollectionNewCasoPersonaToAttach.getClass(), casoPersonaCollectionNewCasoPersonaToAttach.getIdCaso());
                attachedCasoPersonaCollectionNew.add(casoPersonaCollectionNewCasoPersonaToAttach);
            }
            casoPersonaCollectionNew = attachedCasoPersonaCollectionNew;
            persona.setCasoPersonaCollection(casoPersonaCollectionNew);
            persona = em.merge(persona);
            if (afpCodigoAfpOld != null && !afpCodigoAfpOld.equals(afpCodigoAfpNew)) {
                afpCodigoAfpOld.getPersonaCollection().remove(persona);
                afpCodigoAfpOld = em.merge(afpCodigoAfpOld);
            }
            if (afpCodigoAfpNew != null && !afpCodigoAfpNew.equals(afpCodigoAfpOld)) {
                afpCodigoAfpNew.getPersonaCollection().add(persona);
                afpCodigoAfpNew = em.merge(afpCodigoAfpNew);
            }
            if (arlCodigoArlOld != null && !arlCodigoArlOld.equals(arlCodigoArlNew)) {
                arlCodigoArlOld.getPersonaCollection().remove(persona);
                arlCodigoArlOld = em.merge(arlCodigoArlOld);
            }
            if (arlCodigoArlNew != null && !arlCodigoArlNew.equals(arlCodigoArlOld)) {
                arlCodigoArlNew.getPersonaCollection().add(persona);
                arlCodigoArlNew = em.merge(arlCodigoArlNew);
            }
            if (empresaCodigoEmpresaOld != null && !empresaCodigoEmpresaOld.equals(empresaCodigoEmpresaNew)) {
                empresaCodigoEmpresaOld.getPersonaCollection().remove(persona);
                empresaCodigoEmpresaOld = em.merge(empresaCodigoEmpresaOld);
            }
            if (empresaCodigoEmpresaNew != null && !empresaCodigoEmpresaNew.equals(empresaCodigoEmpresaOld)) {
                empresaCodigoEmpresaNew.getPersonaCollection().add(persona);
                empresaCodigoEmpresaNew = em.merge(empresaCodigoEmpresaNew);
            }
            if (epsCodigoEpsOld != null && !epsCodigoEpsOld.equals(epsCodigoEpsNew)) {
                epsCodigoEpsOld.getPersonaCollection().remove(persona);
                epsCodigoEpsOld = em.merge(epsCodigoEpsOld);
            }
            if (epsCodigoEpsNew != null && !epsCodigoEpsNew.equals(epsCodigoEpsOld)) {
                epsCodigoEpsNew.getPersonaCollection().add(persona);
                epsCodigoEpsNew = em.merge(epsCodigoEpsNew);
            }
            if (organizacionSindicalCodigoOrganizacionOld != null && !organizacionSindicalCodigoOrganizacionOld.equals(organizacionSindicalCodigoOrganizacionNew)) {
                organizacionSindicalCodigoOrganizacionOld.getPersonaCollection().remove(persona);
                organizacionSindicalCodigoOrganizacionOld = em.merge(organizacionSindicalCodigoOrganizacionOld);
            }
            if (organizacionSindicalCodigoOrganizacionNew != null && !organizacionSindicalCodigoOrganizacionNew.equals(organizacionSindicalCodigoOrganizacionOld)) {
                organizacionSindicalCodigoOrganizacionNew.getPersonaCollection().add(persona);
                organizacionSindicalCodigoOrganizacionNew = em.merge(organizacionSindicalCodigoOrganizacionNew);
            }
            if (tipoContratoCodigoTipoContratoOld != null && !tipoContratoCodigoTipoContratoOld.equals(tipoContratoCodigoTipoContratoNew)) {
                tipoContratoCodigoTipoContratoOld.getPersonaCollection().remove(persona);
                tipoContratoCodigoTipoContratoOld = em.merge(tipoContratoCodigoTipoContratoOld);
            }
            if (tipoContratoCodigoTipoContratoNew != null && !tipoContratoCodigoTipoContratoNew.equals(tipoContratoCodigoTipoContratoOld)) {
                tipoContratoCodigoTipoContratoNew.getPersonaCollection().add(persona);
                tipoContratoCodigoTipoContratoNew = em.merge(tipoContratoCodigoTipoContratoNew);
            }
            if (personaDireccionNew != null && !personaDireccionNew.equals(personaDireccionOld)) {
                Persona oldPersonaOfPersonaDireccion = personaDireccionNew.getPersona();
                if (oldPersonaOfPersonaDireccion != null) {
                    oldPersonaOfPersonaDireccion.setPersonaDireccion(null);
                    oldPersonaOfPersonaDireccion = em.merge(oldPersonaOfPersonaDireccion);
                }
                personaDireccionNew.setPersona(persona);
                personaDireccionNew = em.merge(personaDireccionNew);
            }
            for (CasoPersona casoPersonaCollectionNewCasoPersona : casoPersonaCollectionNew) {
                if (!casoPersonaCollectionOld.contains(casoPersonaCollectionNewCasoPersona)) {
                    Persona oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona = casoPersonaCollectionNewCasoPersona.getPersonaCedula();
                    casoPersonaCollectionNewCasoPersona.setPersonaCedula(persona);
                    casoPersonaCollectionNewCasoPersona = em.merge(casoPersonaCollectionNewCasoPersona);
                    if (oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona != null && !oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona.equals(persona)) {
                        oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona.getCasoPersonaCollection().remove(casoPersonaCollectionNewCasoPersona);
                        oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona = em.merge(oldPersonaCedulaOfCasoPersonaCollectionNewCasoPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = persona.getCedula();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            PersonaDireccion personaDireccionOrphanCheck = persona.getPersonaDireccion();
            if (personaDireccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the PersonaDireccion " + personaDireccionOrphanCheck + " in its personaDireccion field has a non-nullable persona field.");
            }
            Collection<CasoPersona> casoPersonaCollectionOrphanCheck = persona.getCasoPersonaCollection();
            for (CasoPersona casoPersonaCollectionOrphanCheckCasoPersona : casoPersonaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the CasoPersona " + casoPersonaCollectionOrphanCheckCasoPersona + " in its casoPersonaCollection field has a non-nullable personaCedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Afp afpCodigoAfp = persona.getAfpCodigoAfp();
            if (afpCodigoAfp != null) {
                afpCodigoAfp.getPersonaCollection().remove(persona);
                afpCodigoAfp = em.merge(afpCodigoAfp);
            }
            Arl arlCodigoArl = persona.getArlCodigoArl();
            if (arlCodigoArl != null) {
                arlCodigoArl.getPersonaCollection().remove(persona);
                arlCodigoArl = em.merge(arlCodigoArl);
            }
            Empresa empresaCodigoEmpresa = persona.getEmpresaCodigoEmpresa();
            if (empresaCodigoEmpresa != null) {
                empresaCodigoEmpresa.getPersonaCollection().remove(persona);
                empresaCodigoEmpresa = em.merge(empresaCodigoEmpresa);
            }
            Eps epsCodigoEps = persona.getEpsCodigoEps();
            if (epsCodigoEps != null) {
                epsCodigoEps.getPersonaCollection().remove(persona);
                epsCodigoEps = em.merge(epsCodigoEps);
            }
            OrganizacionSindical organizacionSindicalCodigoOrganizacion = persona.getOrganizacionSindicalCodigoOrganizacion();
            if (organizacionSindicalCodigoOrganizacion != null) {
                organizacionSindicalCodigoOrganizacion.getPersonaCollection().remove(persona);
                organizacionSindicalCodigoOrganizacion = em.merge(organizacionSindicalCodigoOrganizacion);
            }
            TipoContrato tipoContratoCodigoTipoContrato = persona.getTipoContratoCodigoTipoContrato();
            if (tipoContratoCodigoTipoContrato != null) {
                tipoContratoCodigoTipoContrato.getPersonaCollection().remove(persona);
                tipoContratoCodigoTipoContrato = em.merge(tipoContratoCodigoTipoContrato);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

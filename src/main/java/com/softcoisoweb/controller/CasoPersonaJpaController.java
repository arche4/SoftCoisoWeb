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
import com.softcoisoweb.model.CasoAcciones;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.TipoCaso;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.Agendamiento;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.ProcesoCalificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class CasoPersonaJpaController implements Serializable {

    public CasoPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoPersona casoPersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoAcciones casoAcciones = casoPersona.getCasoAcciones();
            if (casoAcciones != null) {
                casoAcciones = em.getReference(casoAcciones.getClass(), casoAcciones.getCasoPersonaIdCaso());
                casoPersona.setCasoAcciones(casoAcciones);
            }
            Persona personaCedula = casoPersona.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula = em.getReference(personaCedula.getClass(), personaCedula.getCedula());
                casoPersona.setPersonaCedula(personaCedula);
            }
            TipoCaso tipoCasoCodigoTipoCaso = casoPersona.getTipoCasoCodigoTipoCaso();
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso = em.getReference(tipoCasoCodigoTipoCaso.getClass(), tipoCasoCodigoTipoCaso.getCodigoTipoCaso());
                casoPersona.setTipoCasoCodigoTipoCaso(tipoCasoCodigoTipoCaso);
            }
            ProcesoReclamacion procesoReclamacion = casoPersona.getProcesoReclamacion();
            if (procesoReclamacion != null) {
                procesoReclamacion = em.getReference(procesoReclamacion.getClass(), procesoReclamacion.getCasoPersonaIdCaso());
                casoPersona.setProcesoReclamacion(procesoReclamacion);
            }
            MedicamentosCaso medicamentosCaso = casoPersona.getMedicamentosCaso();
            if (medicamentosCaso != null) {
                medicamentosCaso = em.getReference(medicamentosCaso.getClass(), medicamentosCaso.getCasoPersonaIdCaso());
                casoPersona.setMedicamentosCaso(medicamentosCaso);
            }
            Agendamiento agendamiento = casoPersona.getAgendamiento();
            if (agendamiento != null) {
                agendamiento = em.getReference(agendamiento.getClass(), agendamiento.getCasoPersonaIdCaso());
                casoPersona.setAgendamiento(agendamiento);
            }
            Diagnostico diagnostico = casoPersona.getDiagnostico();
            if (diagnostico != null) {
                diagnostico = em.getReference(diagnostico.getClass(), diagnostico.getCasoPersonaIdCaso());
                casoPersona.setDiagnostico(diagnostico);
            }
            FlujoCaso flujoCaso = casoPersona.getFlujoCaso();
            if (flujoCaso != null) {
                flujoCaso = em.getReference(flujoCaso.getClass(), flujoCaso.getCasoPersonaIdCaso());
                casoPersona.setFlujoCaso(flujoCaso);
            }
            CambioCaso cambioCaso = casoPersona.getCambioCaso();
            if (cambioCaso != null) {
                cambioCaso = em.getReference(cambioCaso.getClass(), cambioCaso.getCasoPersonaIdCaso());
                casoPersona.setCambioCaso(cambioCaso);
            }
            ProcesoCalificacion procesoCalificacion = casoPersona.getProcesoCalificacion();
            if (procesoCalificacion != null) {
                procesoCalificacion = em.getReference(procesoCalificacion.getClass(), procesoCalificacion.getCasoPersonaIdCaso());
                casoPersona.setProcesoCalificacion(procesoCalificacion);
            }
            em.persist(casoPersona);
            if (casoAcciones != null) {
                CasoPersona oldCasoPersonaOfCasoAcciones = casoAcciones.getCasoPersona();
                if (oldCasoPersonaOfCasoAcciones != null) {
                    oldCasoPersonaOfCasoAcciones.setCasoAcciones(null);
                    oldCasoPersonaOfCasoAcciones = em.merge(oldCasoPersonaOfCasoAcciones);
                }
                casoAcciones.setCasoPersona(casoPersona);
                casoAcciones = em.merge(casoAcciones);
            }
            if (personaCedula != null) {
                personaCedula.getCasoPersonaCollection().add(casoPersona);
                personaCedula = em.merge(personaCedula);
            }
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso.getCasoPersonaCollection().add(casoPersona);
                tipoCasoCodigoTipoCaso = em.merge(tipoCasoCodigoTipoCaso);
            }
            if (procesoReclamacion != null) {
                CasoPersona oldCasoPersonaOfProcesoReclamacion = procesoReclamacion.getCasoPersona();
                if (oldCasoPersonaOfProcesoReclamacion != null) {
                    oldCasoPersonaOfProcesoReclamacion.setProcesoReclamacion(null);
                    oldCasoPersonaOfProcesoReclamacion = em.merge(oldCasoPersonaOfProcesoReclamacion);
                }
                procesoReclamacion.setCasoPersona(casoPersona);
                procesoReclamacion = em.merge(procesoReclamacion);
            }
            if (medicamentosCaso != null) {
                CasoPersona oldCasoPersonaOfMedicamentosCaso = medicamentosCaso.getCasoPersona();
                if (oldCasoPersonaOfMedicamentosCaso != null) {
                    oldCasoPersonaOfMedicamentosCaso.setMedicamentosCaso(null);
                    oldCasoPersonaOfMedicamentosCaso = em.merge(oldCasoPersonaOfMedicamentosCaso);
                }
                medicamentosCaso.setCasoPersona(casoPersona);
                medicamentosCaso = em.merge(medicamentosCaso);
            }
            if (agendamiento != null) {
                CasoPersona oldCasoPersonaOfAgendamiento = agendamiento.getCasoPersona();
                if (oldCasoPersonaOfAgendamiento != null) {
                    oldCasoPersonaOfAgendamiento.setAgendamiento(null);
                    oldCasoPersonaOfAgendamiento = em.merge(oldCasoPersonaOfAgendamiento);
                }
                agendamiento.setCasoPersona(casoPersona);
                agendamiento = em.merge(agendamiento);
            }
            if (diagnostico != null) {
                CasoPersona oldCasoPersonaOfDiagnostico = diagnostico.getCasoPersona();
                if (oldCasoPersonaOfDiagnostico != null) {
                    oldCasoPersonaOfDiagnostico.setDiagnostico(null);
                    oldCasoPersonaOfDiagnostico = em.merge(oldCasoPersonaOfDiagnostico);
                }
                diagnostico.setCasoPersona(casoPersona);
                diagnostico = em.merge(diagnostico);
            }
            if (flujoCaso != null) {
                CasoPersona oldCasoPersonaOfFlujoCaso = flujoCaso.getCasoPersona();
                if (oldCasoPersonaOfFlujoCaso != null) {
                    oldCasoPersonaOfFlujoCaso.setFlujoCaso(null);
                    oldCasoPersonaOfFlujoCaso = em.merge(oldCasoPersonaOfFlujoCaso);
                }
                flujoCaso.setCasoPersona(casoPersona);
                flujoCaso = em.merge(flujoCaso);
            }
            if (cambioCaso != null) {
                CasoPersona oldCasoPersonaOfCambioCaso = cambioCaso.getCasoPersona();
                if (oldCasoPersonaOfCambioCaso != null) {
                    oldCasoPersonaOfCambioCaso.setCambioCaso(null);
                    oldCasoPersonaOfCambioCaso = em.merge(oldCasoPersonaOfCambioCaso);
                }
                cambioCaso.setCasoPersona(casoPersona);
                cambioCaso = em.merge(cambioCaso);
            }
            if (procesoCalificacion != null) {
                CasoPersona oldCasoPersonaOfProcesoCalificacion = procesoCalificacion.getCasoPersona();
                if (oldCasoPersonaOfProcesoCalificacion != null) {
                    oldCasoPersonaOfProcesoCalificacion.setProcesoCalificacion(null);
                    oldCasoPersonaOfProcesoCalificacion = em.merge(oldCasoPersonaOfProcesoCalificacion);
                }
                procesoCalificacion.setCasoPersona(casoPersona);
                procesoCalificacion = em.merge(procesoCalificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCasoPersona(casoPersona.getIdCaso()) != null) {
                throw new PreexistingEntityException("CasoPersona " + casoPersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoPersona casoPersona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoPersona persistentCasoPersona = em.find(CasoPersona.class, casoPersona.getIdCaso());
            CasoAcciones casoAccionesOld = persistentCasoPersona.getCasoAcciones();
            CasoAcciones casoAccionesNew = casoPersona.getCasoAcciones();
            Persona personaCedulaOld = persistentCasoPersona.getPersonaCedula();
            Persona personaCedulaNew = casoPersona.getPersonaCedula();
            TipoCaso tipoCasoCodigoTipoCasoOld = persistentCasoPersona.getTipoCasoCodigoTipoCaso();
            TipoCaso tipoCasoCodigoTipoCasoNew = casoPersona.getTipoCasoCodigoTipoCaso();
            ProcesoReclamacion procesoReclamacionOld = persistentCasoPersona.getProcesoReclamacion();
            ProcesoReclamacion procesoReclamacionNew = casoPersona.getProcesoReclamacion();
            MedicamentosCaso medicamentosCasoOld = persistentCasoPersona.getMedicamentosCaso();
            MedicamentosCaso medicamentosCasoNew = casoPersona.getMedicamentosCaso();
            Agendamiento agendamientoOld = persistentCasoPersona.getAgendamiento();
            Agendamiento agendamientoNew = casoPersona.getAgendamiento();
            Diagnostico diagnosticoOld = persistentCasoPersona.getDiagnostico();
            Diagnostico diagnosticoNew = casoPersona.getDiagnostico();
            FlujoCaso flujoCasoOld = persistentCasoPersona.getFlujoCaso();
            FlujoCaso flujoCasoNew = casoPersona.getFlujoCaso();
            CambioCaso cambioCasoOld = persistentCasoPersona.getCambioCaso();
            CambioCaso cambioCasoNew = casoPersona.getCambioCaso();
            ProcesoCalificacion procesoCalificacionOld = persistentCasoPersona.getProcesoCalificacion();
            ProcesoCalificacion procesoCalificacionNew = casoPersona.getProcesoCalificacion();
            List<String> illegalOrphanMessages = null;
            if (casoAccionesOld != null && !casoAccionesOld.equals(casoAccionesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain CasoAcciones " + casoAccionesOld + " since its casoPersona field is not nullable.");
            }
            if (procesoReclamacionOld != null && !procesoReclamacionOld.equals(procesoReclamacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ProcesoReclamacion " + procesoReclamacionOld + " since its casoPersona field is not nullable.");
            }
            if (medicamentosCasoOld != null && !medicamentosCasoOld.equals(medicamentosCasoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain MedicamentosCaso " + medicamentosCasoOld + " since its casoPersona field is not nullable.");
            }
            if (agendamientoOld != null && !agendamientoOld.equals(agendamientoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Agendamiento " + agendamientoOld + " since its casoPersona field is not nullable.");
            }
            if (diagnosticoOld != null && !diagnosticoOld.equals(diagnosticoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Diagnostico " + diagnosticoOld + " since its casoPersona field is not nullable.");
            }
            if (flujoCasoOld != null && !flujoCasoOld.equals(flujoCasoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain FlujoCaso " + flujoCasoOld + " since its casoPersona field is not nullable.");
            }
            if (cambioCasoOld != null && !cambioCasoOld.equals(cambioCasoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain CambioCaso " + cambioCasoOld + " since its casoPersona field is not nullable.");
            }
            if (procesoCalificacionOld != null && !procesoCalificacionOld.equals(procesoCalificacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ProcesoCalificacion " + procesoCalificacionOld + " since its casoPersona field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoAccionesNew != null) {
                casoAccionesNew = em.getReference(casoAccionesNew.getClass(), casoAccionesNew.getCasoPersonaIdCaso());
                casoPersona.setCasoAcciones(casoAccionesNew);
            }
            if (personaCedulaNew != null) {
                personaCedulaNew = em.getReference(personaCedulaNew.getClass(), personaCedulaNew.getCedula());
                casoPersona.setPersonaCedula(personaCedulaNew);
            }
            if (tipoCasoCodigoTipoCasoNew != null) {
                tipoCasoCodigoTipoCasoNew = em.getReference(tipoCasoCodigoTipoCasoNew.getClass(), tipoCasoCodigoTipoCasoNew.getCodigoTipoCaso());
                casoPersona.setTipoCasoCodigoTipoCaso(tipoCasoCodigoTipoCasoNew);
            }
            if (procesoReclamacionNew != null) {
                procesoReclamacionNew = em.getReference(procesoReclamacionNew.getClass(), procesoReclamacionNew.getCasoPersonaIdCaso());
                casoPersona.setProcesoReclamacion(procesoReclamacionNew);
            }
            if (medicamentosCasoNew != null) {
                medicamentosCasoNew = em.getReference(medicamentosCasoNew.getClass(), medicamentosCasoNew.getCasoPersonaIdCaso());
                casoPersona.setMedicamentosCaso(medicamentosCasoNew);
            }
            if (agendamientoNew != null) {
                agendamientoNew = em.getReference(agendamientoNew.getClass(), agendamientoNew.getCasoPersonaIdCaso());
                casoPersona.setAgendamiento(agendamientoNew);
            }
            if (diagnosticoNew != null) {
                diagnosticoNew = em.getReference(diagnosticoNew.getClass(), diagnosticoNew.getCasoPersonaIdCaso());
                casoPersona.setDiagnostico(diagnosticoNew);
            }
            if (flujoCasoNew != null) {
                flujoCasoNew = em.getReference(flujoCasoNew.getClass(), flujoCasoNew.getCasoPersonaIdCaso());
                casoPersona.setFlujoCaso(flujoCasoNew);
            }
            if (cambioCasoNew != null) {
                cambioCasoNew = em.getReference(cambioCasoNew.getClass(), cambioCasoNew.getCasoPersonaIdCaso());
                casoPersona.setCambioCaso(cambioCasoNew);
            }
            if (procesoCalificacionNew != null) {
                procesoCalificacionNew = em.getReference(procesoCalificacionNew.getClass(), procesoCalificacionNew.getCasoPersonaIdCaso());
                casoPersona.setProcesoCalificacion(procesoCalificacionNew);
            }
            casoPersona = em.merge(casoPersona);
            if (casoAccionesNew != null && !casoAccionesNew.equals(casoAccionesOld)) {
                CasoPersona oldCasoPersonaOfCasoAcciones = casoAccionesNew.getCasoPersona();
                if (oldCasoPersonaOfCasoAcciones != null) {
                    oldCasoPersonaOfCasoAcciones.setCasoAcciones(null);
                    oldCasoPersonaOfCasoAcciones = em.merge(oldCasoPersonaOfCasoAcciones);
                }
                casoAccionesNew.setCasoPersona(casoPersona);
                casoAccionesNew = em.merge(casoAccionesNew);
            }
            if (personaCedulaOld != null && !personaCedulaOld.equals(personaCedulaNew)) {
                personaCedulaOld.getCasoPersonaCollection().remove(casoPersona);
                personaCedulaOld = em.merge(personaCedulaOld);
            }
            if (personaCedulaNew != null && !personaCedulaNew.equals(personaCedulaOld)) {
                personaCedulaNew.getCasoPersonaCollection().add(casoPersona);
                personaCedulaNew = em.merge(personaCedulaNew);
            }
            if (tipoCasoCodigoTipoCasoOld != null && !tipoCasoCodigoTipoCasoOld.equals(tipoCasoCodigoTipoCasoNew)) {
                tipoCasoCodigoTipoCasoOld.getCasoPersonaCollection().remove(casoPersona);
                tipoCasoCodigoTipoCasoOld = em.merge(tipoCasoCodigoTipoCasoOld);
            }
            if (tipoCasoCodigoTipoCasoNew != null && !tipoCasoCodigoTipoCasoNew.equals(tipoCasoCodigoTipoCasoOld)) {
                tipoCasoCodigoTipoCasoNew.getCasoPersonaCollection().add(casoPersona);
                tipoCasoCodigoTipoCasoNew = em.merge(tipoCasoCodigoTipoCasoNew);
            }
            if (procesoReclamacionNew != null && !procesoReclamacionNew.equals(procesoReclamacionOld)) {
                CasoPersona oldCasoPersonaOfProcesoReclamacion = procesoReclamacionNew.getCasoPersona();
                if (oldCasoPersonaOfProcesoReclamacion != null) {
                    oldCasoPersonaOfProcesoReclamacion.setProcesoReclamacion(null);
                    oldCasoPersonaOfProcesoReclamacion = em.merge(oldCasoPersonaOfProcesoReclamacion);
                }
                procesoReclamacionNew.setCasoPersona(casoPersona);
                procesoReclamacionNew = em.merge(procesoReclamacionNew);
            }
            if (medicamentosCasoNew != null && !medicamentosCasoNew.equals(medicamentosCasoOld)) {
                CasoPersona oldCasoPersonaOfMedicamentosCaso = medicamentosCasoNew.getCasoPersona();
                if (oldCasoPersonaOfMedicamentosCaso != null) {
                    oldCasoPersonaOfMedicamentosCaso.setMedicamentosCaso(null);
                    oldCasoPersonaOfMedicamentosCaso = em.merge(oldCasoPersonaOfMedicamentosCaso);
                }
                medicamentosCasoNew.setCasoPersona(casoPersona);
                medicamentosCasoNew = em.merge(medicamentosCasoNew);
            }
            if (agendamientoNew != null && !agendamientoNew.equals(agendamientoOld)) {
                CasoPersona oldCasoPersonaOfAgendamiento = agendamientoNew.getCasoPersona();
                if (oldCasoPersonaOfAgendamiento != null) {
                    oldCasoPersonaOfAgendamiento.setAgendamiento(null);
                    oldCasoPersonaOfAgendamiento = em.merge(oldCasoPersonaOfAgendamiento);
                }
                agendamientoNew.setCasoPersona(casoPersona);
                agendamientoNew = em.merge(agendamientoNew);
            }
            if (diagnosticoNew != null && !diagnosticoNew.equals(diagnosticoOld)) {
                CasoPersona oldCasoPersonaOfDiagnostico = diagnosticoNew.getCasoPersona();
                if (oldCasoPersonaOfDiagnostico != null) {
                    oldCasoPersonaOfDiagnostico.setDiagnostico(null);
                    oldCasoPersonaOfDiagnostico = em.merge(oldCasoPersonaOfDiagnostico);
                }
                diagnosticoNew.setCasoPersona(casoPersona);
                diagnosticoNew = em.merge(diagnosticoNew);
            }
            if (flujoCasoNew != null && !flujoCasoNew.equals(flujoCasoOld)) {
                CasoPersona oldCasoPersonaOfFlujoCaso = flujoCasoNew.getCasoPersona();
                if (oldCasoPersonaOfFlujoCaso != null) {
                    oldCasoPersonaOfFlujoCaso.setFlujoCaso(null);
                    oldCasoPersonaOfFlujoCaso = em.merge(oldCasoPersonaOfFlujoCaso);
                }
                flujoCasoNew.setCasoPersona(casoPersona);
                flujoCasoNew = em.merge(flujoCasoNew);
            }
            if (cambioCasoNew != null && !cambioCasoNew.equals(cambioCasoOld)) {
                CasoPersona oldCasoPersonaOfCambioCaso = cambioCasoNew.getCasoPersona();
                if (oldCasoPersonaOfCambioCaso != null) {
                    oldCasoPersonaOfCambioCaso.setCambioCaso(null);
                    oldCasoPersonaOfCambioCaso = em.merge(oldCasoPersonaOfCambioCaso);
                }
                cambioCasoNew.setCasoPersona(casoPersona);
                cambioCasoNew = em.merge(cambioCasoNew);
            }
            if (procesoCalificacionNew != null && !procesoCalificacionNew.equals(procesoCalificacionOld)) {
                CasoPersona oldCasoPersonaOfProcesoCalificacion = procesoCalificacionNew.getCasoPersona();
                if (oldCasoPersonaOfProcesoCalificacion != null) {
                    oldCasoPersonaOfProcesoCalificacion.setProcesoCalificacion(null);
                    oldCasoPersonaOfProcesoCalificacion = em.merge(oldCasoPersonaOfProcesoCalificacion);
                }
                procesoCalificacionNew.setCasoPersona(casoPersona);
                procesoCalificacionNew = em.merge(procesoCalificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = casoPersona.getIdCaso();
                if (findCasoPersona(id) == null) {
                    throw new NonexistentEntityException("The casoPersona with id " + id + " no longer exists.");
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
            CasoPersona casoPersona;
            try {
                casoPersona = em.getReference(CasoPersona.class, id);
                casoPersona.getIdCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoPersona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            CasoAcciones casoAccionesOrphanCheck = casoPersona.getCasoAcciones();
            if (casoAccionesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the CasoAcciones " + casoAccionesOrphanCheck + " in its casoAcciones field has a non-nullable casoPersona field.");
            }
            ProcesoReclamacion procesoReclamacionOrphanCheck = casoPersona.getProcesoReclamacion();
            if (procesoReclamacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the ProcesoReclamacion " + procesoReclamacionOrphanCheck + " in its procesoReclamacion field has a non-nullable casoPersona field.");
            }
            MedicamentosCaso medicamentosCasoOrphanCheck = casoPersona.getMedicamentosCaso();
            if (medicamentosCasoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the MedicamentosCaso " + medicamentosCasoOrphanCheck + " in its medicamentosCaso field has a non-nullable casoPersona field.");
            }
            Agendamiento agendamientoOrphanCheck = casoPersona.getAgendamiento();
            if (agendamientoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the Agendamiento " + agendamientoOrphanCheck + " in its agendamiento field has a non-nullable casoPersona field.");
            }
            Diagnostico diagnosticoOrphanCheck = casoPersona.getDiagnostico();
            if (diagnosticoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the Diagnostico " + diagnosticoOrphanCheck + " in its diagnostico field has a non-nullable casoPersona field.");
            }
            FlujoCaso flujoCasoOrphanCheck = casoPersona.getFlujoCaso();
            if (flujoCasoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the FlujoCaso " + flujoCasoOrphanCheck + " in its flujoCaso field has a non-nullable casoPersona field.");
            }
            CambioCaso cambioCasoOrphanCheck = casoPersona.getCambioCaso();
            if (cambioCasoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the CambioCaso " + cambioCasoOrphanCheck + " in its cambioCaso field has a non-nullable casoPersona field.");
            }
            ProcesoCalificacion procesoCalificacionOrphanCheck = casoPersona.getProcesoCalificacion();
            if (procesoCalificacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CasoPersona (" + casoPersona + ") cannot be destroyed since the ProcesoCalificacion " + procesoCalificacionOrphanCheck + " in its procesoCalificacion field has a non-nullable casoPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona personaCedula = casoPersona.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula.getCasoPersonaCollection().remove(casoPersona);
                personaCedula = em.merge(personaCedula);
            }
            TipoCaso tipoCasoCodigoTipoCaso = casoPersona.getTipoCasoCodigoTipoCaso();
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso.getCasoPersonaCollection().remove(casoPersona);
                tipoCasoCodigoTipoCaso = em.merge(tipoCasoCodigoTipoCaso);
            }
            em.remove(casoPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoPersona> findCasoPersonaEntities() {
        return findCasoPersonaEntities(true, -1, -1);
    }

    public List<CasoPersona> findCasoPersonaEntities(int maxResults, int firstResult) {
        return findCasoPersonaEntities(false, maxResults, firstResult);
    }

    private List<CasoPersona> findCasoPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoPersona.class));
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

    public CasoPersona findCasoPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CasoPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoPersona> rt = cq.from(CasoPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

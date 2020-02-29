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
import java.util.ArrayList;
import java.util.Collection;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.Formacion;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.Agendamiento;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getCasoAccionesCollection() == null) {
            usuario.setCasoAccionesCollection(new ArrayList<CasoAcciones>());
        }
        if (usuario.getProcesoReclamacionCollection() == null) {
            usuario.setProcesoReclamacionCollection(new ArrayList<ProcesoReclamacion>());
        }
        if (usuario.getFormacionCollection() == null) {
            usuario.setFormacionCollection(new ArrayList<Formacion>());
        }
        if (usuario.getMedicamentosCasoCollection() == null) {
            usuario.setMedicamentosCasoCollection(new ArrayList<MedicamentosCaso>());
        }
        if (usuario.getAgendamientoCollection() == null) {
            usuario.setAgendamientoCollection(new ArrayList<Agendamiento>());
        }
        if (usuario.getDiagnosticoCollection() == null) {
            usuario.setDiagnosticoCollection(new ArrayList<Diagnostico>());
        }
        if (usuario.getFlujoCasoCollection() == null) {
            usuario.setFlujoCasoCollection(new ArrayList<FlujoCaso>());
        }
        if (usuario.getCambioCasoCollection() == null) {
            usuario.setCambioCasoCollection(new ArrayList<CambioCaso>());
        }
        if (usuario.getProcesoCalificacionCollection() == null) {
            usuario.setProcesoCalificacionCollection(new ArrayList<ProcesoCalificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CasoAcciones> attachedCasoAccionesCollection = new ArrayList<CasoAcciones>();
            for (CasoAcciones casoAccionesCollectionCasoAccionesToAttach : usuario.getCasoAccionesCollection()) {
                casoAccionesCollectionCasoAccionesToAttach = em.getReference(casoAccionesCollectionCasoAccionesToAttach.getClass(), casoAccionesCollectionCasoAccionesToAttach.getCasoPersonaIdCaso());
                attachedCasoAccionesCollection.add(casoAccionesCollectionCasoAccionesToAttach);
            }
            usuario.setCasoAccionesCollection(attachedCasoAccionesCollection);
            Collection<ProcesoReclamacion> attachedProcesoReclamacionCollection = new ArrayList<ProcesoReclamacion>();
            for (ProcesoReclamacion procesoReclamacionCollectionProcesoReclamacionToAttach : usuario.getProcesoReclamacionCollection()) {
                procesoReclamacionCollectionProcesoReclamacionToAttach = em.getReference(procesoReclamacionCollectionProcesoReclamacionToAttach.getClass(), procesoReclamacionCollectionProcesoReclamacionToAttach.getCasoPersonaIdCaso());
                attachedProcesoReclamacionCollection.add(procesoReclamacionCollectionProcesoReclamacionToAttach);
            }
            usuario.setProcesoReclamacionCollection(attachedProcesoReclamacionCollection);
            Collection<Formacion> attachedFormacionCollection = new ArrayList<Formacion>();
            for (Formacion formacionCollectionFormacionToAttach : usuario.getFormacionCollection()) {
                formacionCollectionFormacionToAttach = em.getReference(formacionCollectionFormacionToAttach.getClass(), formacionCollectionFormacionToAttach.getIdFormacion());
                attachedFormacionCollection.add(formacionCollectionFormacionToAttach);
            }
            usuario.setFormacionCollection(attachedFormacionCollection);
            Collection<MedicamentosCaso> attachedMedicamentosCasoCollection = new ArrayList<MedicamentosCaso>();
            for (MedicamentosCaso medicamentosCasoCollectionMedicamentosCasoToAttach : usuario.getMedicamentosCasoCollection()) {
                medicamentosCasoCollectionMedicamentosCasoToAttach = em.getReference(medicamentosCasoCollectionMedicamentosCasoToAttach.getClass(), medicamentosCasoCollectionMedicamentosCasoToAttach.getCasoPersonaIdCaso());
                attachedMedicamentosCasoCollection.add(medicamentosCasoCollectionMedicamentosCasoToAttach);
            }
            usuario.setMedicamentosCasoCollection(attachedMedicamentosCasoCollection);
            Collection<Agendamiento> attachedAgendamientoCollection = new ArrayList<Agendamiento>();
            for (Agendamiento agendamientoCollectionAgendamientoToAttach : usuario.getAgendamientoCollection()) {
                agendamientoCollectionAgendamientoToAttach = em.getReference(agendamientoCollectionAgendamientoToAttach.getClass(), agendamientoCollectionAgendamientoToAttach.getCasoPersonaIdCaso());
                attachedAgendamientoCollection.add(agendamientoCollectionAgendamientoToAttach);
            }
            usuario.setAgendamientoCollection(attachedAgendamientoCollection);
            Collection<Diagnostico> attachedDiagnosticoCollection = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoCollectionDiagnosticoToAttach : usuario.getDiagnosticoCollection()) {
                diagnosticoCollectionDiagnosticoToAttach = em.getReference(diagnosticoCollectionDiagnosticoToAttach.getClass(), diagnosticoCollectionDiagnosticoToAttach.getCasoPersonaIdCaso());
                attachedDiagnosticoCollection.add(diagnosticoCollectionDiagnosticoToAttach);
            }
            usuario.setDiagnosticoCollection(attachedDiagnosticoCollection);
            Collection<FlujoCaso> attachedFlujoCasoCollection = new ArrayList<FlujoCaso>();
            for (FlujoCaso flujoCasoCollectionFlujoCasoToAttach : usuario.getFlujoCasoCollection()) {
                flujoCasoCollectionFlujoCasoToAttach = em.getReference(flujoCasoCollectionFlujoCasoToAttach.getClass(), flujoCasoCollectionFlujoCasoToAttach.getCasoPersonaIdCaso());
                attachedFlujoCasoCollection.add(flujoCasoCollectionFlujoCasoToAttach);
            }
            usuario.setFlujoCasoCollection(attachedFlujoCasoCollection);
            Collection<CambioCaso> attachedCambioCasoCollection = new ArrayList<CambioCaso>();
            for (CambioCaso cambioCasoCollectionCambioCasoToAttach : usuario.getCambioCasoCollection()) {
                cambioCasoCollectionCambioCasoToAttach = em.getReference(cambioCasoCollectionCambioCasoToAttach.getClass(), cambioCasoCollectionCambioCasoToAttach.getCasoPersonaIdCaso());
                attachedCambioCasoCollection.add(cambioCasoCollectionCambioCasoToAttach);
            }
            usuario.setCambioCasoCollection(attachedCambioCasoCollection);
            Collection<ProcesoCalificacion> attachedProcesoCalificacionCollection = new ArrayList<ProcesoCalificacion>();
            for (ProcesoCalificacion procesoCalificacionCollectionProcesoCalificacionToAttach : usuario.getProcesoCalificacionCollection()) {
                procesoCalificacionCollectionProcesoCalificacionToAttach = em.getReference(procesoCalificacionCollectionProcesoCalificacionToAttach.getClass(), procesoCalificacionCollectionProcesoCalificacionToAttach.getCasoPersonaIdCaso());
                attachedProcesoCalificacionCollection.add(procesoCalificacionCollectionProcesoCalificacionToAttach);
            }
            usuario.setProcesoCalificacionCollection(attachedProcesoCalificacionCollection);
            em.persist(usuario);
            for (CasoAcciones casoAccionesCollectionCasoAcciones : usuario.getCasoAccionesCollection()) {
                Usuario oldUsuarioCedulaOfCasoAccionesCollectionCasoAcciones = casoAccionesCollectionCasoAcciones.getUsuarioCedula();
                casoAccionesCollectionCasoAcciones.setUsuarioCedula(usuario);
                casoAccionesCollectionCasoAcciones = em.merge(casoAccionesCollectionCasoAcciones);
                if (oldUsuarioCedulaOfCasoAccionesCollectionCasoAcciones != null) {
                    oldUsuarioCedulaOfCasoAccionesCollectionCasoAcciones.getCasoAccionesCollection().remove(casoAccionesCollectionCasoAcciones);
                    oldUsuarioCedulaOfCasoAccionesCollectionCasoAcciones = em.merge(oldUsuarioCedulaOfCasoAccionesCollectionCasoAcciones);
                }
            }
            for (ProcesoReclamacion procesoReclamacionCollectionProcesoReclamacion : usuario.getProcesoReclamacionCollection()) {
                Usuario oldUsuarioCedulaOfProcesoReclamacionCollectionProcesoReclamacion = procesoReclamacionCollectionProcesoReclamacion.getUsuarioCedula();
                procesoReclamacionCollectionProcesoReclamacion.setUsuarioCedula(usuario);
                procesoReclamacionCollectionProcesoReclamacion = em.merge(procesoReclamacionCollectionProcesoReclamacion);
                if (oldUsuarioCedulaOfProcesoReclamacionCollectionProcesoReclamacion != null) {
                    oldUsuarioCedulaOfProcesoReclamacionCollectionProcesoReclamacion.getProcesoReclamacionCollection().remove(procesoReclamacionCollectionProcesoReclamacion);
                    oldUsuarioCedulaOfProcesoReclamacionCollectionProcesoReclamacion = em.merge(oldUsuarioCedulaOfProcesoReclamacionCollectionProcesoReclamacion);
                }
            }
            for (Formacion formacionCollectionFormacion : usuario.getFormacionCollection()) {
                Usuario oldUsuarioCedulaOfFormacionCollectionFormacion = formacionCollectionFormacion.getUsuarioCedula();
                formacionCollectionFormacion.setUsuarioCedula(usuario);
                formacionCollectionFormacion = em.merge(formacionCollectionFormacion);
                if (oldUsuarioCedulaOfFormacionCollectionFormacion != null) {
                    oldUsuarioCedulaOfFormacionCollectionFormacion.getFormacionCollection().remove(formacionCollectionFormacion);
                    oldUsuarioCedulaOfFormacionCollectionFormacion = em.merge(oldUsuarioCedulaOfFormacionCollectionFormacion);
                }
            }
            for (MedicamentosCaso medicamentosCasoCollectionMedicamentosCaso : usuario.getMedicamentosCasoCollection()) {
                Usuario oldUsuarioCedulaOfMedicamentosCasoCollectionMedicamentosCaso = medicamentosCasoCollectionMedicamentosCaso.getUsuarioCedula();
                medicamentosCasoCollectionMedicamentosCaso.setUsuarioCedula(usuario);
                medicamentosCasoCollectionMedicamentosCaso = em.merge(medicamentosCasoCollectionMedicamentosCaso);
                if (oldUsuarioCedulaOfMedicamentosCasoCollectionMedicamentosCaso != null) {
                    oldUsuarioCedulaOfMedicamentosCasoCollectionMedicamentosCaso.getMedicamentosCasoCollection().remove(medicamentosCasoCollectionMedicamentosCaso);
                    oldUsuarioCedulaOfMedicamentosCasoCollectionMedicamentosCaso = em.merge(oldUsuarioCedulaOfMedicamentosCasoCollectionMedicamentosCaso);
                }
            }
            for (Agendamiento agendamientoCollectionAgendamiento : usuario.getAgendamientoCollection()) {
                Usuario oldUsuarioCedulaOfAgendamientoCollectionAgendamiento = agendamientoCollectionAgendamiento.getUsuarioCedula();
                agendamientoCollectionAgendamiento.setUsuarioCedula(usuario);
                agendamientoCollectionAgendamiento = em.merge(agendamientoCollectionAgendamiento);
                if (oldUsuarioCedulaOfAgendamientoCollectionAgendamiento != null) {
                    oldUsuarioCedulaOfAgendamientoCollectionAgendamiento.getAgendamientoCollection().remove(agendamientoCollectionAgendamiento);
                    oldUsuarioCedulaOfAgendamientoCollectionAgendamiento = em.merge(oldUsuarioCedulaOfAgendamientoCollectionAgendamiento);
                }
            }
            for (Diagnostico diagnosticoCollectionDiagnostico : usuario.getDiagnosticoCollection()) {
                Usuario oldUsuarioCedulaOfDiagnosticoCollectionDiagnostico = diagnosticoCollectionDiagnostico.getUsuarioCedula();
                diagnosticoCollectionDiagnostico.setUsuarioCedula(usuario);
                diagnosticoCollectionDiagnostico = em.merge(diagnosticoCollectionDiagnostico);
                if (oldUsuarioCedulaOfDiagnosticoCollectionDiagnostico != null) {
                    oldUsuarioCedulaOfDiagnosticoCollectionDiagnostico.getDiagnosticoCollection().remove(diagnosticoCollectionDiagnostico);
                    oldUsuarioCedulaOfDiagnosticoCollectionDiagnostico = em.merge(oldUsuarioCedulaOfDiagnosticoCollectionDiagnostico);
                }
            }
            for (FlujoCaso flujoCasoCollectionFlujoCaso : usuario.getFlujoCasoCollection()) {
                Usuario oldUsuarioCedulaOfFlujoCasoCollectionFlujoCaso = flujoCasoCollectionFlujoCaso.getUsuarioCedula();
                flujoCasoCollectionFlujoCaso.setUsuarioCedula(usuario);
                flujoCasoCollectionFlujoCaso = em.merge(flujoCasoCollectionFlujoCaso);
                if (oldUsuarioCedulaOfFlujoCasoCollectionFlujoCaso != null) {
                    oldUsuarioCedulaOfFlujoCasoCollectionFlujoCaso.getFlujoCasoCollection().remove(flujoCasoCollectionFlujoCaso);
                    oldUsuarioCedulaOfFlujoCasoCollectionFlujoCaso = em.merge(oldUsuarioCedulaOfFlujoCasoCollectionFlujoCaso);
                }
            }
            for (CambioCaso cambioCasoCollectionCambioCaso : usuario.getCambioCasoCollection()) {
                Usuario oldUsuarioCedulaOfCambioCasoCollectionCambioCaso = cambioCasoCollectionCambioCaso.getUsuarioCedula();
                cambioCasoCollectionCambioCaso.setUsuarioCedula(usuario);
                cambioCasoCollectionCambioCaso = em.merge(cambioCasoCollectionCambioCaso);
                if (oldUsuarioCedulaOfCambioCasoCollectionCambioCaso != null) {
                    oldUsuarioCedulaOfCambioCasoCollectionCambioCaso.getCambioCasoCollection().remove(cambioCasoCollectionCambioCaso);
                    oldUsuarioCedulaOfCambioCasoCollectionCambioCaso = em.merge(oldUsuarioCedulaOfCambioCasoCollectionCambioCaso);
                }
            }
            for (ProcesoCalificacion procesoCalificacionCollectionProcesoCalificacion : usuario.getProcesoCalificacionCollection()) {
                Usuario oldUsuarioCedulaOfProcesoCalificacionCollectionProcesoCalificacion = procesoCalificacionCollectionProcesoCalificacion.getUsuarioCedula();
                procesoCalificacionCollectionProcesoCalificacion.setUsuarioCedula(usuario);
                procesoCalificacionCollectionProcesoCalificacion = em.merge(procesoCalificacionCollectionProcesoCalificacion);
                if (oldUsuarioCedulaOfProcesoCalificacionCollectionProcesoCalificacion != null) {
                    oldUsuarioCedulaOfProcesoCalificacionCollectionProcesoCalificacion.getProcesoCalificacionCollection().remove(procesoCalificacionCollectionProcesoCalificacion);
                    oldUsuarioCedulaOfProcesoCalificacionCollectionProcesoCalificacion = em.merge(oldUsuarioCedulaOfProcesoCalificacionCollectionProcesoCalificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCedula()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCedula());
            Collection<CasoAcciones> casoAccionesCollectionOld = persistentUsuario.getCasoAccionesCollection();
            Collection<CasoAcciones> casoAccionesCollectionNew = usuario.getCasoAccionesCollection();
            Collection<ProcesoReclamacion> procesoReclamacionCollectionOld = persistentUsuario.getProcesoReclamacionCollection();
            Collection<ProcesoReclamacion> procesoReclamacionCollectionNew = usuario.getProcesoReclamacionCollection();
            Collection<Formacion> formacionCollectionOld = persistentUsuario.getFormacionCollection();
            Collection<Formacion> formacionCollectionNew = usuario.getFormacionCollection();
            Collection<MedicamentosCaso> medicamentosCasoCollectionOld = persistentUsuario.getMedicamentosCasoCollection();
            Collection<MedicamentosCaso> medicamentosCasoCollectionNew = usuario.getMedicamentosCasoCollection();
            Collection<Agendamiento> agendamientoCollectionOld = persistentUsuario.getAgendamientoCollection();
            Collection<Agendamiento> agendamientoCollectionNew = usuario.getAgendamientoCollection();
            Collection<Diagnostico> diagnosticoCollectionOld = persistentUsuario.getDiagnosticoCollection();
            Collection<Diagnostico> diagnosticoCollectionNew = usuario.getDiagnosticoCollection();
            Collection<FlujoCaso> flujoCasoCollectionOld = persistentUsuario.getFlujoCasoCollection();
            Collection<FlujoCaso> flujoCasoCollectionNew = usuario.getFlujoCasoCollection();
            Collection<CambioCaso> cambioCasoCollectionOld = persistentUsuario.getCambioCasoCollection();
            Collection<CambioCaso> cambioCasoCollectionNew = usuario.getCambioCasoCollection();
            Collection<ProcesoCalificacion> procesoCalificacionCollectionOld = persistentUsuario.getProcesoCalificacionCollection();
            Collection<ProcesoCalificacion> procesoCalificacionCollectionNew = usuario.getProcesoCalificacionCollection();
            List<String> illegalOrphanMessages = null;
            for (CasoAcciones casoAccionesCollectionOldCasoAcciones : casoAccionesCollectionOld) {
                if (!casoAccionesCollectionNew.contains(casoAccionesCollectionOldCasoAcciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CasoAcciones " + casoAccionesCollectionOldCasoAcciones + " since its usuarioCedula field is not nullable.");
                }
            }
            for (ProcesoReclamacion procesoReclamacionCollectionOldProcesoReclamacion : procesoReclamacionCollectionOld) {
                if (!procesoReclamacionCollectionNew.contains(procesoReclamacionCollectionOldProcesoReclamacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProcesoReclamacion " + procesoReclamacionCollectionOldProcesoReclamacion + " since its usuarioCedula field is not nullable.");
                }
            }
            for (Formacion formacionCollectionOldFormacion : formacionCollectionOld) {
                if (!formacionCollectionNew.contains(formacionCollectionOldFormacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Formacion " + formacionCollectionOldFormacion + " since its usuarioCedula field is not nullable.");
                }
            }
            for (MedicamentosCaso medicamentosCasoCollectionOldMedicamentosCaso : medicamentosCasoCollectionOld) {
                if (!medicamentosCasoCollectionNew.contains(medicamentosCasoCollectionOldMedicamentosCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MedicamentosCaso " + medicamentosCasoCollectionOldMedicamentosCaso + " since its usuarioCedula field is not nullable.");
                }
            }
            for (Agendamiento agendamientoCollectionOldAgendamiento : agendamientoCollectionOld) {
                if (!agendamientoCollectionNew.contains(agendamientoCollectionOldAgendamiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agendamiento " + agendamientoCollectionOldAgendamiento + " since its usuarioCedula field is not nullable.");
                }
            }
            for (Diagnostico diagnosticoCollectionOldDiagnostico : diagnosticoCollectionOld) {
                if (!diagnosticoCollectionNew.contains(diagnosticoCollectionOldDiagnostico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Diagnostico " + diagnosticoCollectionOldDiagnostico + " since its usuarioCedula field is not nullable.");
                }
            }
            for (FlujoCaso flujoCasoCollectionOldFlujoCaso : flujoCasoCollectionOld) {
                if (!flujoCasoCollectionNew.contains(flujoCasoCollectionOldFlujoCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FlujoCaso " + flujoCasoCollectionOldFlujoCaso + " since its usuarioCedula field is not nullable.");
                }
            }
            for (CambioCaso cambioCasoCollectionOldCambioCaso : cambioCasoCollectionOld) {
                if (!cambioCasoCollectionNew.contains(cambioCasoCollectionOldCambioCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CambioCaso " + cambioCasoCollectionOldCambioCaso + " since its usuarioCedula field is not nullable.");
                }
            }
            for (ProcesoCalificacion procesoCalificacionCollectionOldProcesoCalificacion : procesoCalificacionCollectionOld) {
                if (!procesoCalificacionCollectionNew.contains(procesoCalificacionCollectionOldProcesoCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProcesoCalificacion " + procesoCalificacionCollectionOldProcesoCalificacion + " since its usuarioCedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CasoAcciones> attachedCasoAccionesCollectionNew = new ArrayList<CasoAcciones>();
            for (CasoAcciones casoAccionesCollectionNewCasoAccionesToAttach : casoAccionesCollectionNew) {
                casoAccionesCollectionNewCasoAccionesToAttach = em.getReference(casoAccionesCollectionNewCasoAccionesToAttach.getClass(), casoAccionesCollectionNewCasoAccionesToAttach.getCasoPersonaIdCaso());
                attachedCasoAccionesCollectionNew.add(casoAccionesCollectionNewCasoAccionesToAttach);
            }
            casoAccionesCollectionNew = attachedCasoAccionesCollectionNew;
            usuario.setCasoAccionesCollection(casoAccionesCollectionNew);
            Collection<ProcesoReclamacion> attachedProcesoReclamacionCollectionNew = new ArrayList<ProcesoReclamacion>();
            for (ProcesoReclamacion procesoReclamacionCollectionNewProcesoReclamacionToAttach : procesoReclamacionCollectionNew) {
                procesoReclamacionCollectionNewProcesoReclamacionToAttach = em.getReference(procesoReclamacionCollectionNewProcesoReclamacionToAttach.getClass(), procesoReclamacionCollectionNewProcesoReclamacionToAttach.getCasoPersonaIdCaso());
                attachedProcesoReclamacionCollectionNew.add(procesoReclamacionCollectionNewProcesoReclamacionToAttach);
            }
            procesoReclamacionCollectionNew = attachedProcesoReclamacionCollectionNew;
            usuario.setProcesoReclamacionCollection(procesoReclamacionCollectionNew);
            Collection<Formacion> attachedFormacionCollectionNew = new ArrayList<Formacion>();
            for (Formacion formacionCollectionNewFormacionToAttach : formacionCollectionNew) {
                formacionCollectionNewFormacionToAttach = em.getReference(formacionCollectionNewFormacionToAttach.getClass(), formacionCollectionNewFormacionToAttach.getIdFormacion());
                attachedFormacionCollectionNew.add(formacionCollectionNewFormacionToAttach);
            }
            formacionCollectionNew = attachedFormacionCollectionNew;
            usuario.setFormacionCollection(formacionCollectionNew);
            Collection<MedicamentosCaso> attachedMedicamentosCasoCollectionNew = new ArrayList<MedicamentosCaso>();
            for (MedicamentosCaso medicamentosCasoCollectionNewMedicamentosCasoToAttach : medicamentosCasoCollectionNew) {
                medicamentosCasoCollectionNewMedicamentosCasoToAttach = em.getReference(medicamentosCasoCollectionNewMedicamentosCasoToAttach.getClass(), medicamentosCasoCollectionNewMedicamentosCasoToAttach.getCasoPersonaIdCaso());
                attachedMedicamentosCasoCollectionNew.add(medicamentosCasoCollectionNewMedicamentosCasoToAttach);
            }
            medicamentosCasoCollectionNew = attachedMedicamentosCasoCollectionNew;
            usuario.setMedicamentosCasoCollection(medicamentosCasoCollectionNew);
            Collection<Agendamiento> attachedAgendamientoCollectionNew = new ArrayList<Agendamiento>();
            for (Agendamiento agendamientoCollectionNewAgendamientoToAttach : agendamientoCollectionNew) {
                agendamientoCollectionNewAgendamientoToAttach = em.getReference(agendamientoCollectionNewAgendamientoToAttach.getClass(), agendamientoCollectionNewAgendamientoToAttach.getCasoPersonaIdCaso());
                attachedAgendamientoCollectionNew.add(agendamientoCollectionNewAgendamientoToAttach);
            }
            agendamientoCollectionNew = attachedAgendamientoCollectionNew;
            usuario.setAgendamientoCollection(agendamientoCollectionNew);
            Collection<Diagnostico> attachedDiagnosticoCollectionNew = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoCollectionNewDiagnosticoToAttach : diagnosticoCollectionNew) {
                diagnosticoCollectionNewDiagnosticoToAttach = em.getReference(diagnosticoCollectionNewDiagnosticoToAttach.getClass(), diagnosticoCollectionNewDiagnosticoToAttach.getCasoPersonaIdCaso());
                attachedDiagnosticoCollectionNew.add(diagnosticoCollectionNewDiagnosticoToAttach);
            }
            diagnosticoCollectionNew = attachedDiagnosticoCollectionNew;
            usuario.setDiagnosticoCollection(diagnosticoCollectionNew);
            Collection<FlujoCaso> attachedFlujoCasoCollectionNew = new ArrayList<FlujoCaso>();
            for (FlujoCaso flujoCasoCollectionNewFlujoCasoToAttach : flujoCasoCollectionNew) {
                flujoCasoCollectionNewFlujoCasoToAttach = em.getReference(flujoCasoCollectionNewFlujoCasoToAttach.getClass(), flujoCasoCollectionNewFlujoCasoToAttach.getCasoPersonaIdCaso());
                attachedFlujoCasoCollectionNew.add(flujoCasoCollectionNewFlujoCasoToAttach);
            }
            flujoCasoCollectionNew = attachedFlujoCasoCollectionNew;
            usuario.setFlujoCasoCollection(flujoCasoCollectionNew);
            Collection<CambioCaso> attachedCambioCasoCollectionNew = new ArrayList<CambioCaso>();
            for (CambioCaso cambioCasoCollectionNewCambioCasoToAttach : cambioCasoCollectionNew) {
                cambioCasoCollectionNewCambioCasoToAttach = em.getReference(cambioCasoCollectionNewCambioCasoToAttach.getClass(), cambioCasoCollectionNewCambioCasoToAttach.getCasoPersonaIdCaso());
                attachedCambioCasoCollectionNew.add(cambioCasoCollectionNewCambioCasoToAttach);
            }
            cambioCasoCollectionNew = attachedCambioCasoCollectionNew;
            usuario.setCambioCasoCollection(cambioCasoCollectionNew);
            Collection<ProcesoCalificacion> attachedProcesoCalificacionCollectionNew = new ArrayList<ProcesoCalificacion>();
            for (ProcesoCalificacion procesoCalificacionCollectionNewProcesoCalificacionToAttach : procesoCalificacionCollectionNew) {
                procesoCalificacionCollectionNewProcesoCalificacionToAttach = em.getReference(procesoCalificacionCollectionNewProcesoCalificacionToAttach.getClass(), procesoCalificacionCollectionNewProcesoCalificacionToAttach.getCasoPersonaIdCaso());
                attachedProcesoCalificacionCollectionNew.add(procesoCalificacionCollectionNewProcesoCalificacionToAttach);
            }
            procesoCalificacionCollectionNew = attachedProcesoCalificacionCollectionNew;
            usuario.setProcesoCalificacionCollection(procesoCalificacionCollectionNew);
            usuario = em.merge(usuario);
            for (CasoAcciones casoAccionesCollectionNewCasoAcciones : casoAccionesCollectionNew) {
                if (!casoAccionesCollectionOld.contains(casoAccionesCollectionNewCasoAcciones)) {
                    Usuario oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones = casoAccionesCollectionNewCasoAcciones.getUsuarioCedula();
                    casoAccionesCollectionNewCasoAcciones.setUsuarioCedula(usuario);
                    casoAccionesCollectionNewCasoAcciones = em.merge(casoAccionesCollectionNewCasoAcciones);
                    if (oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones != null && !oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones.equals(usuario)) {
                        oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones.getCasoAccionesCollection().remove(casoAccionesCollectionNewCasoAcciones);
                        oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones = em.merge(oldUsuarioCedulaOfCasoAccionesCollectionNewCasoAcciones);
                    }
                }
            }
            for (ProcesoReclamacion procesoReclamacionCollectionNewProcesoReclamacion : procesoReclamacionCollectionNew) {
                if (!procesoReclamacionCollectionOld.contains(procesoReclamacionCollectionNewProcesoReclamacion)) {
                    Usuario oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion = procesoReclamacionCollectionNewProcesoReclamacion.getUsuarioCedula();
                    procesoReclamacionCollectionNewProcesoReclamacion.setUsuarioCedula(usuario);
                    procesoReclamacionCollectionNewProcesoReclamacion = em.merge(procesoReclamacionCollectionNewProcesoReclamacion);
                    if (oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion != null && !oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion.equals(usuario)) {
                        oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion.getProcesoReclamacionCollection().remove(procesoReclamacionCollectionNewProcesoReclamacion);
                        oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion = em.merge(oldUsuarioCedulaOfProcesoReclamacionCollectionNewProcesoReclamacion);
                    }
                }
            }
            for (Formacion formacionCollectionNewFormacion : formacionCollectionNew) {
                if (!formacionCollectionOld.contains(formacionCollectionNewFormacion)) {
                    Usuario oldUsuarioCedulaOfFormacionCollectionNewFormacion = formacionCollectionNewFormacion.getUsuarioCedula();
                    formacionCollectionNewFormacion.setUsuarioCedula(usuario);
                    formacionCollectionNewFormacion = em.merge(formacionCollectionNewFormacion);
                    if (oldUsuarioCedulaOfFormacionCollectionNewFormacion != null && !oldUsuarioCedulaOfFormacionCollectionNewFormacion.equals(usuario)) {
                        oldUsuarioCedulaOfFormacionCollectionNewFormacion.getFormacionCollection().remove(formacionCollectionNewFormacion);
                        oldUsuarioCedulaOfFormacionCollectionNewFormacion = em.merge(oldUsuarioCedulaOfFormacionCollectionNewFormacion);
                    }
                }
            }
            for (MedicamentosCaso medicamentosCasoCollectionNewMedicamentosCaso : medicamentosCasoCollectionNew) {
                if (!medicamentosCasoCollectionOld.contains(medicamentosCasoCollectionNewMedicamentosCaso)) {
                    Usuario oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso = medicamentosCasoCollectionNewMedicamentosCaso.getUsuarioCedula();
                    medicamentosCasoCollectionNewMedicamentosCaso.setUsuarioCedula(usuario);
                    medicamentosCasoCollectionNewMedicamentosCaso = em.merge(medicamentosCasoCollectionNewMedicamentosCaso);
                    if (oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso != null && !oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso.equals(usuario)) {
                        oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso.getMedicamentosCasoCollection().remove(medicamentosCasoCollectionNewMedicamentosCaso);
                        oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso = em.merge(oldUsuarioCedulaOfMedicamentosCasoCollectionNewMedicamentosCaso);
                    }
                }
            }
            for (Agendamiento agendamientoCollectionNewAgendamiento : agendamientoCollectionNew) {
                if (!agendamientoCollectionOld.contains(agendamientoCollectionNewAgendamiento)) {
                    Usuario oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento = agendamientoCollectionNewAgendamiento.getUsuarioCedula();
                    agendamientoCollectionNewAgendamiento.setUsuarioCedula(usuario);
                    agendamientoCollectionNewAgendamiento = em.merge(agendamientoCollectionNewAgendamiento);
                    if (oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento != null && !oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento.equals(usuario)) {
                        oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento.getAgendamientoCollection().remove(agendamientoCollectionNewAgendamiento);
                        oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento = em.merge(oldUsuarioCedulaOfAgendamientoCollectionNewAgendamiento);
                    }
                }
            }
            for (Diagnostico diagnosticoCollectionNewDiagnostico : diagnosticoCollectionNew) {
                if (!diagnosticoCollectionOld.contains(diagnosticoCollectionNewDiagnostico)) {
                    Usuario oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico = diagnosticoCollectionNewDiagnostico.getUsuarioCedula();
                    diagnosticoCollectionNewDiagnostico.setUsuarioCedula(usuario);
                    diagnosticoCollectionNewDiagnostico = em.merge(diagnosticoCollectionNewDiagnostico);
                    if (oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico != null && !oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico.equals(usuario)) {
                        oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico.getDiagnosticoCollection().remove(diagnosticoCollectionNewDiagnostico);
                        oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico = em.merge(oldUsuarioCedulaOfDiagnosticoCollectionNewDiagnostico);
                    }
                }
            }
            for (FlujoCaso flujoCasoCollectionNewFlujoCaso : flujoCasoCollectionNew) {
                if (!flujoCasoCollectionOld.contains(flujoCasoCollectionNewFlujoCaso)) {
                    Usuario oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso = flujoCasoCollectionNewFlujoCaso.getUsuarioCedula();
                    flujoCasoCollectionNewFlujoCaso.setUsuarioCedula(usuario);
                    flujoCasoCollectionNewFlujoCaso = em.merge(flujoCasoCollectionNewFlujoCaso);
                    if (oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso != null && !oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso.equals(usuario)) {
                        oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso.getFlujoCasoCollection().remove(flujoCasoCollectionNewFlujoCaso);
                        oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso = em.merge(oldUsuarioCedulaOfFlujoCasoCollectionNewFlujoCaso);
                    }
                }
            }
            for (CambioCaso cambioCasoCollectionNewCambioCaso : cambioCasoCollectionNew) {
                if (!cambioCasoCollectionOld.contains(cambioCasoCollectionNewCambioCaso)) {
                    Usuario oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso = cambioCasoCollectionNewCambioCaso.getUsuarioCedula();
                    cambioCasoCollectionNewCambioCaso.setUsuarioCedula(usuario);
                    cambioCasoCollectionNewCambioCaso = em.merge(cambioCasoCollectionNewCambioCaso);
                    if (oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso != null && !oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso.equals(usuario)) {
                        oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso.getCambioCasoCollection().remove(cambioCasoCollectionNewCambioCaso);
                        oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso = em.merge(oldUsuarioCedulaOfCambioCasoCollectionNewCambioCaso);
                    }
                }
            }
            for (ProcesoCalificacion procesoCalificacionCollectionNewProcesoCalificacion : procesoCalificacionCollectionNew) {
                if (!procesoCalificacionCollectionOld.contains(procesoCalificacionCollectionNewProcesoCalificacion)) {
                    Usuario oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion = procesoCalificacionCollectionNewProcesoCalificacion.getUsuarioCedula();
                    procesoCalificacionCollectionNewProcesoCalificacion.setUsuarioCedula(usuario);
                    procesoCalificacionCollectionNewProcesoCalificacion = em.merge(procesoCalificacionCollectionNewProcesoCalificacion);
                    if (oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion != null && !oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion.equals(usuario)) {
                        oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion.getProcesoCalificacionCollection().remove(procesoCalificacionCollectionNewProcesoCalificacion);
                        oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion = em.merge(oldUsuarioCedulaOfProcesoCalificacionCollectionNewProcesoCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCedula();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CasoAcciones> casoAccionesCollectionOrphanCheck = usuario.getCasoAccionesCollection();
            for (CasoAcciones casoAccionesCollectionOrphanCheckCasoAcciones : casoAccionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the CasoAcciones " + casoAccionesCollectionOrphanCheckCasoAcciones + " in its casoAccionesCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<ProcesoReclamacion> procesoReclamacionCollectionOrphanCheck = usuario.getProcesoReclamacionCollection();
            for (ProcesoReclamacion procesoReclamacionCollectionOrphanCheckProcesoReclamacion : procesoReclamacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the ProcesoReclamacion " + procesoReclamacionCollectionOrphanCheckProcesoReclamacion + " in its procesoReclamacionCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<Formacion> formacionCollectionOrphanCheck = usuario.getFormacionCollection();
            for (Formacion formacionCollectionOrphanCheckFormacion : formacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Formacion " + formacionCollectionOrphanCheckFormacion + " in its formacionCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<MedicamentosCaso> medicamentosCasoCollectionOrphanCheck = usuario.getMedicamentosCasoCollection();
            for (MedicamentosCaso medicamentosCasoCollectionOrphanCheckMedicamentosCaso : medicamentosCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the MedicamentosCaso " + medicamentosCasoCollectionOrphanCheckMedicamentosCaso + " in its medicamentosCasoCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<Agendamiento> agendamientoCollectionOrphanCheck = usuario.getAgendamientoCollection();
            for (Agendamiento agendamientoCollectionOrphanCheckAgendamiento : agendamientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Agendamiento " + agendamientoCollectionOrphanCheckAgendamiento + " in its agendamientoCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<Diagnostico> diagnosticoCollectionOrphanCheck = usuario.getDiagnosticoCollection();
            for (Diagnostico diagnosticoCollectionOrphanCheckDiagnostico : diagnosticoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Diagnostico " + diagnosticoCollectionOrphanCheckDiagnostico + " in its diagnosticoCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<FlujoCaso> flujoCasoCollectionOrphanCheck = usuario.getFlujoCasoCollection();
            for (FlujoCaso flujoCasoCollectionOrphanCheckFlujoCaso : flujoCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the FlujoCaso " + flujoCasoCollectionOrphanCheckFlujoCaso + " in its flujoCasoCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<CambioCaso> cambioCasoCollectionOrphanCheck = usuario.getCambioCasoCollection();
            for (CambioCaso cambioCasoCollectionOrphanCheckCambioCaso : cambioCasoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the CambioCaso " + cambioCasoCollectionOrphanCheckCambioCaso + " in its cambioCasoCollection field has a non-nullable usuarioCedula field.");
            }
            Collection<ProcesoCalificacion> procesoCalificacionCollectionOrphanCheck = usuario.getProcesoCalificacionCollection();
            for (ProcesoCalificacion procesoCalificacionCollectionOrphanCheckProcesoCalificacion : procesoCalificacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the ProcesoCalificacion " + procesoCalificacionCollectionOrphanCheckProcesoCalificacion + " in its procesoCalificacionCollection field has a non-nullable usuarioCedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public Usuario buscarUsuario(String cedula, String Clave) {
        EntityManager em = getEntityManager();
        Usuario usuario = null;
        try {
            List<Usuario> listado = findUsuarioEntities();
            for (Usuario user : listado) {
                if (cedula.equals(user.getCedula())  && Clave.equals(user.getClave())) {
                    usuario = new Usuario();
                    usuario = user;

                }
            }
            return usuario;
        } finally {
            em.close();
        }
    }
    
}

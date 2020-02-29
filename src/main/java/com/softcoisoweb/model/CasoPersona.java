/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "caso_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoPersona.findAll", query = "SELECT c FROM CasoPersona c")
    , @NamedQuery(name = "CasoPersona.findByIdCaso", query = "SELECT c FROM CasoPersona c WHERE c.idCaso = :idCaso")
    , @NamedQuery(name = "CasoPersona.findByDescripcionCaso", query = "SELECT c FROM CasoPersona c WHERE c.descripcionCaso = :descripcionCaso")
    , @NamedQuery(name = "CasoPersona.findByFechaAfectacion", query = "SELECT c FROM CasoPersona c WHERE c.fechaAfectacion = :fechaAfectacion")
    , @NamedQuery(name = "CasoPersona.findByParteEfectada", query = "SELECT c FROM CasoPersona c WHERE c.parteEfectada = :parteEfectada")
    , @NamedQuery(name = "CasoPersona.findByCreadoPor", query = "SELECT c FROM CasoPersona c WHERE c.creadoPor = :creadoPor")
    , @NamedQuery(name = "CasoPersona.findByAsignado", query = "SELECT c FROM CasoPersona c WHERE c.asignado = :asignado")})
public class CasoPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_caso")
    private String idCaso;
    @Size(max = 250)
    @Column(name = "descripcion_caso")
    private String descripcionCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fecha_afectacion")
    private String fechaAfectacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "parte_efectada")
    private String parteEfectada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "asignado")
    private String asignado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private CasoAcciones casoAcciones;
    @JoinColumn(name = "persona_cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Persona personaCedula;
    @JoinColumn(name = "tipo_caso_codigo_tipo_caso", referencedColumnName = "codigo_tipo_caso")
    @ManyToOne(optional = false)
    private TipoCaso tipoCasoCodigoTipoCaso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private ProcesoReclamacion procesoReclamacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private MedicamentosCaso medicamentosCaso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private Agendamiento agendamiento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private Diagnostico diagnostico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private FlujoCaso flujoCaso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private CambioCaso cambioCaso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "casoPersona")
    private ProcesoCalificacion procesoCalificacion;

    public CasoPersona() {
    }

    public CasoPersona(String idCaso) {
        this.idCaso = idCaso;
    }

    public CasoPersona(String idCaso, String fechaAfectacion, String parteEfectada, String creadoPor, String asignado) {
        this.idCaso = idCaso;
        this.fechaAfectacion = fechaAfectacion;
        this.parteEfectada = parteEfectada;
        this.creadoPor = creadoPor;
        this.asignado = asignado;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    public String getFechaAfectacion() {
        return fechaAfectacion;
    }

    public void setFechaAfectacion(String fechaAfectacion) {
        this.fechaAfectacion = fechaAfectacion;
    }

    public String getParteEfectada() {
        return parteEfectada;
    }

    public void setParteEfectada(String parteEfectada) {
        this.parteEfectada = parteEfectada;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public CasoAcciones getCasoAcciones() {
        return casoAcciones;
    }

    public void setCasoAcciones(CasoAcciones casoAcciones) {
        this.casoAcciones = casoAcciones;
    }

    public Persona getPersonaCedula() {
        return personaCedula;
    }

    public void setPersonaCedula(Persona personaCedula) {
        this.personaCedula = personaCedula;
    }

    public TipoCaso getTipoCasoCodigoTipoCaso() {
        return tipoCasoCodigoTipoCaso;
    }

    public void setTipoCasoCodigoTipoCaso(TipoCaso tipoCasoCodigoTipoCaso) {
        this.tipoCasoCodigoTipoCaso = tipoCasoCodigoTipoCaso;
    }

    public ProcesoReclamacion getProcesoReclamacion() {
        return procesoReclamacion;
    }

    public void setProcesoReclamacion(ProcesoReclamacion procesoReclamacion) {
        this.procesoReclamacion = procesoReclamacion;
    }

    public MedicamentosCaso getMedicamentosCaso() {
        return medicamentosCaso;
    }

    public void setMedicamentosCaso(MedicamentosCaso medicamentosCaso) {
        this.medicamentosCaso = medicamentosCaso;
    }

    public Agendamiento getAgendamiento() {
        return agendamiento;
    }

    public void setAgendamiento(Agendamiento agendamiento) {
        this.agendamiento = agendamiento;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public FlujoCaso getFlujoCaso() {
        return flujoCaso;
    }

    public void setFlujoCaso(FlujoCaso flujoCaso) {
        this.flujoCaso = flujoCaso;
    }

    public CambioCaso getCambioCaso() {
        return cambioCaso;
    }

    public void setCambioCaso(CambioCaso cambioCaso) {
        this.cambioCaso = cambioCaso;
    }

    public ProcesoCalificacion getProcesoCalificacion() {
        return procesoCalificacion;
    }

    public void setProcesoCalificacion(ProcesoCalificacion procesoCalificacion) {
        this.procesoCalificacion = procesoCalificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaso != null ? idCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoPersona)) {
            return false;
        }
        CasoPersona other = (CasoPersona) object;
        if ((this.idCaso == null && other.idCaso != null) || (this.idCaso != null && !this.idCaso.equals(other.idCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CasoPersona[ idCaso=" + idCaso + " ]";
    }
    
}

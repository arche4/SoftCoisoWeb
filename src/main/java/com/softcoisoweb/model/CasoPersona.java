/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "caso_persona")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "CasoPersona.findAll", query = "SELECT c FROM CasoPersona c"),
    @NamedQuery(name = "CasoPersona.findByIdCaso", query = "SELECT c FROM CasoPersona c WHERE c.idCaso = :idCaso"),
    @NamedQuery(name = "CasoPersona.findByFechaAfectacion", query = "SELECT c FROM CasoPersona c WHERE c.fechaAfectacion = :fechaAfectacion"),
    @NamedQuery(name = "CasoPersona.findByParteEfectada", query = "SELECT c FROM CasoPersona c WHERE c.parteEfectada = :parteEfectada"),
    @NamedQuery(name = "CasoPersona.findByTimepoIncapacidad", query = "SELECT c FROM CasoPersona c WHERE c.timepoIncapacidad = :timepoIncapacidad"),
    @NamedQuery(name = "CasoPersona.findByCreadoPor", query = "SELECT c FROM CasoPersona c WHERE c.creadoPor = :creadoPor"),
    @NamedQuery(name = "CasoPersona.findByAsignado", query = "SELECT c FROM CasoPersona c WHERE c.asignado = :asignado"),
    @NamedQuery(name = "CasoPersona.findByPersonaCedula", query = "SELECT c FROM CasoPersona c WHERE c.personaCedula = :personaCedula"),
    @NamedQuery(name = "CasoPersona.findByTipoCasoCodigoTipoCaso", query = "SELECT c FROM CasoPersona c WHERE c.tipoCasoCodigoTipoCaso = :tipoCasoCodigoTipoCaso"),
    @NamedQuery(name = "CasoPersona.findByDescripcionCaso", query = "SELECT c FROM CasoPersona c WHERE c.descripcionCaso = :descripcionCaso")})
public class CasoPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_caso")
    private String idCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fecha_afectacion")
    private String fechaAfectacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "parte_efectada")
    private String parteEfectada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "timepo_incapacidad")
    private String timepoIncapacidad;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "persona_cedula")
    private String personaCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_caso_codigo_tipo_caso")
    private String tipoCasoCodigoTipoCaso;
    @Size(max = 250)
    @Column(name = "descripcion_caso")
    private String descripcionCaso;

    public CasoPersona() {
    }

    public CasoPersona(String idCaso) {
        this.idCaso = idCaso;
    }

    public CasoPersona(String idCaso, String fechaAfectacion, String parteEfectada, String timepoIncapacidad, 
            String creadoPor, String asignado, String personaCedula, String tipoCasoCodigoTipoCaso, String descripcionCaso) {
        this.idCaso = idCaso;
        this.fechaAfectacion = fechaAfectacion;
        this.parteEfectada = parteEfectada;
        this.timepoIncapacidad = timepoIncapacidad;
        this.creadoPor = creadoPor;
        this.asignado = asignado;
        this.personaCedula = personaCedula;
        this.tipoCasoCodigoTipoCaso = tipoCasoCodigoTipoCaso;
        this.descripcionCaso = descripcionCaso;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
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

    public String getTimepoIncapacidad() {
        return timepoIncapacidad;
    }

    public void setTimepoIncapacidad(String timepoIncapacidad) {
        this.timepoIncapacidad = timepoIncapacidad;
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

    public String getPersonaCedula() {
        return personaCedula;
    }

    public void setPersonaCedula(String personaCedula) {
        this.personaCedula = personaCedula;
    }

    public String getTipoCasoCodigoTipoCaso() {
        return tipoCasoCodigoTipoCaso;
    }

    public void setTipoCasoCodigoTipoCaso(String tipoCasoCodigoTipoCaso) {
        this.tipoCasoCodigoTipoCaso = tipoCasoCodigoTipoCaso;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "flujo_caso")
@NamedQueries({
    @NamedQuery(name = "FlujoCaso.findAll", query = "SELECT f FROM FlujoCaso f"),
    @NamedQuery(name = "FlujoCaso.findByFechaCreacion", query = "SELECT f FROM FlujoCaso f WHERE f.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "FlujoCaso.findByFechaActualizacion", query = "SELECT f FROM FlujoCaso f WHERE f.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "FlujoCaso.findByCasoPersonaIdCaso", query = "SELECT f FROM FlujoCaso f WHERE f.casoPersonaIdCaso = :casoPersonaIdCaso"),
    @NamedQuery(name = "FlujoCaso.findByUsuarioCedula", query = "SELECT f FROM FlujoCaso f WHERE f.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "FlujoCaso.findByEstadoCasoCodigoEstado", query = "SELECT f FROM FlujoCaso f WHERE f.estadoCasoCodigoEstado = :estadoCasoCodigoEstado")})
public class FlujoCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado_caso_codigo_estado")
    private String estadoCasoCodigoEstado;

    public FlujoCaso() {
    }

    public FlujoCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public FlujoCaso(String casoPersonaIdCaso, String fechaCreacion, String fechaActualizacion, String usuarioCedula, String estadoCasoCodigoEstado) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioCedula = usuarioCedula;
        this.estadoCasoCodigoEstado = estadoCasoCodigoEstado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getCasoPersonaIdCaso() {
        return casoPersonaIdCaso;
    }

    public void setCasoPersonaIdCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getEstadoCasoCodigoEstado() {
        return estadoCasoCodigoEstado;
    }

    public void setEstadoCasoCodigoEstado(String estadoCasoCodigoEstado) {
        this.estadoCasoCodigoEstado = estadoCasoCodigoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (casoPersonaIdCaso != null ? casoPersonaIdCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlujoCaso)) {
            return false;
        }
        FlujoCaso other = (FlujoCaso) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.FlujoCaso[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

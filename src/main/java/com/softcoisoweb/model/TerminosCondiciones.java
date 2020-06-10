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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "terminos_condiciones")
@NamedQueries({
    @NamedQuery(name = "TerminosCondiciones.findAll", query = "SELECT t FROM TerminosCondiciones t"),
    @NamedQuery(name = "TerminosCondiciones.findByIdTerminosCondiciones", query = "SELECT t FROM TerminosCondiciones t WHERE t.idTerminosCondiciones = :idTerminosCondiciones"),
    @NamedQuery(name = "TerminosCondiciones.findByNombrePersona", query = "SELECT t FROM TerminosCondiciones t WHERE t.nombrePersona = :nombrePersona"),
    @NamedQuery(name = "TerminosCondiciones.findByCedulaPersona", query = "SELECT t FROM TerminosCondiciones t WHERE t.cedulaPersona = :cedulaPersona"),
    @NamedQuery(name = "TerminosCondiciones.findByNombreArchivo", query = "SELECT t FROM TerminosCondiciones t WHERE t.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "TerminosCondiciones.findByRutaArchivo", query = "SELECT t FROM TerminosCondiciones t WHERE t.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "TerminosCondiciones.findByFechaCreacion", query = "SELECT t FROM TerminosCondiciones t WHERE t.fechaCreacion = :fechaCreacion")})
public class TerminosCondiciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_terminos_condiciones")
    private Integer idTerminosCondiciones;
    @Size(max = 45)
    @Column(name = "nombre_persona")
    private String nombrePersona;
    @Size(max = 45)
    @Column(name = "cedula_persona")
    private String cedulaPersona;
    @Size(max = 45)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 45)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    @Size(max = 45)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    public TerminosCondiciones() {
    }

    public TerminosCondiciones(Integer idTerminosCondiciones) {
        this.idTerminosCondiciones = idTerminosCondiciones;
    }

    public Integer getIdTerminosCondiciones() {
        return idTerminosCondiciones;
    }

    public void setIdTerminosCondiciones(Integer idTerminosCondiciones) {
        this.idTerminosCondiciones = idTerminosCondiciones;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTerminosCondiciones != null ? idTerminosCondiciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerminosCondiciones)) {
            return false;
        }
        TerminosCondiciones other = (TerminosCondiciones) object;
        if ((this.idTerminosCondiciones == null && other.idTerminosCondiciones != null) || (this.idTerminosCondiciones != null && !this.idTerminosCondiciones.equals(other.idTerminosCondiciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.TerminosCondiciones[ idTerminosCondiciones=" + idTerminosCondiciones + " ]";
    }
    
}

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "caso_archivo")
@NamedQueries({
    @NamedQuery(name = "CasoArchivo.findAll", query = "SELECT c FROM CasoArchivo c"),
    @NamedQuery(name = "CasoArchivo.findByCodigo", query = "SELECT c FROM CasoArchivo c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CasoArchivo.findByCodigoCaso", query = "SELECT c FROM CasoArchivo c WHERE c.codigoCaso = :codigoCaso"),
    @NamedQuery(name = "CasoArchivo.findByUsuarioCedula", query = "SELECT c FROM CasoArchivo c WHERE c.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "CasoArchivo.findByUsuarioNombre", query = "SELECT c FROM CasoArchivo c WHERE c.usuarioNombre = :usuarioNombre"),
    @NamedQuery(name = "CasoArchivo.findByArchivoNombre", query = "SELECT c FROM CasoArchivo c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CasoArchivo.findByArchivoRuta", query = "SELECT c FROM CasoArchivo c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CasoArchivo.findByFechaActualizacion", query = "SELECT c FROM CasoArchivo c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CasoArchivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo_caso")
    private String codigoCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_nombre")
    private String usuarioNombre;
    @Size(max = 250)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Size(max = 250)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public CasoArchivo() {
    }

    public CasoArchivo(Integer codigo) {
        this.codigo = codigo;
    }

    public CasoArchivo(Integer codigo, String codigoCaso, String usuarioCedula, String usuarioNombre, String fechaActualizacion) {
        this.codigo = codigo;
        this.codigoCaso = codigoCaso;
        this.usuarioCedula = usuarioCedula;
        this.usuarioNombre = usuarioNombre;
        this.fechaActualizacion = fechaActualizacion;
    }

    public CasoArchivo(String codigoCaso, String usuarioCedula, String usuarioNombre, String archivoNombre, String archivoRuta, String fechaActualizacion) {
        this.codigoCaso = codigoCaso;
        this.usuarioCedula = usuarioCedula;
        this.usuarioNombre = usuarioNombre;
        this.archivoNombre = archivoNombre;
        this.archivoRuta = archivoRuta;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoArchivo)) {
            return false;
        }
        CasoArchivo other = (CasoArchivo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CasoArchivo[ codigo=" + codigo + " ]";
    }
    
}

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
@Table(name = "seguimiento_caso")
@NamedQueries({
    @NamedQuery(name = "SeguimientoCaso.findAll", query = "SELECT s FROM SeguimientoCaso s"),
    @NamedQuery(name = "SeguimientoCaso.findByCodigo", query = "SELECT s FROM SeguimientoCaso s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SeguimientoCaso.findByCodigoCaso", query = "SELECT s FROM SeguimientoCaso s WHERE s.codigoCaso = :codigoCaso"),
    @NamedQuery(name = "SeguimientoCaso.findByAccion", query = "SELECT s FROM SeguimientoCaso s WHERE s.accion = :accion"),
    @NamedQuery(name = "SeguimientoCaso.findByUsuario", query = "SELECT s FROM SeguimientoCaso s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "SeguimientoCaso.findByNombreUsuario", query = "SELECT s FROM SeguimientoCaso s WHERE s.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "SeguimientoCaso.findByFechaActualizacion", query = "SELECT s FROM SeguimientoCaso s WHERE s.fechaActualizacion = :fechaActualizacion")})
public class SeguimientoCaso implements Serializable {

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
    @Size(max = 45)
    @Column(name = "accion")
    private String accion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public SeguimientoCaso() {
    }

    public SeguimientoCaso(Integer codigo) {
        this.codigo = codigo;
    }

    public SeguimientoCaso(Integer codigo, String codigoCaso, String usuario, String fechaActualizacion) {
        this.codigo = codigo;
        this.codigoCaso = codigoCaso;
        this.usuario = usuario;
        this.fechaActualizacion = fechaActualizacion;
    }

    public SeguimientoCaso(String codigoCaso, String accion, String usuario, String nombreUsuario, String fechaActualizacion) {
        this.codigoCaso = codigoCaso;
        this.accion = accion;
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        if (!(object instanceof SeguimientoCaso)) {
            return false;
        }
        SeguimientoCaso other = (SeguimientoCaso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.SeguimientoCaso[ codigo=" + codigo + " ]";
    }
    
}

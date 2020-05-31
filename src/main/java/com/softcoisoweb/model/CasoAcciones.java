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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "caso_acciones")
@NamedQueries({
    @NamedQuery(name = "CasoAcciones.findAll", query = "SELECT c FROM CasoAcciones c"),
    @NamedQuery(name = "CasoAcciones.findByCodigo", query = "SELECT c FROM CasoAcciones c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CasoAcciones.findByCodigoCaso", query = "SELECT c FROM CasoAcciones c WHERE c.codigoCaso = :codigoCaso"),
    @NamedQuery(name = "CasoAcciones.findByComentario", query = "SELECT c FROM CasoAcciones c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "CasoAcciones.findByUsuario", query = "SELECT c FROM CasoAcciones c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "CasoAcciones.findByFechaActualizacion", query = "SELECT c FROM CasoAcciones c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CasoAcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 20)
    @Column(name = "codigo_caso")
    private String codigoCaso;
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 15)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 15)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;
    @Lob
    @Column(name = "archivos")
    private byte[] archivos;

    public CasoAcciones() {
    }

    public CasoAcciones(byte[] archivo) {
        this.archivos = archivo;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public byte[] getArchivos() {
        return archivos;
    }

    public void setArchivos(byte[] archivos) {
        this.archivos = archivos;
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
        if (!(object instanceof CasoAcciones)) {
            return false;
        }
        CasoAcciones other = (CasoAcciones) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CasoAcciones[ codigo=" + codigo + " ]";
    }
    
}

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
@Table(name = "caso_comentario")
@NamedQueries({
    @NamedQuery(name = "CasoComentario.findAll", query = "SELECT c FROM CasoComentario c"),
    @NamedQuery(name = "CasoComentario.findByCodigo", query = "SELECT c FROM CasoComentario c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CasoComentario.findByCodigoCaso", query = "SELECT c FROM CasoComentario c WHERE c.codigoCaso = :codigoCaso"),
    @NamedQuery(name = "CasoComentario.findByComentario", query = "SELECT c FROM CasoComentario c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "CasoComentario.findByUsuarioCedula", query = "SELECT c FROM CasoComentario c WHERE c.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "CasoComentario.findByUsuarioNombre", query = "SELECT c FROM CasoComentario c WHERE c.usuarioNombre = :usuarioNombre"),
    @NamedQuery(name = "CasoComentario.findByFechaActualizacion", query = "SELECT c FROM CasoComentario c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CasoComentario implements Serializable {

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
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public CasoComentario() {
    }

    public CasoComentario(Integer codigo) {
        this.codigo = codigo;
    }

    public CasoComentario(Integer codigo, String codigoCaso, String usuarioCedula, String usuarioNombre, String fechaActualizacion) {
        this.codigo = codigo;
        this.codigoCaso = codigoCaso;
        this.usuarioCedula = usuarioCedula;
        this.usuarioNombre = usuarioNombre;
        this.fechaActualizacion = fechaActualizacion;
    }

    public CasoComentario(String codigoCaso, String comentario, String usuarioCedula, String usuarioNombre, String fechaActualizacion) {
        this.codigoCaso = codigoCaso;
        this.comentario = comentario;
        this.usuarioCedula = usuarioCedula;
        this.usuarioNombre = usuarioNombre;
        this.fechaActualizacion = fechaActualizacion;
    }

    public CasoComentario(Integer codigo, String codigoCaso, String comentario, String usuarioCedula, String usuarioNombre, String fechaActualizacion) {
        this.codigo = codigo;
        this.codigoCaso = codigoCaso;
        this.comentario = comentario;
        this.usuarioCedula = usuarioCedula;
        this.usuarioNombre = usuarioNombre;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        if (!(object instanceof CasoComentario)) {
            return false;
        }
        CasoComentario other = (CasoComentario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CasoComentario[ codigo=" + codigo + " ]";
    }
    
}

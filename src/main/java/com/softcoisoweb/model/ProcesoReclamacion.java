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
@Table(name = "proceso_reclamacion")
@NamedQueries({
    @NamedQuery(name = "ProcesoReclamacion.findAll", query = "SELECT p FROM ProcesoReclamacion p"),
    @NamedQuery(name = "ProcesoReclamacion.findByCodigo", query = "SELECT p FROM ProcesoReclamacion p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcesoReclamacion.findByComentarios", query = "SELECT p FROM ProcesoReclamacion p WHERE p.comentarios = :comentarios"),
    @NamedQuery(name = "ProcesoReclamacion.findByArchivos", query = "SELECT p FROM ProcesoReclamacion p WHERE p.archivos = :archivos"),
    @NamedQuery(name = "ProcesoReclamacion.findByCasoPersonaIdCaso", query = "SELECT p FROM ProcesoReclamacion p WHERE p.casoPersonaIdCaso = :casoPersonaIdCaso"),
    @NamedQuery(name = "ProcesoReclamacion.findByUsuarioCedula", query = "SELECT p FROM ProcesoReclamacion p WHERE p.usuarioCedula = :usuarioCedula")})
public class ProcesoReclamacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 4000)
    @Column(name = "comentarios")
    private String comentarios;
    @Size(max = 100)
    @Column(name = "archivos")
    private String archivos;
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

    public ProcesoReclamacion() {
    }

    public ProcesoReclamacion(Integer codigo) {
        this.codigo = codigo;
    }

    public ProcesoReclamacion(Integer codigo, String casoPersonaIdCaso, String usuarioCedula) {
        this.codigo = codigo;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.usuarioCedula = usuarioCedula;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getArchivos() {
        return archivos;
    }

    public void setArchivos(String archivos) {
        this.archivos = archivos;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoReclamacion)) {
            return false;
        }
        ProcesoReclamacion other = (ProcesoReclamacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.ProcesoReclamacion[ codigo=" + codigo + " ]";
    }
    
}
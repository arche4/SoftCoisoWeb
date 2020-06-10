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
@Table(name = "proceso_calificacion")
@NamedQueries({
    @NamedQuery(name = "ProcesoCalificacion.findAll", query = "SELECT p FROM ProcesoCalificacion p"),
    @NamedQuery(name = "ProcesoCalificacion.findByCodigo", query = "SELECT p FROM ProcesoCalificacion p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcesoCalificacion.findByProceso", query = "SELECT p FROM ProcesoCalificacion p WHERE p.proceso = :proceso"),
    @NamedQuery(name = "ProcesoCalificacion.findByComentario", query = "SELECT p FROM ProcesoCalificacion p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "ProcesoCalificacion.findByArchivo", query = "SELECT p FROM ProcesoCalificacion p WHERE p.archivo = :archivo"),
    @NamedQuery(name = "ProcesoCalificacion.findByUsuarioCedula", query = "SELECT p FROM ProcesoCalificacion p WHERE p.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "ProcesoCalificacion.findByCasoPersonaIdCaso", query = "SELECT p FROM ProcesoCalificacion p WHERE p.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class ProcesoCalificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 400)
    @Column(name = "proceso")
    private String proceso;
    @Size(max = 400)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 250)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;

    public ProcesoCalificacion() {
    }

    public ProcesoCalificacion(Integer codigo) {
        this.codigo = codigo;
    }

    public ProcesoCalificacion(Integer codigo, String usuarioCedula, String casoPersonaIdCaso) {
        this.codigo = codigo;
        this.usuarioCedula = usuarioCedula;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getCasoPersonaIdCaso() {
        return casoPersonaIdCaso;
    }

    public void setCasoPersonaIdCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
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
        if (!(object instanceof ProcesoCalificacion)) {
            return false;
        }
        ProcesoCalificacion other = (ProcesoCalificacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.ProcesoCalificacion[ codigo=" + codigo + " ]";
    }
    
}

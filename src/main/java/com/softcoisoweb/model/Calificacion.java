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
@Table(name = "calificacion")
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c"),
    @NamedQuery(name = "Calificacion.findByCodigo", query = "SELECT c FROM Calificacion c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Calificacion.findByDiagnostico", query = "SELECT c FROM Calificacion c WHERE c.diagnostico = :diagnostico"),
    @NamedQuery(name = "Calificacion.findByPorcentaje", query = "SELECT c FROM Calificacion c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "Calificacion.findByComentario", query = "SELECT c FROM Calificacion c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "Calificacion.findByArchivos", query = "SELECT c FROM Calificacion c WHERE c.archivos = :archivos"),
    @NamedQuery(name = "Calificacion.findByUsuarioCedula", query = "SELECT c FROM Calificacion c WHERE c.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "Calificacion.findByCasoPersonaIdCaso", query = "SELECT c FROM Calificacion c WHERE c.casoPersonaIdCaso = :casoPersonaIdCaso"),
    @NamedQuery(name = "Calificacion.findByCodigoProceso", query = "SELECT c FROM Calificacion c WHERE c.codigoProceso = :codigoProceso")})
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 4000)
    @Column(name = "Diagnostico")
    private String diagnostico;
    @Size(max = 50)
    @Column(name = "porcentaje")
    private String porcentaje;
    @Size(max = 4000)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 100)
    @Column(name = "archivos")
    private String archivos;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "codigo_proceso")
    private String codigoProceso;

    public Calificacion() {
    }

    public Calificacion(Integer codigo) {
        this.codigo = codigo;
    }

    public Calificacion(Integer codigo, String usuarioCedula, String casoPersonaIdCaso, String codigoProceso) {
        this.codigo = codigo;
        this.usuarioCedula = usuarioCedula;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.codigoProceso = codigoProceso;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getArchivos() {
        return archivos;
    }

    public void setArchivos(String archivos) {
        this.archivos = archivos;
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

    public String getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
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
        if (!(object instanceof Calificacion)) {
            return false;
        }
        Calificacion other = (Calificacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Calificacion[ codigo=" + codigo + " ]";
    }
    
}

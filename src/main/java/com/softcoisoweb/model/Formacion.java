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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "formacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f")
    , @NamedQuery(name = "Formacion.findByIdFormacion", query = "SELECT f FROM Formacion f WHERE f.idFormacion = :idFormacion")
    , @NamedQuery(name = "Formacion.findByTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.tipoFormacion = :tipoFormacion")
    , @NamedQuery(name = "Formacion.findByFechaFormacion", query = "SELECT f FROM Formacion f WHERE f.fechaFormacion = :fechaFormacion")
    , @NamedQuery(name = "Formacion.findByFormador", query = "SELECT f FROM Formacion f WHERE f.formador = :formador")
    , @NamedQuery(name = "Formacion.findByTema", query = "SELECT f FROM Formacion f WHERE f.tema = :tema")
    , @NamedQuery(name = "Formacion.findByArchivo", query = "SELECT f FROM Formacion f WHERE f.archivo = :archivo")
    , @NamedQuery(name = "Formacion.findByCorreoFormador", query = "SELECT f FROM Formacion f WHERE f.correoFormador = :correoFormador")
    , @NamedQuery(name = "Formacion.findByNumeroAsistente", query = "SELECT f FROM Formacion f WHERE f.numeroAsistente = :numeroAsistente")})
public class Formacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_formacion")
    private Integer idFormacion;
    @Size(max = 15)
    @Column(name = "tipo_formacion")
    private String tipoFormacion;
    @Size(max = 15)
    @Column(name = "fecha_formacion")
    private String fechaFormacion;
    @Size(max = 20)
    @Column(name = "formador")
    private String formador;
    @Size(max = 20)
    @Column(name = "tema")
    private String tema;
    @Size(max = 30)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 20)
    @Column(name = "correo_formador")
    private String correoFormador;
    @Size(max = 4000)
    @Column(name = "numero_asistente")
    private String numeroAsistente;
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Usuario usuarioCedula;

    public Formacion() {
    }

    public Formacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Integer getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getTipoFormacion() {
        return tipoFormacion;
    }

    public void setTipoFormacion(String tipoFormacion) {
        this.tipoFormacion = tipoFormacion;
    }

    public String getFechaFormacion() {
        return fechaFormacion;
    }

    public void setFechaFormacion(String fechaFormacion) {
        this.fechaFormacion = fechaFormacion;
    }

    public String getFormador() {
        return formador;
    }

    public void setFormador(String formador) {
        this.formador = formador;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getCorreoFormador() {
        return correoFormador;
    }

    public void setCorreoFormador(String correoFormador) {
        this.correoFormador = correoFormador;
    }

    public String getNumeroAsistente() {
        return numeroAsistente;
    }

    public void setNumeroAsistente(String numeroAsistente) {
        this.numeroAsistente = numeroAsistente;
    }

    public Usuario getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(Usuario usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacion != null ? idFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.idFormacion == null && other.idFormacion != null) || (this.idFormacion != null && !this.idFormacion.equals(other.idFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Formacion[ idFormacion=" + idFormacion + " ]";
    }
    
}

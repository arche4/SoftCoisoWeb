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
@Table(name = "diagnostico")
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d"),
    @NamedQuery(name = "Diagnostico.findByCodigoDiagnostico", query = "SELECT d FROM Diagnostico d WHERE d.codigoDiagnostico = :codigoDiagnostico"),
    @NamedQuery(name = "Diagnostico.findByDiagnostico", query = "SELECT d FROM Diagnostico d WHERE d.diagnostico = :diagnostico"),
    @NamedQuery(name = "Diagnostico.findByFechaDiagnostico", query = "SELECT d FROM Diagnostico d WHERE d.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "Diagnostico.findByComentario", query = "SELECT d FROM Diagnostico d WHERE d.comentario = :comentario"),
    @NamedQuery(name = "Diagnostico.findByIdCaso", query = "SELECT d FROM Diagnostico d WHERE d.idCaso = :idCaso"),
    @NamedQuery(name = "Diagnostico.findByUsuarioCedula", query = "SELECT d FROM Diagnostico d WHERE d.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "Diagnostico.findByNombreUsuario", query = "SELECT d FROM Diagnostico d WHERE d.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Diagnostico.findByNombreArchivo", query = "SELECT d FROM Diagnostico d WHERE d.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "Diagnostico.findByRutaArchivo", query = "SELECT d FROM Diagnostico d WHERE d.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "Diagnostico.findByFechaCreacion", query = "SELECT d FROM Diagnostico d WHERE d.fechaCreacion = :fechaCreacion")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_diagnostico")
    private Integer codigoDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha_diagnostico")
    private String fechaDiagnostico;
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_caso")
    private String idCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Size(max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Size(max = 45)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 250)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    @Size(max = 45)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    public Diagnostico() {
    }

    public Diagnostico(Integer codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public Diagnostico(Integer codigoDiagnostico, String diagnostico, String fechaDiagnostico, String idCaso, String usuarioCedula) {
        this.codigoDiagnostico = codigoDiagnostico;
        this.diagnostico = diagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.idCaso = idCaso;
        this.usuarioCedula = usuarioCedula;
    }

    public Diagnostico(Integer codigoDiagnostico, String diagnostico, String fechaDiagnostico, String comentario, String idCaso, String usuarioCedula, String nombreUsuario, String nombreArchivo, String rutaArchivo, String fechaCreacion) {
        this.codigoDiagnostico = codigoDiagnostico;
        this.diagnostico = diagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.comentario = comentario;
        this.idCaso = idCaso;
        this.usuarioCedula = usuarioCedula;
        this.nombreUsuario = nombreUsuario;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.fechaCreacion = fechaCreacion;
    }

    public Diagnostico(String diagnostico, String fechaDiagnostico, String comentario, String idCaso, String usuarioCedula, String nombreUsuario, String nombreArchivo, String rutaArchivo, String fechaCreacion) {
        this.diagnostico = diagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.comentario = comentario;
        this.idCaso = idCaso;
        this.usuarioCedula = usuarioCedula;
        this.nombreUsuario = nombreUsuario;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.fechaCreacion = fechaCreacion;
    }

    
    public Integer getCodigoDiagnostico() {
        return codigoDiagnostico;
    }

    public void setCodigoDiagnostico(Integer codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(String fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(String idCaso) {
        this.idCaso = idCaso;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        hash += (codigoDiagnostico != null ? codigoDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        Diagnostico other = (Diagnostico) object;
        if ((this.codigoDiagnostico == null && other.codigoDiagnostico != null) || (this.codigoDiagnostico != null && !this.codigoDiagnostico.equals(other.codigoDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Diagnostico[ codigoDiagnostico=" + codigoDiagnostico + " ]";
    }
    
}

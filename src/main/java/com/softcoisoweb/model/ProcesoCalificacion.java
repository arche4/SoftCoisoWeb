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
    @NamedQuery(name = "ProcesoCalificacion.findByNombreArchivo", query = "SELECT p FROM ProcesoCalificacion p WHERE p.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "ProcesoCalificacion.findByRutaArchivo", query = "SELECT p FROM ProcesoCalificacion p WHERE p.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "ProcesoCalificacion.findByUsuarioCedula", query = "SELECT p FROM ProcesoCalificacion p WHERE p.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "ProcesoCalificacion.findByNombreUsuario", query = "SELECT p FROM ProcesoCalificacion p WHERE p.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "ProcesoCalificacion.findByCasoPersonaIdCaso", query = "SELECT p FROM ProcesoCalificacion p WHERE p.casoPersonaIdCaso = :casoPersonaIdCaso"),
    @NamedQuery(name = "ProcesoCalificacion.findByFechaCreacion", query = "SELECT p FROM ProcesoCalificacion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ProcesoCalificacion.findByFechaActualizada", query = "SELECT p FROM ProcesoCalificacion p WHERE p.fechaActualizada = :fechaActualizada")})
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
    @Size(max = 50)
    @Column(name = "nombreArchivo")
    private String nombreArchivo;
    @Size(max = 250)
    @Column(name = "rutaArchivo")
    private String rutaArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Size(max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;
    @Size(max = 45)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Size(max = 45)
    @Column(name = "fecha_actualizada")
    private String fechaActualizada;

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

    public ProcesoCalificacion(Integer codigo, String proceso, String comentario, String nombreArchivo, String rutaArchivo, String usuarioCedula, String nombreUsuario, String casoPersonaIdCaso, String fechaCreacion, String fechaActualizada) {
        this.codigo = codigo;
        this.proceso = proceso;
        this.comentario = comentario;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.usuarioCedula = usuarioCedula;
        this.nombreUsuario = nombreUsuario;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizada = fechaActualizada;
    }

    public ProcesoCalificacion(String proceso, String comentario, String nombreArchivo, String rutaArchivo, String usuarioCedula, String nombreUsuario, String casoPersonaIdCaso, String fechaCreacion, String fechaActualizada) {
        this.proceso = proceso;
        this.comentario = comentario;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.usuarioCedula = usuarioCedula;
        this.nombreUsuario = nombreUsuario;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizada = fechaActualizada;
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

    public String getCasoPersonaIdCaso() {
        return casoPersonaIdCaso;
    }

    public void setCasoPersonaIdCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizada() {
        return fechaActualizada;
    }

    public void setFechaActualizada(String fechaActualizada) {
        this.fechaActualizada = fechaActualizada;
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

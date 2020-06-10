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
@Table(name = "formacion")
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f"),
    @NamedQuery(name = "Formacion.findByIdFormacion", query = "SELECT f FROM Formacion f WHERE f.idFormacion = :idFormacion"),
    @NamedQuery(name = "Formacion.findByTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.tipoFormacion = :tipoFormacion"),
    @NamedQuery(name = "Formacion.findByAno", query = "SELECT f FROM Formacion f WHERE f.ano = :ano"),
    @NamedQuery(name = "Formacion.findByMes", query = "SELECT f FROM Formacion f WHERE f.mes = :mes"),
    @NamedQuery(name = "Formacion.findByDia", query = "SELECT f FROM Formacion f WHERE f.dia = :dia"),
    @NamedQuery(name = "Formacion.findByHoraInicio", query = "SELECT f FROM Formacion f WHERE f.horaInicio = :horaInicio"),
    @NamedQuery(name = "Formacion.findByHoraFin", query = "SELECT f FROM Formacion f WHERE f.horaFin = :horaFin"),
    @NamedQuery(name = "Formacion.findByTitulo", query = "SELECT f FROM Formacion f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Formacion.findByDescripcion", query = "SELECT f FROM Formacion f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "Formacion.findByNombreFormador", query = "SELECT f FROM Formacion f WHERE f.nombreFormador = :nombreFormador"),
    @NamedQuery(name = "Formacion.findByTema", query = "SELECT f FROM Formacion f WHERE f.tema = :tema"),
    @NamedQuery(name = "Formacion.findByArchivo", query = "SELECT f FROM Formacion f WHERE f.archivo = :archivo"),
    @NamedQuery(name = "Formacion.findByCorreoFormador", query = "SELECT f FROM Formacion f WHERE f.correoFormador = :correoFormador"),
    @NamedQuery(name = "Formacion.findByNumeroAsistente", query = "SELECT f FROM Formacion f WHERE f.numeroAsistente = :numeroAsistente"),
    @NamedQuery(name = "Formacion.findByUsuarioCedula", query = "SELECT f FROM Formacion f WHERE f.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "Formacion.findByEstado", query = "SELECT f FROM Formacion f WHERE f.estado = :estado")})
public class Formacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacion")
    private Integer idFormacion;
    @Size(max = 15)
    @Column(name = "tipo_formacion")
    private String tipoFormacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    private int dia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "hora_fin")
    private String horaFin;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 70)
    @Column(name = "nombre_formador")
    private String nombreFormador;
    @Size(max = 100)
    @Column(name = "tema")
    private String tema;
    @Size(max = 250)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 50)
    @Column(name = "correo_formador")
    private String correoFormador;
    @Size(max = 10)
    @Column(name = "numero_asistente")
    private String numeroAsistente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;

    public Formacion() {
    }

    public Formacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Formacion(Integer idFormacion, int ano, int mes, int dia, String horaInicio, String horaFin, String usuarioCedula, String estado) {
        this.idFormacion = idFormacion;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.usuarioCedula = usuarioCedula;
        this.estado = estado;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreFormador() {
        return nombreFormador;
    }

    public void setNombreFormador(String nombreFormador) {
        this.nombreFormador = nombreFormador;
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

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

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
@Table(name = "actividades")
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a"),
    @NamedQuery(name = "Actividades.findByIdActividad", query = "SELECT a FROM Actividades a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "Actividades.findByActividad", query = "SELECT a FROM Actividades a WHERE a.actividad = :actividad"),
    @NamedQuery(name = "Actividades.findByAno", query = "SELECT a FROM Actividades a WHERE a.ano = :ano"),
    @NamedQuery(name = "Actividades.findByMes", query = "SELECT a FROM Actividades a WHERE a.mes = :mes"),
    @NamedQuery(name = "Actividades.findByDia", query = "SELECT a FROM Actividades a WHERE a.dia = :dia"),
    @NamedQuery(name = "Actividades.findByHoraInicio", query = "SELECT a FROM Actividades a WHERE a.horaInicio = :horaInicio"),
    @NamedQuery(name = "Actividades.findByHoraFin", query = "SELECT a FROM Actividades a WHERE a.horaFin = :horaFin"),
    @NamedQuery(name = "Actividades.findByTitulo", query = "SELECT a FROM Actividades a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "Actividades.findByDescripcion", query = "SELECT a FROM Actividades a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Actividades.findByInvitados", query = "SELECT a FROM Actividades a WHERE a.invitados = :invitados"),
    @NamedQuery(name = "Actividades.findByUsuarioCedula", query = "SELECT a FROM Actividades a WHERE a.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "Actividades.findByEstado", query = "SELECT a FROM Actividades a WHERE a.estado = :estado")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Integer idActividad;
    @Size(max = 30)
    @Column(name = "actividad")
    private String actividad;
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
    @Column(name = "invitados")
    private String invitados;
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

    public Actividades() {
    }

    public Actividades(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividades(Integer idActividad, int ano, int mes, int dia, String horaInicio, String horaFin, String usuarioCedula, String estado) {
        this.idActividad = idActividad;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.usuarioCedula = usuarioCedula;
        this.estado = estado;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
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

    public String getInvitados() {
        return invitados;
    }

    public void setInvitados(String invitados) {
        this.invitados = invitados;
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
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Actividades[ idActividad=" + idActividad + " ]";
    }
    
}

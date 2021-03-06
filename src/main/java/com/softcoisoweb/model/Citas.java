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
@Table(name = "citas")
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByCodigoCita", query = "SELECT c FROM Citas c WHERE c.codigoCita = :codigoCita")
    , @NamedQuery(name = "Citas.findByAno", query = "SELECT c FROM Citas c WHERE c.ano = :ano")
    , @NamedQuery(name = "Citas.findByMes", query = "SELECT c FROM Citas c WHERE c.mes = :mes")
    , @NamedQuery(name = "Citas.findByDia", query = "SELECT c FROM Citas c WHERE c.dia = :dia")
    , @NamedQuery(name = "Citas.findByHoraInicio", query = "SELECT c FROM Citas c WHERE c.horaInicio = :horaInicio")
    , @NamedQuery(name = "Citas.findByHoraFin", query = "SELECT c FROM Citas c WHERE c.horaFin = :horaFin")
    , @NamedQuery(name = "Citas.findByTitulo", query = "SELECT c FROM Citas c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Citas.findByDescripcion", query = "SELECT c FROM Citas c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Citas.findByCodigoCasoPersona", query = "SELECT c FROM Citas c WHERE c.codigoCasoPersona = :codigoCasoPersona")
    , @NamedQuery(name = "Citas.findByCorreoPersona", query = "SELECT c FROM Citas c WHERE c.correoPersona = :correoPersona")
    , @NamedQuery(name = "Citas.findByNombrepersona", query = "SELECT c FROM Citas c WHERE c.nombrepersona = :nombrepersona")
    , @NamedQuery(name = "Citas.findByCorreoUsuario", query = "SELECT c FROM Citas c WHERE c.correoUsuario = :correoUsuario")
    , @NamedQuery(name = "Citas.findByUsuario", query = "SELECT c FROM Citas c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "Citas.findByEstado", query = "SELECT c FROM Citas c WHERE c.estado = :estado")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_cita")
    private Integer codigoCita;
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
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "codigo_caso_persona")
    private String codigoCasoPersona;
    @Size(max = 50)
    @Column(name = "correo_persona")
    private String correoPersona;
    @Size(max = 50)
    @Column(name = "Nombre_persona")
    private String nombrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_usuario")
    private String correoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;

    public Citas() {
    }

    public Citas(Integer codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Citas(Integer codigoCita, int ano, int mes, int dia, String horaInicio, String horaFin, String titulo,
            String descripcion, String codigoCasoPersona,String correoPersona, String nombrepersona,
            String correoUsuario, String usuario, String estado) {
        this.codigoCita=codigoCita;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.codigoCasoPersona = codigoCasoPersona;
        this.correoPersona = correoPersona;
        this.nombrepersona = nombrepersona;
        this.correoUsuario = correoUsuario;
        this.usuario = usuario;
        this.estado = estado;
    }
    
    public Citas(int ano, int mes, int dia, String horaInicio, String horaFin, String titulo,
            String descripcion, String codigoCasoPersona,String correoPersona, String nombrepersona,
            String correoUsuario, String usuario, String estado) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.codigoCasoPersona = codigoCasoPersona;
        this.correoPersona = correoPersona;
        this.nombrepersona = nombrepersona;
        this.correoUsuario = correoUsuario;
        this.usuario = usuario;
        this.estado = estado;
    }

    public Integer getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(Integer codigoCita) {
        this.codigoCita = codigoCita;
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

    public String getCodigoCasoPersona() {
        return codigoCasoPersona;
    }

    public void setCodigoCasoPersona(String codigoCasoPersona) {
        this.codigoCasoPersona = codigoCasoPersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        hash += (codigoCita != null ? codigoCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.codigoCita == null && other.codigoCita != null) || (this.codigoCita != null && !this.codigoCita.equals(other.codigoCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Citas[ codigoCita=" + codigoCita + " ]";
    }
    
}

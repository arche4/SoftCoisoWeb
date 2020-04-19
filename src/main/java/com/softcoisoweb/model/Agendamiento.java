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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "agendamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agendamiento.findAll", query = "SELECT a FROM Agendamiento a")
    , @NamedQuery(name = "Agendamiento.findByFechaCita", query = "SELECT a FROM Agendamiento a WHERE a.fechaCita = :fechaCita")
    , @NamedQuery(name = "Agendamiento.findByHora", query = "SELECT a FROM Agendamiento a WHERE a.hora = :hora")
    , @NamedQuery(name = "Agendamiento.findByDescripcion", query = "SELECT a FROM Agendamiento a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Agendamiento.findByTitulo", query = "SELECT a FROM Agendamiento a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "Agendamiento.findByCasoPersonaIdCaso", query = "SELECT a FROM Agendamiento a WHERE a.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class Agendamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha_cita")
    private String fechaCita;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "hora")
    private String hora;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;
    @JoinColumn(name = "caso_persona_id_caso", referencedColumnName = "id_caso", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CasoPersona casoPersona;
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Usuario usuarioCedula;

    public Agendamiento() {
    }

    public Agendamiento(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public Agendamiento(String casoPersonaIdCaso, String fechaCita, String hora) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaCita = fechaCita;
        this.hora = hora;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCasoPersonaIdCaso() {
        return casoPersonaIdCaso;
    }

    public void setCasoPersonaIdCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public CasoPersona getCasoPersona() {
        return casoPersona;
    }

    public void setCasoPersona(CasoPersona casoPersona) {
        this.casoPersona = casoPersona;
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
        hash += (casoPersonaIdCaso != null ? casoPersonaIdCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agendamiento)) {
            return false;
        }
        Agendamiento other = (Agendamiento) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Agendamiento[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

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
    @NamedQuery(name = "Diagnostico.findByFecha", query = "SELECT d FROM Diagnostico d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Diagnostico.findByIdCaso", query = "SELECT d FROM Diagnostico d WHERE d.idCaso = :idCaso"),
    @NamedQuery(name = "Diagnostico.findByUsuarioCedula", query = "SELECT d FROM Diagnostico d WHERE d.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "Diagnostico.findByArchivos", query = "SELECT d FROM Diagnostico d WHERE d.archivos = :archivos")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_diagnostico")
    private Integer codigoDiagnostico;
    @Size(max = 250)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha")
    private String fecha;
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
    @Size(max = 250)
    @Column(name = "archivos")
    private String archivos;

    public Diagnostico() {
    }

    public Diagnostico(Integer codigoDiagnostico) {
        this.codigoDiagnostico = codigoDiagnostico;
    }

    public Diagnostico(Integer codigoDiagnostico, String fecha, String idCaso, String usuarioCedula) {
        this.codigoDiagnostico = codigoDiagnostico;
        this.fecha = fecha;
        this.idCaso = idCaso;
        this.usuarioCedula = usuarioCedula;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getArchivos() {
        return archivos;
    }

    public void setArchivos(String archivos) {
        this.archivos = archivos;
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

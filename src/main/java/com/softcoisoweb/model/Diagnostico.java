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
@Table(name = "diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnostico.findAll", query = "SELECT d FROM Diagnostico d")
    , @NamedQuery(name = "Diagnostico.findByDiagnostico", query = "SELECT d FROM Diagnostico d WHERE d.diagnostico = :diagnostico")
    , @NamedQuery(name = "Diagnostico.findByFechaActualizada", query = "SELECT d FROM Diagnostico d WHERE d.fechaActualizada = :fechaActualizada")
    , @NamedQuery(name = "Diagnostico.findByCasoPersonaIdCaso", query = "SELECT d FROM Diagnostico d WHERE d.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 4000)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fecha_actualizada")
    private String fechaActualizada;
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

    public Diagnostico() {
    }

    public Diagnostico(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public Diagnostico(String casoPersonaIdCaso, String fechaActualizada) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaActualizada = fechaActualizada;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFechaActualizada() {
        return fechaActualizada;
    }

    public void setFechaActualizada(String fechaActualizada) {
        this.fechaActualizada = fechaActualizada;
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
        if (!(object instanceof Diagnostico)) {
            return false;
        }
        Diagnostico other = (Diagnostico) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Diagnostico[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

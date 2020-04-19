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
@Table(name = "proceso_reclamacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoReclamacion.findAll", query = "SELECT p FROM ProcesoReclamacion p")
    , @NamedQuery(name = "ProcesoReclamacion.findByComentarios", query = "SELECT p FROM ProcesoReclamacion p WHERE p.comentarios = :comentarios")
    , @NamedQuery(name = "ProcesoReclamacion.findByArchivo", query = "SELECT p FROM ProcesoReclamacion p WHERE p.archivo = :archivo")
    , @NamedQuery(name = "ProcesoReclamacion.findByCasoPersonaIdCaso", query = "SELECT p FROM ProcesoReclamacion p WHERE p.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class ProcesoReclamacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 4000)
    @Column(name = "comentarios")
    private String comentarios;
    @Size(max = 100)
    @Column(name = "archivo")
    private String archivo;
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

    public ProcesoReclamacion() {
    }

    public ProcesoReclamacion(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
        if (!(object instanceof ProcesoReclamacion)) {
            return false;
        }
        ProcesoReclamacion other = (ProcesoReclamacion) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.ProcesoReclamacion[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

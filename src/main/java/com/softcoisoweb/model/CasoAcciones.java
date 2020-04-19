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
@Table(name = "caso_acciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoAcciones.findAll", query = "SELECT c FROM CasoAcciones c")
    , @NamedQuery(name = "CasoAcciones.findByComentario", query = "SELECT c FROM CasoAcciones c WHERE c.comentario = :comentario")
    , @NamedQuery(name = "CasoAcciones.findByArchivo", query = "SELECT c FROM CasoAcciones c WHERE c.archivo = :archivo")
    , @NamedQuery(name = "CasoAcciones.findByFechaActualizada", query = "SELECT c FROM CasoAcciones c WHERE c.fechaActualizada = :fechaActualizada")
    , @NamedQuery(name = "CasoAcciones.findByCasoPersonaIdCaso", query = "SELECT c FROM CasoAcciones c WHERE c.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class CasoAcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 4000)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 200)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 10)
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

    public CasoAcciones() {
    }

    public CasoAcciones(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
        if (!(object instanceof CasoAcciones)) {
            return false;
        }
        CasoAcciones other = (CasoAcciones) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CasoAcciones[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

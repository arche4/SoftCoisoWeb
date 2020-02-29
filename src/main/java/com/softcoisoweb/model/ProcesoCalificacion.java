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
@Table(name = "proceso_calificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoCalificacion.findAll", query = "SELECT p FROM ProcesoCalificacion p")
    , @NamedQuery(name = "ProcesoCalificacion.findByDiagnostico", query = "SELECT p FROM ProcesoCalificacion p WHERE p.diagnostico = :diagnostico")
    , @NamedQuery(name = "ProcesoCalificacion.findByPorcentaje", query = "SELECT p FROM ProcesoCalificacion p WHERE p.porcentaje = :porcentaje")
    , @NamedQuery(name = "ProcesoCalificacion.findByComentario", query = "SELECT p FROM ProcesoCalificacion p WHERE p.comentario = :comentario")
    , @NamedQuery(name = "ProcesoCalificacion.findByArchivo", query = "SELECT p FROM ProcesoCalificacion p WHERE p.archivo = :archivo")
    , @NamedQuery(name = "ProcesoCalificacion.findByCasoPersonaIdCaso", query = "SELECT p FROM ProcesoCalificacion p WHERE p.casoPersonaIdCaso = :casoPersonaIdCaso")})
public class ProcesoCalificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 4000)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Size(max = 50)
    @Column(name = "porcentaje")
    private String porcentaje;
    @Size(max = 4000)
    @Column(name = "comentario")
    private String comentario;
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

    public ProcesoCalificacion() {
    }

    public ProcesoCalificacion(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
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
        if (!(object instanceof ProcesoCalificacion)) {
            return false;
        }
        ProcesoCalificacion other = (ProcesoCalificacion) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.ProcesoCalificacion[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

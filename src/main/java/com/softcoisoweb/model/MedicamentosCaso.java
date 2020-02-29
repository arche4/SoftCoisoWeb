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
@Table(name = "medicamentos_caso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentosCaso.findAll", query = "SELECT m FROM MedicamentosCaso m")
    , @NamedQuery(name = "MedicamentosCaso.findByFechaMedicamento", query = "SELECT m FROM MedicamentosCaso m WHERE m.fechaMedicamento = :fechaMedicamento")
    , @NamedQuery(name = "MedicamentosCaso.findByCasoPersonaIdCaso", query = "SELECT m FROM MedicamentosCaso m WHERE m.casoPersonaIdCaso = :casoPersonaIdCaso")
    , @NamedQuery(name = "MedicamentosCaso.findByComentario", query = "SELECT m FROM MedicamentosCaso m WHERE m.comentario = :comentario")})
public class MedicamentosCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_medicamento")
    private String fechaMedicamento;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;
    @Size(max = 4000)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "caso_persona_id_caso", referencedColumnName = "id_caso", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CasoPersona casoPersona;
    @JoinColumn(name = "medicamentos_codigo_medicamento", referencedColumnName = "codigo_medicamento")
    @ManyToOne(optional = false)
    private Medicamentos medicamentosCodigoMedicamento;
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Usuario usuarioCedula;

    public MedicamentosCaso() {
    }

    public MedicamentosCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public MedicamentosCaso(String casoPersonaIdCaso, String fechaMedicamento) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.fechaMedicamento = fechaMedicamento;
    }

    public String getFechaMedicamento() {
        return fechaMedicamento;
    }

    public void setFechaMedicamento(String fechaMedicamento) {
        this.fechaMedicamento = fechaMedicamento;
    }

    public String getCasoPersonaIdCaso() {
        return casoPersonaIdCaso;
    }

    public void setCasoPersonaIdCaso(String casoPersonaIdCaso) {
        this.casoPersonaIdCaso = casoPersonaIdCaso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CasoPersona getCasoPersona() {
        return casoPersona;
    }

    public void setCasoPersona(CasoPersona casoPersona) {
        this.casoPersona = casoPersona;
    }

    public Medicamentos getMedicamentosCodigoMedicamento() {
        return medicamentosCodigoMedicamento;
    }

    public void setMedicamentosCodigoMedicamento(Medicamentos medicamentosCodigoMedicamento) {
        this.medicamentosCodigoMedicamento = medicamentosCodigoMedicamento;
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
        if (!(object instanceof MedicamentosCaso)) {
            return false;
        }
        MedicamentosCaso other = (MedicamentosCaso) object;
        if ((this.casoPersonaIdCaso == null && other.casoPersonaIdCaso != null) || (this.casoPersonaIdCaso != null && !this.casoPersonaIdCaso.equals(other.casoPersonaIdCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.MedicamentosCaso[ casoPersonaIdCaso=" + casoPersonaIdCaso + " ]";
    }
    
}

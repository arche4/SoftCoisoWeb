/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Table(name = "medicamentos_caso")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "MedicamentosCaso.findAll", query = "SELECT m FROM MedicamentosCaso m"),
    @NamedQuery(name = "MedicamentosCaso.findByCodigoMedicamento", query = "SELECT m FROM MedicamentosCaso m WHERE m.codigoMedicamento = :codigoMedicamento"),
    @NamedQuery(name = "MedicamentosCaso.findByFechaMedicamento", query = "SELECT m FROM MedicamentosCaso m WHERE m.fechaMedicamento = :fechaMedicamento"),
    @NamedQuery(name = "MedicamentosCaso.findByCasoPersonaIdCaso", query = "SELECT m FROM MedicamentosCaso m WHERE m.casoPersonaIdCaso = :casoPersonaIdCaso"),
    @NamedQuery(name = "MedicamentosCaso.findByMedicamentosCodigoMedicamento", query = "SELECT m FROM MedicamentosCaso m WHERE m.medicamentosCodigoMedicamento = :medicamentosCodigoMedicamento"),
    @NamedQuery(name = "MedicamentosCaso.findByUsuarioCedula", query = "SELECT m FROM MedicamentosCaso m WHERE m.usuarioCedula = :usuarioCedula"),
    @NamedQuery(name = "MedicamentosCaso.findByComentario", query = "SELECT m FROM MedicamentosCaso m WHERE m.comentario = :comentario"),
    @NamedQuery(name = "MedicamentosCaso.findByArchivos", query = "SELECT m FROM MedicamentosCaso m WHERE m.archivos = :archivos"),
    @NamedQuery(name = "MedicamentosCaso.findByDosificacion", query = "SELECT m FROM MedicamentosCaso m WHERE m.dosificacion = :dosificacion"),
    @NamedQuery(name = "MedicamentosCaso.findByCantidad", query = "SELECT m FROM MedicamentosCaso m WHERE m.cantidad = :cantidad")})
public class MedicamentosCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_medicamento")
    private Integer codigoMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_medicamento")
    private String fechaMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "caso_persona_id_caso")
    private String casoPersonaIdCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "medicamentos_codigo_medicamento")
    private String medicamentosCodigoMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 500)
    @Column(name = "archivos")
    private String archivos;
    @Size(max = 100)
    @Column(name = "dosificacion")
    private String dosificacion;
    @Size(max = 100)
    @Column(name = "cantidad")
    private String cantidad;

    public MedicamentosCaso() {
    }

    public MedicamentosCaso(Integer codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public MedicamentosCaso(Integer codigoMedicamento, String fechaMedicamento, String casoPersonaIdCaso, String medicamentosCodigoMedicamento, String usuarioCedula) {
        this.codigoMedicamento = codigoMedicamento;
        this.fechaMedicamento = fechaMedicamento;
        this.casoPersonaIdCaso = casoPersonaIdCaso;
        this.medicamentosCodigoMedicamento = medicamentosCodigoMedicamento;
        this.usuarioCedula = usuarioCedula;
    }

    public Integer getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(Integer codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
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

    public String getMedicamentosCodigoMedicamento() {
        return medicamentosCodigoMedicamento;
    }

    public void setMedicamentosCodigoMedicamento(String medicamentosCodigoMedicamento) {
        this.medicamentosCodigoMedicamento = medicamentosCodigoMedicamento;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getArchivos() {
        return archivos;
    }

    public void setArchivos(String archivos) {
        this.archivos = archivos;
    }

    public String getDosificacion() {
        return dosificacion;
    }

    public void setDosificacion(String dosificacion) {
        this.dosificacion = dosificacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMedicamento != null ? codigoMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentosCaso)) {
            return false;
        }
        MedicamentosCaso other = (MedicamentosCaso) object;
        if ((this.codigoMedicamento == null && other.codigoMedicamento != null) || (this.codigoMedicamento != null && !this.codigoMedicamento.equals(other.codigoMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.MedicamentosCaso[ codigoMedicamento=" + codigoMedicamento + " ]";
    }
    
}
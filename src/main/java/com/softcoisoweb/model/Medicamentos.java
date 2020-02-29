/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamentos.findAll", query = "SELECT m FROM Medicamentos m")
    , @NamedQuery(name = "Medicamentos.findByCodigoMedicamento", query = "SELECT m FROM Medicamentos m WHERE m.codigoMedicamento = :codigoMedicamento")
    , @NamedQuery(name = "Medicamentos.findByNombreMedicamento", query = "SELECT m FROM Medicamentos m WHERE m.nombreMedicamento = :nombreMedicamento")
    , @NamedQuery(name = "Medicamentos.findByDescripcionMedicamento", query = "SELECT m FROM Medicamentos m WHERE m.descripcionMedicamento = :descripcionMedicamento")})
public class Medicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_medicamento")
    private String codigoMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_medicamento")
    private String nombreMedicamento;
    @Size(max = 100)
    @Column(name = "descripcion_medicamento")
    private String descripcionMedicamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamentosCodigoMedicamento")
    private Collection<MedicamentosCaso> medicamentosCasoCollection;

    public Medicamentos() {
    }

    public Medicamentos(String codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public Medicamentos(String codigoMedicamento, String nombreMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(String codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    @XmlTransient
    public Collection<MedicamentosCaso> getMedicamentosCasoCollection() {
        return medicamentosCasoCollection;
    }

    public void setMedicamentosCasoCollection(Collection<MedicamentosCaso> medicamentosCasoCollection) {
        this.medicamentosCasoCollection = medicamentosCasoCollection;
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
        if (!(object instanceof Medicamentos)) {
            return false;
        }
        Medicamentos other = (Medicamentos) object;
        if ((this.codigoMedicamento == null && other.codigoMedicamento != null) || (this.codigoMedicamento != null && !this.codigoMedicamento.equals(other.codigoMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Medicamentos[ codigoMedicamento=" + codigoMedicamento + " ]";
    }
    
}

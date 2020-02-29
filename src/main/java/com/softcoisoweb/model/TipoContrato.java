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
@Table(name = "tipo_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t")
    , @NamedQuery(name = "TipoContrato.findByCodigoTipoContrato", query = "SELECT t FROM TipoContrato t WHERE t.codigoTipoContrato = :codigoTipoContrato")
    , @NamedQuery(name = "TipoContrato.findByNombre", query = "SELECT t FROM TipoContrato t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoContrato.findByDescripcion", query = "SELECT t FROM TipoContrato t WHERE t.descripcion = :descripcion")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_tipo_contrato")
    private String codigoTipoContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoContratoCodigoTipoContrato")
    private Collection<Persona> personaCollection;

    public TipoContrato() {
    }

    public TipoContrato(String codigoTipoContrato) {
        this.codigoTipoContrato = codigoTipoContrato;
    }

    public TipoContrato(String codigoTipoContrato, String nombre) {
        this.codigoTipoContrato = codigoTipoContrato;
        this.nombre = nombre;
    }

    public String getCodigoTipoContrato() {
        return codigoTipoContrato;
    }

    public void setCodigoTipoContrato(String codigoTipoContrato) {
        this.codigoTipoContrato = codigoTipoContrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoContrato != null ? codigoTipoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.codigoTipoContrato == null && other.codigoTipoContrato != null) || (this.codigoTipoContrato != null && !this.codigoTipoContrato.equals(other.codigoTipoContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.TipoContrato[ codigoTipoContrato=" + codigoTipoContrato + " ]";
    }
    
}

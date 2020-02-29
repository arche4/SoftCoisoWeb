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
@Table(name = "persona_direccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaDireccion.findAll", query = "SELECT p FROM PersonaDireccion p")
    , @NamedQuery(name = "PersonaDireccion.findByMunicipio", query = "SELECT p FROM PersonaDireccion p WHERE p.municipio = :municipio")
    , @NamedQuery(name = "PersonaDireccion.findByBarrio", query = "SELECT p FROM PersonaDireccion p WHERE p.barrio = :barrio")
    , @NamedQuery(name = "PersonaDireccion.findByDireccion", query = "SELECT p FROM PersonaDireccion p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "PersonaDireccion.findByPersonaCedula", query = "SELECT p FROM PersonaDireccion p WHERE p.personaCedula = :personaCedula")})
public class PersonaDireccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "municipio")
    private String municipio;
    @Size(max = 20)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 20)
    @Column(name = "direccion")
    private String direccion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "persona_cedula")
    private String personaCedula;
    @JoinColumn(name = "persona_cedula", referencedColumnName = "cedula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public PersonaDireccion() {
    }

    public PersonaDireccion(String personaCedula) {
        this.personaCedula = personaCedula;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaCedula() {
        return personaCedula;
    }

    public void setPersonaCedula(String personaCedula) {
        this.personaCedula = personaCedula;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaCedula != null ? personaCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaDireccion)) {
            return false;
        }
        PersonaDireccion other = (PersonaDireccion) object;
        if ((this.personaCedula == null && other.personaCedula != null) || (this.personaCedula != null && !this.personaCedula.equals(other.personaCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.PersonaDireccion[ personaCedula=" + personaCedula + " ]";
    }
    
}

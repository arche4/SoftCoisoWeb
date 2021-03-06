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
@Table(name = "organizacion_sindical")
@NamedQueries({
    @NamedQuery(name = "OrganizacionSindical.findAll", query = "SELECT o FROM OrganizacionSindical o"),
    @NamedQuery(name = "OrganizacionSindical.findByCodigoOrganizacion", query = "SELECT o FROM OrganizacionSindical o WHERE o.codigoOrganizacion = :codigoOrganizacion"),
    @NamedQuery(name = "OrganizacionSindical.findByNombre", query = "SELECT o FROM OrganizacionSindical o WHERE o.nombre = :nombre")})
public class OrganizacionSindical implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_organizacion")
    private String codigoOrganizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nombre")
    private String nombre;

    public OrganizacionSindical() {
    }

    public OrganizacionSindical(String codigoOrganizacion) {
        this.codigoOrganizacion = codigoOrganizacion;
    }

    public OrganizacionSindical(String codigoOrganizacion, String nombre) {
        this.codigoOrganizacion = codigoOrganizacion;
        this.nombre = nombre;
    }

    public String getCodigoOrganizacion() {
        return codigoOrganizacion;
    }

    public void setCodigoOrganizacion(String codigoOrganizacion) {
        this.codigoOrganizacion = codigoOrganizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOrganizacion != null ? codigoOrganizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizacionSindical)) {
            return false;
        }
        OrganizacionSindical other = (OrganizacionSindical) object;
        if ((this.codigoOrganizacion == null && other.codigoOrganizacion != null) || (this.codigoOrganizacion != null && !this.codigoOrganizacion.equals(other.codigoOrganizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.OrganizacionSindical[ codigoOrganizacion=" + codigoOrganizacion + " ]";
    }
    
}

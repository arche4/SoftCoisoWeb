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
@Table(name = "arl")
@NamedQueries({
    @NamedQuery(name = "Arl.findAll", query = "SELECT a FROM Arl a"),
    @NamedQuery(name = "Arl.findByCodigoArl", query = "SELECT a FROM Arl a WHERE a.codigoArl = :codigoArl"),
    @NamedQuery(name = "Arl.findByNombreArl", query = "SELECT a FROM Arl a WHERE a.nombreArl = :nombreArl")})
public class Arl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_arl")
    private String codigoArl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_arl")
    private String nombreArl;

    public Arl() {
    }

    public Arl(String codigoArl) {
        this.codigoArl = codigoArl;
    }

    public Arl(String codigoArl, String nombreArl) {
        this.codigoArl = codigoArl;
        this.nombreArl = nombreArl;
    }

    public String getCodigoArl() {
        return codigoArl;
    }

    public void setCodigoArl(String codigoArl) {
        this.codigoArl = codigoArl;
    }

    public String getNombreArl() {
        return nombreArl;
    }

    public void setNombreArl(String nombreArl) {
        this.nombreArl = nombreArl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoArl != null ? codigoArl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arl)) {
            return false;
        }
        Arl other = (Arl) object;
        if ((this.codigoArl == null && other.codigoArl != null) || (this.codigoArl != null && !this.codigoArl.equals(other.codigoArl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Arl[ codigoArl=" + codigoArl + " ]";
    }
    
}

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
@Table(name = "afp")
@NamedQueries({
    @NamedQuery(name = "Afp.findAll", query = "SELECT a FROM Afp a"),
    @NamedQuery(name = "Afp.findByCodigoAfp", query = "SELECT a FROM Afp a WHERE a.codigoAfp = :codigoAfp"),
    @NamedQuery(name = "Afp.findByNombreAfp", query = "SELECT a FROM Afp a WHERE a.nombreAfp = :nombreAfp")})
public class Afp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_afp")
    private String codigoAfp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_afp")
    private String nombreAfp;

    public Afp() {
    }

    public Afp(String codigoAfp) {
        this.codigoAfp = codigoAfp;
    }

    public Afp(String codigoAfp, String nombreAfp) {
        this.codigoAfp = codigoAfp;
        this.nombreAfp = nombreAfp;
    }

    public String getCodigoAfp() {
        return codigoAfp;
    }

    public void setCodigoAfp(String codigoAfp) {
        this.codigoAfp = codigoAfp;
    }

    public String getNombreAfp() {
        return nombreAfp;
    }

    public void setNombreAfp(String nombreAfp) {
        this.nombreAfp = nombreAfp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAfp != null ? codigoAfp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afp)) {
            return false;
        }
        Afp other = (Afp) object;
        if ((this.codigoAfp == null && other.codigoAfp != null) || (this.codigoAfp != null && !this.codigoAfp.equals(other.codigoAfp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Afp[ codigoAfp=" + codigoAfp + " ]";
    }
    
}

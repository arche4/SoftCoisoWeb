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
@Table(name = "estado_caso")
@NamedQueries({
    @NamedQuery(name = "EstadoCaso.findAll", query = "SELECT e FROM EstadoCaso e"),
    @NamedQuery(name = "EstadoCaso.findByCodigoEstado", query = "SELECT e FROM EstadoCaso e WHERE e.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "EstadoCaso.findByNombreEstado", query = "SELECT e FROM EstadoCaso e WHERE e.nombreEstado = :nombreEstado"),
    @NamedQuery(name = "EstadoCaso.findByDescripcion", query = "SELECT e FROM EstadoCaso e WHERE e.descripcion = :descripcion")})
public class EstadoCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_estado")
    private String codigoEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;

    public EstadoCaso() {
    }

    public EstadoCaso(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public EstadoCaso(String codigoEstado, String nombreEstado, String descripcion) {
        this.codigoEstado = codigoEstado;
        this.nombreEstado = nombreEstado;
        this.descripcion = descripcion;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEstado != null ? codigoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCaso)) {
            return false;
        }
        EstadoCaso other = (EstadoCaso) object;
        if ((this.codigoEstado == null && other.codigoEstado != null) || (this.codigoEstado != null && !this.codigoEstado.equals(other.codigoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.EstadoCaso[ codigoEstado=" + codigoEstado + " ]";
    }
    
}

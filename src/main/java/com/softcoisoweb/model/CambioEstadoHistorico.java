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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "cambio_estado_historico")
@NamedQueries({
    @NamedQuery(name = "CambioEstadoHistorico.findAll", query = "SELECT c FROM CambioEstadoHistorico c"),
    @NamedQuery(name = "CambioEstadoHistorico.findByIdCambioEstado", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.idCambioEstado = :idCambioEstado"),
    @NamedQuery(name = "CambioEstadoHistorico.findByCasoid", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.casoid = :casoid"),
    @NamedQuery(name = "CambioEstadoHistorico.findByCedulaUsuario", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.cedulaUsuario = :cedulaUsuario"),
    @NamedQuery(name = "CambioEstadoHistorico.findByNombreUsuario", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "CambioEstadoHistorico.findByComentario", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.comentario = :comentario"),
    @NamedQuery(name = "CambioEstadoHistorico.findByEstadoCaso", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.estadoCaso = :estadoCaso"),
    @NamedQuery(name = "CambioEstadoHistorico.findByFechaCambio", query = "SELECT c FROM CambioEstadoHistorico c WHERE c.fechaCambio = :fechaCambio")})
public class CambioEstadoHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cambio_estado")
    private Integer idCambioEstado;
    @Size(max = 25)
    @Column(name = "casoid")
    private String casoid;
    @Size(max = 15)
    @Column(name = "cedula_usuario")
    private String cedulaUsuario;
    @Size(max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Size(max = 500)
    @Column(name = "comentario")
    private String comentario;
    @Size(max = 45)
    @Column(name = "estado_caso")
    private String estadoCaso;
    @Size(max = 25)
    @Column(name = "fecha_cambio")
    private String fechaCambio;

    public CambioEstadoHistorico() {
    }

    public CambioEstadoHistorico(Integer idCambioEstado) {
        this.idCambioEstado = idCambioEstado;
    }

    public Integer getIdCambioEstado() {
        return idCambioEstado;
    }

    public void setIdCambioEstado(Integer idCambioEstado) {
        this.idCambioEstado = idCambioEstado;
    }

    public String getCasoid() {
        return casoid;
    }

    public void setCasoid(String casoid) {
        this.casoid = casoid;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCambioEstado != null ? idCambioEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambioEstadoHistorico)) {
            return false;
        }
        CambioEstadoHistorico other = (CambioEstadoHistorico) object;
        if ((this.idCambioEstado == null && other.idCambioEstado != null) || (this.idCambioEstado != null && !this.idCambioEstado.equals(other.idCambioEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.CambioEstadoHistorico[ idCambioEstado=" + idCambioEstado + " ]";
    }
    
}



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
@Table(name = "persona")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Persona.findByNombrePersona", query = "SELECT p FROM Persona p WHERE p.nombrePersona = :nombrePersona"),
    @NamedQuery(name = "Persona.findByApellidoPersona", query = "SELECT p FROM Persona p WHERE p.apellidoPersona = :apellidoPersona"),
    @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero"),
    @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Persona.findByEdad", query = "SELECT p FROM Persona p WHERE p.edad = :edad"),
    @NamedQuery(name = "Persona.findByAntiguedadEmpresa", query = "SELECT p FROM Persona p WHERE p.antiguedadEmpresa = :antiguedadEmpresa"),
    @NamedQuery(name = "Persona.findByCargo", query = "SELECT p FROM Persona p WHERE p.cargo = :cargo"),
    @NamedQuery(name = "Persona.findByFechaClinica", query = "SELECT p FROM Persona p WHERE p.fechaClinica = :fechaClinica"),
    @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo"),
    @NamedQuery(name = "Persona.findByRecomendado", query = "SELECT p FROM Persona p WHERE p.recomendado = :recomendado"),
    @NamedQuery(name = "Persona.findByCasoAsociado", query = "SELECT p FROM Persona p WHERE p.casoAsociado = :casoAsociado"),
    @NamedQuery(name = "Persona.findByAfpCodigoAfp", query = "SELECT p FROM Persona p WHERE p.afpCodigoAfp = :afpCodigoAfp"),
    @NamedQuery(name = "Persona.findByArlCodigoArl", query = "SELECT p FROM Persona p WHERE p.arlCodigoArl = :arlCodigoArl"),
    @NamedQuery(name = "Persona.findByEpsCodigoEps", query = "SELECT p FROM Persona p WHERE p.epsCodigoEps = :epsCodigoEps"),
    @NamedQuery(name = "Persona.findByTipoContratoCodigoTipoContrato", query = "SELECT p FROM Persona p WHERE p.tipoContratoCodigoTipoContrato = :tipoContratoCodigoTipoContrato"),
    @NamedQuery(name = "Persona.findByOrganizacionSindicalCodigoOrganizacion", query = "SELECT p FROM Persona p WHERE p.organizacionSindicalCodigoOrganizacion = :organizacionSindicalCodigoOrganizacion"),
    @NamedQuery(name = "Persona.findByNombreEmpresa", query = "SELECT p FROM Persona p WHERE p.nombreEmpresa = :nombreEmpresa"),
    @NamedQuery(name = "Persona.findBySectorEconomico", query = "SELECT p FROM Persona p WHERE p.sectorEconomico = :sectorEconomico"),
    @NamedQuery(name = "Persona.findByEmpresaUsuaria", query = "SELECT p FROM Persona p WHERE p.empresaUsuaria = :empresaUsuaria")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre_persona")
    private String nombrePersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido_persona")
    private String apellidoPersona;
    @Size(max = 10)
    @Column(name = "genero")
    private String genero;
    @Size(max = 30)
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Size(max = 50)
    @Column(name = "edad")
    private String edad;
    @Size(max = 10)
    @Column(name = "antiguedad_empresa")
    private String antiguedadEmpresa;
    @Size(max = 20)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 20)
    @Column(name = "fecha_clinica")
    private String fechaClinica;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "correo")
    private String correo;
    @Size(max = 30)
    @Column(name = "recomendado")
    private String recomendado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "caso_asociado")
    private String casoAsociado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "afp_codigo_afp")
    private String afpCodigoAfp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "arl_codigo_arl")
    private String arlCodigoArl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "eps_codigo_eps")
    private String epsCodigoEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_contrato_codigo_tipo_contrato")
    private String tipoContratoCodigoTipoContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "organizacion_sindical_codigo_organizacion")
    private String organizacionSindicalCodigoOrganizacion;
    @Size(max = 55)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Size(max = 55)
    @Column(name = "sector_economico")
    private String sectorEconomico;
    @Size(max = 55)
    @Column(name = "empresa_usuaria")
    private String empresaUsuaria;

    public Persona() {
    }

    public Persona(String cedula) {
        this.cedula = cedula;
    }

    public Persona(String cedula, String nombrePersona, String apellidoPersona, String casoAsociado, String afpCodigoAfp, String arlCodigoArl, String epsCodigoEps, String tipoContratoCodigoTipoContrato, String organizacionSindicalCodigoOrganizacion) {
        this.cedula = cedula;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.casoAsociado = casoAsociado;
        this.afpCodigoAfp = afpCodigoAfp;
        this.arlCodigoArl = arlCodigoArl;
        this.epsCodigoEps = epsCodigoEps;
        this.tipoContratoCodigoTipoContrato = tipoContratoCodigoTipoContrato;
        this.organizacionSindicalCodigoOrganizacion = organizacionSindicalCodigoOrganizacion;
    }

    public Persona(String cedula, String nombrePersona, String apellidoPersona, String genero, String fechaNacimiento,
            String edad, String antiguedadEmpresa, String cargo, String fechaClinica, String telefono, String correo,
            String recomendado, String casoAsociado, String afpCodigoAfp, String arlCodigoArl, String epsCodigoEps,
            String tipoContratoCodigoTipoContrato, String organizacionSindicalCodigoOrganizacion, String nombreEmpresa,
            String sectorEconomico, String empresaUsuaria) {
        this.cedula = cedula;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.antiguedadEmpresa = antiguedadEmpresa;
        this.cargo = cargo;
        this.fechaClinica = fechaClinica;
        this.telefono = telefono;
        this.correo = correo;
        this.recomendado = recomendado;
        this.casoAsociado = casoAsociado;
        this.afpCodigoAfp = afpCodigoAfp;
        this.arlCodigoArl = arlCodigoArl;
        this.epsCodigoEps = epsCodigoEps;
        this.tipoContratoCodigoTipoContrato = tipoContratoCodigoTipoContrato;
        this.organizacionSindicalCodigoOrganizacion = organizacionSindicalCodigoOrganizacion;
        this.nombreEmpresa = nombreEmpresa;
        this.sectorEconomico = sectorEconomico;
        this.empresaUsuaria = empresaUsuaria;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getAntiguedadEmpresa() {
        return antiguedadEmpresa;
    }

    public void setAntiguedadEmpresa(String antiguedadEmpresa) {
        this.antiguedadEmpresa = antiguedadEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaClinica() {
        return fechaClinica;
    }

    public void setFechaClinica(String fechaClinica) {
        this.fechaClinica = fechaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(String recomendado) {
        this.recomendado = recomendado;
    }

    public String getCasoAsociado() {
        return casoAsociado;
    }

    public void setCasoAsociado(String casoAsociado) {
        this.casoAsociado = casoAsociado;
    }

    public String getAfpCodigoAfp() {
        return afpCodigoAfp;
    }

    public void setAfpCodigoAfp(String afpCodigoAfp) {
        this.afpCodigoAfp = afpCodigoAfp;
    }

    public String getArlCodigoArl() {
        return arlCodigoArl;
    }

    public void setArlCodigoArl(String arlCodigoArl) {
        this.arlCodigoArl = arlCodigoArl;
    }

    public String getEpsCodigoEps() {
        return epsCodigoEps;
    }

    public void setEpsCodigoEps(String epsCodigoEps) {
        this.epsCodigoEps = epsCodigoEps;
    }

    public String getTipoContratoCodigoTipoContrato() {
        return tipoContratoCodigoTipoContrato;
    }

    public void setTipoContratoCodigoTipoContrato(String tipoContratoCodigoTipoContrato) {
        this.tipoContratoCodigoTipoContrato = tipoContratoCodigoTipoContrato;
    }

    public String getOrganizacionSindicalCodigoOrganizacion() {
        return organizacionSindicalCodigoOrganizacion;
    }

    public void setOrganizacionSindicalCodigoOrganizacion(String organizacionSindicalCodigoOrganizacion) {
        this.organizacionSindicalCodigoOrganizacion = organizacionSindicalCodigoOrganizacion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public String getEmpresaUsuaria() {
        return empresaUsuaria;
    }

    public void setEmpresaUsuaria(String empresaUsuaria) {
        this.empresaUsuaria = empresaUsuaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softcoisoweb.model.Persona[ cedula=" + cedula + " ]";
    }

}

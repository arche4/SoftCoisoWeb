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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Persona.findByNombrePersona", query = "SELECT p FROM Persona p WHERE p.nombrePersona = :nombrePersona")
    , @NamedQuery(name = "Persona.findByApellidoPersona", query = "SELECT p FROM Persona p WHERE p.apellidoPersona = :apellidoPersona")
    , @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero")
    , @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Persona.findByEdad", query = "SELECT p FROM Persona p WHERE p.edad = :edad")
    , @NamedQuery(name = "Persona.findByAntiguedadEmpresa", query = "SELECT p FROM Persona p WHERE p.antiguedadEmpresa = :antiguedadEmpresa")
    , @NamedQuery(name = "Persona.findByCargo", query = "SELECT p FROM Persona p WHERE p.cargo = :cargo")
    , @NamedQuery(name = "Persona.findByFechaClinica", query = "SELECT p FROM Persona p WHERE p.fechaClinica = :fechaClinica")
    , @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo")
    , @NamedQuery(name = "Persona.findByRecomendado", query = "SELECT p FROM Persona p WHERE p.recomendado = :recomendado")
    , @NamedQuery(name = "Persona.findByCasoAsociado", query = "SELECT p FROM Persona p WHERE p.casoAsociado = :casoAsociado")})
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "antiguedad_empresa")
    private String antiguedadEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fecha_clinica")
    private String fechaClinica;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "recomendado")
    private String recomendado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "caso_asociado")
    private String casoAsociado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaCedula")
    private Collection<CasoPersona> casoPersonaCollection;
    @JoinColumn(name = "afp_codigo_afp", referencedColumnName = "codigo_afp")
    @ManyToOne(optional = false)
    private Afp afpCodigoAfp;
    @JoinColumn(name = "arl_codigo_arl", referencedColumnName = "codigo_arl")
    @ManyToOne(optional = false)
    private Arl arlCodigoArl;
    @JoinColumn(name = "empresa_codigo_empresa", referencedColumnName = "codigo_empresa")
    @ManyToOne(optional = false)
    private Empresa empresaCodigoEmpresa;
    @JoinColumn(name = "eps_codigo_eps", referencedColumnName = "codigo_eps")
    @ManyToOne(optional = false)
    private Eps epsCodigoEps;
    @JoinColumn(name = "organizacion_sindical_codigo_organizacion", referencedColumnName = "codigo_organizacion")
    @ManyToOne(optional = false)
    private OrganizacionSindical organizacionSindicalCodigoOrganizacion;
    @JoinColumn(name = "tipo_contrato_codigo_tipo_contrato", referencedColumnName = "codigo_tipo_contrato")
    @ManyToOne(optional = false)
    private TipoContrato tipoContratoCodigoTipoContrato;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private PersonaDireccion personaDireccion;

    public Persona() {
    }

    public Persona(String cedula) {
        this.cedula = cedula;
    }

    public Persona(String cedula, String nombrePersona, String apellidoPersona, String antiguedadEmpresa, String cargo, String fechaClinica, String recomendado, String casoAsociado) {
        this.cedula = cedula;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.antiguedadEmpresa = antiguedadEmpresa;
        this.cargo = cargo;
        this.fechaClinica = fechaClinica;
        this.recomendado = recomendado;
        this.casoAsociado = casoAsociado;
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

    @XmlTransient
    public Collection<CasoPersona> getCasoPersonaCollection() {
        return casoPersonaCollection;
    }

    public void setCasoPersonaCollection(Collection<CasoPersona> casoPersonaCollection) {
        this.casoPersonaCollection = casoPersonaCollection;
    }

    public Afp getAfpCodigoAfp() {
        return afpCodigoAfp;
    }

    public void setAfpCodigoAfp(Afp afpCodigoAfp) {
        this.afpCodigoAfp = afpCodigoAfp;
    }

    public Arl getArlCodigoArl() {
        return arlCodigoArl;
    }

    public void setArlCodigoArl(Arl arlCodigoArl) {
        this.arlCodigoArl = arlCodigoArl;
    }

    public Empresa getEmpresaCodigoEmpresa() {
        return empresaCodigoEmpresa;
    }

    public void setEmpresaCodigoEmpresa(Empresa empresaCodigoEmpresa) {
        this.empresaCodigoEmpresa = empresaCodigoEmpresa;
    }

    public Eps getEpsCodigoEps() {
        return epsCodigoEps;
    }

    public void setEpsCodigoEps(Eps epsCodigoEps) {
        this.epsCodigoEps = epsCodigoEps;
    }

    public OrganizacionSindical getOrganizacionSindicalCodigoOrganizacion() {
        return organizacionSindicalCodigoOrganizacion;
    }

    public void setOrganizacionSindicalCodigoOrganizacion(OrganizacionSindical organizacionSindicalCodigoOrganizacion) {
        this.organizacionSindicalCodigoOrganizacion = organizacionSindicalCodigoOrganizacion;
    }

    public TipoContrato getTipoContratoCodigoTipoContrato() {
        return tipoContratoCodigoTipoContrato;
    }

    public void setTipoContratoCodigoTipoContrato(TipoContrato tipoContratoCodigoTipoContrato) {
        this.tipoContratoCodigoTipoContrato = tipoContratoCodigoTipoContrato;
    }

    public PersonaDireccion getPersonaDireccion() {
        return personaDireccion;
    }

    public void setPersonaDireccion(PersonaDireccion personaDireccion) {
        this.personaDireccion = personaDireccion;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author manue
 */
public class reportePersona {

    private Integer cedula;
    private String nombre;
    private String genero;
    private String fechaNacimiento;
    private String edad;
    private String empresa;
    private String antiguedadEmpresa;
    private String cargo;
    private String fechaClinica;
    private String telefono;
    private String correo;
    private String eps;
    private String arl;
    private String afp;
    private String contrato;
    private String organizacion;
    private String municipio;
    private String barrio;
    private String dirrecion;

    private List<String> header;

    private List<reportePersona> bodyTable;
    public static String sql = "SELECT  p.cedula, CONCAT(p.nombre_persona, ' ', p.apellido_persona)   as nombre,  "
            + "p.genero, p.fecha_nacimiento, p.edad, p.nombre_empresa,  p.antiguedad_empresa,  "
            + "p.cargo, p.fecha_clinica, p.telefono, p.correo, eps.nombre_eps as Eps, afp.nombre_afp as Afp, arl.nombre_arl as Arl, "
            + "tc.nombre as Contrato, os.nombre Organizacion_sindical, pd.municipio as municipio, pd.barrio as barrio,"
            + "pd.direccion as direccion "
            + "FROM persona p "
            + "INNER JOIN eps on p.eps_codigo_eps = eps.codigo_eps "
            + "INNER JOIN afp on afp.codigo_afp = p.afp_codigo_Afp   "
            + "INNER JOIN arl  on arl.codigo_arl = p.arl_codigo_arl "
            + "INNER JOIN tipo_contrato  tc on tc.codigo_tipo_contrato = p.tipo_contrato_codigo_tipo_contrato "
            + "INNER JOIN organizacion_sindical os on os.codigo_organizacion = p.organizacion_sindical_codigo_organizacion "
            + "INNER JOIN persona_direccion pd on pd.persona_cedula = p.cedula "
            + "where p.fecha_clinica BETWEEN ? AND ? ";

    public reportePersona() {
        initHeader();
    }

    public reportePersona(Integer cedula, String nombre, String genero, String fechaNacimiento, String edad,
            String empresa, String antiguedadEmpresa, String cargo, String fechaClinica, String telefono,
            String correo, String eps, String arl, String afp, String contrato, String organizacion,
            String municipio, String barrio, String dirrecion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.empresa = empresa;
        this.antiguedadEmpresa = antiguedadEmpresa;
        this.cargo = cargo;
        this.fechaClinica = fechaClinica;
        this.telefono = telefono;
        this.correo = correo;
        this.eps = eps;
        this.arl = arl;
        this.afp = afp;
        this.contrato = contrato;
        this.organizacion = organizacion;
        this.municipio = municipio;
        this.barrio = barrio;
        this.dirrecion = dirrecion;
        initHeader();
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
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

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<reportePersona> getBodyTable() {
        return bodyTable;
    }

    public void setBodyTable(List<reportePersona> bodyTable) {
        this.bodyTable = bodyTable;
    }

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        reportePersona.sql = sql;
    }

    public final void initHeader() {
        header = new ArrayList<>();
        header.add("Cedula");
        header.add("Nombre");
        header.add("Genero");
        header.add("Fecha_nacimiento");
        header.add("Edad");
        header.add("Empresa");
        header.add("Antiguedad Empresa");
        header.add("Cargo");
        header.add("Fecha clinica");
        header.add("Telefono");
        header.add("Coreo");
        header.add("Eps");
        header.add("Afp");
        header.add("Arl");
        header.add("Contrato");
        header.add("Grupo Sindicato");
        header.add("Municipio");
        header.add("Barrio");
        header.add("Dirrecion");
    }

    public String getTableHtml() {
        String uniqueID = UUID.randomUUID().toString();
        StringBuilder htmlTable = new StringBuilder();
        StringBuilder append = htmlTable.append("<table id = '").append(uniqueID).append("' class=\"table table-bordered table-hover\">");
        htmlTable.append(getHeaders());
        htmlTable.append(getBody());
        htmlTable.append("</table>");
        return htmlTable.toString();

    }

    private String getHeaders() {
        StringBuilder Header = new StringBuilder();
        Header.append("<thead><tr>");
        header.stream().map(campo -> {
            Header.append("<th>");
            Header.append(campo);
            return campo;
        }).forEachOrdered(_item -> {
            Header.append("</th>");
        });
        Header.append("</tr></thead>");
        return Header.toString();
    }

    private String getBody() {
        StringBuilder body = new StringBuilder();
        body.append("<tbody>");

        bodyTable.stream().map(campo -> {
            body.append("<tr>");
            body.append("<td>").append(campo.getCedula()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getNombre()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getGenero()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getFechaNacimiento()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getEdad()).append("</td>");
            return campo;
        }).map((reportePersona campo) -> {
            body.append("<td>").append(campo.getEmpresa()).append("</td>");
            return campo;
        }).map((reportePersona campo) -> {
            body.append("<td>").append(campo.getAntiguedadEmpresa()).append("</td>");
            return campo;
        }).map((reportePersona campo) -> {
            body.append("<td>").append(campo.getCargo()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getFechaClinica()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getTelefono()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getCorreo()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getEps()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getAfp()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getArl()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getContrato()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getOrganizacion()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getMunicipio()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getBarrio()).append("</td>");
            return campo;
        }).map(campo -> {
            body.append("<td>").append(campo.getDirrecion()).append("</td>");
            return campo;
        }).forEachOrdered(_item -> {
            body.append("</tr>");
        });
        body.append("</tbody>");
        return body.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.PersonaDireccionJpaController;
import com.softcoisoweb.controller.PersonaJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.PersonaDireccion;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
public class PersonaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String btnCrearPersona = request.getParameter("btnCrearPersona");
        String selectConsulta = request.getParameter("selectConsulta");
        String btnModificar = request.getParameter("btnModificar");
        String btnEliminar = request.getParameter("btnEliminar");
        try {
            if (btnCrearPersona != null && btnCrearPersona.equals("ok")) {
                String crear = crearPersona(request, response);
                out.print(crear);
            }
            if (selectConsulta != null) {
                String datos = consultarDatos(selectConsulta);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modificarPersona = modificarPersona(request, response);
                out.print(modificarPersona);
            }
            if (btnEliminar != null && btnEliminar.equals("ok")) {
                String eliminarPersona = eliminarPersona(request);
                out.print(eliminarPersona);
            }
            cargarDatos(request, response);
        } finally {
            out.close();
        }
    }

    private String crearPersona(HttpServletRequest request, HttpServletResponse response) {
        String resultado;
        PersonaJpaController PersonJpa = new PersonaJpaController(JPAFactory.getFACTORY());
        PersonaDireccionJpaController PersonDireccionJpa = new PersonaDireccionJpaController(JPAFactory.getFACTORY());
        String cedula = request.getParameter("cedula");
        String nombrePersona = request.getParameter("nombrePersona");
        String apellidos = request.getParameter("apellidos");
        String genero = request.getParameter("genero");
        String edad = request.getParameter("edad");
        String cumpleanos = request.getParameter("cumpleanos");
        String municipio = request.getParameter("municipio");
        String barrio = request.getParameter("barrio");
        String personaDireccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String codigoEps = request.getParameter("eps");
        String codigoArl = request.getParameter("arl");
        String codigoAfp = request.getParameter("afp");
        String codigoContrato = request.getParameter("contrato");
        String codigoSindicato = request.getParameter("sindicato");
        String empresa = request.getParameter("empresa");
        String empresaUsuaria = request.getParameter("empresaUsuaria");
        String sectorEconomico = request.getParameter("sectorEconomico");
        String cargo = request.getParameter("cargo");
        String anosExperiencia = request.getParameter("anosExperiencia");
        String FechaClinica = request.getParameter("FechaClinica");
        String recomendado = request.getParameter("recomendado");
        try {

            Persona persona = new Persona(cedula, nombrePersona, apellidos, genero, cumpleanos, edad, anosExperiencia, cargo,
                    FechaClinica, telefono, correo, recomendado, "No", codigoAfp, codigoArl, codigoEps, codigoContrato, codigoSindicato,
                    empresa, empresaUsuaria, sectorEconomico);
            PersonJpa.create(persona);

            PersonaDireccion direccion = new PersonaDireccion(cedula, municipio, barrio, personaDireccion);
            PersonDireccionJpa.create(direccion);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error creando una persona, el error es: " + e);
            resultado = "Error";
        }

        return resultado;
    }

    public void cargarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        PersonaJpaController Persona = new PersonaJpaController(JPAFactory.getFACTORY());
        Persona.refreshJPACache();
        List<Persona> listPerson = Persona.findPersonaEntities();
        session.setAttribute("Persona", listPerson);
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    private String consultarDatos(String cedula) {
        String respuesta = null;
        try {
            PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            PersonaDireccionJpaController direccionJpa = new PersonaDireccionJpaController(JPAFactory.getFACTORY());
            Persona persona = Personajpa.findPersona(cedula);
            PersonaDireccion direccion = direccionJpa.findPersonaDireccion(cedula);

            respuesta = persona.getCedula() + "," + persona.getNombrePersona() + "," + persona.getApellidoPersona() + "," + persona.getGenero() + "," + persona.getEdad()
                    + "," + persona.getFechaNacimiento() + "," + direccion.getMunicipio() + "," + direccion.getBarrio() + "," + direccion.getDireccion() + "," + persona.getTelefono()
                    + "," + persona.getCorreo() + "," + persona.getEpsCodigoEps() + "," + persona.getArlCodigoArl() + "," + persona.getAfpCodigoAfp()
                    + "," + persona.getTipoContratoCodigoTipoContrato() + "," + persona.getOrganizacionSindicalCodigoOrganizacion()
                    + "," + persona.getNombreEmpresa() + "," + persona.getEmpresaUsuaria() + "," + persona.getSectorEconomico() + "," + persona.getCargo()
                    + "," + persona.getAntiguedadEmpresa() + "," + persona.getFechaClinica() + "," + persona.getRecomendado() + "," + persona.getCasoAsociado();

        } catch (Exception e) {
            System.out.println("Error consultando una persona: " + e);
        }

        return respuesta;
    }

    private String modificarPersona(HttpServletRequest request, HttpServletResponse response) {
        String resultado;
        try {
            String cedula = request.getParameter("cedulaPersona");
            String nombrePersona = request.getParameter("PersonaNombre");
            String apellidos = request.getParameter("personaApellido");
            String genero = request.getParameter("personaGenero");
            String edad = request.getParameter("personaEdad");
            String cumpleanos = request.getParameter("personaCumple");
            String municipio = request.getParameter("personaMunicipio");
            String barrio = request.getParameter("personaBarrio");
            String personaDireccion = request.getParameter("personaDir");
            String telefono = request.getParameter("personaTel");
            String correo = request.getParameter("personaEmail");
            String codigoEps = request.getParameter("personEps");
            String codigoArl = request.getParameter("arlPerson");
            String codigoAfp = request.getParameter("personAfp");
            String codigoContrato = request.getParameter("personContrato");
            String codigoSindicato = request.getParameter("personSindicato");
            String empresa = request.getParameter("personaEmp");
            String empresaUsuaria = request.getParameter("personEmpresaUsuaria");
            String sectorEconomico = request.getParameter("PersonSectorEconomico");
            String cargo = request.getParameter("PersonCargo");
            String anosExperiencia = request.getParameter("personExperiencia");
            String FechaClinica = request.getParameter("personFechaClinica");
            String recomendado = request.getParameter("personRecomendado");
            String casoAsociado = request.getParameter("casoAsociado");

            PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            PersonaDireccionJpaController PersonDireccionJpa = new PersonaDireccionJpaController(JPAFactory.getFACTORY());

            Persona persona = new Persona(cedula, nombrePersona, apellidos, genero, cumpleanos, edad, anosExperiencia, cargo,
                    FechaClinica, telefono, correo, recomendado, casoAsociado, codigoAfp, codigoArl, codigoEps, codigoContrato, codigoSindicato,
                    empresa, empresaUsuaria, sectorEconomico);
            Personajpa.edit(persona);
            PersonaDireccion direccion = new PersonaDireccion(cedula, municipio, barrio, personaDireccion);
            PersonDireccionJpa.edit(direccion);

            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error actualizando datos de una persona: " + e);
            resultado = "Error";
        }
        return resultado;

    }

    private String eliminarPersona(HttpServletRequest request) {
        String resultado;
        try {
            PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            PersonaDireccionJpaController PersonDireccionJpa = new PersonaDireccionJpaController(JPAFactory.getFACTORY());
            String cedula = request.getParameter("cedula");
            Personajpa.destroy(cedula);
            resultado = "Existoso";
        } catch (NonexistentEntityException e) {
            System.out.println("Error eliminando datos de una persona: " + e);
            resultado = "Error";
        }
        return resultado;
    }

    public String agregarCaso(String cedula) {
        String respuesta;
        try {
            PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            Personajpa.cambiarCasoAsociado(cedula);
            respuesta = "Exitoso";
        } catch (NonexistentEntityException e) {
            System.out.println("Error actualizando el caso asociado: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

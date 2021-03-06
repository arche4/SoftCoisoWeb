/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.OrganizacionSindicalJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.OrganizacionSindical;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
public class GrupoSindicatoServlet extends HttpServlet {

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

        String btnCrear = request.getParameter("btnCrear");
        String btnConsultar = request.getParameter("btnConsultar");
        String btnModificar = request.getParameter("btnModificar");
        String btnEliminar = request.getParameter("btnEliminar");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCrear != null && btnCrear.equals("ok")) {
                String crear = crear(request);
                out.print(crear);
            }
            if (btnConsultar != null) {
                String datos = consultar(btnConsultar);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modEstado = modificar(request);
                out.print(modEstado);
            }
            if (btnEliminar != null) {
                String eliminar = eliminar(btnEliminar);
                out.print(eliminar);
            }
            cargarDatos(request);

        }

    }

    private String crear(HttpServletRequest request) {
        String resultado;
        String codigo = request.getParameter("codigoSindicato");
        String nombre = request.getParameter("nombreSindicato");
        OrganizacionSindicalJpaController sindicatoJpa = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());
        try {
            OrganizacionSindical sindicato = sindicatoJpa.findOrganizacionSindical(codigo);
            if (sindicato != null) {
                resultado = "1";
            } else {
                OrganizacionSindical sindicatoC = new OrganizacionSindical(codigo, nombre);
                sindicatoJpa.create(sindicatoC);
                resultado = "0";
            }
        } catch (Exception e) {
            System.out.println("Error creando el grupo sindicato: " + codigo + "El error es:" + e);
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrganizacionSindicalJpaController sindicatoJpa = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());
        try {
            List<OrganizacionSindical> listSindicato = sindicatoJpa.findOrganizacionSindicalEntities();
            session.setAttribute("Sindicato", listSindicato);
        } catch (Exception e) {
            System.out.println("Error cargando datos de grupo sindicato. El error es:" + e);

        }
    }

    private String consultar(String codigo) {
        String respuesta = null;
        OrganizacionSindicalJpaController sindicatoJpa = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());
        try {
            OrganizacionSindical sindicato = sindicatoJpa.findOrganizacionSindical(codigo);
            respuesta = sindicato.getCodigoOrganizacion() + "#" + sindicato.getNombre();
        } catch (Exception e) {
            System.out.println("Error consultando datos del grupo sindicato" + codigo + " El error es:" + e);
        }
        return respuesta;

    }

    private String modificar(HttpServletRequest request) {
        String resultado;
        String codigo = request.getParameter("codigoSindicato");
        String nombre = request.getParameter("nombreSindicato");
        OrganizacionSindicalJpaController sindicatoJpa = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());
        try {
            OrganizacionSindical sindicatoC = new OrganizacionSindical(codigo, nombre);
            sindicatoJpa.edit(sindicatoC);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando el grupo Sindicato: " + codigo + "El error es:" + e);
            resultado = "Error";
        }
        return resultado;
    }
    
     private String eliminar(String codigo) {
        String resultado;
        OrganizacionSindicalJpaController sindicatoJpa = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());
        try {
            List<Persona> sindicatoXpersona = sindicatoJpa.sindicatoXpersona(codigo);
            if (!sindicatoXpersona.isEmpty()) {
                resultado = "1";
            } else {
                sindicatoJpa.destroy(codigo);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            System.out.println("Error eliminando el grupo sindicato: " + codigo + "El error es:" + e);
            resultado = "2";
        }
        return resultado;

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

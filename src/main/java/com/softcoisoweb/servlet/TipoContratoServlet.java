/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.TipoContratoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.TipoContrato;
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
public class TipoContratoServlet extends HttpServlet {

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
        String codigo = request.getParameter("codigoContrato");
        String nombre = request.getParameter("nombreContrato");
        String descripcion = request.getParameter("descripcionContrato");
        TipoContratoJpaController contratoJpa = new TipoContratoJpaController(JPAFactory.getFACTORY());
        try {
            TipoContrato contrato = contratoJpa.findTipoContrato(codigo);
            if (contrato != null) {
                resultado = "1";
            } else {
                TipoContrato contratoC = new TipoContrato(codigo, nombre, descripcion);
                contratoJpa.create(contratoC);
                resultado = "0";
            }
        } catch (Exception e) {
            System.out.println("Error creando el contrato: " + codigo + "El error es:" + e);
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TipoContratoJpaController contratoJpa = new TipoContratoJpaController(JPAFactory.getFACTORY());
        try {
            List<TipoContrato> listContrato = contratoJpa.findTipoContratoEntities();
            session.setAttribute("Contrato", listContrato);
        } catch (Exception e) {
            System.out.println("Error cargando datos de contrato. El error es:" + e);

        }
    }

    private String consultar(String codigo) {
        String respuesta = null;
        TipoContratoJpaController contratoJpa = new TipoContratoJpaController(JPAFactory.getFACTORY());
        try {
            TipoContrato contrato = contratoJpa.findTipoContrato(codigo);
            respuesta = contrato.getCodigoTipoContrato() + "#" + contrato.getNombre() + "#" + contrato.getDescripcion();
        } catch (Exception e) {
            System.out.println("Error consultando datos del contrato " + codigo + " El error es:" + e);
        }
        return respuesta;

    }

    private String modificar(HttpServletRequest request) {
        String resultado;
        String codigo = request.getParameter("codigoContrato");
        String nombre = request.getParameter("nombreContrato");
        String descripcion = request.getParameter("descripcionContrato");
        TipoContratoJpaController contratoJpa = new TipoContratoJpaController(JPAFactory.getFACTORY());
        try {
            TipoContrato contratoC = new TipoContrato(codigo, nombre, descripcion);
            contratoJpa.edit(contratoC);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando el contrato: " + codigo + "El error es:" + e);
            resultado = "Error";
        }
        return resultado;
    }

    private String eliminar(String codigo) {
        String resultado;
        TipoContratoJpaController contratoJpa = new TipoContratoJpaController(JPAFactory.getFACTORY());
        try {
            List<Persona> contratoXpersona = contratoJpa.contratoXpersona(codigo);
            if (!contratoXpersona.isEmpty()) {
                resultado = "1";
            } else {
                contratoJpa.destroy(codigo);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            System.out.println("Error eliminando el contrato: " + codigo + "El error es:" + e);
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

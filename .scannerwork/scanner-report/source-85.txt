/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.EstadoCasoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
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
public class EstadoCasoServlet extends HttpServlet {

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
                String crear = crearEstado(request);
                out.print(crear);
            }
            if (btnConsultar != null) {
                String datos = consultarEstado(btnConsultar);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modEstado = modificarEstado(request);
                out.print(modEstado);
            }
            if (btnEliminar != null) {
                String eliminar = eliminarEstado(btnEliminar);
                out.print(eliminar);
            }
            cargarDatos(request);
        }
    }

    private String crearEstado(HttpServletRequest request) {
        String resultado;
        String codigoEstado = request.getParameter("codigoEstado");
        String nombreEstado = request.getParameter("nombreEstado");
        String descripcionEstado = request.getParameter("descripcionEstado");
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        try {
            EstadoCaso estado = estadoJpa.findEstadoCaso(codigoEstado);
            if (estado != null) {
                resultado = "1";
            } else {
                EstadoCaso estadoC = new EstadoCaso(codigoEstado, nombreEstado, descripcionEstado);
                estadoJpa.create(estadoC);
                resultado = "0";
            }
        } catch (Exception e) {
            System.out.println("Error creando el estado de caso: " + codigoEstado + "El error es:" + e);
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        try {
            List<EstadoCaso> ListEstado = estadoJpa.findEstadoCasoEntities();
            session.setAttribute("Estado", ListEstado);
        } catch (Exception e) {
            System.out.println("Error cargando datos de estado de caso. El error es:" + e);
        }
    }

    private String consultarEstado(String codigoEstado) {
        String respuesta = null;
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        try {
            EstadoCaso estado = estadoJpa.findEstadoCaso(codigoEstado);
            respuesta = estado.getCodigoEstado() + "#" + estado.getNombreEstado() + "#" + estado.getDescripcion();
        } catch (Exception e) {
            System.out.println("Error consultando datos de estado de caso " + codigoEstado + " El error es:" + e);
        }
        return respuesta;
    }

    private String modificarEstado(HttpServletRequest request) {
        String respuesta;
        String codigoEstado = request.getParameter("codigoEstado");
        String nombreEstado = request.getParameter("nombreEstado");
        String descripcionEstado = request.getParameter("descripcionEstado");
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        try {
            EstadoCaso estadoC = new EstadoCaso(codigoEstado, nombreEstado, descripcionEstado);
            estadoJpa.edit(estadoC);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando el estado de caso: " + codigoEstado + "El error es:" + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String eliminarEstado(String codigoEstado) {
        String resultado;
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        try {
            List<FlujoCaso> estadoXcaso = estadoJpa.EstadoxCasos(codigoEstado);
            if (!estadoXcaso.isEmpty()) {
                resultado = "1";
            } else {
                estadoJpa.destroy(codigoEstado);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            System.out.println("Error eliminando el estado de caso: " + codigoEstado + "El error es:" + e);
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

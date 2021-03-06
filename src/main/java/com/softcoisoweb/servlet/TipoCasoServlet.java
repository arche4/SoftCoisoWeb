/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.TipoCasoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.TipoCaso;
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
public class TipoCasoServlet extends HttpServlet {

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

        String btnCrearTipoCaso = request.getParameter("btnCrearTipoCaso");
        String consultarTipoCaso = request.getParameter("consultarTipoCaso");
        String btnModificar = request.getParameter("btnModificar");
        String btnEliminar = request.getParameter("btnEliminar");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCrearTipoCaso != null && btnCrearTipoCaso.equals("ok")) {
                String crear = crearTipoCaso(request);
                out.print(crear);
            }
            if (consultarTipoCaso != null) {
                String datos = consultarTipoCaso(consultarTipoCaso);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modificar = modificarTipoCaso(request);
                out.print(modificar);
            }
            if (btnEliminar != null) {
                String codTipo = eliminarTipoCaso(btnEliminar);
                out.print(codTipo);
            }
            cargarDatos(request);
        }
    }

    private String crearTipoCaso(HttpServletRequest request) {
        String resultado;
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        String codigoTipoCaso = request.getParameter("codigoTipoCaso");
        String nombreTipoCaso = request.getParameter("nombreTipoCaso");
        String descripcionTipoCaso = request.getParameter("descripcionTipoCaso");
        try {
            TipoCaso tipocaso = tipoCasoJpa.findTipoCaso(codigoTipoCaso);
            if (tipocaso != null) {
                resultado = "1";
            } else {
                TipoCaso tipo = new TipoCaso(codigoTipoCaso, nombreTipoCaso, descripcionTipoCaso);
                tipoCasoJpa.create(tipo);
                resultado = "0";
            }
        } catch (Exception e) {
            System.out.println("Error creando el tipo de caso: " + codigoTipoCaso + "El error es:" + e);
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        try {
            List<TipoCaso> listTipoCaso = tipoCasoJpa.findTipoCasoEntities();
            session.setAttribute("Tipo", listTipoCaso);
        } catch (Exception e) {
            System.out.println("Error cargando datos de tipo de caso. El error es:" + e);
        }
    }

    private String consultarTipoCaso(String codigo) {
        String respuesta = null;
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        try {
            TipoCaso tipoCaso = tipoCasoJpa.findTipoCaso(codigo);
            respuesta = tipoCaso.getCodigoTipoCaso() + "#" + tipoCaso.getTipoCaso() + "#" + tipoCaso.getDescripcion();
        } catch (Exception e) {
            System.out.println("Error consultando datos de tipo de caso " + codigo + " El error es:" + e);
        }
        return respuesta;
    }

    private String modificarTipoCaso(HttpServletRequest request) {
        String respuesta;
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        String codigoTipoCaso = request.getParameter("codigoTipoCaso");
        String nombreTipoCaso = request.getParameter("nombreTipoCaso");
        String descripcionTipoCaso = request.getParameter("descripcionTipoCaso");
        try {
            TipoCaso tipo = new TipoCaso(codigoTipoCaso, nombreTipoCaso, descripcionTipoCaso);
            tipoCasoJpa.edit(tipo);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando el tipo de caso: " + codigoTipoCaso + "El error es:" + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String eliminarTipoCaso(String codigo) {
        String resultado;
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        try {
            List<CasoPersona> tipoXCaso = tipoCasoJpa.tipoXCaso(codigo);
            if (!tipoXCaso.isEmpty()) {
                resultado = "1";
            } else {
                tipoCasoJpa.destroy(codigo);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            System.out.println("Error eliminando el tipo de caso: " + codigo + "El error es:" + e);
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

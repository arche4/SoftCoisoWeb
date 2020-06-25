/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.ArlJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Arl;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.util.Gestor;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
@WebServlet(name = "ArlServlet", urlPatterns = {"/ArlServlet"})
public class ArlServlet extends HttpServlet {

    private final Gestor doc = new Gestor();

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
        response.setContentType("text/html;charset=UTF-8");

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
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        ArlJpaController arlJpa = new ArlJpaController(JPAFactory.getFACTORY());
        try {
            Arl arl = arlJpa.findArl(codigo);
            if (arl != null) {
                resultado = "1";
            } else {
                Arl arlCreate = new Arl(codigo, nombre);
                arlJpa.create(arlCreate);
                resultado = "0";
            }
        } catch (Exception e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error creando una nueva ARL: " + codigo + " El error es: " + e);
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArlJpaController arlJpa = new ArlJpaController(JPAFactory.getFACTORY());
        try {
            List<Arl> listArl = arlJpa.findArlEntities();
            session.setAttribute("ARL", listArl);
        } catch (Exception e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error cargando los datos de las  ARL, El error es: " + e);

        }
    }

    private String consultar(String codigo) {
        String respuesta = null;
        ArlJpaController arlJpa = new ArlJpaController(JPAFactory.getFACTORY());
        try {
            Arl arl = arlJpa.findArl(codigo);
            respuesta = arl.getCodigoArl() + "#" + arl.getNombreArl();
        } catch (Exception e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-SSe presento un error consultando los datos de la  ARL: " + codigo + " El error es: " + e);

        }
        return respuesta;

    }

    private String modificar(HttpServletRequest request) {
        String resultado;
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        ArlJpaController arlJpa = new ArlJpaController(JPAFactory.getFACTORY());
        try {
            Arl arlEdit = new Arl(codigo, nombre);
            arlJpa.edit(arlEdit);
            resultado = "Exitoso";
        } catch (Exception e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-SSe presento un error modificando los datos de la  ARL: " + codigo + " El error es: " + e);
            resultado = "Error";
        }
        return resultado;
    }

    private String eliminar(String codigo) {
        String resultado;
        ArlJpaController arlJpa = new ArlJpaController(JPAFactory.getFACTORY());
        try {
            List<Persona> arlXpersona = arlJpa.arlXpersona(codigo);
            if (!arlXpersona.isEmpty()) {
                resultado = "1";
            } else {
                arlJpa.destroy(codigo);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-SSe presento un error eliminando  los datos de la  ARL: " + codigo + " El error es: " + e);
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

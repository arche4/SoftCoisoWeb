/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.AfpJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Afp;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AfpServlet", urlPatterns = {"/AfpServlet"})
public class AfpServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

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
        AfpJpaController afpJpa = new AfpJpaController(JPAFactory.getFACTORY());
        try {
            Afp afp = afpJpa.findAfp(codigo);
            if (afp != null) {
                resultado = "1";
            } else {
                Afp afpCreate = new Afp(codigo, nombre);
                afpJpa.create(afpCreate);
                resultado = "0";
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando una nueva AFP:  {0} El error es: {1}", new Object[]{codigo, e});
            resultado = "2";
        }
        return resultado;
    }

    private void cargarDatos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AfpJpaController afpJpa = new AfpJpaController(JPAFactory.getFACTORY());
        try {
            List<Afp> listAfp = afpJpa.findAfpEntities();
            session.setAttribute("AFP", listAfp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error cargando los datos de las  AFP:  El error es: {0}", new Object[]{e});
        }
    }

    private String consultar(String codigo) {
        String respuesta = null;
        AfpJpaController afpJpa = new AfpJpaController(JPAFactory.getFACTORY());
        try {
            Afp afp = afpJpa.findAfp(codigo);
            respuesta = afp.getCodigoAfp() + "#" + afp.getNombreAfp();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la  AFP: {0} El error es: {1}", new Object[]{codigo, e});
        }
        return respuesta;

    }

    private String modificar(HttpServletRequest request) {
        String resultado;
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        AfpJpaController afpJpa = new AfpJpaController(JPAFactory.getFACTORY());
        try {
            Afp afpEdit = new Afp(codigo, nombre);
            afpJpa.edit(afpEdit);
            resultado = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error modificando los datos de la  AFP: {0} El error es: {1}", new Object[]{codigo, e});
            resultado = "Error";
        }
        return resultado;
    }
    
    private String eliminar(String codigo) {
        String resultado;
        AfpJpaController afpJpa = new AfpJpaController(JPAFactory.getFACTORY());
        try {
            List<Persona> afpXpersona = afpJpa.afpXpersona(codigo);
            if (!afpXpersona.isEmpty()) {
                resultado = "1";
            } else {
                afpJpa.destroy(codigo);
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando la  AFP: {0} El error es: {1}", new Object[]{codigo, e});
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

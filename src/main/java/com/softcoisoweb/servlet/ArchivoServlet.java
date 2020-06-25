/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.CasoArchivoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.CasoArchivo;
import com.softcoisoweb.util.Gestor;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
@WebServlet(name = "ArchivoServlet", urlPatterns = {"/ArchivoServlet"})
public class ArchivoServlet extends HttpServlet {

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

        String btnCargarArchivo = request.getParameter("btnCargarArchivo");
        String btnEliminarArchivo = request.getParameter("btnEliminarArchivo");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCargarArchivo != null && btnCargarArchivo.equals("ok")) {
                String crear = cargarArchivo(request);
                out.print(crear);
            }
            if (btnEliminarArchivo != null) {
                String deleteArchivo = eliminarArchivo(btnEliminarArchivo);
                out.print(deleteArchivo);
            }
        }
    }

    private String cargarArchivo(HttpServletRequest request) {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        CasoArchivoJpaController archivoJpa = new CasoArchivoJpaController(JPAFactory.getFACTORY());
        String casoId = request.getParameter("casoId");
        String usuario = request.getParameter("usuario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");

        try {
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            String fechaActual = accionesExpediente.getFecha();

            CasoArchivo archivo = new CasoArchivo(casoId, usuario, nombreUsuario, nombreArchivo, rutaArchivo, fechaActual);
            archivoJpa.create(archivo);
            respuesta = "Exitoso";
        } catch (Exception e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error agregando un archivo al expediente, El error es: " + e);

            respuesta = "Error";
        }
        return respuesta;
    }

    private String eliminarArchivo(String codigoArchivo) {
        String respuesta;
        CasoArchivoJpaController archivoJpa = new CasoArchivoJpaController(JPAFactory.getFACTORY());
        try {
            archivoJpa.destroy(Integer.parseInt(codigoArchivo));
            respuesta = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error eliminando el archivo al expediente, El error es: " + e);
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

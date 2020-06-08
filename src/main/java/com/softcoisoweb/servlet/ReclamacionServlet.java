/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.obtenerFecha;
import com.softcoisoweb.controller.ProcesoReclamacionJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
@WebServlet(name = "ReclamacionServlet", urlPatterns = {"/ReclamacionServlet"})
public class ReclamacionServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        
        String btnCrear = request.getParameter("btnCrearReclamacion");
        String btnModificar = request.getParameter("btnModificarReclamacion");
        String btnConsultar = request.getParameter("btnConsultarReclamacion");
        String btnEliminar = request.getParameter("btnEliminarReclamacion");
       
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
                String modificar = modificar(request);
                out.print(modificar);
            }
            if (btnEliminar != null) {
                String eliminar = eliminar(btnEliminar);
                out.print(eliminar);
            }
        }
    }
    
    private String crear(HttpServletRequest request) {
        String respuesta;
        ProcesoReclamacionJpaController reclamacionJpa= new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        String comentarioReclamacion = request.getParameter("comentarioReclamacion");
        String nombreArchivoReclamacion = request.getParameter("nombreArchivoReclamacion");
        String rutaArchivoReclamacion = request.getParameter("rutaArchivoReclamacion");
        String casoIdReclamacion = request.getParameter("casoIdReclamacion");
        String usuarioReclamacion = request.getParameter("usuarioReclamacion");
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoReclamacion reclamacionCreate = new ProcesoReclamacion(comentarioReclamacion, nombreArchivoReclamacion, rutaArchivoReclamacion,
                    casoIdReclamacion, usuarioReclamacion,fechaActual, fechaActual);
            reclamacionJpa.create(reclamacionCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error creando la reclamacion, El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigoReclamacion) {
        String respuesta = null;
        ProcesoReclamacionJpaController reclamacionJpa= new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        try {
            ProcesoReclamacion getReclamacion = reclamacionJpa.findProcesoReclamacion(Integer.parseInt(codigoReclamacion));
            respuesta = getReclamacion.getCodigo() + "#" + getReclamacion.getComentarios() + "#" + getReclamacion.getNombreArchivo()
                    + "#" + getReclamacion.getRutaArchivos()+ "#" + getReclamacion.getCasoPersonaIdCaso() + "#" + getReclamacion.getUsuarioCedula() + "#" + getReclamacion.getFechaCreacion();
        } catch (NumberFormatException e) {
            System.out.println("Error consultando al proceso: " + codigoReclamacion + "El error es:" + e);
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) throws Exception {
        String respuesta;
        ProcesoReclamacionJpaController reclamacionJpa= new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        String codigoReclamacion = request.getParameter("codigoReclamacion");
        String comentarioReclamacion = request.getParameter("comentarioReclamacion");
        String nombreArchivoReclamacion = request.getParameter("nombreArchivoReclamacion");
        String rutaArchivoReclamacion = request.getParameter("rutaArchivoReclamacion");
        String casoIdReclamacion = request.getParameter("casoIdReclamacion");
        String usuarioReclamacion = request.getParameter("usuarioReclamacion");
        String fechaCreacionReclamacion = request.getParameter("usuarioReclamacion");
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoReclamacion reclamacionCreate = new ProcesoReclamacion(Integer.parseInt(codigoReclamacion), comentarioReclamacion, nombreArchivoReclamacion, rutaArchivoReclamacion,
                    casoIdReclamacion, usuarioReclamacion,fechaCreacionReclamacion, fechaActual);
            reclamacionJpa.edit(reclamacionCreate);
            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error creando el proceso de calificacion: " + codigoReclamacion + " El Error es: " + e);
            respuesta = "Error";
        }

        return respuesta;

    }

    private String eliminar(String codigoReclamacion) {
        String resultado;
        ProcesoReclamacionJpaController reclamacionJpa= new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        try {
            reclamacionJpa.destroy(Integer.parseInt(codigoReclamacion));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            System.err.println("Se presento un error eliminando el proceso de calificacion: " + codigoReclamacion + " El Error es: " + e);
            resultado = "Error";
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ReclamacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ReclamacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

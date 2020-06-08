/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.obtenerFecha;
import com.softcoisoweb.controller.ProcesoCalificacionJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
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
@WebServlet(name = "procesoCalificacionServlet", urlPatterns = {"/procesoCalificacionServlet"})
public class procesoCalificacionServlet extends HttpServlet {

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

        String btnCrear = request.getParameter("btnCrearProceso");
        String btnModificar = request.getParameter("btnModificarProceso");
        String btnConsultar = request.getParameter("btnConsultarProceso");
        String btnEliminar = request.getParameter("btnEliminarProceso");

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
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        String nombreProceso = request.getParameter("proceso");
        String comentarioProceso = request.getParameter("comentarioProceso");
        String nombreArchivoProceso = request.getParameter("nombreArchivoProceso");
        String rutaArchivoProceso = request.getParameter("rutaArchivoProceso");
        String usuarioProceso = request.getParameter("usuarioProceso");
        String casoIdProceso = request.getParameter("casoIdProceso");
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(nombreProceso, comentarioProceso, nombreArchivoProceso,
                    rutaArchivoProceso, usuarioProceso, casoIdProceso, fechaActual, fechaActual);
            procesoJpa.create(procesoCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error creando el proceso de calificacion: " + nombreProceso + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigoProceso) {
        String respuesta = null;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            respuesta = getProceso.getCodigo() + "#" + getProceso.getProceso() + "#" + getProceso.getComentario()
                    + "#" + getProceso.getNombreArchivo() + "#" + getProceso.getRutaArchivo() + "#" + getProceso.getUsuarioCedula() + "#" + getProceso.getFechaCreacion();
        } catch (NumberFormatException e) {
            System.out.println("Error consultando al proceso: " + codigoProceso + "El error es:" + e);
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) {
        String respuesta;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        String codigoProceso = request.getParameter("codigoProceso");
        String nombreProceso = request.getParameter("proceso");
        String comentarioProceso = request.getParameter("comentarioProceso");
        String nombreArchivoProceso = request.getParameter("nombreArchivoProceso");
        String rutaArchivoProceso = request.getParameter("rutaArchivoProceso");
        String usuarioProceso = request.getParameter("usuarioProceso");
        String casoIdProceso = request.getParameter("casoIdProceso");
        String fechaCreacion = request.getParameter("fechaCreacionProceso");
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), nombreProceso, comentarioProceso, nombreArchivoProceso,
                    rutaArchivoProceso, usuarioProceso, casoIdProceso, fechaCreacion, fechaActual);
            procesoJpa.edit(procesoCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error creando el proceso de calificacion: " + nombreProceso + " El Error es: " + e);
            respuesta = "Error";
        }

        return respuesta;

    }

    private String eliminar(String codigoProceso) {
        String resultado;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {
            procesoJpa.destroy(Integer.parseInt(codigoProceso));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            System.err.println("Se presento un error eliminando el proceso de calificacion: " + codigoProceso + " El Error es: " + e);
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

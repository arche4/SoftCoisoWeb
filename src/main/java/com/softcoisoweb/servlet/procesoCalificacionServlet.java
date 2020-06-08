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
        String btnConsultarArchivo = request.getParameter("btnConsultarArchivo");
        String btnAgregarArchivo = request.getParameter("btnModificarArchivo");
        String btnEliminarArchivo = request.getParameter("btnEliminarArchivo");

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
            if (btnConsultarArchivo != null) {
                String gerArchivo = consultarArchivo(btnConsultarArchivo);
                out.print(gerArchivo);
            }
            if (btnAgregarArchivo != null && btnAgregarArchivo.equals("ok")) {
                String setArchivo = agregarArchivo(request);
                out.print(setArchivo);
            }
            if (btnEliminarArchivo != null) {
                String deleteArchivo = eliminarArchivo(btnEliminarArchivo);
                out.print(deleteArchivo);
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
            respuesta = getProceso.getCodigo() + "#" + getProceso.getProceso() + "#" + getProceso.getComentario();
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
        String usuarioProceso = request.getParameter("usuarioProceso");

        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), nombreProceso, comentarioProceso, getProceso.getNombreArchivo(),
                    getProceso.getRutaArchivo(), usuarioProceso, getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
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

    private String consultarArchivo(String idProceso) {
        String respuesta = null;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(idProceso));
            respuesta = getProceso.getCodigo() + "," + getProceso.getRutaArchivo();
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error consultando el archivo del proceso: " + idProceso + " El Error es: " + e);
        }
        return respuesta;
    }

    private String agregarArchivo(HttpServletRequest request) {
        String respuesta;
        String codigoProceso = request.getParameter("codigoProceso");
        String nombreArchivo = request.getParameter("nombreArchivoProceso");
        String rutaArchivoProceso = request.getParameter("rutaArchivoProceso");
        String usuarioProceso = request.getParameter("usuarioProceso");
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), getProceso.getProceso(), getProceso.getComentario(), nombreArchivo,
                    rutaArchivoProceso, usuarioProceso, getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            procesoJpa.edit(procesoCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error agregar un archivo al proceso de calificacion: " + codigoProceso + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }
    
    private String eliminarArchivo(String codigoProceso){
     String respuesta;
     ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), getProceso.getProceso(), getProceso.getComentario(), "",
                    "", getProceso.getUsuarioCedula(), getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            procesoJpa.edit(procesoCreate);
            respuesta = "Exitoso"; 
        } catch (Exception e) {
            System.err.println("Se presento un error eliminando un archivo al proceso de calificacion: " + codigoProceso + " El Error es: " + e);
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

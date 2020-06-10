/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.DiagnosticoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Diagnostico;
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
@WebServlet(name = "DiagnosticoServlet", urlPatterns = {"/DiagnosticoServlet"})
public class DiagnosticoServlet extends HttpServlet {

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

        String btnCrear = request.getParameter("btnCrearDiagnostico");
        String btnModificar = request.getParameter("btnModificarDiagnostico");
        String btnConsultar = request.getParameter("btnConsultarDiagnostico");
        String btnEliminar = request.getParameter("btnEliminarDiagnostico");

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
                String modfiicar = modificar(request);
                out.print(modfiicar);
            }
            if (btnEliminar != null) {
                String eliminar = eliminar(btnEliminar);
                out.print(eliminar);
            }
        }
    }

    private String crear(HttpServletRequest request) {
        String respuesta;
        DiagnosticoJpaController diagnosticoJpa = new DiagnosticoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String diagnostico = request.getParameter("diagnostico");
        String fechaDiagnostico = request.getParameter("fechaDiagnostico");
        String comentario = request.getParameter("comentarioDiagnostico");
        String casoid = request.getParameter("idCasoDiagnostico");
        String usuario = request.getParameter("usuarioDiagnostico");
        String nombreArchivoDiagnostico = request.getParameter("nombreArchivoDiagnostico");
        String rutaArchivoDiagnostico = request.getParameter("rutaArchivoDiagnostico");
        String accion = "Se agregar un diagnostico al expediente";

        try {

            String fechaActual = accionesExpediente.ObtenerFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            Diagnostico diagnosticoCreate = new Diagnostico(diagnostico, fechaDiagnostico, comentario,
                    casoid, usuario, nombreUsuario, nombreArchivoDiagnostico, rutaArchivoDiagnostico, fechaActual);
            diagnosticoJpa.create(diagnosticoCreate);
            accionesExpediente.guardarAccionesExpediente(casoid, usuario, accion);

            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error creando el diagnostico al expediente," + casoid + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigo) {
        String respuesta = null;
        DiagnosticoJpaController diagnosticoJpa = new DiagnosticoJpaController(JPAFactory.getFACTORY());
        try {
            Diagnostico diagnostico = diagnosticoJpa.findDiagnostico(Integer.parseInt(codigo));

            respuesta = diagnostico.getCodigoDiagnostico()
                    + "#" + diagnostico.getDiagnostico()
                    + "#" + diagnostico.getFechaDiagnostico()
                    + "#" + diagnostico.getComentario() + "#" + diagnostico.getIdCaso() + "#" + diagnostico.getUsuarioCedula()
                    + "#" + diagnostico.getNombreArchivo() + "#" + diagnostico.getRutaArchivo() + "#" + diagnostico.getFechaCreacion();
        } catch (NumberFormatException e) {
            System.out.println("Error consultando el diagnostico: " + codigo + "El error es:" + e);
        }
        return respuesta;
    }

    private String modificar(HttpServletRequest request) {
        String respuesta;
        DiagnosticoJpaController diagnosticoJpa = new DiagnosticoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String codigo = request.getParameter("codigo");
        String diagnostico = request.getParameter("diagnostico");
        String comentario = request.getParameter("comentarioDiagnostico");
        String casoId = request.getParameter("idCasoDiagnostico");
        String usuario = request.getParameter("usuarioDiagnostico");
        String nombreArchivo = request.getParameter("nombreArchivoDiagnostico");
        String rutaArchivo = request.getParameter("rutaArchivoDiagnostico");
        String accion = "Se modifica el diagnostico al expediente";
        try {

            String fechaActual = accionesExpediente.ObtenerFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);

            Diagnostico getDiagnostico = diagnosticoJpa.findDiagnostico(Integer.parseInt(codigo));

            Diagnostico diagnosticoCreate;
            if (!rutaArchivo.equals("")) {
                diagnosticoCreate = new Diagnostico(Integer.parseInt(codigo), diagnostico, getDiagnostico.getFechaDiagnostico(), comentario,
                        casoId, usuario, nombreUsuario, nombreArchivo, rutaArchivo, fechaActual);
            } else {
                diagnosticoCreate = new Diagnostico(Integer.parseInt(codigo), diagnostico, getDiagnostico.getFechaDiagnostico(), comentario,
                        casoId, usuario, nombreUsuario, getDiagnostico.getNombreArchivo(), getDiagnostico.getRutaArchivo(), fechaActual);
            }

            diagnosticoJpa.create(diagnosticoCreate);
            accionesExpediente.guardarAccionesExpediente(casoId, usuario, accion);

            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error modificando el diagnostico al expediente: " + casoId + " El Error es: " + e);
            respuesta = "Error";
        }

        return respuesta;

    }

    private String eliminar(String codigoDiagnostico) {
        String resultado;
        DiagnosticoJpaController diagnosticoJpa = new DiagnosticoJpaController(JPAFactory.getFACTORY());
        try {
            diagnosticoJpa.destroy(Integer.parseInt(codigoDiagnostico));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            System.err.println("Se presento un error eliminando el diagnostico al expediente: " + codigoDiagnostico + " El Error es: " + e);
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

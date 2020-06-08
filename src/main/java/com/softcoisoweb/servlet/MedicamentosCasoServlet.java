/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.obtenerFecha;
import com.softcoisoweb.controller.MedicamentosCasoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.MedicamentosCaso;
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
@WebServlet(name = "MedicamentosCasoServlet", urlPatterns = {"/MedicamentosCasoServlet"})
public class MedicamentosCasoServlet extends HttpServlet {

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

        String btnCrear = request.getParameter("btnCrearMedicamento");
        String btnModificar = request.getParameter("btnModificarMedicamento");
        String btnConsultar = request.getParameter("btnConsultarMedicamento");
        String btnEliminar = request.getParameter("btnEliminarMedicamento");

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
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        String casoIdMedicamento = request.getParameter("casoIdMedicamento");
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String dosificacion = request.getParameter("dosificacion");
        String cantidadMedicamento = request.getParameter("cantidadMedicamento");
        String ccomentarioMedicamento = request.getParameter("ccomentarioMedicamento");
        String usuarioMedicamento = request.getParameter("usuarioMedicamento");
        String nombreArchivoMedicamento = request.getParameter("nombreArchivoMedicamento");
        String rutaArchivoMedicamento = request.getParameter("rutaArchivoMedicamento");

        try {
            obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            MedicamentosCaso medicamentroCreate = new MedicamentosCaso(casoIdMedicamento, fechaActual, codigoMedicamento,
                    dosificacion, cantidadMedicamento, ccomentarioMedicamento, usuarioMedicamento, nombreArchivoMedicamento,
                    rutaArchivoMedicamento,fechaActual);
            medicamentoJpa.create(medicamentroCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error creando el medicamento al expediente, El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigoMedicamento) {
        String respuesta = null;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        try {
            MedicamentosCaso getMedicamento = medicamentoJpa.findMedicamentosCaso(Integer.parseInt(codigoMedicamento));
            respuesta = getMedicamento.getCodigoMedicamento() + "#" + getMedicamento.getCasoPersonaIdCaso() + "#" + getMedicamento.getFechaMedicamento()
                    + "#" + getMedicamento.getDosificacion() + "#" + getMedicamento.getCantidad() + "#" + getMedicamento.getComentario()
                    + "#" + getMedicamento.getUsuarioCedula()+ "#" + getMedicamento.getNombreArchivo()+ "#" + getMedicamento.getRutaArchivo()
                    + "#" + getMedicamento.getFechaActualizacion();
        } catch (NumberFormatException e) {
            System.out.println("Error consultando el medicamento: " + codigoMedicamento + "El error es:" + e);
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) throws Exception {
        String respuesta;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        String idMedicamento = request.getParameter("idMedicamento");
        String casoIdMedicamento = request.getParameter("casoIdMedicamento");
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String dosificacion = request.getParameter("dosificacion");
        String cantidadMedicamento = request.getParameter("cantidadMedicamento");
        String ccomentarioMedicamento = request.getParameter("ccomentarioMedicamento");
        String usuarioMedicamento = request.getParameter("usuarioMedicamento");
        String nombreArchivoMedicamento = request.getParameter("nombreArchivoMedicamento");
        String rutaArchivoMedicamento = request.getParameter("rutaArchivoMedicamento");
        String fechaMedicamento = request.getParameter("fechaMedicamento");
        
        try {
           obtenerFecha fecha = new obtenerFecha();
            String fechaActual = fecha.ObtenerFecha();
            MedicamentosCaso medicamentoUpdate = new MedicamentosCaso(Integer.parseInt(idMedicamento), casoIdMedicamento, fechaMedicamento, codigoMedicamento,
                    dosificacion, cantidadMedicamento, ccomentarioMedicamento, usuarioMedicamento, nombreArchivoMedicamento,
                    rutaArchivoMedicamento,fechaActual);
            medicamentoJpa.edit(medicamentoUpdate);
            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error modificando el medicamento al expediente: " + casoIdMedicamento + " El Error es: " + e);
            respuesta = "Error";
        }

        return respuesta;

    }

    private String eliminar(String codigoMedicamento) {
        String resultado;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        try {
            medicamentoJpa.destroy(Integer.parseInt(codigoMedicamento));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            System.err.println("Se presento un error eliminando el medicamento al expediente: " + codigoMedicamento + " El Error es: " + e);
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
            Logger.getLogger(MedicamentosCasoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MedicamentosCasoServlet.class.getName()).log(Level.SEVERE, null, ex);
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

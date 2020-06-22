/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
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
            throws ServletException, IOException, Exception {

        String btnCrear = request.getParameter("btnCrearMedicamento");
        String btnModificar = request.getParameter("btnModificarMedicamento");
        String btnConsultar = request.getParameter("btnConsultarMedicamento");
        String btnEliminar = request.getParameter("btnEliminarMedicamento");
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
                String modfiicar = modificar(request);
                out.print(modfiicar);
            }
            if (btnEliminar != null) {
                String eliminar = eliminar(btnEliminar);
                out.print(eliminar);
            }
            if (btnEliminarArchivo != null) {
                String deleteArchivo = eliminarArchivo(btnEliminarArchivo);
                out.print(deleteArchivo);
            }
        }
    }

    private String crear(HttpServletRequest request) {
        String respuesta;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String casoId = request.getParameter("casoId");
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String dosificacion = request.getParameter("dosificacion");
        String cantidadMedicamento = request.getParameter("cantidad");
        String comentario = request.getParameter("comentario");
        String usuario = request.getParameter("usuario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String fechaMedicamento = request.getParameter("fechaMedicamento");
        String accion = "Se crea un nuevo medicamento al expediente";
        try {
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            String fechaActual = accionesExpediente.getFecha();
            MedicamentosCaso medicamentroCreate = new MedicamentosCaso(casoId, fechaMedicamento, codigoMedicamento,
                    dosificacion, cantidadMedicamento, comentario, usuario, nombreUsuario, nombreArchivo,
                    rutaArchivo, fechaActual);
            medicamentoJpa.create(medicamentroCreate);
            accionesExpediente.guardarAccionesExpediente(casoId, usuario, accion);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando el medicamento al expediente:  El error es: {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigoMedicamento) {
        String respuesta = null;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        try {
            MedicamentosCaso getMedicamento = medicamentoJpa.findMedicamentosCaso(Integer.parseInt(codigoMedicamento));
            respuesta = getMedicamento.getCodigoMedicamento() + "#" + getMedicamento.getFechaMedicamento()+ "#" + getMedicamento.getMedicamentosCodigoMedicamento()
                    + "#" + getMedicamento.getDosificacion() + "#" + getMedicamento.getCantidad() + "#" + getMedicamento.getComentario();
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error consultando el medicamento:  {0} El error es: {1}", new Object[]{codigoMedicamento, e});
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) throws Exception {
        String respuesta;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String casoId = request.getParameter("casoId");
        String idMedicamento = request.getParameter("idMedicamento");
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String dosificacion = request.getParameter("dosificacion");
        String cantidadMedicamento = request.getParameter("cantidad");
        String comentario = request.getParameter("comentario");
        String usuario = request.getParameter("usuario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String fechaMedicamento = request.getParameter("fechaMedicamento");
        MedicamentosCaso medicamentoUpdate;
        String accion = "Se modificar el medicamento al expediente " + idMedicamento;

        try {
            MedicamentosCaso getMedicamento = medicamentoJpa.findMedicamentosCaso(Integer.parseInt(idMedicamento));
            String fechaActual = accionesExpediente.getFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);

            if (!rutaArchivo.equals("")) {
                medicamentoUpdate = new MedicamentosCaso(Integer.parseInt(idMedicamento), casoId, fechaMedicamento, codigoMedicamento,
                        dosificacion, cantidadMedicamento, comentario, usuario, nombreUsuario, nombreArchivo,
                        rutaArchivo, fechaActual);
            } else {
                medicamentoUpdate = new MedicamentosCaso(Integer.parseInt(idMedicamento), casoId, fechaMedicamento, codigoMedicamento,
                        dosificacion, cantidadMedicamento, comentario, usuario, nombreUsuario, getMedicamento.getNombreArchivo(),
                        getMedicamento.getRutaArchivo(), fechaActual);
            }
            medicamentoJpa.edit(medicamentoUpdate);
            accionesExpediente.guardarAccionesExpediente(getMedicamento.getCasoPersonaIdCaso(), usuario, accion);
            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error modificando el medicamento al expediente:  {0} El error es: {1}", new Object[]{casoId, e});
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
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando el medicamento al expediente:  {0} El error es: {1}", new Object[]{codigoMedicamento, e});
            resultado = "Error";
        }
        return resultado;
    }

    private String eliminarArchivo(String codigoMedicamento) {
        String respuesta;
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String acciones = "Se Eliminar el archivo de la medicacion";
        try {

            String fechaActual = accionesExpediente.getFecha();
            MedicamentosCaso getMedicamento = medicamentoJpa.findMedicamentosCaso(Integer.parseInt(codigoMedicamento));

            MedicamentosCaso eliminarArchivo = new MedicamentosCaso(Integer.parseInt(codigoMedicamento), getMedicamento.getCasoPersonaIdCaso(), getMedicamento.getFechaMedicamento(),
                    getMedicamento.getMedicamentosCodigoMedicamento(), getMedicamento.getDosificacion(), getMedicamento.getCantidad(), getMedicamento.getComentario(),
                    getMedicamento.getUsuarioCedula(), getMedicamento.getNombrePersona(), "", "", fechaActual);
            medicamentoJpa.edit(eliminarArchivo);
            accionesExpediente.guardarAccionesExpediente(getMedicamento.getCasoPersonaIdCaso(), getMedicamento.getUsuarioCedula(), acciones + codigoMedicamento);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando un archivo al proceso de reclamacion:  {0} El error es: {1}", new Object[]{codigoMedicamento, e});
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

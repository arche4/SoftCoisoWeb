/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
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

        String btnCrear = request.getParameter("btnCrearReclamacion");
        String btnModificar = request.getParameter("btnModificarReclamacion");
        String btnConsultar = request.getParameter("btnConsultarReclamacion");
        String btnEliminar = request.getParameter("btnEliminarReclamacion");
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
            if (btnEliminarArchivo != null) {
                String deleteArchivo = eliminarArchivo(btnEliminarArchivo);
                out.print(deleteArchivo);
            }
        }
    }

    private String crear(HttpServletRequest request) {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        String comentarioReclamacion = request.getParameter("comentario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String casoid = request.getParameter("casoId");
        String usuario = request.getParameter("usuario");
        String accion = "Se agregar un proceso de reclamación al expediente";
        try {
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            String fechaActual = accionesExpediente.getFecha();
            ProcesoReclamacion reclamacionCreate = new ProcesoReclamacion(comentarioReclamacion, nombreArchivo, rutaArchivo,
                    casoid, usuario, nombreUsuario, fechaActual, fechaActual);
            reclamacionJpa.create(reclamacionCreate);
            accionesExpediente.guardarAccionesExpediente(casoid, usuario, accion);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando la reclamacion:  El error es: {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultar(String codigoReclamacion) {
        String respuesta = null;
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        try {
            ProcesoReclamacion getReclamacion = reclamacionJpa.findProcesoReclamacion(Integer.parseInt(codigoReclamacion));
            respuesta = getReclamacion.getCodigo() + "#" + getReclamacion.getComentarios();
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error consultando la reclamacion:  {0} El error es: {1}", new Object[]{codigoReclamacion, e});
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) throws Exception {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        String codigo = request.getParameter("codigo");
        String comentario = request.getParameter("comentario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String casoId = request.getParameter("casoId");
        String usuario = request.getParameter("usuario");
        String accion = "Se modifica el proceso de reclamación" + codigo + " al expediente";
        try {
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            String fechaActual = accionesExpediente.getFecha();
            ProcesoReclamacion getReclamacion = reclamacionJpa.findProcesoReclamacion(Integer.parseInt(codigo));
            ProcesoReclamacion reclamacionCreate;
            if (!rutaArchivo.equals("")) {
                reclamacionCreate = new ProcesoReclamacion(Integer.parseInt(codigo), comentario, nombreArchivo, rutaArchivo,
                        casoId, usuario, nombreUsuario, getReclamacion.getFechaCreacion(), fechaActual);
            } else {
                reclamacionCreate = new ProcesoReclamacion(Integer.parseInt(codigo), comentario, getReclamacion.getNombreArchivo(),
                        getReclamacion.getRutaArchivos(), casoId, usuario, nombreUsuario, getReclamacion.getFechaCreacion(), fechaActual);
            }
            reclamacionJpa.edit(reclamacionCreate);
            accionesExpediente.guardarAccionesExpediente(casoId, usuario, accion);
            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error modificando la reclamacion:  {0} El error es: {1}", new Object[]{codigo, e});
            respuesta = "Error";
        }

        return respuesta;

    }

    private String eliminar(String codigoReclamacion) {
        String resultado;
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        try {
            reclamacionJpa.destroy(Integer.parseInt(codigoReclamacion));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error eliminando la reclamacion:  {0} El error es: {1}", new Object[]{codigoReclamacion, e});
            resultado = "Error";
        }
        return resultado;
    }

    private String eliminarArchivo(String codigoReclamacion) {
        String respuesta;
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String acciones = "Se Eliminar el archivo de la calificación";
        try {

            String fechaActual = accionesExpediente.getFecha();
            ProcesoReclamacion getReclamacion = reclamacionJpa.findProcesoReclamacion(Integer.parseInt(codigoReclamacion));

            ProcesoReclamacion eliminarArchivo = new ProcesoReclamacion(Integer.parseInt(codigoReclamacion), getReclamacion.getComentarios(), "",
                    "", getReclamacion.getCasoPersonaIdCaso(), getReclamacion.getUsuarioCedula(), getReclamacion.getNombreUsuario(), getReclamacion.getFechaCreacion(), fechaActual);
            reclamacionJpa.edit(eliminarArchivo);
            accionesExpediente.guardarAccionesExpediente(getReclamacion.getCasoPersonaIdCaso(), getReclamacion.getUsuarioCedula(), acciones + getReclamacion.getCodigo());
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando un archivo al proceso de reclamacion:  {0} El error es: {1}", new Object[]{codigoReclamacion, e});
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

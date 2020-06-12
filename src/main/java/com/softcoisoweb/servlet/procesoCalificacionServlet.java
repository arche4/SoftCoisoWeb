/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.ProcesoCalificacionJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
public class procesoCalificacionServlet extends HttpServlet {

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
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String nombreProceso = request.getParameter("proceso");
        String comentarioProceso = request.getParameter("comentarioProceso");
        String nombreArchivoProceso = request.getParameter("nombreArchivoProceso");
        String rutaArchivoProceso = request.getParameter("rutaArchivoProceso");
        String usuarioProceso = request.getParameter("usuarioProceso");
        String casoIdProceso = request.getParameter("casoIdProceso");
        try {

            String fechaActual = accionesExpediente.getFecha();

            Usuario usuario = usuarioJpa.findUsuario(usuarioProceso);
            String nombreUsuario = usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario();

            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(nombreProceso, comentarioProceso, nombreArchivoProceso,
                    rutaArchivoProceso, usuarioProceso, nombreUsuario, casoIdProceso, fechaActual, fechaActual);
            procesoJpa.create(procesoCreate);
            accionesExpediente.guardarAccionesExpediente(casoIdProceso, usuarioProceso, "Se agrega un proceso de calificación");
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando el proceso de calificacion:  {0} El error es: {1}", new Object[]{nombreProceso, e});
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
            LOGGER.log(Level.SEVERE, "Error consultando al proceso:  {0} El error es: {1}", new Object[]{codigoProceso, e});
        }

        return respuesta;
    }

    private String modificar(HttpServletRequest request) {
        String respuesta;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String codigoProceso = request.getParameter("codigoProceso");
        String nombreProceso = request.getParameter("proceso");
        String comentarioProceso = request.getParameter("comentarioProceso");
        String usuario = request.getParameter("usuarioProceso");
        String nombreArchivo = request.getParameter("nombreArchivoProceso");
        String rutaArchivo = request.getParameter("rutaArchivoProceso");

        try {
            String fechaActual = accionesExpediente.getFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);

            ProcesoCalificacion procesoCreate;

            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            if (!rutaArchivo.equals("")) {
                procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), nombreProceso, comentarioProceso, nombreArchivo,
                        rutaArchivo, usuario, nombreUsuario, getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            } else {
                procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), nombreProceso, comentarioProceso, getProceso.getNombreArchivo(),
                        getProceso.getRutaArchivo(), usuario, nombreUsuario, getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            }

            procesoJpa.edit(procesoCreate);
            accionesExpediente.guardarAccionesExpediente(getProceso.getCasoPersonaIdCaso(), usuario, "Se modifica el proceso de calificación" + nombreProceso);

            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando el proceso de calificacion:  {0} El error es: {1}", new Object[]{nombreProceso, e});
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
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando el proceso de calificacion:  {0} El error es: {1}", new Object[]{codigoProceso, e});
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
            LOGGER.log(Level.SEVERE, "Se presento un error consultando el archivo del proceso:  {0} El error es: {1}", new Object[]{idProceso, e});
        }
        return respuesta;
    }

    private String agregarArchivo(HttpServletRequest request) {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        String codigoProceso = request.getParameter("codigoProceso");
        String nombreArchivo = request.getParameter("nombreArchivoProceso");
        String rutaArchivoProceso = request.getParameter("rutaArchivoProceso");
        String usuarioProceso = request.getParameter("usuarioProceso");
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        try {

            String fechaActual = accionesExpediente.getFecha();
            Usuario usuario = usuarioJpa.findUsuario(usuarioProceso);
            String nombreUsuario = usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario();

            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), getProceso.getProceso(), getProceso.getComentario(), nombreArchivo,
                    rutaArchivoProceso, usuarioProceso, nombreUsuario, getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            procesoJpa.edit(procesoCreate);
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error agregar un archivo al proceso de calificacion: " + codigoProceso + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String eliminarArchivo(String codigoProceso) {
        String respuesta;
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();

        try {

            String fechaActual = accionesExpediente.getFecha();
            ProcesoCalificacion getProceso = procesoJpa.findProcesoCalificacion(Integer.parseInt(codigoProceso));
            ProcesoCalificacion procesoCreate = new ProcesoCalificacion(Integer.parseInt(codigoProceso), getProceso.getProceso(), getProceso.getComentario(), "",
                    "", getProceso.getUsuarioCedula(), getProceso.getNombreUsuario(), getProceso.getCasoPersonaIdCaso(), getProceso.getFechaCreacion(), fechaActual);
            procesoJpa.edit(procesoCreate);
            accionesExpediente.guardarAccionesExpediente(getProceso.getCasoPersonaIdCaso(), getProceso.getUsuarioCedula(), "Se Eliminar el archivo del proceso de calificación" + getProceso.getProceso());
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando un archivo al proceso de calificacion:  {0} El error es: {1}", new Object[]{codigoProceso, e});
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

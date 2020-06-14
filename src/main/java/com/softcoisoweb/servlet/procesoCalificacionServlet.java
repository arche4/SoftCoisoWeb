/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.CalificacionJpaController;
import com.softcoisoweb.controller.ProcesoCalificacionJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Calificacion;
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
        String btnEliminarArchivo = request.getParameter("btnEliminarArchivo");
        String btnCrearCalificacion = request.getParameter("btnCrearCalificacion");
        String btnConsultarCalificacion = request.getParameter("btnConsultarCalificacion");
        String btnModificarCalificacion = request.getParameter("btnModificarCalificacion");
        String btnEliminarCalificacion = request.getParameter("btnEliminarCalificacion");

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
            if (btnEliminarArchivo != null) {
                String deleteArchivo = eliminarArchivo(btnEliminarArchivo);
                out.print(deleteArchivo);
            }
            if (btnCrearCalificacion != null && btnCrearCalificacion.equals("ok")) {
                String crear = crearCalificacion(request);
                out.print(crear);
            }
            if (btnConsultarCalificacion != null) {
                String datos = consultarCalificacion(btnConsultarCalificacion);
                out.print(datos);
            }
            if (btnModificarCalificacion != null && btnModificarCalificacion.equals("ok")) {
                String modificar = modificarCalificacion(request);
                out.print(modificar);
            }
            if (btnEliminarCalificacion != null) {
                String deleteArchivo = eliminarCalificacion(btnEliminarCalificacion);
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

    private String crearCalificacion(HttpServletRequest request) {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        CalificacionJpaController calificacionJpa = new CalificacionJpaController(JPAFactory.getFACTORY());

        String casoId = request.getParameter("casoId");
        String usuario = request.getParameter("usuario");
        String diagnostico = request.getParameter("diagnostico");
        String porcentaje = request.getParameter("porcentaje");
        String comentario = request.getParameter("comentario");
        String nombreArchivo = request.getParameter("nombreArchivoProceso");
        String rutaArchivo = request.getParameter("rutaArchivoProceso");
        String accion = "Se crea la calificación del expediente" + casoId;

        try {
            String fechaActual = accionesExpediente.getFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);

            Calificacion calificacion = new Calificacion(diagnostico, porcentaje, comentario, nombreArchivo, rutaArchivo, usuario, nombreUsuario, casoId, fechaActual, fechaActual);
            calificacionJpa.create(calificacion);
            accionesExpediente.guardarAccionesExpediente(casoId, usuario, accion);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error creando la calificación del expediente :  {0} El error es: {1}", new Object[]{casoId, e});
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultarCalificacion(String codigo) {
        String respuesta = null;
        CalificacionJpaController calificacionJpa = new CalificacionJpaController(JPAFactory.getFACTORY());
        try {
            Calificacion getCalificacion = calificacionJpa.findCalificacion(Integer.parseInt(codigo));
            respuesta = getCalificacion.getCodigo() + "#" + getCalificacion.getDiagnostico() + "#" + getCalificacion.getPorcentaje()
                    + "#" + getCalificacion.getComentario();
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando la calificación, el error es :  {0}", new Object[]{e});
        }

        return respuesta;
    }

    private String modificarCalificacion(HttpServletRequest request) {
        String respuesta;
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        CalificacionJpaController calificacionJpa = new CalificacionJpaController(JPAFactory.getFACTORY());
        String codigo = request.getParameter("codigoCalificacion");
        String cedulaUsuario = request.getParameter("usuario");
        String diagnostico = request.getParameter("DiagnosticoCalificacion");
        String porcentaje = request.getParameter("porcentaje");
        String comentario = request.getParameter("comentario");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");

        try {
            String fechaActual = accionesExpediente.getFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(cedulaUsuario);

            Calificacion getCalificacion = calificacionJpa.findCalificacion(Integer.parseInt(codigo));
            Calificacion calificacion;
            if (!rutaArchivo.equals("")) {
                calificacion = new Calificacion(Integer.parseInt(codigo), diagnostico, porcentaje, comentario, nombreArchivo,
                        rutaArchivo, cedulaUsuario, nombreUsuario, getCalificacion.getCasoPersonaIdCaso(), getCalificacion.getFechaCalificacion(), fechaActual);
            } else {
                calificacion = new Calificacion(Integer.parseInt(codigo), diagnostico, porcentaje, comentario, getCalificacion.getArchivoNombre(),
                        getCalificacion.getArchivoRuta(), cedulaUsuario, nombreUsuario, getCalificacion.getCasoPersonaIdCaso(), getCalificacion.getFechaCalificacion(), fechaActual);
            }
            calificacionJpa.edit(calificacion);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error modificando la calificación, el error es :  {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }
    
    private String eliminarCalificacion(String codigo) {
        String resultado;
        CalificacionJpaController calificacionJpa = new CalificacionJpaController(JPAFactory.getFACTORY());
        try {
            calificacionJpa.destroy(Integer.parseInt(codigo));
            resultado = "Exitoso";
        } catch (NonexistentEntityException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando el proceso de calificacion:  {0} El error es: {1}", new Object[]{codigo, e});
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.CalificacionJpaController;
import com.softcoisoweb.controller.CambioEstadoHistoricoJpaController;
import com.softcoisoweb.controller.CasoArchivoJpaController;
import com.softcoisoweb.controller.CasoComentarioJpaController;
import com.softcoisoweb.controller.CasoPersonaJpaController;
import com.softcoisoweb.controller.CitasJpaController;
import com.softcoisoweb.controller.EstadoCasoJpaController;
import com.softcoisoweb.controller.FlujoCasoJpaController;
import com.softcoisoweb.controller.MedicamentosCasoJpaController;
import com.softcoisoweb.controller.PersonaJpaController;
import com.softcoisoweb.controller.ProcesoCalificacionJpaController;
import com.softcoisoweb.controller.ProcesoReclamacionJpaController;
import com.softcoisoweb.controller.SeguimientoCasoJpaController;
import com.softcoisoweb.controller.TipoCasoJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Calificacion;
import com.softcoisoweb.model.CambioEstadoHistorico;
import com.softcoisoweb.model.CasoArchivo;
import com.softcoisoweb.model.CasoComentario;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Citas;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.SeguimientoCaso;
import com.softcoisoweb.model.TipoCaso;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
public class ExpedienteServlet extends HttpServlet {

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

        String verExpediente = request.getParameter("verExpediente");
        String btnCambiarEstado = request.getParameter("btnCambiarEstado");
        String btnComentar = request.getParameter("btnComentar");
        String btnEliminarComentario = request.getParameter("btnEliminarComentario");
        String btnConsultarCometario = request.getParameter("btnConsultarCometario");
        String btnModificarComentario = request.getParameter("btnModificarComentario");
        String btnAsginarUsuario = request.getParameter("btnAsginarUsuario");
        String btnConsultarArchivo = request.getParameter("btnConsultarArchivo");

        try ( PrintWriter out = response.getWriter()) {
            if (verExpediente != null && !verExpediente.equals("")) {
                String codigoCaso = verExpediente;
                buscarExpediente(request, response, codigoCaso, "No");
            }
            if (btnCambiarEstado != null && btnCambiarEstado.equals("ok")) {
                String estado = cambiarEstado(request, response);
                out.print(estado);
            }
            if (btnComentar != null && btnComentar.equals("ok")) {
                String comentario = guardarComentario(request, response);
                out.print(comentario);
            }
            if (btnModificarComentario != null && btnModificarComentario.equals("ok")) {
                String comentarioMod = modificarComentario(request);
                out.print(comentarioMod);
            }
            if (btnEliminarComentario != null && !btnEliminarComentario.equals("")) {
                String eliminarComentario = eliminarComentario(btnEliminarComentario);
                out.print(eliminarComentario);
            }
            if (btnConsultarCometario != null && !btnConsultarCometario.equals("")) {
                String consultarComentario = consultarComentario(btnConsultarCometario);
                out.print(consultarComentario);
            }
            if (btnAsginarUsuario != null && btnAsginarUsuario.equals("ok")) {
                String asignarUsuario = asignarUsuario(request);
                out.print(asignarUsuario);
            }
            if (btnConsultarArchivo != null && !btnConsultarArchivo.equals("")) {
                String consultarArchivo = consultarArchivo(request, btnConsultarArchivo);
                out.print(consultarArchivo);
            }

        }
    }

    public void buscarExpediente(HttpServletRequest request, HttpServletResponse response, String codigoCaso, String cargar) throws ServletException, IOException {
        CasoPersonaJpaController casojpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        CasoComentarioJpaController comentarioJpa = new CasoComentarioJpaController(JPAFactory.getFACTORY());
        CasoArchivoJpaController archivoJpa = new CasoArchivoJpaController(JPAFactory.getFACTORY());
        CitasJpaController citasJpa = new CitasJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController seguimientoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());
        ProcesoCalificacionJpaController procesoJpa = new ProcesoCalificacionJpaController(JPAFactory.getFACTORY());
        PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
        ProcesoReclamacionJpaController reclamacionJpa = new ProcesoReclamacionJpaController(JPAFactory.getFACTORY());
        MedicamentosCasoJpaController medicamentoJpa = new MedicamentosCasoJpaController(JPAFactory.getFACTORY());
        CalificacionJpaController calificacionJpa = new CalificacionJpaController(JPAFactory.getFACTORY());

        CasoPersona caso = casojpa.findCasoPersona(codigoCaso);
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;

        try {
            List<CasoComentario> listComentarios = comentarioJpa.casoXcomentario(codigoCaso);
            List<CasoArchivo> listArchivos = archivoJpa.casoXarchivo(codigoCaso);
            List<Citas> listCita = citasJpa.citasXCaso(caso.getPersonaCedula());
            List<ProcesoCalificacion> listProceso = procesoJpa.procesoXexpediente(codigoCaso);
            List<ProcesoReclamacion> listReclamacion = reclamacionJpa.reclamacionxExpediente(codigoCaso);
            List<MedicamentosCaso> listMedicamentosCasos = medicamentoJpa.medicamentoXexpediente(codigoCaso);
            List<SeguimientoCaso> listSeguimiento = seguimientoJpa.seguimientoExpediente(codigoCaso);
            List<Calificacion> listCalificacion = calificacionJpa.calificacionExpediente(codigoCaso);

            TipoCaso tipoCaso = tipoCasoJpa.findTipoCaso(caso.getTipoCasoCodigoTipoCaso());
            FlujoCaso flujo = flujoJpa.findFlujoCaso(codigoCaso);
            EstadoCaso estado = estadoJpa.findEstadoCaso(flujo.getEstadoCasoCodigoEstado());

            //Usuarios que gestionan el expediente
            Usuario creadoPor = usuarioJpa.findUsuario(caso.getCreadoPor());
            Usuario asignado = usuarioJpa.findUsuario(caso.getAsignado());

            String usuarioInformador = creadoPor.getNombreUsuario() + " " + creadoPor.getApellidoUsuario();
            String usuarioAsignado = asignado.getNombreUsuario() + " " + asignado.getApellidoUsuario();
            Persona persona = Personajpa.findPersona(caso.getPersonaCedula());
            String nombrePersona = persona.getNombrePersona() + " " + persona.getApellidoPersona();

            session.setAttribute("Expediente", caso);
            session.setAttribute("listComentario", listComentarios);
            session.setAttribute("listArchivos", listArchivos);
            session.setAttribute("PersonaNombre", nombrePersona);
            session.setAttribute("nombreTipoCaso", tipoCaso.getTipoCaso());
            session.setAttribute("nombreEstado", estado.getNombreEstado());
            session.setAttribute("creadoPor", usuarioInformador);
            session.setAttribute("Asignado", usuarioAsignado);
            session.setAttribute("FlujoCaso", flujo);
            session.setAttribute("listCitas", listCita);
            session.setAttribute("listProceso", listProceso);
            session.setAttribute("listReclamacion", listReclamacion);
            session.setAttribute("listMedicamentosCasos", listMedicamentosCasos);
            session.setAttribute("ListSeguimiento", listSeguimiento);
            session.setAttribute("listCalificacion", listCalificacion);
            LOGGER.log(Level.SEVERE, "Prueba logger imprimir logs: {0}", new Object[]{listCalificacion});

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error buscando el expediente  del caso, el error es: {0}", new Object[]{e});

            System.err.println("Error buscando el expediente  del caso, el error es: " + e);
        }
        if (!cargar.equals("Cargar")) {
            rd = request.getRequestDispatcher("views/expediente.jsp");
        }
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    public String cambiarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String resultado;
        String estado = request.getParameter("codigoEstado");
        String comentarioEstado = request.getParameter("comentarioEstado");
        String casoid = request.getParameter("casoid");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String cedulaUsuario = request.getParameter("cedulaUsuario");
        String nombreArchivo = request.getParameter("nombreArchivo");

        try {
            resultado = guardarAcciones(casoid, cedulaUsuario, estado, comentarioEstado, "Estado",
                    nombreArchivo, rutaArchivo, "", "");
        } catch (ExceptionInInitializerError e) {
            System.err.println("Se presento un error cambiando el estado del expediente: " + casoid + " El Error es: " + e);
            resultado = "2";
        }
        return resultado;
    }

    private String gestionCaso(String casoId, String usuarioCedula, String fechaActual, String codigoEstado, String accion, String nombreUsuario) {
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController seguimientoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());
        String respuesta;
        try {
            SeguimientoCaso seguimiento = new SeguimientoCaso(casoId, accion, usuarioCedula, nombreUsuario, fechaActual);
            flujoJpa.actualizarFlujoCaso(casoId, fechaActual, codigoEstado, usuarioCedula);
            seguimientoJpa.create(seguimiento);
            respuesta = "Exitoso";
        } catch (NonexistentEntityException e) {
            System.err.println("Se presento un error cambiando actualizando el expediente: " + casoId + " El Error es: " + e);
            respuesta = "Error";
        }

        return respuesta;
    }

    private String guardarComentario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String respuesta;
        String casoId = request.getParameter("casoComentario");
        String comentarioUsuario = request.getParameter("comentarioUsuario");
        String comentarioExpediente = request.getParameter("comentarioExpediente");
        try {
            respuesta = guardarAcciones(casoId, comentarioUsuario, "", comentarioExpediente, "Comentario",
                    "", "", "", "");
        } catch (IOException | ServletException e) {
            System.err.println("Se presento un error agregando comentario al expediente: " + casoId + " El Error es: " + e);
            respuesta = "2";
        }
        return respuesta;
    }

    private String guardarAcciones(
            String casoId, String usuarioCedula, String codigoEstado, String comentario,
            String operacion, String nombreArchivo, String rutaArchivo, String codigoComentario, String usuarioResponsable) throws ServletException, IOException, Exception {
        String resultado = null;
        CasoPersonaJpaController caso = new CasoPersonaJpaController(JPAFactory.getFACTORY());
        CasoComentarioJpaController comentarioJpa = new CasoComentarioJpaController(JPAFactory.getFACTORY());
        CasoArchivoJpaController archivoJpa = new CasoArchivoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        CambioEstadoHistoricoJpaController estadoHistoricoJpa = new CambioEstadoHistoricoJpaController(JPAFactory.getFACTORY());
        String fecha, fecha_Actual;
        CasoComentario comentarioC;
        CasoArchivo archivo;
        try {
            Usuario usuario = usuarioJpa.findUsuario(usuarioCedula);
            String fechaActual = obtenerFechaActual();
            String horaActual = obtenerHoraActual();
            SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatterFecha.parse(fechaActual);
            fecha = formatterFecha2.format(date);
            fecha_Actual = fecha + " " + horaActual;
            String nombreUsuario = usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario();
            switch (operacion) {
                case "Comentario":
                    comentarioC = new CasoComentario(casoId, comentario, usuarioCedula, nombreUsuario, fecha_Actual);
                    comentarioJpa.create(comentarioC);
                    resultado = "0";
                    break;
                case "Estado":
                    if (!comentario.equals("")) {
                        comentarioC = new CasoComentario(casoId, comentario, usuarioCedula, nombreUsuario, fecha_Actual);
                        comentarioJpa.create(comentarioC);
                    }
                    if (!rutaArchivo.equals("")) {
                        archivo = new CasoArchivo(casoId, usuarioCedula, nombreUsuario, nombreArchivo, rutaArchivo, fecha_Actual);
                        archivoJpa.create(archivo);
                    }
                    CambioEstadoHistorico historico = new CambioEstadoHistorico(casoId, usuarioCedula, nombreUsuario, comentario, codigoEstado, fecha_Actual);
                    estadoHistoricoJpa.create(historico);
                    String accion = "Se cambia el caso del expediente ";
                    String seguimiento = gestionCaso(casoId, usuarioCedula, fecha_Actual, codigoEstado, accion, nombreUsuario);

                    if (seguimiento.equals("Exitoso")) {
                        resultado = "0";
                    } else {
                        resultado = "1";
                    }
                    break;
                case "ModificarComentario":
                    comentarioC = new CasoComentario(Integer.parseInt(codigoComentario), casoId, comentario, usuarioCedula, nombreUsuario, fecha_Actual);
                    comentarioJpa.edit(comentarioC);
                    break;
                case "AsignarUsuario":
                    try {
                    caso.asignarUsuario(casoId, usuarioResponsable);
                    if (!comentario.equals("")) {
                        comentarioC = new CasoComentario(casoId, comentario, usuarioCedula, nombreUsuario, fecha_Actual);
                        comentarioJpa.create(comentarioC);
                    }
                    AccionesExpediente accionesExpediente = new AccionesExpediente();
                    String acciones = "Se cambia el responsable del expediente";
                    accionesExpediente.guardarAccionesExpediente(casoId, usuarioCedula, acciones);
                    resultado = "0";
                } catch (NonexistentEntityException e) {
                    System.err.println("Se presento un error cambiando el usuario del expediente: " + casoId + " El Error es: " + e);
                }
                break;
            }
        } catch (ParseException e) {
            System.err.println("Se presento un error guardando acciones del expediente: " + casoId + " El Error es: " + e);
            resultado = "2";
        }
        return resultado;
    }

    private String eliminarComentario(String codigoComentario) {
        String respuesta;
        CasoComentarioJpaController comentarioJpa = new CasoComentarioJpaController(JPAFactory.getFACTORY());
        try {
            comentarioJpa.destroy(Integer.parseInt(codigoComentario));
            respuesta = "Exitoso";
        } catch (NonexistentEntityException e) {
            System.err.println("Se presento un error eliminando  comentario del expediente: " + codigoComentario + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    private String consultarComentario(String codigoComentario) {
        String respuesta = null;
        CasoComentarioJpaController comentarioJpa = new CasoComentarioJpaController(JPAFactory.getFACTORY());
        try {
            CasoComentario comentario = comentarioJpa.findCasoComentario(Integer.parseInt(codigoComentario));
            respuesta = comentario.getCodigo().toString() + "#" + comentario.getCodigoCaso() + "#" + comentario.getComentario();
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error consultando comentario del expediente: " + codigoComentario + " El Error es: " + e);
        }
        return respuesta;
    }

    private String modificarComentario(HttpServletRequest request) {
        String respuesta;
        String casoId = request.getParameter("codigoCaso");
        String comentario = request.getParameter("comentario");
        String codigoComentario = request.getParameter("codigoComentario");
        String cedulaUsuario = request.getParameter("usuario");

        try {
            guardarAcciones(casoId, cedulaUsuario, "", comentario, "ModificarComentario", "", "", codigoComentario, "");
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.err.println("Se presento un error modificar el  comentario del expediente: " + casoId + " El Error es: " + e);
            respuesta = "Error";
        }
        return respuesta;
    }

    public String asignarUsuario(HttpServletRequest request) {
        String resultado;
        String casoId = request.getParameter("casoUsuer");
        String cedulaUsuario = request.getParameter("usuarioGestor");
        String usuarioResponsable = request.getParameter("usuarioRespo");
        String comentario = request.getParameter("comentarioAsig");
        try {
            resultado = guardarAcciones(casoId, cedulaUsuario, "", comentario, "AsignarUsuario", "", "", "", usuarioResponsable);
        } catch (Exception e) {
            System.err.println("Se presento un error cambiando el usuario del expediente: " + casoId + " El Error es: " + e);
            resultado = "2";
        }
        return resultado;
    }

    private String consultarArchivo(HttpServletRequest request, String idArchivo) {
        String resultado = null;
        CasoArchivoJpaController archivoJpa = new CasoArchivoJpaController(JPAFactory.getFACTORY());
        try {
            CasoArchivo archivo = archivoJpa.findCasoArchivo(Integer.parseInt(idArchivo));
            resultado = archivo.getArchivoNombre() + "," + archivo.getArchivoRuta();
        } catch (NumberFormatException e) {
            System.err.println("Se presento un error consultando el archivo del expediente: " + idArchivo + " El Error es: " + e);
        }
        return resultado;
    }

    private String obtenerFechaActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturar_fecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.DATE)));
    }

    private String agregarCerosIzquierda(final String diames) {
        final StringBuffer retorno = new StringBuffer();
        if (Integer.parseInt(diames) < 10) {
            final String add = "0" + diames;
            retorno.append(add);
        } else {
            retorno.append(diames);
        }
        return retorno.toString();
    }

    private String obtenerHoraActual() {
        final Time sqlTime = new Time(new java.util.Date().getTime());
        return sqlTime.toString();
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
            Logger.getLogger(ExpedienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExpedienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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

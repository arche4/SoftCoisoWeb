/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.EnviarCorreo;
import com.softcoisoweb.conexion.OperacionesBD;
import com.softcoisoweb.controller.FormacionJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Formacion;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author manue
 */
@WebServlet(name = "FormacionServlet", urlPatterns = {"/FormacionServlet"})
public class FormacionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("LogsErrores");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        String btnCrear = request.getParameter("btnCrearFormacion");
        String btnModificar = request.getParameter("btnModificarFormacion");
        String btnEliminar = request.getParameter("btnEliminarFormacion");
        String btnConsultar = request.getParameter("btnConsultarFormacion");
        String btnEliminarArchivo = request.getParameter("btnEliminarArchivo");

        try ( PrintWriter out = response.getWriter()) {

            if (btnCrear != null && btnCrear.equals("ok")) {
                String guardar = guardatos(request);
                out.print(guardar);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modificar = modificar(request);
                out.print(modificar);
            }
            if (btnEliminar != null && btnEliminar.equals("ok")) {
                String eliminar = eliminar(request);
                out.print(eliminar);
            }
            if (btnEliminarArchivo != null) {
                String eliminar = eliminarArchivo(btnEliminarArchivo);
                out.print(eliminar);
            }
            if (btnConsultar != null) {
                String consultar = getCosultaCita(btnConsultar);
                out.print(consultar);
            } else {
                JSONArray cargar = cargarDatos(request);
                out.print(cargar);
            }

        }
    }

    private String guardatos(HttpServletRequest request) {
        String respuesta;
        FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());
        String tipoFormacion = request.getParameter("tipoFormacion");
        String ano = request.getParameter("ano");
        String mes = request.getParameter("mes");
        String dia = request.getParameter("dia");
        String hora = request.getParameter("iniHora");
        String finHora = request.getParameter("finHora");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String nombreFormador = request.getParameter("nombreFormador");
        String tema = request.getParameter("tema");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String correoFormador = request.getParameter("correoFormador");
        String numeroAsistentes = request.getParameter("nAsistentes");
        String usuario = request.getParameter("usuario");
        String emailUsuario = request.getParameter("emailUsuario");

        try {
            Formacion formacion = new Formacion(tipoFormacion, Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia),
                    hora, finHora, titulo, descripcion, nombreFormador, tema, nombreArchivo, rutaArchivo, correoFormador,
                    numeroAsistentes, usuario, "Creada");
            formacionJpa.create(formacion);
            respuesta = "Exitosa";
            String asuntoCorreo = "Invitación: " + titulo;
            String descripcionCorreo = "Te han invitado a una formación";
            enviarCorreo(ano, mes, dia, hora, finHora, asuntoCorreo, descripcionCorreo, descripcion, correoFormador, emailUsuario);
        } catch (IOException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error creando la formacion, el error es:  {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    private String modificar(HttpServletRequest request) throws ParseException {
        String respuesta;
        FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());
        String idFormacion = request.getParameter("idFormacion");
        String tipoFormacion = request.getParameter("tipoFormacion");
        String hora = request.getParameter("IniHora");
        String finHora = request.getParameter("FinHora");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String nombreFormador = request.getParameter("nombreFormador");
        String tema = request.getParameter("tema");
        String nombreArchivo = request.getParameter("nombreArchivo");
        String rutaArchivo = request.getParameter("rutaArchivo");
        String correoFormador = request.getParameter("correoFormador");
        String numeroAsistentes = request.getParameter("nAsistentes");
        String usuario = request.getParameter("usuario");
        String emailUsuario = request.getParameter("emailUsuario");
        Formacion formacion;

        try {
            Formacion getFormacion = formacionJpa.findFormacion(Integer.parseInt(idFormacion));
            int ano = getFormacion.getAno();
            int mes = getFormacion.getMes();
            int dia = getFormacion.getDia();
            if (!rutaArchivo.equals("")) {
                formacion = new Formacion(Integer.parseInt(idFormacion), tipoFormacion, ano, mes, dia,
                        hora, finHora, titulo, descripcion, nombreFormador, tema, nombreArchivo, rutaArchivo, correoFormador,
                        numeroAsistentes, usuario, "Modificada");
            } else {
                formacion = new Formacion(Integer.parseInt(idFormacion), tipoFormacion, ano, mes, dia,
                        hora, finHora, titulo, descripcion, nombreFormador, tema, getFormacion.getArchivoNombre(), getFormacion.getRutaArchivo(),
                        correoFormador, numeroAsistentes, usuario, "Modificada");
            }
            try {
                formacionJpa.edit(formacion);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error modificando la formacion, el error es:  {0}", new Object[]{ex});
            }
            Calendar fecha = Calendar.getInstance();
            int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
            int mesActual = fecha.get(Calendar.MONTH) + 1;
            int anoActual = fecha.get(Calendar.YEAR);
            String fechaActual = diaActual + "/" + mesActual + "/" + anoActual;
            String fechaCita = dia + "/" + mes + "/" + ano;
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fechaActual);
            Date fechaDate2 = formateador.parse(fechaCita);
            if (fechaDate1.before(fechaDate2)) {
                String asuntoCorreo = "Modificacion: " + titulo;
                String descripcionCorreo = "Se realizo una modificación en la formación";
                try {
                    enviarCorreo(String.valueOf(ano), String.valueOf(mes), String.valueOf(dia), hora, finHora, asuntoCorreo, descripcionCorreo, descripcion, correoFormador, emailUsuario);
                } catch (IOException ex) {
                    Logger.getLogger(FormacionServlet.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
            respuesta = "Exitosa";
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error modificando la formacion, el error es:  {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    public String getCosultaCita(String idFormacion) {
        String respuesta = null;
        try {
            FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());
            Formacion getFormacion = formacionJpa.findFormacion(Integer.parseInt(idFormacion));
            String horaIni = getFormacion.getHoraInicio();
            String horaFin = getFormacion.getHoraFin();

            String hIni = horaIni.substring(0, 2);
            String mIni = horaIni.substring(2, 4);
            String sIni = horaIni.substring(4, 6);

            String hFin = horaFin.substring(0, 2);
            String mFin = horaFin.substring(2, 4);
            String sFin = horaFin.substring(4, 6);

            String fechaIni = hIni + ":" + mIni + ":" + sIni;
            String fechaFin = hFin + ":" + mFin + ":" + sFin;

            respuesta = getFormacion.getIdFormacion() + "#" + fechaIni + "#" + fechaFin
                    + "#" + getFormacion.getTitulo() + "#" + getFormacion.getDescripcion() + "#" + getFormacion.getNombreFormador()
                    + "#" + getFormacion.getTema() + "#" + getFormacion.getArchivoNombre() + "#" + getFormacion.getRutaArchivo()
                    + "#" + getFormacion.getCorreoFormador() + "#" + getFormacion.getNumeroAsistente() + "#" + getFormacion.getTipoFormacion();

        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error buscando una cita, el error es:  {0}", new Object[]{e});
        }
        return respuesta;
    }

    private String eliminar(HttpServletRequest request) {
        String respuesta;
        FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());
        String codigoFormacion = request.getParameter("codigoFormacion");
        try {
            formacionJpa.destroy(Integer.parseInt(codigoFormacion));
            respuesta = "Exitosa";
        } catch (NonexistentEntityException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Se presento un erro eliminando la formacion:  {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    public JSONArray cargarDatos(HttpServletRequest request) throws IOException {
        JSONArray respuesta = null;
        try {
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String[] fIniVec = start.split("T");
            fIniVec = fIniVec[0].split("-");
            String[] fFinVec = end.split("T");
            fFinVec = fFinVec[0].split("-");
            int anoIni = Integer.parseInt(fIniVec[0]);
            int mesIni = Integer.parseInt(fIniVec[1]);
            int anoFin = Integer.parseInt(fFinVec[0]);
            int mesFin = Integer.parseInt(fFinVec[1]);
            int mesMed = mesIni + 1;
            int anoMed = anoIni;
            if (mesMed > 12) {
                mesMed = 1;
                anoMed++;
            }
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.cargarDatosFormacion(anoIni, mesIni, anoMed, mesMed, anoFin, mesFin);
        } catch (IOException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error consultado las formaciones, el error es:  {0}", new Object[]{e});
        }
        return respuesta;
    }

    private String eliminarArchivo(String codigoFormacion) {
        String respuesta;
        FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());
        try {
            Formacion getFormacion = formacionJpa.findFormacion(Integer.parseInt(codigoFormacion));
            Formacion formacion = new Formacion(Integer.parseInt(codigoFormacion), getFormacion.getTipoFormacion(), getFormacion.getAno(),
                    getFormacion.getMes(), getFormacion.getDia(),getFormacion.getHoraInicio(), getFormacion.getHoraFin(), getFormacion.getTitulo(),
                    getFormacion.getDescripcion(), getFormacion.getNombreFormador(), getFormacion.getTema(), "", "", getFormacion.getCorreoFormador(),
                    getFormacion.getNumeroAsistente(), getFormacion.getUsuarioCedula(),  "Modificada");  
            formacionJpa.edit(formacion);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error eliminando un archivo a la formacion:  {0} El error es: {1}", new Object[]{codigoFormacion, e});
            respuesta = "Error";
        }
        return respuesta;
    }

    public void enviarCorreo(String ano, String mes, String dia, String IniHora, String FinHora,
            String asuntoCorreo, String descripcion, String comentario, String correoPersona, String emailUsuario) throws IOException {
        String correos = correoPersona + "," + emailUsuario;
        EnviarCorreo enviarCorreo = new EnviarCorreo();

        String hIni = IniHora.substring(0, 2);
        String mIni = IniHora.substring(2, 4);
        String sIni = "pm";
        String hFin = FinHora.substring(0, 2);
        String mFin = FinHora.substring(2, 4);
        String sFin = "pm";
        if (Integer.parseInt(hIni) < 12) {
            sIni = "am";
        }
        if (Integer.parseInt(hFin) < 12) {
            sFin = "am";
        }
        String fechaIni = hIni + ":" + mIni + ":" + sIni;
        String fechaFin = hFin + ":" + mFin + ":" + sFin;

        String fecha = dia + "/" + mes + "/" + ano + "  " + fechaIni + "-" + fechaFin;

        try {
            StringBuilder contenidoMensaje = new StringBuilder();
            contenidoMensaje.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"");
            contenidoMensaje.append("<html>");
            contenidoMensaje.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head>");
            contenidoMensaje.append("<body style=\"color: #797979; background: #eaeaea; font-family: 'Ruda', sans-serif;\">");
            contenidoMensaje.append("<img src=\"https://i.ibb.co/ZLnqNXX/Cabeza-Email.png\" alt=\"\" style=\"margin: auto; display: block;\"/>");
            contenidoMensaje.append("<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">");
            contenidoMensaje.append("<td class=\"esd-container-frame\" width=\"600\" valign=\"top\" align=\"center\">");
            contenidoMensaje.append("<table style=\"border-radius: 4px; border-collapse: separate; background-color: #ffffff;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\">");
            contenidoMensaje.append("<tbody><tr>");
            contenidoMensaje.append("<td class=\"esd-block-text es-m-txt-l es-p20t es-p35b es-p30r es-p30l\" bgcolor=\"#ffffff\" align=\"center\">");
            contenidoMensaje.append("<p><br></p><h1>Hola !!! Te enviamos este correo para informarte...</h1>");
            contenidoMensaje.append("<h3><br>");
            contenidoMensaje.append(descripcion);
            contenidoMensaje.append("</h3>");
            contenidoMensaje.append("<h3>Para la fecha: ");
            contenidoMensaje.append(fecha);
            contenidoMensaje.append("</h3><h3><br>");
            contenidoMensaje.append(comentario);
            contenidoMensaje.append("</h3></td></tr></tbody></table></td></table>");
            contenidoMensaje.append("<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\"><td align=\"center\" style=\"padding:0;Margin:0;\"> ");
            contenidoMensaje.append("<table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\"><td align=\"left\" style=\"padding:0;Margin:0;\">");
            contenidoMensaje.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\"><td width=\"600\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\">");
            contenidoMensaje.append("<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:4px;background-color:#111111;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#111111\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\">");
            contenidoMensaje.append("<td align=\"center\" style=\"padding:0;Margin:0;padding-top:30px;padding-left:30px;padding-right:30px;\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#ffffff;\">Corporación colectivo intersindical de salud ocupacional</h3></td>");
            contenidoMensaje.append("</tr><tr style=\"border-collapse:collapse;\">");
            contenidoMensaje.append("<td esdev-links-color=\"#7c72dc\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:30px;padding-left:30px;padding-right:30px;\"><h4 style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:18px;color:#7C72DC;\">En defensa de la salud de los trabajadores</h4></td>");
            contenidoMensaje.append("</tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>");
            contenidoMensaje.append("<br>");
            contenidoMensaje.append("<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\"><td align=\"center\" style=\"padding:0;Margin:0;\">");
            contenidoMensaje.append("<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#C6C2ED;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#c6c2ed\" align=\"center\">");
            contenidoMensaje.append("<tbody>");
            contenidoMensaje.append("<tr style=\"border-collapse:collapse;\">");
            contenidoMensaje.append("<td align=\"left\" style=\"padding:0;Margin:0;\">");
            contenidoMensaje.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\"><td width=\"600\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\">");
            contenidoMensaje.append("<table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;border-radius:4px;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">");
            contenidoMensaje.append("<tbody><tr style=\"border-collapse:collapse;\">");
            contenidoMensaje.append("<td align=\"center\" style=\"padding:0;Margin:0;padding-top:30px;padding-left:30px;padding-right:30px;\"><h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#111111;\">Necesitas más ayuda?</h3></td>");
            contenidoMensaje.append("</tr><tr style=\"border-collapse:collapse;\">");
            contenidoMensaje.append("<td esdev-links-color=\"#7c72dc\" align=\"center\" style=\"padding:0;Margin:0;padding-bottom:30px;padding-left:30px;padding-right:30px;\"><a target=\"_blank\" href=\"http://www.coiso.org/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:18px;text-decoration:underline;color:#7C72DC;\">Visitanos en nuestra pagina oficial</a></td>");
            contenidoMensaje.append("</tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>");
            contenidoMensaje.append("</body></html>");

            enviarCorreo.enviarCorreo(correos, asuntoCorreo, contenidoMensaje);
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, "Error enviando el correo:  {0}", new Object[]{ex});
        }

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

        } catch (ParseException ex) {
            Logger.getLogger(FormacionServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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

        } catch (ParseException ex) {
            Logger.getLogger(FormacionServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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

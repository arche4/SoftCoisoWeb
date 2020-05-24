/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.EnviarCorreo;
import com.softcoisoweb.controller.CitasJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Citas;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.MessagingException;
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
public class CalendarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        PrintWriter out = response.getWriter();

        String btnCrearCita = request.getParameter("btnCrearCita");
        String btnModificarCita = request.getParameter("btnModificarCita");
        String btnEliminarCita = request.getParameter("btnEliminarCita");

        try {
            if (btnCrearCita != null && btnCrearCita.equals("si")) {
                String guardar = guardatos(request, response);
                out.print(guardar);
            }
            if (btnModificarCita != null && btnModificarCita.equals("ok")) {
                String modificar = modificarCita(request, response);
                out.print(modificar);
            }
            if (btnEliminarCita != null && btnEliminarCita.equals("ok")) {
                String eliminar = eliminarCita(request, response);
                out.print(eliminar);
            }
        } finally {
            out.close();
        }

    }

    public String guardatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String respuesta = null;
        RequestDispatcher rd = null;
        CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());
        String codigoPersona = request.getParameter("cedula");
        String nombrePersona = request.getParameter("nombrePersona");
        String emailPersona = request.getParameter("email");
        String horaIni = request.getParameter("iniHora");
        String horaFin = request.getParameter("finHora");
        String emailUsuario = request.getParameter("emailUsuario");
        String cedulaUsuario = request.getParameter("cedulaUsuario");
        String titulo = request.getParameter("titulo");
        String comentario = request.getParameter("comentario");
        String ano = request.getParameter("ano");
        String mes = request.getParameter("mes");
        String dia = request.getParameter("dia");
        //PARA GUARDAR LOS DATOS SE NECESITA VALIDAR QUE LA PERSONA EXISTA.
        try {
            Citas cita = new Citas(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), horaIni, horaFin, titulo, comentario, codigoPersona, emailPersona, nombrePersona, emailUsuario, cedulaUsuario, "Creada");
            jpaCita.create(cita);
            String asuntoCorreo = "Invitación: " + titulo;
            String descripcion = "Te han invitado a una cita";
            enviarCorreo(ano, mes, dia, horaIni, horaFin, asuntoCorreo, descripcion, comentario, emailPersona, emailUsuario);
            respuesta = "Exitoso";
        } catch (NumberFormatException e) {
            System.out.println("Error creando una cita, el error es: " + e);
        }
        return respuesta;
    }

    public String getCosultaCita(int codigoCita) {
        String respuesta = null;
        try {
            CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());
            Citas cita = jpaCita.findCitas(codigoCita);
            String horaIni = cita.getHoraInicio();
            String horaFin = cita.getHoraFin();

            String hIni = horaIni.substring(0, 2);
            String mIni = horaIni.substring(2, 4);
            String sIni = horaIni.substring(4, 6);

            String hFin = horaFin.substring(0, 2);
            String mFin = horaFin.substring(2, 4);
            String sFin = horaFin.substring(4, 6);

            String fechaIni = hIni + ":" + mIni + ":" + sIni;
            String fechaFin = hFin + ":" + mFin + ":" + sFin;

            respuesta = cita.getCodigoCita() + "," + cita.getAno() + "," + cita.getMes()
                    + "," + cita.getDia() + "," + fechaIni + "," + fechaFin
                    + "," + cita.getTitulo() + "," + cita.getDescripcion() + "," + cita.getCodigoCasoPersona()
                    + "," + cita.getCorreoPersona() + "," + cita.getNombrepersona() + "," + cita.getCorreoUsuario()
                    + "," + cita.getUsuario();

        } catch (Exception e) {
            System.out.println("Error buscando una cita, el error es: " + e);
        }
        return respuesta;
    }

    public String modificarCita(HttpServletRequest request, HttpServletResponse response) {
        String respuesta = "Error";
        CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());
        String codigoCita = request.getParameter("codigoCita");
        String anoCita = request.getParameter("anoCita");
        String mesCita = request.getParameter("mesCita");
        String diaCita = request.getParameter("diaCita");
        String CitaIniHora = request.getParameter("CitaIniHora");
        String CitaFinHora = request.getParameter("CitaFinHora");
        String Citatitulo = request.getParameter("Citatitulo");
        String Citacomentario = request.getParameter("Citacomentario");
        String citaCedula = request.getParameter("citaCedula");
        String correoPersona = request.getParameter("Citaemail");
        String nombrePersona = request.getParameter("citaNom");
        String emailUsuarioCita = request.getParameter("emailUsuarioCita");
        String cedulaUsuarioCita = request.getParameter("cedulaUsuarioCita");
        String enviarCorreo = request.getParameter("enviarCorreo");

        try {
            Citas cita = new Citas(Integer.parseInt(codigoCita), Integer.parseInt(anoCita),
                    Integer.parseInt(mesCita), Integer.parseInt(diaCita), CitaIniHora, CitaFinHora, Citatitulo,
                    Citacomentario, citaCedula, correoPersona, nombrePersona, emailUsuarioCita, cedulaUsuarioCita, "Modificada");
            jpaCita.edit(cita);

            Calendar fecha = Calendar.getInstance();
            int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
            int mesActual = fecha.get(Calendar.MONTH) + 1;
            int anoActual = fecha.get(Calendar.YEAR);
            String fechaActual = diaActual + "/" + mesActual + "/" + anoActual;
            String fechaCita = diaCita + "/" + mesCita + "/" + anoCita;
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(fechaActual);
            Date fechaDate2 = formateador.parse(fechaCita);
            if (fechaDate1.before(fechaDate2)) {
                if (enviarCorreo != null && enviarCorreo.equals("Si")) {
                    String asuntoCorreo = "Modificacion: " + Citatitulo;
                    String descripcion = "Se realizo una modificación en la citación";
                    enviarCorreo(anoCita, mesCita, diaCita, CitaIniHora, CitaFinHora, asuntoCorreo, descripcion, Citacomentario, correoPersona, emailUsuarioCita);
                }
            }
            respuesta = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando una cita, el error es: " + e);
            respuesta = "Error";
        }

        return respuesta;
    }

    public String eliminarCita(HttpServletRequest request, HttpServletResponse response) {
        String resultado = "Error";
        CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());
        String codigoCita = request.getParameter("codigoCita");
        try {
            jpaCita.destroy(Integer.parseInt(codigoCita));
            resultado = "Exitoso";
        } catch (NonexistentEntityException e) {
            System.out.println("Se presento un erro eliminando la cita: " + e);
            resultado = "Error";
        }
        return resultado;
    }

    public void enviarCorreo(String ano, String mes, String dia, String IniHora, String FinHora,
            String asuntoCorreo, String descripcion, String comentario, String correoPersona, String emailUsuario) throws IOException {
        String[] correos = {correoPersona, emailUsuario};
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

            enviarCorreo.EnviarCorreo(correos, asuntoCorreo, contenidoMensaje);
        } catch (MessagingException ex) {
            System.out.println("Error enviando el correo: " + ex);
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
        PrintWriter out = response.getWriter();
        String codigoCita = request.getParameter("codigo");
        if (codigoCita != null && !codigoCita.equals("")) {
            String respuesta = getCosultaCita(Integer.parseInt(codigoCita));
            out.print(respuesta);
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

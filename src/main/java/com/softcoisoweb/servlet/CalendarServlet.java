/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.conexion.OperacionesBD;
import com.softcoisoweb.controller.CitasJpaController;
import com.softcoisoweb.model.Citas;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

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
    protected void guardarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
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
        PrintWriter out = response.getWriter();
        try {
            Citas cita = new Citas(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), horaIni, horaFin, titulo, comentario, codigoPersona, emailPersona, nombrePersona, emailUsuario, cedulaUsuario, "Creada");
            try {
                jpaCita.create(cita);
                out.print("Exitoso");
            } catch (Exception e) {
                System.out.println("Error creando una cita, el error es: " + e);
                out.print("Error");
            }
        } catch (Exception e) {
            System.out.println("Error creando una cita, el error es: " + e);
        }
    }

    public String getCosultaCita(int codigoCita) {
        CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());
        Citas cita = jpaCita.findCitas(codigoCita);
        String respuesta = cita.getCodigoCita() + "," + cita.getAno() + "," + cita.getMes()
                + "," + cita.getDia() + "," + cita.getHoraInicio() + "," + cita.getHoraFin()
                + "," + cita.getTitulo() + "," + cita.getDescripcion() + "," + cita.getCodigoCasoPersona()
                + "," + cita.getCorreoPersona() + "," + cita.getNombrepersona();
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
        String btnCrearCita = request.getParameter("btnCrearCita");
        PrintWriter out = response.getWriter();
        if (btnCrearCita.equals("si")) {
            guardarDatos(request, response);
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

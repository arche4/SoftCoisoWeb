/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        CitasJpaController jpaCita = new CitasJpaController(JPAFactory.getFACTORY());

        String btnCrearCita = request.getParameter("btnCrearCita");
        String codigoPersona = request.getParameter("cedula");
        String nombrePersona = request.getParameter("nombrePersona");
        String emailPersona = request.getParameter("email");
        String horaIni = request.getParameter("horaIni");
        String horaFin = request.getParameter("horaFin");
        String emailUsuario = request.getParameter("emailUsuario");
        String cedulaUsuario = request.getParameter("cedulaUsuario");
        String titulo = request.getParameter("titulo");
        String comentario = request.getParameter("comentario");
        String ano = request.getParameter("ano");
        String mes = request.getParameter("mes");
        String dia = request.getParameter("dia");
        try {
            if (btnCrearCita.equals("si")) {
                Citas cita = new Citas(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia), horaIni, horaFin, titulo, comentario, codigoPersona, emailPersona, nombrePersona, emailUsuario, cedulaUsuario, "Creada");
                try {
                    jpaCita.create(cita);
                } catch (Exception e) {
                    System.out.println("Error creando una cita, el error es: " + e);
                }

                session.setAttribute("Estado", "");
                rd = request.getRequestDispatcher("/view/estadoCasos.jsp");
            }

            rd.forward(request, response);
            
        } catch (Exception e) {
            System.out.println("Error creando una cita, el error es: " + e);
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

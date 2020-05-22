/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.CasoPersonaJpaController;
import com.softcoisoweb.controller.EstadoCasoJpaController;
import com.softcoisoweb.controller.FlujoCasoJpaController;
import com.softcoisoweb.controller.PersonaJpaController;
import com.softcoisoweb.controller.TipoCasoJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.TipoCaso;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
public class ExpedienteServlet extends HttpServlet {

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

        String verExpediente = request.getParameter("verExpediente");
        try ( PrintWriter out = response.getWriter()) {
            if (verExpediente != null && !verExpediente.equals("")) {
                String codigoCaso = verExpediente;
                buscarExpediente(request, response, codigoCaso, "No");
            }
        }
    }

    private void buscarExpediente(HttpServletRequest request, HttpServletResponse response, String codigoCaso, String cargar) throws ServletException, IOException {
        CasoPersonaJpaController casojpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
        TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        CasoPersona caso = casojpa.findCasoPersona(codigoCaso);
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        try {
            TipoCaso tipoCaso = tipoCasoJpa.findTipoCaso(caso.getTipoCasoCodigoTipoCaso());
            FlujoCaso flujo = flujoJpa.findFlujoCaso(codigoCaso);
            EstadoCaso estado = estadoJpa.findEstadoCaso(flujo.getEstadoCasoCodigoEstado());
            Usuario creadoPor = usuarioJpa.findUsuario(caso.getCreadoPor());
            Usuario asignado = usuarioJpa.findUsuario(caso.getAsignado());
            String usuarioInformador = creadoPor.getNombreUsuario()+" "+creadoPor.getApellidoUsuario();
            String usuarioAsignado = asignado.getNombreUsuario()+" "+asignado.getApellidoUsuario();
            session.setAttribute("Expediente", caso);
            PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            Persona persona = Personajpa.findPersona(caso.getPersonaCedula());
            String nombrePersona = persona.getNombrePersona() + " " + persona.getApellidoPersona();
            session.setAttribute("PersonaNombre", nombrePersona);
            session.setAttribute("nombreTipoCaso", tipoCaso.getTipoCaso());
            session.setAttribute("nombreEstado", estado.getNombreEstado());
            session.setAttribute("creadoPor", usuarioInformador);
            session.setAttribute("Asignado", usuarioAsignado);
            session.setAttribute("FlujoCaso", flujo);
            
        } catch (Exception e) {
            System.out.println("Error buscando el expediente  del caso, el error es: " + e);
        }
        if (!cargar.equals("Cargar")) {
            rd = request.getRequestDispatcher("views/expediente.jsp");
        }
        if (rd != null) {
            rd.forward(request, response);
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

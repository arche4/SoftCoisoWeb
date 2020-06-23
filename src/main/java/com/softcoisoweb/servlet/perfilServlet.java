/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
@WebServlet(name = "perfilServlet", urlPatterns = {"/perfilServlet"})
public class perfilServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String btnEditarPerfil = request.getParameter("btnEditarPerfil");

        try ( PrintWriter out = response.getWriter()) {

            if (btnEditarPerfil != null && btnEditarPerfil.equals("ok")) {
                String modUser = modificarUsuario(request, response);
                out.print(modUser);
            }
        }
    }

    private String modificarUsuario(HttpServletRequest request, HttpServletResponse response) {
        String resultado;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String emalUsuario = request.getParameter("usuarioEmail");
        String claveUsuario = request.getParameter("nuevaContraseña");
        try {
            Usuario getUsuario = usuarioJpa.findUsuario(cedula);
            Usuario usuario;
            if (!claveUsuario.equals("")) {
                usuario = new Usuario(cedula, nombre, apellido, claveUsuario, getUsuario.getRol(), emalUsuario);
            } else {
                usuario = new Usuario(cedula, nombre, apellido, getUsuario.getClave(), getUsuario.getRol(), emalUsuario);
            }
            usuarioJpa.edit(usuario);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando al usuario: " + cedula + "El error es:" + e);
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

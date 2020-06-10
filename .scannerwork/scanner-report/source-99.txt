/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws com.softcoisoweb.controller.exceptions.NonexistentEntityException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException {

        String btnCrearUsuario = request.getParameter("btnCrearUsuario");
        String usuarioConsulta = request.getParameter("usuarioConsulta");
        String btnModificar = request.getParameter("btnModificar");
        String btnEliminar = request.getParameter("btnEliminar");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCrearUsuario != null && btnCrearUsuario.equals("ok")) {
                String crear = crearUsuario(request);
                out.print(crear);
            }
            if (usuarioConsulta != null) {
                String datos = consultarUsuario(usuarioConsulta);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modUser = modificarUsuario(request, response);
                out.print(modUser);
            }
            if (btnEliminar != null) {
                String eliminarPersona = eliminarUsuario(btnEliminar);
                out.print(eliminarPersona);
            }
            cargarDatos(request, response);
        }
    }

    private String crearUsuario(HttpServletRequest request) {
        String resultado;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String rol = request.getParameter("rol");
        String emalUsuario = request.getParameter("emalUsuario");
        try {
            String contraseña = cedula.substring(0, 5);
            Usuario usuario = new Usuario(cedula, nombre, apellido, contraseña, rol, emalUsuario);
            usuarioJpa.create(usuario);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error creando al usuario: " + cedula + "El error es:" + e);
            resultado = "Error";
        }
        return resultado;
    }

    private String consultarUsuario(String cedula) {
        String respuesta = null;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        try {
            Usuario usuario = usuarioJpa.findUsuario(cedula);

            respuesta = usuario.getCedula() + "," + usuario.getNombreUsuario() + "," + usuario.getApellidoUsuario()
                    + "," + usuario.getRol() + "," + usuario.getCorreo() + "," + usuario.getClave();

        } catch (Exception e) {
            System.out.println("Error consultando al usuario: " + cedula + "El error es:" + e);
        }
        return respuesta;
    }

    private String modificarUsuario(HttpServletRequest request, HttpServletResponse response) {
        String resultado;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String rol = request.getParameter("rol");
        String emalUsuario = request.getParameter("emalUsuario");
        String claveUsuario = request.getParameter("claveUsuario");
        try {
            Usuario usuario = new Usuario(cedula, nombre, apellido, claveUsuario, rol, emalUsuario);
            usuarioJpa.edit(usuario);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error modificando al usuario: " + cedula + "El error es:" + e);
            resultado = "Error";
        }
        return resultado;
    }

    private String eliminarUsuario(String cedula) throws NonexistentEntityException {
        String resultado;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        try {
            usuarioJpa.destroy(cedula);
            resultado = "Exitoso";
        } catch (ExceptionInInitializerError e) {
            System.out.println("Error eliminando al usuario: " + cedula + "El error es:" + e);
            resultado = "Error";
        }
        return resultado;
    }

    public void cargarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        try {
            List<Usuario> listUsuario = usuarioJpa.findUsuarioEntities();
            session.setAttribute("listUsuario", listUsuario);
        } catch (Exception e) {
            System.out.println("Error cargando datos, El error es:" + e);
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
        try {
            processRequest(request, response);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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

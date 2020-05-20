/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.AfpJpaController;
import com.softcoisoweb.controller.ArlJpaController;
import com.softcoisoweb.controller.EpsJpaController;
import com.softcoisoweb.controller.OrganizacionSindicalJpaController;
import com.softcoisoweb.controller.PersonaJpaController;
import com.softcoisoweb.controller.TipoContratoJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.model.Afp;
import com.softcoisoweb.model.Arl;
import com.softcoisoweb.model.Eps;
import com.softcoisoweb.model.OrganizacionSindical;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.TipoContrato;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        String cedula = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        HttpSession misession = (HttpSession) request.getSession();

        EpsJpaController eps = new EpsJpaController(JPAFactory.getFACTORY());
        ArlJpaController arl = new ArlJpaController(JPAFactory.getFACTORY());
        AfpJpaController afp = new AfpJpaController(JPAFactory.getFACTORY());
        TipoContratoJpaController contrato = new TipoContratoJpaController(JPAFactory.getFACTORY());
        OrganizacionSindicalJpaController sindical = new OrganizacionSindicalJpaController(JPAFactory.getFACTORY());

        try {
            if (misession.equals(true)) {
                rd = request.getRequestDispatcher("index.jsp");
            }
            UsuarioJpaController ujc = new UsuarioJpaController(JPAFactory.getFACTORY());
            Usuario usuario = ujc.buscarUsuario(cedula, clave);
            String Mensaje = "";
            if (usuario == null) {
                Mensaje = "Email o Clave no validos";
                session.setAttribute("MENSAJE", Mensaje);
                rd = request.getRequestDispatcher("index.jsp");
            } else {
                session.setAttribute("user", usuario);
                session.setAttribute("USUARIO", usuario);
                List<Usuario> listUsuario = ujc.findUsuarioEntities();
                session.setAttribute("listUsuario", listUsuario);
                session.setAttribute("rol", "Administrador");
                List<Eps> listEps = eps.findEpsEntities();
                session.setAttribute("EPS", listEps);
                List<Arl> ListArl = arl.findArlEntities();
                session.setAttribute("ARL", ListArl);
                List<Afp> ListAfp = afp.findAfpEntities();
                session.setAttribute("AFP", ListAfp);
                List<TipoContrato> listContrato = contrato.findTipoContratoEntities();
                session.setAttribute("Contrato", listContrato);
                List<OrganizacionSindical> listSindicato = sindical.findOrganizacionSindicalEntities();
                session.setAttribute("Sindicato", listSindicato);
                PersonaJpaController Persona = new PersonaJpaController(JPAFactory.getFACTORY());
                List<Persona> listPerson = Persona.findPersonaEntities();
                session.setAttribute("Persona", listPerson);
                rd = request.getRequestDispatcher("views/dashboard.jsp");
            }
        } catch (Exception e) {
            rd = request.getRequestDispatcher("index.jsp");
            System.out.println("Error buscando al usuario: " + e);
        }
        rd.forward(request, response);
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

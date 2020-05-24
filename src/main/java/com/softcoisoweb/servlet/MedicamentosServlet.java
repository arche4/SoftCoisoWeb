/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.MedicamentosJpaController;
import com.softcoisoweb.model.Medicamentos;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class MedicamentosServlet extends HttpServlet {

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

        String btnCrearMedicamento = request.getParameter("btnCrearMedicamento");
        String consultarMedicamento = request.getParameter("consultarMedicamento");
        String btnModificar = request.getParameter("btnModificar");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCrearMedicamento != null && btnCrearMedicamento.equals("ok")) {
                String crear = crearMedicamento(request);
                out.print(crear);
            }
            if (consultarMedicamento != null) {
                String datos = consultarMedicamento(consultarMedicamento);
                out.print(datos);
            }
            if (btnModificar != null && btnModificar.equals("ok")) {
                String modMed = modificarMedicamento(request);
                out.print(modMed);
            }
            cargarDatos(request, response);
        }
    }

    private String crearMedicamento(HttpServletRequest request) {
        String respuesta;
        MedicamentosJpaController medicamentoJpa = new MedicamentosJpaController(JPAFactory.getFACTORY());
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String nombreMedicamento = request.getParameter("nombreMedicamento");
        String descripcionMedicamento = request.getParameter("descripcionMedicamento");
        try {
            Medicamentos medicamento = medicamentoJpa.findMedicamentos(codigoMedicamento);
            if (medicamento != null) {
                respuesta = "1";
            } else {
                Medicamentos med = new Medicamentos(codigoMedicamento, nombreMedicamento, descripcionMedicamento);
                medicamentoJpa.create(med);
                respuesta = "0";
            }
        } catch (Exception e) {
            System.out.println("Error creando el medicamento: " + codigoMedicamento + "El error es:" + e);
            respuesta = "2";
        }
        return respuesta;
    }

    private void cargarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MedicamentosJpaController medicamentoJpa = new MedicamentosJpaController(JPAFactory.getFACTORY());
        RequestDispatcher rd = null;
        try {
            List<Medicamentos> listMedicamento = medicamentoJpa.findMedicamentosEntities();
            session.setAttribute("listMedicamento", listMedicamento);
        } catch (Exception e) {
            System.out.println("Error cargando datos de los medicamentos. El error es:" + e);
        }
        rd.forward(request, response);
    }

    private String consultarMedicamento(String codigoMedicamento) {
        String respuesta = null;
        MedicamentosJpaController medicamentoJpa = new MedicamentosJpaController(JPAFactory.getFACTORY());
        try {
            Medicamentos med = medicamentoJpa.findMedicamentos(codigoMedicamento);
            respuesta = med.getCodigoMedicamento() + "#" + med.getNombreMedicamento() + "#" + med.getDescripcionMedicamento();

        } catch (Exception e) {
            System.out.println("Error consultando el medicamento: " + codigoMedicamento + "El error es:" + e);
        }
        return respuesta;
    }

    private String modificarMedicamento(HttpServletRequest request) {
        String resultado;
        MedicamentosJpaController medicamentoJpa = new MedicamentosJpaController(JPAFactory.getFACTORY());
        String codigoMedicamento = request.getParameter("codigoMedicamento");
        String nombreMedicamento = request.getParameter("nombreMedicamento");
        String descripcionMedicamento = request.getParameter("descripcionMedicamento");
        try {
            Medicamentos med = new Medicamentos(codigoMedicamento, nombreMedicamento, descripcionMedicamento);
            medicamentoJpa.edit(med);
            resultado = "Exitoso";
        } catch (Exception e) {
            System.out.println("Error consultando el medicamento: " + codigoMedicamento + "El error es:" + e);
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

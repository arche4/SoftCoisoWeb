/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.conexion.OperacionesBD;
import com.softcoisoweb.util.Gestor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
@WebServlet(name = "ReportesServlet", urlPatterns = {"/ReportesServlet"})
public class ReportesServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");
    private final Gestor doc = new Gestor();

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

        String btnReporte = request.getParameter("btnReporte");

        try ( PrintWriter out = response.getWriter()) {
            if (btnReporte != null && btnReporte.equals("ok")) {
                String reporte = generarReporte(request);
                out.print(reporte);
            }
        }
    }

    private String generarReporte(HttpServletRequest request) throws ParseException, IOException {
        String contextPath = doc.leerProperties("rutaReportes");
        String respuesta = null;
        OperacionesBD ops = new OperacionesBD();
        String tCons = request.getParameter("tCons");
        String fecha_i = request.getParameter("fecha_i");
        String fecha_f = request.getParameter("fecha_f");

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date_i = dt.parse(fecha_i);
        Date date_f = dt.parse(fecha_f);

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_ini = dt1.format(date_i);
        String fecha_fin = dt1.format(date_f);

        try {
            respuesta = ops.getConsultas(tCons, fecha_ini, fecha_fin, contextPath);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error Obteniendo los datos para el reporte de personas, El error es:  {0}", new Object[]{ex});
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
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

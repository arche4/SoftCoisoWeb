/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.conexion.OperacionesBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

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

        String graficaEstado = request.getParameter("graficaEstado");
        String graficaGenero = request.getParameter("graficaGenero");
        String graficaArl = request.getParameter("graficaArl");
        String graficaEps = request.getParameter("graficaEps");
        String graficaAfp = request.getParameter("graficaAfp");

        try ( PrintWriter out = response.getWriter()) {
            if (graficaEstado != null && graficaEstado.equals("ok")) {
                JSONArray grafica = consltarGraficaEstado();
                out.print(grafica);
            }

            if (graficaGenero != null && graficaGenero.equals("ok")) {
                JSONArray grafica = consultarGraficaGenero();
                out.print(grafica);
            }
            if (graficaArl != null && graficaArl.equals("ok")) {
                JSONArray grafica = consultarGraficaArl();
                out.print(grafica);
            }
            if (graficaEps != null && graficaEps.equals("ok")) {
                JSONArray grafica = consultarGraficaEps();
                out.print(grafica);
            }
            if (graficaAfp != null && graficaAfp.equals("ok")) {
                JSONArray grafica = consultarGraficaAfp();
                out.print(grafica);
            }
        }
    }

    private JSONArray consltarGraficaEstado() {
        JSONArray respuesta = new JSONArray();
        try {
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.graficaEstado();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la grafica de estados:   El error es: {0}", new Object[]{e});
        }
        return respuesta;
    }

    private JSONArray consultarGraficaGenero() {
        JSONArray respuesta = new JSONArray();
        try {
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.graficaGenero();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la grafica de genero:   El error es: {0}", new Object[]{e});
        }
        return respuesta;

    }

    private JSONArray consultarGraficaArl() {
        JSONArray respuesta = new JSONArray();
        try {
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.grafiaArl();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la grafica de genero:   El error es: {0}", new Object[]{e});
        }
        return respuesta;
    }
    
    private JSONArray consultarGraficaEps() {
        JSONArray respuesta = new JSONArray();
        try {
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.grafiaEps();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la grafica de genero:   El error es: {0}", new Object[]{e});
        }
        return respuesta;
    }
    private JSONArray consultarGraficaAfp() {
        JSONArray respuesta = new JSONArray();
        try {
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.grafiaAfp();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos de la grafica de genero:   El error es: {0}", new Object[]{e});
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

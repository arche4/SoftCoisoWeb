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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author manue
 */
public class CargarDatosCalendarServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        try {
            JSONArray respuesta;
            String start = req.getParameter("start");
            String end = req.getParameter("end");
            String[] fIniVec = start.split("T");
            fIniVec = fIniVec[0].split("-");
            String[] fFinVec = end.split("T");
            fFinVec = fFinVec[0].split("-");
            int anoIni = Integer.parseInt(fIniVec[0]);
            int mesIni = Integer.parseInt(fIniVec[1]);
            int anoFin = Integer.parseInt(fFinVec[0]);
            int mesFin = Integer.parseInt(fFinVec[1]);
            int mesMed = mesIni + 1;
            int anoMed = anoIni;
            if (mesMed > 12) {
                mesMed = 1;
                anoMed++;
            }
            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.cargarDatos(anoIni, mesIni, anoMed, mesMed, anoFin, mesFin);

            out.print(respuesta);
        } catch (IOException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultando los datos del calendario:   El error es: {0}", new Object[]{e});

        }

    }

}

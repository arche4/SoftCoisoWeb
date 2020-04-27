/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.conexion.OperacionesBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CargarDatosCalendarServlet extends HttpServlet  {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        JSONArray respuesta = new JSONArray();

        String start = req.getParameter("start");
        String end = req.getParameter("end");
        String[] fIniVec = start.split("T");
        fIniVec = fIniVec[0].split("-");
        String[] fFinVec = end.split("T");
        fFinVec = fFinVec[0].split("-");
        int anoIni = Integer.parseInt(fIniVec[0]);
        int mesIni = Integer.parseInt(fIniVec[1]);
        int diaIni = Integer.parseInt(fIniVec[2]);
        int anoFin = Integer.parseInt(fFinVec[0]);
        int mesFin = Integer.parseInt(fFinVec[1]);
        int diaFin = Integer.parseInt(fFinVec[2]);
        int mesMed = mesIni+1;
        int anoMed = anoIni;
        if (mesMed > 12) {
            mesMed = 1;
            anoMed++;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        

        try {
            Date fechaIni = format.parse(start);
            Date fechaFin = format.parse(end);

            Date dateIni = new java.sql.Date(fechaIni.getTime());
            Date dateFin = new java.sql.Date(fechaFin.getTime());

            OperacionesBD ops = new OperacionesBD();
            respuesta = ops.cargarDatos(anoIni, mesIni, anoMed, mesMed, anoFin, mesFin);
        } catch (ParseException e) {
            System.out.println("el error es: " + e);
        }

        out.print(respuesta);
    }
    
}

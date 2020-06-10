/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
public class OperacionesBD {

    public String scheme = "citas";

    public JSONArray cargarDatos(final int anoIni, final int mesIni, final int anoMed,
            final int mesMed, final int anoFin, final int mesFin) throws IOException {
        JSONArray resp = new JSONArray();
        PreparedStatement pstmt = null;
        Connection conn = null;
        Statement stmt = null;
        BufferedReader br = null;
        String fechaI = null;
        String fechaF = null;
        String eventColor = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            pstmt = conn.prepareStatement("SELECT * from " + scheme
                    + " WHERE ano IN(?,?,?) AND mes IN (?,?,?)");
            pstmt.setInt(1, anoIni);
            pstmt.setInt(2, anoMed);
            pstmt.setInt(3, anoFin);
            pstmt.setInt(4, mesIni);
            pstmt.setInt(5, mesMed);
            pstmt.setInt(6, mesFin);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                JSONObject data = new JSONObject();
                int ano = result.getInt(2);
                int mes = result.getInt(3);
                int dia = result.getInt(4);
                int codigo = result.getInt(1);
                String horaInicial = result.getString(5);
                String horaFinal = result.getString(6);
                String titulo = result.getString(7);
                String estado = result.getString(14);
                String hIni = horaInicial.substring(0, 2);
                String mIni = horaInicial.substring(2, 4);
                String sIni = horaInicial.substring(4, 6);
                String hFin = horaFinal.substring(0, 2);
                String mFin = horaFinal.substring(2, 4);
                String sFin = horaFinal.substring(4, 6);
                String diaString;
                String mesString;
                if (dia < 10) {
                    diaString = "0" + Integer.toString(dia);
                } else {
                    diaString = Integer.toString(dia);
                }
                if (mes < 10) {
                    mesString = "0" + Integer.toString(mes);
                } else {
                    mesString = Integer.toString(mes);
                }
                Calendar fecha = Calendar.getInstance();
                int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
                int mesActual = fecha.get(Calendar.MONTH) + 1;

                if (dia < diaActual && mes == mesActual) {
                    eventColor = "#777";
                } else if (dia < diaActual && mes < mesActual) {
                    eventColor = "#777";
                } else if (dia > diaActual && mes < mesActual) {
                    eventColor = "#777";
                } else if (dia == diaActual) {
                    eventColor = "#f0ad4e";
                } else if (dia > diaActual) {
                    eventColor = "#5cb85c";
                } else if (estado.equals("Modificada")) {
                    eventColor = "#4e69cd";
                } else {
                    eventColor = "#5cb85c";
                }
                fechaI = Integer.toString(ano) + "-"
                        + mesString + "-"
                        + diaString
                        + "T" + hIni + ":" + mIni + ":" + sIni + "-05:00";
                fechaF = Integer.toString(ano) + "-"
                        + mesString + "-"
                        + diaString
                        + "T" + hFin + ":" + mFin + ":" + sFin + "-05:00";

                data.put("title", codigo + "(" + titulo + ")");
                data.put("start", fechaI);
                data.put("end", fechaF);
                data.put("color", eventColor);
                resp.put(data);
            }
            System.out.println(resp);
        } catch (SQLException e) {
            System.out.println("com.softcoisoweb.conexion.OperacionesBD.cargarDatos(): ERROR" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (SQLException e) {
                System.out.println("com.softcoisoweb.conexion.OperacionesBD.cargarDatos(): ERROR" + e);
            }
        }
        return resp;
    }
}

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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
public class OperacionesBD {

    public String scheme = "citas";
    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    public JSONArray cargarDatos(final int anoIni, final int mesIni, final int anoMed,
            final int mesMed, final int anoFin, final int mesFin) throws IOException {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        Statement stmt = null;
        BufferedReader br = null;
        String fechaI;
        String fechaF;
        String eventColor;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("SELECT * from " + scheme
                    + " WHERE ano IN(?,?,?) AND mes IN (?,?,?)");) {
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
                LOGGER.log(Level.SEVERE, "Error Obteniendo los datos de las citas, El error es:  {0}", new Object[]{e});
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error Obteniendo los datos de las citas, El error es:  {0}", new Object[]{e});
        } finally {
            try {
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

    public JSONArray graficaEstado() {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("select ec.nombre_estado as estado, "
                    + "COUNT(fc.caso_persona_id_caso) as cantidad "
                    + "from estado_caso ec "
                    + "inner join flujo_caso fc "
                    + "on ec.codigo_estado = fc.estado_caso_codigo_estado "
                    + "GROUP BY ec.nombre_estado");) {
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    JSONObject data = new JSONObject();
                    String Estado = result.getString(1);
                    String Cantidad = result.getString(2);
                    data.put("Estado", Estado);
                    data.put("Cantidad", Cantidad);
                    resp.put(data);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Se presento un error consultado la grafica de estados:   El error es: {0}", new Object[]{e});

            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultado la grafica de estados:   El error es: {0}", new Object[]{e});

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("com.softcoisoweb.conexion.OperacionesBD.cargarDatos(): ERROR" + e);
            }
        }
        return resp;
    }

    public JSONArray graficaGenero() {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("select p.genero as genero, "
                    + "COUNT(p.cedula) as cantidad "
                    + "from persona p "
                    + "GROUP BY p.genero");) {
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    JSONObject data = new JSONObject();
                    String Genero = result.getString(1);
                    String Cantidad = result.getString(2);
                    data.put("Genero", Genero);
                    data.put("Cantidad", Cantidad);
                    resp.put(data);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Se presento un error consultado la grafica de generos:   El error es: {0}", new Object[]{e});

            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error consultado la grafica de generos:   El error es: {0}", new Object[]{e});

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("com.softcoisoweb.conexion.OperacionesBD.cargarDatos(): ERROR" + e);
            }
        }
        return resp;
    }
}

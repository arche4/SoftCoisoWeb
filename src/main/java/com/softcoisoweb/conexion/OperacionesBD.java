/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.conexion;

import com.softcoisoweb.clase.ExcelCreateReport;
import com.softcoisoweb.clase.reportePersona;
import com.softcoisoweb.util.Gestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
public class OperacionesBD {

    public String scheme = "citas";
    public String formacion = "formacion";
    private final Gestor doc = new Gestor();

    public String getConsultas(String Reporte, String fecha_ini, String fecha_fin, String path) throws SQLException {
        String data = "";

        if (Reporte.equals("Personas")) {
            data = reportePersonas(fecha_ini, fecha_fin, Reporte, path);
        }

        return data;
    }

    public String reportePersonas(String fecha_ini, String fecha_fin, String tCons, String path) throws SQLException {

        //Conexion Base de Datos
        Connection conn;
        ResultSet rs = null;
        String data = "";

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            //Variables Locales
            reportePersona mihtmlTabla = new reportePersona();
            List<reportePersona> datosReport = new ArrayList<>(); //Variable donde se guarda la consulta
            try ( PreparedStatement stmt = conn.prepareStatement(reportePersona.sql)) {
                stmt.setString(1, fecha_ini.trim());
                stmt.setString(2, fecha_fin.trim());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    reportePersona thisObjRepo = new reportePersona();
                    thisObjRepo.setCedula(rs.getInt(1));
                    thisObjRepo.setNombre(rs.getString(2));
                    thisObjRepo.setGenero(rs.getString(3));
                    thisObjRepo.setFechaNacimiento(rs.getString(4));
                    thisObjRepo.setEdad(rs.getString(5));
                    thisObjRepo.setEmpresa(rs.getString(6));
                    thisObjRepo.setAntiguedadEmpresa(rs.getString(7));
                    thisObjRepo.setCargo(rs.getString(8));
                    thisObjRepo.setFechaClinica(rs.getString(9));
                    thisObjRepo.setTelefono(rs.getString(10));
                    thisObjRepo.setCorreo(rs.getString(11));
                    thisObjRepo.setEps(rs.getString(12));
                    thisObjRepo.setArl(rs.getString(13));
                    thisObjRepo.setAfp(rs.getString(14));
                    thisObjRepo.setContrato(rs.getString(15));
                    thisObjRepo.setOrganizacion(rs.getString(16));
                    thisObjRepo.setMunicipio(rs.getString(17));
                    thisObjRepo.setBarrio(rs.getString(18));
                    thisObjRepo.setDirrecion(rs.getString(19));
                    datosReport.add(thisObjRepo);
                }

                //Generar Reporte Excel
                ExcelCreateReport excelReport = new ExcelCreateReport("Reporte", path + tCons + ".xls");
                excelReport.createPersona(datosReport);

                //Generar vista HTML 
                mihtmlTabla.setBodyTable(datosReport);
                data = mihtmlTabla.getTableHtml();
            } catch (Exception e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos para el reporte de personas, El error es: " + e);
            }

        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos para el reporte de personas, El error es: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos para el reporte de personas, El error es: " + e);
            }
        }

        return data;
    }

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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las citas, El error es: " + e);
            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las citas, El error es: " + e);
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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las citas, El error es: " + e);
            }
        }
        return resp;
    }

    public JSONArray cargarDatosFormacion(final int anoIni, final int mesIni, final int anoMed,
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
            try ( PreparedStatement pstmt = conn.prepareStatement("SELECT * from " + formacion
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
                    int ano = result.getInt(3);
                    int mes = result.getInt(4);
                    int dia = result.getInt(5);
                    int codigo = result.getInt(1);
                    String horaInicial = result.getString(6);
                    String horaFinal = result.getString(7);
                    String titulo = result.getString(8);
                    String estado = result.getString(17);
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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las formaciones, El error es: " + e);
            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las formaciones, El error es: " + e);
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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Error Obteniendo los datos de las formaciones, El error es: " + e);
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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de estados, El error es: " + e);
            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de estados, El error es: " + e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de estados, El error es: " + e);
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
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de generos, El error es: " + e);

            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de generos, El error es: " + e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de generos, El error es: " + e);
            }
        }
        return resp;
    }

    public JSONArray grafiaArl() {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("select a.nombre_arl, "
                    + "COUNT(p.arl_codigo_arl) as cantidad "
                    + "from arl a "
                    + "inner join persona p "
                    + "on a.codigo_arl = p.arl_codigo_arl "
                    + "GROUP BY a.nombre_arl");) {
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    JSONObject data = new JSONObject();
                    String arl = result.getString(1);
                    String Cantidad = result.getString(2);
                    data.put("Arl", arl);
                    data.put("Cantidad", Cantidad);
                    resp.put(data);
                }
            } catch (Exception e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de arl, El error es: " + e);
            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de arl, El error es: " + e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de arl, El error es: " + e);
            }
        }
        return resp;
    }

    public JSONArray grafiaEps() {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("select e.nombre_eps, "
                    + "COUNT(p.eps_codigo_eps) as cantidad "
                    + "from eps e "
                    + "inner join persona p "
                    + "on e.codigo_eps = p.eps_codigo_eps "
                    + "GROUP BY e.nombre_eps");) {
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    JSONObject data = new JSONObject();
                    String Eps = result.getString(1);
                    String Cantidad = result.getString(2);
                    data.put("Eps", Eps);
                    data.put("Cantidad", Cantidad);
                    resp.put(data);
                }
            } catch (Exception e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Eps, El error es: " + e);
            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Eps, El error es: " + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Eps, El error es: " + e);
            }
        }
        return resp;
    }

    public JSONArray grafiaAfp() {
        JSONArray resp = new JSONArray();
        Connection conn = null;
        try {
            Conexion objConn = new Conexion();
            conn = objConn.conectarMySQL();
            try ( PreparedStatement pstmt = conn.prepareStatement("select af.nombre_afp, "
                    + "COUNT(p.afp_codigo_afp) as cantidad "
                    + "from afp af "
                    + "inner join persona p "
                    + "on af.codigo_afp = p.afp_codigo_afp "
                    + "GROUP BY af.nombre_afp");) {
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    JSONObject data = new JSONObject();
                    String afp = result.getString(1);
                    String Cantidad = result.getString(2);
                    data.put("Afp", afp);
                    data.put("Cantidad", Cantidad);
                    resp.put(data);
                }
            } catch (Exception e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Afp, El error es: " + e);

            }
        } catch (SQLException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Afp, El error es: " + e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                doc.imprimirLog(doc.obtenerHoraActual() + "-Se presento un error consultado la grafica de Afp, El error es: " + e);
            }
        }
        return resp;
    }
}

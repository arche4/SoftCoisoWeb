/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.conexion;

import com.softcoisoweb.util.Gestor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manue
 */
public class Conexion {

    private final Gestor doc = new Gestor();
    final private String USER = doc.leerProperties("USER");
    final private String PASS = doc.leerProperties("PASS");
    final private String HOST = doc.leerProperties("HOST");
    final private String PORT = doc.leerProperties("PORT");
    final private String DATABASE = doc.leerProperties("DATABASE");
    final static private String DRIVER = "com.mysql.cj.jdbc.Driver";
    final private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false";

    public Connection conectarMySQL() throws SQLException {
        Connection CONMYSQL = null;

        try {
            Class.forName(DRIVER);
            CONMYSQL = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            doc.imprimirLog(doc.obtenerHoraActual() + "Error conectandose a la base de datos, El error es: " + e);
        }
        return CONMYSQL;
    }

}

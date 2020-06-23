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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Conexion {

    private final Gestor doc = new Gestor();
//    final private String USER = doc.leerProperties("USER");
//    final private String PASS = doc.leerProperties("PASS");
//    final private String HOST = doc.leerProperties("HOST");
//    final private String PORT = doc.leerProperties("PORT");
//    final private String DATABASE = doc.leerProperties("DATABASE");
    
    final private String USER = "coiso_dllo";
    final private String PASS = "C01s0.c0m";
    final private String HOST = "190.8.176.153";
    final private String PORT = "3306";
    final private String DATABASE = "coiso_PRODMDLLO";
    final static private String DRIVER = "com.mysql.cj.jdbc.Driver";
    final private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false";
    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    public Connection conectarMySQL() throws SQLException {
        Connection CONMYSQL = null;

        try {
            Class.forName(DRIVER);
            CONMYSQL = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error conectandose a la base de datos, El error es:  {0}", new Object[]{e});
        }
        return CONMYSQL;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.conexion;

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

    final static private String USER = "coiso_BDAdmon20";
    final static private String PASS = "Coiso2020";
    final static private String HOST = "coiso.org";
    final static private String PORT = "3306";
    final static private String DATABASE = "coiso_BDpdn";
    final static private String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false";

     public Connection conectarMySQL() throws SQLException {
        Connection CONMYSQL = null;

        try {
            Class.forName(DRIVER);
            CONMYSQL = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return CONMYSQL;
    }
}

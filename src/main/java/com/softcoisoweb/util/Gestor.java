/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Gestor {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    public String leerProperties(final String proper) {
        String propiedad;
        final Properties prop = new Properties();
        InputStream input_stream = null;
        try { 
            System.out.println(getClass().getResource("/conexion.properties"));
            System.out.println(this.getClass().getResourceAsStream("/conexion.properties"));
            input_stream = getClass().getResource("conexion.properties").openStream();
            prop.load(input_stream);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error leyendo el archivo properties, El error es:  {0}", new Object[]{ex});
        } finally {
            if (input_stream != null) {
                try {
                    input_stream.close();
                } catch (IOException ex1) {
                    LOGGER.log(Level.SEVERE, "Error cerrando el archivo properties, El error es:  {0}", new Object[]{ex1});
                }
            }
        }
        propiedad = prop.getProperty(proper, "");
        prop.clear();
        return propiedad;
    }
}

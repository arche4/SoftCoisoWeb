/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Gestor {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");
    private final String Ruta = leerProperties("rutaLogs");

    public String leerProperties(final String proper) {
        String propiedad;
        final Properties prop = new Properties();
        InputStream input_stream = null;
        try {

            input_stream = new FileInputStream("C:\\SoftCoisoWeb\\Configuracion sistema\\conexion.properties");
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

    public void imprimirLog(final String error) {
        synchronized (this) {
            FileWriter escrbibeArchivo;
            BufferedWriter bufferescribe;
            try {
                escrbibeArchivo = new FileWriter(generarNombreDocumento(), true);
                bufferescribe = new BufferedWriter(escrbibeArchivo);
                bufferescribe.write(error);
                bufferescribe.newLine();
                bufferescribe.close();
                escrbibeArchivo.close();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error cerrando imprimiendo logs:  {0}", new Object[]{ex});
            }
        }
    }

    public String obtenerFechaActual() {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final String FechaActual = sdf.format(fecha);
        return FechaActual;
    }

    public String generarNombreDocumento() {
        final File rutaLocal = new File(Ruta);
        if (!rutaLocal.isDirectory()) {
            rutaLocal.mkdir();
        }
        return rutaLocal + File.separator + obtenerFechaActual() + "Errores.txt";

    }
    
    public String obtenerHoraActual() {
        final Time sqlTime = new Time(new java.util.Date().getTime());
        return sqlTime.toString();
    }
}

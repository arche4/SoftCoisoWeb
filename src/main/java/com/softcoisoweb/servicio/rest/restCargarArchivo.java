/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servicio.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
public class restCargarArchivo {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    @SuppressWarnings("empty-statement")
    public String cargarArchivo(File fileName, int folder) throws MalformedURLException, IOException {
        final StringBuilder retorno = new StringBuilder();
        final StringBuilder sb = new StringBuilder();
        String token = "Basic " + Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes());
        String dServer;
        String boundary;

        try {
            boundary = "---" + System.currentTimeMillis() + "---";
            URL uri = new URL("https://service-archivo.herokuapp.com/api/files/" + folder);
            HttpURLConnection connection = (HttpsURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("Authorization", "Basic " + token);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter connOutWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            try {
                connOutWriter.write("\r\n--" + boundary + "\r\n");
                connOutWriter.write("Content-Disposition: form-data; "
                        + "name=\"file\"; " + "filename=\"" + fileName.getName() + "\"" + "\r\n\r\n");
                connOutWriter.flush();
                try ( FileInputStream fileStream = new FileInputStream((File) fileName)) {
                    int readBytes;
                    int numero = 0;
                    byte[] buffer = new byte[1024];
                    while ((readBytes = fileStream.read(buffer)) != -1) {
                        outputStream.write(buffer, numero, readBytes);
                    }
                    outputStream.flush();
                    connOutWriter.write("\r\n--" + boundary + "--\r\n");
                    connOutWriter.flush();
                    connOutWriter.close();
                    outputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error consumiento el servicio de la carga de archivos, el error ess:  {0}", new Object[]{e});
            }

            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                try ( BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        dServer = sb.toString();
                        final String obtenerRespueta = obtenerRuta(dServer);
                        retorno.append(obtenerRespueta);
                    }
                }
                connection.disconnect();
            } else {
                retorno.append("Error");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error consumiento el servicio de la carga de archivos, el error ess:  {0}", new Object[]{e});

        }

        return retorno.toString();

    }

    public String obtenerRuta(final String respuesta) {
        final StringBuilder respondo = new StringBuilder();
        String fileName;
        String ruta;
        JSONObject object = new JSONObject(respuesta);
        if (respuesta.length() > 0 && !respuesta.contains("EXCEPTION")) {
            try {
                fileName = object.getString("fileName");
                ruta = object.getString("fileDownloadUri");
                respondo.append("Exitoso").append(",").append(fileName).append(",").append(ruta);
            } catch (JSONException e) {
              LOGGER.log(Level.SEVERE, "Error obteniendo la ruta del archivo, el error e:  {0}", new Object[]{e});

            }
        }
        return respondo.toString();
    }
}

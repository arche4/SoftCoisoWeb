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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
public class restCargarArchivo {

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
            BufferedWriter conn_out_writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            try {
                conn_out_writer.write("\r\n--" + boundary + "\r\n");
                conn_out_writer.write("Content-Disposition: form-data; "
                        + "name=\"file\"; " + "filename=\"" + fileName.getName() + "\"" + "\r\n\r\n");
                conn_out_writer.flush();
                try ( FileInputStream file_stream = new FileInputStream((File) fileName)) {
                    int read_bytes;
                    int numero = 0;
                    byte[] buffer = new byte[1024];
                    while ((read_bytes = file_stream.read(buffer)) != -1) {
                        outputStream.write(buffer, numero, read_bytes);
                    }
                    outputStream.flush();
                    conn_out_writer.write("\r\n--" + boundary + "--\r\n");
                    conn_out_writer.flush();
                    conn_out_writer.close();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Error consumiento el servicio de la carga de archivos, el error es: " + e);
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
            System.err.println("Error consumiento el servicio de la carga de archivos, el error es: " + e);

        }

        return retorno.toString();

    }

    public String obtenerRuta(final String respuesta) {
        final StringBuilder respondo = new StringBuilder();
        String fileName, ruta;
        JSONObject object = new JSONObject(respuesta);
        if (respuesta.length() > 0 && !respuesta.contains("EXCEPTION")) {
            try {
                fileName = object.getString("fileName");
                ruta = object.getString("fileDownloadUri");
                respondo.append("Exitoso").append(",").append(fileName).append(",").append(ruta);
            } catch (JSONException e) {
                System.err.println("Error obteniendo la ruta del archivo, el error es: " + e);
            }
        }
        return respondo.toString();
    }
}

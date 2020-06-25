package com.softcoisoweb.servlet;


import com.softcoisoweb.util.Gestor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CargaArchivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = Logger.getLogger("LogsErrores");
    private final Gestor doc = new Gestor();

    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public CargaArchivoServlet() {
      
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String folder = doc.leerProperties("ArchivosExpediente");
        String nombre;
        String resultado = null;
        String rutaArchivo;
        String nombreArchivo;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        nombre = new File(item.getName()).getName();
                        String expediente = nombre.split("_")[0];
                        String name = nombre.split("_")[1];
                        File folderExpediente = new File(folder + File.separator + "Expediente" + expediente);
                        if (folderExpediente.exists() == false) {
                            folderExpediente.mkdir();
                        }

                        item.write(new File(folderExpediente + File.separator + name));
                        rutaArchivo = "http://127.0.0.1:8887/" +  "Expediente" + expediente + "/" + name;
                        nombreArchivo = name;
                        resultado = "Exitoso" + "," + nombreArchivo + "," + rutaArchivo;
                    }
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error cargando el archivo, El error es:  {0}", new Object[]{e});
                resultado = "Error";
            }

            PrintWriter out = response.getWriter();
            out.print(resultado);
        }

    }

}

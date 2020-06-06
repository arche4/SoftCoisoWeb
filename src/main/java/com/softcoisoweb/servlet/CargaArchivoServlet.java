package com.softcoisoweb.servlet;

import com.softcoisoweb.servicio.rest.restCargarArchivo;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CargaArchivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaArchivoServlet() {
        super();
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
        final String baseTempPath = System.getProperty("java.io.tmpdir");
        restCargarArchivo cargarArchivo = new restCargarArchivo();
        String name = null;
        String resultado = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        Random rand = new Random();
                        int randomInt = 1 + rand.nextInt();

                        File tempDir = new File(baseTempPath + File.separator + "tempDir" + randomInt);
                        if (tempDir.exists() == false) {
                            tempDir.mkdir();
                        }
                        name = new File(item.getName()).getName();
                        item.write(new File(tempDir + File.separator + name));
                        File file = new File(tempDir + File.separator + name);
                        resultado = cargarArchivo.cargarArchivo(file, 2);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error cargando el archivo, El error es" + e);
                resultado = "Error";
            }

            PrintWriter out = response.getWriter();
            out.print(resultado);
        }

    }

}

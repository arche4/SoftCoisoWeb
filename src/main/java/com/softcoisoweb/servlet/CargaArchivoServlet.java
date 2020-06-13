package com.softcoisoweb.servlet;

import com.softcoisoweb.servicio.rest.restCargarArchivo;
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
    private final Random rand;
    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    /**
     * @throws java.security.NoSuchAlgorithmException
     * @see HttpServlet#HttpServlet()
     */
    public CargaArchivoServlet() throws NoSuchAlgorithmException {
        super();
        this.rand = SecureRandom.getInstanceStrong();
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
        String name;
        String resultado = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        int randomInt = this.rand.nextInt();

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
                LOGGER.log(Level.SEVERE, "Error cargando el archivo, El error es:  {0}", new Object[]{e});
                resultado = "Error";
            }

            PrintWriter out = response.getWriter();
            out.print(resultado);
        }

    }

}

package com.softcoisoweb.servlet;

import com.softcoisoweb.controller.CasoAccionesJpaController;
import com.softcoisoweb.controller.CasoPersonaJpaController;
import com.softcoisoweb.model.CasoAcciones;
import com.softcoisoweb.util.JPAFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class CargaArchivoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaArchivoServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String UPLOAD_DIRECTORY = "C:\\Users\\manue\\Documents\\NetBeansProjects\\SoftCoisoWeb\\Archivos";
        String archivo = null;
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = item.getFieldName();
                        InputStream stream = item.getInputStream();
                        File fileSaveDir = new File(UPLOAD_DIRECTORY);
                        if (!fileSaveDir.exists()) {
                            fileSaveDir.mkdir();
                        }
                        byte[] pdf = stream.readAllBytes();
                        CasoAccionesJpaController accionJpa = new CasoAccionesJpaController(JPAFactory.getFACTORY());
                        CasoAcciones list = accionJpa.findCasoAcciones(11);
                        byte[] image = list.getArchivos();
                        archivo = new String(image);
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
//                        archivo = UPLOAD_DIRECTORY + File.separator + name;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error  El error es" + e);
            }

            PrintWriter out = response.getWriter();
            String respuesta = "1" + "," + archivo;
            out.print(respuesta);
        }

    }

}

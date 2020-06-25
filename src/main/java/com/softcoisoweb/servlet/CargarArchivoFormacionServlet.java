/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.util.Gestor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author manue
 */
@WebServlet(name = "CargarArchivoFormacionServlet", urlPatterns = {"/CargarArchivoFormacionServlet"})
public class CargarArchivoFormacionServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");
    private final Gestor doc = new Gestor();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String folder = doc.leerProperties("ArchivosFormacion");
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
                        rutaArchivo = "http://127.0.0.1:8887/" + "Expediente" + expediente + "/" + name;
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.servlet;

import com.softcoisoweb.clase.AccionesExpediente;
import com.softcoisoweb.controller.CasoPersonaJpaController;
import com.softcoisoweb.controller.EstadoCasoJpaController;
import com.softcoisoweb.controller.FlujoCasoJpaController;
import com.softcoisoweb.controller.PersonaJpaController;
import com.softcoisoweb.controller.SeguimientoCasoJpaController;
import com.softcoisoweb.controller.TipoCasoJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.Calificacion;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.Medicamentos;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.SeguimientoCaso;
import com.softcoisoweb.model.TipoCaso;
import com.softcoisoweb.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
public class CasoServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");
    private final Random rand;

    public CasoServlet() throws NoSuchAlgorithmException {
        super();
        this.rand = SecureRandom.getInstanceStrong();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        String btnCrearCaso = request.getParameter("btnCrearCaso");
        String verCasos = request.getParameter("verCasos");
        String selectConsulta = request.getParameter("selectConsulta");
        String btnModificarCaso = request.getParameter("btnModificarCaso");
        String btnModificarCasoExp = request.getParameter("btnModificarCasoExp");
        String btnEliminarCaso = request.getParameter("btnEliminarCaso");

        try ( PrintWriter out = response.getWriter()) {
            if (btnCrearCaso != null && btnCrearCaso.equals("ok")) {
                String crear = crearCaso(request, response);
                out.print(crear);
            }
            if (selectConsulta != null) {
                String datos = consultarCaso(selectConsulta);
                out.print(datos);
            }
            if (verCasos != null && !verCasos.equals("")) {
                String cedulaPersona = verCasos;
                obtenerCaso(request, response, cedulaPersona, "No");
            }
            if (btnModificarCaso != null && btnModificarCaso.equals("ok")) {
                String modificar = modificarCaso(request, response, "");
                out.print(modificar);
            }
            if (btnModificarCasoExp != null && btnModificarCasoExp.equals("ok")) {
                String modificar = modificarCaso(request, response, "Expediente");
                out.print(modificar);
            }
            if (btnEliminarCaso != null) {
                String eliminarCaso = eliminarCaso(btnEliminarCaso);
                out.print(eliminarCaso);
            }
        }
    }

    private void obtenerCaso(HttpServletRequest request, HttpServletResponse response, String cedula, String cargar) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        String idCaso = null;
        try {
            CasoPersonaJpaController caso = new CasoPersonaJpaController(JPAFactory.getFACTORY());
            TipoCasoJpaController tipoCasoJpa = new TipoCasoJpaController(JPAFactory.getFACTORY());
            FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
            EstadoCasoJpaController estadoJpa = new EstadoCasoJpaController(JPAFactory.getFACTORY());
            List<CasoPersona> listCasos = caso.casosxpersona(cedula);
            idCaso = listCasos.get(0).getIdCaso();
            TipoCaso tipoCaso = tipoCasoJpa.findTipoCaso(listCasos.get(0).getTipoCasoCodigoTipoCaso());
            FlujoCaso flujo = flujoJpa.findFlujoCaso(idCaso);
            EstadoCaso estado = estadoJpa.findEstadoCaso(flujo.getEstadoCasoCodigoEstado());
            session.setAttribute("Casos", listCasos);
            PersonaJpaController personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
            Persona persona = personajpa.findPersona(cedula);
            String nombrePersona = persona.getNombrePersona() + " " + persona.getApellidoPersona();
            session.setAttribute("PersonaNombre", nombrePersona);
            session.setAttribute("cedulaPersona", cedula);
            session.setAttribute("nombreTipoCaso", tipoCaso.getTipoCaso());
            session.setAttribute("nombreEstado", estado.getNombreEstado());
            if (!cargar.equals("Cargar")) {
                rd = request.getRequestDispatcher("views/caso.jsp");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error obteniendo datos  del caso:  {0} El error es: {1}", new Object[]{idCaso, e});
            rd = request.getRequestDispatcher("views/persona.jsp");
        }
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    private String crearCaso(HttpServletRequest request, HttpServletResponse response) throws ParseException, Exception {
        String respuesta;
        PersonaServlet persona = new PersonaServlet();
        String cedulaPersona = request.getParameter("cedulaPersona");
        String cedulaUsuario = request.getParameter("cedulaUsuario");
        String tipo = request.getParameter("Tipo");
        String fechaAfectacion = request.getParameter("fechaAfectacion");
        String parteAfectada = request.getParameter("parteAfectada");
        String tiempoIncapacidad = request.getParameter("tiempoInca");
        String descripcionCaso = request.getParameter("descripcionCaso");

        try {
            //Se buscaPrimero el caso, para agregarlo
            CasoPersonaJpaController casoJpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
            CasoPersona buscarCodigo = casoJpa.findCasoPersona(cedulaPersona);
            Integer codigoCaso = Integer.parseInt(cedulaPersona);
            int codigo = Integer.parseInt(cedulaPersona);
            if (buscarCodigo != null) {
                int valorDado = this.rand.nextInt(10) + 1;
                codigo = codigoCaso + valorDado;
            }
            CasoPersona caso = new CasoPersona(String.valueOf(codigo), fechaAfectacion, parteAfectada, tiempoIncapacidad,
                    cedulaUsuario, cedulaUsuario, cedulaPersona, tipo, descripcionCaso);
            casoJpa.create(caso);
            String gestionCaso = gestionCaso("Crear", String.valueOf(codigo), cedulaUsuario, "", "");
            String casoAsociado = persona.agregarCaso(cedulaPersona);
            if (gestionCaso.equals("Exitoso") && casoAsociado.equals("Exitoso")) {
                respuesta = "Exitoso";
                persona.cargarDatos(request, response);
            } else {
                respuesta = "Error";
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error creando el caso, el error es  {0}", new Object[]{e});
            respuesta = "Error";
        }

        return respuesta;
    }

    private String gestionCaso(String operacion, String casoId, String usuario, String estado, String fechaCracion) {
        String respuesta;
        String estadoCaso = "101";
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController gestionCasoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());
        AccionesExpediente accionesExpediente = new AccionesExpediente();
        SeguimientoCaso gestionCaso;
        try {
            String fechaActual = accionesExpediente.getFecha();
            String nombreUsuario = accionesExpediente.getUsuarioSession(usuario);
            if (operacion.equals("Crear")) {
                FlujoCaso flujoCaso = new FlujoCaso(fechaActual, fechaActual, casoId, usuario, estadoCaso);
                flujoJpa.create(flujoCaso);
                gestionCaso = new SeguimientoCaso(casoId, "Se crea el caso", usuario, nombreUsuario, fechaActual);
            } else {
                FlujoCaso flujoCaso = new FlujoCaso(fechaCracion, fechaActual, casoId, usuario, estado);
                flujoJpa.edit(flujoCaso);
                gestionCaso = new SeguimientoCaso(casoId, "Se edita el caso", usuario, nombreUsuario, fechaActual);
            }
            gestionCasoJpa.create(gestionCaso);
            respuesta = "Exitoso";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creando el caso, el error es  {0}", new Object[]{e});
            respuesta = "Error";
        }

        return respuesta;
    }

    private String consultarCaso(String codigCaso) {
        String respuesta;
        try {
            CasoPersonaJpaController casoJpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
            CasoPersona caso = casoJpa.findCasoPersona(codigCaso);

            respuesta = caso.getIdCaso() + "#" + caso.getTipoCasoCodigoTipoCaso() + "#" + caso.getFechaAfectacion() + "#" + caso.getParteEfectada()
                    + "#" + caso.getTimepoIncapacidad() + "#" + caso.getDescripcionCaso() + "#" + caso.getCreadoPor()
                    + "#" + caso.getAsignado() + "#" + caso.getPersonaCedula();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error consultado el caso, el error es  {0}", new Object[]{e});
            respuesta = "Error";
        }
        return respuesta;
    }

    public String modificarCaso(HttpServletRequest request, HttpServletResponse response, String expediente) {
        String respuesta;
        String idCaso = request.getParameter("IdCaso");
        String tipoCaso = request.getParameter("tipoCaso");
        String casoFechaAfectacion = request.getParameter("casoFechaAfectacion");
        String casoParteAfectada = request.getParameter("casoParteAfectada");
        String casoTimpoInca = request.getParameter("casoTimpoInca");
        String casoDescripcion = request.getParameter("casoDescripcion");
        String casoCedulaPersona = request.getParameter("casoCedulaPersona");
        String creadoPor = request.getParameter("CreadoPor");
        String asignado = request.getParameter("Asignado");
        String cedulaUsuario = request.getParameter("cedulaUsuario");
        try {
            CasoPersonaJpaController casoJpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
            FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());

            CasoPersona caso = new CasoPersona(idCaso, casoFechaAfectacion, casoParteAfectada, casoTimpoInca,
                    creadoPor, asignado, casoCedulaPersona, tipoCaso, casoDescripcion);
            casoJpa.edit(caso);

            FlujoCaso flujo = flujoJpa.findFlujoCaso(idCaso);
            String gestionCaso = gestionCaso("Actualizar", idCaso, cedulaUsuario, flujo.getEstadoCasoCodigoEstado(), flujo.getFechaCreacion());
            if (gestionCaso.equals("Exitoso")) {
                respuesta = "Exitoso";
                if (expediente.equals("Expediente")) {
                    ExpedienteServlet cargarExpediente = new ExpedienteServlet();
                    cargarExpediente.buscarExpediente(request, response, idCaso, "Cargar");
                } else {
                    obtenerCaso(request, response, casoCedulaPersona, "Cargar");
                }

            } else {
                respuesta = "Error";
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error modificando el caso:  {0} El error es: {1}", new Object[]{idCaso, e});
            respuesta = "Error";
        }

        return respuesta;
    }

    private String eliminarCaso(String casoId) {
        String resultado;
        CasoPersonaJpaController casoJpa = new CasoPersonaJpaController(JPAFactory.getFACTORY());
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        PersonaJpaController Personajpa = new PersonaJpaController(JPAFactory.getFACTORY());
        try {
            List<ProcesoCalificacion> casoXproceso = casoJpa.casoXproceso(casoId);
            List<Calificacion> casoXcalificacion = casoJpa.casoXcalificacion(casoId);
            List<ProcesoReclamacion> casoXreclamacion = casoJpa.casoXreclamacion(casoId);
            List<Medicamentos> casoXmedicamentos = casoJpa.casoXmedicamentos(casoId);
            List<Diagnostico> casoXdiagnostico = casoJpa.casoXdiagnostico(casoId);
            if (!casoXproceso.isEmpty()) {
                resultado = "1";
            } else if (!casoXcalificacion.isEmpty()) {
                resultado = "2";
            } else if (!casoXreclamacion.isEmpty()) {
                resultado = "3";
            } else if (!casoXmedicamentos.isEmpty()) {
                resultado = "4";
            } else if (!casoXdiagnostico.isEmpty()) {
                resultado = "5";
            } else {
                CasoPersona caso = casoJpa.findCasoPersona(casoId);

                casoJpa.destroy(casoId);
                flujoJpa.destroy(casoId);
                casoJpa.eliminarArchivos(casoId);
                casoJpa.eliminarComentarios(casoId);
                casoJpa.eliminarHistoricoEstado(casoId);
                casoJpa.eliminarSeguimiento(casoId);
                List<CasoPersona> personaXcaso = Personajpa.personaXcaso(caso.getPersonaCedula());
                if (personaXcaso.isEmpty()) {
                    casoJpa.cambiarCasoAsociado(caso.getPersonaCedula());
                }
                resultado = "0";
            }
        } catch (NonexistentEntityException e) {
            LOGGER.log(Level.SEVERE, "Error eliminando el caso:  {0} El error es: {1}", new Object[]{casoId, e});
            resultado = "6";
        }
        return resultado;
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

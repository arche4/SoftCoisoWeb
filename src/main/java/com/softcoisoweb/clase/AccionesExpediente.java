package com.softcoisoweb.clase;

import com.softcoisoweb.controller.FlujoCasoJpaController;
import com.softcoisoweb.controller.SeguimientoCasoJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.SeguimientoCaso;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class AccionesExpediente {

    private final static Logger LOGGER = Logger.getLogger("LogsErrores");

    public void guardarAccionesExpediente(String casoId, String usuarioCedula, String accion) {
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController seguimientoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());

        String fechaActual = getFecha();
        try {
            String nombreUsuario = getUsuarioSession(usuarioCedula);
            SeguimientoCaso seguimiento = new SeguimientoCaso(casoId, accion, usuarioCedula, nombreUsuario, fechaActual);
            seguimientoJpa.create(seguimiento);
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error agreando actividad del expediente::  {0} El error es: {1}", new Object[]{casoId, e});
        }
        try {
            flujoJpa.actualizarFechaFlujoCaso(casoId, fechaActual, usuarioCedula);
        } catch (NonexistentEntityException e) {
            LOGGER.log(Level.SEVERE, "Se presento un error agreando actividad del expediente:  {0} El error es: {1}", new Object[]{casoId, e});

        }
    }

    public String getUsuarioSession(String cedula) {
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        String nombreUsuario = null;
        try {

            Usuario usuario = usuarioJpa.findUsuario(cedula);
            nombreUsuario = usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se presento un error buscando el usuario en sesion,  El Error es:  {0}", new Object[]{e});
        }

        return nombreUsuario;
    }

    public String getFecha() {
        String fecha;
        String fechaResultado = null;

        try {
            String fechaActual = obtenerFechaActual();
            String horaActual = obtenerHoraActual();
            SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatterFecha.parse(fechaActual);
            fecha = formatterFecha2.format(date);
            fechaResultado = fecha + " " + horaActual;
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Error obteniendo la fechaa actual, El error es:  {0}", new Object[]{e});

        }
        return fechaResultado;

    }

    private String obtenerFechaActual() {
        final Calendar capturarFecha = Calendar.getInstance();
        return Integer.toString(capturarFecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturarFecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturarFecha.get(Calendar.DATE)));
    }

    private String agregarCerosIzquierda(final String diames) {
        final StringBuilder  retorno = new StringBuilder ();
        if (Integer.parseInt(diames) < 10) {
            final String add = "0" + diames;
            retorno.append(add);
        } else {
            retorno.append(diames);
        }
        return retorno.toString();
    }

    private String obtenerHoraActual() {
        final Time sqlTime = new Time(new java.util.Date().getTime());
        return sqlTime.toString();
    }

}

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

/**
 *
 * @author manue
 */
public class AccionesExpediente {

    public void guardarAccionesExpediente(String casoId, String usuarioCedula, String accion) {
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController seguimientoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());

        String fechaActual = ObtenerFecha();
        try {
            String nombreUsuario = getUsuarioSession(usuarioCedula);
            SeguimientoCaso seguimiento = new SeguimientoCaso(casoId, accion, usuarioCedula, nombreUsuario, fechaActual);
            seguimientoJpa.create(seguimiento);
        } catch (Exception e) {
            System.err.println("Se presento un error agreando actividad del expediente: " + casoId + " El Error es: " + e);
        }
        try {
            flujoJpa.actualizarFechaFlujoCaso(casoId, fechaActual, usuarioCedula);
        } catch (NonexistentEntityException e) {
            System.err.println("Se presento un error agreando actividad del expediente: " + casoId + " El Error es: " + e);

        }
    }

    public String getUsuarioSession(String cedula) {
        UsuarioJpaController usuarioJpa = new UsuarioJpaController(JPAFactory.getFACTORY());
        String nombreUsuario = null;
        try {

            Usuario usuario = usuarioJpa.findUsuario(cedula);
            nombreUsuario = usuario.getNombreUsuario() + " " + usuario.getApellidoUsuario();
        } catch (Exception e) {
            System.err.println("Se presento un error buscando el usuario en sesion,  El Error es: " + e);
        }

        return nombreUsuario;
    }

    public String ObtenerFecha() {
        String fecha, fechaResultado = null;
        try {
            String fechaActual = obtenerFechaActual();
            String horaActual = obtenerHoraActual();
            SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatterFecha.parse(fechaActual);
            fecha = formatterFecha2.format(date);
            fechaResultado = fecha + " " + horaActual;
        } catch (ParseException e) {
            System.err.println("Error obteniendo la fechaa actual, El error es:" + e);

        }
        return fechaResultado;

    }

    private String obtenerFechaActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturar_fecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.DATE)));
    }

    private String agregarCerosIzquierda(final String diames) {
        final StringBuffer retorno = new StringBuffer();
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

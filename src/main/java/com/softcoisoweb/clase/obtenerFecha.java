/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author manue
 */
public class obtenerFecha {

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

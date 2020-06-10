/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softcoisoweb.clase;

import com.softcoisoweb.controller.FlujoCasoJpaController;
import com.softcoisoweb.controller.SeguimientoCasoJpaController;
import com.softcoisoweb.controller.UsuarioJpaController;
import com.softcoisoweb.controller.exceptions.NonexistentEntityException;
import com.softcoisoweb.model.SeguimientoCaso;
import com.softcoisoweb.model.Usuario;
import com.softcoisoweb.util.JPAFactory;

/**
 *
 * @author manue
 */
public class GestionarAccionesExpediente {

    public void guardarAccionesExpediente(String casoId, String usuarioCedula, String accion) {
        FlujoCasoJpaController flujoJpa = new FlujoCasoJpaController(JPAFactory.getFACTORY());
        SeguimientoCasoJpaController seguimientoJpa = new SeguimientoCasoJpaController(JPAFactory.getFACTORY());

        ObtenerFecha fecha = new ObtenerFecha();
        String fechaActual = fecha.ObtenerFecha();
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
}

package com.softcoisoweb.clase;

/**
 *
 * @author manue
 */
public class Expediente {
    String id_caso;
    String persona_cedula;
    String tipo_caso;
    String nombre_estado;

    public Expediente(String id_caso, String persona_cedula, String tipo_caso, String nombre_estado) {
        this.id_caso = id_caso;
        this.persona_cedula = persona_cedula;
        this.tipo_caso = tipo_caso;
        this.nombre_estado = nombre_estado;
    }
    
    public String getId_caso() {
        return id_caso;
    }

    public void setId_caso(String id_caso) {
        this.id_caso = id_caso;
    }

    public String getPersona_cedula() {
        return persona_cedula;
    }

    

    public void setPersona_cedula(String persona_cedula) {
        this.persona_cedula = persona_cedula;
    }

    public String getTipo_caso() {
        return tipo_caso;
    }

    public void setTipo_caso(String tipo_caso) {
        this.tipo_caso = tipo_caso;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
}

package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(Agendamiento.class)
public class Agendamiento_ { 

    public static volatile SingularAttribute<Agendamiento, String> descripcion;
    public static volatile SingularAttribute<Agendamiento, String> fechaCita;
    public static volatile SingularAttribute<Agendamiento, Usuario> usuarioCedula;
    public static volatile SingularAttribute<Agendamiento, String> hora;
    public static volatile SingularAttribute<Agendamiento, String> titulo;
    public static volatile SingularAttribute<Agendamiento, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<Agendamiento, CasoPersona> casoPersona;

}
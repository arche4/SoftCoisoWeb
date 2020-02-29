package com.softcoisoweb.model;

import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(Formacion.class)
public class Formacion_ { 

    public static volatile SingularAttribute<Formacion, String> fechaFormacion;
    public static volatile SingularAttribute<Formacion, Usuario> usuarioCedula;
    public static volatile SingularAttribute<Formacion, String> tema;
    public static volatile SingularAttribute<Formacion, String> archivo;
    public static volatile SingularAttribute<Formacion, Integer> idFormacion;
    public static volatile SingularAttribute<Formacion, String> tipoFormacion;
    public static volatile SingularAttribute<Formacion, String> correoFormador;
    public static volatile SingularAttribute<Formacion, String> formador;
    public static volatile SingularAttribute<Formacion, String> numeroAsistente;

}
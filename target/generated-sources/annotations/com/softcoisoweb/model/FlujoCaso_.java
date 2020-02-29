package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(FlujoCaso.class)
public class FlujoCaso_ { 

    public static volatile SingularAttribute<FlujoCaso, Usuario> usuarioCedula;
    public static volatile SingularAttribute<FlujoCaso, String> fechaCreacion;
    public static volatile SingularAttribute<FlujoCaso, String> fechaActualizacion;
    public static volatile SingularAttribute<FlujoCaso, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<FlujoCaso, EstadoCaso> estadoCasoCodigoEstado;
    public static volatile SingularAttribute<FlujoCaso, CasoPersona> casoPersona;

}
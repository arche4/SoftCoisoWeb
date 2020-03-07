package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.EstadoCaso;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-07T17:20:31")
@StaticMetamodel(CambioCaso.class)
public class CambioCaso_ { 

    public static volatile SingularAttribute<CambioCaso, Usuario> usuarioCedula;
    public static volatile SingularAttribute<CambioCaso, String> fechaActualizacion;
    public static volatile SingularAttribute<CambioCaso, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<CambioCaso, EstadoCaso> estadoCasoCodigoEstado;
    public static volatile SingularAttribute<CambioCaso, CasoPersona> casoPersona;

}
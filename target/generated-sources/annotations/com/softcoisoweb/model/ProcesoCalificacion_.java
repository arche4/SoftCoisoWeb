package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(ProcesoCalificacion.class)
public class ProcesoCalificacion_ { 

    public static volatile SingularAttribute<ProcesoCalificacion, Usuario> usuarioCedula;
    public static volatile SingularAttribute<ProcesoCalificacion, String> archivo;
    public static volatile SingularAttribute<ProcesoCalificacion, String> diagnostico;
    public static volatile SingularAttribute<ProcesoCalificacion, String> porcentaje;
    public static volatile SingularAttribute<ProcesoCalificacion, String> comentario;
    public static volatile SingularAttribute<ProcesoCalificacion, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<ProcesoCalificacion, CasoPersona> casoPersona;

}
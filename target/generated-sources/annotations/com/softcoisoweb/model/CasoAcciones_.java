package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(CasoAcciones.class)
public class CasoAcciones_ { 

    public static volatile SingularAttribute<CasoAcciones, Usuario> usuarioCedula;
    public static volatile SingularAttribute<CasoAcciones, String> archivo;
    public static volatile SingularAttribute<CasoAcciones, String> fechaActualizada;
    public static volatile SingularAttribute<CasoAcciones, String> comentario;
    public static volatile SingularAttribute<CasoAcciones, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<CasoAcciones, CasoPersona> casoPersona;

}
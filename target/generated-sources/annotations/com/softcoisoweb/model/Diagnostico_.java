package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:49:15")
@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ { 

    public static volatile SingularAttribute<Diagnostico, Usuario> usuarioCedula;
    public static volatile SingularAttribute<Diagnostico, String> diagnostico;
    public static volatile SingularAttribute<Diagnostico, String> fechaActualizada;
    public static volatile SingularAttribute<Diagnostico, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<Diagnostico, CasoPersona> casoPersona;

}
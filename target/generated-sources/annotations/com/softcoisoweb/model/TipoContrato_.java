package com.softcoisoweb.model;

import com.softcoisoweb.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:49:15")
@StaticMetamodel(TipoContrato.class)
public class TipoContrato_ { 

    public static volatile SingularAttribute<TipoContrato, String> descripcion;
    public static volatile SingularAttribute<TipoContrato, String> codigoTipoContrato;
    public static volatile CollectionAttribute<TipoContrato, Persona> personaCollection;
    public static volatile SingularAttribute<TipoContrato, String> nombre;

}
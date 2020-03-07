package com.softcoisoweb.model;

import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.FlujoCaso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-07T17:20:31")
@StaticMetamodel(EstadoCaso.class)
public class EstadoCaso_ { 

    public static volatile SingularAttribute<EstadoCaso, String> descripcion;
    public static volatile SingularAttribute<EstadoCaso, String> nombreEstado;
    public static volatile CollectionAttribute<EstadoCaso, FlujoCaso> flujoCasoCollection;
    public static volatile SingularAttribute<EstadoCaso, String> codigoEstado;
    public static volatile CollectionAttribute<EstadoCaso, CambioCaso> cambioCasoCollection;

}
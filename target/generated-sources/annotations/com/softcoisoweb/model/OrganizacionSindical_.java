package com.softcoisoweb.model;

import com.softcoisoweb.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(OrganizacionSindical.class)
public class OrganizacionSindical_ { 

    public static volatile SingularAttribute<OrganizacionSindical, String> codigoOrganizacion;
    public static volatile CollectionAttribute<OrganizacionSindical, Persona> personaCollection;
    public static volatile SingularAttribute<OrganizacionSindical, String> nombre;

}
package com.softcoisoweb.model;

import com.softcoisoweb.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:49:15")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> codigoEmpresa;
    public static volatile CollectionAttribute<Empresa, Persona> personaCollection;
    public static volatile SingularAttribute<Empresa, String> sectorEconomico;
    public static volatile SingularAttribute<Empresa, String> nitEmpresa;
    public static volatile SingularAttribute<Empresa, String> nombreEmpresa;

}
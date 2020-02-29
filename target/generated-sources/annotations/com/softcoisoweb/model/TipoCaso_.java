package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(TipoCaso.class)
public class TipoCaso_ { 

    public static volatile SingularAttribute<TipoCaso, String> descripcion;
    public static volatile CollectionAttribute<TipoCaso, CasoPersona> casoPersonaCollection;
    public static volatile SingularAttribute<TipoCaso, String> codigoTipoCaso;
    public static volatile SingularAttribute<TipoCaso, String> tipoCaso;

}
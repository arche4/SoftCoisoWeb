package com.softcoisoweb.model;

import com.softcoisoweb.model.MedicamentosCaso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(Medicamentos.class)
public class Medicamentos_ { 

    public static volatile SingularAttribute<Medicamentos, String> codigoMedicamento;
    public static volatile SingularAttribute<Medicamentos, String> descripcionMedicamento;
    public static volatile SingularAttribute<Medicamentos, String> nombreMedicamento;
    public static volatile CollectionAttribute<Medicamentos, MedicamentosCaso> medicamentosCasoCollection;

}
package com.softcoisoweb.model;

import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Medicamentos;
import com.softcoisoweb.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-29T15:07:41")
@StaticMetamodel(MedicamentosCaso.class)
public class MedicamentosCaso_ { 

    public static volatile SingularAttribute<MedicamentosCaso, Usuario> usuarioCedula;
    public static volatile SingularAttribute<MedicamentosCaso, String> casoPersonaIdCaso;
    public static volatile SingularAttribute<MedicamentosCaso, String> comentario;
    public static volatile SingularAttribute<MedicamentosCaso, String> fechaMedicamento;
    public static volatile SingularAttribute<MedicamentosCaso, CasoPersona> casoPersona;
    public static volatile SingularAttribute<MedicamentosCaso, Medicamentos> medicamentosCodigoMedicamento;

}
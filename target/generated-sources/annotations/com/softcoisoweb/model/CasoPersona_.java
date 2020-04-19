package com.softcoisoweb.model;

import com.softcoisoweb.model.Agendamiento;
import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.CasoAcciones;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.Persona;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
import com.softcoisoweb.model.TipoCaso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:49:15")
@StaticMetamodel(CasoPersona.class)
public class CasoPersona_ { 

    public static volatile SingularAttribute<CasoPersona, String> descripcionCaso;
    public static volatile SingularAttribute<CasoPersona, String> asignado;
    public static volatile SingularAttribute<CasoPersona, MedicamentosCaso> medicamentosCaso;
    public static volatile SingularAttribute<CasoPersona, CasoAcciones> casoAcciones;
    public static volatile SingularAttribute<CasoPersona, String> parteEfectada;
    public static volatile SingularAttribute<CasoPersona, ProcesoCalificacion> procesoCalificacion;
    public static volatile SingularAttribute<CasoPersona, Persona> personaCedula;
    public static volatile SingularAttribute<CasoPersona, String> creadoPor;
    public static volatile SingularAttribute<CasoPersona, Agendamiento> agendamiento;
    public static volatile SingularAttribute<CasoPersona, CambioCaso> cambioCaso;
    public static volatile SingularAttribute<CasoPersona, ProcesoReclamacion> procesoReclamacion;
    public static volatile SingularAttribute<CasoPersona, FlujoCaso> flujoCaso;
    public static volatile SingularAttribute<CasoPersona, Diagnostico> diagnostico;
    public static volatile SingularAttribute<CasoPersona, String> idCaso;
    public static volatile SingularAttribute<CasoPersona, String> fechaAfectacion;
    public static volatile SingularAttribute<CasoPersona, TipoCaso> tipoCasoCodigoTipoCaso;

}
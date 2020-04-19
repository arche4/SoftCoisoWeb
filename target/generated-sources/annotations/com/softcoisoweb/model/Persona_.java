package com.softcoisoweb.model;

import com.softcoisoweb.model.Afp;
import com.softcoisoweb.model.Arl;
import com.softcoisoweb.model.CasoPersona;
import com.softcoisoweb.model.Empresa;
import com.softcoisoweb.model.Eps;
import com.softcoisoweb.model.OrganizacionSindical;
import com.softcoisoweb.model.PersonaDireccion;
import com.softcoisoweb.model.TipoContrato;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T21:49:15")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, TipoContrato> tipoContratoCodigoTipoContrato;
    public static volatile SingularAttribute<Persona, String> apellidoPersona;
    public static volatile CollectionAttribute<Persona, CasoPersona> casoPersonaCollection;
    public static volatile SingularAttribute<Persona, Empresa> empresaCodigoEmpresa;
    public static volatile SingularAttribute<Persona, String> fechaNacimiento;
    public static volatile SingularAttribute<Persona, String> antiguedadEmpresa;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile SingularAttribute<Persona, String> casoAsociado;
    public static volatile SingularAttribute<Persona, String> nombrePersona;
    public static volatile SingularAttribute<Persona, String> fechaClinica;
    public static volatile SingularAttribute<Persona, String> edad;
    public static volatile SingularAttribute<Persona, Arl> arlCodigoArl;
    public static volatile SingularAttribute<Persona, Eps> epsCodigoEps;
    public static volatile SingularAttribute<Persona, OrganizacionSindical> organizacionSindicalCodigoOrganizacion;
    public static volatile SingularAttribute<Persona, PersonaDireccion> personaDireccion;
    public static volatile SingularAttribute<Persona, Afp> afpCodigoAfp;
    public static volatile SingularAttribute<Persona, String> genero;
    public static volatile SingularAttribute<Persona, String> correo;
    public static volatile SingularAttribute<Persona, String> recomendado;
    public static volatile SingularAttribute<Persona, String> cargo;
    public static volatile SingularAttribute<Persona, String> telefono;

}
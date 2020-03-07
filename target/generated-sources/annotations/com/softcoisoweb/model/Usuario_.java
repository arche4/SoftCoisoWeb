package com.softcoisoweb.model;

import com.softcoisoweb.model.Agendamiento;
import com.softcoisoweb.model.CambioCaso;
import com.softcoisoweb.model.CasoAcciones;
import com.softcoisoweb.model.Diagnostico;
import com.softcoisoweb.model.FlujoCaso;
import com.softcoisoweb.model.Formacion;
import com.softcoisoweb.model.MedicamentosCaso;
import com.softcoisoweb.model.ProcesoCalificacion;
import com.softcoisoweb.model.ProcesoReclamacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-07T17:20:31")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile CollectionAttribute<Usuario, ProcesoCalificacion> procesoCalificacionCollection;
    public static volatile SingularAttribute<Usuario, String> apellidoUsuario;
    public static volatile CollectionAttribute<Usuario, FlujoCaso> flujoCasoCollection;
    public static volatile SingularAttribute<Usuario, String> cedula;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile CollectionAttribute<Usuario, MedicamentosCaso> medicamentosCasoCollection;
    public static volatile SingularAttribute<Usuario, String> rol;
    public static volatile CollectionAttribute<Usuario, Diagnostico> diagnosticoCollection;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile CollectionAttribute<Usuario, Agendamiento> agendamientoCollection;
    public static volatile CollectionAttribute<Usuario, Formacion> formacionCollection;
    public static volatile CollectionAttribute<Usuario, CasoAcciones> casoAccionesCollection;
    public static volatile CollectionAttribute<Usuario, ProcesoReclamacion> procesoReclamacionCollection;
    public static volatile CollectionAttribute<Usuario, CambioCaso> cambioCasoCollection;

}
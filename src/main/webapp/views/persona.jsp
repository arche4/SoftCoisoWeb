<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty user}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/index.jsp"/>
</c:if> 
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>SofCoiso-Personas</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->

        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <!--external css-->
        <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/jquery.dataTables.js"></script>

        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/demo_table.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/DT_bootstrap.css" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/gritter/css/jquery.gritter.css" />
        <link href="${pageContext.servletContext.contextPath}/css/general.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.servletContext.contextPath}/Loading.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.servletContext.contextPath}/css/wizard.css" rel="stylesheet" type="text/css"/>

        <link href="${pageContext.servletContext.contextPath}/lib/jquery-smartwizard-master/dist/css/smart_wizard.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.servletContext.contextPath}/lib/jquery-smartwizard-master/dist/css/smart_wizard_theme_arrows.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery-smartwizard-master/dist/js/jquery.smartWizard.min.js" type="text/javascript"></script>
        <!-- =======================================================
          Template Name: Dashio
          Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>

    <body>
        <section id="container">
            <!-- **********************************************************************************************************************************************************
                TOP BAR CONTENT & NOTIFICATIONS
                *********************************************************************************************************************************************************** -->
            <!--header start-->
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="${pageContext.servletContext.contextPath}/views/dashboard.jsp" class="logo"><b>SOF<span>COISO</span></b></a>

                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li><form action = "${pageContext.servletContext.contextPath}/CerrarSesionServlet" method = "post">
                                <button id="btnExit"  class="logout" type="submit">Cerrar sesi&oacute;n</button>
                            </form></li>
                    </ul>
                </div>
            </header>
            <!--header end-->
            <!-- **********************************************************************************************************************************************************
                MAIN SIDEBAR MENU
                *********************************************************************************************************************************************************** -->
            <!--sidebar start-->
            <aside>
                <div id="sidebar" class="nav-collapse ">
                    <!-- sidebar menu start-->
                    <ul class="sidebar-menu" id="nav-accordion">
                        <p class="centered"><a href="profile.html"><img src="${pageContext.servletContext.contextPath}/img/ui-sam.jpg" class="img-circle" width="80"></a></p>
                        <h5 class="centered">${sessionScope.USUARIO.nombreUsuario} ${sessionScope.USUARIO.apellidoUsuario}</h5>
                        <li class="mt">
                            <a href="${pageContext.servletContext.contextPath}/views/dashboard.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-calendar"></i>
                                <span>Agendamiento</span>
                            </a>
                            <ul class="sub">
                                <li><a href="${pageContext.servletContext.contextPath}/views/calendar.jsp">Citas</a></li>
                                <li><a href="${pageContext.servletContext.contextPath}/views/calendar.jsp">Formaciones</a></li>
                                <li><a href="panels.html">Planeacion</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a class="active" href="${pageContext.servletContext.contextPath}/views/persona.jsp">
                                <i class="fa fa-users"></i>
                                <span>Persona</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-print"></i>
                                <span>Reportes del Sistema</span>
                            </a>
                            <ul class="sub">
                                <li><a href="${pageContext.servletContext.contextPath}/views/usuario.jsp">Reporte</a></li>
                                <li><a href="usuario.jsp">Reporte Medicamentos</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-desktop"></i>
                                <span>Modulos Administrativos</span>
                            </a>
                            <ul class="sub">
                                <li ><a href="${pageContext.servletContext.contextPath}/views/usuario.jsp">Usuarios</a></li>
                                <li ><a href="usuario.jsp">Formaciones</a></li>
                                <li><a href="panels.html">Tipo de casos</a></li>
                                <li><a href="font_awesome.html">Estados de caso</a></li>
                                <li><a href="font_awesome.html">Medicamentos</a></li>
                            </ul>
                        </li>

                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>
            <!--sidebar end-->
            <!-- **********************************************************************************************************************************************************
                MAIN CONTENT
                *********************************************************************************************************************************************************** -->
            <!--main content start-->

            <section id="main-content">
                <section class="wrapper">
                    <h3><i class="fa fa-angle-right"></i> Personas </h3>
                    <div class="row mb">
                        <!-- page start-->

                        <button type="button" class="btn btn-theme" data-toggle="modal" data-target="#personaModal" style="margin: 10px;">Registrar Persona</button>
                        <div class="alert alert-success" id="Exitoso" style="display:none;">
                            <strong>¡Bien hecho!</strong>Se guardo correctamente los datos.
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="personaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Registro Persona</h5> 
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="#" id="myForm" role="form" data-toggle="validator" method="post" accept-charset="utf-8">
                                            <div id="smartwizard">
                                                <ul>
                                                    <li><a href="#step-1">Paso 1<br /><small>Datos Personales</small></a></li>
                                                    <li><a href="#step-2">Paso 2<br /><small>Contacto</small></a></li>
                                                    <li><a href="#step-3">Paso 3<br /><small>Datos Empresa Y Salud</small></a></li>
                                                    <li><a href="#step-4">Paso 4<br /><small>Clinica</small></a></li>
                                                </ul>
                                                <div class="mt-4" style="margin: 10px;">
                                                    <div id="step-1">
                                                        <div id="form-step-0" role="form" data-toggle="validator">

                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Cedula</label>
                                                                <input type="number" class="form-control" id="cedula" name="cedula"  placeholder="Cedula" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Nombre</label>
                                                                <input type="text" class="form-control" id="nombrePersona" name="nombrePersona"  placeholder="Nombres" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Apellidos</label>
                                                                <input type="text" class="form-control" id="apellidos" name="apellidos"  placeholder="Apellidos" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Genero</label>
                                                                <select name="genero" id="genero" class="form-control" required>
                                                                    <option value="Femenimo" selected>Mujer</option>
                                                                    <option value="Masculino" selected>Hombre</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Edad</label>
                                                                <input type="text" class="form-control" name="edad" id="edad" placeholder="Edad">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Fecha  Nacimiento</label>
                                                                <input type="date" class="form-control" id="cumpleanos" name="cumpleanos" placeholder="MM/DD/YYY" id="example-month-input">
                                                            </div> 
                                                        </div>
                                                    </div>
                                                    <div id="step-2" style="margin: 10px;">
                                                        <div id="form-step-1" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Municipio</label>
                                                                <input type="text" class="form-control" name="municipio" id="municipio" placeholder="Municipio">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Barrio</label>
                                                                <input type="text" class="form-control" name="barrio" id="barrio" placeholder="Barrio">
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label class="control-label">Direccion</label>
                                                                <input type="text" class="form-control" name="direccion" id="direccion" placeholder="Direccion">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Telefono</label>
                                                                <input type="text" class="form-control" name="telefono" id="telefono" placeholder="Telefono">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Correo</label>
                                                                <input type="email" class="form-control" name="correo" id="correo" placeholder="Correo Electronico">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="step-3" style="margin: 10px;">
                                                        <div id="form-step-2" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Eps</label>
                                                                <select name="eps" id="eps" class="form-control-sm form-control" required>
                                                                    <option value="">EPS</option>
                                                                    <c:forEach var="eps" items="${sessionScope.EPS}">
                                                                        <option value="${eps.getCodigoEps()}"><c:out value="${eps.getNombreEps()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Arl</label>
                                                                <select name="arl" id="arl"  class="form-control-sm form-control" required>
                                                                    <option value="">ARL</option>
                                                                    <c:forEach var="arl" items="${sessionScope.ARL}">
                                                                        <option value="${arl.getCodigoArl()}"><c:out value="${arl.getNombreArl()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Afp</label>
                                                                <select name="afp" id="afp"  class="form-control-sm form-control" required>
                                                                    <option value="">AFP</option>
                                                                    <c:forEach var="afp" items="${sessionScope.AFP}">
                                                                        <option value="${afp.getCodigoAfp()}"><c:out value="${afp.getNombreAfp()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Tipo de Contrato</label>
                                                                <select name="contrato" id="contrato" class="form-control-sm form-control" required>
                                                                    <option value="">Contrato</option>
                                                                    <c:forEach var="contrato" items="${sessionScope.Contrato}">
                                                                        <option value="${contrato.getCodigoTipoContrato()}"><c:out value="${contrato.getNombre()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Organización Sindical</label>
                                                                <select name="sindicato" id="sindicato" class="form-control-sm form-control" required>
                                                                    <option value="">Sindical</option>
                                                                    <c:forEach var="sindicato" items="${sessionScope.Sindicato}">
                                                                        <option value="${sindicato.getCodigoOrganizacion()}"><c:out value="${sindicato.getNombre()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" id="empresa" name="empresa" placeholder="Empresa">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" id="empresaUsuaria" name="empresaUsuaria" placeholder="Empresa Usuaria">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" name="sectorEconomico" id="sectorEconomico" placeholder="Sector Economico">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Cargo</label>
                                                                <input type="text" class="form-control" id="cargo" name="cargo" placeholder="cargo" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Años Antiguedad</label>
                                                                <input type="text" class="form-control" name="anosExperiencia" id="anosExperiencia" placeholder="Años Experiencia">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="step-4" style="margin: 10px;">
                                                        <div id="form-step-3" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Fecha de Clinica</label>
                                                                <input type="date" class="form-control" id="FechaClinica" name="FechaClinica" placeholder="MM/DD/YYY" id="example-month-input" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Recomendado</label>
                                                                <input type="text" class="form-control" name="recomendado" id="recomendado" placeholder="Recomendado">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="verPersona" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Persona</h5> 
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
                                    </div>

                                    <div class="modal-body">
                                        <form action="#" id="formPerson" role="form" data-toggle="validator"  accept-charset="utf-8">
                                            <div id="wizzardMostrar">
                                                <ul>
                                                    <li><a href="#step1">Paso 1<br /><small>Datos Personales</small></a></li>
                                                    <li><a href="#step2">Paso 2<br /><small>Contacto</small></a></li>
                                                    <li><a href="#step3">Paso 3<br /><small>Datos Empresa Y Salud</small></a></li>
                                                    <li><a href="#step4">Paso 4<br /><small>Clinica</small></a></li>
                                                </ul>
                                                <div class="mt-4" style="margin: 10px;">
                                                    <div id="step1">
                                                        <div id="formstep-0" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Cedula</label>
                                                                <input type="number" class="form-control" id="cedulaPersona" name="cedulaPersona"  placeholder="Cedula" readonly>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Nombre</label>
                                                                <input type="text" class="form-control" id="PersonaNombre" name="PersonaNombre"  placeholder="Nombres" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Apellidos</label>
                                                                <input type="text" class="form-control" id="personaApellido" name="personaApellido"  placeholder="Apellidos" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Genero</label>
                                                                <select name="personaGenero" id="personaGenero" class="form-control" required>
                                                                    <option value="Femenimo" selected>Mujer</option>
                                                                    <option value="Masculino" selected>Hombre</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Edad</label>
                                                                <input type="text" class="form-control" name="personaEdad" id="personaEdad" placeholder="Edad">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Fecha  Nacimiento</label>
                                                                <input type="date" class="form-control" id="personaCumple" name="personaCumple" placeholder="MM/DD/YYY" id="example-month-input">
                                                            </div>   
                                                        </div>
                                                    </div>
                                                    <div id="step2">
                                                        <div id="formstep-1" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Municipio</label>
                                                                <input type="text" class="form-control" name="personaMunicipio" id="personaMunicipio" placeholder="Municipio">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Barrio</label>
                                                                <input type="text" class="form-control" name="personaBarrio" id="personaBarrio" placeholder="Barrio">
                                                            </div>
                                                            <div class="form-group col-md-12">
                                                                <label class="control-label">Direccion</label>
                                                                <input type="text" class="form-control" name="personaDir" id="personaDir" placeholder="Direccion">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Telefono</label>
                                                                <input type="text" class="form-control" name="personaTel" id="personaTel" placeholder="Telefono">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Correo</label>
                                                                <input type="email" class="form-control" name="personaEmail" id="personaEmail" placeholder="Correo Electronico">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="step3">
                                                        <div id="formstep-2" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Eps</label>
                                                                <select name="personEps" id="personEps" class="form-control-sm form-control" required>
                                                                    <option value="">EPS</option>
                                                                    <c:forEach var="personEps" items="${sessionScope.EPS}">
                                                                        <option value="${personEps.getCodigoEps()}"><c:out value="${personEps.getNombreEps()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Arl</label>
                                                                <select name="arlPerson" id="arlPerson"  class="form-control-sm form-control" required>
                                                                    <option value="">ARL</option>
                                                                    <c:forEach var="arlPerson" items="${sessionScope.ARL}">
                                                                        <option value="${arlPerson.getCodigoArl()}"><c:out value="${arlPerson.getNombreArl()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <label class="control-label">Afp</label>
                                                                <select name="personAfp" id="personAfp"  class="form-control-sm form-control" required>
                                                                    <option value="">AFP</option>
                                                                    <c:forEach var="personAfp" items="${sessionScope.AFP}">
                                                                        <option value="${personAfp.getCodigoAfp()}"><c:out value="${personAfp.getNombreAfp()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Tipo de Contrato</label>
                                                                <select name="personContrato" id="personContrato" class="form-control-sm form-control" required>
                                                                    <option value="">Contrato</option>
                                                                    <c:forEach var="personContrato" items="${sessionScope.Contrato}">
                                                                        <option value="${personContrato.getCodigoTipoContrato()}"><c:out value="${personContrato.getNombre()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Organización Sindical</label>
                                                                <select name="personSindicato" id="personSindicato" class="form-control-sm form-control" required>
                                                                    <option value="">Sindical</option>
                                                                    <c:forEach var="personSindicato" items="${sessionScope.Sindicato}">
                                                                        <option value="${personSindicato.getCodigoOrganizacion()}"><c:out value="${personSindicato.getNombre()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" id="personaEmp" name="personaEmp" placeholder="Empresa">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" id="personEmpresaUsuaria" name="personEmpresaUsuaria" placeholder="Empresa Usuaria">
                                                            </div>
                                                            <div class="form-group col-md-4">
                                                                <input type="text" class="form-control" name="PersonSectorEconomico" id="PersonSectorEconomico" placeholder="Sector Economico">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Cargo</label>
                                                                <input type="text" class="form-control" id="PersonCargo" name="PersonCargo" placeholder="cargo" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Años Antiguedad</label>
                                                                <input type="text" class="form-control" name="personExperiencia" id="personExperiencia" placeholder="Años Experiencia">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="step4">
                                                        <div id="formstep-3" role="form" data-toggle="validator">
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Fecha de Clinica</label>
                                                                <input type="date" class="form-control" id="personFechaClinica" name="personFechaClinica" placeholder="MM/DD/YYY" id="example-month-input" required>
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <label class="control-label">Recomendado</label>
                                                                <input type="text" class="form-control" name="personRecomendado" id="personRecomendado" placeholder="Recomendado">
                                                            </div>
                                                            <input class="form-control " id="casoAsociado" type="hidden" name="casoAsociado">
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="content-panel" style="margin: 10px;">
                            <div class="adv-table">
                                <table id="table_id" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th scope="col">Cedula</th>
                                            <th scope="col">Nombre</th>
                                            <th scope="col">Fecha Clinica</th>
                                            <th scope="col">Caso</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="persona" items="${sessionScope.Persona}" varStatus="myIndex">
                                            <c:choose>
                                                <c:when test="${persona.getCasoAsociado() == sessionScope.TieneCaso}">  
                                                    <tr class="gradeX odd">
                                                        <td>
                                                            <button id ="selectConsulta" name="selectConsulta" class="btn btn-link" value="${persona.getCedula()}"><c:out value="${persona.getCedula()}"/></button></td>
                                                        <td><c:out value="${persona.getNombrePersona()}"/> <c:out value="${persona.getApellidoPersona()}"/></td>
                                                        <td><c:out value="${persona.getFechaClinica()}"/></td>
                                                        <td>
                                                            <button  id ="casoCrear"  name="casoCrear"  class="btn btn-link" value="${persona.getCedula()}">
                                                                Crear Caso
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:when> 
                                                <c:otherwise>
                                                    <tr class="gradeA odd">
                                                        <td>
                                                            <button id ="selectConsulta" name="selectConsulta" class="btn btn-link" value="${persona.getCedula()}"><c:out value="${persona.getCedula()}"/></button>
                                                        </td>
                                                        <td><c:out value="${persona.getNombrePersona()}"/> <c:out value="${persona.getApellidoPersona()}"/></td>
                                                        <td><c:out value="${persona.getFechaClinica()}"/></td>
                                                        <td>
                                                            <form  method="post" action="${pageContext.servletContext.contextPath}/CasoServlet">
                                                                <button name="verCasos" value="${persona.getCedula()}" type="submit" class="btn btn-link">Ver detalles</button>
                                                            </form> 
                                                        </td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- page end-->
                    </div>
                    <!-- /row -->
                </section>
                <!-- /wrapper -->
            </section>
            <!-- /MAIN CONTENT -->
            <!--modales-->
            <div class="modal fade" id="crearCaso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Crear  Caso</h4>
                        </div>
                        <form id="caso" data-toggle="validator">
                            <br>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Tipo de Caso</label>
                                    <select name="Tipo" id="Tipo" class="form-control-sm form-control" required>
                                        <option value="">Tipo Caso </option>
                                        <c:forEach var="Tipo" items="${sessionScope.Tipo}">
                                            <option value="${Tipo.getCodigoTipoCaso()}"><c:out value="${Tipo.getTipoCaso()}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Fecha  Afectacion</label>
                                    <input type="text" class="form-control" id="fechaAfectacion" name="fechaAfectacion" placeholder="Inicio afectacion(meses)"  required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label class="control-label">Parte Afectada</label>
                                    <input  type="text" class="form-control"  name="parteAfectada" id="parteAfectada" placeholder="Parte del cuerpo afectada" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Tiempo de Incapacidad</label>
                                    <input type="text" class="form-control" id="tiempoInca" name="tiempoInca" placeholder="Tiempo de Incapacidad" >
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label class="control-label">Descripcion</label>
                                    <textarea class="form-control " rows="10" cols="50" id="descripcionCaso" name="descripcionCaso" required></textarea>
                                </div>
                            </div>  
                            <input class="form-control " id="cedulaPersona" type="hidden" name="cedulaPersona">
                            <input class="form-control " id="cedulaUsuario" type="hidden" name="cedulaUsuario" value="${sessionScope.USUARIO.cedula}">
                            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="guardarCaso()">
                                    Crear
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal" id="modalInfexito" abindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Operación Exitosa</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="personaEdit" id="persona" action="">
                                        <div class="modal-body" id="modInfexito">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <hr width="0%">
                                <button type="button" id="Guardar" class="btn btn-primary" onclick="myFunctionReload()">Ok</button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modalValidar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">

                    <!-- Modal content-->
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmar Cambios</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="modConfirmar" id="modConfirmar" action="">
                                        <div class="modal-body" id="InfoConfirmar">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="Modificar()">
                                    Si
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">

                    <!-- Modal content-->
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmar Cambios</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="modConfirmar" id="modConfirmar" action="">
                                        <div class="modal-body" id="InfoEliminar">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="Eliminar()">
                                    Si
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal" id="modalDatosIncorrectos" abindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Operación Erronea</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="personaEdit" id="persona" action="">
                                        <div class="modal-body" id="modMensaje">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <hr width="0%">
                                <button type="button" id="Guardar" class="btn btn-primary" onclick="cerrarModal()">Ok</button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal" id="modalInfError" abindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Operación Erronea</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="personaEdit" id="persona" action="">
                                        <div class="modal-body" id="modInferror">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <hr width="0%">
                                <button type="button" id="Guardar" class="btn btn-primary" onclick="myFunctionReload()">Ok</button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
            <footer class="site-footer">
                <div class="text-center">
                    <p>
                        &copy; Derechos de autor <strong>Coiso</strong>. ©2020 Todos los derechos reservados.
                    </p>
                    <div class="credits">
                        Corporación colectivo intersindical de salud ocupacional. Privacidad y términos <a href="http://www.coiso.org/"></a>
                    </div>
                    <a href="http://www.coiso.org/" class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div>
            </footer>
            <!--footer end-->
        </section>
        <div class="loader" id="loader" style="display:none"></div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/validator.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/JavaScript/persona.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/DT_bootstrap.js"></script>

        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>

        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter-conf.js"></script>

    </body>
</html>

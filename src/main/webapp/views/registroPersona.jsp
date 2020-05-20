<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty user}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/index.jsp"/>
</c:if> 
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset='utf-8' />
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>SOFCOISO</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/bootstrap-datepicker/css/datepicker.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/bootstrap-daterangepicker/daterangepicker.css" />

        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/general.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <section id="container">
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="index.html" class="logo"><b>SOF<span>COISO</span></b></a>

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
                            <a  href="${pageContext.servletContext.contextPath}/views/dashboard.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-desktop"></i>
                                <span>Agendamiento</span>
                            </a>
                            <ul class="sub">
                                <li><a href="${pageContext.servletContext.contextPath}/views/calendar.jsp">Citas</a></li>
                                <li><a href="${pageContext.servletContext.contextPath}/views/formacion.jsp">Formaciones</a></li>
                                <li><a href="panels.html">Planeacion</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-desktop"></i>
                                <span>Persona</span>
                            </a>
                            <ul class="sub">
                                <li><a href="${pageContext.servletContext.contextPath}/views/registroPersona.jsp">Registrar Personas</a></li>
                                <li><a href="${pageContext.servletContext.contextPath}/views/persona.jsp">Personas</a></li>
                            </ul>
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
                                <li><a href="${pageContext.servletContext.contextPath}/views/usuario.jsp">Usuarios</a></li>
                                <li><a href="usuario.jsp">Formaciones</a></li>
                                <li><a href="panels.html">Tipo de casos</a></li>
                                <li><a href="font_awesome.html">Estados de caso</a></li>
                                <li><a href="font_awesome.html">Medicamentos</a></li>
                            </ul>
                        </li>

                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>
            <section id="main-content">
                <section class="wrapper">
                    <h3><i class="fa fa-angle-right"></i> Registrar Persona</h3>
                    <!-- page start-->
                    <!--Alertas -->
                    <div class="alert alert-danger" id="Error" style="display:none;">
                        <strong>Error! </strong>Debes seleccionar una fecha correcta.
                    </div>
                    <div class="alert alert-danger" id="ErrorGuardando" style="display:none;">
                        <strong>Error! </strong>Se presento un error guardando la cita.
                    </div>
                    <div class="alert alert-danger" id="ErrorFechas" style="display:none;">
                        <strong>Error! </strong>La hora final de la cita es incorrecta.
                    </div>
                    <div class="alert alert-success" id="Exitoso" style="display:none;">
                        <strong>¡Bien hecho!</strong>Se guardo correctamente la cita.
                    </div>
                    <!-- page end-->

                    <div class="row mt">
                        <div class="col-lg-12">
                            <div>
                                <form>
                                    <div class="row content-panel mt mb" style="margin: 10px;">
                                        <h4 class="mb"><i class="fa fa-angle-right"></i>Datos Personales</h4>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Cedula</label>
                                                <input type="number" class="form-control" id="cedula" name="cedula"  placeholder="Cedula" required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Nombre</label>
                                                <input type="text" class="form-control" id="nombrePersona" name="nombrePersona"  placeholder="Nombres" required>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Apellidos</label>
                                                <input type="text" class="form-control" id="apellidos" name="apellidos"  placeholder="Apellidos" required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Genero</label>
                                                <select name="genero" id="genero" class="form-control">
                                                    <option value="Femenimo" selected>Mujer</option>
                                                    <option value="Masculino" selected>Hombre</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
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
                                    <div class="row content-panel mt mb" style="margin: 10px;">
                                        <h4 class="mb"><i class="fa fa-angle-right"></i>Contacto</h4>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Municipio</label>
                                                <input type="text" class="form-control" name="municipio" id="municipio" placeholder="Municipio">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Barrio</label>
                                                <input type="text" class="form-control" name="barrio" id="barrio" placeholder="Barrio">
                                            </div>

                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-10">
                                                <label class="control-label">Direccion</label>
                                                <input type="text" class="form-control" name="direccion" id="direccion" placeholder="Direccion">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Telefono</label>
                                                <input type="text" class="form-control" name="telefono" id="telefono" placeholder="Telefono">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Correo</label>
                                                <input type="text" class="form-control" name="correo" id="correo" placeholder="Correo Electronico">
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row content-panel mt mb" style="margin: 10px;">
                                        <h4 class="mb"><i class="fa fa-angle-right"></i>Datos Empresa Y Salud</h4>
                                        <div class="form-row">
                                            <div class="form-group col-md-4">
                                                <label class="control-label">Eps</label>
                                                <select name="eps" id="eps" class="form-control-sm form-control">
                                                    <option value="">EPS</option>
                                                    <c:forEach var="eps" items="${sessionScope.EPS}">
                                                        <option value="${eps.getCodigoEps()}"><c:out value="${eps.getNombreEps()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label class="control-label">Arl</label>
                                                <select name="arl" id="arl"  class="form-control-sm form-control">
                                                    <option value="">ARL</option>
                                                    <c:forEach var="arl" items="${sessionScope.ARL}">
                                                        <option value="${arl.getCodigoArl()}"><c:out value="${arl.getNombreArl()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label class="control-label">Afp</label>
                                                <select name="afp" id="afp"  class="form-control-sm form-control">
                                                    <option value="">AFP</option>
                                                    <c:forEach var="afp" items="${sessionScope.AFP}">
                                                        <option value="${afp.getNombreAfp()}"><c:out value="${afp.getNombreAfp()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tipo de Contrato</label>
                                                <select name="contrato" id="contrato" class="form-control-sm form-control">
                                                    <option value="">Contrato</option>
                                                    <c:forEach var="contrato" items="${sessionScope.Contrato}">
                                                        <option value="${contrato.getCodigoTipoContrato()}"><c:out value="${contrato.getNombre()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Empresa</label>
                                                    <select name="empresa" id="empresa" class="form-control-sm form-control">
                                                        <option value="">Empresa</option>
                                                        <c:forEach var="empresa" items="${sessionScope.Empresa}">
                                                            <option value="${empresa.getCodigoEmpresa()}"><c:out value="${empresa.getNombre()}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Organización Sindical</label>
                                                    <select name="sindicato" id="sindicato" class="form-control-sm form-control">
                                                        <option value="">Sindical</option>
                                                        <c:forEach var="sindicato" items="${sessionScope.Sindicato}">
                                                            <option value="${sindicato.getCodigoOrganizacion()}"><c:out value="${sindicato.getNombre()}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Cargo</label>
                                                    <input type="text" class="form-control" id="cargo" name="cargo" placeholder="cargo">
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Años Antiguedad</label>
                                                    <input type="text" class="form-control" name="anosExperiencia" id="anosExperiencia" placeholder="Años Experiencia">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row content-panel mt mb" style="margin: 10px;">
                                        <h4 class="mb"><i class="fa fa-angle-right"></i>Clinica</h4>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Fecha de Clinica</label>
                                                <input type="date" class="form-control" id="FechaClinica" name="FechaClinica" placeholder="MM/DD/YYY" id="example-month-input">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Recomendado</label>
                                                <input type="text" class="form-control" name="recomendado" id="recomendado" placeholder="Recomendado">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-2 col-lg-10">
                                                <button class="btn btn-success" type="submit" onclick="guardar()">Guardar Datos</button>
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- col-lg-12-->
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
                    <!--Modales --> 
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
                    <!-- /wrapper -->

                </section>
            </section>
            <div class="loader" id="loader"></div>
            <!-- js placed at the end of the document so the pages load faster -->
            <script src="${pageContext.servletContext.contextPath}/JavaScript/persona.js" type="text/javascript"></script>
            <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
            <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
            <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
            <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
            <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
            <!--common script for all pages-->
            <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
            <!--script for this page-->
            <script src="${pageContext.servletContext.contextPath}/lib/jquery-ui-1.9.2.custom.min.js"></script>
            <!--custom switch-->
            <script src="${pageContext.servletContext.contextPath}/lib/bootstrap-switch.js"></script>
            <!--custom tagsinput-->
            <script src="${pageContext.servletContext.contextPath}/lib/jquery.tagsinput.js"></script>
            <!--custom checkbox & radio-->
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/bootstrap-daterangepicker/date.js"></script>
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
            <script src="${pageContext.servletContext.contextPath}/lib/form-component.js"></script>

    </body>
</html>

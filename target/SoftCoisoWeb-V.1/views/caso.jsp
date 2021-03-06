<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty user}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/index.jsp"/>
</c:if> 
<!DOCTYPE html>
<html lang="en">

    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>SofCoiso-Personas</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
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
                        <p class="centered"><a href="${pageContext.servletContext.contextPath}/views/perfil.jsp"><img src="${pageContext.servletContext.contextPath}/img/icono-user.png" class="img-circle" width="80"></a></p>
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
                                <li><a href="${pageContext.servletContext.contextPath}/views/formacion.jsp">Formaciones</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a class="active" href="${pageContext.servletContext.contextPath}/views/persona.jsp">
                                <i class="fa fa-users"></i>
                                <span>Persona</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="${pageContext.servletContext.contextPath}/views/reportes.jsp">
                                <i class="fa fa-print"></i>
                                <span>Reportes del Sistema</span>
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${sessionScope.USUARIO.getRol() == sessionScope.rol}">
                                <li class="sub-menu">
                                    <a href="javascript:;">
                                        <i class="fa fa-desktop"></i>
                                        <span>Modulos Administrativos</span>
                                    </a>
                                    <ul class="sub">
                                        <li><a href="${pageContext.servletContext.contextPath}/views/usuario.jsp">Usuarios</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/medicamento.jsp">Medicamentos</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/tipoCaso.jsp">Tipo de casos</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/estadoCaso.jsp">Estados de caso</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/tipoContrato.jsp">Tipos de Contratos</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/grupoSindicales.jsp">Grupos Sindicales</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/eps.jsp">Listado Eps</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/arl.jsp">Listado Arl</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/views/afp.jsp">Listado Afp</a></li>
                                    </ul>
                                </li>
                            </c:when>
                        </c:choose>
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
                <section class="wrapper site-min-height">
                    <h3><i class="fa fa-angle-right"></i> ${sessionScope.PersonaNombre} - ${sessionScope.cedulaPersona}</h3>
                    <div class="row mb">
                        <!-- page start-->
                        <button class="btn btn-theme" data-toggle="modal" data-target="#crearCaso" style="margin: 10px;">
                            Crear Caso
                        </button>
                        <div class="alert alert-success" id="Exitoso" style="display:none;">
                            <strong>¡Bien hecho!</strong>Se guardo correctamente los datos.
                        </div>
                        <!-- Tabla -->
                        <br>
                        <div class="content-panel" style="margin: 10px;">
                            <div class="adv-table">
                                <table id="table_id" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th scope="col">Codigo Caso</th>
                                            <th scope="col">Cedula</th>
                                            <th scope="col">Tipo</th>
                                            <th scope="col">Estado</th>
                                            <th scope="col">Ver</th>
                                            <th scope="col">Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="caso" items="${sessionScope.Casos}" varStatus="myIndex">
                                            <tr>
                                                <td>
                                                    <button id ="selectConsulta" name="selectConsulta" class="btn btn-link" value="${caso.getIdCaso()}"><c:out value="${caso.getIdCaso()}"/></button>
                                                </td>
                                                <td><c:out value="${caso.getPersonaCedula()}"/></td>
                                                <td><c:out value="${sessionScope.nombreTipoCaso}"/></td>
                                                <td><c:out value="${sessionScope.nombreEstado}"/></td>
                                                <td> <form  method="post" action="${pageContext.servletContext.contextPath}/ExpedienteServlet">
                                                        <button name="verExpediente" id="verExpediente" value="${caso.getIdCaso()}" type="submit" class="btn btn-link">Ver Expediente</button>
                                                    </form>
                                                </td>
                                                <td>
                                                    <button  class="btn btn-link" id ="btnEliminarCaso"  name="btnEliminarCaso" value="${caso.getIdCaso()}">
                                                        <i class="fa fa-remove"></i>
                                                    </button>
                                                </td>
                                            </tr>
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
                        <form id="calendario">
                            <br>
                            <div class="form-group col-md-6">
                                <label for="Tipo">Tipo de Caso</label>
                                <select name="Tipo" id="Tipo" class="form-control-sm form-control" required>
                                    <option value="">Tipo Caso </option>
                                    <c:forEach var="Tipo" items="${sessionScope.Tipo}">
                                        <option value="${Tipo.getCodigoTipoCaso()}"><c:out value="${Tipo.getTipoCaso()}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="fechaAfectacion">Fecha  Afectacion</label>
                                <input type="text" class="form-control" id="fechaAfectacion" name="fechaAfectacion" placeholder="Inicio afectacion(meses)" id="example-month-input" required>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="Tipo">Parte Afectada</label>
                                <input  name="parteAfectada" id="parteAfectada" type="text" class="form-control" placeholder="Parte del cuerpo afectada" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="Tipo">Tiempo de Incapacidad</label>
                                <input type="text" class="form-control" id="tiempoInca" name="tiempoInca" placeholder="Tiempo de Incapacidad" >
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Descripcion del Caso</label>
                                <textarea class="form-control " rows="10" cols="50" id="descripcionCaso" name="descripcionCaso" required></textarea>
                            </div>
                            <input class="form-control " id="cedulaPersona" type="hidden" name="cedulaPersona" value="${sessionScope.cedulaPersona}">
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
            <div class="modal fade" id="verCaso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Caso</h4>
                        </div>
                        <form id="caso">
                            <br>
                            <div class="form-group col-md-6">
                                <label class="control-label">Codigo Caso</label>
                                <input type="number" class="form-control" id="IdCaso" name="IdCaso" readonly>
                            </div>
                            <div class="form-group col-md-6">
                                <label>Tipo de Caso</label>
                                <select name="tipoCaso" id="tipoCaso" class="form-control-sm form-control" required>
                                    <option value="">Tipo Caso </option>
                                    <c:forEach var="Tipo" items="${sessionScope.Tipo}">
                                        <option value="${Tipo.getCodigoTipoCaso()}"><c:out value="${Tipo.getTipoCaso()}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label>Fecha  Afectacion</label>
                                <input type="text" class="form-control" id="casoFechaAfectacion" name="casoFechaAfectacion" placeholder="Inicio afectacion(meses)" id="example-month-input" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label>Tiempo de Incapacidad</label>
                                <input type="text" class="form-control" id="casoTimpoInca" name="casoTimpoInca" placeholder="Tiempo de Incapacidad" >
                            </div>
                            <div class="form-group col-md-12">
                                <label>Parte Afectada</label>
                                <input  name="casoParteAfectada" id="casoParteAfectada" type="text" class="form-control" placeholder="Parte del cuerpo afectada" required>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Descripcion del Caso</label>
                                <textarea class="form-control " rows="10" cols="50" id="casoDescripcion" name="casoDescripcion" required></textarea>
                            </div>
                            <input class="form-control " id="casoCedulaPersona" type="hidden" name="casoCedulaPersona">
                            <input class="form-control " id="CreadoPor" type="hidden" name="CreadoPor">
                            <input class="form-control " id="Asignado" type="hidden" name="Asignado">
                            <input class="form-control " id="cedulaUsuario" type="hidden" name="cedulaUsuario" value="${sessionScope.USUARIO.cedula}">

                            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="validar()">
                                    Guardar
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
            <div class="modal fade" id="valiEliminarCaso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmar Cambios</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="modvalidar" id="modvalidar" action="">
                                        <div class="modal-body" id="modvalidar">
                                        </div>
                                    </form>
                                    <input class="form-control " id="casoEliminar" type="hidden" name="casoEliminar">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="eliminarCaso()">
                                    Si
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
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
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="modificarCaso()">
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
        <div class="loader"></div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/JavaScript/caso.js" type="text/javascript"></script>
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

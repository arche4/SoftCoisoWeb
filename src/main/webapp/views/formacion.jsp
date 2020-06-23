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
        <title>SofCoiso-Formaciones</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
        <link href="${pageContext.servletContext.contextPath}/css/calendario.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/css/calendario.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">

        <link href='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/core/main.css' rel='stylesheet' />
        <link href='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/daygrid/main.css' rel='stylesheet' />
        <link href='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/timegrid/main.css' rel='stylesheet' />

        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">

        <link href="${pageContext.servletContext.contextPath}/Loading.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/gritter/css/jquery.gritter.css" />
        <link href="${pageContext.servletContext.contextPath}/css/general.css" rel="stylesheet" type="text/css"/>

        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap-fileinput-master/css/fileinput.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>
    </head>

    <body>
        <section id="container">
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
                            <a  href="${pageContext.servletContext.contextPath}/views/dashboard.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a class="active" href="javascript:;">
                                <i class="fa fa-calendar"></i>
                                <span>Agendamiento</span>
                            </a>
                            <ul class="sub">
                                <li><a href="${pageContext.servletContext.contextPath}/views/calendar.jsp">Citas</a></li>
                                <li class="active"><a  href="${pageContext.servletContext.contextPath}/views/formacion.jsp">Formaciones</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="${pageContext.servletContext.contextPath}/views/persona.jsp">
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
                                        <span>Módulos Administrativos</span>
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

            <section id="main-content">
                <section class="wrapper">
                    <input class="form-control " id="emailUsuario" type="hidden" name="emailUsuario" value="${sessionScope.USUARIO.correo}">
                    <input class="form-control " id="cedulaUsuario" type="hidden" name="cedulaUsuario" value="${sessionScope.USUARIO.cedula}">
                    <h3><i class="fa fa-angle-right"></i> Formaciones</h3>
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
                    <div class="row mt">

                        <aside class="col-lg-11 mt" style="margin: 20px;">
                            <section class="panel">
                                <div class="panel-body">
                                    <div id='calendar'></div>
                                </div>
                            </section>
                        </aside>
                    </div>
                    <!-- page end-->
                </section>

                <!--Modales -->
                <div class="modal fade" id="modificarFormacion" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Formación</h4>
                            </div>
                            <form id="calendario">
                                <br>
                                <input class="form-control " id="idFormacion" type="hidden" name="idFormacion">

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label class="control-label">Tipo de Formación</label>
                                        <input class="form-control " id="tipoFormacionMod" type="text" name="tipoFormacionMod" placeholder="Tipo de formación" required >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Hora de inicio</label>
                                        <input type="time" class="form-control" id="horaIniMod" name="horaIniMod"  required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Hora de fin</label>
                                        <input type="time" class="form-control" id="horaFinMod" name="horaFinMod"  required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Titulo</label>
                                        <input class="form-control " id="tituloMod" type="text" name="tituloMod" placeholder="Titulo" required >
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label class="control-label">Tema</label>
                                        <input class="form-control " id="temaMod" type="text" name="temaMod" placeholder="Tema" required >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Correo Formador</label>
                                        <input class="form-control " id="correoFormadorMod" type="email" name="correoFormadorMod" placeholder="Correo formador" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Número de asistentes</label>
                                        <input class="form-control " id="nAsistentesMod" type="number" name="nAsistentesMod" placeholder="Numero de asistentes"  >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Nombre Formador</label>
                                        <input class="form-control " id="nombreFormadorMod" type="nombreFormador" name="nombreFormadorMod" placeholder="Correo formador" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <button type="button" class="btn btn-theme" data-toggle="modal" data-target="#cargarArchivos">
                                            Cargar Archivos
                                        </button>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-lg-12 ">
                                        <label class="control-label">Descripción</label>
                                        <textarea class="form-control " id="comentarioMod" name="comentarioMod" ></textarea>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-sm-4 col-md-3">
                                        <div class="file-preview-thumbnails clearfix">
                                            <div class="file-preview-frame krajee-default  kv-preview-thumb" id="archivo" data-fileindex="0"  data-template="pdf"  >
                                                <div class="kv-file-content">
                                                    <embed class="kv-preview-data file-preview-pdf" id="pdf" src="" type="application/pdf" style="width:100%;height:160px;position:relative;">
                                                </div><div class="file-thumbnail-footer">
                                                    <div class="file-footer-caption" title="">
                                                        <div class="file-caption-info"></div>
                                                    </div>
                                                    <div class="file-actions">
                                                        <div class="file-footer-buttons">
                                                            <button type="button" id ="verArchivo" name="verArchivo"><i class="glyphicon glyphicon-zoom-in"></i></button>
                                                            <button type="button" id ="eliminarArchivo" name="eliminarArchivo"   ><i class="glyphicon glyphicon-remove"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <input class="form-control " id="nombreArchivo" type="hidden" name="nombreArchivo">
                                <input class="form-control " id="rutaArchivo" type="hidden" name="rutaArchivo">
                                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                                <div class="modal-footer">
                                    <button  type="submit" class="btn btn-success" id="btnCrear" onclick="modificarFormacion()">
                                        Guardar
                                    </button>
                                    <button  type="submit" class="btn btn-danger" id="btnEliminar" onclick="validarEliminar()">
                                        Eliminar
                                    </button>
                                    <button type="button" class="btn btn-theme" data-dismiss="modal">Cerrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="visualizarPdf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="pull-right hidden-phone">
                                <button type="button" class="btn btn-sm btn-kv btn-default btn-outline-secondary btn-close" title="Cerrar vista detallada" data-dismiss="modal" aria-hidden="true"><i class="glyphicon glyphicon-remove"></i></button>
                            </div>  
                            <h5 class="modal-title">Vista previa detallada</h5>
                            <span class="kv-zoom-title" title="pdf"  id="nombre"></span>

                        </div>
                        <div class="modal-body">
                            <embed id="archivoPdf" src="" type="application/pdf" width="100%" height="600px" />
                        </div>
                    </div>
                </div>
            </div>
                <div class="modal fade" id="crearFormacion" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Crear  Formación</h4>
                            </div>
                            <form id="calendario">
                                <br>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label class="control-label">Tipo de Formación</label>
                                        <input class="form-control " id="tipoFormacion" type="text" name="tipoFormacion" placeholder="Tipo de formación" required >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Hora de inicio</label>
                                        <input type="time" class="form-control" id="horaIni" name="horaIni"  required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Hora de fin</label>
                                        <input type="time" class="form-control" id="horaFin" name="horaFin"  required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Titulo</label>
                                        <input class="form-control " id="titulo" type="text" name="titulo" placeholder="Titulo" required >
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label class="control-label">Tema</label>
                                        <input class="form-control " id="tema" type="text" name="tema" placeholder="Tema" required >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Correo Formador</label>
                                        <input class="form-control " id="correoFormador" type="email" name="correoFormador" placeholder="Correo formador" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Número de asistentes</label>
                                        <input class="form-control " id="nAsistentes" type="number" name="nAsistentes" placeholder="Numero de asistentes"  >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Nombre Formador</label>
                                        <input class="form-control " id="nombreFormador" type="text" name="nombreFormador" placeholder="Correo formador" >
                                    </div>
                                    <div class="form-group col-md-6">
                                        <button type="button" class="btn btn-theme" data-toggle="modal" data-target="#cargarArchivos">
                                            Cargar Archivos
                                        </button>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-lg-12 ">
                                        <label class="control-label">Descripción</label>
                                        <textarea class="form-control " id="comentario" name="comentario" ></textarea>
                                    </div>
                                </div>

                                <input class="form-control " id="nombreArchivo" type="hidden" name="nombreArchivo">
                                <input class="form-control " id="rutaArchivo" type="hidden" name="rutaArchivo">
                                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                                <div class="modal-footer">
                                    <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="guardar()">
                                        Crear
                                    </button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="cargarArchivos">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Cargar Archivo</h4>
                            </div>
                            <form id="sampleUploadFrm" method="POST" action="#" enctype="multipart/form-data">
                                <div class="alert alert-danger" id="Error" style="display:none;">
                                    <strong>Error! </strong>Se ha presentado un problema en la carga.
                                </div>
                                <div class="form-group col-md-12">
                                    <div class="file-loading"> 
                                        <input id="input-700" name="kartik-input-700[]" type="file" multiple>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary pull-right" id="uploadBtn">Cargar</button>
                                    <button type="reset" class="btn btn-danger">cancelar</button>
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
                <div class="modal fade" id="validarArchivo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content" id="modales-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Confirmar Cambios</h4>
                        </div>
                        <div class="modal-body">
                            <div class="modal-row">
                                <div class="col-md-12">
                                    <form method="post" name="modArchivo" id="modArchivo" action="">
                                        <div class="modal-body" id="modArchivo">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="eliminarArchivo()">
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
        <div class="loader"></div>

        <script src="${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/core/locales-all.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/JavaScript/formacion.js" type="text/javascript"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
        <!--script for this page-->
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter-conf.js"></script>

        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>

        <!--common script for all pages-->
        <script src='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/core/main.js'></script>
        <script src='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/interaction/main.js'></script>
        <script src='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/daygrid/main.js'></script>
        <script src='${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/timegrid/main.js'></script>


    </body>
</html>

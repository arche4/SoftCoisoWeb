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
        <title>SofCoiso-Expediente</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>

        <!--external css-->
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">

        <link href="${pageContext.servletContext.contextPath}/css/general.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.servletContext.contextPath}/Loading.css" rel="stylesheet" type="text/css"/>

        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap-fileinput-master/css/fileinput.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>

        <link href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/demo_table.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/DT_bootstrap.css" />
        <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/jquery.dataTables.js"></script>
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
                <section class="wrapper">
                    <!-- page start-->
                    <div class="row mt">
                        <div class="col-sm-9">
                            <section class="panel">
                                <header class="panel-heading wht-bg">
                                    <h4 class="gen-case">
                                        Expediente - ${sessionScope.Expediente.getIdCaso()}
                                    </h4>
                                </header>
                                <div class="panel-body ">
                                    <div class="mail-header row">
                                        <div class="col-md-4">
                                            <h4 class="pull-left"> ${sessionScope.PersonaNombre} </h4>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="compose-btn pull-right">
                                                <button  id ="editCaso"  name="editCaso"  class="btn btn-sm btn-theme" value="${sessionScope.Expediente.getIdCaso()}">
                                                    Editar</button>
                                                <button class="btn btn-sm btn-theme" data-toggle="modal" data-target="#cambiarEstado" type="button">Cambiar Estado</button>
                                                <button class="btn btn-sm btn-theme"  data-toggle="modal" data-target="#CambiarUsuario" type="button">Asignar Usuario</button>
                                                <a href="${pageContext.servletContext.contextPath}/views/calendar.jsp" class="btn btn-sm btn-theme"  type="button">Citar</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mail-sender">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <strong>Detalles</strong>
                                            </div>
                                            <div class="col-md-4">
                                                <p class="date">${sessionScope.FlujoCaso.getFechaActualizacion()}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="view-mail">
                                        <div class="col-md-6">
                                            <h5> <strong>Tipo: </strong> ${sessionScope.nombreTipoCaso} </h5>
                                        </div>
                                        <div class="col-md-6">
                                            <h5> <strong>Estado: </strong> ${sessionScope.nombreEstado} </h5>
                                        </div>
                                        <div class="col-md-6">
                                            <h5> <strong>Parte Afectada: </strong> ${sessionScope.Expediente.getParteEfectada()} </h5>
                                        </div>
                                        <div class="col-md-6">
                                            <h5> <strong>Tiempo de Incapacidad: </strong>${sessionScope.Expediente.getTimepoIncapacidad()} </h5>
                                        </div>
                                        <div class="col-md-8">
                                            <h5> <strong>Fecha de Inicio de Afectacion: </strong>${sessionScope.Expediente.getFechaAfectacion()}</h5>
                                        </div>
                                    </div>
                                    <div class="mail-sender">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <strong>Descripción</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="view-mail">
                                        <div class="col-md-12">
                                            <h5>${sessionScope.Expediente.getDescripcionCaso()} </h5>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-sm-3">
                            <section class="panel">
                                <div class="panel-body">
                                    <h4>Usuario</h4>
                                    <strong>Informador</strong>
                                    <li>${sessionScope.creadoPor}</li>
                                    <br>
                                    <strong>Responsable</strong>
                                    <li>${sessionScope.Asignado}</li>
                                </div>
                            </section>
                            <section class="panel">
                                <div class="panel-body">
                                    <h4>Fechas</h4>
                                    <strong>Creada</strong>
                                    <li>${sessionScope.FlujoCaso.getFechaCreacion()}</li>
                                    <br>
                                    <strong>Actualizada</strong>
                                    <li>${sessionScope.FlujoCaso.getFechaActualizacion()}</li>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-12 mt">
                            <div class="panel">
                                <section class="panel">
                                    <header class="panel-heading wht-bg">
                                        <h4 class="gen-case">
                                            Actividad
                                        </h4>
                                    </header>
                                </section>
                                <div class="panel-heading">
                                    <ul class="nav nav-tabs nav-justified">
                                        <li class="active">
                                            <a data-toggle="tab" href="#overview">Acciones</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#citas">Citas</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#Calificacion">Proceso de Calificación</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#edit">Reclamación</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#edit">Medicacion</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#edit">Diagnostico</a>
                                        </li>
                                    </ul>
                                </div>
                                <!-- /panel-heading -->
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="overview" class="tab-pane active">
                                            <div class="row">
                                                <div class="col-md-6 detailed" >
                                                    <label>Comentarios</label>
                                                    <c:forEach var="comentario" items="${sessionScope.listComentario}">
                                                        <div class="recent-activity">
                                                            <div class="activity-panel">
                                                                <div class="task-title">
                                                                    <span class="badge bg-theme"><c:out value="${comentario.getUsuarioNombre()}"/></span> - Añadio un comentario - <c:out value="${comentario.getFechaActualizacion()}"/>
                                                                    <div class="pull-right hidden-phone">
                                                                        <button type="button" id ="btnConsultarCometario" name="btnConsultarCometario"  value="${comentario.getCodigo()}" class="btn btn-primary btn-xs fa fa-pencil"></button>
                                                                        <button type="button" id="btnEliminarComentario" name="btnEliminarComentario" value="${comentario.getCodigo()}" class="btn btn-danger btn-xs fa fa-trash-o"></button>
                                                                    </div>
                                                                    <p></p>
                                                                    <p><c:out value="${comentario.getComentario()}"/></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br>
                                                    </c:forEach>
                                                    <!-- /recent-activity -->
                                                    <h4></h4>
                                                    <textarea rows="3" id="comentarioExpediente" name="comentarioExpediente" class="form-control" placeholder="Comentar.."></textarea>
                                                    <div class="grey-style">
                                                        <div class="pull-right">
                                                            <input class="form-control " id="casoComentario" type="hidden" name="casoComentario" value="${sessionScope.FlujoCaso.getCasoPersonaIdCaso()}">
                                                            <input class="form-control " id="comentarioUsuario" type="hidden" name="comentarioUsuario" value="${sessionScope.USUARIO.cedula}">
                                                            <button  type="submit" class="btn btn-sm btn-theme03" id="btnComentar" onclick="comentar()">
                                                                Comentar
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <!-- /detailed -->

                                                </div>
                                                <!-- /col-md-6 -->
                                                <div class="col-md-6 detailed">
                                                    <label>Archivos Cargados</label>
                                                    <div class=" file-drop-zone clearfix">
                                                        <div class="file-preview-thumbnails clearfix">
                                                            <c:forEach var="archivo" items="${sessionScope.listArchivos}">
                                                                <div class="file-preview-frame krajee-default  kv-preview-thumb" id="archivo" data-fileindex="0" data-fileid="4289183_${archivo.getArchivoNombre()}" data-template="pdf" title="${archivo.getArchivoNombre()}"><div class="kv-file-content">
                                                                        <embed class="kv-preview-data file-preview-pdf" src="${archivo.getArchivoRuta()}" type="application/pdf" style="width:100%;height:160px;position:relative;">
                                                                    </div><div class="file-thumbnail-footer">
                                                                        <div class="file-footer-caption" title="${archivo.getArchivoNombre()}">
                                                                            <div class="file-caption-info">${archivo.getArchivoNombre()}</div>
                                                                        </div>

                                                                        <div class="file-upload-indicator" title="No subido todavÃ&shy;a"><i class="glyphicon glyphicon-plus-sign text-warning"></i></div>
                                                                        <div class="file-actions">
                                                                            <div class="file-footer-buttons">
                                                                                <button type="button" id ="btnConsultarArchivo" name="btnConsultarArchivo"  value="${archivo.getCodigo()}" ><i class="glyphicon glyphicon-zoom-in"></i></button></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /col-md-6 -->
                                            </div>
                                            <!-- /OVERVIEW -->
                                        </div>
                                        <!-- /tab-pane -->
                                        <div id="citas" class="tab-pane">
                                            <div class="row">
                                                <div class="col-md-12 detailed">
                                                    <h4>Citas</h4>
                                                    <div class="content-panel" style="margin: 10px;">
                                                        <div class="adv-table">
                                                            <table id="table_id" class="display" cellspacing="0" width="100%">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col">Titulo Cita</th>
                                                                        <th scope="col">Fecha</th>
                                                                        <th scope="col">Hora Inicio</th>
                                                                        <th scope="col">Hora Fin</th>
                                                                        <th scope="col">Descripcion</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach var="citas" items="${sessionScope.listCitas}" varStatus="myIndex">
                                                                        <tr>
                                                                            <td><c:out value="${citas.getTitulo()}"/></td>
                                                                            <td><c:out value="${citas.getDia()}"/>/<c:out value="${citas.getMes()}"/>/<c:out value="${citas.getAno()}"/></td>
                                                                            <td><c:out value="${citas.getHoraInicio()}"/></td>
                                                                            <td><c:out value="${citas.getHoraFin()}"/></td>
                                                                            <td><c:out value="${citas.getDescripcion()}"/></td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /col-md-6 -->
                                            </div>
                                            <!-- /row -->
                                        </div>
                                        <!-- /tab-pane -->
                                        <div id="Calificacion" class="tab-pane">
                                            <div class="row">
                                                <div class="col-lg-8 col-lg-offset-2 detailed">
                                                    <h4 class="mb">Proceso de calificación</h4> 
                                                    <button class="btn btn-sm btn-theme"  data-toggle="modal" data-target="#agregarProcesoCalificacion" type="button">Agregar Proceso de Calificacion</button>
                                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                                        <div class="custom-box">
                                                            <div class="servicetitle">
                                                                <h4>Standard</h4>
                                                                <hr>
                                                            </div>
                                                            <div class="icn-main-container">
                                                                <span class="icn-container">$25</span>
                                                            </div>
                                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry.</p>
                                                            <ul class="pricing">
                                                                <li>150 Mb Storage</li>
                                                                <li>1 Domain</li>
                                                                <li>2 Sub Domains</li>
                                                                <li>3 MySQL DBs</li>
                                                                <li>2 Emails</li>
                                                                <li>WordPress Installation</li>
                                                                <li>24/7 Support</li>
                                                            </ul>
                                                            <a class="btn btn-theme" href="#">Order Now</a>
                                                        </div>
                                                        <!-- end custombox -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /tab-pane -->
                                    </div>
                                    <!-- /tab-content -->
                                </div>
                                <!-- /panel-body -->
                            </div>
                            <!-- /col-lg-12 -->
                        </div>
                    </div>
                </section>
                <!-- /wrapper -->
            </section>
            <!-- /MAIN CONTENT -->
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
                            <embed id="pdf" src="" type="application/pdf" width="100%" height="600px" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="agregarProcesoCalificacion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Agregar Proceso de Calificación</h4>
                        </div>
                        <div class="alert alert-success" id="Exitoso" style="display:none;">
                            <strong>¡Bien hecho!</strong>Se guardo a cargado correctamente el archivo.
                        </div>
                            <form id="procesoCalificacion" data-toggle="validator">
                            <br>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <label class="control-label">Proceso</label>
                                    <input type="text" class="form-control" id="proceso" name="proceso" placeholder="Proceso" required>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Quieres agregar algun comentario ? </label>
                                <textarea class="form-control " id="comentarioAsig" name="comentarioAsig" placeholder="Comentar..."></textarea>
                            </div>
                            <div class="form-group col-md-6">
                                <button type="button" class="btn btn-theme" data-toggle="modal" data-target="#cargarArchivos">
                                    Cargar Archivos
                                </button>
                            </div>
                            <input class="form-control " id="casoUsuer" type="hidden" name="casoUsuer" value="${sessionScope.FlujoCaso.getCasoPersonaIdCaso()}">
                            <input class="form-control " id="usuarioGestor" type="hidden" name="usuarioGestor" value="${sessionScope.USUARIO.cedula}">
                            <br><br><br><br><br><br><br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="guardarProceso()">
                                    Guardar
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="CambiarUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Asigar Expediente</h4>
                        </div>
                        <form id="asignarForm" data-toggle="validator">
                            <br>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Asginar Usuario </label>
                                    <select name="usuarioRespo" id="usuarioRespo" class="form-control-sm form-control" required>
                                        <option value="">Usuario </option>
                                        <c:forEach var="usuarioRespo" items="${sessionScope.listUsuario}">
                                            <option value="${usuarioRespo.getCedula()}"><c:out value="${usuarioRespo.getNombreUsuario()}"/> <c:out value="${usuarioRespo.getApellidoUsuario()}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Quieres agregar algun comentario ? </label>
                                <textarea class="form-control " id="comentarioAsig" name="comentarioAsig" placeholder="Comentar..."></textarea>
                            </div>
                            <input class="form-control " id="casoUsuer" type="hidden" name="casoUsuer" value="${sessionScope.FlujoCaso.getCasoPersonaIdCaso()}">
                            <input class="form-control " id="usuarioGestor" type="hidden" name="usuarioGestor" value="${sessionScope.USUARIO.cedula}">
                            <br><br><br><br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="asignarUsuario()">
                                    Asignar
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modificarComentario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Comentario</h4>
                        </div>
                        <form id="modificarComentario" data-toggle="validator">
                            <br>
                            <div class="form-group col-md-12">
                                <label class="control-label">Modifica tu comentario </label>
                                <textarea class="form-control " rows="10" cols="50" id="comentarioMod" name="comentarioMod" required></textarea>
                            </div>
                            <input class="form-control " id="codigoCasoMod" type="hidden" name="codigoCasoMod">
                            <input class="form-control " id="codigoComentarioMod" type="hidden" name="codigoComentarioMod">
                            <input class="form-control " id="usuarioCambio" type="hidden" name="usuarioCambio" value="${sessionScope.USUARIO.cedula}">
                            <br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="comentarioModificar()">
                                    Guardar
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>              
            <div class="modal fade" id="eliminarComentarioMod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Eliminar Comentario</h4>
                        </div>
                        <form id="modificarComentario" data-toggle="validator">
                            <br>
                            <div class="form-group col-md-12">
                                <label class="control-label">Estas seguro que quieres eliminar este comentario ? </label>
                                <textarea class="form-control "  id="comentarioEliminar" name="comentarioEliminar" readonly></textarea>
                            </div>
                            <input class="form-control " id="codigoComentario" type="hidden" name="codigoComentario">
                            <br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="eliminar" onclick="eliminarComentario()">
                                    Si
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div> 
            <div class="modal fade" id="cambiarEstado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Cambiar Estado</h4>
                        </div>
                        <form id="estadoCaso" data-toggle="validator">
                            <br>
                            <div class="alert alert-success" id="Exitoso" style="display:none;">
                                <strong>¡Bien hecho!</strong>Se guardo a cargado correctamente el archivo.
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <select name="codigoEstado" id="codigoEstado" class="form-control-sm form-control" required>
                                        <option value="">Estado </option>
                                        <c:forEach var="codigoEstado" items="${sessionScope.Estado}">
                                            <option value="${codigoEstado.getCodigoEstado()}"><c:out value="${codigoEstado.getNombreEstado()}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <button type="button" class="btn btn-theme" data-toggle="modal" data-target="#cargarArchivos">
                                        Cargar
                                    </button>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Quieres agregar algun comentario ? </label>
                                <textarea class="form-control " id="comentarioEstado" name="comentarioEstado" placeholder="Comentar..."></textarea>
                            </div>
                            <input class="form-control " id="casoid" type="hidden" name="casoid" value="${sessionScope.FlujoCaso.getCasoPersonaIdCaso()}">
                            <input class="form-control " id="cedulaUsuario" type="hidden" name="cedulaUsuario" value="${sessionScope.USUARIO.cedula}">
                            <input class="form-control " id="rutaArchivo" type="hidden" name="rutaArchivo">
                            <input class="form-control " id="nombreArchivo" type="hidden" name="nombreArchivo">
                            <br><br><br><br><br><br><br><br><br>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="cambiarEstado()">
                                    Cambiar Estado
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
            <div class="modal fade" id="validarEstadoMod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                        <div class="modal-body" id="infoEstado">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-success" id="btnModificar" onclick="cambiarEstado()">
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
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/DT_bootstrap.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/validator.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/JavaScript/Expediente.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap-fileinput-master/js/locales/es.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/gritter/css/jquery.gritter.css" />

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
                        <li><a class="logout" href="login.html">Logout</a></li>
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
                            <a class="active" href="${pageContext.servletContext.contextPath}/views/calendar.jsp">
                                <i class="fa fa-calendar"></i>
                                <span>Calendario</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-users"></i>
                                <span>Personas</span>
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
                    <h3><i class="fa fa-angle-right"></i> Calendario</h3>
                    <!-- page start-->
                    <!--Alertas -->
                    <div class="alert alert-danger" id="Error" style="display:none;">
                        <strong>Error! </strong>Debes seleccionar una fecha correcta.
                    </div>
                    <div class="alert alert-danger" id="ErrorGuardando" style="display:none;">
                        <strong>Error! </strong>Se presento un error guardando la cita.
                    </div>
                    <div class="alert alert-success" id="Exitoso" style="display:none;">
                        <strong>¡Bien hecho! </strong>Se guardo correctamente la cita.</div>
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

                <div class="modal fade" id="crearCita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Crear  Cita</h4>
                            </div>
                            <form id="calendario">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Cedula</label>
                                        <input type="number" class="form-control" id="cedula" name="cedula"  placeholder="Cedula" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label">Nombre</label>
                                        <input type="text" class="form-control" id="nombrePersona" name="nombrePersona"  placeholder="Nombre Persona">
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
                                        <label class="control-label">Email</label>
                                        <input class="form-control " id="emailPersona" type="email" name="emailPersona" placeholder="Email" required >
                                    </div>
                                    <input class="form-control " id="emailUsuario" type="hidden" name="emailUsuario" value="${sessionScope.USUARIO.correo}">
                                    <input class="form-control " id="cedulaUsuario" type="hidden" name="cedulaUsuario" value="${sessionScope.USUARIO.cedula}">

                                    <div class="form-group col-md-6">
                                        <label class="control-label">Titulo</label>
                                        <input class="form-control " id="titulo" type="text" name="titulo" placeholder="Titulo" required >
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group ">
                                        <div class="col-lg-12">
                                            <label class="control-label">Descripcion</label>
                                            <textarea class="form-control " id="comentario" name="comentario" required></textarea>
                                        </div>
                                    </div>
                                </div>
                                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>                
                                <div class="modal-footer">
                                    <button  type="submit" class="btn btn-success" id="btnCrearCita" onclick="guardar()">
                                        Crear
                                    </button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--Modales --> 
                <footer class="site-footer">
                    <div class="text-center">
                        <p>
                            &copy; Copyrights <strong>Dashio</strong>. All Rights Reserved
                        </p>
                        <div class="credits">
                            <!--
                              You are NOT allowed to delete the credit link to TemplateMag with free version.
                              You can delete the credit link only if you bought the pro version.
                              Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/dashio-bootstrap-admin-template/
                              Licensing information: https://templatemag.com/license/
                            -->
                            Created with Dashio template by <a href="https://templatemag.com/">TemplateMag</a>
                        </div>
                        <a href="calendar.html#" class="go-top">
                            <i class="fa fa-angle-up"></i>
                        </a>
                    </div>
                </footer>
                <!-- /wrapper -->

            </section>
            <div id='loading'>loading...</div>


        </section>
        <script src="${pageContext.servletContext.contextPath}/lib/fullcalendar/packages/bundle/locales/es.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/JavaScript/calendario.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
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

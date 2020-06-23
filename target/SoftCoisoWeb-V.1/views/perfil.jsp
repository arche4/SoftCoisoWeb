<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty user}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/index.jsp"/>
</c:if> 
<c:if test="${sessionScope.USUARIO.getRol() != sessionScope.rol}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/views/dashboard.jsp"/>
</c:if> 
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>SofCoiso-Perfil</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">
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
                                <li><a href="${pageContext.servletContext.contextPath}/views/calendar.jsp">Formaciones</a></li>
                                <li><a href="panels.html">Planeacion</a></li>
                            </ul>
                        </li>
                        <li class="sub-menu">
                            <a href="${pageContext.servletContext.contextPath}/views/persona.jsp">
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
                            <a  href="javascript:;">
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
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="row content-panel">
                                <!-- /col-md-4 -->
                                <div class="col-md-4 profile-text">
                                    <h3>${sessionScope.USUARIO.nombreUsuario} ${sessionScope.USUARIO.apellidoUsuario}</h3>
                                    <h6> ${sessionScope.USUARIO.rol}</h6>
                                    <div class="profile-pic">
                                        <p><img src="${pageContext.servletContext.contextPath}/img/icono-user.png" class="img-circle"></p>
                                    </div>
                                </div>
                                <div class="col-md-8 profile-text mt mb">
                                    <div class="right-divider hidden-sm hidden-xs">
                                        <form id="usuario" data-toggle="validator">
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Cedula</label>
                                                    <input type="number" class="form-control" id="usuarioCedula" name="usuarioCedula" value="${sessionScope.USUARIO.cedula}"  readonly >
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Nombre</label>
                                                    <input type="text" class="form-control" id="usuarioNombre" name="usuarioNombre" value="${sessionScope.USUARIO.nombreUsuario}" required>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Apellido</label>
                                                    <input type="text" class="form-control" id="usuarioApellido" name="usuarioApellido"  value="${sessionScope.USUARIO.apellidoUsuario}" required>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Email</label>
                                                    <input class="form-control " id="usuarioEmail" type="email" name="usuarioEmail" value="${sessionScope.USUARIO.correo}" required>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Nueva Contraseña</label>
                                                    <input type="password" class="form-control" id="nuevaContraseña" name="nuevaContraseña"  placeholder="xxxx">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label class="control-label">Validar Contraseña</label>
                                                    <input class="form-control " id="validaContraseña" type="password" name="validaContraseña" placeholder="xxxx">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-primary" onclick="modificarUsuario()">Guardar Cambios</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- /row -->
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
                                            <form>
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
                    <!-- /container -->
                </section>
                <!-- /wrapper -->
            </section>
            <!-- /MAIN CONTENT -->
            <!--main content end-->
            <!--footer start-->
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
                    <a href="profile.html#" class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div>
            </footer>
            <!--footer end-->
        </section>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/JavaScript/perfil.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>


    </body>

</html>

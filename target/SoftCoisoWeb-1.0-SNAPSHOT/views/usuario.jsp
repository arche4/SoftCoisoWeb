<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>SofCoiso-Usuario</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">

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
                            <a href="javascript:;">
                                <i class="fa fa-users"></i>
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
                            <a  class="active" href="javascript:;">
                                <i class="fa fa-desktop"></i>
                                <span>Modulos Administrativos</span>
                            </a>
                            <ul class="sub">
                                <li class="active"><a href="${pageContext.servletContext.contextPath}/views/usuario.jsp">Usuarios</a></li>
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
                    <h3><i class="fa fa-angle-right"></i> Usuarios </h3>
                    <div class="row mb">
                        <!-- page start-->
                        <button class="btn btn-theme" data-toggle="modal" data-target="#myModal" style="margin: 10px;">
                            Crear Usuario
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                    </div>
                                    <div class="modal-body">
                                        Hi there, I am a Modal Example for Dashio Admin Panel.
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
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
                                            <th scope="col">Apellidos</th>
                                            <th scope="col">rol</th>
                                            <th scope="col">Ver</th>
                                            <th scope="col">Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="usuario" items="${sessionScope.listUsuario}" varStatus="myIndex">
                                            <tr>
                                                <td><c:out value="${usuario.getCedula()}"/></td>
                                                <td><c:out value="${usuario.getNombreUsuario()}"/></td>
                                                <td><c:out value="${usuario.getApellidoUsuario()}"/></td>
                                                <td><c:out value="${usuario.getRol()}"/></td>
                                                <td> <button type="button" href="#modalInf" id ="usuarioConsulta" 
                                                             name="usuarioConsulta" class="btn btn-link" value="${usuario.getCedula()}"><i class="fa fa-eye"></i> </button>
                                                </td>
                                                <td><button type="button" href="#modalDelete" id ="btnElimiar" 
                                                            name="btnElmiar" class="btn btn-link" value="${usuario.getCedula()}"><i class="fa fa-trash-o"></i></button>   </td>
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
            <!--main content end-->
            <!--footer start-->
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
        <div class="loader" id="loader"></div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/JavaScript/usuario.js" type="text/javascript"></script>
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

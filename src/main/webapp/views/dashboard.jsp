<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty user}">
    <jsp:forward page="${pageContext.servletContext.contextPath}/index.jsp"/>
</c:if> 
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>Sofcoiso-Dashboard</title>
        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon"/>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
        
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/DT_bootstrap.css" />
        <link href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/demo_table.css" rel="stylesheet" />
        <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/jquery.dataTables.js"></script>


        <!--external css-->
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet"/>
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet"/>

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
                            <a class="active" href="${pageContext.servletContext.contextPath}/views/dashboard.jsp">
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
            <!--sidebar end-->
            <!-- **********************************************************************************************************************************************************
                MAIN CONTENT
                *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <h3><i class="fa fa-angle-right"></i> Dashboard</h3>
                    <div class="tab-pane" id="chartjs">
                        <div class="row mt">
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i> Cantidad de Genero</h4>
                                    <div class="panel-body text-center">
                                        <canvas id="graficoGenero" height="300" width="400"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i> Estados de los casos</h4>
                                    <div class="panel-body text-center">
                                        <canvas id="graficoEstado" height="300" width="400"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt">
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i> Arl</h4>
                                    <div class="panel-body text-center">
                                        <canvas id="graficaArl" height="300" width="400"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i>Eps</h4>
                                    <div class="panel-body text-center">
                                        <canvas id="graficaEps" height="300" width="400"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt">
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i> Afp</h4>
                                    <div class="panel-body text-center">
                                        <canvas id="graficaAfp" height="300" width="400"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i>Orden de Llegada</h4>
                                    <div class="panel-body text-center">
                                        <table id="table_id" class="display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Cedula</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Apellido</th>
                                                    <th scope="col">Hora llegada</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="persona" items="${sessionScope.listOrdenLLegada}" varStatus="myIndex">
                                                    <tr>
                                                        <td><c:out value="${persona.getCedula()}"/></td>
                                                        <td><c:out value="${persona.getNombrePersona()}"/></td>
                                                        <td><c:out value="${persona.getApellidoPersona()}"/></td>
                                                        <td><c:out value="${persona.getOrdenLlegada()}"/></td>
                                                        
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
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
        <div class="loader"></div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/JavaScript/dashboard.js" type="text/javascript"></script>

        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.sparkline.js"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter-conf.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js" integrity="sha256-JG6hsuMjFnQ2spWq0UiaDRJBaarzhFbUxiUTxQDA9Lk=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js" integrity="sha256-XF29CBwU1MWLaGEnsELogU6Y6rcc5nCkhhx89nFMIDQ=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js" integrity="sha256-J2sc79NPV/osLcIpzL3K8uJyAD7T5gaEFKlLDM18oxY=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js" integrity="sha256-CfcERD4Ov4+lKbWbYqXD6aFM9M51gN4GUEtDhkWABMo=" crossorigin="anonymous"></script>   
    </body>

</html>

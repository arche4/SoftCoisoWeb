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
                    <!-- page start-->
                    <div class="row mt">
                        <div class="col-sm-9">
                            <section class="panel">
                                <header class="panel-heading wht-bg">
                                    <h4 class="gen-case">
                                        View Message
                                        <form action="#" class="pull-right mail-src-position">
                                            <div class="input-append">
                                                <input type="text" class="form-control " placeholder="Search Mail">
                                            </div>
                                        </form>
                                    </h4>
                                </header>
                                <div class="panel-body ">
                                    <div class="mail-header row">
                                        <div class="col-md-8">
                                            <h4 class="pull-left"> Dashio, New Admin Dashboard & Front-end </h4>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="compose-btn pull-right">
                                                <a href="mail_compose.html" class="btn btn-sm btn-theme"><i class="fa fa-reply"></i> Reply</a>
                                                <button class="btn  btn-sm tooltips" data-original-title="Print" type="button" data-toggle="tooltip" data-placement="top" title=""><i class="fa fa-print"></i> </button>
                                                <button class="btn btn-sm tooltips" data-original-title="Trash" data-toggle="tooltip" data-placement="top" title=""><i class="fa fa-trash-o"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mail-sender">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <img src="${pageContext.servletContext.contextPath}/img/ui-zac.jpg" alt="">
                                                <strong>Zac Doe</strong>
                                                <span>[zac@youremail.com]</span> to
                                                <strong>me</strong>
                                            </div>
                                            <div class="col-md-4">
                                                <p class="date"> 10:15AM 02 FEB 2014</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="view-mail">
                                        <p>As he bent his head in his most courtly manner, there was a secrecy in his smiling face, and he conveyed an air of mystery to those words, which struck the eyes and ears of his nephew forcibly. At the same time, the thin straight lines
                                            of the setting of the eyes, and the thin straight lips, and the markings in the nose, curved with a sarcasm that looked handsomely diabolic. </p>
                                        <p>"Yes," repeated the Marquis. "A Doctor with a daughter. Yes. So commences the new philosophy! You are fatigued. Good night!"</p>
                                        <p>It would have been of as much avail to interrogate any stone face outside the chateau as to interrogate that face of his. The nephew looked at him, in vain, in passing on to the door. </p>
                                        <p>"Good night!" said the uncle. "I look to the pleasure of seeing you again in the morning. Good repose! Light Monsieur my nephew to his chamber there!--And burn Monsieur my nephew in his bed, if you will," he added to himself, before
                                            he rang his little bell again, and summoned his valet to his own bedroom.</p>
                                    </div>
                                    <div class="attachment-mail">
                                        <p>
                                            <span><i class="fa fa-paperclip"></i> 2 attachments — </span>
                                            <a href="#">Download all attachments</a> |
                                            <a href="#">View all images</a>
                                        </p>
                                        <ul>
                                            <li>
                                                <a class="atch-thumb" href="#">
                                                    <img src="${pageContext.servletContext.contextPath}/img/instagram.jpg">
                                                </a>
                                                <a class="name" href="#">
                                                    IMG_001.jpg
                                                    <span>20KB</span>
                                                </a>
                                                <div class="links">
                                                    <a href="#">View</a> -
                                                    <a href="#">Download</a>
                                                </div>
                                            </li>
                                            <li>
                                                <a class="atch-thumb" href="#">
                                                    <img src="${pageContext.servletContext.contextPath}/img/weather.jpg">
                                                </a>
                                                <a class="name" href="#">
                                                    IMG_001.jpg
                                                    <span>20KB</span>
                                                </a>
                                                <div class="links">
                                                    <a href="#">View</a> -
                                                    <a href="#">Download</a>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="compose-btn pull-left">
                                        <a href="mail_compose.html" class="btn btn-sm btn-theme"><i class="fa fa-reply"></i> Reply</a>
                                        <button class="btn btn-sm "><i class="fa fa-arrow-right"></i> Forward</button>
                                        <button class="btn  btn-sm tooltips" data-original-title="Print" type="button" data-toggle="tooltip" data-placement="top" title=""><i class="fa fa-print"></i> </button>
                                        <button class="btn btn-sm tooltips" data-original-title="Trash" data-toggle="tooltip" data-placement="top" title=""><i class="fa fa-trash-o"></i></button>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-sm-3">
                            <section class="panel">
                                <div class="panel-body">
                                    <a href="mail_compose.html" class="btn btn-compose">
                                        <i class="fa fa-pencil"></i>  Compose Mail
                                    </a>
                                    <ul class="nav nav-pills nav-stacked mail-nav">
                                        <li class="active"><a href="inbox.html"> <i class="fa fa-inbox"></i> Inbox  <span class="label label-theme pull-right inbox-notification">3</span></a></li>
                                        <li><a href="#"> <i class="fa fa-envelope-o"></i> Send Mail</a></li>
                                        <li><a href="#"> <i class="fa fa-exclamation-circle"></i> Important</a></li>
                                        <li><a href="#"> <i class="fa fa-file-text-o"></i> Drafts <span class="label label-info pull-right inbox-notification">8</span></a></a>
                                        </li>
                                        <li><a href="#"> <i class="fa fa-trash-o"></i> Trash</a></li>
                                    </ul>
                                </div>
                            </section>
                            <section class="panel">
                                <div class="panel-body">
                                    <ul class="nav nav-pills nav-stacked labels-info ">
                                        <li>
                                            <h4>Friends Online</h4>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <img src="${pageContext.servletContext.contextPath}/img/friends/fr-10.jpg" class="img-circle" width="20">Laura
                                                <p><span class="label label-success">Available</span></p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <img src="${pageContext.servletContext.contextPath}/img/friends/fr-05.jpg" class="img-circle" width="20">David
                                                <p><span class="label label-danger"> Busy</span></p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <img src="${pageContext.servletContext.contextPath}/img/friends/fr-01.jpg" class="img-circle" width="20">Mark
                                                <p>Offline</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <img src="${pageContext.servletContext.contextPath}/img/friends/fr-03.jpg" class="img-circle" width="20">Phillip
                                                <p>Offline</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <img src="${pageContext.servletContext.contextPath}/img/friends/fr-02.jpg" class="img-circle" width="20">Joshua
                                                <p>Offline</p>
                                            </a>
                                        </li>
                                    </ul>
                                    <a href="#"> + Add More</a>
                                    <div class="inbox-body text-center inbox-action">
                                        <div class="btn-group">
                                            <a class="btn mini btn-default" href="javascript:;">
                                                <i class="fa fa-power-off"></i>
                                            </a>
                                        </div>
                                        <div class="btn-group">
                                            <a class="btn mini btn-default" href="javascript:;">
                                                <i class="fa fa-cog"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>

                    </div>
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
                    <a href="mail_view.html#" class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div>
            </footer>
            <!--footer end-->
        </section>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.servletContext.contextPath}/lib/jquery/jquery.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
    </body>
</html>

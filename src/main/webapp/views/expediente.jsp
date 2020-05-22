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
                                                <button class="btn btn-sm btn-theme"  type="button">Editar</button>
                                                <button class="btn btn-sm btn-theme"  type="button">Cambiar Estado</button>
                                                <button class="btn btn-sm btn-theme"  type="button">Asignar Usuario</button>
                                                <button class="btn btn-sm btn-theme"  type="button">Citar</button>
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
                                            <a data-toggle="tab" href="#edit">Citas</a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#edit">Proceso de Calificación</a>
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
                                                <div class="col-md-6">
                                                    <textarea rows="3" class="form-control" placeholder="Whats on your mind?"></textarea>
                                                    <div class="grey-style">
                                                        <div class="pull-left">
                                                            <button class="btn btn-sm btn-theme"><i class="fa fa-camera"></i></button>
                                                            <button class="btn btn-sm btn-theme"><i class="fa fa-map-marker"></i></button>
                                                        </div>
                                                        <div class="pull-right">
                                                            <button class="btn btn-sm btn-theme03">POST</button>
                                                        </div>
                                                    </div>
                                                    <div class="detailed mt">
                                                        <h4>Recent Activity</h4>
                                                        <div class="recent-activity">
                                                            <div class="activity-icon bg-theme"><i class="fa fa-check"></i></div>
                                                            <div class="activity-panel">
                                                                <h5>1 HOUR AGO</h5>
                                                                <p>Purchased: Dashio Admin Panel & Front-end theme.</p>
                                                            </div>
                                                            <div class="activity-icon bg-theme02"><i class="fa fa-trophy"></i></div>
                                                            <div class="activity-panel">
                                                                <h5>5 HOURS AGO</h5>
                                                                <p>Task Completed. Resolved issue with Disk Space.</p>
                                                            </div>
                                                            <div class="activity-icon bg-theme04"><i class="fa fa-rocket"></i></div>
                                                            <div class="activity-panel">
                                                                <h5>3 DAYS AGO</h5>
                                                                <p>Launched a new product: Flat Pack Heritage.</p>
                                                            </div>
                                                        </div>
                                                        <!-- /recent-activity -->
                                                    </div>
                                                    <!-- /detailed -->
                                                </div>
                                                <!-- /col-md-6 -->
                                                <div class="col-md-6 detailed">
                                                    <h4>User Stats</h4>
                                                    <div class="row centered mt mb">
                                                        <div class="col-sm-4">
                                                            <h1><i class="fa fa-money"></i></h1>
                                                            <h3>$22,980</h3>
                                                            <h6>LIFETIME EARNINGS</h6>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <h1><i class="fa fa-trophy"></i></h1>
                                                            <h3>37</h3>
                                                            <h6>COMPLETED TASKS</h6>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <h1><i class="fa fa-shopping-cart"></i></h1>
                                                            <h3>1980</h3>
                                                            <h6>ITEMS SOLD</h6>
                                                        </div>
                                                    </div>
                                                    <!-- /row -->
                                                    <h4>My Friends</h4>
                                                    <div class="row centered mb">
                                                        <ul class="my-friends">
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-01.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-02.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-03.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-04.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-05.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-06.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-07.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-08.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-09.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-10.jpg"></div>
                                                            </li>
                                                            <li>
                                                                <div class="friends-pic"><img class="img-circle" width="35" height="35" src="img/friends/fr-11.jpg"></div>
                                                            </li>
                                                        </ul>
                                                        <div class="row mt">
                                                            <div class="col-md-4 col-md-offset-4">
                                                                <h6><a href="#">VIEW ALL</a></h6>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- /row -->
                                                    <h4>Pending Tasks</h4>
                                                    <div class="row centered">
                                                        <div class="col-md-8 col-md-offset-2">
                                                            <h5>Dashboard Update (40%)</h5>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                                    <span class="sr-only">40% Complete (success)</span>
                                                                </div>
                                                            </div>
                                                            <h5>Unanswered Messages (80%)</h5>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                                    <span class="sr-only">80% Complete (success)</span>
                                                                </div>
                                                            </div>
                                                            <h5>Product Review (60%)</h5>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                                    <span class="sr-only">60% Complete (success)</span>
                                                                </div>
                                                            </div>
                                                            <h5>Friend Requests (90%)</h5>
                                                            <div class="progress">
                                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 90%">
                                                                    <span class="sr-only">90% Complete (success)</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- /col-md-8 -->
                                                    </div>
                                                    <!-- /row -->
                                                </div>
                                                <!-- /col-md-6 -->
                                            </div>
                                            <!-- /OVERVIEW -->
                                        </div>
                                        <!-- /tab-pane -->
                                        <div id="contact" class="tab-pane">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div id="map"></div>
                                                </div>
                                                <!-- /col-md-6 -->
                                                <div class="col-md-6 detailed">
                                                    <h4>Location</h4>
                                                    <div class="col-md-8 col-md-offset-2 mt">
                                                        <p>
                                                            Postal Address<br/> PO BOX 12988, Sutter Ave<br/> Brownsville, New York.
                                                        </p>
                                                        <br>
                                                        <p>
                                                            Headquarters<br/> 844 Sutter Ave,<br/> 9003, New York.
                                                        </p>
                                                    </div>
                                                    <h4>Contacts</h4>
                                                    <div class="col-md-8 col-md-offset-2 mt">
                                                        <p>
                                                            Phone: +33 4898-4303<br/> Cell: 48 4389-4393<br/>
                                                        </p>
                                                        <br>
                                                        <p>
                                                            Email: hello@dashiotheme.com<br/> Skype: UseDashio<br/> Website: http://Alvarez.is
                                                        </p>
                                                    </div>
                                                </div>
                                                <!-- /col-md-6 -->
                                            </div>
                                            <!-- /row -->
                                        </div>
                                        <!-- /tab-pane -->
                                        <div id="edit" class="tab-pane">
                                            <div class="row">
                                                <div class="col-lg-8 col-lg-offset-2 detailed">
                                                    <h4 class="mb">Personal Information</h4>
                                                    <form role="form" class="form-horizontal">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label"> Avatar</label>
                                                            <div class="col-lg-6">
                                                                <input type="file" id="exampleInputFile" class="file-pos">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Company</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="c-name" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Lives In</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="lives-in" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Country</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="country" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Description</label>
                                                            <div class="col-lg-10">
                                                                <textarea rows="10" cols="30" class="form-control" id="" name=""></textarea>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="col-lg-8 col-lg-offset-2 detailed mt">
                                                    <h4 class="mb">Contact Information</h4>
                                                    <form role="form" class="form-horizontal">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Address 1</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="addr1" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Address 2</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="addr2" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Phone</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="phone" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Cell</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="cell" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Email</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="email" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Skype</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" placeholder=" " id="skype" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-lg-offset-2 col-lg-10">
                                                                <button class="btn btn-theme" type="submit">Save</button>
                                                                <button class="btn btn-theme04" type="button">Cancel</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <!-- /col-lg-8 -->
                                            </div>
                                            <!-- /row -->
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

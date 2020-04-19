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
        <title>Dashio - Bootstrap Admin Template</title>

        <!-- Favicons -->
        <link href="${pageContext.servletContext.contextPath}/img/favicon.png" rel="icon">
        <link href="${pageContext.servletContext.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.servletContext.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

                
        <link href="${pageContext.servletContext.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/demo_page.css" rel="stylesheet" />
        <link href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/demo_table.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/lib/advanced-datatable/css/DT_bootstrap.css" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/css/style-responsive.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/lib/gritter/css/jquery.gritter.css" />

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
                <a href="index.html" class="logo"><b>SOF<span>COSIO</span></b></a>
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
                        <h5 class="centered">Sam Soffes</h5>
                        <li class="mt">
                            <a href="index.html">
                                <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="sub-menu">
                            <a href="javascript:;">
                                <i class="fa fa-desktop"></i>
                                <span>UI Elements</span>
                            </a>
                            <ul class="sub">
                                <li><a href="general.html">General</a></li>
                                <li><a href="buttons.html">Buttons</a></li>
                                <li><a href="panels.html">Panels</a></li>
                                <li><a href="font_awesome.html">Font Awesome</a></li>
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
                        <button class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModal">
                Launch Modal
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
                        <div class="content-panel">
                            <div class="adv-table">
                                <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered" id="hidden-table-info">
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
                        &copy; 2020 <strong>SofCoiso</strong>. Todos los Derechos Reservados
                    </p>
                   
                    <a href="advanced_table.html#" class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div>
            </footer>
            <!--footer end-->
        </section>
        <!-- js placed at the end of the document so the pages load faster -->
        <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/jquery.js"></script>

        <script class="include" type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
        <script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/advanced-datatable/js/DT_bootstrap.js"></script>
        <!--common script for all pages-->
        <script src="${pageContext.servletContext.contextPath}/lib/common-scripts.js"></script>
        <!--script for this page-->
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter/js/jquery.gritter.js"></script>
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/lib/gritter-conf.js"></script>
        <!--script for this page-->
        <script type="text/javascript">
            /* Formating function for row details */
            function fnFormatDetails(oTable, nTr) {
                var aData = oTable.fnGetData(nTr);
                var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
                sOut += '<tr><td>Rendering engine:</td><td>' + aData[1] + ' ' + aData[4] + '</td></tr>';
                sOut += '<tr><td>Link to source:</td><td>Could provide a link here</td></tr>';
                sOut += '<tr><td>Extra info:</td><td>And any further details here (images etc)</td></tr>';
                sOut += '</table>';

                return sOut;
            }

            $(document).ready(function () {
                /*
                 * Insert a 'details' column to the table
                 */
             

                /*
                 * Initialse DataTables, with no sorting on the 'details' column
                 */
                var oTable = $('#hidden-table-info').dataTable({
                    "aoColumnDefs": [{
                            "bSortable": false,
                            "aTargets": [0]
                        }],
                    "aaSorting": [
                        [1, 'asc']
                    ]
                });

                /* Add event listener for opening and closing details
                 * Note that the indicator for showing which row is open is not controlled by DataTables,
                 * rather it is done here
                 */
                $('#hidden-table-info tbody td img').live('click', function () {
                    var nTr = $(this).parents('tr')[0];
                    if (oTable.fnIsOpen(nTr)) {
                        /* This row is already open - close it */
                        this.src = "lib/advanced-datatable/media/images/details_open.png";
                        oTable.fnClose(nTr);
                    } else {
                        /* Open this row */
                        this.src = "lib/advanced-datatable/images/details_close.png";
                        oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                    }
                });
            });
        </script>
    </body>

</html>

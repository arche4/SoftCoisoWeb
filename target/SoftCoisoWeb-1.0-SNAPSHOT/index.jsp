<!DOCTYPE html>

<html lang="en">
    <head>
        <title>SofCoiso</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--===============================================================================================-->	
        <link rel="icon" type="${pageContext.servletContext.contextPath}/image/png" href="img/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/vendor/bootstrap/css/bootstrap.min.css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/vendor/animate/animate.css"/>
        <!--===============================================================================================-->	
        <link href="${pageContext.servletContext.contextPath}/login/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" type="text/css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/vendor/animsition/css/animsition.min.css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/vendor/select2/select2.min.css"/>
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/vendor/daterangepicker/daterangepicker.css"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/css/util.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/login/css/main.css"/>
        <!--===============================================================================================-->
    </head>

    <body style="background-color: #666666;">
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                   <form class="login100-form validate-form" id="frmLogin" method="post" action="${pageContext.servletContext.contextPath}/ServletLogin">
                       
                 <!--  <form class="login100-form validate-form"> -->
                  <span class="login100-form-title p-b-43">
                            Sofcoiso!
                        </span>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" id="usuario" name="usuario">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Usuario</span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" id="clave" name="clave">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Password</span>
                        </div>
                        <div class="flex-sb-m w-full p-t-3 p-b-32">
                            <div class="contact100-form-checkbox">
                                <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                                <label class="label-checkbox100" for="ckb1">
                                    Recordar
                                </label>
                            </div>

                            <div>
                                <a href="#" class="txt1">
                                    Has olvidado la contraseña?
                                </a>
                            </div>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" id="btnLog" type="submit">
                                Ingresar
                            </button>
                        </div>


                        <div class="clearfix"></div>

                        <div class="separator">
                            <div class="clearfix"></div>
                            <br />

                            <div>
                                <p>©2020 Todos los derechos reservados. Corporación colectivo intersindical de salud ocupacional. Privacidad y términos.</p>
                            </div>
                        </div>
                    </form>
                    <div class="login100-more" style="background-image: url('${pageContext.servletContext.contextPath}/login/images/principal.png');">
                    </div>
                </div>
            </div>
        </div>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/animsition/js/animsition.min.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/bootstrap/js/popper.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/login/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/select2/select2.min.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/daterangepicker/moment.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/login/vendor/daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/vendor/countdowntime/countdowntime.js" type="text/javascript"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.servletContext.contextPath}/login/js/main.js" type="text/javascript"></script>
    </body>
</html>

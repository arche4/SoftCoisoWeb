<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>SoftCoiso </title>

        <!-- Bootstrap -->

        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <!-- Font Awesome -->
        <link href="build/css/custom.min.css" rel="stylesheet" type="text/css"/>
        <!-- NProgress -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="otros/nprogress.css" rel="stylesheet" type="text/css"/>
        <!-- Animate.css -->

        <!-- Custom Theme Style -->
    </head>

    <body class="login">
        <div>
            <a class="hiddenanchor" id="signup"></a>
            <a class="hiddenanchor" id="signin"></a>

            <div class="login_wrapper">
                <div class="animate form login_form">
                    <section class="login_content">
                        <form id="frmLogin" method="post" action="${pageContext.servletContext.contextPath}/ServletLogin">
                            <h1>Iniciar Sesion</h1>
                            <div>
                                <input type="text" id="usuario" name="usuario" class="form-control" placeholder="Usuario" required="" />
                            </div>
                            <div>
                                <input type="password" id="clave" name="clave" class="form-control" placeholder="Contraseña" required="" />
                            </div>
                            <div>
                                <button class="btn btn-default submit" id="btnLog" type="submit">Iniciar Sesion</button>
                                <a class="reset_pass" href="#">Se te olvido la contraseña?</a>
                            </div>

                            <div class="clearfix"></div>

                            <div class="separator">
                                <div class="clearfix"></div>
                                <br />

                                <div>
                                    <h1></i> SoftCoiso!</h1>
                                    <p>©2020 Todos los derechos reservados. Corporación colectivo intersindical de salud ocupacional. Privacidad y términos.</p>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>

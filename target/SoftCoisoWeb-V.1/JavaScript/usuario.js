
$(document).ready(function () {
    $('#table_id').dataTable();

    $("body").on("click", "#usuarioConsulta", function () {
        $(".loader").fadeIn("slow");
        var usuarioConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/SoftCoisoWeb/UsuarioServlet",
            data: 'usuarioConsulta=' + usuarioConsulta,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split(",");
                    $("#usuarioCedula").val(respuesta[0]);
                    $("#usuarioNombre").val(respuesta[1]);
                    $("#usuarioApellido").val(respuesta[2]);
                    $("#usuarioRol").val(respuesta[3]);
                    $("#usuarioEmail").val(respuesta[4]);
                    $("#usuarioContraseña").val(respuesta[5]);
                    $('#verUsuario').modal('show');
                } else {
                    var cadena = '<div class="form-row">'
                            + '<h5>Lo sentimos, se ha presentado un problema consultado los datos .</h3>'
                            + ' </div>';
                    $('#modInferror').html(cadena);
                    $('#modalInfError').modal('show');
                }
            }
        });

    });
});
$(window).load(function () {
    $(".loader").fadeOut("slow");
});

function myFunctionReload() {
    location.reload();
}
function guardarUsuario() {
    var elmForm = $("#usuario");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#myModal').modal('hide');
            var btnCrearUsuario = 'ok';
            var cedula = $('#cedula').val();
            var nombre = $('#nombre').val();
            var apellido = $('#apellido').val();
            var rol = $('#rol').val();
            var emalUsuario = $('#emalUsuario').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/SoftCoisoWeb/UsuarioServlet",
                data: 'btnCrearUsuario=' + btnCrearUsuario + '&cedula=' + cedula + '&nombre=' + nombre + '&apellido=' + apellido +
                        '&rol=' + rol + '&emalUsuario=' + emalUsuario,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "Exitoso") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>Los cambios fueron guardados con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else {
                        var cadena = '<div class="form-row">'
                                + '<h5>Lo sentimos, se ha presentado un problema guardando los datos .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');
                    }
                }});
        }
    }
}
function validar() {
    var elmForm = $("#userMod");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            var cadena = ' <div class="form-row">'
                    + '<h5> ¿ Esta seguro que quieres realizar los cambios ? </h3>'
                    + ' </div>';
            $('#InfoConfirmar').html(cadena);
            $('#modalValidar').modal('show');
            $('#verUsuario').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el usuario? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#verUsuario').modal('hide');
}
function modificarUsuario() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var cedula = $('#usuarioCedula').val();
    var nombre = $('#usuarioNombre').val();
    var apellido = $('#usuarioApellido').val();
    var rol = $('#usuarioRol').val();
    var emalUsuario = $('#usuarioEmail').val();
    var claveUsuario = $('#usuarioContraseña').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/UsuarioServlet",
        data: 'btnModificar=' + btnModificar + '&cedula=' + cedula + '&nombre=' + nombre + '&apellido=' + apellido +
                '&rol=' + rol + '&emalUsuario=' + emalUsuario + '&claveUsuario=' + claveUsuario,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>Los cambios fueron guardados con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema guardando los datos .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }});
}
function eliminarUsuario() {
    $(".loader").fadeIn("slow");
    $('#modalEliminar').modal('hide');
    var btnEliminar = $('#usuarioCedula').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/SoftCoisoWeb/UsuarioServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El usuario fue eliminado  con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando el usuario.</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}
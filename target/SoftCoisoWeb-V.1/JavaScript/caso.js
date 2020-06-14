$(window).load(function () {
    $(".loader").fadeOut("slow");
});
$(document).ready(function () {
    $('#table_id').dataTable();

    $("body").on("click", "#selectConsulta", function () {
        var selectConsulta = $(this).val();
        $(".loader").fadeIn("slow");
        $.ajax({
            async: false,
            type: "GET",
            url: "/CasoServlet",
            data: 'selectConsulta=' + selectConsulta,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#IdCaso").val(respuesta[0]);
                    $("#tipoCaso").val(respuesta[1]);
                    $("#casoFechaAfectacion").val(respuesta[2]);
                    $("#casoParteAfectada").val(respuesta[3]);
                    $("#casoTimpoInca").val(respuesta[4]);
                    $("#casoDescripcion").val(respuesta[5]);
                    $("#CreadoPor").val(respuesta[6]);
                    $("#Asignado").val(respuesta[7]);
                    $("#casoCedulaPersona").val(respuesta[8]);
                    $('#verCaso').modal('show');
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
function myFunctionReload() {
    location.reload();
}
function guardarCaso() {
    $(".loader").fadeIn("slow");
    event.preventDefault();
    $('#crearCaso').modal('hide');
    var btnCrearCaso = 'ok';
    var cedulaPersona = $('#cedulaPersona').val();
    var cedulaUsuario = $('#cedulaUsuario').val();
    var Tipo = $('#Tipo').val();
    var fechaAfectacion = $('#fechaAfectacion').val();
    var parteAfectada = $('#parteAfectada').val();
    var tiempoInca = $('#tiempoInca').val();
    var descripcionCaso = $('#descripcionCaso').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/CasoServlet",
        data: 'btnCrearCaso=' + btnCrearCaso + '&cedulaPersona=' + cedulaPersona + '&cedulaUsuario=' + cedulaUsuario + '&Tipo=' + Tipo +
                '&fechaAfectacion=' + fechaAfectacion + '&parteAfectada=' + parteAfectada + '&tiempoInca=' + tiempoInca +
                '&descripcionCaso=' + descripcionCaso,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                        + ' </div>';
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

function validar() {
    event.preventDefault();
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres realizar los cambios ? </h3>'
            + ' </div>';
    $('#InfoConfirmar').html(cadena);
    $('#modalValidar').modal('show');
    $('#verCaso').modal('hide');
}

function modificarCaso() {
    $('#modalValidar').modal('hide');
    $(".loader").fadeIn("slow");
    event.preventDefault();
    var btnModificarCaso = 'ok';
    var IdCaso = $('#IdCaso').val();
    var tipoCaso = $('#tipoCaso').val();
    var casoFechaAfectacion = $('#casoFechaAfectacion').val();
    var casoParteAfectada = $('#casoParteAfectada').val();
    var casoTimpoInca = $('#casoTimpoInca').val();
    var casoDescripcion = $('#casoDescripcion').val();
    var casoCedulaPersona = $('#casoCedulaPersona').val();
    var CreadoPor = $('#CreadoPor').val();
    var Asignado = $('#Asignado').val();
    var cedulaUsuario = $('#cedulaUsuario').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/CasoServlet",
        data: 'btnModificarCaso=' + btnModificarCaso + '&IdCaso=' + IdCaso + '&tipoCaso=' + tipoCaso + '&casoFechaAfectacion=' + casoFechaAfectacion +
                '&casoParteAfectada=' + casoParteAfectada + '&casoTimpoInca=' + casoTimpoInca + '&casoDescripcion=' + casoDescripcion +
                '&casoCedulaPersona=' + casoCedulaPersona+'&CreadoPor=' + CreadoPor+'&Asignado=' + Asignado+'&cedulaUsuario=' + cedulaUsuario,
        success: function (data) {
            event.preventDefault();
             $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                        + ' </div>';
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
$(window).load(function () {
    $(".loader").fadeOut("slow");
});

$(document).ready(function () {
    $("#input-700").fileinput({
        language: 'es',
        showUpload: false,
        maxFileCount: 5

    });

    $("#uploadBtn").on("click", function () {
        var url = "/CargaArchivoServlet";
        var form = $("#sampleUploadFrm")[0];
        var data = new FormData(form);
        var src = $(this).attr('src');
        $.ajax({
            type: "POST",
            encType: "multipart/form-data",
            url: url,
            cache: false,
            processData: false,
            contentType: false,
            data: data,
            success: function (data) {
                var respuesta = $.trim(data);
                if (respuesta !== "") {
                    respuesta = respuesta.split(",");
                    var estatus = respuesta[0];
                    if (estatus == 1) {
                        $("#rutaArchivo").val(respuesta[1]);
                        $('#cargarArchivos').modal('hide');
                        $('#Exitoso').fadeIn(5000);
                        setTimeout(function () {
                            $('#Exitoso').fadeOut(5000);
                        }, 5000);
                    } else {
                        $('#Error').fadeIn(5000);
                        setTimeout(function () {
                            $('#Error').fadeOut(5000);
                        }, 5000);
                    }
                }
            },
            error: function (msg) {
                $('#Error').fadeIn(5000);
                setTimeout(function () {
                    $('#Error').fadeOut(5000);
                }, 5000);
            }
        });
    });
    $("body").on("click", "#editCaso", function () {
        var editCaso = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/CasoServlet",
            data: 'selectConsulta=' + editCaso,
            success: function (data) {
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
function validar() {
    event.preventDefault();
    var cadena = ' <div class="form-row">'
            + '<h5>¿Esta seguro que quieres realizar los cambios?</h3>'
            + ' </div>';
    $('#InfoConfirmar').html(cadena);
    $('#modalValidar').modal('show');
    $('#verCaso').modal('hide');
}

function validarEstado() {
    var cadena = ' <div class="form-row">'
            + '<h5>¿Esta seguro que quieres realizar los cambios?</h3>'
            + ' </div>';
    $('#infoEstado').html(cadena);
    $('#validarEstado').modal('show');
    $('#cambiarEstado').modal('hide');
}
function cambiarEstado() {
    var btnCambiarEstado = 'ok';
    var estadoCaso = $('#estadoCaso').val();
    var fechaCreacion = $('#fechaCreacion').val();
    var casoid = $('#casoid').val();
    var casoDescripcion = $('#casoDescripcion').val();
    var cedulaUsuario = $('#cedulaUsuario').val();
    var rutaArchivo = $('#rutaArchivo').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ExpedienteServlet",
        data: 'btnCambiarEstado=' + btnCambiarEstado + '&estadoCaso=' + estadoCaso + '&fechaCreacion=' + fechaCreacion
                + '&casoid=' + casoid + '&casoDescripcion=' + casoDescripcion + '&cedulaUsuario=' + cedulaUsuario + '&rutaArchivo=' + rutaArchivo,
        success: function (data) {
            event.preventDefault();
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
        }
    });
}
function modificarCaso() {
    $('#modalValidar').modal('hide');
    var btnModificarCasoExp = 'ok';
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
        data: 'btnModificarCasoExp=' + btnModificarCasoExp + '&IdCaso=' + IdCaso + '&tipoCaso=' + tipoCaso + '&casoFechaAfectacion=' + casoFechaAfectacion +
                '&casoParteAfectada=' + casoParteAfectada + '&casoTimpoInca=' + casoTimpoInca + '&casoDescripcion=' + casoDescripcion +
                '&casoCedulaPersona=' + casoCedulaPersona + '&CreadoPor=' + CreadoPor + '&Asignado=' + Asignado + '&cedulaUsuario=' + cedulaUsuario,
        success: function (data) {
            event.preventDefault();
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
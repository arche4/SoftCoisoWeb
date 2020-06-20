$(window).load(function () {
    $(".loader").fadeOut("slow");
});

$(document).ready(function () {

    $('#table_id').dataTable();

    $("#input-700").fileinput({
        language: 'es',
        showUpload: false,
        maxFileCount: 5

    });

    $("#uploadBtn").on("click", function () {
        $(".loader").fadeIn("slow");
        var url = "/CargaArchivoServlet";
        var form = $("#sampleUploadFrm")[0];
        var data = new FormData(form);
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
                $(".loader").fadeOut("slow");
                if (respuesta !== "") {
                    respuesta = respuesta.split(",");
                    var estatus = respuesta[0];
                    if (estatus === "Exitoso") {
                        $("#nombreArchivo").val(respuesta[1]);
                        $("#rutaArchivo").val(respuesta[2]);
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
    $("body").on("click", "#btnEliminarComentario", function () {
        $(".loader").fadeIn("slow");
        var btnConsultarCometario = $(this).val();
        $.ajax({
            async: false,
            type: "POST",
            url: "/ExpedienteServlet",
            data: 'btnConsultarCometario=' + btnConsultarCometario,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoComentario").val(respuesta[0]);
                    $("#comentarioEliminar").val(respuesta[2]);
                    $('#eliminarComentarioMod').modal('show');
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
    $("body").on("click", "#btnConsultarCometario", function () {
        $(".loader").fadeIn("slow");
        var btnConsultarCometario = $(this).val();
        $.ajax({
            async: false,
            type: "POST",
            url: "/ExpedienteServlet",
            data: 'btnConsultarCometario=' + btnConsultarCometario,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoComentarioMod").val(respuesta[0]);
                    $("#codigoCasoMod").val(respuesta[1]);
                    $("#comentarioMod").val(respuesta[2]);
                    $('#modificarComentario').modal('show');
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
    $("body").on("click", "#editCaso", function () {
        $(".loader").fadeIn("slow");
        var editCaso = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/CasoServlet",
            data: 'selectConsulta=' + editCaso,
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
    $("body").on("click", "#btnConsultarArchivo", function () {
        var btnConsultarArchivo = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/ExpedienteServlet",
            data: 'btnConsultarArchivo=' + btnConsultarArchivo,
            success: function (data) {
                $(".loader").fadeOut("slow");
                event.preventDefault();
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split(",");
                    $("#pdf").attr("src", respuesta[1]);
                    $("#nombre").val(respuesta[0]);
                    $('#visualizarPdf').modal('show');
                } else {
                    var cadena = '<div class="form-row">'
                            + '<h5>Lo sentimos, se ha presentado un problema guardando los datos .</h3>'
                            + ' </div>';
                    $('#modInferror').html(cadena);
                    $('#modalInfError').modal('show');
                }
            }
        });
    });

    $("body").on("click", "#verArchivo", function () {
        var rutaArchivo = $(this).val();
        $(".loader").fadeOut("slow");
        $("#pdf").attr("src", rutaArchivo);
        $('#visualizarPdf').modal('show');
    });

    $("body").on("click", "#btnEliminarArchivoProceso", function () {
        var cadena = ' <div class="form-row">'
                + '<h5> ¿ Esta seguro que quieres eliminar el archivo ?</h3>'
                + ' </div>';
        $('#modvalidar').html(cadena);
        $('#modValidarArhivoProceso').modal('show');
    });

    $("body").on("click", "#btnConsultarProceso", function () {
        $(".loader").fadeIn("slow");
        var btnConsultarProceso = $(this).val();
        $.ajax({
            async: false,
            type: "POST",
            url: "/procesoCalificacionServlet",
            data: 'btnConsultarProceso=' + btnConsultarProceso,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoProceso").val(respuesta[0]);
                    $("#procesoMod").val(respuesta[1]);
                    $("#comentarioProcesoMod").val(respuesta[2]);
                    $('#modificarProcesoCalificacion').modal('show');
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

    $("body").on("click", "#btnConsultarCalificacion", function () {
        $(".loader").fadeIn("slow");
        var btnConsultarCalificacion = $(this).val();
        $.ajax({
            async: false,
            type: "POST",
            url: "/procesoCalificacionServlet",
            data: 'btnConsultarCalificacion=' + btnConsultarCalificacion,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoCalificacion").val(respuesta[0]);
                    $("#diagnosticoCaliMod").val(respuesta[1]);
                    $("#porcentajeMod").val(respuesta[2]);
                    $("#comentarioCaliMod").val(respuesta[3]);
                    $('#modificarCalificacion').modal('show');
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

    $("body").on("click", "#btnConsultarReclamacion", function () {
        $(".loader").fadeIn("slow");
        var btnConsultarReclamacion = $(this).val();
        $.ajax({
            async: false,
            type: "POST",
            encoding: "UTF-8",
            url: "/ReclamacionServlet",
            data: 'btnConsultarReclamacion=' + btnConsultarReclamacion,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoReclamacion").val(respuesta[0]);
                    $("#comentarioReclamacionMod").val(respuesta[1]);
                    $('#modificarReclamacion').modal('show');
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
    
     $("body").on("click", "#btnEliminarArchivo", function () {
        var cadena = ' <div class="form-row">'
                + '<h5> ¿ Esta seguro que quieres eliminar el archivo ?</h3>'
                + ' </div>';
        $('#modvalidar').html(cadena);
        $('#modValidarArchivoReclamacion').modal('show');
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
    $('#cambiarEstado').modal('hide');
    $('#validarEstadoMod').modal('show');

}


function cambiarEstado() {
    var elmForm = $("#estadoCaso");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#cambiarEstado').modal('hide');
            var btnCambiarEstado = 'ok';
            var codigoEstado = $('#codigoEstado').val();
            var comentarioEstado = $('#comentarioEstado').val();
            var casoid = $('#casoid').val();
            var cedulaUsuario = $('#cedulaUsuario').val();
            var rutaArchivo = $('#rutaArchivo').val();
            var nombreArchivo = $('#nombreArchivo').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/ExpedienteServlet",
                data: 'btnCambiarEstado=' + btnCambiarEstado + '&codigoEstado=' + codigoEstado
                        + '&comentarioEstado=' + comentarioEstado + '&casoid=' + casoid + '&cedulaUsuario=' + cedulaUsuario
                        + '&rutaArchivo=' + rutaArchivo + '&nombreArchivo=' + nombreArchivo,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                                + ' </div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>Lo sentimos, se ha presentado un problema cambiando el estado .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');
                    } else if (data === "2") {
                        var cadena = '<div class="form-row">'
                                + '<h5>Lo sentimos, se ha presentado un problema actualizando el expediente .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');

                    }
                }
            });
        }
    }
}
function modificarCaso() {
    $(".loader").fadeIn("slow");
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

function comentar() {
    $(".loader").fadeIn("slow");
    var btnComentar = 'ok';
    var casoComentario = $('#casoComentario').val();
    var comentarioUsuario = $('#comentarioUsuario').val();
    var comentarioExpediente = $('#comentarioExpediente').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ExpedienteServlet",
        data: 'btnComentar=' + btnComentar + '&casoComentario=' + casoComentario + '&comentarioUsuario=' + comentarioUsuario
                + '&comentarioExpediente=' + comentarioExpediente,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                        + ' </div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema cambiando el estado .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            } else if (data === "2") {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema actualizando el expediente .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');

            }
        }
    });
}

function comentarioModificar() {
    $(".loader").fadeIn("slow");
    $('#modificarComentario').modal('hide');
    var btnModificarComentario = 'ok';
    var comentario = $('#comentarioMod').val();
    var codigoCaso = $('#codigoCasoMod').val();
    var codigoComentario = $('#codigoComentarioMod').val();
    var usuario = $('#usuarioCambio').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ExpedienteServlet",
        data: 'btnModificarComentario=' + btnModificarComentario + '&comentario=' + comentario + '&codigoCaso=' + codigoCaso
                + '&codigoComentario=' + codigoComentario + '&usuario=' + usuario,
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
        }
    });
}

function eliminarComentario() {
    $('#eliminarComentarioMod').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarComentario = $('#codigoComentario').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ExpedienteServlet",
        data: 'btnEliminarComentario=' + btnEliminarComentario,
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
        }
    });

}

function asignarUsuario() {
    var elmForm = $("#asignarForm");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#CambiarUsuario').modal('hide');
            $(".loader").fadeIn("slow");
            var btnAsginarUsuario = 'ok';
            var usuarioRespo = $('#usuarioRespo').val();
            var comentarioAsig = $('#comentarioAsig').val();
            var casoUsuer = $('#casoUsuer').val();
            var usuarioGestor = $('#usuarioGestor').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/ExpedienteServlet",
                data: 'btnAsginarUsuario=' + btnAsginarUsuario + '&usuarioRespo=' + usuarioRespo + '&comentarioAsig=' + comentarioAsig
                        + '&casoUsuer=' + casoUsuer + '&usuarioGestor=' + usuarioGestor,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                                + ' </div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>Lo sentimos, se ha presentado un problema cambiando el estado .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');
                    } else if (data === "2") {
                        var cadena = '<div class="form-row">'
                                + '<h5>Lo sentimos, se ha presentado un problema actualizando el expediente .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');

                    }
                }
            });
        }
    }

}

function guardarProceso() {
    var elmForm = $("#procesoCalificacion");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#agregarProcesoCalificacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnCrearProceso = 'ok';
            var proceso = $('#proceso').val();
            var comentarioProceso = $('#comentarioProceso').val();
            var nombreArchivoProceso = $('#nombreArchivo').val();
            var rutaArchivoProceso = $('#rutaArchivo').val();
            var usuarioProceso = $('#usuarioProceso').val();
            var casoIdProceso = $('#casoIdProceso').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/procesoCalificacionServlet",
                data: 'btnCrearProceso=' + btnCrearProceso + '&proceso=' + proceso + '&comentarioProceso=' + comentarioProceso
                        + '&nombreArchivoProceso=' + nombreArchivoProceso + '&rutaArchivoProceso=' + rutaArchivoProceso
                        + '&usuarioProceso=' + usuarioProceso + '&casoIdProceso=' + casoIdProceso,
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
                }
            });
        }
    }
}

function modificarProceso() {
    var elmForm = $("#procesoCalificacionMod");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#modificarProcesoCalificacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnModificarProceso = 'ok';
            var codigoProceso = $('#codigoProceso').val();
            var proceso = $('#procesoMod').val();
            var comentarioProceso = $('#comentarioProcesoMod').val();
            var usuarioProceso = $('#usuarioProcesoMod').val();
            var nombreArchivoProceso = $('#nombreArchivo').val();
            var rutaArchivoProceso = $('#rutaArchivo').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/procesoCalificacionServlet",
                data: 'btnModificarProceso=' + btnModificarProceso + '&codigoProceso=' + codigoProceso + '&proceso=' + proceso
                        + '&comentarioProceso=' + comentarioProceso + '&usuarioProceso=' + usuarioProceso + '&nombreArchivoProceso='
                        + nombreArchivoProceso + '&rutaArchivoProceso=' + rutaArchivoProceso,
                success: function (data) {
                    $('#modificarProcesoCalificacion').modal('hide');
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
                }
            });
        }
    }
}


function validarProcesoCalificacion() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el proceso de calificación ?</h3>'
            + ' </div>';
    $('#infoEstado').html(cadena);
    $('#modValidarProcesoCalificacion').modal('show');

}
function validarEliminarProceso() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el proceso ?</h3>'
            + ' </div>';
    $('#modeliminar').html(cadena);
    $('#modificarProcesoCalificacion').modal('hide');
    $('#modValidarProceso').modal('show');
}
function eliminarProceso() {
    $('#modValidarProceso').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarProceso = $('#codigoProceso').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/procesoCalificacionServlet",
        data: 'btnEliminarProceso=' + btnEliminarProceso,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El proceso fue eliminado con ex\u00EDto.</h3>'
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

function eliminarArchivo() {
    $('#modValidarArhivoProceso').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarArchivo = $('#btnEliminarArchivoProceso').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/procesoCalificacionServlet",
        data: 'btnEliminarArchivo=' + btnEliminarArchivo,
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
        }
    });

}

function crearCalificacion() {
    var elmForm = $("#calificacion");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#agregarCalificacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnCrearCalificacion = 'ok';
            var casoId = $('#expedienteId').val();
            var usuario = $('#usuarioLogeado').val();
            var diagnostico = $('#diagnosticoCali').val();
            var porcentaje = $('#porcentaje').val();
            var comentario = $('#comentarioCali').val();
            var nombreArchivo = $('#nombreArchivo').val();
            var rutaArchivo = $('#rutaArchivo').val();

            $.ajax({
                async: false,
                type: "POST",
                url: "/procesoCalificacionServlet",
                data: 'btnCrearCalificacion=' + btnCrearCalificacion + '&casoId=' + casoId + '&usuario=' + usuario
                        + '&diagnostico=' + diagnostico + '&porcentaje=' + porcentaje
                        + '&comentario=' + comentario + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "Exitoso") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La calificacion fue guardada  con ex\u00EDto.</h3>'
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
    }
}

function modificarCalificacion() {
    var elmForm = $("#calificacionMod");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#modificarCalificacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnModificarCalificacion = 'ok';
            var usuario = $('#usuarioLogeado').val();
            var DiagnosticoCalificacion = $('#diagnosticoCaliMod').val();
            var porcentaje = $('#porcentajeMod').val();
            var comentario = $('#comentarioCaliMod').val();
            var nombreArchivo = $('#nombreArchivo').val();
            var rutaArchivo = $('#rutaArchivo').val();
            var codigoCalificacion = $('#codigoCalificacion').val();

            $.ajax({
                async: false,
                type: "POST",
                url: "/procesoCalificacionServlet",
                data: 'btnModificarCalificacion=' + btnModificarCalificacion + '&usuario=' + usuario
                        + '&DiagnosticoCalificacion=' + DiagnosticoCalificacion + '&porcentaje=' + porcentaje
                        + '&comentario=' + comentario + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo
                        + '&codigoCalificacion=' + codigoCalificacion,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "Exitoso") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La calificacion fue guardada  con ex\u00EDto.</h3>'
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
    }
}

function validarCalifEli() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar la calificacion ?</h3>'
            + ' </div>';
    $('#modCali').html(cadena);
    $('#modificarCalificacion').modal('hide');
    $('#modalValidarCalificacion').modal('show');
}
function eliminarCalificacion() {
    $('#modalValidarCalificacion').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarCalificacion = $('#codigoCalificacion').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/procesoCalificacionServlet",
        data: 'btnEliminarCalificacion=' + btnEliminarCalificacion,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>La calificacion fue eliminada con ex\u00EDto.</h3>'
                        + ' </div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando los datos .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}

function crearReclamacion() {
    var elmForm = $("#reclamacion");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#agregarReclamacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnCrearReclamacion = 'ok';
            var casoId = $('#expedienteId').val();
            var usuario = $('#usuarioLogeado').val();
            var comentario = $('#comentarioReclamacion').val();
            var nombreArchivo = $('#nombreArchivo').val();
            var rutaArchivo = $('#rutaArchivo').val();

            $.ajax({
                async: false,
                type: "POST",
                url: "/ReclamacionServlet",
                data: 'btnCrearReclamacion=' + btnCrearReclamacion + '&casoId=' + casoId + '&usuario=' + usuario
                        + '&comentario=' + comentario + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "Exitoso") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La calificacion fue guardada  con ex\u00EDto.</h3>'
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
    }
}
function modificarReclamacion() {
    var elmForm = $("#reclamacionMod");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $('#modificarReclamacion').modal('hide');
            $(".loader").fadeIn("slow");
            var btnModificarReclamacion = 'ok';
            var codigo = $('#codigoReclamacion').val();
            var casoId = $('#expedienteId').val();
            var usuario = $('#usuarioLogeado').val();
            var comentario = $('#comentarioReclamacionMod').val();
            var nombreArchivo = $('#nombreArchivo').val();
            var rutaArchivo = $('#rutaArchivo').val();

            $.ajax({
                async: false,
                type: "POST",
                url: "/ReclamacionServlet",
                data: 'btnModificarReclamacion=' + btnModificarReclamacion + '&casoId=' + casoId + '&usuario=' + usuario
                        + '&comentario=' + comentario + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo
                        + '&codigo=' + codigo,
                success: function (data) {
                    event.preventDefault();
                    $(".loader").fadeOut("slow");
                    if (data === "Exitoso") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La reclamacion fue guardada  con ex\u00EDto.</h3>'
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
    }
}

function validarEliReclamacion() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar la reclamacion ?</h3>'
            + ' </div>';
    $('#modrecla').html(cadena);
    $('#modificarReclamacion').modal('hide');
    $('#modValiReclamacion').modal('show');
}

function eliminarReclamacion() {
    $('#modValiReclamacion').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarReclamacion = $('#codigoReclamacion').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ReclamacionServlet",
        data: 'btnEliminarReclamacion=' + btnEliminarReclamacion,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>La Reclamacion fue eliminada con ex\u00EDto.</h3>'
                        + ' </div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando los datos .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}

function eliminarArchivoReclamacion() {
    $('#modValidarArchivoReclamacion').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarArchivo = $('#btnEliminarArchivo').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/ReclamacionServlet",
        data: 'btnEliminarArchivo=' + btnEliminarArchivo,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El archivo fue eliminado con ex\u00EDto.</h3>'
                        + ' </div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando el archivo .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });

}
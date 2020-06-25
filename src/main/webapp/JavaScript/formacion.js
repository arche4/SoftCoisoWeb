var fechaCompleta1;
$(window).load(function () {
    $(".loader").fadeOut("slow");
});
document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {

        locale: 'es',
        plugins: ['dayGrid', 'timeGrid', 'interaction', 'list'],

        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        defaultView: 'dayGridMonth',
        defaultDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        //selectMirror: true,
        eventClick: function (info) {
            var codigo = info.event.title.split("(");
            codigo = codigo[0];
            $(window).load(function () {
                $(".loader").fadeOut("slow");
            });
            $.ajax({
                type: "GET",
                url: "/SoftCoisoWeb/FormacionServlet",
                data: '&btnConsultarFormacion=' + codigo,
                success: function (data) {
                    var respuesta = $.trim(data);
                    if (respuesta !== "") {
                        respuesta = respuesta.split("#");
                        $("#idFormacion").val(respuesta[0]);
                        $("#eliminarArchivo").val(respuesta[0]);
                        $("#horaIniMod").val(respuesta[1]);
                        $("#horaFinMod").val(respuesta[2]);
                        $("#tituloMod").val(respuesta[3]);
                        $("#comentarioMod").val(respuesta[4]);
                        $("#nombreFormadorMod").val(respuesta[5]);
                        $("#temaMod").val(respuesta[6]);
                        $("#verArchivo").val(respuesta[8]);
                        $("#pdf").attr("src", respuesta[8]);
                        $("#correoFormadorMod").val(respuesta[9]);
                        $("#nAsistentesMod").val(respuesta[10]);
                        $("#tipoFormacionMod").val(respuesta[11]);
                        $('#modificarFormacion').modal('show');
                    } else {
                        $('#ErrorGuardando').fadeIn(1000);
                        setTimeout(function () {
                            $('#ErrorGuardando').fadeOut(1000);
                        }, 5000);
                    }
                }
            });
        },
        dateClick: function (info) {
            fechaCompleta1 = info.dateStr;
            $("#crearFormacion").modal();

        },
        events: '/SoftCoisoWeb/FormacionServlet'
    });
    calendar.render();
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
    });
});

$(document).ready(function () {
    $("#input-700").fileinput({
        language: 'es',
        showUpload: false,
        maxFileCount: 5

    });

    $("body").on("click", "#eliminarArchivo", function () {
        var cadena = ' <div class="form-row">'
                + '<h5> ¿ Esta seguro que quieres eliminar el archivo ?</h3>'
                + ' </div>';
        $('#modArchivo').html(cadena);
        $('#modificarFormacion').modal('hide');
        $('#validarArchivo').modal('show');
    });

    $("body").on("click", "#verArchivo", function () {
        var rutaArchivo = $(this).val();
        $(".loader").fadeOut("slow");
        $("#archivoPdf").attr("src", rutaArchivo);
        $('#visualizarPdf').modal('show');
    });

    $("#uploadBtn").on("click", function () {
        $(".loader").fadeIn("slow");
        var url = "/SoftCoisoWeb/CargarArchivoFormacionServlet";
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
});
function myFunctionReload() {
    location.reload();
}
function guardar() {
    $(".loader").fadeIn("slow");
    $('#crearFormacion').modal('hide');
    var horaIni = $('#horaIni').val();
    var horaFin = $('#horaFin').val();
    var iniHora = horaIni[0] + "" + horaIni[1] + "" + horaIni[3] + "" + horaIni[4] + "" + '00';
    var finHora = horaFin[0] + "" + horaFin[1] + "" + horaFin[3] + "" + horaFin[4] + "" + '00';
    if (finHora > iniHora) {
        var btnCrearFormacion = 'ok';
        var tipoFormacion = $('#tipoFormacion').val();
        var correoFormador = $('#correoFormador').val();
        var emailUsuario = $('#emailUsuario').val();
        var usuario = $('#cedulaUsuario').val();
        var titulo = $('#titulo').val();
        var tema = $('#tema').val();
        var nAsistentes = $('#nAsistentes').val();
        var nombreFormador = $('#nombreFormador').val();
        var nombreArchivo = $('#nombreArchivo').val();
        var rutaArchivo = $('#rutaArchivo').val();
        var descripcion = $('#comentario').val();
        var fechaCompletaSplit = fechaCompleta1.split("T");
        var fecha = fechaCompletaSplit[0].split("-");
        var ano = fecha[0];
        var mes = fecha[1];
        var dia = fecha[2];
        $.ajax({
            async: false,
            type: "POST",
            url: "/SoftCoisoWeb/FormacionServlet",
            data: 'tipoFormacion=' + tipoFormacion + '&iniHora=' + iniHora + '&finHora=' + finHora + '&correoFormador=' + correoFormador +
                    '&emailUsuario=' + emailUsuario + '&usuario=' + usuario + '&titulo=' + titulo + '&descripcion=' + descripcion +
                    '&btnCrearFormacion=' + btnCrearFormacion + '&ano=' + ano + '&mes=' + mes + '&dia=' + dia + '&tema=' + tema + '&nAsistentes=' + nAsistentes
                    + '&nombreFormador=' + nombreFormador + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                if (data === "Exitosa") {
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
    } else {
        $(".loader").fadeOut("slow");
        $('#ErrorFechas').fadeIn(7000);
        setTimeout(function () {
            $('#ErrorFechas').fadeOut(7000);
        }, 7000);
    }
}

function validar() {
    event.preventDefault();
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres realizar los cambios ? </h3>'
            + ' </div>';
    $('#InfoConfirmar').html(cadena);
    $('#modalValidar').modal('show');
    $('#modificarFormacion').modal('hide');
}

function modificarFormacion() {
    $('#modificarFormacion').modal('hide');
    $(".loader").fadeIn("slow");
    var iniHora = $('#horaIniMod').val();
    var finHora = $('#horaFinMod').val();
    var IniHora = iniHora[0] + "" + iniHora[1] + "" + iniHora[3] + "" + iniHora[4] + "" + '00';
    var FinHora = finHora[0] + "" + finHora[1] + "" + finHora[3] + "" + finHora[4] + "" + '00';
    if (finHora > iniHora) {
        var btnModificarFormacion = 'ok';
        var idFormacion = $('#idFormacion').val();
        var tipoFormacion = $('#tipoFormacionMod').val();
        var correoFormador = $('#correoFormadorMod').val();
        var emailUsuario = $('#emailUsuario').val();
        var usuario = $('#cedulaUsuario').val();
        var titulo = $('#tituloMod').val();
        var tema = $('#temaMod').val();
        var nAsistentes = $('#nAsistentesMod').val();
        var nombreFormador = $('#nombreFormadorMod').val();
        var nombreArchivo = $('#nombreArchivo').val();
        var rutaArchivo = $('#rutaArchivo').val();
        var descripcion = $('#comentarioMod').val();
        $.ajax({
            async: false,
            type: "POST",
            url: "/SoftCoisoWeb/FormacionServlet",
            data: 'tipoFormacion=' + tipoFormacion + '&IniHora=' + IniHora + '&FinHora=' + FinHora + '&correoFormador=' + correoFormador +
                    '&emailUsuario=' + emailUsuario + '&usuario=' + usuario + '&titulo=' + titulo + '&descripcion=' + descripcion +
                    '&btnModificarFormacion=' + btnModificarFormacion + '&tema=' + tema + '&nAsistentes=' + nAsistentes
                    + '&nombreFormador=' + nombreFormador + '&nombreArchivo=' + nombreArchivo + '&rutaArchivo=' + rutaArchivo + '&idFormacion=' + idFormacion,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                if (data === "Exitosa") {
                    var cadena = ' <div class="form-row">'
                            + '<h4>Genial!!!.</h4>'
                            + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h5>'
                            + ' </div>';
                    $('#modInfexito').html(cadena);
                    $('#modalInfexito').modal('show');
                } else {
                    var cadena = '<div class="form-row">'
                            + '<h4>Lo sentimos!!</h4>'
                            + '<h5>Se  presento un problema guardando los datos .</h3>'
                            + '</div>';
                    $('#modInferror').html(cadena);
                    $('#modalInfError').modal('show');
                }
            }
        });
    } else {
        $(".loader").fadeOut("slow");
        $('#ErrorFechas').fadeIn(7000);
        setTimeout(function () {
            $('#ErrorFechas').fadeOut(7000);
        }, 7000);
    }
}

function validarEliminar() {
    event.preventDefault();
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar la formacion ? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#modificarFormacion').modal('hide');
}

function Eliminar() {
    $('#modalEliminar').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarFormacion = 'ok';
    var codigoFormacion = $('#idFormacion').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/FormacionServlet",
        data: 'btnEliminarFormacion=' + btnEliminarFormacion + '&codigoFormacion=' + codigoFormacion,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitosa") {
                var cadena = ' <div class="form-row">'
                        + '<h4>Genial!!!.</h4>'
                        + '<h5>Se elimino correctamente la formación.</h5>'
                        + ' </div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else {
                var cadena = '<div class="form-row">'
                        + '<h4>Lo sentimos!!</h4>'
                        + '<h5>Se  presento un problema eliminando los datos .</h3>'
                        + '</div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}

function eliminarArchivo() {
    $('#validarArchivo').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarArchivo = $('#eliminarArchivo').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/FormacionServlet",
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
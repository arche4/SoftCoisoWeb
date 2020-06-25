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
                url: "/SoftCoisoWeb-V.1/CalendarServlet",
                data: '&codigo=' + codigo,
                success: function (data) {
                    var respuesta = $.trim(data);
                    if (respuesta !== "") {
                        respuesta = respuesta.split(",");
                        $('#CitaInfo').html(' <div class="form-row">  <div class="form-group col-md-6">'
                                + '<label for="cedula">Cedula</label>'
                                + '<input type="number" class="form-control" id="citaCedula" name="citaCedula"  placeholder="Cedula"  readonly> </div>'
                                + '<div class="form-group col-md-6">'
                                + ' <label class="control-label">Nombre</label>'
                                + '<input type="text" class="form-control" id="citaNom" name="citaNom" readonly> </div> </div>'
                                + '<div class="form-row"> <div class="form-group col-md-6">'
                                + '<label class="control-label">Hora de inicio</label>'
                                + '<input type="time" class="form-control" id="CitaIniHora" name="CitaIniHora"> </div>'
                                + '<div class="form-group col-md-6">'
                                + '<label class="control-label">Hora de fin</label>'
                                + '<input type="time" class="form-control" id="CitaFinHora" name="CitaFinHora"> </div> </div>'
                                + '<div class="form-row"> <div class="form-group col-md-6"> '
                                + '<label class="control-label">Email</label>'
                                + '<input class="form-control " id="Citaemail" type="email" name="Citaemail"> </div> '
                                + '<div class="form-group col-md-6">'
                                + '<label class="control-label">Titulo</label>'
                                + '<input class="form-control" id="Citatitulo" type="text" name="Citatitulo" placeholder="Titulo"> </div> </div>'
                                + '<div class="form-row">'
                                + '<div class="form-group col-lg-12">'
                                + '<label class="control-label">Descripcion</label>'
                                + '<textarea class="form-control" id="Citacomentario" name="Citacomentario"></textarea> </div> </div>'
                                + '<div class="form-row">'
                                + '<div class="form-group">'
                                + '<label for="agree" class="control-label col-md-4">Enviar Corero Electronico</label>'
                                + '<input type="checkbox" style="width: 20px" class="checkbox form-control" id="envioEmail" name="envioEmail" />'
                                + '</div></div>'
                                + '<input class="form-control " id="emailUsuarioCita" type="hidden" name="emailUsuarioCita">'
                                + '<input class="form-control " id="cedulaUsuarioCita" type="hidden" name="cedulaUsuarioCita">'
                                + '<input class="form-control " id="codigoCita" type="hidden" name="codigoCita">'
                                + '<input class="form-control " id="anoCita" type="hidden" name="anoCita">'
                                + '<input class="form-control " id="mesCita" type="hidden" name="mesCita">'
                                + '<input class="form-control " id="diaCita" type="hidden" name="diaCita">'
                                )
                        $("#citaCedula").val(respuesta[8]);
                        $("#Citaemail").val(respuesta[9]);
                        $("#Citatitulo").val(respuesta[6]);
                        $("#Citacomentario").val(respuesta[7]);
                        $("#citaNom").val(respuesta[10]);
                        $("#CitaIniHora").val(respuesta[4]);
                        $("#CitaFinHora").val(respuesta[5]);
                        $("#emailUsuarioCita").val(respuesta[11]);
                        $("#cedulaUsuarioCita").val(respuesta[12]);
                        $("#codigoCita").val(respuesta[0]);
                        $("#anoCita").val(respuesta[1]);
                        $("#mesCita").val(respuesta[2]);
                        $("#diaCita").val(respuesta[3]);
                        $('#mostrarCita').modal('show');
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
            var moment = calendar.getDate();
            moment = moment.toISOString();
            moment = moment.split("T");
            var fechaJS = new Date();
            if (fechaJS.getMonth() < 10 && fechaJS.getDate() < 10) {
                fechaJS = fechaJS.getFullYear() + "-0" + (fechaJS.getMonth() + 1) + "-0" + (fechaJS.getDate());
            } else if (fechaJS.getDate() < 10) {
                fechaJS = fechaJS.getFullYear() + "-" + (fechaJS.getMonth() + 1) + "-0" + (fechaJS.getDate());
            } else if (fechaJS.getMonth() < 10) {
                fechaJS = fechaJS.getFullYear() + "-0" + (fechaJS.getMonth() + 1) + "-" + (fechaJS.getDate());
            } else {
                fechaJS = fechaJS.getFullYear() + "-" + (fechaJS.getMonth() + 1) + "-" + (fechaJS.getDate());
            }
            var fechaCompleta = info.dateStr;
            if (fechaCompleta < fechaJS) {
                $('#Error').fadeIn(1000);
                setTimeout(function () {
                    $('#Error').fadeOut(1000);
                }, 5000);
            } else {
                fechaCompleta = fechaCompleta.split('T');
                if (fechaCompleta.length > 1) {
                    var auxfecha = fechaCompleta[1].split('-');
                    var horaJS = new Date();
                    if (horaJS.getHours() < 10) {
                        hsystem = "0" + horaJS.getHours();
                    } else {
                        hsystem = horaJS.getHours();
                    }
                    if (horaJS.getMinutes() < 10) {
                        msystem = "0" + horaJS.getMinutes();
                    } else {
                        msystem = horaJS.getMinutes();
                    }
                    if (horaJS.getSeconds() < 10) {
                        ssystem = "0" + horaJS.getSeconds();
                    } else {
                        ssystem = horaJS.getSeconds();
                    }
                    horaJS = hsystem + ":" + msystem + ":" + ssystem;
                    if ((auxfecha[0] < horaJS) && (fechaJS === fechaCompleta[0])) {
                        $('#Error').fadeIn(1000);
                        setTimeout(function () {
                            $('#Error').fadeOut(1000);
                        }, 5000);
                    } else {
                        $("#crearCita").modal();
                    }
                } else {
                    $("#crearCita").modal();
                }
            }
        },
        events: '/SoftCoisoWeb-V.1/CargarDatosCalendarServlet'
    });
    calendar.render();
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
    });
});

$(document).ready(function() {
    $('.js-example-basic-single').select2();
});
function myFunctionReload() {
    location.reload();
}
function guardar() {
     $('#crearCita').modal('hide');
    $(".loader").fadeIn("slow");
    var horaIni = $('#horaIni').val();
    var horaFin = $('#horaFin').val();
    var iniHora = horaIni[0] + "" + horaIni[1] + "" + horaIni[3] + "" + horaIni[4] + "" + '00';
    var finHora = horaFin[0] + "" + horaFin[1] + "" + horaFin[3] + "" + horaFin[4] + "" + '00';
    if (finHora > iniHora) {
        var btnCrearCita = 'si';
        var persona = $('#persona').val();
        var email = $('#emailPersona').val();
        var emailUsuario = $('#emailUsuario').val();
        var cedulaUsuario = $('#cedulaUsuario').val();
        var titulo = $('#titulo').val();
        var comentario = $('#comentario').val();
        var fechaCompletaSplit = fechaCompleta1.split("T");
        var fecha = fechaCompletaSplit[0].split("-");
        var ano = fecha[0];
        var mes = fecha[1];
        var dia = fecha[2];
        $.ajax({
            async: false,
            type: "POST",
            url: "/SoftCoisoWeb-V.1/CalendarServlet",
            data: 'persona=' + persona + '&iniHora=' + iniHora + '&finHora=' + finHora + '&email=' + email +
                    '&emailUsuario=' + emailUsuario + '&cedulaUsuario=' + cedulaUsuario + '&titulo=' + titulo + '&comentario=' + comentario + '&btnCrearCita=' + btnCrearCita
                    + '&ano=' + ano + '&mes=' + mes + '&dia=' + dia,
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
    $('#mostrarCita').modal('hide');
}

function Modificar() {
    $('#modalValidar').modal('hide');
    $(".loader").fadeIn("slow");
    var btnModificarCita = 'ok';
    var citaCedula = $('#citaCedula').val();
    var citaNom = $('#citaNom').val();
    var iniHora = $('#CitaIniHora').val();
    var finHora = $('#CitaFinHora').val();
    var Citaemail = $('#Citaemail').val();
    var Citatitulo = $('#Citatitulo').val();
    var Citacomentario = $('#Citacomentario').val();
    var emailUsuarioCita = $('#emailUsuarioCita').val();
    var cedulaUsuarioCita = $('#cedulaUsuarioCita').val();
    var codigoCita = $('#codigoCita').val();
    var anoCita = $('#anoCita').val();
    var mesCita = $('#mesCita').val();
    var diaCita = $('#diaCita').val();
    var CitaIniHora = iniHora[0] + "" + iniHora[1] + "" + iniHora[3] + "" + iniHora[4] + "" + '00';
    var CitaFinHora = finHora[0] + "" + finHora[1] + "" + finHora[3] + "" + finHora[4] + "" + '00';
    var enviarEmail;
    var enviarCorreo = 'No';
    if ((enviarEmail = $("#envioEmail:checked").val()) !== null) {
        var enviarCorreo = 'Si';
    }
    if (finHora > iniHora) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/SoftCoisoWeb-V.1/CalendarServlet",
            data: 'btnModificarCita=' + btnModificarCita + '&citaCedula=' + citaCedula + '&citaNom=' + citaNom + '&CitaIniHora=' + CitaIniHora + '&CitaFinHora=' + CitaFinHora +
                    '&Citaemail=' + Citaemail + '&Citatitulo=' + Citatitulo + '&Citacomentario=' + Citacomentario + '&emailUsuarioCita=' + emailUsuarioCita + '&cedulaUsuarioCita=' + cedulaUsuarioCita
                    + '&codigoCita=' + codigoCita + '&anoCita=' + anoCita + '&mesCita=' + mesCita + '&diaCita=' + diaCita + '&enviarCorreo=' + enviarCorreo,
            success: function (data) {
                event.preventDefault();
                $(".loader").fadeOut("slow");
                if (data === "Exitoso") {
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
            + '<h5> ¿ Esta seguro que quieres eliminar la cita ? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#mostrarCita').modal('hide');
}

function Eliminar() {
    $('#modalEliminar').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminarCita = 'ok';
    var codigoCita = $('#codigoCita').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb-V.1/CalendarServlet",
        data: 'btnEliminarCita=' + btnEliminarCita + '&codigoCita=' + codigoCita,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "Exitoso") {
                var cadena = ' <div class="form-row">'
                        + '<h4>Genial!!!.</h4>'
                        + '<h5>Se elimino correctamente la cita.</h5>'
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
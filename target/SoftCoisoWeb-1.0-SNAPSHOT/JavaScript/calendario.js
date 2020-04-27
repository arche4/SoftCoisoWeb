var fechaCompleta1;

document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid', 'timeGrid', 'interaction'],
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        defaultView: 'dayGridMonth',
        defaultDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        //selectMirror: true,
        eventClick: function (info) {
            var codigo = info.event.title.split("(");
            codigo = codigo[0];
            $.ajax({
                type: "GET",
                url: "/CalendarServlet",
                data: '&codigo=' + codigo,
                success: function (data) {
                    var respuesta = $.trim(data);
                    if (respuesta != "") {
                        respuesta = respuesta.split(",");
                        if (respuesta[0] < 10) {
                            respuesta[0] = "0" + respuesta[0];
                        }
                        if (respuesta[1] < 10) {
                            respuesta[1] = "0" + respuesta[1];
                        }
                        if (respuesta[2] < 10) {
                            respuesta[2] = "0" + respuesta[2];
                        }
                        $('#CitaInfo').html(' <div class="form-row">  <div class="form-group col-md-6">'
                                + '<label for="cedula">Cedula</label>'
                                + '<p></p>'
                                + '<span>' + respuesta[8] + '</span>'
                                + '<input name="cedulaUser" id="cedulaUser" type="hidden" class="form-control" value=' + respuesta[8] + '> </div>'
                                + '<div class="form-group col-md-6">'
                                + '<label for="nombre">Nombre</label>'
                                + '<input name="nomUser" id="nomUser" class="form-control" value=' + respuesta[10] + '> </div> </div>'
                                + '<div class="form-row"> <div class="form-group col-md-6">'
                                + '<label for="apellido">Apellido</label>'
                                + '<input name="apellidoUser" id="apellidoUser" class="form-control" value=' + respuesta[9] + '> </div>'
                                + '<div class="form-group col-md-6">'
                                + '<label for="rol">Rol</label>'
                                + '<input name="rolUser" id="rolUser" class="form-control" value=' + respuesta[5] + '> </div> </div>'
                                + '<div class="form-row"> <div class="form-group col-md-6"> '
                                + '<label for="Clave">Clave</label>'
                                + '<input type="password" name="ClaveUser" id="ClaveUser" class="form-control" value=' + respuesta[7] + '> </div> </div>'

                                )
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
                    if ((auxfecha[0] < horaJS) && (fechaJS == fechaCompleta[0])) {
                        $('#Error').fadeIn(1000);
                        setTimeout(function () {
                            $('#Error').fadeOut(1000);
                        }, 5000);
                    } else {
                        $("#crearCita").modal()
                    }
                } else {
                    $("#crearCita").modal()
                }
            }
        },
        events: '/CargarDatosCalendarServlet'
    });
    calendar.render();
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
    });
});
function myFunctionReload() {
    location.reload();
}
function guardar() {
    $('#crearCita').modal('hide');
    $("#correctamente").modal('show');
    var horaIni = $('#horaIni').val();
    var horaFin = $('#horaFin').val();
    var iniHora = horaIni[0] + "" + horaIni[1] + "" + horaIni[3] + "" + horaIni[4] + "" + '00';
    var finHora = horaFin[0] + "" + horaFin[1] + "" + horaFin[3] + "" + horaFin[4] + "" + '00';
    if (finHora > iniHora) {
        var btnCrearCita = 'si';
        var cedula = $('#cedula').val();
        var nombrePersona = $('#nombrePersona').val();
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
            url: "/CalendarServlet",
            data: 'cedula=' + cedula + '&nombrePersona=' + nombrePersona + '&iniHora=' + iniHora + '&finHora=' + finHora + '&email=' + email +
                    '&emailUsuario=' + emailUsuario + '&cedulaUsuario=' + cedulaUsuario + '&titulo=' + titulo + '&comentario=' + comentario + '&btnCrearCita=' + btnCrearCita
                    + '&ano=' + ano + '&mes=' + mes + '&dia=' + dia,
            success: function (data) {
                $(window).load(function () {
                    $(".loader").fadeOut("slow");
                });
                if (data == "Exitoso") {
                    var cadena = ' <div class="form-row">'
                            + '<h3>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                            + ' </div>';
                    $('#modInfexito').html(cadena)
                    $('#modalInfexito').modal('show');
                } else {
                    $('#ErrorGuardando').fadeIn(1000);
                    setTimeout(function () {
                        $('#ErrorGuardando').fadeOut(1000);
                    }, 5000);
                }
            }
        });
    } else {
        $('#ErrorFechas').fadeIn(7000);
        setTimeout(function () {
            $('#ErrorFechas').fadeOut(7000);
        }, 7000);
    }
}
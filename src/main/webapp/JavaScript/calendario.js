var fechaClick;
var fechaCompleta;
document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['interaction', 'dayGrid', 'timeGrid'],
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        defaultView: 'dayGridMonth',
        defaultDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        dateClick: function (info) {
            fechaCompleta = info.dateStr;
            $("#crearCita").modal()
        },
    });

    calendar.render();
});
function guardar() {
    var btnCrearCita = 'si';
    var cedula = $('#cedula').val();
    var nombrePersona = $('#nombrePersona').val();
    var horaIni = $('#horaIni').val();
    var horaFin = $('#horaFin').val();
    var email = $('#emailPersona').val();
    var emailUsuario = $('#emailUsuario').val();
    var cedulaUsuario = $('#cedulaUsuario').val();
    var titulo = $('#titulo').val();
    var comentario = $('#comentario').val();
    var fechaCompletaSplit = fechaCompleta.split("T");
    var fecha = fechaCompletaSplit[0].split("-");
    var ano = fecha[0];
    var mes = fecha[1];
    var dia = fecha[2];
    $.ajax({
        async: false,
        type: "GET",
        url: "/CalendarServlet",
        data: 'cedula=' + cedula + '&nombrePersona=' + nombrePersona + '&horaIni=' + horaIni + '&horaFin=' + horaFin + '&email=' + email +
                '&emailUsuario=' + emailUsuario + '&cedulaUsuario=' + cedulaUsuario + '&titulo=' + titulo + '&comentario=' + comentario + '&btnCrearCita=' + btnCrearCita
                + '&ano=' + ano + '&mes=' + mes + '&dia=' + dia,
        success: function (data) {
       console.log(data);
           
        }
        
    });
}

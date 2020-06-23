$body = $("body");
$(document).ready(function () {
     $(".loader").fadeOut("slow");
     
    $('#rangTime').daterangepicker({
        timePicker: true,
        timePickerIncrement: 1,
        locale: {
            format: 'YYYY-MM-DD HH:mm'
        }
    });

    $("#btnDescargar").click(function () {
       window.location = 'http://127.0.0.1:8887//SoftCoisoWeb-V.1Personas.xls';
    });

    //EL BOTON CONSULTAR EJECUTA  
    $("body").on("click", "#botCons", function () {
        $(".loader").fadeIn("slow");
        var tCons;
        var fecha_i;
        var fecha_f;
        var btnReporte = 'ok';
        event.stopPropagation();
        event.preventDefault();
        tCons = $('#selectConsulta option:selected').text();
        fecha_i = ($('#rangTime').val()).substring(0, 16);
        fecha_f = ($('#rangTime').val()).substring(19, 41);
        $.when($.ajax({
            type: "POST",
            url: "/ReportesServlet",
            data: 'tCons=' + tCons + '&fecha_i=' + fecha_i + '&fecha_f=' + fecha_f + '&btnReporte=' + btnReporte,
            success: function (data) {
                console.log(data);
            }
        })).done(function (data) {
            $(".loader").fadeOut("slow");
            $("#btnDescargar").attr("urlDescarga", tCons + ".xls");
            $("#btnDescargar").prop("disabled", false);
            $body.removeClass("loading");
            $('#miTabla').html(data);
            var idTabla = $('#miTabla table').attr("id");
            $("#" + idTabla).DataTable({
                "language": {
                    "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
                    "zeroRecords": "No se encontraron datos ",
                    "info": "Mostrando p&aacute;gina _PAGE_ de _PAGES_",
                    "infoEmpty": "No hay registros disponibles",
                    "infoFiltered": "(Filtrados de un total de _MAX_ registros)",
                    "search": "Buscar registro:",
                    "pagingType": {
                        "next": "Siguiente",
                        "previous": "Anterior"
                    },
                    "loadingRecords": "Cargando..."
                },

            });

        });
    });
});

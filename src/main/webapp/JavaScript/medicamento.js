$(window).load(function () {
    $(".loader").fadeOut("slow");
});


$(document).ready(function () {
    $('#table_id').dataTable();

    $("body").on("click", "#consultarMedicamento", function () {
        $(".loader").fadeIn("slow");
        var consultarMedicamento = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/MedicamentosServlet",
            data: 'consultarMedicamento=' + consultarMedicamento,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#medCod").val(respuesta[0]);
                    $("#medNom").val(respuesta[1]);
                    $("#medDescrip").val(respuesta[2]);
                    $('#verMedicamento').modal('show');
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
function guardar() {
    var elmForm = $("#medicamento");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#medicamentoMod').modal('hide');
            var btnCrearMedicamento = 'ok';
            var codigoMedicamento = $('#codMed').val();
            var nombreMedicamento = $('#nomMed').val();
            var descripcionMedicamento = $('#descripMed').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/MedicamentosServlet",
                data: 'btnCrearMedicamento=' + btnCrearMedicamento + '&codigoMedicamento=' + codigoMedicamento + '&nombreMedicamento=' + nombreMedicamento
                        + '&descripcionMedicamento=' + descripcionMedicamento,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>El medicamento fue guardado con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>El medicamento ya existe, intente con otro nuevo .</h3>'
                                + ' </div>';
                        $('#modInferror').html(cadena);
                        $('#modalInfError').modal('show');
                    } else if (data === "2") {
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
            $('#verMedicamento').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el medicamento? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#verMedicamento').modal('hide');
}
function modificarMedicamento() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var codigoMedicamento = $('#medCod').val();
    var nombreMedicamento = $('#medNom').val();
    var descripcionMedicamento = $('#medDescrip').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/MedicamentosServlet",
        data: 'btnModificar=' + btnModificar + '&codigoMedicamento=' + codigoMedicamento + '&nombreMedicamento=' + nombreMedicamento
                + '&descripcionMedicamento=' + descripcionMedicamento,
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
function eliminarMedicamento() {
    $(".loader").fadeIn("slow");
    $('#modalEliminar').modal('hide');
    var btnEliminar = $('#medCod').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/MedicamentosServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El medicamento fue eliminado con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>El medicamento no se puede eliminar, esta siendo usado.</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            } else if (data === "2") {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando el medicamento .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}
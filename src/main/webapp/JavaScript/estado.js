$(window).load(function () {
    $(".loader").fadeOut("slow");
});
$(document).ready(function () {
    $('#table_id').dataTable();

    $("body").on("click", "#btnConsultar", function () {
        $(".loader").fadeIn("slow");
        var btnConsultar = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/SoftCoisoWeb-V.1/EstadoCasoServlet",
            data: 'btnConsultar=' + btnConsultar,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#estadoCodigo").val(respuesta[0]);
                    $("#estadoNom").val(respuesta[1]);
                    $("#estadoDescrip").val(respuesta[2]);
                    $('#verEstado').modal('show');
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
    var elmForm = $("#estadoCaso");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#modEstado').modal('hide');
            var btnCrear = 'ok';
            var codigoEstado = $('#codEstado').val();
            var nombreEstado = $('#nomEstado').val();
            var descripcionEstado = $('#descripEstado').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/SoftCoisoWeb-V.1/EstadoCasoServlet",
                data: 'btnCrear=' + btnCrear + '&codigoEstado=' + codigoEstado + '&nombreEstado=' + nombreEstado
                        + '&descripcionEstado=' + descripcionEstado,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>El estado de caso fue guardado con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>El Estado ya existe, intente con otro nuevo .</h3>'
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
    var elmForm = $("#estadoVer");
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
            $('#verEstado').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el estado? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#verEstado').modal('hide');
}
function modificar() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var codigoEstado = $('#estadoCodigo').val();
    var nombreEstado = $('#estadoNom').val();
    var descripcionEstado = $('#estadoDescrip').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb-V.1/EstadoCasoServlet",
        data: 'btnModificar=' + btnModificar + '&codigoEstado=' + codigoEstado + '&nombreEstado=' + nombreEstado
                + '&descripcionEstado=' + descripcionEstado,
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
function eliminar() {
    $(".loader").fadeIn("slow");
    $('#modalEliminar').modal('hide');
    var btnEliminar = $('#estadoCodigo').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/SoftCoisoWeb-V.1/EstadoCasoServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El Estado fue eliminado con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>El Estado de caso no se puede eliminar, porque esta asociado a un caso.</h5>'
                        + '<h5>Intente cambiando el Estado del caso.</h5>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            } else if (data === "2") {
                var cadena = '<div class="form-row">'
                        + '<h5>Lo sentimos, se ha presentado un problema eliminando los datos .</h3>'
                        + ' </div>';
                $('#modInferror').html(cadena);
                $('#modalInfError').modal('show');
            }
        }
    });
}



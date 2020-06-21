$(window).load(function () {
    $(".loader").fadeOut("slow");
});
$(document).ready(function () {
    $('#table_id').dataTable();

    $("body").on("click", "#consultarTipoCaso", function () {
        $(".loader").fadeIn("slow");
        var consultarTipoCaso = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/TipoCasoServlet",
            data: 'consultarTipoCaso=' + consultarTipoCaso,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#tipoCod").val(respuesta[0]);
                    $("#tipoNom").val(respuesta[1]);
                    $("#tipoDescrip").val(respuesta[2]);
                    $('#verTipoCaso').modal('show');
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
    var elmForm = $("#tipoCaso");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#modTipo').modal('hide');
            var btnCrearTipoCaso = 'ok';
            var codigoTipoCaso = $('#codTipo').val();
            var nombreTipoCaso = $('#nomTipo').val();
            var descripcionTipoCaso = $('#descripTipo').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/TipoCasoServlet",
                data: 'btnCrearTipoCaso=' + btnCrearTipoCaso + '&codigoTipoCaso=' + codigoTipoCaso + '&nombreTipoCaso=' + nombreTipoCaso
                        + '&descripcionTipoCaso=' + descripcionTipoCaso,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>El Tipo de caso fue guardado con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>El codigo de tipo ya existe, intente con otro nuevo .</h3>'
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
    var elmForm = $("#modTipoCaso");
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
            $('#verTipoCaso').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el tipo de caso? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#verTipoCaso').modal('hide');
}
function modificarTipo() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var codigoTipoCaso = $('#tipoCod').val();
    var nombreTipoCaso = $('#tipoNom').val();
    var descripcionTipoCaso = $('#tipoDescrip').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/TipoCasoServlet",
        data: 'btnModificar=' + btnModificar + '&codigoTipoCaso=' + codigoTipoCaso + '&nombreTipoCaso=' + nombreTipoCaso
                + '&descripcionTipoCaso=' + descripcionTipoCaso,
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
function eliminarTipo() {
    $(".loader").fadeIn("slow");
    $('#modalEliminar').modal('hide');
    var btnEliminar = $('#tipoCod').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/TipoCasoServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>El tipo de caso fue eliminado con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>El tipo de caso no se puede eliminar, porque esta asociado a un caso.</h5>'
                        + '<h5>Intente cambiando el tipo de caso.</h5>'
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


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
            url: "/SoftCoisoWeb/GrupoSindicatoServlet",
            data: 'btnConsultar=' + btnConsultar,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#sindicatoCodigo").val(respuesta[0]);
                    $("#sindicatoNom").val(respuesta[1]);
                    $('#verSindicato').modal('show');
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
    var elmForm = $("#sindicato");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#modSindicato').modal('hide');
            var btnCrear = 'ok';
            var codigoSindicato = $('#codigo').val();
            var nombreSindicato = $('#nombre').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/SoftCoisoWeb/GrupoSindicatoServlet",
                data: 'btnCrear=' + btnCrear + '&codigoSindicato=' + codigoSindicato + '&nombreSindicato=' + nombreSindicato,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La organización sindical fue guardada con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>La organización sindical ya existe, intente con otro nuevo .</h3>'
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
    var elmForm = $("#sindicatoVer");
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
            $('#verSindicato').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar el tipo de caso? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#verSindicato').modal('hide');
}
function modificar() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var codigoSindicato = $('#sindicatoCodigo').val();
    var nombreSindicato = $('#sindicatoNom').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/GrupoSindicatoServlet",
        data: 'btnModificar=' + btnModificar + '&codigoSindicato=' + codigoSindicato + '&nombreSindicato=' + nombreSindicato,
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
    var btnEliminar = $('#sindicatoCodigo').val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/SoftCoisoWeb/GrupoSindicatoServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>La organización sindical fue eliminada con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>La organización sindical no se puede eliminar, porque esta asociado a una persona.</h5>'
                        + '<h5>Intente cambiando la organización sindical de las personas que tengan asociado el grupo, para su eliminación.</h5>'
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



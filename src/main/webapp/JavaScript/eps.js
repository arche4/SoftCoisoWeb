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
            url: "/SoftCoisoWeb-V.1/EpsServlet",
            data: 'btnConsultar=' + btnConsultar,
            success: function (data) {
                $(".loader").fadeOut("slow");
                var respuesta = $.trim(data);
                if (respuesta !== "" && respuesta !== null) {
                    respuesta = respuesta.split("#");
                    $("#codigoMod").val(respuesta[0]);
                    $("#nombreMod").val(respuesta[1]);
                    $('#modificarEps').modal('show');
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
    var elmForm = $("#eps");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            $('#crearEps').modal('hide');
            var btnCrear = 'ok';
            var codigo = $('#codigo').val();
            var nombre = $('#nombre').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/SoftCoisoWeb-V.1/EpsServlet",
                data: 'btnCrear=' + btnCrear + '&codigo=' + codigo + '&nombre=' + nombre,
                success: function (data) {
                    $(".loader").fadeOut("slow");
                    if (data === "0") {
                        var cadena = ' <div class="form-row">'
                                + '<h5>La Eps   fue guardado con ex\u00EDto.</h3>'
                                + '</div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else if (data === "1") {
                        var cadena = '<div class="form-row">'
                                + '<h5>La Eps  ya existe, intente con otro nuevo .</h3>'
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
    var elmForm = $("#epsMod");
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
            $('#modificarEps').modal('hide');
        }
    }
}

function validarEliminar() {
    var cadena = ' <div class="form-row">'
            + '<h5> ¿ Esta seguro que quieres eliminar la EPS? </h3>'
            + ' </div>';
    $('#InfoEliminar').html(cadena);
    $('#modalEliminar').modal('show');
    $('#modificarEps').modal('hide');
}
function modificar() {

    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnModificar = 'ok';
    var codigo = $('#codigoMod').val();
    var nombre = $('#nombreMod').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb-V.1/EpsServlet",
        data: 'btnModificar=' + btnModificar + '&codigo=' + codigo + '&nombre=' + nombre,
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
    var btnEliminar = $('#codigoMod').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb-V.1/EpsServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>La EPS fue eliminadA con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>La EPS  no se puede eliminar, porque esta asociado a una Persona.</h5>'
                        + '<h5>Intente cambiando el  EPS de la persona.</h5>'
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



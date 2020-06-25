$(document).ready(function () {

    $("body").on("click", "#btnEliminarPersona", function () {
        var cedula = $(this).val();
        var cadena = ' <div class="form-row">'
                + '<h5> ¿ Esta seguro que quieres eliminar la persona ? tenga en cuenta que validaremos si se puede eliminar</h3>'
                + ' </div>';
        $("#cedulaEliminar").val(cedula);
        $('#modvalidar').html(cadena);
        $('#valiEliminarPersona').modal('show');
    });

    $('#table_id').dataTable();
    var btnFinish = $('<button></button>').text('Finalizar').addClass('btn btn-theme').on('click', function () {
        if (!$(this).hasClass('disabled')) {
            var elmForm = $("#myForm");
            if (elmForm) {
                elmForm.validator('validate');
                var elmErr = elmForm.find('.has-error');
                if (elmErr && elmErr.length > 0) {
                    var cadena = '<div class="form-row">'
                            + '<h5>Todavia faltan datos por llenar .</h3>'
                            + ' </div>';
                    $('#modMensaje').html(cadena);
                    $('#modalDatosIncorrectos').modal('show');
                    return false;
                } else {
                    event.preventDefault();
                    $('#personaModal').modal('hide');
                    $(".loader").fadeIn("slow");
                    var btnCrearPersona = 'ok';
                    var cedula = $('#cedula').val();
                    var nombrePersona = $('#nombrePersona').val();
                    var apellidos = $('#apellidos').val();
                    var genero = $('#genero').val();
                    var edad = $('#edad').val();
                    var cumpleanos = $('#cumpleanos').val();
                    var municipio = $('#municipio').val();
                    var barrio = $('#barrio').val();
                    var direccion = $('#direccion').val();
                    var telefono = $('#telefono').val();
                    var correo = $('#correo').val();
                    var eps = $('#eps').val();
                    var arl = $('#arl').val();
                    var afp = $('#afp').val();
                    var contrato = $('#contrato').val();
                    var sindicato = $('#sindicato').val();
                    var empresa = $('#empresa').val();
                    var empresaUsuaria = $('#empresaUsuaria').val();
                    var sectorEconomico = $('#sectorEconomico').val();
                    var cargo = $('#cargo').val();
                    var anosExperiencia = $('#anosExperiencia').val();
                    var FechaClinica = $('#FechaClinica').val();
                    var recomendado = $('#recomendado').val();
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "/SoftCoisoWeb/PersonaServlet",
                        data: 'btnCrearPersona=' + btnCrearPersona + '&cedula=' + cedula + '&nombrePersona=' + nombrePersona + '&apellidos=' + apellidos + '&genero=' + genero +
                                '&edad=' + edad + '&cumpleanos=' + cumpleanos + '&municipio=' + municipio + '&barrio=' + barrio + '&direccion=' + direccion
                                + '&telefono=' + telefono + '&correo=' + correo + '&eps=' + eps + '&arl=' + arl + '&afp=' + afp + '&contrato=' + contrato
                                + '&sindicato=' + sindicato + '&empresa=' + empresa + '&empresaUsuaria=' + empresaUsuaria + '&sectorEconomico=' + sectorEconomico
                                + '&cargo=' + cargo + '&anosExperiencia=' + anosExperiencia + '&FechaClinica=' + FechaClinica + '&recomendado=' + recomendado,
                        timeout: 30000,
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
                                return false;
                            }
                        }});
                }
            }
        }
    });
    var btnFinalizar = $('<button></button>').text('Finalizar').addClass('btn btn-theme').on('click', function () {
        if (!$(this).hasClass('disabled')) {
            var elmForm = $("#formPerson");
            if (elmForm) {
                elmForm.validator('validate');
                var elmErr = elmForm.find('.has-error');
                if (elmErr && elmErr.length > 0) {
                    var cadena = '<div class="form-row">'
                            + '<h5>Todavia faltan datos por llenar .</h3>'
                            + ' </div>';
                    $('#modInferror').html(cadena);
                    $('#modalInfError').modal('show');
                    return false;
                } else {
                    event.preventDefault();
                    $('#verPersona').modal('hide');
                    $(".loader").fadeIn("slow");
                    var btnModificar = 'ok';
                    var cedulaPersona = $('#cedulaPersona').val();
                    var PersonaNombre = $('#PersonaNombre').val();
                    var personaApellido = $('#personaApellido').val();
                    var personaGenero = $('#personaGenero').val();
                    var personaEdad = $('#personaEdad').val();
                    var personaCumple = $('#personaCumple').val();
                    var personaMunicipio = $('#personaMunicipio').val();
                    var personaBarrio = $('#personaBarrio').val();
                    var personaDir = $('#personaDir').val();
                    var personaTel = $('#personaTel').val();
                    var personaEmail = $('#personaEmail').val();
                    var personEps = $('#personEps').val();
                    var arlPerson = $('#arlPerson').val();
                    var personAfp = $('#personAfp').val();
                    var personContrato = $('#personContrato').val();
                    var personSindicato = $('#personSindicato').val();
                    var personaEmp = $('#personaEmp').val();
                    var personEmpresaUsuaria = $('#personEmpresaUsuaria').val();
                    var PersonSectorEconomico = $('#PersonSectorEconomico').val();
                    var PersonCargo = $('#PersonCargo').val();
                    var personExperiencia = $('#personExperiencia').val();
                    var personFechaClinica = $('#personFechaClinica').val();
                    var personRecomendado = $('#personRecomendado').val();
                    var casoAsociado = $('#casoAsociado').val();
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "/SoftCoisoWeb/PersonaServlet",
                        data: 'btnModificar=' + btnModificar + '&cedulaPersona=' + cedulaPersona + '&PersonaNombre=' + PersonaNombre + '&personaApellido=' + personaApellido + '&personaGenero=' + personaGenero +
                                '&personaEdad=' + personaEdad + '&personaCumple=' + personaCumple + '&personaMunicipio=' + personaMunicipio + '&personaBarrio=' + personaBarrio + '&personaDir=' + personaDir
                                + '&personaTel=' + personaTel + '&personaEmail=' + personaEmail + '&personEps=' + personEps + '&arlPerson=' + arlPerson + '&personAfp=' + personAfp + '&personContrato=' + personContrato
                                + '&personSindicato=' + personSindicato + '&personaEmp=' + personaEmp + '&personEmpresaUsuaria=' + personEmpresaUsuaria + '&PersonSectorEconomico=' + PersonSectorEconomico
                                + '&PersonCargo=' + PersonCargo + '&personExperiencia=' + personExperiencia + '&personFechaClinica=' + personFechaClinica + '&personRecomendado=' + personRecomendado
                                + '&casoAsociado=' + casoAsociado,
                        success: function (data) {
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
                                $('#modMensaje').html(cadena);
                                $('#modalDatosIncorrectos').modal('show');
                            }
                        }});
                }
            }
        }
    });
    var btnCancel = $('<button></button>').text('Cancelar')
            .addClass('btn btn-danger')
            .on('click', function () {
                $('#smartwizard').smartWizard("reset");
                $('#myForm').find("input, textarea").val("");
            });
    var btnCancelar = $('<button></button>').text('Cancelar')
            .addClass('btn btn-danger')
            .on('click', function () {
                $('#wizzardMostrar').smartWizard("reset");
                $('#formPerson').find("input, textarea").val("");
            });

    $('#smartwizard').smartWizard({

        selected: 0,
        theme: 'dots',
        transitionEffect: 'fade',
        toolbarSettings: {toolbarPosition: 'bottom',
            toolbarExtraButtons: [btnFinish, btnCancel]
        },
        anchorSettings: {
            markDoneStep: true,
            markAllPreviousStepsAsDone: true,
            removeDoneStepOnNavigateBack: true,
            enableAnchorOnDoneStep: true
        }
    });
    $('#wizzardMostrar').smartWizard({
        selected: 0,
        theme: 'dots',
        transitionEffect: 'fade',
        toolbarSettings: {toolbarPosition: 'bottom',
            toolbarExtraButtons: [btnFinalizar, btnCancelar]
        },
        anchorSettings: {
            markDoneStep: true,
            markAllPreviousStepsAsDone: true,
            removeDoneStepOnNavigateBack: true,
            enableAnchorOnDoneStep: true
        }
    });
    $("#smartwizard").on("leaveStep", function (e, anchorObject, stepNumber, stepDirection) {
        var elmForm = $("#form-step-" + stepNumber);
        if (stepDirection === 'forward' && elmForm) {
            elmForm.validator('validate');
            var elmErr = elmForm.children('.has-error');
            if (elmErr && elmErr.length > 0) {
                return false;
            }
        }
        return true;
    });

    $("#wizzardMostrar").on("leaveStep", function (e, anchorObject, stepNumber, stepDirection) {
        var elmForm = $("#formstep-" + stepNumber);
        if (stepDirection === 'forward' && elmForm) {
            elmForm.validator('validate');
            var elmErr = elmForm.children('.has-error');
            if (elmErr && elmErr.length > 0) {
                return false;
            }
        }
        return true;
    });

    $("#smartwizard").on("showStep", function (e, anchorObject, stepNumber, stepDirection) {
        if (stepNumber === 3) {
            $('.btn-finish').removeClass('disabled');
        } else {
            $('.btn-finish').addClass('disabled');
        }
    });
    $("#wizzardMostrar").on("showStep", function (e, anchorObject, stepNumber, stepDirection) {
        if (stepNumber === 3) {
            $('.btn-finish').removeClass('disabled');
        } else {
            $('.btn-finish').addClass('disabled');
        }
    });

    $("body").on("click", "#casoCrear", function () {
        var casoCrear = $(this).val();
        $("#cedulaPersona").val(casoCrear);
        $('#crearCaso').modal('show');
    });

});
$(window).load(function () {
    $(".loader").fadeOut("slow");
});
$("body").on("click", "#selectConsulta", function () {
    var selectConsulta = $(this).val();
    $(".loader").fadeIn("slow");
    $.ajax({
        async: false,
        type: "GET",
        url: "/SoftCoisoWeb/PersonaServlet",
        data: 'selectConsulta=' + selectConsulta,
        success: function (data) {
            $(".loader").fadeOut("slow");
            var respuesta = $.trim(data);
            if (respuesta !== "" && respuesta !== null) {
                respuesta = respuesta.split(",");
                $("#cedulaPersona").val(respuesta[0]);
                $("#PersonaNombre").val(respuesta[1]);
                $("#personaApellido").val(respuesta[2]);
                $("#personaGenero").val(respuesta[3]);
                $("#personaEdad").val(respuesta[4]);
                $("#personaCumple").val(respuesta[5]);
                $("#personaMunicipio").val(respuesta[6]);
                $("#personaBarrio").val(respuesta[7]);
                $("#personaDir").val(respuesta[8]);
                $("#personaTel").val(respuesta[9]);
                $("#personaEmail").val(respuesta[10]);
                $("#personEps").val(respuesta[11]);
                $("#arlPerson").val(respuesta[12]);
                $("#personAfp").val(respuesta[13]);
                $("#personContrato").val(respuesta[14]);
                $("#personSindicato").val(respuesta[15]);
                $("#personaEmp").val(respuesta[16]);
                $("#personEmpresaUsuaria").val(respuesta[17]);
                $("#PersonSectorEconomico").val(respuesta[18]);
                $("#PersonCargo").val(respuesta[19]);
                $("#personExperiencia").val(respuesta[20]);
                $("#personFechaClinica").val(respuesta[21]);
                $("#personRecomendado").val(respuesta[22]);
                $("#casoAsociado").val(respuesta[23]);
                $('#verPersona').modal('show');

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
function cerrarModal() {
    $('#modalDatosIncorrectos').modal('hide');
}
function myFunctionReload() {
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/PersonaServlet",
        success: function (data) {
            location.reload();
        }
    });
}

function guardarCaso() {
    var elmForm = $("#caso");
    if (elmForm) {
        elmForm.validator('validate');
        var elmErr = elmForm.find('.has-error');
        if (elmErr && elmErr.length > 0) {
            return false;
        } else {
            $(".loader").fadeIn("slow");
            event.preventDefault();
            $('#crearCaso').modal('hide');
            var btnCrearCaso = 'ok';
            var cedulaPersona = $('#cedulaPersona').val();
            var cedulaUsuario = $('#cedulaUsuario').val();
            var Tipo = $('#Tipo').val();
            var fechaAfectacion = $('#fechaAfectacion').val();
            var parteAfectada = $('#parteAfectada').val();
            var tiempoInca = $('#tiempoInca').val();
            var descripcionCaso = $('#descripcionCaso').val();
            $.ajax({
                async: false,
                type: "POST",
                url: "/SoftCoisoWeb/CasoServlet",
                data: 'btnCrearCaso=' + btnCrearCaso + '&cedulaPersona=' + cedulaPersona + '&cedulaUsuario=' + cedulaUsuario + '&Tipo=' + Tipo +
                        '&fechaAfectacion=' + fechaAfectacion + '&parteAfectada=' + parteAfectada + '&tiempoInca=' + tiempoInca +
                        '&descripcionCaso=' + descripcionCaso,
                success: function (data) {
                    event.preventDefault();
                    if (data === "Exitoso") {
                        $(".loader").fadeOut("slow");
                        var cadena = ' <div class="form-row">'
                                + '<h5>Sus cambios fueron guardados con ex\u00EDto.</h3>'
                                + ' </div>';
                        $('#modInfexito').html(cadena);
                        $('#modalInfexito').modal('show');
                    } else {
                        $(".loader").fadeOut("slow");
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

function eliminarPersona() {
    $('#valiEliminarPersona').modal('hide');
    $(".loader").fadeIn("slow");
    var btnEliminar = $('#cedulaEliminar').val();
    $.ajax({
        async: false,
        type: "POST",
        url: "/SoftCoisoWeb/PersonaServlet",
        data: 'btnEliminar=' + btnEliminar,
        success: function (data) {
            event.preventDefault();
            $(".loader").fadeOut("slow");
            if (data === "0") {
                var cadena = ' <div class="form-row">'
                        + '<h5>La Persona fue eliminada con ex\u00EDto.</h3>'
                        + '</div>';
                $('#modInfexito').html(cadena);
                $('#modalInfexito').modal('show');
            } else if (data === "1") {
                var cadena = '<div class="form-row">'
                        + '<h5>La Persona no se puede eliminar, porque tiene casos asociados.</h5>'
                        + '<h5>Elimine los casos asociados, para eliminar la persona.</h5>'
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
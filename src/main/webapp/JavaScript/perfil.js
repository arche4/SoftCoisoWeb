function myFunctionReload() {
    location.reload();
}
function modificarUsuario() {
    $(".loader").fadeIn("slow");
    $('#modalValidar').modal('hide');
    var btnEditarPerfil = 'ok';
    var cedula = $('#usuarioCedula').val();
    var nombre = $('#usuarioNombre').val();
    var apellido = $('#usuarioApellido').val();
    var usuarioEmail = $('#usuarioEmail').val();
    var nuevaContraseña = $('#nuevaContraseña').val();
    var validaContraseña = $('#validaContraseña').val();
    var validado = 'ok';
    if (nuevaContraseña !== '') {
        if (nuevaContraseña === validaContraseña) {
            validado = 'ok';
        } else {
            validado = 'No';
        }
    }
    if (validado === 'ok') {
        $.ajax({
            async: false,
            type: "POST",
            url: "/SoftCoisoWeb-V.1/perfilServlet",
            data: 'btnEditarPerfil=' + btnEditarPerfil + '&cedula=' + cedula + '&nombre=' + nombre + '&apellido=' + apellido +
                    '&usuarioEmail=' + usuarioEmail  + '&nuevaContraseña=' + nuevaContraseña,
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
    } else {
        var cadena = '<div class="form-row">'
                + '<h5>Las contraseñas deben Coincidir.</h3>'
                + ' </div>';
        $('#modInferror').html(cadena);
        $('#modalInfError').modal('show');
    }


}
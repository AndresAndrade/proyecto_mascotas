//Inicializador de popover
/*$(document).ready(function(){
    $('[data-bs-toggle="popover"]').popover();
});*/

$(document).ready(function () {
    $("#form-login").submit(function (event) {
        event.preventDefault();
        autenticarUsuario();
    });
    $("#form-register").submit(function (event) {
        event.preventDefault();
        registrarUsuario();
    });
});

function autenticarUsuario() {
    let username = $("#username").val();
    let password = $("#password").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioLogin",
        data: $.param({
            username: username,
            password: password
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                $("#login-error").addClass("d-none");
                let username = parsedResult['username'];
                document.location.href = "home_user.html?username=" + username;
            } else {
                $("#login-error").removeClass("d-none");
            }
        }
    });
}

function registrarUsuario() {
    let username = $("#input-username").val();
    let primerNombre = $("#input-primer-nombre").val();
    let segundoNombre = $("#input-segundo-nombre").val();
    let primerApellido = $("#input-primer-apellido").val();
    let segundoApellido = $("#input-segundo-apellido").val();
    let email = $("#input-email").val();
    let telefono = $("#input-telefono").val();
    let password = $("#input-contrasena").val();
    let contrasenaConfirmacion = $("#input-contrasena-repeat").val();

    if (password === contrasenaConfirmacion) {
        $.ajax({
            type: "GET",
            dataType: "html",
            url: "./ServletUsuarioRegister",
            data: $.param({
                username: username,
                primerNombre: primerNombre,
                segundoNombre: segundoNombre,
                primerApellido: primerApellido,
                segundoApellido: segundoApellido,
                email: email,
                telefono: telefono,
                password: password,
                contrasenaConfirmacion: contrasenaConfirmacion,
                ciudad: '1',
                fundacion: '1'
            }),
            success: function (result) {
                let parsedResult = JSON.parse(result);

                if (parsedResult !== false) {
                    $("#register-error").addClass("d-none");
                    let username = parsedResult['username'];
                    document.location.href = "home_user.html?username=" + username;
                } else {
                    $("#register-error").removeClass("d-none");
                    $("#register-error").html("Error en el registro del usuario");
                }
            }
        });
    } else {
        $("#register-error").removeClass("d-none");
        $("#register-error").html("Las contraseñas no coinciden");
    }
}

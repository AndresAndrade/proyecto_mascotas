//Inicializador de popover
/*$(document).ready(function(){
    $('[data-bs-toggle="popover"]').popover();
});*/

$(document).ready(function () {
    $("#form-login").submit(function (event) {
        event.preventDefault();
        autenticarUsuario();
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



$(document).ready(function () {
    $("#form-register").submit(function (event) {
        event.preventDefault();
        registrarUsuario();
    });

    getDepartamentos();
    $("#select-departamento").change(function() {
       let text = $('#select-departamento option:selected').text();
        getCiudades(text);
    });
    getFund();

    getUsuariosHome()
});

/*Registrar usuario*/
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

/*Select de departamentos*/
function getDepartamentos() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDepartamentoListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarDepartamentos(parsedResult);
            } else {
                console.log("Error recuperando los datos de los departamentos.");
            }
        }
    });
}

function mostrarDepartamentos(departamentos) {
    let contenido = "";
    $.each(departamentos, function (index, departamento) {
        departamento = JSON.parse(departamento);
        contenido += '<option value="'+ departamento.idDepartamento +'">' + departamento.departamento + '</option>';

    });
    $("#select-departamento").html(contenido);

}

/*Select de ciudades*/
function getCiudades(departamento) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletCiudadListar",
        data: $.param({
            departamento: departamento
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarCiudades(parsedResult);
            } else {
                console.log("Error recuperando los datos de las ciudades.");
            }
        }
    });
}

function mostrarCiudades(ciudades) {
    let contenido = "";
    $.each(ciudades, function (index, ciudad) {
        ciudad = JSON.parse(ciudad);
        contenido += '<option value="'+ ciudad.idCiudad +'">' + ciudad.ciudad + '</option>';
    });
    $("#select-ciudad").html(contenido);
}

/*Select de fundaciones*/
function getFund() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionSeleccionar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarFund(parsedResult);
            } else {
                console.log("Error recuperando los datos de las fundaciones.");
            }
        }
    });
}

function mostrarFund(fundaciones) {
    let contenido = "<option selected>--Seleccione la fundación--</option>";
    $.each(fundaciones, function (index, fundacion) {
        fundacion = JSON.parse(fundacion);
        contenido += '<option value="'+ fundacion.idFundacion +'">' + fundacion.nombreFundacion + '</option>';
    });
    $("#select-fundacion").html(contenido);
}

/*Listar usuarios*/
function getUsuariosHome() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarUsuarios(parsedResult);
            } else {
                console.log("Error recuperando los datos de llos usuarios.");
            }
        }
    });
}

function mostrarUsuarios(usuarios) {
    let contenido = "";
    $.each(usuarios, function (index, usuario) {
        usuario = JSON.parse(usuario);
        contenido += '<tr><th scope="row">' + usuario.idUsuario + '</th>' +
            '<td>' + usuario.primerNombre + '</td>' +
            '<td>' + usuario.segundoNombre + '</td>' +
            '<td>' + usuario.primerApellido + '</td>' +
            '<td>' + usuario.segundoApellido + '</td>' +
            '<td>' + usuario.email + '</td>' +
            '<td>' + usuario.telefono + '</td>' +
            '<td>' + usuario.fundacion + '</td>' +
            '<td><button class="btn btn-success">Editar</button></td>' +
            '<td><button class="btn btn-warning">Eliminar</button></td></tr>';
    });
    $("#usuarios-tbody").html(contenido);
}

var username = new URL(location.href).searchParams.get("username");
const admin = "admin";
console.log(username);

$(document).ready(function () {

    if (username === admin) {
        $("#tarjeta-usuarios").removeClass("d-none");
        $("#tarjeta-fundaciones").removeClass("d-none");
    } else {
        if (!$("#tarjeta-fundaciones").hasClass("d-none")) {
            $("#tarjeta-usuarios").addClass("d-none");
            $("#tarjeta-fundaciones").addClass("d-none");
        }
    }

    getUsuario().then(function () {
        $("#welcome-user").html(user.primerNombre + " " + user.primerApellido);
    });

    $(".btn-success").click(function () {
        location.reload();
    });

    $(".btn-close").click(function () {
        location.reload();
    });

    $("#form-register").submit(function (event) {
        event.preventDefault();
        registrarUsuario();
    });

    $("#form-editar-usuario").submit(function (event) {
        event.preventDefault();
        editarUsuario();
    });

    listarUsuarios();

    getDepartamentos();

    $("#select-departamento").change(function() {
       let text = $('#select-departamento option:selected').text();
        getCiudades(text);
    });

    getFund();

});

//proceso asincronico, obligatorio usar los prefijos async y await
async function getUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioGet",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                user = parsedResult;
            } else {
                console.log("Error, recuperando los datos del usuario.");
            }
        }
    });
}

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
    let idCiudad = $("#select-ciudad").change(function() {
        $("#select-ciudad option:selected").val();
    });
    let idFundacion = $("#select-fundacion").change(function() {
        $("#select-fundacion option:selected").val();
    });

    console.log("idCiudad = " + idCiudad[0].value);
    console.log("idFundacion = " + idFundacion[0].value);

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
                ciudad: idCiudad[0].value,
                fundacion: idFundacion[0].value
            }),
            success: function (result) {
                let parsedResult = JSON.parse(result);

                if (parsedResult !== false) {
                    $("#btnRegistrar-usuario").addClass("d-none");
                    $("#register-success").removeClass("d-none");
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
    let contenido = "<option selected>--Seleccione el departamento--</option>";
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
    let contenido = "<option selected>--Seleccione la ciudad--</option>";
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
function listarUsuarios() {
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
            '<td><button class="btn btn-success" type="submit" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-editar-usuario" ' +
            'onclick="llenarFormularioUsuario(' + usuario.idUsuario + ')"><i class="fas fa-pencil"></button></td>' +
            '<td><button type="submit" class="btn btn-warning" id="btnEliminar-usuario" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-eliminar-usuario" onclick="llenarUsuarioModal('+ usuario.idUsuario +')">' +
            '<i class="fas fa-trash"></button></td></tr>';
    });
    $("#usuarios-tbody").html(contenido);
}

//Llenar el formulario de usuario para la edición
function llenarFormularioUsuario(idUsuario) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioLlenarForm",
        data: $.param({
            idUsuario: idUsuario
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#input-editar-idUsuario").val(parsedResult.idUsuario);
                $("#input-editar-username").val(parsedResult.username);
                $("#input-editar-primer-nombre").val(parsedResult.primerNombre);
                $("#input-editar-segundo-nombre").val(parsedResult.segundoNombre);
                $("#input-editar-primer-apellido").val(parsedResult.primerApellido);
                $("#input-editar-segundo-apellido").val(parsedResult.segundoApellido);
                $("#input-editar-email-usuario").val(parsedResult.email);
                $("#input-editar-telefono-usuario").val(parsedResult.telefono);
                $("#input-editar-contrasena-repeat").val(parsedResult.password);
                $("#input-editar-contrasena").val(parsedResult.password);
                $("#select-editar-ciudad-usuario").val(parsedResult.ciudad);
                $("#select-editar-departamento-usuario").val(parsedResult.departamento);
                $("#select-editar-fundacion-usuario").val(parsedResult.fundacion);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

//Editar usuario
function editarUsuario() {

    let idUsuario = $("#input-editar-idUsuario").val();
    let primerNombre = $("#input-editar-primer-nombre").val();
    let segundoNombre = $("#input-editar-segundo-nombre").val();
    let primerApellido = $("#input-editar-primer-apellido").val();
    let segundoApellido = $("#input-editar-segundo-apellido").val();
    let email = $("#input-editar-email-usuario").val();
    let telefono = $("#input-editar-telefono-usuario").val();
    let password = $("#input-editar-contrasena").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            idUsuario: idUsuario,
            primerNombre: primerNombre,
            segundoNombre: segundoNombre,
            primerApellido: primerApellido,
            segundoApellido: segundoApellido,
            email: email,
            telefono: telefono,
            password: password
        }),
        success: function (result) {

            if (result !== false) {
                $("#editar-error-usuario").addClass("d-none");
                $("#btnEditar-usuario").addClass("d-none");
                $("#editar-success-usuario").removeClass("d-none");
            } else {
                $("#editar-error-usuario").removeClass("d-none");
                $("#editar-error-usuario").html("Error en el registro del usuario");
            }
        }
    });
}

/*Eliminar usuario*/
function eliminarUsuario() {

    let idUsuario = $("#label-idUsuario").html();
    console.log(idUsuario);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            idUsuario: idUsuario
        }),
        success: function (result) {

            if (result !== false) {
                console.log("Registro eliminado")
                $("#eliminar-error-usuario").addClass("d-none");
                $("#btnEliminar-usuario-aceptar").addClass("d-none");
                $("#btnEliminar-usuario-close").addClass("d-none");
                $("#eliminar-success-usuario").removeClass("d-none");
            } else {
                console.log("Error eliminando el registro del usuario");
                $("#eliminar-error-usuario").removeClass("d-none");
                $("#eliminar-error-usuario").html("Error al eliminar al usuario");
            }
        }
    });
}

/*llenar el objeto modal para elminar usuario*/
function llenarUsuarioModal(idUsuario) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModal",
        data: $.param({
            idUsuario: idUsuario
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#label-idUsuario").html(parsedResult.idUsuario);
                $("#label-nombre-usuario").html(parsedResult.nombreCompleto);
                $("#label-username").html(parsedResult.username);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

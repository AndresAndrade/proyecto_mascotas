$(document).ready(function () {
    $("#form-register-adoptante").submit(function (event) {
        event.preventDefault();
        registrarAdoptante();
    });

    $("#form-editar-adoptante").submit(function (event) {
        event.preventDefault();
        editarAdoptante();
    });

    getDptoAdoptante();
    $("#select-departamento-adoptante").change(function() {
        let text = $('#select-departamento-adoptante option:selected').text();
        getCiudadesAdoptante(text);
    });

    listarAdoptantes()
});

/*Registrar usuario*/
function registrarAdoptante() {
    let cedula = $("#input-cedula").val();
    let primerNombre = $("#input-primer-nombre-adoptante").val();
    let segundoNombre = $("#input-segundo-nombre-adoptante").val();
    let primerApellido = $("#input-primer-apellido-adoptante").val();
    let segundoApellido = $("#input-segundo-apellido-adoptante").val();
    let email = $("#input-email-adoptante").val();
    let telefono = $("#input-telefono-adoptante").val();
    let observacion = $("#input-observacion-adoptante").val();
    let idCiudad = $("#select-ciudad-adoptante").change(function() {
        $("#select-ciudad-adoptante option:selected").val();
    });

    console.log("idCiudad = " + idCiudad[0].value);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteRegister",
        data: $.param({
            cedula: cedula,
            primerNombre: primerNombre,
            segundoNombre: segundoNombre,
            primerApellido: primerApellido,
            segundoApellido: segundoApellido,
            email: email,
            telefono: telefono,
            observacion: observacion,
            idCiudad: idCiudad[0].value
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                $("#register-error-adoptante").addClass("d-none");
                $("#btnRegistrar-adoptante").addClass("d-none");
                $("#register-success-adoptante").removeClass("d-none");
            } else {
                $("#register-error-adoptante").removeClass("d-none");
                $("#register-error-adoptante").html("Error en el registro del adoptante");
            }
        }
    });
}

/*Select de departamentos*/
function getDptoAdoptante() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDepartamentoListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarDptoAdoptante(parsedResult);
            } else {
                console.log("Error recuperando los datos de los departamentos.");
            }
        }
    });
}

function mostrarDptoAdoptante(departamentos) {
    let contenido = "<option selected>--Seleccione el departamento--</option>";
    $.each(departamentos, function (index, departamento) {
        departamento = JSON.parse(departamento);
        contenido += '<option value="'+ departamento.idDepartamento +'">' + departamento.departamento + '</option>';

    });
    $("#select-departamento-adoptante").html(contenido);

}

/*Select de ciudades*/
function getCiudadesAdoptante(departamento) {
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
                mostrarCiudadesAdoptante(parsedResult);
            } else {
                console.log("Error recuperando los datos de las ciudades.");
            }
        }
    });
}

function mostrarCiudadesAdoptante(ciudades) {
    let contenido = "<option selected>--Seleccione la ciudad--</option>";
    $.each(ciudades, function (index, ciudad) {
        ciudad = JSON.parse(ciudad);
        contenido += '<option value="'+ ciudad.idCiudad +'">' + ciudad.ciudad + '</option>';
    });
    $("#select-ciudad-adoptante").html(contenido);
}

/*Listar adoptantes*/
function listarAdoptantes() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarAdoptantes(parsedResult);
            } else {
                console.log("Error recuperando los datos de los adoptantes.");
            }
        }
    });
}

function mostrarAdoptantes(adoptantes) {
    let contenido = "";
    $.each(adoptantes, function (index, adoptante) {
        adoptante = JSON.parse(adoptante);
        contenido += '<tr><th scope="row">' + adoptante.cedula + '</th>' +
            '<td>' + adoptante.primerNombre + '</td>' +
            '<td>' + adoptante.segundoNombre + '</td>' +
            '<td>' + adoptante.primerApellido + '</td>' +
            '<td>' + adoptante.segundoApellido + '</td>' +
            '<td>' + adoptante.email + '</td>' +
            '<td>' + adoptante.telefono + '</td>' +
            '<td>' + adoptante.ciudad + '</td>' +
            '<td>' + adoptante.observacion + '</td>' +
            '<td><button class="btn btn-success" type="submit" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-editar-adoptante" ' +
            'onclick="llenarFormularioAdoptante(' + adoptante.cedula + ')"><i class="fas fa-pencil"></i></button></td>' +
            '<td><button type="submit" class="btn btn-warning" id="btnEliminar-adoptante" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-eliminar-adoptante" onclick="llenarAdoptanteModal('+ adoptante.cedula +')">' +
                '<i class="fas fa-trash"></i></button></td></tr>';
    });
    $("#adoptantes-tbody").html(contenido);
}

//Llenar el formulario de adoptante para la edición
function llenarFormularioAdoptante(cedula) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteLlenarForm",
        data: $.param({
            cedula: cedula
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#input-editar-cedula").val(parsedResult.cedula);
                $("#input-editar-primer-nombre-adoptante").val(parsedResult.primerNombre);
                $("#input-editar-segundo-nombre-adoptante").val(parsedResult.segundoNombre);
                $("#input-editar-primer-apellido-adoptante").val(parsedResult.primerApellido);
                $("#input-editar-segundo-apellido-adoptante").val(parsedResult.segundoApellido);
                $("#input-editar-email-adoptante").val(parsedResult.email);
                $("#input-editar-telefono-adoptante").val(parsedResult.telefono);
                $("#select-editar-ciudad-adoptante").val(parsedResult.ciudad);
                $("#select-editar-departamento-adoptante").val(parsedResult.departamento);
                $("#input-editar-observacion-adoptante").val(parsedResult.observacion);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

//Editar adoptante
function editarAdoptante() {

    let cedula = $("#input-editar-cedula").val();
    let primerNombre = $("#input-editar-primer-nombre-adoptante").val();
    let segundoNombre = $("#input-editar-segundo-nombre-adoptante").val();
    let primerApellido = $("#input-editar-primer-apellido-adoptante").val();
    let segundoApellido = $("#input-editar-segundo-apellido-adoptante").val();
    let email = $("#input-editar-email-adoptante").val();
    let telefono = $("#input-editar-telefono-adoptante").val();
    let observacion = $("#input-editar-observacion-adoptante").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteModificar",
        data: $.param({
            cedula: cedula,
            primerNombre: primerNombre,
            segundoNombre: segundoNombre,
            primerApellido: primerApellido,
            segundoApellido: segundoApellido,
            email: email,
            telefono: telefono,
            observacion: observacion
        }),
        success: function (result) {

            if (result !== false) {
                $("#editar-error-adoptante").addClass("d-none");
                $("#btnEditar-adoptante").addClass("d-none");
                $("#editar-success-adoptante").removeClass("d-none");
            } else {
                $("#editar-error-adoptante").removeClass("d-none");
                $("#editar-error-adoptante").html("Error en el registro del adoptante");
            }
        }
    });
}

/*Eliminar adoptante*/
function eliminarAdoptante() {

    let cedula = $("#label-cedula").html();
    console.log(cedula);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteEliminar",
        data: $.param({
            cedula: cedula
        }),
        success: function (result) {

            if (result !== false) {
                console.log("Registro eliminado")
                $("#eliminar-error-adoptante").addClass("d-none");
                $("#eliminar-success-adoptante").removeClass("d-none");
                $("#btnEliminar-adoptante-aceptar").addClass("d-none");
                $("#btnEliminar-adoptante-close").addClass("d-none");
            } else {
                console.log("Error eliminando el registro del adoptante");
                $("#eliminar-error-adoptante").removeClass("d-none");
                $("#eliminar-error-adoptante").html("Error al eliminar al adoptante");
            }
        }
    });
}

/*llenar el objeto modal para elminar adoptante*/
function llenarAdoptanteModal(cedula) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletAdoptanteModal",
        data: $.param({
            cedula: cedula
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#label-cedula").html(parsedResult.cedula);
                $("#label-nombre-completo").html(parsedResult.nombreCompleto);

            } else {
                console.log("Error recuperando los datos del adoptante");
            }
        }
    });
}

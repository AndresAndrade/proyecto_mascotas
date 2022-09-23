$(document).ready(function () {
    $("#form-register-adoptante").submit(function (event) {
        event.preventDefault();
        registrarAdoptante();
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
                $("#register-success-adoptante").removeClass("d-none");
                $("#register-success-adoptante").html("Registro exitoso");
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
    let contenido = "";
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
    let contenido = "";
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
            '<td><button class="btn btn-success">Editar</button></td>' +
            '<td><button class="btn btn-warning">Eliminar</button></td></tr>';
    });
    $("#adoptantes-tbody").html(contenido);
}

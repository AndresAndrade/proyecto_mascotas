let path = $(location).attr('pathname');
let pathRegex = /\/(proyecto_mascotas_war_exploded|adoptaunahuella.herokuapp.com)\/directorio_fundaciones.html/;

$(document).ready(function () {
    console.log(path);
    $("#form-register-fundacion").submit(function (event) {
        event.preventDefault();
        registrarFundacion();
    });

    $("#form-editar-fundacion").on("submit", function (event) {
        event.preventDefault();
        editarFundacion();
    });

    getDeptosFundacion();
    $("#select-departamento-fundacion").change(function() {
        let text = $('#select-departamento-fundacion option:selected').text();
        getCiudadesFundacion(text);
    });

    listarFundaciones();

});

/*Registro de fundacion*/
function registrarFundacion() {
    let nombre = $("#input-nombre-fundacion").val();
    let telefono = $("#input-telefono-fundacion").val();
    let email = $("#input-email-fundacion").val();
    let idCiudad = $("#select-ciudad-fundacion").change(function() {
        $("#select-ciudad-fundacion option:selected").val();
    });

    console.log("idCiudad = " + idCiudad[0].value);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionRegister",
        data: $.param({
            nombre: nombre,
            telefono: telefono,
            email: email,
            idCiudad: idCiudad[0].value
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                $("#register-error-fundacion").addClass("d-none");
                $("#btnRegistrar-fundacion").addClass("d-none");
                $("#register-success-fundacion").removeClass("d-none");
            } else {
                $("#register-error-fundacion").removeClass("d-none");
                $("#register-error-fundacion").html("Error en el registro de la mascota");
            }
        }
    });
}

/*Select de departamentos*/
function getDeptosFundacion() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDepartamentoListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarDeptosFundacion(parsedResult);
            } else {
                console.log("Error recuperando los datos de los departamentos.");
            }
        }
    });
}

function mostrarDeptosFundacion(departamentos) {
    let contenido = "<option selected>--Seleccione el departamento--</option>";
    $.each(departamentos, function (index, departamento) {
        departamento = JSON.parse(departamento);
        contenido += '<option value="'+ departamento.idDepartamento +'">' + departamento.departamento + '</option>';

    });
    $("#select-departamento-fundacion").html(contenido);

}

/*Select de ciudades*/
function getCiudadesFundacion(departamento) {
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
                mostrarCiudadesFundacion(parsedResult);
            } else {
                console.log("Error recuperando los datos de las ciudades.");
            }
        }
    });
}

function mostrarCiudadesFundacion(ciudades) {
    let contenido = "<option selected>--Seleccione la ciudad--</option>";
    $.each(ciudades, function (index, ciudad) {
        ciudad = JSON.parse(ciudad);
        contenido += '<option value="'+ ciudad.idCiudad +'">' + ciudad.ciudad + '</option>';
    });
    $("#select-ciudad-fundacion").html(contenido);
}


/*Listar fundaciones*/
function listarFundaciones() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarFundaciones(parsedResult);
            } else {
                console.log("Error recuperando los datos de las funsaciones.");
            }
        }
    });
}

function mostrarFundaciones(fundaciones) {
    let contenido = "";
    $.each(fundaciones, function (index, fundacion) {
        fundacion = JSON.parse(fundacion);
        contenido += '<tr><th scope="row">' + fundacion.idFundacion + '</th>' +
            '<td>' + fundacion.nombreFundacion + '</td>' +
            '<td>' + fundacion.telefono + '</td>' +
            '<td>' + fundacion.email + '</td>' +
            '<td>' + fundacion.ciudadFundacion + '</td>' +
            '<td>' + fundacion.departamentoFundacion + '</td>';

        if (!pathRegex.test(path)) {
            contenido += '<td><button class="btn btn-success" type="submit" data-bs-toggle="modal" ' +
                'data-bs-target="#modal-editar-fundacion" ' +
                'onclick="llenarFormularioFundacion(' + fundacion.idFundacion + ')"><i class="fas fa-pencil"></button></td>' +
                '<td><button type="submit" class="btn btn-warning" id="btnEliminar-fundacion" data-bs-toggle="modal" ' +
                'data-bs-target="#modal-eliminar-fundacion" onclick="llenarFundacionModal('+ fundacion.idFundacion +')">' +
                '<i class="fas fa-trash"></button></td></tr>';
        }
    });
    $("#fundaciones-tbody").html(contenido);
}

//Llenar el formulario de fundaciones para la edición
function llenarFormularioFundacion(idFundacion) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionLlenarForm",
        data: $.param({
            idFundacion: idFundacion
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            console.log(parsedResult);
            if (parsedResult !== false) {

                $("#input-editar-idFundacion").val(parsedResult.idFundacion);
                $("#input-editar-nombre-fundacion").val(parsedResult.nombreFundacion);
                $("#input-editar-telefono-fundacion").val(parsedResult.telefono);
                $("#input-editar-email-fundacion").val(parsedResult.email);
                $("#select-editar-ciudad-fundacion").val(parsedResult.ciudadFundacion);
                $("#select-editar-departamento-fundacion").val(parsedResult.departamentoFundacion);

            } else {
                console.log("Error recuperando los datos dde la mascota");
            }
        }
    });
}

//editar fundacion
function editarFundacion() {

    let idFundacion = $("#input-editar-idFundacion").val();
    let nombreFundacion = $("#input-editar-nombre-fundacion").val();
    let telefono = $("#input-editar-telefono-fundacion").val();
    let email = $("#input-editar-email-fundacion").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionModificar",
        data: $.param({
            idFundacion: idFundacion,
            nombreFundacion: nombreFundacion,
            telefono: telefono,
            email: email
        }),
        success: function (result) {

            if (result !== false) {
                $("#editar-error-fundacion").addClass("d-none");
                $("#btnEditar-fundacion").addClass("d-none");
                $("#editar-success-fundacion").removeClass("d-none");
            } else {
                $("#editar-error-fundacion").removeClass("d-none");
                $("#editar-error-fundacion").html("Error en el registro de la fundacion");
            }
        }
    });
}

/*Eliminar fundacion*/
function eliminarFundacion() {

    let idFundacion = $("#label-idFundacion").html();
    console.log(idFundacion);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionEliminar",
        data: $.param({
            idFundacion: idFundacion
        }),
        success: function (result) {

            if (result !== false) {
                console.log("Registro eliminado")
                $("#eliminar-error-fundacion").addClass("d-none");
                $("#eliminar-success-fundacion").removeClass("d-none");
                $("#btnEliminar-fundacion-aceptar").addClass("d-none");
                $("#btnEliminar-fundacion-close").addClass("d-none");
            } else {
                console.log("Error eliminando el registro del adoptante");
                $("#eliminar-error-fundacion").removeClass("d-none");
                $("#eliminar-error-fundacion").html("Error al eliminar la fundación");
            }
        }
    });
}

/*llenar el objeto modal para eliminar fundación*/
function llenarFundacionModal(idFundacion) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionModal",
        data: $.param({
            idFundacion: idFundacion
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#label-idFundacion").html(parsedResult.idFundacion);
                $("#label-nombre-fundacion").html(parsedResult.nombreFundacion);

            } else {
                console.log("Error recuperando los datos de la fundación");
            }
        }
    });
}

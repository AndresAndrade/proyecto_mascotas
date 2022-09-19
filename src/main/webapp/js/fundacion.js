$(document).ready(function () {
    $("#form-register-fundacion").submit(function (event) {
        event.preventDefault();
        registrarFundacion();
    });

    getDeptos();
    $("#select-departamento-fundacion").change(function() {
        let text = $('#select-departamento-fundacion option:selected').text();
        getCities(text);
    });

    listarFundaciones();

    /*$("#select-ciudad-fundacion").change(function() {
        $("#select-ciudad-fundacion option:selected").val();
    });*/

});

/*Registro de fundacion*/
function registrarFundacion() {
    let nombre = $("#input-nombre-fundacion").val();
    let telefono = $("#input-telefono-fundacion").val();
    let email = $("#input-email-fundacion").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionRegister",
        data: $.param({
            nombre: nombre,
            telefono: telefono,
            email: email,
            idCiudad: '1',
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                $("#register-error-fundacion").addClass("d-none");
                $("#register-success-fundacion").removeClass("d-none");
                $("#register-success-fundacion").html("Registro exitoso");
            } else {
                $("#register-error-fundacion").removeClass("d-none");
                $("#register-error-fundacion").html("Error en el registro de la mascota");
            }
        }
    });
}

/*Select de departamentos*/
function getDeptos() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletDepartamentoListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarDeptos(parsedResult);
            } else {
                console.log("Error recuperando los datos de los departamentos.");
            }
        }
    });
}

function mostrarDeptos(departamentos) {
    let contenido = "";
    $.each(departamentos, function (index, departamento) {
        departamento = JSON.parse(departamento);
        contenido += '<option value="'+ departamento.idDepartamento +'">' + departamento.departamento + '</option>';

    });
    $("#select-departamento-fundacion").html(contenido);

}

/*Select de ciudades*/
function getCities(departamento) {
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
                mostrarCities(parsedResult);
            } else {
                console.log("Error recuperando los datos de las ciudades.");
            }
        }
    });
}

function mostrarCities(ciudades) {
    let contenido = "";
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
            '<td>' + fundacion.departamentoFundacion + '</td>' +
            '<td><button class="btn btn-success">Editar</button></td>' +
            '<td><button class="btn btn-warning">Eliminar</button></td></tr>';
    });
    $("#fundaciones-tbody").html(contenido);
}

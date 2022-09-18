$(document).ready(function () {
    getDepartamentos();
    $("#select-departamento").change(function() {
       let text = ($('#select-departamento option:selected').text());
        getCiudades(text);
    });
    getFundaciones();
});

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

function getFundaciones() {
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
                console.log("Error recuperando los datos de las fundaciones.");
            }
        }
    });
}

function mostrarFundaciones(fundaciones) {
    let contenido = "";
    $.each(fundaciones, function (index, fundacion) {
        fundacion = JSON.parse(fundacion);
        contenido += '<option value="'+ fundacion.idFundacion +'">' + fundacion.nombreFundacion + '</option>';
    });

    $("#select-fundacion").html(contenido);
}
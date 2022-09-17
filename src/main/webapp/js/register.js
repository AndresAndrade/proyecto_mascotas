$(document).ready(function () {
    $("#select-departamento").change(function() {
        alert($('#select-departamento option:selected').text());
    });


    getCiudades($('#select-departamento option:selected').text());
    getDepartamentos();
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
    /*let departamento = $("#select-departamento option:selected").text();*/
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
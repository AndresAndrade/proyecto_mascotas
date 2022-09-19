$(document).ready(function () {
    $("#form-register-mascota").submit(function (event) {
        event.preventDefault();
        registrarMascota();
    });
    getMascotas();
    getEspecies();

    $("#select-especie").change(function() {
        let text = $('#select-especie option:selected').text();
        getRaza(text);
    });

    getFundaciones()

    /*$("#select-raza").change(function() {
        $("#select-raza option:selected").val();
    });*/

});

/*Registro de mascotas*/
function registrarMascota() {
    let nombreMascota = $("#input-nombre-mascota").val();
    let edad = $("#input-edad").val();
    let descripcion = $("#input-descripcion").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaRegister",
        data: $.param({
            nombreMascota: nombreMascota,
            edad: edad,
            descripcion: descripcion,
            idRaza: '1',
            idFundacion: '1'
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                $("#register-error-mascota").addClass("d-none");
                $("#register-success-mascota").removeClass("d-none");
                $("#register-success-mascota").html("Registro exitoso");
            } else {
                $("#register-error-mascota").removeClass("d-none");
                $("#register-error-mascota").html("Error en el registro de la mascota");
            }
        }
    });
}

/*Select de especies*/
function getEspecies() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletEspecieListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarEspecies(parsedResult);
            } else {
                console.log("Error recuperando los datos de las especies.");
            }
        }
    });
}

function mostrarEspecies(especies) {
    let contenido = "";
    $.each(especies, function (index, especie) {
        especie = JSON.parse(especie);
        contenido += '<option value="'+ especie.idEspecie +'">' + especie.especie + '</option>';
    });
    $("#select-especie").html(contenido);
}

/*Select de razas*/
function getRaza(especie) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletRazaListar",
        data: $.param({
            especie: especie
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarRazas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las razas.");
            }
        }
    });
}

function mostrarRazas(razas) {
    let contenido = "";
    $.each(razas, function (index, raza) {
        raza = JSON.parse(raza);
        contenido += '<option value="'+ raza.idRaza +'">' + raza.raza + '</option>';
    });
    $("#select-raza").html(contenido);
}

/*Select de fundaciones*/
function getFundaciones() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionSeleccionar",
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

    $("#select-fund").html(contenido);
}

/*Listar mascotas*/
function getMascotas() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarMascotas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las mascotas.");
            }
        }
    });
}

function mostrarMascotas(mascotas) {
    let contenido = "";
    $.each(mascotas, function (index, mascota) {
        mascota = JSON.parse(mascota);
        contenido += '<tr><th scope="row">' + mascota.idMascota + '</th>' +
            '<td>' + mascota.nombreMascota + '</td>' +
            '<td>' + mascota.edad + '</td>' +
            '<td>' + mascota.especie + '</td>' +
            '<td>' + mascota.raza + '</td>' +
            '<td>' + mascota.fundacion + '</td>' +
            '<td><input type="checkbox" name="estado" id="estado' + mascota.idMascota + '" disabled ';

        if (mascota.adoptado) {
            contenido += 'checked'
        }
        contenido += '><td>' + mascota.descripcion + '</td>' +
            '<td><button class="btn btn-success">Editar</button></td>' +
            '<td><button class="btn btn-warning">Eliminar</button></td></tr>';
    });
    $("#mascotas-tbody").html(contenido);
}

var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    getUsuario().then(function () {
        getMascotas();
        getEspecies();
        $("#select-especie").change(function() {
            let text = ($('#select-especie option:selected').text());
            getRaza(text);

        });
    });
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
var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {
    getUsuario().then(function () {
        //$("#mi-perfil-btn").attr("href", "profile.html?username=" + username);
        getEspecies();
        $("#select-departamento").change(function() {
            let text = ($('#select-especie option:selected').text());
            getRaza(text);
        });
        //$("#ordernar-genero").click(ordenarPelicula);
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
                mostrarDepartamentos(parsedResult);
            } else {
                console.log("Error recuperando los datos de las especies.");
            }
        }
    });
}

function mostrarEspecies(especies) {
    let contenido = "";
    $.each(especies, function (index, especie) {
        especie = JSON.parse(departamento);
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
                mostrarCiudades(parsedResult);
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
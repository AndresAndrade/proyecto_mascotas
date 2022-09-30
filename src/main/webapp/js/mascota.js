$(document).ready(function () {
    $("#form-register-mascota").submit(function (event) {
        event.preventDefault();
        registrarMascota();
    });

    $("#form-editar-mascota").on("submit", function (event) {
        event.preventDefault();
        editarMascota();
    });

    getMascotasIndex();
    
    getMascotas();

    getEspecies();

    $("#select-especie").change(function() {
        let text = $('#select-especie option:selected').text();
        getRaza(text);
    });

    getFundMascotas()

});

/*Registro de mascotas*/
function registrarMascota() {
    let nombreMascota = $("#input-nombre-mascota").val();
    let edad = $("#input-edad").val();
    let descripcion = $("#input-descripcion").val();
    let idRaza = $("#select-raza").change(function() {
         $("#select-raza option:selected").val();
    });
    let idFundacion = $("#select-fundacion-mascota").change(function() {
         $("#select-fundacion-mascota option:selected").val();
    });

    console.log("idRaza = " + idRaza[0].value);
    console.log("idFundacion = " + idFundacion[0].value);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaRegister",
        data: $.param({
            nombreMascota: nombreMascota,
            edad: edad,
            descripcion: descripcion,
            idRaza: idRaza[0].value,
            idFundacion: idFundacion[0].value
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult !== false) {
                $("#register-error-mascota").addClass("d-none");
                $("#btnRegistrar-mascota").addClass("d-none");
                $("#register-success-mascota").removeClass("d-none");
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
    let contenido = "<option selected>--Seleccione la especie--</option>";
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
    let contenido = "<option selected>--Seleccione la raza--</option>";
    $.each(razas, function (index, raza) {
        raza = JSON.parse(raza);
        contenido += '<option value="'+ raza.idRaza +'">' + raza.raza + '</option>';
    });
    $("#select-raza").html(contenido);
}

/*Select de fundaciones*/
function getFundMascotas() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletFundacionSeleccionar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                mostrarFundMascotas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las fundaciones.");
            }
        }
    });
}

function mostrarFundMascotas(fundaciones) {
    let contenido = "<option selected>--Seleccione la fundación--</option>";
    $.each(fundaciones, function (index, fundacion) {
        fundacion = JSON.parse(fundacion);
        contenido += '<option value="'+ fundacion.idFundacion +'">' + fundacion.nombreFundacion + '</option>';
    });

    $("#select-fundacion-mascota").html(contenido);
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
                listarMascotas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las mascotas.");
            }
        }
    });
}

function listarMascotas(mascotas) {
    let contenido = "";
    $.each(mascotas, function (index, mascota) {
        mascota = JSON.parse(mascota);
        contenido += '<tr><th scope="row" id="idMascota' + mascota.idMascota + '">' + mascota.idMascota + '</th>' +
            '<td>' + mascota.nombreMascota + '</td>' +
            '<td>' + mascota.edad + '</td>' +
            '<td>' + mascota.especie + '</td>' +
            '<td>' + mascota.raza + '</td>' +
            '<td>' + mascota.fundacion + '</td>' +
            '<td><input type="checkbox" name="estado" id="estado' + mascota.idMascota + '" disabled ';

        if (mascota.estado) {
            contenido += 'checked'
        }
        contenido += '><td>' + mascota.descripcion + '</td>' +
            '<td><button class="btn btn-success" type="submit" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-editar-mascota" ' +
            'onclick="llenarFormularioMascota(' + mascota.idMascota + ')"><i class="fas fa-pencil"></button></td>' +
            '<td><button type="submit" class="btn btn-warning" id="btnEliminar-mascota" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-eliminar-mascota" onclick="llenarMascotaModal('+ mascota.idMascota +')">' +
            '<i class="fas fa-trash"></button></td></tr>';
    });

    $("#mascotas-tbody").html(contenido);
}

//Llenar el formulario de mascota para la edición
function llenarFormularioMascota(idMascota) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaLlenarForm",
        data: $.param({
            idMascota: idMascota
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#input-editar-id-mascota").val(parsedResult.idMascota);
                $("#input-editar-nombre-mascota").val(parsedResult.nombreMascota);
                $("#input-editar-edad").val(parsedResult.edad);
                $("#select-editar-especie").val(parsedResult.especie);
                $("#select-editar-raza").val(parsedResult.raza);
                $("#input-editar-descripcion").val(parsedResult.descripcion);
                $("#select-editar-fundacion-mascota").val(parsedResult.fundacion);
                $("#input-estado").prop("checked", parsedResult.estado);

            } else {
                console.log("Error recuperando los datos de la mascota");
            }
        }
    });
}

//editarMascota
function editarMascota() {

    let idMascota = $("#input-editar-id-mascota").val();
    let nombreMascota = $("#input-editar-nombre-mascota").val();
    let edad = $("#input-editar-edad").val();
    let descripcion = $("#input-editar-descripcion").val();
    let estado = $("#input-estado-mascota").prop('checked');

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaModificar",
        data: $.param({
            idMascota:idMascota,
            nombreMascota: nombreMascota,
            edad: edad,
            descripcion: descripcion,
            estado: estado,
        }),
        success: function (result) {

            if (result !== false) {
                $("#editar-error-mascota").addClass("d-none");
                $("#btnEditar-mascota").addClass("d-none");
                $("#editar-success-mascota").removeClass("d-none");
            } else {
                $("#editar-error-mascota").removeClass("d-none");
                $("#editar-error-mascota").html("Error en el registro de la mascota");
            }
        }
    });
}

/*Eliminar mascota*/
function eliminarMascota() {

    let idMascota = $("#label-idMascota").html();
    console.log(idMascota);

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaEliminar",
        data: $.param({
            idMascota: idMascota
        }),
        success: function (result) {

            if (result !== false) {
                console.log("Registro eliminado")
                $("#eliminar-error-mascota").addClass("d-none");
                $("#eliminar-success-mascota").removeClass("d-none");
                $("#eliminar-success-mascota").html("Registro eliminado con exito");
            } else {
                console.log("Error eliminando el registro de la mascota");
                $("#eliminar-error-mascota").removeClass("d-none");
                $("#eliminar-error-mascota").html("Error al eliminar la mascota");
            }
        }
    });
}

/*llenar el objeto modal para elminar mascota*/
function llenarMascotaModal(idMascota) {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaModal",
        data: $.param({
            idMascota: idMascota
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {

                $("#label-idMascota").html(parsedResult.idMascota);
                $("#label-nombre-mascota").html(parsedResult.nombreMascota);

            } else {
                console.log("Error recuperando los datos de la mascota");
            }
        }
    });
}

/*Listar mascotas index*/
function getMascotasIndex() {
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMascotaListar",
        data: $.param({

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult !== false) {
                listarMascotasIndex(parsedResult);
            } else {
                console.log("Error recuperando los datos de las mascotas.");
            }
        }
    });
}

function listarMascotasIndex(mascotas) {
    let contenido = "";
    const RAZAS_URL = {
        /*Perros*/
        'Bulldog frances': "img/pets/bulldog-frances.jpg",
        'Yorkshire Terrier': "img/pets/yorkie.jpg",
        'Braco de Weimar': "img/pets/braco-weimar.jpg",
        'Golden Retriever': "img/pets/golden-retriever.jpg",
        'Husky Siberiano': "img/pets/husky.jpg",
        Criollo: "img/pets/mestizo.jpg",
        'Springer Spaniel': "img/pets/springer.jpg",
        'Cocker Spaniel': "img/pets/cocker-spaniel.jpg",
        'Bobtail': "img/pets/bobtail.jpg",
        'Pincher': "img/pets/pinscher.jpg",
        'Chihuahua': "img/pets/chihuahua.jpg",
        'Beagle': "img/pets/beagle.jpg",
        'Pug': "img/pets/pug.jpg",
        'Poodle': "img/pets/poodle.jpg",
        'Dálmata': "img/pets/dalmata.jpg",
        'Pastor Alemán': "img/pets/pastor-aleman.jpg",
        'Border Collie': "img/pets/border-collie.jpeg",

        /*Gatos*/
        Persa: "img/pets/persa.jpg",
        'Azul ruso': "img/pets/azul.jpg",
        'Siamés': "img/pets/siames.jpg",
        'Angora Turco': "img/pets/angora-turco.jpg",
        Siberiano: "img/pets/gato-siberiano.jpg",
        'Bengalí': "img/pets/bengali.jpg",
        Birmano: "img/pets/birmano.jpg",
        Esfinge: "img/pets/gato-esfinge.jpg",
        'Común': "img/pets/gato-comun.jpg",

        /*Otros*/
        Conejo: "img/pets/conejo.jpg",
        Caballo: "img/pets/caballo.jpg",
        Hamster: "img/pets/hamster.jpg",
        'Mini pig': "img/pets/piggy.jpg"
    };

    $.each(mascotas, function (index, mascota) {
        mascota = JSON.parse(mascota);
        let imagenPet = RAZAS_URL[mascota.raza];

        contenido += '<div class="col">' +
            '<div class="card">' +
                '<img src="' + imagenPet +'" class="card-img-top" height="200px" alt="' + mascota.raza + '">' +
                    '<div class="card-body">' +
                        '<h2 class="card-title text-center">'+ mascota.nombreMascota +'</h2>' +
                        '<div class="row g-3 align-items-center mb-3">' +
                            '<div class="col-auto">' +
                                '<p class="card-text"><strong>Especie: </strong>' + mascota.especie + '</p>' +
                            '</div>' +
                            '<div class="col-auto">' +
                                '<p class="card-text"><strong>Raza: </strong>' + mascota.raza + '</p>' +
                            '</div>' +
                            '<div class="col-auto">' +
                                '<p class="card-text"><strong>Edad: </strong>' + mascota.edad + ' años</p>' +
                            '</div>' +
                        '</div>' +
                        '<a class="btn btn-outline-info" data-bs-toggle="collapse" href="#info-dog' + mascota.idMascota +'" ' +
                            'role="button" aria-expanded="false" aria-controls="info-dog' + mascota.idMascota +'">' +
                                'Más Info' +
                            '</a>' +
                        '</p>' +
                        '<div class="collapse" id="info-dog' + mascota.idMascota +'">' +
                            '<div class="card card-body">' +
                                '<h5>Descripción:</h5>' +
                                '<p class="card-text">' + mascota.descripcion + '</p>' +
                                '<h5>Fundación:</h5>' +
                                '<p class="card-text">' + mascota.fundacion + '</p>' +
                            '</div></div></div></div></div>';
    });

    $("#index-lista-mascotas").html(contenido);
}

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

    /*var raza_id = document.getElementById('select-raza').options;
    console.log(raza_id)
    var razas;
    for(var i = 0 ; i < raza_id.length ; i++) {
        razas = raza_id[i];
        console.log(razas.value)
    }*/

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
            'onclick="llenarFormularioMascota(' + mascota.idMascota + ')">Editar</button></td>' +
            '<td><button type="submit" class="btn btn-warning" id="btnEliminar-mascota" data-bs-toggle="modal" ' +
            'data-bs-target="#modal-eliminar-mascota" onclick="llenarMascotaModal('+ mascota.idMascota +')">' +
            'Eliminar</button></td></tr>';
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
                $("#editar-success-mascota").removeClass("d-none");
                $("#editar-success-mascota").html("Registro exitoso");
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
    $.each(mascotas, function (index, mascota) {
        mascota = JSON.parse(mascota);
        contenido += '<div class="col">' +
            '<div class="card h-100">' +
                '<img src="img/dog_head_profile.svg" class="card-img-top" alt="...">' +
                    '<div class="card-body">'+
                        '<h4 class="card-title">'+ mascota.nombreMascota +'</h4>' +
                        '<h5>Especie:</h5>' +
                        '<p class="card-text">' + mascota.especie + '</p>' +
                        '<h5>Raza:</h5>' +
                        '<p class="card-text">' + mascota.raza + '</p>' +
                        '<h5>Edad:</h5>' +
                        '<p class="card-text">' + mascota.edad + '</p>' +
                        '<p>' +
                            '<a class="btn btn-primary" data-bs-toggle="collapse" href="#info-dog' + mascota.idMascota +'" ' +
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

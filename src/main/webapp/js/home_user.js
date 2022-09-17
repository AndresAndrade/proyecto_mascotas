var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    getUsuario().then(function () {
        //$("#mi-perfil-btn").attr("href", "profile.html?username=" + username);
        getPeliculas(false, "ASC");
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
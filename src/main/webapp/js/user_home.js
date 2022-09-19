var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    //getUsuario().then(function () {

    //});
});

//proceso asincronico, obligatorio usar los prefijos async y await
/*async function getUsuario() {
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
}*/



document.addEventListener("DOMContentLoaded", () => {
    if (!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        console.error("No se encontro ID de usuario en la sesión.");
        //document.getElementById("lista-productos").innerHTML = "<p>Favor de iniciar sesión nuevamente.</p>";
        return;
    }
    //cargarCarrito();
});



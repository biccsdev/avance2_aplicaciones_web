document.addEventListener("DOMContentLoaded", () => {
    if (!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        console.error("No se encontro ID de usuario en la sesión.");
        //document.getElementById("lista-productos").innerHTML = "<p>Favor de iniciar sesión nuevamente.</p>";
        return;
    }
    //cargarCarrito();
});

window.onload = () => {
    const nombreUsuario = document.getElementById("profile-name");
    const emailUsuario = document.getElementById("profile-email");
    const estadoUsuario = document.getElementById("profile-status");
    
    const init = () => { 
        obtenerUsuario();
    }
    
    const obtenerUsuario = () => {
        let host = `${CONTEXT_PATH}/resources/api/perfil`;
        let id = new URLSearchParams(window.location.search).get("idUsuario");
        if(id != null){
            fetch(
                host + `/${id}`, 
                {method: "GET"}
            ).then(response => {
                if(!response.ok){
                    throw new Error("Error al intentar obtener el usuario.");
                }
                return response.json();
            }).then(usuario => {
                cargarUsuario(usuario);
            }).catch(err => {
                console.log(err);
            });
        }
    }
    
    const cargarUsuario = (usuario) => {
        // Carga cada atributo del usuario
        nombreUsuario.innerHTML = `${usuario.nombres}` + `${usuario.apellidoPaterno}` + `${usuario.apellidoMaterno}`;
        emailUsuario.innerHTML = `${usuario.email}`;
        estadoUsuario.innerHTML = `${usuario.estadoUsuario}`;
    }
    
    init();
}
document.addEventListener("DOMContentLoaded", () => {
    renderizarMensajes();
    if (typeof ID_USUARIO_ACTUAL === 'undefined' || !ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        console.error("Error: No se encontró ID de usuario en la sesión.");
        document.getElementById("profile-name").textContent = "Sesión no válida";
        return; 
    }
    obtenerUsuario(ID_USUARIO_ACTUAL);
});

const renderizarMensajes = () => {
    const contenedor = document.getElementById("mensajes-container");
    
    // Validar si hay algún mensaje de éxito 
    if (typeof SERVER_MSG_SUCCESS !== 'undefined' && SERVER_MSG_SUCCESS.trim() !== "") {
        const alerta = document.createElement("h4");
        alerta.className = "form-message success";
        alerta.textContent = SERVER_MSG_SUCCESS;
        contenedor.appendChild(alerta);
    }

    // Validar si hay algún mensaje de error
    if (typeof SERVER_MSG_ERROR !== 'undefined' && SERVER_MSG_ERROR.trim() !== "") {
        const alerta = document.createElement("h4");
        alerta.className = "form-message error";
        alerta.textContent = SERVER_MSG_ERROR;
        contenedor.appendChild(alerta);
    }
};

const obtenerUsuario = (id) => {
    // url de la api
    const url = `${CONTEXT_PATH}/resources/api/perfil/usuario/${id}`;
    console.log(`Consultando API: ${url}`); // Línea para hacer validaciones
    fetch(url, { method: "GET" })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }
            return response.json();
        })
        .then(usuario => {
            cargarUsuario(usuario);
        })
        .catch(err => {
            console.error("Error obteniendo usuario:", err);
            document.getElementById("profile-name").textContent = "Error al cargar datos";
        });
};

const cargarUsuario = (usuario) => {
    // constantes que hacen referencia al DOM
    const nombreUsuario = document.getElementById("profile-name");
    const emailUsuario = document.getElementById("profile-email");
    const estadoUsuario = document.getElementById("profile-status");

    // Validamos para evitar errores si un dato viene null (ej. apellido materno)
    const nombre = usuario.nombres || "";
    const paterno = usuario.apellidoPaterno || "";
    const materno = usuario.apellidoMaterno || "";

    // Insertar Nombre Completo
    nombreUsuario.textContent = `${nombre} ${paterno} ${materno}`.trim();

    // Insertar Email
    emailUsuario.textContent = usuario.email || "Sin email";

    // Insertar Estado junto con su color correspondiente
    const estado = usuario.estadoUsuario || "DESCONOCIDO";
    const claseEstado = (estado === 'ACTIVO') ? 'active' : 'inactive';
    
    estadoUsuario.innerHTML = `
        <span class="status-dot ${claseEstado}"></span>
        <span class="status-label"> ${estado.toLowerCase()}</span>
    `;
};
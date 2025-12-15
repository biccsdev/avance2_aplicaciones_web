// Expresión regular que permite el uso de letras, acentos, ñ, ü y espacios
const REGEX_SOLO_LETRAS = /^[a-zA-ZÁÉÍÓÚáéíóúñÑÜü\s]+$/;
// Expresión regular que restringe el uso de solo dígitos del 0 al 9.
const REGEX_SOLO_NUMEROS = /^[0-9]+$/;

document.addEventListener('DOMContentLoaded', () => {
    // Inicializar las validaciones DOM
    inicializarValidaciones();

    const form = document.querySelector('.profile-form');
    if (form) {
        form.addEventListener('submit', function (e) {
            if (!form.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
                form.reportValidity();
            } else {
                e.preventDefault();
                actualizarPerfil();
            }
        });
    }
});

function inicializarValidaciones() {
    const form = document.querySelector('.profile-form');
    const inputs = form.querySelectorAll('input');

    inputs.forEach(input => {
        // Evento que resalta los inputs
        input.addEventListener('focus', resaltar);

        // Evento blur que quita el resaltado
        input.addEventListener('blur', (e) => {
            noResaltar(e);
            validarInput(e);
        });

        // Evento input que valida los campos en tiempo real
        input.addEventListener('input', validarInput);
    });
}

function resaltar(evento) {
    evento.target.classList.add("selected");
}

function noResaltar(evento) {
    evento.target.classList.remove("selected");
}

function validarInput(evento) {
    const input = evento.target;
    const valor = input.value.trim();
    const nombreCampo = input.name;

    // Validar si el campo está vacío
    if (input.hasAttribute('required') && valor === "") {
        marcarError(input, "Este campo es obligatorio.");
        return;
    }

    // Si el campo no es requerido y está vacío, se limpia el estado
    if (valor === "") {
        limpiarEstado(input);
        return;
    }

    // Validar los nombres y los apellidos
    if (nombreCampo === 'nombres' || nombreCampo === 'apellidoPaterno' || nombreCampo === 'apellidoMaterno') {
        if (!REGEX_SOLO_LETRAS.test(valor)) {
            marcarError(input, "No se permiten números ni caracteres especiales.");
        } else {
            marcarValido(input);
        }
    }

    //validar colonia
    else if (nombreCampo === 'colonia') {
        if (!REGEX_SOLO_LETRAS.test(valor)) {
            marcarError(input, "La colonia solo puede contener letras y espacios.");
        } else if (valor.length < 5) {
            marcarError(input, "La colonia debe tener al menos 5 caracteres.");
        } else {
            marcarValido(input);
        }
    }

    // Validar el número exterior
    else if (nombreCampo === 'numero') {
        if (!REGEX_SOLO_NUMEROS.test(valor)) {
            marcarError(input, "Solo se permiten números.");
        } else if (valor.length < 3 || valor.length > 4) {
            marcarError(input, "El número debe tener entre 3 y 4 dígitos.");
        } else {
            marcarValido(input);
        }
    }

    // Validar teléfono
    else if (nombreCampo === 'telefono') {
        if (!REGEX_SOLO_NUMEROS.test(valor)) {
            marcarError(input, "Solo se permiten números.");
        } else if (valor.length !== 10) {
            marcarError(input, "El teléfono debe tener 10 dígitos.");
        } else {
            marcarValido(input);
        }
    }

    // Validar contraseña
    else if (nombreCampo === 'contrasenia') {
        if (valor.length > 0 && valor.length < 4) {
            marcarError(input, "La contraseña debe tener al menos 4 caracteres.");
        } else {
            marcarValido(input);
        }
    } else {
        marcarValido(input);
    }
}

function marcarError(input, mensaje) {
    input.setCustomValidity(mensaje);

    input.classList.add("error");
    input.classList.remove("valid");

    input.reportValidity();
}

function marcarValido(input) {
    input.setCustomValidity(""); // String vacío = Válido
    input.classList.remove("error");
    input.classList.add("valid"); // Color Verde
}

function limpiarEstado(input) {
    input.setCustomValidity("");
    input.classList.remove("error");
    input.classList.remove("valid");
}

async function actualizarPerfil() {
    const btnGuardar = document.querySelector('.btn-black-big');
    const originalText = btnGuardar.innerText;

    const form = document.querySelector('.profile-form');
    const formData = new FormData(form);

    const datosUsuario = {
        email: USER_EMAIL,
        nombres: formData.get('nombres'),
        apellidoPaterno: formData.get('apellidoPaterno'),
        apellidoMaterno: formData.get('apellidoMaterno'),
        telefono: formData.get('telefono'),
        contrasenia: formData.get('contrasenia') || null,
        direccion: {
            calle: formData.get('calle'),
            numero: formData.get('numero'),
            colonia: formData.get('colonia')
        }
    };

    try {
        btnGuardar.disabled = true;
        btnGuardar.innerText = "Guardando...";

        const response = await fetch(`${CONTEXT_PATH}/resources/api/perfil/actualizar`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosUsuario)
        });

        const resultado = await response.json();

        if (response.ok) {
            alert('¡Información actualizada con éxito!');
            window.location.href = `${CONTEXT_PATH}/perfil/ver.jsp`;
        } else {
            alert('Error: ' + (resultado.error || 'No se pudo actualizar el perfil.'));
        }

    } catch (error) {
        console.error("Error:", error);
        alert('Ocurrió un error.');
    } finally {
        btnGuardar.disabled = false;
        btnGuardar.innerText = originalText;
    }
}
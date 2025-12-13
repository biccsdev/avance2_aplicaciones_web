document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.profile-form');
    
    if(form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            actualizarPerfil();
        });
    }
});

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

        const response = await fetch(`${CONTEXT_PATH}/resources/usuarios/actualizar`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosUsuario)
        });

        const resultado = await response.json();

        if (response.ok) {
            alert('¡Información actualizada con exito!');
            
            window.location.href = `${CONTEXT_PATH}/perfil`; 
            
        } else {
            alert('Error: ' + (resultado.error || 'No se pudo actualizar el perfil.'));
        }

    } catch (error) {
        console.error("Error:", error);
        alert('Ocurrio un error de conexion.');
    } finally {
        btnGuardar.disabled = false;
        btnGuardar.innerText = originalText;
    }
}

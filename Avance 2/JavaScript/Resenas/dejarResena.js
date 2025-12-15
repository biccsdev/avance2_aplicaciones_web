/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", () => {
    if (!ID_PEDIDO) {
        alert("Error: No se especificó el pedido.");
        window.location.href = `${CONTEXT_PATH}/pedidos`;
        return;
    }
    
    cargarProductosDelPedido();

    const form = document.getElementById("form-resena");
    if (form) {
        form.addEventListener("submit", (e) => {
            e.preventDefault();
            enviarResena();
        });
    }
});

async function cargarProductosDelPedido() {
    const listaVisual = document.getElementById("lista-visual-productos");
    const selectProducto = document.getElementById("productoId");

    try {
        const response = await fetch(`${CONTEXT_PATH}/resources/pedidos/${ID_PEDIDO}`);
        
        if (!response.ok) throw new Error("No se pudo cargar la información del pedido.");

        const pedido = await response.json();
        const detalles = pedido.detalles || [];

        listaVisual.innerHTML = "";
        selectProducto.innerHTML = '<option value="">Selecciona un producto</option>';

        detalles.forEach(detalle => {
            const juego = detalle.videojuego;
            
            const option = document.createElement("option");
            option.value = juego.idVideojuego;
            option.textContent = `${juego.nombre} - ${juego.plataforma}`;
            selectProducto.appendChild(option);

            let rutaImagen = juego.urlImagen;
            if (rutaImagen && !rutaImagen.startsWith("http")) {
                if (!rutaImagen.startsWith("imgs/") && !rutaImagen.startsWith("/imgs/")) {
                    rutaImagen = "imgs/" + rutaImagen;
                }
                if (rutaImagen.startsWith("/")) rutaImagen = rutaImagen.substring(1);
                rutaImagen = CONTEXT_PATH + "/" + rutaImagen;
            } else if (!rutaImagen) {
                rutaImagen = CONTEXT_PATH + "/imgs/iconoImagen.png";
            }

            const htmlItem = `
                <div class="product-preview">
                    <img src="${rutaImagen}" alt="${juego.nombre}" class="product-img" onerror="this.src='${CONTEXT_PATH}/imgs/iconoImagen.png'">
                    <span>${juego.nombre} (${juego.plataforma})</span>
                </div>
            `;
            listaVisual.insertAdjacentHTML("beforeend", htmlItem);
        });

    } catch (error) {
        console.error(error);
        listaVisual.innerHTML = "<p>Error al cargar productos.</p>";
    }
}

async function enviarResena() {
    const btnEnviar = document.getElementById("btn-enviar");
    const originalText = btnEnviar.innerText;

    const idVideojuego = document.getElementById("productoId").value;
    const titulo = document.getElementById("titulo").value.trim();
    const comentario = document.getElementById("comentario").value.trim();
    
    const ratingInput = document.querySelector('input[name="calificacion"]:checked');

    if (!idVideojuego) {
        alert("Por favor selecciona que producto quieres reseñar.");
        return;
    }

    if (!titulo) {
        alert("Por favor escribe un título para tu reseña.");
        return;
    }

    if (!ratingInput) {
        alert("Por favor selecciona una calificación para continuar.");
        return;
    }

    if (!comentario) {
        alert("Por favor agrega un comentario");
        return;
    }

    const calificacion = parseFloat(ratingInput.value);

    const resenaDTO = {
        idCliente: parseInt(ID_USUARIO_ACTUAL),
        idVideojuego: parseInt(idVideojuego),
        calificacion: calificacion,
        titulo: titulo,
        comentario: comentario,
        fechaResena: new Date().toISOString().split('T')[0]
    };

    try {
        btnEnviar.disabled = true;
        btnEnviar.innerText = "Enviando...";

        const response = await fetch(`${CONTEXT_PATH}/resources/api/resena`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(resenaDTO)
        });

        if (response.ok) {
            alert("¡Gracias! Tu reseña ha sido publicada.");
            window.location.href = `${CONTEXT_PATH}/pedidos/detalle?id=${ID_PEDIDO}`;
        } else {
            const data = await response.json().catch(() => ({}));
            alert("Error: " + (data.error || "No se pudo guardar la reseña."));
        }

    } catch (error) {
        console.error(error);
        alert("Ocurrió un error de conexión.");
    } finally {
        btnEnviar.disabled = false;
        btnEnviar.innerText = originalText;
    }
}

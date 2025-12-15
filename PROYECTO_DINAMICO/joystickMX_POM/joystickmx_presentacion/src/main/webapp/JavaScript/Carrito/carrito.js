/**
 * @file carrito.js
 * @description Módulo encargado de la gestión del carrito de compras en el cliente.
 * Incluye la carga de productos, actualización de cantidades, eliminación de items y validaciones de stock.
 * @author Ariel y sebas
 */

document.addEventListener("DOMContentLoaded", () => {
    // Verificamos si existe un usuario logueado antes de iniciar la carga
    if (!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        console.error("No se encontro ID de usuario en la sesión.");
        document.getElementById("lista-productos").innerHTML = "<p>Por favor inicia sesión nuevamente.</p>";
        return;
    }
    cargarCarrito();
});

/**
 * Carga los productos del carrito desde el servidor y renderiza la vista.
 * Calcula subtotales, verifica items deshabilitados y actualiza el estado de los botones.
 * * @async
 * @function cargarCarrito
 * @returns {Promise<void>} No retorna valor, manipula el DOM directamente.
 */
async function cargarCarrito() {
    // Referencias al DOM
    const listaProductosContainer = document.getElementById("lista-productos");
    const lblSubtotal = document.getElementById("lbl-subtotal");

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`;
        const response = await fetch(urlApi);

        // Manejo de errores HTTP
        if (!response.ok) {
            if (response.status === 404) {
                // Carrito vacío o no encontrado
                listaProductosContainer.innerHTML = "<p>No tienes productos en el carrito.</p>";
                lblSubtotal.innerText = "$0.00";
                actualizarEstadoBotones(true);
                return;
            }
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const carrito = await response.json();
        const items = carrito.items || [];

        // Limpiar variables de estado
        listaProductosContainer.innerHTML = "";
        let subtotal = 0;
        let hayItemsDeshabilitados = false; // FLAG para controlar el botón de pago visualmente

        if (items.length === 0) {
            listaProductosContainer.innerHTML = "<p>Tu carrito esta vacio.</p>";
            lblSubtotal.innerText = "$0.00";

            const btnPago = document.getElementById("btn-pago");
            if (btnPago) {
                btnPago.classList.remove("btn-warning");
                btnPago.innerText = "Proceder al pago";
            }

            actualizarEstadoBotones(true);
            return;
        }

        items.forEach(item => {
            const videojuego = item.videojuego || {};
            const nombreJuego = videojuego.nombre || "Producto desconocido";
            const precioJuego = videojuego.precio || 0;
            const plataforma = videojuego.plataforma || "";

            const estaHabilitado = (typeof videojuego.habilitado !== 'undefined') ? videojuego.habilitado : true;

            // Lógica para construir la URL de la imagen
            let rutaImagen = videojuego.urlImagen;
            if (!rutaImagen) {
                rutaImagen = "imgs/iconoImagen.png";
            } else if (!rutaImagen.startsWith("http") && !rutaImagen.startsWith("imgs/") && !rutaImagen.startsWith("/imgs/")) {
                rutaImagen = `imgs/${rutaImagen}`;
            }
            if (rutaImagen.startsWith("/")) {
                rutaImagen = rutaImagen.substring(1);
            }
            const urlFinal = rutaImagen.startsWith("http") ? rutaImagen : `${CONTEXT_PATH}/${rutaImagen}`;

            const precioItem = precioJuego * item.cantidad;

            // Calcular subtotal solo de items activos
            if (estaHabilitado) {
                subtotal += precioItem;
            } else {
                hayItemsDeshabilitados = true;
            }

            const claseDeshabilitado = !estaHabilitado ? "item-deshabilitado" : "";
            const mensajeError = !estaHabilitado ? "<div class='error-msg-item'> PRODUCTO NO DISPONIBLE - ELIMÍNALO PARA CONTINUAR</div>" : "";
            const controlesCantidad = estaHabilitado ?
                    `<button class="btn-menos" onclick="actualizarCantidad(${item.idItemCarrito}, ${item.cantidad - 1})">-</button>
                 <span class="qty-num">${item.cantidad}</span>
                 <button class="btn-mas" onclick="actualizarCantidad(${item.idItemCarrito}, ${item.cantidad + 1})">+</button>`
                    :
                    `<span class="qty-num">No disponible</span>`;

            const htmlProducto = `
                <article class="producto-item ${claseDeshabilitado}" data-id-item="${item.idItemCarrito}" data-habilitado="${estaHabilitado}">
                    <div class="producto-info">
                        <img class="producto-img" 
                             src="${urlFinal}" 
                             alt="${nombreJuego}"
                             onerror="this.src='${CONTEXT_PATH}/imgs/iconoImagen.png'"> 
                             
                        <div class="producto-meta">
                            <h2 class="producto-nombre">
                                ${nombreJuego} - ${plataforma}
                            </h2>
                            ${mensajeError} 
                            
                            <div class="producto-cantidad">
                                ${controlesCantidad}
                            </div>
                            
                            <button class="btn-eliminar" onclick="eliminarItem(${item.idItemCarrito})">Eliminar del carro</button>
                        </div>
                    </div>
                    <div class="producto-precio">$${precioItem.toFixed(2)}</div>
                </article>
            `;

            listaProductosContainer.insertAdjacentHTML("beforeend", htmlProducto);
        });

        // Actualizar UI final
        lblSubtotal.innerText = `$${subtotal.toLocaleString("en-US", {minimumFractionDigits: 2, maximumFractionDigits: 2})}`;

        actualizarEstadoBotones(false);
        const btnPago = document.getElementById("btn-pago");
        if (btnPago) {
            if (hayItemsDeshabilitados) {
                btnPago.classList.add("btn-warning");
                btnPago.innerText = "Revisa tu carrito";
            } else {
                btnPago.classList.remove("btn-warning");
                btnPago.innerText = "Proceder al pago";
            }
        }

    } catch (error) {
        console.error("Error detallado:", error);
        listaProductosContainer.innerHTML = "<p>Hubo un error al cargar tu carrito.</p>";
    }
}


/**
 * Elimina un item específico del carrito.
 * Solicita confirmación al usuario antes de proceder.
 * * @async
 * @function eliminarItem
 * @param {number} idItem - El ID del item de carrito a eliminar.
 * @returns {Promise<void>}
 */

async function eliminarItem(idItem) {
    if (!confirm("¿Estas seguro de eliminar este producto?"))
        return;

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/item/${idItem}`;
        const response = await fetch(urlApi, {method: "DELETE"});

        if (response.ok) {
            cargarCarrito();
        } else {
            alert("No se pudo eliminar el producto. Intenta nuevamente.");
        }
    } catch (error) {
        console.error("Error al eliminar:", error);
        alert("Error de conexión al intentar eliminar.");
    }
}

/**
 * Actualiza la cantidad de un producto en el carrito.
 * Si la cantidad llega a 0, invoca la eliminación del item.
 * * @async
 * @function actualizarCantidad
 * @param {number} idItem - El ID del item de carrito a modificar.
 * @param {number} nuevaCantidad - La nueva cantidad deseada.
 * @returns {Promise<void>}
 */

async function actualizarCantidad(idItem, nuevaCantidad) {
    if (nuevaCantidad < 1) {
        eliminarItem(idItem);
        return;
    }

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/item/${idItem}?cantidad=${nuevaCantidad}`;
        const response = await fetch(urlApi, {method: "PUT"});

        if (response.ok) {
            cargarCarrito();
        } else {
            const data = await response.json().catch(() => ({}));
            alert("Error: " + (data.error || "No se pudo actualizar la cantidad."));
        }
    } catch (error) {
        console.error("Error al actualizar:", error);
    }
}

/**
 * Elimina todos los productos del carrito del usuario actual.
 * Solicita confirmación de seguridad antes de ejecutar la acción masiva.
 * * @async
 * @function vaciarCarritoCompleto
 * @returns {Promise<void>}
 */

async function vaciarCarritoCompleto() {
    if (!confirm("¿Estas seguro de que quieres eliminar TODOS los productos del carrito?")) {
        return;
    }

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}/vaciar`;

        const response = await fetch(urlApi, {
            method: "DELETE"
        });

        if (response.ok) {
            cargarCarrito();
        } else {
            const data = await response.json().catch(() => ({}));
            alert("Error: " + (data.error || "No se pudo vaciar el carrito."));
        }

    } catch (error) {
        console.error("Error al vaciar carrito:", error);
        alert("Ocurrió un error de conexión.");
    }
}

/**
 * Habilita o deshabilita visualmente los botones de acción global (vaciar/pagar).
 * Se usa cuando el carrito queda vacío o se está cargando.
 * * @function actualizarEstadoBotones
 * @param {boolean} carritoVacio - Indica si el carrito está vacío para bloquear botones.
 */
function actualizarEstadoBotones(carritoVacio) {
    const botones = [
        document.getElementById("btn-vaciar"),
        document.getElementById("btn-pago")
    ];

    botones.forEach(btn => {
        if (!btn)
            return;
        if (carritoVacio) {
            btn.classList.add("btn-disabled");
            btn.disabled = true;
        } else {
            btn.classList.remove("btn-disabled");
            btn.disabled = false;
        }
    });
}

/**
 * Inicia el proceso de pago.
 * Realiza validaciones previas: items deshabilitados y stock suficiente en el servidor.
 * Si todo es correcto, redirige a la página de pago.
 * * @async
 * @function irAPago
 * @returns {Promise<void>}
 */
async function irAPago() {
    const btn = document.getElementById("btn-pago");
    if (btn.disabled)
        return;

    //if Items deshabilitados
    const itemsNoDisponibles = document.querySelectorAll('article[data-habilitado="false"]');

    if (itemsNoDisponibles.length > 0) {
        let nombresJuegos = "";
        itemsNoDisponibles.forEach(item => {
            const nombreElement = item.querySelector(".producto-nombre");
            const nombreTexto = nombreElement ? nombreElement.innerText.split("\n")[0] : "Juego desconocido"; 
            nombresJuegos += `- ${nombreTexto}\n`;
        });

        alert(`️NO PUEDES CONTINUAR. Los siguientes juegos ya no están disponibles en la tienda:
                \n
                ${nombresJuegos}
                \n
                Por favor, elimínalos del carrito para proceder al pago.`);

        return;
    }

    const textoOriginal = btn.innerText;

    try {
        btn.disabled = true;
        btn.innerText = "Verificando...";

        // if Verificación de stock en servidor
        const urlValidacion = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}/validar-stock`;
        const response = await fetch(urlValidacion);

        if (!response.ok)
            throw new Error("Error en la validación de stock");

        const resultado = await response.json();

        if (Array.isArray(resultado) && resultado.length > 0) {
            let mensajeAlerta = "️ NO SE PUEDE CONTINUAR️\n\nAlgunos productos superan las existencias disponibles:\n\n";
            mensajeAlerta += resultado.join("\n\n");
            mensajeAlerta += "\n\nPor favor, reduce la cantidad de estos productos antes de continuar.";

            alert(mensajeAlerta);

            return;
        }

        window.location.href = `${CONTEXT_PATH}/carrito/pago.jsp`;

    } catch (error) {
        console.error("Error validando stock:", error);
        alert("Ocurrió un error al verificar las existencias. Intente nuevamente.");
    } finally {
        btn.disabled = false;
        btn.innerText = textoOriginal;
    }
}
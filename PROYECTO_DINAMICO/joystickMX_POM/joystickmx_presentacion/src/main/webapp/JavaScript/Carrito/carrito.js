document.addEventListener('DOMContentLoaded', () => {
    if (!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        console.error("No se encontró ID de usuario en la sesión.");
        document.getElementById('lista-productos').innerHTML = '<p>Por favor inicia sesión nuevamente.</p>';
        return;
    }
    cargarCarrito();
});

async function cargarCarrito() {
    const listaProductosContainer = document.getElementById('lista-productos');
    const lblSubtotal = document.getElementById('lbl-subtotal');

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`;
        const response = await fetch(urlApi);

        if (!response.ok) {
            if (response.status === 404) {
                listaProductosContainer.innerHTML = '<p>No tienes productos en el carrito.</p>';
                lblSubtotal.innerText = '$0.00';
                return;
            }
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const carrito = await response.json();
        const items = carrito.items || [];

        listaProductosContainer.innerHTML = '';
        let subtotal = 0;

        if (items.length === 0) {
            listaProductosContainer.innerHTML = '<p>Tu carrito está vacío.</p>';
            lblSubtotal.innerText = '$0.00';
            return;
        }

        items.forEach(item => {
            const videojuego = item.videojuego || {};
            const nombreJuego = videojuego.nombre || "Producto desconocido";
            const precioJuego = videojuego.precio || 0;
            const plataforma = videojuego.plataforma || "";
            const urlImagen = videojuego.urlImagen ?
                    (videojuego.urlImagen.startsWith('http') ? videojuego.urlImagen : `${CONTEXT_PATH}/${videojuego.urlImagen}`)
                    : `${CONTEXT_PATH}/imgs/iconoImagen.png`;

            const precioItem = precioJuego * item.cantidad;
            subtotal += precioItem;

            const htmlProducto = `
                <article class="producto-item" data-id-item="${item.idItemCarrito}">
                    <div class="producto-info">
                        <img class="producto-img" 
                             src="${urlImagen}" 
                             alt="${nombreJuego}"
                             onerror="this.src='${CONTEXT_PATH}/imgs/iconoImagen.png'"> 
                             
                        <div class="producto-meta">
                            <h2 class="producto-nombre">${nombreJuego} - ${plataforma}</h2>
                            
                            <div class="producto-cantidad">
                                <button class="btn-menos" onclick="actualizarCantidad(${item.idItemCarrito}, ${item.cantidad - 1})">-</button>
                                <span class="qty-num">${item.cantidad}</span>
                                <button class="btn-mas" onclick="actualizarCantidad(${item.idItemCarrito}, ${item.cantidad + 1})">+</button>
                            </div>
                            
                            <button class="btn-eliminar" onclick="eliminarItem(${item.idItemCarrito})">Eliminar del carro</button>
                        </div>
                    </div>
                    <div class="producto-precio">$${precioJuego.toFixed(2)}</div>
                </article>
            `;

            listaProductosContainer.insertAdjacentHTML('beforeend', htmlProducto);
        });

        lblSubtotal.innerText = `$${subtotal.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;

    } catch (error) {
        console.error('Error detallado:', error);
        listaProductosContainer.innerHTML = '<p>Hubo un error al cargar tu carrito.</p>';
    }
}

//funciones para los botones del carrito

async function eliminarItem(idItem) {
    if (!confirm("¿Estás seguro de eliminar este producto?")) return;

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/item/${idItem}`;
        const response = await fetch(urlApi, { method: 'DELETE' });

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

async function actualizarCantidad(idItem, nuevaCantidad) {
    if (nuevaCantidad < 1) {
        eliminarItem(idItem);
        return;
    }

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/item/${idItem}?cantidad=${nuevaCantidad}`;
        const response = await fetch(urlApi, { method: 'PUT' });

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

async function vaciarCarritoCompleto() {
    if (!confirm("¿Estás seguro de que quieres eliminar TODOS los productos del carrito?")) {
        return;
    }

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}/vaciar`;
        
        const response = await fetch(urlApi, {
            method: 'DELETE'
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
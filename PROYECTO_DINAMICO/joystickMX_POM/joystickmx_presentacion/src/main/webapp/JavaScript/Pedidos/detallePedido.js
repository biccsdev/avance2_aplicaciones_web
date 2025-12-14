document.addEventListener("DOMContentLoaded", () => {
    cargarDetallePedido();
});

async function cargarDetallePedido() {
    const idPedido = new URLSearchParams(window.location.search).get("id");
    const container = document.querySelector(".order-detail-content");

    if (!idPedido) {
        container.innerHTML = "<p>Error: No se especificó el ID del pedido.</p>";
        return;
    }

    try {
        const response = await fetch(`${CONTEXT_PATH}/resources/pedidos/${idPedido}`);
        
        if (!response.ok) {
            throw new Error("No se pudo cargar el pedido.");
        }

        const pedido = await response.json();
        renderizarDetalle(pedido);

    } catch (error) {
        console.error(error);
        container.innerHTML = "<p class='error-msg'>No se pudo cargar la información del pedido. Intenta nuevamente.</p>";
    }
}

function renderizarDetalle(pedido) {
    let fecha = "Desconocida";
    if (Array.isArray(pedido.fechaPedido)) {
        const [anio, mes, dia] = pedido.fechaPedido;
        fecha = `${dia}/${mes}/${anio}`;
    }

    const estado = pedido.estadoPedido || "DESCONOCIDO";
    const metodoPago = pedido.pago ? pedido.pago.metodoPago : "No especificado";
    const direccion = pedido.direccionEnvio;
    const direccionStr = direccion ? `${direccion.calle} #${direccion.numero}, ${direccion.colonia}` : "Dirección no disponible";

    const infoSection = document.getElementById("info-pedido-container");
    infoSection.innerHTML = `
        <div class="info-card">
            <p><strong>Número de Pedido:</strong> #${pedido.idPedido}</p>
            <p><strong>Fecha:</strong> ${fecha}</p>
            <p><strong>Estado:</strong> <span class="status-badge status-${estado.toLowerCase()}">${estado}</span></p>
            <p><strong>Método de pago:</strong> ${metodoPago}</p>
            <p><strong>Dirección de envío:</strong></p>
            <p class="address">${direccionStr}</p>
        </div>
    `;

    const productosContainer = document.getElementById("lista-productos-detalle");
    productosContainer.innerHTML = "";
    
    const detalles = pedido.detalles || [];
    
    detalles.forEach(detalle => {
        const juego = detalle.videojuego || {};
        const nombre = juego.nombre || "Producto desconocido";
        const precio = detalle.precioUnitario || 0;
        const cantidad = detalle.cantidad || 0;
        
        let rutaImagen = juego.urlImagen;
        if (!rutaImagen) rutaImagen = "imgs/iconoImagen.png";
        else if (!rutaImagen.startsWith("http") && !rutaImagen.startsWith("imgs/") && !rutaImagen.startsWith("/imgs/")) {
            rutaImagen = `imgs/${rutaImagen}`;
        }
        if (rutaImagen.startsWith("/")) rutaImagen = rutaImagen.substring(1);
        const urlImg = rutaImagen.startsWith("http") ? rutaImagen : `${CONTEXT_PATH}/${rutaImagen}`;

        const htmlProd = `
            <div class="product-item">
                <img src="${urlImg}" alt="${nombre}" class="product-img" onerror="this.src='${CONTEXT_PATH}/imgs/iconoImagen.png'">
                <div class="product-info">
                    <h3>${nombre}</h3>
                    <p>Cantidad: ${cantidad}</p>
                    <p class="product-price">$${precio.toFixed(2)}</p>
                </div>
            </div>
        `;
        productosContainer.insertAdjacentHTML("beforeend", htmlProd);
    });

    let subtotal = 0;
    detalles.forEach(d => subtotal += (d.precioUnitario * d.cantidad));
    
    const total = pedido.totalPagado || 0;
    const envio = total - subtotal; 
    document.getElementById("resumen-subtotal").innerText = `$${subtotal.toFixed(2)}`;
    document.getElementById("resumen-envio").innerText = `$${envio.toFixed(2)}`;
    document.getElementById("resumen-total").innerText = `$${total.toFixed(2)}`;

    const btnResena = document.getElementById("btn-dejar-resena");
    if(btnResena) {
        btnResena.href = `${CONTEXT_PATH}/pedidos/resena?id=${pedido.idPedido}`;
    }
}


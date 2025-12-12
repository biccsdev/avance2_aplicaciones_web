document.addEventListener("DOMContentLoaded", () => {
    cargarDatosConfirmacion();
});

let totalCalculado = 0;

async function cargarDatosConfirmacion() {
    const lblSubtotal = document.getElementById("lbl-subtotal");
    const lblEnvio = document.getElementById("lbl-envio");
    const lblTotal = document.getElementById("lbl-total");
    const lblMetodo = document.getElementById("lbl-metodo-pago");
    const lblDetalle = document.getElementById("lbl-detalle-pago");
    const divProductos = document.getElementById("lista-productos-resumen");

    const COSTO_ENVIO = 100.00;

    const metodoGuardado = sessionStorage.getItem("metodoPagoSeleccionado");
    const detalleTarjeta = sessionStorage.getItem("datosPagoDetalle");

    if (!metodoGuardado) {
        alert("No hay método de pago seleccionado. Volviendo...");
        window.location.href = `${CONTEXT_PATH}/carrito/pago.jsp`;
        return;
    }

    lblMetodo.innerText = metodoGuardado;
    if (metodoGuardado === "TARJETA" && detalleTarjeta) {
        const datos = JSON.parse(detalleTarjeta);
        lblDetalle.innerText = `Titular: ${datos.nombre} | Terminación: **** ${datos.numero}`;
    }

    try {
        const response = await fetch(`${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`);
        if (!response.ok)
            throw new Error("Error recuperando carrito");

        const carrito = await response.json();
        const items = carrito.items || [];

        let subtotal = 0;
        divProductos.innerHTML = "";

        items.forEach(item => {
            const precio = item.videojuego ? item.videojuego.precio : 0;
            const totalItem = precio * item.cantidad;
            subtotal += totalItem;

            divProductos.innerHTML += `<div>- ${item.videojuego.nombre} (x${item.cantidad})</div>`;
        });

        const totalFinal = subtotal + COSTO_ENVIO;
        totalCalculado = totalFinal;

        lblSubtotal.innerText = `$${subtotal.toFixed(2)}`;
        lblEnvio.innerText = `$${COSTO_ENVIO.toFixed(2)}`;
        lblTotal.innerText = `$${totalFinal.toFixed(2)}`;

    } catch (error) {
        console.error(error);
        alert("Error al cargar los detalles del pedido.");
    }
}

async function confirmarPedidoFinal() {
    const metodoPago = sessionStorage.getItem("metodoPagoSeleccionado");

    const pedidoDTO = {
        idCliente: parseInt(ID_USUARIO_ACTUAL), 
        totalPagado: totalCalculado, 
        metodoPago: metodoPago                 
    };

    try {
        const response = await fetch(`${CONTEXT_PATH}/resources/pedidos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pedidoDTO)
        });

        if (response.ok) {
            sessionStorage.removeItem("metodoPagoSeleccionado");
            sessionStorage.removeItem("datosPagoDetalle");

            window.location.href = `${CONTEXT_PATH}/carrito/mensajeFinalPedido.jsp`;

        } else {
            const errorData = await response.json();
            alert("Error al crear el pedido: " + (errorData.error || "Desconocido"));
        }
    } catch (error) {
        console.error(error);
        alert("Error de conexión al procesar el pedido.");
    }
}
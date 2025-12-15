/**
 * @file confirmacion.js
 * @description Módulo para la vista de resumen final del pedido.
 * Se encarga de calcular los totales finales, validar el método de pago seleccionado y enviar la orden al servidor.
 * @author Ariel Y Sebas
 */

document.addEventListener("DOMContentLoaded", () => {
    cargarDatosConfirmacion();
});

let totalCalculado = 0;

/**
 * Recupera los items del carrito y la información de pago de la sesión.
 * Calcula el subtotal, suma el envío y renderiza el resumen financiero en el DOM.
 * @async
 * @function cargarDatosConfirmacion
 * @returns {Promise<void>}
 */
async function cargarDatosConfirmacion() {
    // referencias a elementos dom donde vamos a mostrar las cosas
    const lblSubtotal = document.getElementById("lbl-subtotal");
    const lblEnvio = document.getElementById("lbl-envio");
    const lblTotal = document.getElementById("lbl-total");
    const lblMetodo = document.getElementById("lbl-metodo-pago");
    const lblDetalle = document.getElementById("lbl-detalle-pago");
    const divProductos = document.getElementById("lista-productos-resumen");

    const COSTO_ENVIO = 100.00;

    // Sacamos los datos del pago guardados en el paso anterior
    const metodoGuardado = sessionStorage.getItem("metodoPagoSeleccionado");
    const detalleTarjeta = sessionStorage.getItem("datosPagoDetalle");

    // Checamos si si agarro metodo de pago si no lo regresamos
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
        // Consultamos el carrito actual al servidor para obtener los items freshy fresh
        const response = await fetch(`${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`);
        if (!response.ok)
            throw new Error("Error recuperando carrito");

        const carrito = await response.json();
        const items = carrito.items || [];

        let subtotal = 0;
        divProductos.innerHTML = "";

        // un foreach en los productos para calcular  subtotal y generar la lista visual
        items.forEach(item => {
            const precio = item.videojuego ? item.videojuego.precio : 0;
            const totalItem = precio * item.cantidad;
            subtotal += totalItem;

            divProductos.innerHTML += `<div>- ${item.videojuego.nombre} (x${item.cantidad})</div>`;
        });

        // Calculamos el total final sumando el envío fijo
        const totalFinal = subtotal + COSTO_ENVIO;
        totalCalculado = totalFinal;

        // Actualizamos la interfaz con los precios formateados
        lblSubtotal.innerText = `$${subtotal.toFixed(2)}`;
        lblEnvio.innerText = `$${COSTO_ENVIO.toFixed(2)}`;
        lblTotal.innerText = `$${totalFinal.toFixed(2)}`;

    } catch (error) {
        console.error(error);
        alert("Error al cargar los detalles del pedido.");
    }
}

/**
 * Construye el objeto de transferencia (DTO) con los datos de la orden
 * y realiza la petición POST para registrar el pedido en la base de datos.
 * @async
 * @function confirmarPedidoFinal
 * @returns {Promise<void>}
 */

async function confirmarPedidoFinal() {
    const metodoPago = sessionStorage.getItem("metodoPagoSeleccionado");

    // Armamos el DTO que espera la Api con el ID del usuario y el total calculado
    const pedidoDTO = {
        idCliente: parseInt(ID_USUARIO_ACTUAL), 
        totalPagado: totalCalculado, 
        metodoPago: metodoPago                  
    };

    try {
        // Enviamos la petición de creación de pedido
        const response = await fetch(`${CONTEXT_PATH}/resources/pedidos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pedidoDTO)
        });

        // limpiamos sesión y redirigimos si todo bien con el pedido
        if (response.ok) {
            sessionStorage.removeItem("metodoPagoSeleccionado");
            sessionStorage.removeItem("datosPagoDetalle");

            window.location.href = `${CONTEXT_PATH}/carrito/mensajeFinalPedido.jsp`;

        } else {
            // errores de negocio 
            const errorData = await response.json();
            alert("Error al crear el pedido: " + (errorData.error || "Desconocido"));
        }
    } catch (error) {
        console.error(error);
        alert("Error de conexión al procesar el pedido.");
    }
}
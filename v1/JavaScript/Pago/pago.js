/**
 * @file pago.js
 * @description Gestiona la lógica de la pantalla de selección de método de pago.
 * Controla la visualización del formulario de tarjeta, valida los inputs de pago,
 * verifica el stock disponible antes de proceder y redirige a la confirmación.
 * @author Ariel y Sebas
 */

document.addEventListener("DOMContentLoaded", () => {
    cargarResumenPago();
    mostrarFormularioTarjeta();
});

/**
 * Controla la visibilidad del formulario de tarjeta de crédito/débito.
 * Si el usuario selecciona "TARJETA", muestra los campos y los hace obligatorios.
 * Si selecciona otro método, oculta el formulario y limpia los campos.
 * @function mostrarFormularioTarjeta
 */
function mostrarFormularioTarjeta() {
    const metodoSeleccionado = document.querySelector("input[name='metodoPago']:checked").value;
    const seccionTarjeta = document.getElementById("seccion-tarjeta");

    const camposIds = ["numeroTarjeta", "nombreTitular", "fechaExpiracion", "cvv"];

    if (metodoSeleccionado === "TARJETA") {
        seccionTarjeta.classList.remove("oculto");
        camposIds.forEach(id => document.getElementById(id).required = true);
    } else {
        seccionTarjeta.classList.add("oculto");
        camposIds.forEach(id => {
            const input = document.getElementById(id);
            input.required = false;
            input.value = "";
        });
    }
}

/**
 * Obtiene el resumen del carrito desde el servidor para mostrar los montos antes de pagar.
 * Calcula el subtotal, agrega el costo de envío fijo y actualiza el DOM.
 * @async
 * @function cargarResumenPago
 * @returns {Promise<void>}
 */
async function cargarResumenPago() {
    const contenedorItems = document.getElementById("lista-resumen-items");
    const lblSubtotal = document.getElementById("lbl-subtotal");
    const lblTotal = document.getElementById("lbl-total-final");
    const lblCantidad = document.getElementById("lbl-cantidad-productos");
    const btnConfirmar = document.getElementById("btn-confirmar-pedido");

    const COSTO_ENVIO = 100.00;

    try {
        const urlApi = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`;
        const response = await fetch(urlApi);

        if (!response.ok)
            throw new Error("Error al obtener carrito");

        const carrito = await response.json();
        const items = carrito.items || [];

        // Limpiamos los contenedores
        contenedorItems.innerHTML = "";
        let subtotal = 0;
        let cantidadTotalItems = 0;

        if (items.length === 0) {
            contenedorItems.innerHTML = "<p>El carrito esta vacio.</p>";
            if (btnConfirmar)
                btnConfirmar.disabled = true;
            return;
        }

        if (btnConfirmar)
            btnConfirmar.disabled = false;

        // ForEach para el subtotal y la lista de manera visual
        items.forEach(item => {
            const nombre = item.videojuego ? item.videojuego.nombre : "Producto";
            const precio = item.videojuego ? item.videojuego.precio : 0;
            const totalItem = precio * item.cantidad;

            subtotal += totalItem;
            cantidadTotalItems += item.cantidad;

            // Template literal para la fila del producto en el resumen
            const htmlItem = `
                <div class="detalles-item-carrito">
                    <span>${nombre} (x${item.cantidad})</span>
                    <span>$${totalItem.toFixed(2)}</span>
                </div>
            `;
            contenedorItems.insertAdjacentHTML("beforeend", htmlItem);
        });

        const totalFinal = subtotal + COSTO_ENVIO;

        lblCantidad.innerText = `Productos (${cantidadTotalItems}):`;
        lblSubtotal.innerText = `$${subtotal.toFixed(2)}`;
        lblTotal.innerText = `$${totalFinal.toFixed(2)}`;

    } catch (error) {
        console.error(error);
        contenedorItems.innerHTML = "<p class='error-msg'>Error cargando resumen. Intente nuevamente.</p>";

        if (btnConfirmar) {
            btnConfirmar.disabled = true;
            btnConfirmar.innerText = "Error de conexión";
            btnConfirmar.classList.add("btn-disabled");
        }
    }
}

/**
 * Ejecuta la validación final y la transición a la confirmación de pedido.
 * Valida los datos de tarjeta (si aplica), verifica el stock en el servidor
 * y guarda la información necesaria en sessionStorage.
 * @async
 * @function procesarPago
 * @returns {Promise<void>}
 */
async function procesarPago() {
    const metodo = document.querySelector("input[name='metodoPago']:checked")?.value;

    if (!metodo) {
        alert("Por favor selecciona un metodo de pago.");
        return;
    }

    if (metodo === "TARJETA") {
        const numero = document.getElementById("numeroTarjeta").value.trim();
        const nombre = document.getElementById("nombreTitular").value.trim();
        const expiracion = document.getElementById("fechaExpiracion").value.trim();
        const cvv = document.getElementById("cvv").value.trim();

                //validaciones tarjeta
        if (!validarNumeroTarjeta(numero)) {
            alert("Número de tarjeta inválido");
            return;
        }
        if (!validarNombreTitular(nombre)) {
            alert("Nombre titular inválido");
            return;
        }
        if (!validarFechaExpiracion(expiracion)) {
            alert("Fecha expiración inválida");
            return;
        }
        if (!validarCVV(cvv)) {
            alert("CVV inválido");
            return;
        }

        sessionStorage.setItem("datosPagoDetalle", JSON.stringify({numero: numero.slice(-4), nombre: nombre}));
    } else {
        sessionStorage.removeItem("datosPagoDetalle");
    }

    const btnConfirmar = document.getElementById("btn-confirmar-pedido");

    try {
        if (btnConfirmar)
            btnConfirmar.disabled = true;

        // checamos existencias con la api
        const urlValidacion = `${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}/validar-stock`;
        const response = await fetch(urlValidacion);

        if (!response.ok)
            throw new Error("Error en la validación de stock");

        const resultado = await response.json();

        
        if (Array.isArray(resultado) && resultado.length > 0) {
            let mensajeAlerta = "NO SE PUEDE CONTINUAR ️\n\nAlgunos productos superan las existencias disponibles:\n\n";

            mensajeAlerta += resultado.join("\n\n");
            mensajeAlerta += "\n\nPor favor, ajusta las cantidades en tu carrito.";

            alert(mensajeAlerta);

            window.location.href = `${CONTEXT_PATH}/carrito/ver.jsp`;

            return;
        }

    } catch (error) {
        console.error("Error validando stock:", error);
        alert("Ocurrió un error al verificar las existencias. Intente nuevamente.");
        return;
    } finally { 
        if (btnConfirmar)
            btnConfirmar.disabled = false;
    }

    sessionStorage.setItem("metodoPagoSeleccionado", metodo);

    const totalTexto = document.getElementById('lbl-total-final').innerText;
    if (confirm(`¿Confirmar pedido por ${totalTexto}?`)) {
        window.location.href = `${CONTEXT_PATH}/carrito/confirmacion.jsp`;
    }
}


/**
 * Valida que la tarjeta tenga entre 13 y 19 dígitos numéricos.
 * @param {string} numero - Número de tarjeta.
 * @returns {boolean}
 */
function validarNumeroTarjeta(numero) {
    const limpio = numero.replace(/\s+/g, "");
    return /^[0-9]{13,19}$/.test(limpio);
}

/**
 * Valida que el nombre solo contenga letras y espacios.
 * @param {string} nombre - Nombre del titular.
 * @returns {boolean}
 */
function validarNombreTitular(nombre) {
    return /^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$/.test(nombre.trim());
}

/**
 * Valida el formato MM/AA y que la tarjeta no esté vencida.
 * @param {string} fecha - Fecha en formato MM/AA.
 * @returns {boolean}
 */
function validarFechaExpiracion(fecha) {
    fecha = fecha.trim();
    if (!/^\d{2}\/\d{2}$/.test(fecha))
        return false;

    const [mesStr, anioStr] = fecha.split("/");
    const mes = parseInt(mesStr, 10);
    const anio = parseInt(anioStr, 10);

    if (mes < 1 || mes > 12)
        return false;

    const hoy = new Date();
    const mesActual = hoy.getMonth() + 1; 
    const anioActual = hoy.getFullYear() % 100; // Últimos 2 dígitos del año

    if (anio < anioActual)
        return false;

    if (anio === anioActual && mes < mesActual)
        return false;

    return true;
}

/**
 * Valida que el CVV tenga 3 o 4 dígitos.
 * @param {string} cvv - Código de seguridad.
 * @returns {boolean}
 */
function validarCVV(cvv) {
    return /^[0-9]{3,4}$/.test(cvv);
}
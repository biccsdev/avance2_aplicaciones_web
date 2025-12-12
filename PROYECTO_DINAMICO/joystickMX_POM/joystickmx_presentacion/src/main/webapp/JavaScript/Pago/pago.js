/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", () => {
    cargarResumenPago();
    mostrarFormularioTarjeta(); 
});

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

async function cargarResumenPago() {
    const contenedorItems = document.getElementById("lista-resumen-items");
    const lblSubtotal = document.getElementById("lbl-subtotal");
    const lblTotal = document.getElementById("lbl-total-final");
    const lblCantidad = document.getElementById("lbl-cantidad-productos");
    const COSTO_ENVIO = 100.00;

    try {
        const urlApi = "${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}";
        const response = await fetch(urlApi);

        if (!response.ok) throw new Error("Error al obtener carrito");

        const carrito = await response.json();
        const items = carrito.items || [];

        contenedorItems.innerHTML = "";
        let subtotal = 0;
        let cantidadTotalItems = 0;

        if (items.length === 0) {
            contenedorItems.innerHTML = "<p>El carrito esta vacio.</p>";
            return;
        }

        // Generar html por cada producto
        items.forEach(item => {
            const nombre = item.videojuego ? item.videojuego.nombre : "Producto";
            const precio = item.videojuego ? item.videojuego.precio : 0;
            const totalItem = precio * item.cantidad;

            subtotal += totalItem;
            cantidadTotalItems += item.cantidad;

            const htmlItem = `
                <div class="detalles-item-carrito">
                    <span>${nombre} (x${item.cantidad})</span>
                    <span>$${totalItem.toFixed(2)}</span>
                </div>
            `;
            contenedorItems.insertAdjacentHTML("beforeend", htmlItem);
        });

        //calcular total final
        const totalFinal = subtotal + COSTO_ENVIO;

        lblCantidad.innerText = "Productos (${cantidadTotalItems}):";
        lblSubtotal.innerText = "$${subtotal.toFixed(2)}";
        lblTotal.innerText = "$${totalFinal.toFixed(2)}";

    } catch (error) {
        console.error(error);
        contenedorItems.innerHTML = "<p>Error cargando resumen.</p>";
    }
}

// procesamiento del pago, aqui se llaman a las funciones para validar los datos de el metodo de pago tarjeta
function procesarPago() {
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

        if (!validarNumeroTarjeta(numero)) {
            alert("El numero de tarjeta no es valido.");
            return;
        }

        if (!validarNombreTitular(nombre)) {
            alert("El nombre del titular solo puede contener letras y espacios.");
            return;
        }

        if (!validarFechaExpiracion(expiracion)) {
            alert("La fecha de expiración es invalida o la tarjeta está vencida.");
            return;
        }

        if (!validarCVV(cvv)) {
            alert("El CVV debe ser un codigo de 3 o 4 dígitos.");
            return;
        }
    }

    // Confirmación
    if (confirm("¿Confirmar pedido por ${document.getElementById('lbl-total-final').innerText}?")) {
        console.log("Enviando pedido...");

    }
}

// Valida formato 16 dígitos
function validarNumeroTarjeta(numero) {
    const limpio = numero.replace(/\s+/g, "");
    return /^[0-9]{13,19}$/.test(limpio);
}

// Valida nombre
function validarNombreTitular(nombre) {
    return /^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$/.test(nombre.trim());
}

// Valida MM/AA y que no este vencida
function validarFechaExpiracion(fecha) {
    fecha = fecha.trim();
    if (!/^\d{2}\/\d{2}$/.test(fecha)) return false;

    const [mesStr, anioStr] = fecha.split("/");
    const mes = parseInt(mesStr, 10);
    const anio = parseInt(anioStr, 10);
    
    if (mes < 1 || mes > 12) return false;

    // Obtener fecha actual
    const hoy = new Date();
    const mesActual = hoy.getMonth() + 1;
    const anioActual = hoy.getFullYear() % 100;

    if (anio < anioActual) return false;

    if (anio === anioActual && mes < mesActual) return false;

    return true;
}

// Valida CVV
function validarCVV(cvv) {
    return /^[0-9]{3,4}$/.test(cvv);
}


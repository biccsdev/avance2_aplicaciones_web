document.addEventListener("DOMContentLoaded", () => {
    cargarPedidos();
});

async function cargarPedidos() {
    const listaContainer = document.getElementById("lista-pedidos");
    
    if(!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL === "") {
        listaContainer.innerHTML = "<p>Inicia sesión para ver tus pedidos.</p>";
        return;
    }

    try {
        const url = `${CONTEXT_PATH}/resources/pedidos/usuario/${ID_USUARIO_ACTUAL}`;
        const response = await fetch(url);

        if (!response.ok) {
             throw new Error("Error al obtener pedidos");
        }

        const pedidos = await response.json();
        
        listaContainer.innerHTML = "";
        
        if (pedidos.length === 0) {
            listaContainer.innerHTML = "<p>No tienes pedidos realizados.</p>";
            return;
        }

        pedidos.forEach(pedido => {
             const html = renderPedido(pedido);
             listaContainer.insertAdjacentHTML("beforeend", html);
        });

    } catch (error) {
        console.error(error);
        listaContainer.innerHTML = "<p>Error al cargar los pedidos.</p>";
    }
}

function renderPedido(pedido) {
    // La fecha puede venir como arreglo [anio, mes, dia, hora, min, etc] o string
    let fecha = "Fecha desconocida";
    if (Array.isArray(pedido.fechaPedido)) {
        // [2025, 12, 10, 15, 30]
        const [anio, mes, dia] = pedido.fechaPedido;
        fecha = `${dia}/${mes}/${anio}`;
    } else if (pedido.fechaPedido) {
        fecha = new Date(pedido.fechaPedido).toLocaleDateString();
    }

    const total = pedido.totalPagado ? pedido.totalPagado.toFixed(2) : "0.00";
    const id = pedido.idPedido;
    
    const estado = pedido.estadoPedido; // e.g. "ENTREGADO", "PENDIENTE"
    
    const isEntregado = estado === "ENTREGADO" ? "dot-active" : "";
    const isEnviado = estado === "ENVIADO" ? "dot-active" : "";
    const isPendiente = estado === "PENDIENTE" ? "dot-active" : "";
    
    return `
    <li class="order-card">
        <div class="order-info">
            <div class="order-head">
                <h3 class="order-id">Pedido #${id}</h3>
                <span class="order-date text-muted">${fecha}</span>
            </div>
            <div class="order-meta">
                <div class="order-row">
                    <span class="text-muted">total:</span> 
                    <strong class="order-total">$${total}</strong>
                </div>
            </div>
            <div class="order-actions">
                <a href="${CONTEXT_PATH}/pedidos/detalle?id=${id}">
                    <button class="btn btn-primary order-details">Detalles</button>
                </a>
            </div>
        </div>
        <div class="order-status">
            <div class="status-item">
                <span class="status-label">Entregado</span>
                <span class="dot dot-success ${isEntregado}"></span>
            </div>
            <div class="status-item">
                <span class="status-label">Enviado</span>
                <span class="dot dot-info ${isEnviado}"></span>
            </div>
            <div class="status-item">
                <span class="status-label">Pendiente</span>
                <span class="dot dot-warning ${isPendiente}"></span>
            </div>
        </div>
    </li>
    `;
}


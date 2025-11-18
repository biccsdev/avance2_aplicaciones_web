<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <%-- Este es el CSS nuevo y actualizado --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pedidosAdmin.css"> 
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Gestionar Pedidos</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="div-main">
        
        <div class="sidebar-filter">
            <h1 class="pedidos-title">Pedidos</h1>
            <form action="${pageContext.request.contextPath}/admin/pedidos/gestionar" method="GET">
                <input name="filtroId" id="pedido-filtro" class="input-filter" type="text" placeholder="Buscar por ID...">
                <button type="submit" class="btn-filtro">Buscar</button>
            </form>
        </div>

        <div class="main-content">

            <c:if test="${not empty errorMessage}">
                <h4 class="form-message error">${errorMessage}</h4>
            </c:if>
            <c:if test="${not empty successMessage}">
                <h4 class="form-message success">${successMessage}</h4>
            </c:if>

            <%-- Iteramos sobre la lista de pedidos --%>
            <c:forEach var="pedido" items="${listaPedidos}">
                <div class="pedido-item">
                    

                    <div class="pedido-data">
                        <h4>Pedido #${pedido.idPedido}</h4>
                        <h6>Cliente: ${pedido.cliente.nombres} ${pedido.cliente.apellidoPaterno} ${pedido.cliente.apellidoMaterno}</h6>
                        
                        <fmt:formatDate value="${pedido.fechaPedidoAsDate}" pattern="dd/MM/yyyy HH:mm" var="fechaFormateada"/>
                        <span>Fecha: ${fechaFormateada}</span>
                        
                        <fmt:formatNumber value="${pedido.totalPagado}" type="currency" currencySymbol="$" var="totalFormateado"/>
                        <span>Total: ${totalFormateado}</span>

                        <%-- Estructura del estado actualizada --%>
                        <div class="pedido-status">
                            <span class="status-dot estado-${pedido.estadoPedido.toLowerCase()}"></span>
                            <span>${pedido.estadoPedido}</span>
                        </div>
                    </div>

                    <div class="pedido-actions">
                        <a href="${pageContext.request.contextPath}/admin/pedidos/detalle?id=${pedido.idPedido}" class="btn-detalle">
                            Ver Detalles
                        </a>

                        <form class="pedido-action-form" method="POST" action="${pageContext.request.contextPath}/admin/pedidos/gestionar">
                            <input type="hidden" name="action" value="cambiarEstado">
                            <input type="hidden" name="pedidoId" value="${pedido.idPedido}">
                            
                            <select name="nuevoEstado">
                                <option value="PENDIENTE" ${pedido.estadoPedido == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                                <option value="ENVIADO" ${pedido.estadoPedido == 'ENVIADO' ? 'selected' : ''}>Enviado</option>
                                <option value="ENTREGADO" ${pedido.estadoPedido == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
                                <option value="CANCELADO" ${pedido.estadoPedido == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
                            </select>
                            <button type="submit" class="btn-actualizar">Actualizar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>

            <%-- Mensaje si no hay pedidos --%>
            <c:if test="${empty listaPedidos}">
                <div class="pedido-item">
                    <div class="pedido-data">
                        <h4>(No se encontraron pedidos)</h4>
                    </div>
                </div>
            </c:if>

        </div>
    </main>
</body>
</html>


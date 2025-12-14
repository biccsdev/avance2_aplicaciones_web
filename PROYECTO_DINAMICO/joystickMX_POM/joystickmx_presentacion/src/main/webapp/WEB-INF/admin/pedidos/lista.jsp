<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionar Pedidos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pedidos.css"> 
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="orders-container">
        <h1 class="orders-title brand-title">Gestión de Pedidos</h1>

        <form class="form orders-filter" role="search" action="${pageContext.request.contextPath}/admin/pedidos/gestionar" method="GET">
            <label class="form-label" for="filtroNombrePedidos">Cliente</label>
            <input class="input" 
                   id="filtroNombrePedidos" 
                   name="filtroNombrePedidos" 
                   type="text" 
                   placeholder="Buscar por nombre..." 
                   value="${not empty filtroAplicado ? filtroAplicado : ''}" />
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>

        <c:if test="${not empty errorMessage}">
            <div class="alert-box alert-error">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert-box alert-success">${successMessage}</div>
        </c:if>

        <ul class="orders-list" id="lista-pedidos">
            
            <c:if test="${empty listaPedidos}">
                <p>No se encontraron pedidos con ese criterio.</p>
            </c:if>

            <c:forEach var="pedido" items="${listaPedidos}">
                <li class="order-card">
                    
                    <div class="order-info">
                        <div class="order-head">
                            <h3 class="order-id">Pedido #${pedido.idPedido}</h3>
                            
                            <fmt:formatDate value="${pedido.fechaPedidoAsDate}" pattern="dd/MM/yyyy" var="fechaCorta"/>
                            <span class="order-date text-muted">${fechaCorta}</span>
                        </div>

                        <div class="order-meta">
                            <span class="client-name">
                                <strong>Cliente:</strong> ${pedido.cliente.nombres} ${pedido.cliente.apellidoPaterno}
                            </span>

                            <div class="order-row">
                                <span class="text-muted">Total:</span> 
                                <fmt:formatNumber value="${pedido.totalPagado}" type="currency" currencySymbol="$" var="totalFormateado"/>
                                <strong class="order-total">${totalFormateado}</strong>
                            </div>
                        </div>

                        <div class="order-actions">
                            <a href="${pageContext.request.contextPath}/admin/pedidos/detalles?id=${pedido.idPedido}">
                                <button class="btn btn-primary order-details">Ver Detalles</button>
                            </a>
                        </div>
                    </div>

                    <div class="order-status">
                        <form class="admin-controls" method="POST" action="${pageContext.request.contextPath}/admin/pedidos/gestionar">
                            <input type="hidden" name="action" value="cambiarEstado">
                            <input type="hidden" name="pedidoId" value="${pedido.idPedido}">

                            <div class="status-item">
                                <span class="status-label">Estado Actual</span>
                                <c:choose>
                                    <c:when test="${pedido.estadoPedido == 'ENTREGADO'}">
                                        <span class="dot dot-success dot-active"></span>
                                    </c:when>
                                    <c:when test="${pedido.estadoPedido == 'ENVIADO'}">
                                        <span class="dot dot-info dot-active"></span>
                                    </c:when>
                                    <c:when test="${pedido.estadoPedido == 'PENDIENTE'}">
                                        <span class="dot dot-warning dot-active"></span>
                                    </c:when>
                                        <c:when test="${pedido.estadoPedido == 'CANCELADO'}">
                                        <span class="dot dot-danger dot-active"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="dot dot-active"></span>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <select name="nuevoEstado" class="admin-select">
                                <option value="PENDIENTE" ${pedido.estadoPedido == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                                <option value="ENVIADO" ${pedido.estadoPedido == 'ENVIADO' ? 'selected' : ''}>Enviado</option>
                                <option value="ENTREGADO" ${pedido.estadoPedido == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
                                <option value="CANCELADO" ${pedido.estadoPedido == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
                            </select>

                            <button type="submit" class="btn btn-primary">Actualizar</button>
                        </form>
                    </div>

                </li>
            </c:forEach>
        </ul>
    </main>
</body>
</html>




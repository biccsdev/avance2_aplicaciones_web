<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedidos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pedidos.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="orders-container">
        <h1 class="orders-title brand-title">Mis Pedidos</h1>

        <form class="form orders-filter" role="search" action="${pageContext.request.contextPath}/pedidos" method="get">
            <label class="form-label" for="orderNumber">Número de pedido</label>
            <input class="input" id="orderNumber" name="orderNumber" type="text" placeholder="Ej. 68" value="${param.orderNumber}" />
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>

        <ul class="orders-list">
            <%-- Datos dummy de pedidos --%>
            <li class="order-card">
                <div class="order-info">
                    <div class="order-head">
                        <h3 class="order-id">Pedido #67</h3>
                        <span class="order-date text-muted">12/10/25</span>
                    </div>
                    <div class="order-meta">
                        <div class="order-row">
                            <span class="text-muted">total:</span> 
                            <strong class="order-total">$342</strong>
                        </div>
                        <div class="order-row">
                            <span class="order-customer">Sebastian Borquez</span>
                        </div>
                    </div>
                    <div class="order-actions">
                        <a href="${pageContext.request.contextPath}/pedidos/detalle?id=67">
                            <button class="btn btn-primary order-details">Detalles</button>
                        </a>
                        <a href="${pageContext.request.contextPath}/pedidos/resena?id=67">
                            <button class="btn btn-secondary">Dejar Reseña</button>
                        </a>
                    </div>
                </div>
                <div class="order-status">
                    <div class="status-item">
                        <span class="status-label">Entregado</span>
                        <span class="dot dot-success dot-active"></span>
                    </div>
                    <div class="status-item">
                        <span class="status-label">Enviado</span>
                        <span class="dot dot-info"></span>
                    </div>
                    <div class="status-item">
                        <span class="status-label">Pendiente</span>
                        <span class="dot dot-warning"></span>
                    </div>
                </div>
            </li>

            <li class="order-card">
                <div class="order-info">
                    <div class="order-head">
                        <h3 class="order-id">Pedido #68</h3>
                        <span class="order-date text-muted">23/11/25</span>
                    </div>
                    <div class="order-meta">
                        <div class="order-row">
                            <span class="text-muted">total:</span> 
                            <strong class="order-total">$900</strong>
                        </div>
                        <div class="order-row">
                            <span class="order-customer">Sebastian Borquez</span>
                        </div>
                    </div>
                    <div class="order-actions">
                        <a href="${pageContext.request.contextPath}/pedidos/detalle?id=68">
                            <button class="btn btn-primary order-details">Detalles</button>
                        </a>
                    </div>
                </div>
                <div class="order-status">
                    <div class="status-item">
                        <span class="status-label">Entregado</span>
                        <span class="dot dot-success"></span>
                    </div>
                    <div class="status-item">
                        <span class="status-label">Enviado</span>
                        <span class="dot dot-info dot-active"></span>
                    </div>
                    <div class="status-item">
                        <span class="status-label">Pendiente</span>
                        <span class="dot dot-warning"></span>
                    </div>
                </div>
            </li>
        </ul>
    </main>
</body>
</html>


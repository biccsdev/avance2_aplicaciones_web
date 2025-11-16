<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Pedido</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detallePedido.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="order-detail-container">
        <h1 class="order-detail-title">Detalle de Pedido #${param.id}</h1>

        <div class="order-detail-content">
            <section class="order-info-section">
                <h2>Información del Pedido</h2>
                <div class="info-card">
                    <p><strong>Número de Pedido:</strong> #${param.id}</p>
                    <p><strong>Fecha:</strong> 23/11/2025</p>
                    <p><strong>Estado:</strong> <span class="status-badge status-enviado">Enviado</span></p>
                    <p><strong>Método de pago:</strong> PayPal</p>
                    <p><strong>Dirección de envío:</strong></p>
                    <p class="address">Calle Principal #123, Colonia Centro, Ciudad Obregón, Sonora</p>
                </div>
            </section>

            <section class="order-products-section">
                <h2>Productos</h2>
                <div class="products-list">
                    <div class="product-item">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Producto" class="product-img">
                        <div class="product-info">
                            <h3>Call of Duty: Ghosts - PlayStation 4</h3>
                            <p>Cantidad: 1</p>
                            <p class="product-price">$600.00</p>
                        </div>
                    </div>
                    <div class="product-item">
                        <img src="${pageContext.request.contextPath}/imgs/poly.png" alt="Producto" class="product-img">
                        <div class="product-info">
                            <h3>The Battle of Polytopia - PC</h3>
                            <p>Cantidad: 1</p>
                            <p class="product-price">$450.00</p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="order-summary-section">
                <h2>Resumen</h2>
                <div class="summary-card">
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span>$1,050.00</span>
                    </div>
                    <div class="summary-row">
                        <span>Envío:</span>
                        <span>$100.00</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total:</span>
                        <span>$1,150.00</span>
                    </div>
                </div>

                <div class="action-buttons">
                    <a href="${pageContext.request.contextPath}/pedidos/resena?id=${param.id}" class="btn btn-primary">Dejar Reseña</a>
                    <a href="${pageContext.request.contextPath}/pedidos" class="btn btn-secondary">Volver a mis pedidos</a>
                </div>
            </section>
        </div>
    </main>
</body>
</html>


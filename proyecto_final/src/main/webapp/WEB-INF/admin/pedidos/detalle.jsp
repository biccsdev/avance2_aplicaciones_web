<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detallePedidoAdmin.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Detalle de Pedido - Admin</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="order-detail-container">
        <h1 class="order-detail-title">Detalle de Pedido #${param.id}</h1>

        <div class="order-detail-content">
            <section class="order-info-section">
                <h2>Información del Cliente</h2>
                <div class="info-card">
                    <p><strong>Nombre:</strong> Sebastian Borquez</p>
                    <p><strong>Email:</strong> sonic15622@gmail.com</p>
                    <p><strong>Teléfono:</strong> +52 644 123 4567</p>
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
                            <p>Cantidad: 2</p>
                            <p class="product-price">$900.00</p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="order-summary-section">
                <h2>Resumen del Pedido</h2>
                <div class="summary-card">
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span>$1,500.00</span>
                    </div>
                    <div class="summary-row">
                        <span>Envío:</span>
                        <span>$100.00</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total:</span>
                        <span>$1,600.00</span>
                    </div>
                    <div class="summary-row">
                        <span>Estado:</span>
                        <span class="status-badge status-enviado">Enviado</span>
                    </div>
                    <div class="summary-row">
                        <span>Fecha de pedido:</span>
                        <span>23/11/2025</span>
                    </div>
                    <div class="summary-row">
                        <span>Método de pago:</span>
                        <span>PayPal</span>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/admin/pedidos/actualizar-estado" method="post" class="update-status-form">
                    <input type="hidden" name="id" value="${param.id}">
                    <label for="estado">Actualizar Estado:</label>
                    <select id="estado" name="estado" class="input">
                        <option value="pendiente">Pendiente</option>
                        <option value="enviado" selected>Enviado</option>
                        <option value="entregado">Entregado</option>
                        <option value="cancelado">Cancelado</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Actualizar Estado</button>
                </form>

                <a href="${pageContext.request.contextPath}/admin/pedidos" class="btn btn-secondary">Volver a la lista</a>
            </section>
        </div>
    </main>
</body>
</html>


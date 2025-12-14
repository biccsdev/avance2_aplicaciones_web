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
    
    <script>
        const CONTEXT_PATH = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/JavaScript/Pedidos/detallePedido.js" defer></script>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="order-detail-container">
        
        <div class="header-actions" style="margin-bottom: 20px;">
            <a href="${pageContext.request.contextPath}/pedidos" class="btn-back">
                &larr; Volver a mis pedidos
            </a>
        </div>

        <h1 class="order-detail-title">Detalle de Pedido</h1>

        <div class="order-detail-content">
            <section class="order-info-section">
                <h2>Información General</h2>
                <div id="info-pedido-container">
                    <p>Cargando información...</p>
                </div>
            </section>

            <section class="order-products-section">
                <h2>Productos</h2>
                <div class="products-list" id="lista-productos-detalle">
                    <p>Cargando productos...</p>
                </div>
            </section>

            <section class="order-summary-section">
                <h2>Resumen de Costos</h2>
                <div class="summary-card">
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span id="resumen-subtotal">$0.00</span>
                    </div>
                    <div class="summary-row">
                        <span>Envío:</span>
                        <span id="resumen-envio">$0.00</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total:</span>
                        <span id="resumen-total">$0.00</span>
                    </div>
                </div>

                <div class="action-buttons">
                    <a href="#" id="btn-dejar-resena" class="btn btn-primary">Dejar Reseña</a>
                </div>
            </section>
        </div>
    </main>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Pedido #${pedido.idPedido}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detallePedido.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="order-detail-container">
        
        <div class="header-actions" style="margin-bottom: 20px;">
            <a href="${pageContext.request.contextPath}/admin/pedidos/gestionar" class="btn-back">
                &larr; Volver a la lista
            </a>
        </div>

        <h1 class="order-detail-title">Detalle de Pedido #${pedido.idPedido}</h1>

        <div class="order-detail-content">
            
            <section class="order-info-section">
                <h2>Información General</h2>
                <div class="info-card">
                    <p><strong>Número de Pedido:</strong> #${pedido.idPedido}</p>
                    
                    <fmt:formatDate value="${pedido.fechaPedidoAsDate}" pattern="dd/MM/yyyy" var="fechaFormateada"/>
                    <p><strong>Fecha:</strong> ${fechaFormateada}</p>
                    
                    <p><strong>Estado:</strong> 
                        <span class="status-badge status-${pedido.estadoPedido.toLowerCase()}">
                            ${pedido.estadoPedido}
                        </span>
                    </p>
                    
                    <p><strong>Método de pago:</strong> 
                        ${not empty pedido.pago ? pedido.pago.metodoPago : 'No especificado'}
                    </p>
                    
                    <p><strong>Dirección de envío:</strong></p>
                    <p class="address">
                        <c:choose>
                            <c:when test="${not empty pedido.direccionEnvio}">
                                ${pedido.direccionEnvio.calle} #${pedido.direccionEnvio.numero}, ${pedido.direccionEnvio.colonia}
                            </c:when>
                            <c:otherwise>Dirección no disponible</c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </section>

            <c:set var="subtotalCalc" value="0" />
            <c:forEach var="det" items="${pedido.detalles}">
                <c:set var="subtotalCalc" value="${subtotalCalc + (det.precioUnitario * det.cantidad)}" />
            </c:forEach>
            <c:set var="costoEnvio" value="${pedido.totalPagado - subtotalCalc}" />

            <section class="order-products-section">
                <h2>Productos</h2>
                <div class="products-list">
                    
                    <c:forEach var="detalle" items="${pedido.detalles}">
                        <div class="product-item">
                            
                            <c:set var="rutaOriginal" value="${detalle.videojuego.urlImagen}" />
                            
                            <c:choose>
                                <c:when test="${fn:startsWith(rutaOriginal, 'http')}">
                                    <c:set var="imgSrc" value="${rutaOriginal}" />
                                </c:when>
                                
                                <c:otherwise>
                                    <c:if test="${fn:startsWith(rutaOriginal, '/')}">
                                        <c:set var="rutaOriginal" value="${fn:substring(rutaOriginal, 1, fn:length(rutaOriginal))}" />
                                    </c:if>
                                    
                                    <c:if test="${not fn:startsWith(rutaOriginal, 'imgs/')}">
                                        <c:set var="rutaOriginal" value="imgs/${rutaOriginal}" />
                                    </c:if>
                                    
                                    <c:set var="imgSrc" value="${pageContext.request.contextPath}/${rutaOriginal}" />
                                </c:otherwise>
                            </c:choose>

                            <img src="${imgSrc}" 
                                 alt="${detalle.videojuego.nombre}" 
                                 class="product-img" 
                                 onerror="this.src='${pageContext.request.contextPath}/imgs/iconoImagen.png'">
                            
                            <div class="product-info">
                                <h3>${detalle.videojuego.nombre} - ${detalle.videojuego.plataforma}</h3>
                                <p>Cantidad: ${detalle.cantidad}</p>
                                <fmt:formatNumber value="${detalle.precioUnitario}" type="currency" currencySymbol="$" var="precioUnit"/>
                                <p class="product-price">${precioUnit}</p>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty pedido.detalles}">
                        <p>No hay productos en este pedido.</p>
                    </c:if>
                </div>
            </section>

            <section class="order-summary-section">
                <h2>Resumen de Costos</h2>
                <div class="summary-card">
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <fmt:formatNumber value="${subtotalCalc}" type="currency" currencySymbol="$" var="subtotalF"/>
                        <span>${subtotalF}</span>
                    </div>
                    <div class="summary-row">
                        <span>Envío:</span>
                        <fmt:formatNumber value="${costoEnvio}" type="currency" currencySymbol="$" var="envioF"/>
                        <span>${envioF}</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total:</span>
                        <fmt:formatNumber value="${pedido.totalPagado}" type="currency" currencySymbol="$" var="totalF"/>
                        <span>${totalF}</span>
                    </div>
                </div>
            </section>

        </div>
    </main>
</body>
</html>
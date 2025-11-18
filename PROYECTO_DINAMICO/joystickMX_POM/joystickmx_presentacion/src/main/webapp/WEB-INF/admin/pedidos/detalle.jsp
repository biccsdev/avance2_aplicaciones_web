<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %> 

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detallePedidoAdmin.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Detalle de Pedido #${pedido.idPedido}</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="order-detail-container">
        <h1 class="order-detail-title">Detalle de Pedido #${pedido.idPedido}</h1>

        <div class="order-detail-content">
            

            <%-- SECCION DE PRODUCTOS --%>
            <section class="order-products-section">
                <h2>Productos (${fn:length(pedido.detalles)})</h2>
                <div class="products-list">
                    
                    <c:set var="subtotalProductos" value="0" />

                    <c:forEach var="detalle" items="${pedido.detalles}">
                        <c:set var="lineaSubtotal" value="${detalle.precioUnitario * detalle.cantidad}" />
                        <c:set var="subtotalProductos" value="${subtotalProductos + lineaSubtotal}" />

                        <div class="product-item">
                            <img src="${pageContext.request.contextPath}/${detalle.videojuego.urlImagen}" alt="${detalle.videojuego.nombre}" class="product-img">
                            <div class="product-info">
                                <h3>${detalle.videojuego.nombre} - ${detalle.videojuego.plataforma}</h3>
                                <p>Cantidad: ${detalle.cantidad}</p>
                                <fmt:formatNumber value="${detalle.precioUnitario}" type="currency" currencySymbol="$" var="precioUnit"/>
                                <p class="product-price">Precio Unitario: ${precioUnit}</p>
                            </div>

                            <fmt:formatNumber value="${lineaSubtotal}" type="currency" currencySymbol="$" var="subtotalFormateado"/>
                            <p class="product-subtotal">${subtotalFormateado}</p>
                        </div>
                    </c:forEach>
                    
                    <c:if test="${empty pedido.detalles}">
                        <div class="product-item">
                            <div class="product-info">
                                <h3>(No hay productos en este pedido)</h3>
                            </div>
                        </div>
                    </c:if>
                </div>
            </section>

            <section class="order-summary-section">
                <h2>Resumen del Pedido</h2>
                <div class="summary-card">
                    
                    <c:set var="costoEnvio" value="100.00" />
                    <c:set var="totalFinal" value="${subtotalProductos + costoEnvio}" />

                    <div class="summary-row">
                        <span>Subtotal Productos:</span>
                        <fmt:formatNumber value="${subtotalProductos}" type="currency" currencySymbol="$" var="subtotalF"/>
                        <span>${subtotalF}</span>
                    </div>
                    <div class="summary-row">
                        <span>Envío:</span>
                        <fmt:formatNumber value="${costoEnvio}" type="currency" currencySymbol="$" var="envioF"/>
                        <span>${envioF}</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total:</span>
                        <fmt:formatNumber value="${totalFinal}" type="currency" currencySymbol="$" var="totalF"/>
                        <span>${totalF}</span>
                    </div>
                </div>

                <a href="${pageContext.request.contextPath}/admin/pedidos/gestionar" class="btn btn-secondary">Volver a la lista</a>
            </section>
        </div>
    </main>
</body>
</html>


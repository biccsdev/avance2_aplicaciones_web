<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrito.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Carrito de compras</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="main">
        <h1 class="texto-titulo">
            Carrito
            <img src="${pageContext.request.contextPath}/imgs/carrito.png" alt="Carrito de compras" class="icono-carrito">
        </h1>

        <div class="carrito-container">
            <div class="container-productos">
                <div class="productos-header">
                    <span class="col-producto"></span>
                    <span class="col-precio">Precio</span>
                </div>

                <div class="productos-lista">
                    <%-- Datos dummy de productos en carrito --%>
                    <article class="producto-item">
                        <div class="producto-info">
                            <img class="producto-img" src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Call of Duty Ghosts">
                            <div class="producto-meta">
                                <h2 class="producto-nombre">Call of Duty: Ghosts - PlayStation 4</h2>
                                <div class="producto-cantidad">
                                    <button class="btn-menos">-</button>
                                    <span class="qty-num">1</span>
                                    <button class="btn-mas">+</button>
                                </div>
                                <form action="${pageContext.request.contextPath}/carrito/eliminar" method="post" style="display: inline;">
                                    <input type="hidden" name="productoId" value="1">
                                    <button type="submit" class="btn-eliminar">Eliminar del carro</button>
                                </form>
                            </div>
                        </div>
                        <div class="producto-precio">$600.00</div>
                    </article>

                    <article class="producto-item">
                        <div class="producto-info">
                            <img class="producto-img" src="${pageContext.request.contextPath}/imgs/poly.png" alt="Battle of Polytopia">
                            <div class="producto-meta">
                                <h2 class="producto-nombre">The Battle of Polytopia - PC</h2>
                                <div class="producto-cantidad">
                                    <button class="btn-menos">-</button>
                                    <span class="qty-num">1</span>
                                    <button class="btn-mas">+</button>
                                </div>
                                <form action="${pageContext.request.contextPath}/carrito/eliminar" method="post" style="display: inline;">
                                    <input type="hidden" name="productoId" value="2">
                                    <button type="submit" class="btn-eliminar">Eliminar del carro</button>
                                </form>
                            </div>
                        </div>
                        <div class="producto-precio">$450.00</div>
                    </article>
                </div>
            </div>

            <aside class="orden-resumen">
                <h2 class="resumen">
                    <span class="resumen-titulo">Subtotal:</span>
                    <span class="resumen-subtotal">$1050.00</span>
                </h2>
                <p class="resumen-texto">*Se pueden aplicar tarifas extras</p>
                <a href="${pageContext.request.contextPath}/carrito/pago">
                    <button class="btn-pago">Proceder al pago</button>
                </a>
            </aside>
        </div>
    </main>
</body>
</html>


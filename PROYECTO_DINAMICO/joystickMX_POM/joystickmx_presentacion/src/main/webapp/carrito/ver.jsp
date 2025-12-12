<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.usuario}">
    <c:redirect url="/login"/>
</c:if>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrito.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <script src="${pageContext.request.contextPath}/JavaScript/Carrito/carrito.js"></script>
        <title>Carrito de compras</title>

        <script>
            const CONTEXT_PATH = "${pageContext.request.contextPath}";
            const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
        </script>
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
                        <span class="col-producto">Producto</span>
                        <span class="col-precio">Precio</span>
                    </div>

                    <div class="productos-lista" id="lista-productos">
                        <p style="text-align: center; margin-top: 20px;">Cargando productos...</p>
                    </div>
                </div>

                <aside class="orden-resumen">
                    <h2 class="resumen">
                        <span class="resumen-titulo">Subtotal:</span>
                        <span class="resumen-subtotal" id="lbl-subtotal">$0.00</span>
                    </h2>
                    <p class="resumen-texto">*Se pueden aplicar tarifas extras</p>
                    <a href="${pageContext.request.contextPath}/carrito/pago.jsp">
                        <button class="btn-pago">Proceder al pago</button>
                    </a>
                </aside>
            </div>
        </main>


    </body>
</html>
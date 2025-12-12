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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirmacion.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <title>Confirmar pedido</title>

        <script>
        const CONTEXT_PATH = "${pageContext.request.contextPath}";
        const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
        </script>
        <script src="${pageContext.request.contextPath}/JavaScript/Carrito/confirmacion.js" defer></script>
    </head>

    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main class="main">
            <h1 class="texto-titulo">Confirmación</h1>

            <div class="resumen">
                <div class="detalles">
                    <h2 class="detalles-pedido">Detalles del pedido</h2>

                    <div id="lista-productos-resumen" style="margin-bottom: 20px; font-size: 0.9em; color: #ccc;"></div>

                    <div class="detalles-item">
                        <span>Subtotal Productos:</span>
                        <span id="lbl-subtotal">$0.00</span>
                    </div>
                    <div class="detalles-item">
                        <span>Envío:</span>
                        <span id="lbl-envio">$0.00</span>
                    </div>
                    <div class="detalles-item total-item">
                        <span>Total a Pagar:</span>
                        <span id="lbl-total">$0.00</span>
                    </div>

                    <div class="metodo-pago">
                        <h3>Método de pago:</h3>
                        <p id="lbl-metodo-pago">Cargando...</p>
                        <p id="lbl-detalle-pago"  class = "lbl-metodo-pago" "></p>
                    </div>
                </div>

                <div class="confirmacion">
                    <h2 class="confirmar-pedido">CONFIRMAR PEDIDO</h2>

                    <div class="acciones">
                        <button class="btn-aceptar" onclick="confirmarPedidoFinal()">Aceptar</button>
                        <button class="btn-cancelar" onclick="window.history.back()">Rechazar</button>
                    </div>
                </div>
            </div>
        </main>

    </body>
</html>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pago.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <title>Pantalla de pago</title>
        
        <script>
            const CONTEXT_PATH = "${pageContext.request.contextPath}";
            const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
        </script>
        <script src="${pageContext.request.contextPath}/JavaScript/Pago/pago.js" defer></script>
    </head>
    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main class="main">
            <div class="resumen-carrito">
                <div class="detalles">
                    <h2 class="Carro-detalles:">Resumen de compra:</h2>
                    <div id="lista-resumen-items">
                        <p>Cargando detalles...</p>
                    </div>
                </div>
            </div>

            <form id="form-pago" class="payment-form">
                <div class="panel-metodo-pago">
                    <div class="opciones-pago">
                        <h2 class="panel-titulo">Método De Pago</h2>

                        <label class="radio-row paypal-row">
                            <img src="${pageContext.request.contextPath}/imgs/paypal.png" alt="PayPal" class="icono-paypal">
                            <input type="radio" name="metodoPago" value="TRANSFERENCIA" checked onchange="toggleFormularioTarjeta()" />
                        </label>

                        <label class="radio-row">
                            <span>Contra entrega</span>
                            <input type="radio" name="metodoPago" value="CONTRA_PAGO" onchange="toggleFormularioTarjeta()" />
                        </label>

                        <label class="radio-row">
                            <span>Tarjeta de Crédito o Débito</span>
                            <input type="radio" name="metodoPago" value="TARJETA" onchange="toggleFormularioTarjeta()" />
                        </label>

                        <div class="tarjeta-form oculto" id="seccion-tarjeta">
                            <label>
                                <span class="label-small">Número de Tarjeta</span>
                                <input type="text" id="numeroTarjeta" inputmode="numeric" maxlength="19" />
                            </label>
                            <label>
                                <span class="label-small">Nombre del Titular:</span>
                                <input type="text" id="nombreTitular" />
                            </label>
                            <div style="display: flex; gap: 10px;">
                                <label style="flex: 1;">
                                    <span class="label-small">Expiración</span>
                                    <input type="text" id="fechaExpiracion" maxlength="5" placeholder="MM/AA" />
                                </label>
                                <label style="flex: 1;">
                                    <span class="label-small">CVV</span>
                                    <input type="password" id="cvv" inputmode="numeric" maxlength="3" />
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="panel-resumen-total">
                <h2 class="panel-titulo">Cuenta</h2>
                <div class="resumen-row">
                    <span id="lbl-cantidad-productos">Productos (0):</span>
                    <span class="monto" id="lbl-subtotal">$0.00</span>
                </div>
                <div class="resumen-row">
                    <span>Envío:</span>
                    <span class="monto">$100.00</span>
                </div>
                <div class="resumen-row total">
                    <span>Total:</span>
                    <span class="monto" id="lbl-total-final">$0.00</span>
                </div>
                <button type="button" class="btn-confirmar" onclick="procesarPago()">Confirmar Pedido</button>
            </div>
        </main>
    </body>
</html>


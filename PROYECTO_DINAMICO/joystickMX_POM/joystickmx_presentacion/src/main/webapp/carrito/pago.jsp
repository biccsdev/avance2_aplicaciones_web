<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="main">
        <div class="resumen-carrito">
            <div class="detalles">
                <h2 class="Carro-detalles:">Carrito de Compras:</h2>
                <div class="detalles-item-carrito">
                    <span>Call of Duty: Ghosts</span>
                    <span>x1</span>
                </div>
                <div class="detalles-item-carrito">
                    <span>The battle of Polytopia</span>
                    <span>x1</span>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/pedidos/crear" method="post" class="payment-form">
            <div class="panel-metodo-pago">
                <div class="opciones-pago">
                    <h2 class="panel-titulo">Método De Pago</h2>

                    <label class="radio-row paypal-row">
                        <img src="${pageContext.request.contextPath}/imgs/paypal.png" alt="PayPal" class="icono-paypal">
                        <input type="radio" name="metodoPago" value="paypal" checked />
                    </label>

                    <label class="radio-row">
                        <span>Contra entrega</span>
                        <input type="radio" name="metodoPago" value="contra-entrega" />
                    </label>

                    <label class="radio-row">
                        <span>Tarjeta de Crédito o Débito</span>
                        <input type="radio" name="metodoPago" value="tarjeta" />
                    </label>

                    <div class="tarjeta-form">
                        <label>
                            <span class="label-small">Número de Tarjeta</span>
                            <input type="text" name="numeroTarjeta" inputmode="numeric" maxlength="19" />
                        </label>
                        <label>
                            <span class="label-small">Nombre del Titular De La Tarjeta:</span>
                            <input type="text" name="nombreTitular" />
                        </label>
                        <label>
                            <span class="label-small">Fecha de Expiración</span>
                            <input type="text" name="fechaExpiracion" maxlength="5" placeholder="MM/AA" />
                        </label>
                        <label>
                            <span class="label-small">CVV</span>
                            <input type="password" name="cvv" inputmode="numeric" maxlength="3" />
                        </label>
                    </div>
                </div>
            </div>

            <div class="panel-resumen-total">
                <h2 class="panel-titulo">Cuenta</h2>
                <div class="resumen-row">
                    <span>Productos (2):</span>
                    <span class="monto">$1050.00</span>
                </div>
                <div class="resumen-row">
                    <span>Envío:</span>
                    <span class="monto">$100.00</span>
                </div>
                <div class="resumen-row total">
                    <span>Total:</span>
                    <span class="monto">$1150.00</span>
                </div>
                <button type="submit" class="btn-confirmar">Confirmar Pedido</button>
            </div>
        </form>
    </main>
</body>
</html>


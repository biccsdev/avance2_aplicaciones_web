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
    <title>Pedido Confirmado</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mensajeFinal.css"> 
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>

<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="div-main">
        <div class="main-mensaje">
            <img class="icono-carrito" src="${pageContext.request.contextPath}/imgs/carrito.png" alt="Carrito de Compras">

            <h1 class="confirmacion">¡Todo Listo!</h1>

            <p class="subtexto">
                ¡Tu pedido fue Confirmado Exitosamente!
                <br>
                Este se ha añadido a tus "Pedidos".
            </p>

        </div>
    </main>

</body>
</html>
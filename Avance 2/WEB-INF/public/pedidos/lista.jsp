<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedidos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pedidos.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <script>
        const CONTEXT_PATH = "${pageContext.request.contextPath}";
        const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
    </script>
    <script src="${pageContext.request.contextPath}/JavaScript/Pedidos/pedidos.js" defer></script>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="orders-container">
        <h1 class="orders-title brand-title">Mis Pedidos</h1>

        <form class="form orders-filter" role="search" action="${pageContext.request.contextPath}/pedidos" method="get">
            <label class="form-label" for="orderNumber">Número de pedido</label>
            <input class="input" id="orderNumber" name="orderNumber" type="text" placeholder="Ej. 68" value="${param.orderNumber}" />
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>

        <ul class="orders-list" id="lista-pedidos">
            <p>Cargando pedidos...</p>
        </ul>
    </main>
</body>
</html>


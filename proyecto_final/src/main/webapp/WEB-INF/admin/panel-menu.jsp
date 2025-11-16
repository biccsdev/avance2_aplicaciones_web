<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/panelMenu.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Panel de Control</title>
</head>
<body class="app-bg">
    <%-- Header unificado que detecta automáticamente que es admin --%>
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main>
        <h1 class="title">PANEL DE CONTROL</h1>

        <div class="buttons-div">
            <a href="${pageContext.request.contextPath}/admin/productos/gestionar">
                <button class="panel-button">Editar Producto</button>
            </a>
            <a href="${pageContext.request.contextPath}/admin/pedidos">
                <button class="panel-button">Pedidos</button>
            </a>
            <a href="${pageContext.request.contextPath}/admin/productos/crear">
                <button class="panel-button">Crear Producto</button>
            </a>
            <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar">
                <button class="panel-button">Gestionar Usuarios</button>
            </a>
            <a href="${pageContext.request.contextPath}/admin/resenas/moderar">
                <button class="panel-button">Moderar Reseñas</button>
            </a>
        </div>
    </main>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eliminarUsuario.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Eliminar Usuario</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="div-main">
        <div class="sidebar-filter">
            <h1 class="title">Gestionar Usuarios</h1>

            <c:if test="${not empty usuario}">
                <div class="user-item">
                    <div class="user-icon-container">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                    </div>
                    <div class="user-data">
                        <h4>${usuario.nombre}</h4>
                        <h6>${usuario.email}</h6>
                        <div class="user-status">
                            <span class="status-dot ${usuario.activo ? 'active' : 'inactive'}"></span>
                            <span>Cuenta ${usuario.activo ? 'activa' : 'inactiva'}</span>
                        </div>
                    </div>
                    <div class="user-btns">
                        <button class="btn-invisible">Activar</button>
                        <button class="btn-invisible">Desactivar</button>
                        <button class="btn-invisible">Eliminar</button>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="main-users">
            <h4 class="confirmacion-gap">.</h4>
            <h1 class="confirmacion">
                ¿Seguro que deseas Eliminar la cuenta de <strong>${usuario.nombre}</strong>?
                Esta acción es irreversible.
            </h1>

            <a href="${pageContext.request.contextPath}/admin/usuarios/eliminar?id=${usuario.id}">
                <button class="btn-eliminar btn-formateo">ELIMINAR</button>
            </a>
            
            <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar">
                <button class="btn-cancelar btn-formateo">Cancelar</button>
            </a>
        </div>
    </main>
</body>
</html>
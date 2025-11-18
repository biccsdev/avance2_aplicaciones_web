<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarioEliminado.css"> 
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <title>Usuario Eliminado</title>
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
                            <h4>${usuario.nombres}</h4>
                            <h6>${usuario.email}</h6>
                            <div class="user-status">
                                <span class="status-dot"></span> 
                                <span>Cuenta Eliminada</span>
                            </div>
                        </div>
                        <div class="user-btns">
                            <button class="btn-invisible"></button>
                            <button class="btn-invisible"></button>
                            <button class="btn-invisible"></button>
                        </div>
                    </div>
                </c:if>
            </div>

            <div class="main-users">
                <h1 class="confirmacion-gap">Usuario Eliminado</h1>
                <img class="basura-icon" src="${pageContext.request.contextPath}/imgs/basura.png" alt="Usuario Eliminado">
                <h1 class="confirmacion">Usuario Eliminado</h1>

                <a href="${pageContext.request.contextPath}/admin/usuarios">
                    <button class="btn-cancelar btn-formateo">Volver a la lista</button>
                </a>
            </div>
        </main>
    </body>
</html>
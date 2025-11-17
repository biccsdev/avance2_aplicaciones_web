<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gestionarUsuarios.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <title>Gestionar Usuarios</title>
    </head>
    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main class="div-main">
            <div class="sidebar-filter">
                <h1 class="title">Gestionar Usuarios</h1>
                <label class="filter-label" for="nombre-filtro">Nombre:</label>
                <input id="nombre-filtro" class="input-filter" type="text" placeholder="Buscar usuario...">
            </div>

            <div class="main-users">

                <c:if test="${not empty error}">
                    <h4 style="color: red; text-align: center;">${error}</h4>
                </c:if>

                <c:forEach var="usuario" items="${listaUsuarios}">
                    <div class="user-item">
                        <div class="user-icon-container">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                        </div>

                        <div class="user-data">
                            <h4>${usuario.nombres}</h4>
                            <h6>${usuario.email}</h6> 
                            <div class="user-status">
                                <span class="status-dot ${usuario.estadoUsuario == 'ACTIVO' ? 'active' : 'inactive'}"></span>
                                <span>Cuenta ${usuario.estadoUsuario == 'ACTIVO' ? 'activa' : 'inactiva'}</span>
                            </div>
                        </div>

                        <div class="user-actions">

                            <form class="user-action-form" method="POST" action="">
                                <input type="hidden" name="correo" value="${usuario.email}" />

                                <button type="submit" name="action" value="activar"
                                        class="btn-activar"
                                        ${usuario.estadoUsuario != 'INACTIVO' ? 'disabled' : ''}>
                                    Activar
                                </button>

                                <button type="submit" name="action" value="desactivar"
                                        class="btn-desactivar"
                                        ${usuario.estadoUsuario != 'ACTIVO' ? 'disabled' : ''}>
                                    Desactivar
                                </button>

                                <c:choose>
                                    <c:when test="${usuario.estadoUsuario == 'ELIMINADO'}">
                                        <button class="btn-eliminar" disabled>Eliminar</button>
                                    </c:when>

                                    <c:otherwise>
                                        <button type="submit" name="action" value="eliminar" class="btn-eliminar">
                                            Eliminar
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </form>

                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty listaUsuarios}">
                    <div class="user-item">
                        <div class="user-icon-container">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                        </div>
                        <div class="user-data">
                            <h4>(No se encontraron usuarios)</h4>
                            <h6>ejemplo@ejemplo.com</h6>
                        </div>
                    </div>
                </c:if>

            </div>
        </main>
    </body>
</html>
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
                        <h4>${usuario.nombre}</h4>
                        <h6>${usuario.email}</h6> 
                        <div class="user-status">
                            <span class="status-dot ${usuario.activo ? 'active' : 'inactive'}"></span>
                            <span>Cuenta ${usuario.activo ? 'activa' : 'inactiva'}</span>
                        </div>
                    </div>

                    <div class="user-btns">
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%> <%-- PENDIENTE --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        <%-- TODO: Implementar servlets para activar/desactivar --%>
                        
                        <button class="btn-activar">Activar</button>
                        <button class="btn-desactivar">Desactivar</button>
                        
                        <a href="${pageContext.request.contextPath}/admin/usuarios/confirmar-eliminar?id=${usuario.id}">
                            <button class="btn-eliminar">Eliminar</button>
                        </a>
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
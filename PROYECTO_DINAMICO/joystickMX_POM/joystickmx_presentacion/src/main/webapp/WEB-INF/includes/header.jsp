<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- Verificamos si hay alguien logueado --%>
<c:set var="isLoggedIn" value="${not empty sessionScope.usuario}" />
<c:set var="isAdmin" value="${isLoggedIn and sessionScope.rol == 'admin'}" />
<c:set var="isCliente" value="${isLoggedIn and sessionScope.rol == 'cliente'}" />
<c:set var="currentUser" value="${sessionScope.usuario}" />

<header class="main-header">
    <div class="header-left">
        <a href="${contextPath}/home">
            <img class="logo" src="${contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
        </a>
    </div>

    <div class="header-center">
        <span class="search-label">Busqueda de productos</span>
        <form class="search-container" action="${contextPath}/home" method="GET">
            <input class="search-input" type="text" name="busqueda" 
                   placeholder="Buscar videojuego..." 
                   value="<c:out value='${param.busqueda}'/>">
            <button type="submit" class="search-submit-button">
                <img class="search-icon" src="${contextPath}/imgs/lupa.png" alt="Icono de búsqueda">
            </button>
        </form>
    </div>

    <div class="header-right">

        <%-- Usuario invitado --%>
        <c:if test="${not isLoggedIn}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/login">
                    <img src="${contextPath}/imgs/carrito.png" alt="Icono de carrito de compras" class="cart-icon">
                </a>
            </div>
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/login">
                    <button class="admin-button">Pedidos</button>
                </a>
            </div>
            <div class="user-info">
                <span class="margin-label">.</span>
                <a href="${contextPath}/login">
                    <button class="user-profile-button">
                        <div class="user-icon">
                            <img class="icono-usuario" src="${contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                        </div>
                        <span>Ingresar</span>
                    </button>
                </a>
            </div>
        </c:if>

        <%-- Usuario admin --%>
        <c:if test="${isAdmin}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/admin/panel-menu">
                    <button class="admin-button">Panel de admin</button>
                </a>
            </div>
            <div class="user-info">
                <span class="admin-label">ADMIN</span>
                <a href="${contextPath}/admin/perfil">
                    <button class="user-profile-button">
                        <div class="user-icon">
                            <img class="icono-usuario" src="${contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                        </div>
                        <span>${not empty currentUser.nombres ? currentUser.nombres : 'Admin'}</span>
                    </button>
                </a>
            </div>
        </c:if>

        <%-- Usuario cliente --%>
        <c:if test="${isCliente}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/carrito/ver.jsp">
                    <img src="${contextPath}/imgs/carrito.png" alt="Icono de carrito de compras" class="cart-icon">
                </a>
            </div>
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/pedidos">
                    <button class="admin-button">Pedidos</button>
                </a>
            </div>
            <div class="user-info">
                <span class="margin-label">.</span>
                <a href="${contextPath}/perfil/ver.jsp">
                    <button class="user-profile-button">
                        <div class="user-icon">
                            <img class="icono-usuario" src="${contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                        </div>
                        <span>${not empty currentUser.nombres ? currentUser.nombres : 'Usuario'}</span>
                    </button>
                </a>
            </div>
        </c:if>

    </div>
</header>


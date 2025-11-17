<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- Verificamos si hay alguien logueado --%>
<c:set var="isLoggedIn" value="${not empty sessionScope.usuario}" />

<c:set var="isAdmin" value="${isLoggedIn and sessionScope.rol == 'admin'}" />
<c:set var="isCliente" value="${isLoggedIn and sessionScope.rol == 'cliente'}" />

<%-- El usuario actual es el objeto de la sesión --%>
<c:set var="currentUser" value="${sessionScope.usuario}" />

<header class="main-header">
    <div class="header-left">
        <a href="${contextPath}/home">
            <img class="logo" src="${contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
        </a>
    </div>


    <div class="header-center">
        <span class="search-label">Busqueda de productos</span>
        <div class="search-container">
            <input class="search-input" type="text" placeholder="Search">
            <img class="search-icon" src="${contextPath}/imgs/lupa.png" alt="Icono de búsqueda">
        </div>
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
                        <span>Ingresar</span> <%-- Texto cambiado para 'invitado' --%>
                    </button>
                </a>
            </div>
        </c:if>

        <%-- usuario admin --%>
        <c:if test="${isAdmin}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/admin/panel-menu">
                    <button class="admin-button">Panel de admin</button>
                </a>
            </div>
        </c:if>

        <%-- usuario cliente --%>
        <c:if test="${isCliente}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/carrito">
                    <img src="${contextPath}/imgs/carrito.png" alt="Icono de carrito de compras" class="cart-icon">
                </a>
            </div>

            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${contextPath}/pedidos">
                    <button class="admin-button">Pedidos</button>
                </a>
            </div>
        </c:if>

        <%-- Info de perfil Para cliente y admin --%>
        <c:if test="${isLoggedIn}">
            <div class="user-info">
                <c:if test="${isAdmin}">
                    <span class="admin-label">ADMIN</span>
                </c:if>
                <c:if test="${isCliente}">
                    <span class="margin-label">.</span>
                </c:if>

                <c:choose>
                    <%-- Botón Perfil Admin --%>
                    <c:when test="${isAdmin}">
                        <a href="${contextPath}/admin/perfil">
                            <button class="user-profile-button">
                                <div class="user-icon">
                                    <img class="icono-usuario" src="${contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                                </div>
                                <span>${not empty currentUser.nombres ? currentUser.nombres : 'Admin'}</span>
                            </button>
                        </a>
                    </c:when>
                    <%-- Botón Perfil Cliente --%>
                    <c:otherwise> <%-- Es Cliente --%>
                        <a href="${contextPath}/perfil">
                            <button class="user-profile-button">
                                <div class="user-icon">
                                    <img class="icono-usuario" src="${contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                                </div>
                                <span>${not empty currentUser.nombres ? currentUser.nombres : 'Usuario'}</span>
                            </button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>

    </div>
</header>


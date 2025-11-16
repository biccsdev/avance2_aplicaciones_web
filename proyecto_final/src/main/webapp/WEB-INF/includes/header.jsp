<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- 
    Header unificado que se adapta según el tipo de usuario
    Detecta automáticamente si es admin o usuario regular
--%>
<c:set var="isAdmin" value="${not empty sessionScope.adminUser}" />
<c:set var="isUser" value="${not empty sessionScope.usuario}" />
<c:set var="currentUser" value="${isAdmin ? sessionScope.adminUser : sessionScope.usuario}" />

<header class="main-header">
    <div class="header-left">
        <c:choose>
            <c:when test="${isAdmin}">
                <a href="${pageContext.request.contextPath}/admin/panel-menu">
                    <img class="logo" src="${pageContext.request.contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/">
                    <img class="logo" src="${pageContext.request.contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="header-center">
        <span class="search-label">Busqueda de productos</span>
        <div class="search-container">
            <input class="search-input" type="text" placeholder="Search">
            <img class="search-icon" src="${pageContext.request.contextPath}/imgs/lupa.png" alt="Icono de búsqueda">
        </div>
    </div>

    <div class="header-right">
        <%-- Botones específicos para ADMIN --%>
        <c:if test="${isAdmin}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${pageContext.request.contextPath}/admin/panel-menu">
                    <button class="admin-button">Panel de admin</button>
                </a>
            </div>
        </c:if>

        <%-- Botones específicos para USUARIO --%>
        <c:if test="${isUser and not isAdmin}">
            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${pageContext.request.contextPath}/carrito">
                    <img src="${pageContext.request.contextPath}/imgs/carrito.png" alt="Icono de carrito de compras" class="cart-icon">
                </a>
            </div>

            <div class="user-panel-button">
                <span class="margin-label">.</span>
                <a href="${pageContext.request.contextPath}/pedidos">
                    <button class="admin-button">Pedidos</button>
                </a>
            </div>
        </c:if>

        <%-- Información de usuario (común para ambos) --%>
        <div class="user-info">
            <c:if test="${isAdmin}">
                <span class="admin-label">ADMIN</span>
            </c:if>
            <c:if test="${not isAdmin and isUser}">
                <span class="margin-label">.</span>
            </c:if>
            
            <c:choose>
                <c:when test="${isAdmin}">
                    <a href="${pageContext.request.contextPath}/admin/perfil">
                        <button class="user-profile-button">
                            <div class="user-icon">
                                <img class="icono-usuario" src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                            </div>
                            <span>${not empty currentUser.nombre ? currentUser.nombre : 'Admin'}</span>
                        </button>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/perfil">
                        <button class="user-profile-button">
                            <div class="user-icon">
                                <img class="icono-usuario" src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                            </div>
                            <span>${not empty currentUser.nombre ? currentUser.nombre : 'Usuario'}</span>
                        </button>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>


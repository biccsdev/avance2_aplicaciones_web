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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/eliminarUsuario.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/usuarioEliminado.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Gestionar Usuarios</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="div-main">
        <%-- Sidebar con usuario seleccionado --%>
        <div class="sidebar-filter">
            <h1 class="title">Gestionar Usuarios</h1>
            
            <%-- Solo muestra el usuario seleccionado cuando hay estado de confirmación o eliminado --%>
            <c:if test="${param.estado eq 'confirmar' or param.estado eq 'eliminado'}">
                <div class="user-item">
                    <div class="user-icon-container">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                    </div>

                    <div class="user-data">
                        <h4>Sebastian Borquez</h4>
                        <h6>sonic15622@gmail.com</h6>
                        <div class="user-status">
                            <span class="status-dot"></span>
                            <span>Cuenta activa</span>
                        </div>
                    </div>

                    <div class="user-btns">
                        <button class="btn-invisible">Activar</button>
                        <button class="btn-invisible">Desactivar</button>
                        <button class="btn-invisible">Eliminar</button>
                    </div>
                </div>
            </c:if>

            <%-- Filtro de búsqueda solo en estado lista --%>
            <c:if test="${empty param.estado or param.estado eq 'lista'}">
                <label class="filter-label" for="nombre-filtro">Nombre:</label>
                <input id="nombre-filtro" class="input-filter" type="text" placeholder="Buscar usuario...">
            </c:if>
        </div>

        <%-- Estado: LISTA DE USUARIOS --%>
        <c:if test="${empty param.estado or param.estado eq 'lista'}">
            <div class="main-users">
                <%-- Datos dummy de usuarios --%>
                <div class="user-item">
                    <div class="user-icon-container">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                    </div>

                    <div class="user-data">
                        <h4>Sebastian Borquez</h4>
                        <h6>sonic15622@gmail.com</h6>
                        <div class="user-status">
                            <span class="status-dot"></span>
                            <span>Cuenta activa</span>
                        </div>
                    </div>

                    <div class="user-btns">
                        <button class="btn-activar">Activar</button>
                        <button class="btn-desactivar">Desactivar</button>
                        <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar?estado=confirmar&id=1">
                            <button class="btn-eliminar">Eliminar</button>
                        </a>
                    </div>
                </div>

                <div class="user-item">
                    <div class="user-icon-container">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                    </div>

                    <div class="user-data">
                        <h4>Ariel Rodriguez</h4>
                        <h6>ArielLocuras333@hotmail.com</h6>
                        <div class="user-status">
                            <span class="status-dot"></span>
                            <span>Cuenta activa</span>
                        </div>
                    </div>

                    <div class="user-btns">
                        <button class="btn-activar">Activar</button>
                        <button class="btn-desactivar">Desactivar</button>
                        <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar?estado=confirmar&id=2">
                            <button class="btn-eliminar">Eliminar</button>
                        </a>
                    </div>
                </div>

                <div class="user-item">
                    <div class="user-icon-container">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Icono de usuario">
                    </div>

                    <div class="user-data">
                        <h4>Victor Torres</h4>
                        <h6>Yeyo899@Roblox.com</h6>
                        <div class="user-status">
                            <span class="status-dot"></span>
                            <span>Cuenta activa</span>
                        </div>
                    </div>

                    <div class="user-btns">
                        <button class="btn-activar">Activar</button>
                        <button class="btn-desactivar">Desactivar</button>
                        <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar?estado=confirmar&id=3">
                            <button class="btn-eliminar">Eliminar</button>
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

        <%-- Estado: CONFIRMAR ELIMINACIÓN --%>
        <c:if test="${param.estado eq 'confirmar'}">
            <div class="main-users">
                <h4 class="confirmacion-gap">.</h4>
                <h1 class="confirmacion">
                    ¿Seguro que deseas Eliminar la cuenta de este usuario? Esta acción es irreversible
                </h1>

                <a href="${pageContext.request.contextPath}/admin/usuarios/eliminar?id=${param.id}">
                    <button class="btn-eliminar btn-formateo">ELIMINAR</button>
                </a>
                <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar">
                    <button class="btn-cancelar btn-formateo">Cancelar</button>
                </a>
            </div>
        </c:if>

        <%-- Estado: USUARIO ELIMINADO --%>
        <c:if test="${param.estado eq 'eliminado'}">
            <div class="main-users">
                <h1 class="confirmacion-gap">Usuario Eliminado</h1>
                <img class="basura-icon" src="${pageContext.request.contextPath}/imgs/basura.png" alt="Usuario Eliminado">
                <h1 class="confirmacion">Usuario Eliminado</h1>
                <a href="${pageContext.request.contextPath}/admin/usuarios/gestionar">
                    <button class="btn-cancelar btn-formateo">Volver a la lista</button>
                </a>
            </div>
        </c:if>
    </main>
</body>
</html>


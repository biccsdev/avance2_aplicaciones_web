<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mi información</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProfileAdmin.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    </head>

    <body class="app-bg-animated">

        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main class="profile-wrapper">

            <!-- TÍTULO -->
            <h1 class="profile-title-big">Mi informacion</h1>

            <section class="profile-card-simple">

                <form action="${pageContext.request.contextPath}/admin/perfil/actualizar" method="post" class="profile-form">

                    <!-- NOMBRE -->
                    <div class="form-group">
                        <label class="form-label">Nombre</label>
                        <input type="text" name="nombres" class="input-large"
                               value="${sessionScope.usuario.nombres}" required>
                    </div>

                    <!-- APELLIDOS -->
                    <div class="form-group">
                        <label class="form-label">Apellido paterno</label>
                        <input type="text" name="apellidoPaterno" class="input-large"
                               value="${sessionScope.usuario.apellidoPaterno}" required>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Apellido materno</label>
                        <input type="text" name="apellidoPaterno" class="input-large"
                               value="${sessionScope.usuario.apellidoMaterno}" required>
                    </div>

                    <!-- CONTRASEÑA -->
                    <div class="form-group">
                        <label class="form-label">Contraseña</label>
                        <input type="password" name="contrasenia" class="input-large"
                               placeholder="Nueva contraseña (opcional)">
                    </div>

                    <!-- TELEFONO -->
                    <div class="form-group">
                        <label class="form-label">Teléfono</label>
                        <input type="tel" name="telefono" class="input-large"
                               value="${sessionScope.usuario.telefono}">
                    </div>

                    <!-- CALLE -->
                    <div class="form-group">
                        <label class="form-label">Calle</label>
                        <input type="text" name="calle" class="input-large"
                               value="${sessionScope.usuario.direccion.calle}">
                    </div>

                    <!-- NÚMERO + COLONIA -->
                    <div class="form-row-2">
                        <div class="form-group">
                            <label class="form-label">Número exterior*</label>
                            <input type="text" name="numero" class="input-large"
                                   value="${sessionScope.usuario.direccion.numero}">
                        </div>

                        <div class="form-group">
                            <label class="form-label">Colonia*</label>
                            <input type="text" name="colonia" class="input-large"
                                   value="${sessionScope.usuario.direccion.colonia}">
                        </div>
                    </div>

                    <!-- MENSAJES -->
                    <c:if test="${not empty error}">
                        <div class="error-text">${error}</div>
                    </c:if>

                    <c:if test="${not empty success}">
                        <div class="success-text">${success}</div>
                    </c:if>

                    <!-- BOTÓN -->
                    <div class="form-actions-center">
                        <button type="submit" class="btn-black-big">Guardar</button>
                    </div>

                </form>
            </section>
        </main>

    </body>
</html>

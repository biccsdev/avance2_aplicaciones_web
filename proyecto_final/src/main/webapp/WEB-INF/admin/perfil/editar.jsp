<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProfileAdmin.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="profile-container container-center">
        <h1 class="brand-title profile-title">Editar Perfil</h1>

        <section class="profile-card surface-glass shadow-lg rounded-lg">
            <form action="${pageContext.request.contextPath}/admin/perfil/actualizar" method="post" class="edit-profile-form">
                <div class="form-group">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" id="nombre" name="nombre" class="input" 
                           value="${not empty sessionScope.adminUser ? sessionScope.adminUser.nombre : 'Sebastian'}" required>
                </div>

                <div class="form-group">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" id="apellido" name="apellido" class="input" 
                           value="${not empty sessionScope.adminUser ? sessionScope.adminUser.apellido : 'Borquez'}" required>
                </div>

                <div class="form-group">
                    <label for="email" class="form-label">Correo Electrónico</label>
                    <input type="email" id="email" name="email" class="input" 
                           value="${not empty sessionScope.adminUser ? sessionScope.adminUser.email : 'sebastian.borquez252115@potros.itson.edu.mx'}" required>
                </div>

                <div class="form-group">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" class="input" 
                           value="${not empty sessionScope.adminUser ? sessionScope.adminUser.telefono : '+52 644 123 4567'}">
                </div>

                <div class="form-section">
                    <h3 class="section-title">Cambiar Contraseña (Opcional)</h3>
                    <div class="form-group">
                        <label for="passwordActual" class="form-label">Contraseña Actual</label>
                        <input type="password" id="passwordActual" name="passwordActual" class="input" 
                               placeholder="Dejar en blanco para no cambiar">
                    </div>

                    <div class="form-group">
                        <label for="passwordNueva" class="form-label">Nueva Contraseña</label>
                        <input type="password" id="passwordNueva" name="passwordNueva" class="input">
                    </div>

                    <div class="form-group">
                        <label for="passwordConfirmar" class="form-label">Confirmar Nueva Contraseña</label>
                        <input type="password" id="passwordConfirmar" name="passwordConfirmar" class="input">
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div class="error-text">${error}</div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="success-text">${success}</div>
                </c:if>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    <a href="${pageContext.request.contextPath}/admin/perfil" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </section>
    </main>
</body>
</html>


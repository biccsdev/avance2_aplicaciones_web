<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProfile.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="profile-container container-center">
        <h1 class="brand-title profile-title">Editar Perfil</h1>

        <section class="profile-card surface-glass shadow-lg rounded-lg">
            <form action="${pageContext.request.contextPath}/perfil/actualizar" method="post" class="edit-profile-form">
                <div class="form-section">
                    <h3 class="section-title">Información Personal</h3>
                    
                    <div class="form-group">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="input" 
                               value="${not empty sessionScope.usuario ? sessionScope.usuario.nombre : 'Sebastian'}" required>
                    </div>

                    <div class="form-group">
                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" id="apellido" name="apellido" class="input" 
                               value="${not empty sessionScope.usuario ? sessionScope.usuario.apellido : 'Borquez'}" required>
                    </div>

                    <div class="form-group">
                        <label for="email" class="form-label">Correo Electrónico</label>
                        <input type="email" id="email" name="email" class="input" 
                               value="${not empty sessionScope.usuario ? sessionScope.usuario.email : 'sebastian.borquez252115@potros.itson.edu.mx'}" required>
                    </div>

                    <div class="form-group">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="tel" id="telefono" name="telefono" class="input" 
                               value="${not empty sessionScope.usuario ? sessionScope.usuario.telefono : '+52 644 123 4567'}">
                    </div>
                </div>

                <div class="form-section">
                    <h3 class="section-title">Dirección de Envío</h3>
                    
                    <div class="form-group">
                        <label for="calle" class="form-label">Calle</label>
                        <input type="text" id="calle" name="calle" class="input" 
                               value="Calle Principal" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="numeroExterior" class="form-label">Número Exterior</label>
                            <input type="text" id="numeroExterior" name="numeroExterior" class="input" 
                                   value="123" required>
                        </div>

                        <div class="form-group">
                            <label for="colonia" class="form-label">Colonia</label>
                            <input type="text" id="colonia" name="colonia" class="input" 
                                   value="Centro" required>
                        </div>
                    </div>
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
                    <a href="${pageContext.request.contextPath}/perfil" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </section>
    </main>
</body>
</html>


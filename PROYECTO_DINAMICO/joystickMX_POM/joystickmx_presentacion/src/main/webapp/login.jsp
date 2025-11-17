
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - JoystickMX</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/icono_app.png" type="image/x-icon">
</head>
<body>

    <%-- <jsp:include page="/WEB-INF/includes/header.jsp" /> --%>

    <main class="login-container">
        
        <div class="login-logo">
            <img src="${pageContext.request.contextPath}/imgs/logo.png" alt="Logo JoystickMX">
        </div>

        <form class="login-form" action="${pageContext.request.contextPath}/login" method="POST">
            <h2>Iniciar Sesión</h2>
            
            <c:if test="${not empty error}">
                <div class="error-message">
                    <p>${error}</p>
                </div>
            </c:if>

            <div class="form-group">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required autocomplete="email">
            </div>

            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit" class="login-button">Iniciar sesion</button>
            
            <p>¿No tienes una cuenta? <a href="register.jsp">Regístrate aquí</a></p>
        </form>
    </main>

</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">
    <title>Iniciar sesión · JoystickMX</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>

<body class="app-bg">
    <div class="login-page">
        <%-- Header simple para login (sin sesión) --%>
        <header class="brand-header center">
            <div class="header-left">
                <img class="logo" src="${pageContext.request.contextPath}/imgs/logo.png" alt="Logo de JoystickMX">
            </div>
        </header>

        <main>
            <div class="container-center welcome-text">
                <h1 class="brand-title" aria-label="JoystickMX">Bienvenido Gamer!</h1>
            </div>
            
            <section class="container-center login-card">
                <div class="auth-card">
                    <div class="divider" aria-hidden="true"></div>
                    <h2 class="auth-title">Inicia Sesión</h2>

                    <c:if test="${not empty error}">
                        <div class="error-text">${error}</div>
                    </c:if>

                    <form class="form" action="${pageContext.request.contextPath}/login" method="post" novalidate>
                        <div>
                            <label class="form-label" for="email">Correo electrónico</label>
                            <input class="input" id="email" name="email" type="email" autocomplete="email"
                                   placeholder="Ingresa tu correo electrónico" required>
                        </div>

                        <div>
                            <label class="form-label" for="password">Contraseña</label>
                            <input class="input" id="password" name="password" type="password" autocomplete="current-password"
                                   placeholder="••••••••" required>
                        </div>

                        <button class="btn btn-primary mt-4" type="submit">Ingresar</button>

                        <p class="text-center text-muted mt-4">
                            ¿No tienes cuenta?
                            <a class="link" href="${pageContext.request.contextPath}/register">Registrarse</a>
                        </p>
                    </form>
                </div>
            </section>
        </main>
    </div>
</body>
</html>


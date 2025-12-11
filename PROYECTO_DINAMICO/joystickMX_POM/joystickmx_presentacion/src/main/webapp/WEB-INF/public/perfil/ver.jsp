<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="profile-container container-center">
        <h1 class="brand-title profile-title">Perfil</h1>

        <section class="profile-card surface-glass shadow-lg rounded-lg">
            <div class="profile-row">
                <div class="profile-avatar">
                    <div class="avatar-circle">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Avatar" class="avatar-img">
                    </div>
                </div>

                <div class="profile-info">
                    <h2 class="profile-name">
                        ${not empty sessionScope.usuario ? sessionScope.usuario.nombre : 'Sebastian'}<br>
                        ${not empty sessionScope.usuario ? sessionScope.usuario.apellido : 'Borquez'}
                    </h2>
                    <div class="profile-email">
                        ${not empty sessionScope.usuario ? sessionScope.usuario.email : 'sebastian.borquez252115@potros.itson.edu.mx'}
                    </div>

                    <div class="profile-status">
                        <span class="status-dot"></span>
                        <span class="status-label">Cuenta activa</span>
                    </div>
                </div>

                <a href="${pageContext.request.contextPath}/perfil/editar">
                    <button class="edit-button" aria-label="Editar perfil">
                        <img src="${pageContext.request.contextPath}/imgs/icono_edit_user.png" alt="Editar" class="edit-icon">
                    </button>
                </a>
            </div>

            <div class="profile-actions">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="btn btn-danger logout-button">Cerrar Sesión</button>
                </form>
            </div>
        </section>
    </main>
</body>
</html>


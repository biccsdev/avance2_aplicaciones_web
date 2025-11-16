<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- ¡Esto ya funcionará! --%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JoystickMX - Tu tienda de videojuegos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/icono_app.png" type="image/x-icon">
    </head>
    <body>

        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <main class="main-container">
            <section class="hero-section">
                <h1>Bienvenido a JoystickMX</h1>
                <p>Tu próxima aventura comienza aquí. Encuentra los mejores títulos a precios increíbles.</p>
            </section>

            <h2>Catálogo de Productos</h2>

            <section class="product-grid">
                <c:choose>
                    <%-- errorES enviados por el Servlet --%>
                    <c:when test="${not empty error}">
                        <div class="error-message">
                            <p>${error}</p>
                        </div>
                    </c:when>

                    <c:when test="${empty videojuegos}">
                        <p>No hay videojuegos disponibles en este momento.</p>
                    </c:when>

                    <c:otherwise>
                        <c:forEach var="juego" items="${videojuegos}">
                            <article class="product-card">
                                <a href="#"> <%-- Aquí iría un link al Servlet de Detalles --%>
                                    <img src="${pageContext.request.contextPath}${juego.urlImagen}" alt="Portada de ${juego.nombre}">
                                    <div class="product-info">
                                        <h3>${juego.nombre}</h3>
                                        <p class="product-platform">${juego.plataforma}</p>
                                        <p class="product-price">$${String.format("%.2f", juego.precio)}</p>
                                    </div>
                                </a>
                            </article>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />

    </body>
</html>
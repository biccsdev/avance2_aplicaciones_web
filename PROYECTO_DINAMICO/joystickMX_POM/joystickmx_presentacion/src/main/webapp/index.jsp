<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover">
        <title>JoystickMX - Tu tienda de videojuegos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    </head>
    <body>

        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <main class="main-container">
            <%-- Esta sección de "héroe" es un agregado, la dejamos --%>
            <section class="hero-section">
                <h1>Bienvenido a JoystickMX</h1>
                <p>Tu próxima aventura comienza aquí. Encuentra los mejores títulos a precios increíbles.</p>
            </section>

            <h2>Catálogo de Productos</h2>

            <%-- 
              Aquí empieza la corrección. 
              Cambiamos "section class='product-grid'" por "div class='catalogo'" 
              para que coincida con index.html.
            --%>
            <div class="catalogo">
                <c:choose>
                    <%-- Caso de Error --%>
                    <c:when test="${not empty error}">
                        <div class="error-message">
                            <p>${error}</p>
                        </div>
                    </c:when>

                    <%-- Caso de "No hay productos" --%>
                    <c:when test="${empty videojuegos}">
                        <p>No hay videojuegos disponibles en este momento.</p>
                    </c:when>

                    <%-- Caso Principal: Mostrar productos --%>
                    <c:otherwise>
                        <%-- 
                          1. Agregamos el UL que faltaba, 
                          para que coincida con index.html
                        --%>
                        <ul class="videojuegos-lista">

                            <c:forEach var="juego" items="${videojuegos}">
                                <%-- 
                                  2. Todo el bloque dentro del forEach ahora es 
                                  idéntico a un "li" de index.html 
                                --%>
                                <li class="videojuego-item">
                                    <div class="videojuego">
                                        <div class="videojuego-imagen">
                                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}${juego.urlImagen}" alt="Portada de ${juego.nombre}">
                                        </div>
                                        <div class="videojuego-info">
                                            <h3 class="videojuego-nombre">${juego.nombre}</h3>

                                            <%-- 3. Usamos H2 para el precio, como en index.html --%>
                                            <h2 class="videojuego-precio">$${String.format("%.2f", juego.precio)}</h2>

                                            <%-- 4. Agregamos el botón que faltaba en tu JSP --%>
                                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>

                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />

    </body>
</html>
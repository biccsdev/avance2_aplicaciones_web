<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="joystickmx.itson.DTO.VideojuegoDTO"%>
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
    <body class="app-bg-animated">

        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <main class="grid-container">
            <%-- 
              Aquí empieza la corrección. 
              Cambiamos "section class='product-grid'" por "div class='catalogo'" 
              para que coincida con index.html.
            --%>
            <div class="videojuego-container">
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
                        <div class="filtro-busqueda">
                            <div class="filtro-seccion-rango">
                                <span class="filtro-titulo">Precio</span>
                                <span class="filtro-cantidad">$0-3000</span>
                            </div>

                            <input class="filtro-rango" type="range" min="0" max="3000" value="3000" aria-label="Rango de precio">

                            <select class="filtro-select" aria-label="Plataforma">
                                <option selected>Plataforma</option>
                                <option>Xbox</option>
                                <option>PlayStation</option>
                                <option>Nintendo</option>
                            </select>


                            <select class="filtro-select" aria-label="Plataforma">
                                <option selected>Género</option>
                                <option>Accion y aventuras</option>
                                <option>Terror</option>
                                <option>Mundo abierto</option>
                                <option>Carreras</option>
                            </select>

                        </div>
                        <%-- 
                          1. Agregamos el UL que faltaba, 
                          para que coincida con index.html
                        --%>
                        <ul class="videojuego-lista">

                            <c:forEach var="juego" items="${videojuegos}">
                                <%-- 
                                  2. Todo el bloque dentro del forEach ahora es 
                                  idéntico a un "li" de index.html 
                                --%>
                                <li class="videojuego-item">
                                    <div class="videojuego">
                                        <div class="videojuego-imagen">
                                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}${juego.getUrlImagen()}" alt="Portada de ${juego.nombre}">
                                        </div>
                                        <div class="videojuego-info">
                                            <h3 class="videojuego-nombre">${juego.getNombre()}</h3>

                                            <%-- 3. Usamos H2 para el precio, como en index.html --%>
                                            <h2 class="videojuego-precio">$${String.format("%.2f", juego.getPrecio())}</h2>

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
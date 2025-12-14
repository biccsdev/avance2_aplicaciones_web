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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    </head>
    <body class="app-bg-animated">

        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <main class="grid-container">

            <div class="videojuego-container">
                <c:choose>
                    <%-- Caso de Error --%>
                    <c:when test="${not empty error}">
                        <div class="error-message">
                            <p>${error}</p>
                        </div>
                    </c:when>

                    <c:otherwise>

                        <form action="${pageContext.request.contextPath}/home" method="GET" class="filtro-busqueda">


                            <input type="hidden" name="busqueda" value="${param.busqueda}">

                            <div class="filtro-seccion-rango">
                                <span class="filtro-titulo">Filtros</span>
                            </div>

                            <select name="precioMin" class="filtro-select" aria-label="Precio Mínimo">
                                <option value="" selected>Min. Precio</option>
                                <option value="0" ${param.precioMin == '0' ? 'selected' : ''}>$0</option>
                                <option value="200" ${param.precioMin == '200' ? 'selected' : ''}>$200</option>
                                <option value="400" ${param.precioMin == '400' ? 'selected' : ''}>$400</option>
                                <option value="600" ${param.precioMin == '600' ? 'selected' : ''}>$600</option>
                                <option value="800" ${param.precioMin == '800' ? 'selected' : ''}>$800</option>
                                <option value="1000" ${param.precioMin == '1000' ? 'selected' : ''}>$1000</option>
                                <option value="2000" ${param.precioMin == '2000' ? 'selected' : ''}>$2000</option>
                            </select>

                            <select name="precioMax" class="filtro-select" aria-label="Precio Máximo">
                                <option value="" selected>Max. Precio</option>
                                <option value="200" ${param.precioMax == '200' ? 'selected' : ''}>$200</option>
                                <option value="400" ${param.precioMax == '400' ? 'selected' : ''}>$400</option>
                                <option value="600" ${param.precioMax == '600' ? 'selected' : ''}>$600</option>
                                <option value="800" ${param.precioMax == '800' ? 'selected' : ''}>$800</option>
                                <option value="1000" ${param.precioMax == '1000' ? 'selected' : ''}>$1000</option>
                                <option value="2000" ${param.precioMax == '2000' ? 'selected' : ''}>$2000</option>
                                <option value="5000" ${param.precioMax == '5000' ? 'selected' : ''}>Más de $2000</option>
                            </select>

                            <select name="plataforma" class="filtro-select" aria-label="Plataforma">
                                <option value="" selected>Todas las plataformas</option>
                                <option value="Xbox" ${param.plataforma == 'Xbox' ? 'selected' : ''}>Xbox</option>
                                <option value="PlayStation" ${param.plataforma == 'PlayStation' ? 'selected' : ''}>PlayStation</option>
                                <option value="Nintendo" ${param.plataforma == 'Nintendo' ? 'selected' : ''}>Nintendo</option>
                                <option value="PC" ${param.plataforma == 'PC' ? 'selected' : ''}>PC</option>
                            </select>

                            <select name="categoria" class="filtro-select" aria-label="Categoría">
                                <option value="" selected>Todas las categorías</option>
                                <option value="1" ${param.categoria == '1' ? 'selected' : ''}>Acción</option>
                                <option value="2" ${param.categoria == '2' ? 'selected' : ''}>Acción y Aventuras</option>
                                <option value="3" ${param.categoria == '3' ? 'selected' : ''}>Mundo Abierto</option>
                                <option value="4" ${param.categoria == '4' ? 'selected' : ''}>Carreras</option>
                                <option value="5" ${param.categoria == '5' ? 'selected' : ''}>Survival Horror</option>
                                <option value="6" ${param.categoria == '6' ? 'selected' : ''}>FPS (Disparos)</option>
                                <option value="7" ${param.categoria == '7' ? 'selected' : ''}>RPG (Rol por Turnos)</option>
                            </select>

                            <button type="submit" class="btn-carrito btn-dark" style="width: 100%; margin-top: 10px;">Aplicar Filtros</button>
                        </form>

                        <ul class="videojuego-lista">
                            <c:if test="${empty videojuegos}">
                                <li class="videojuego-item" style="width: 100%; text-align: center; color: white;">
                                    <h3 class ="mensaje-tuki">No se encontraron videojuegos con esos criterios.</h3>
                                </li>
                            </c:if>

                            <c:forEach var="juego" items="${videojuegos}" varStatus="estado">
                                <c:choose>
                                    <c:when test="${sessionScope.rol == 'admin'}">
                                        <li class="videojuego-item">
                                            <div class="videojuego">
                                                <div class="videojuego-imagen">
                                                    <img class="videojuego-imagen" src="${pageContext.request.contextPath}${juego.getUrlImagen()}" alt="Portada de ${juego.nombre}">
                                                </div>
                                                <div class="videojuego-info">
                                                    <h3 class="videojuego-nombre">${String.format("%s (%s)", juego.getNombre(), juego.getPlataforma())}</h3>
                                                    <h2 class="videojuego-precio">$${String.format("%.2f", juego.getPrecio())}</h2>
                                                    <div class="botones-admin">
                                                        <form action="${pageContext.request.contextPath}/admin/productos/editar" method="get">
                                                            <button type="submit" class="btn-carrito btn-dark btn-admin" name="idVideojuego" value="${juego.getIdVideojuego()}"> Editar </button>
                                                        </form>
                                                        <form name="moderar" action="${pageContext.request.contextPath}/admin/moderar" method="get">
                                                            <button class="btn-carrito btn-dark btn-admin" name="idVideojuego" value="${juego.getIdVideojuego().toString()}">Reseñas</button>
                                                        </form>
                                                    </div>

                                                </div>
                                            </div>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li 
                                            id="videojuego-item" 
                                            class="videojuego-item" 
                                            onmouseover="this.style.cursor='pointer';" 
                                            onmouseout="this.style.cursor='default';"
                                            onclick ="document.getElementById('videojuego-form-${estado.index}').submit(); return false;"
                                        >
                                            <form id="videojuego-form-${estado.index}" name="videojuego-form" action="${pageContext.request.contextPath}/videojuego" method="get">
                                                <input id="videojuego-sumit" type="hidden" name="idVideojuego" value="${juego.getIdVideojuego()}">
                                            </form>
                                            <div class="videojuego">
                                                <div class="videojuego-imagen">
                                                    <img class="videojuego-imagen" src="${pageContext.request.contextPath}${juego.getUrlImagen()}" alt="Portada de ${juego.nombre}">
                                                </div>
                                                <div class="videojuego-info">
                                                    <h3 class="videojuego-nombre">${String.format("%s (%s)", juego.getNombre(), juego.getPlataforma())}</h3>
                                                    <h2 class="videojuego-precio">$${String.format("%.2f", juego.getPrecio())}</h2>
                                                </div>
                                            </div>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />

    </body>
</html>
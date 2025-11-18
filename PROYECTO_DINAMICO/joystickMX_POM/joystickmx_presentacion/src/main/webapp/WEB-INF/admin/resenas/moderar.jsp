<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/moderarResenas.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Moderar Reseñas</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="reviews-container">
        <h1 class="reviews-title brand-title">Moderar Reseñas</h1>

        <div class="reviews-filters">
            <form action="${pageContext.request.contextPath}/moderar" method="get" class="filter-form">
                <div class="filter-group">
                    <label for="producto">Filtrar por videojuego</label>
                    <input type="text" id="producto" name="nombreVideojuego" class="input" placeholder="Nombre del videojuego...">
                </div>
                <button type="submit" class="btn btn-primary">Filtrar por nombre</button>
                
            </form>
            <form action="${pageContext.request.contextPath}/moderar" method="get" class="filter-form">
                <div class="filter-group">
                    <label></label>
                    <label for="calificacion">Calificación:</label>
                    <select id="calificacion" name="calificacion" class="input">
                        <option value="">Todas</option>
                        <option value="5">5 estrellas</option>
                        <option value="4.5">4.5 estrellas</option>
                        <option value="4">4 estrellas</option>
                        <option value="3.5">3.5 estrellas</option>
                        <option value="3">3 estrellas</option>
                        <option value="2.5">2.5 estrellas</option>
                        <option value="2">2 estrellas</option>
                        <option value="1.5">1.5 estrellas</option>
                        <option value="1">1 estrella</option>
                        <option value="0.5">0.5 estrella</option>
                        <option value="0">0 estrella</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Filtrar por calificación</button>
            </form>
        </div>
        <c:if test="${not empty mensaje}">
            <div class="success-message">${mensaje}</div>
        </c:if>
        <div class="reviews-list">
            <%-- Datos dummy de reseñas --%>
            <c:forEach var="resena" items="${resenas}">
                <div class="review-card">
                    <div class="review-header">
                        <div class="review-product-info">
                            <img src="${pageContext.request.contextPath}${resena.getUrlImagen()}" alt="Producto" class="review-product-img">
                            <div>
                                <h3 class="review-product-name">${resena.getNombreVideojuego()}</h3>
                                <div class="review-rating">
                                    <span class="stars">★★★★★</span>
                                    <span class="rating-number">${resena.getResena().getCalificacion()}</span>
                                </div>
                            </div>
                        </div>
                        <div class="review-date">
                            <span class="text-muted">${
                                String.format("%d/%d/%d", 
                                  resena.getResena().getFechaResena().getDayOfMonth(), 
                                  resena.getResena().getFechaResena().getMonthValue(), 
                                  resena.getResena().getFechaResena().getYear())
                                }
                            </span>
                        </div>
                    </div>
                    <div class="review-body">
                        <div class="review-author">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Usuario" class="author-avatar">
                            <span class="author-name">${resena.getNombreJugador()}</span>
                        </div>
                        <p class="review-text">
                            ${resena.getResena().getComentario()}
                        </p>
                    </div>
                    <div class="review-actions">
                        <form action="${pageContext.request.contextPath}/admin/resenas/eliminar" method="post" style="display: inline;">
                            <input type="hidden" name="id" value="1">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</body>
</html>
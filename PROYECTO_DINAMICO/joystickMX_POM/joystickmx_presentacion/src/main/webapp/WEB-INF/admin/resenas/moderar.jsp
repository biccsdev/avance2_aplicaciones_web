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
            <form action="${pageContext.request.contextPath}/admin/moderar" method="get" class="filter-form">
                <div class="filter-group">
                    <label for="producto">Filtrar por videojuego</label>
                    <input type="text" id="producto" name="nombreVideojuego" class="input" placeholder="Nombre del videojuego...">
                </div>
                <button type="submit" class="btn btn-primary">Filtrar por nombre</button>
            </form>
            
            <form action="${pageContext.request.contextPath}/admin/moderar" method="get" class="filter-form">
                <div class="filter-group">
                    <label for="calificacion">Calificación:</label>
                    <select id="calificacion" name="calificacion" class="input">
                        <option value="">Todas</option>
                        <option value="5">5 estrellas</option>
                        <option value="4">4 estrellas</option>
                        <option value="3">3 estrellas</option>
                        <option value="2">2 estrellas</option>
                        <option value="1">1 estrella</option>
                        <option value="0">0 estrellas</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Filtrar por calificación</button>
            </form>
        </div>

        <c:if test="${not empty mensaje}">
            <div class="success-message" style="background: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 20px; text-align: center;">
                ${mensaje}
            </div>
        </c:if>

        <div class="reviews-list">
            <c:if test="${empty resenas}">
                <p style="text-align: center; color: white;">No se encontraron reseñas.</p>
            </c:if>

            <c:forEach var="resena" items="${resenas}">
                <div class="review-card">
                    <div class="review-header">
                        <div class="review-product-info">
                            <c:set var="imgUrl" value="${resena.getUrlImagen()}"/>
                            <c:if test="${not empty imgUrl and not imgUrl.startsWith('http') and not imgUrl.startsWith('/')}">
                                <c:set var="imgUrl" value="/${imgUrl}"/>
                            </c:if>
                            
                            <img src="${pageContext.request.contextPath}${imgUrl}" 
                                 alt="Producto" 
                                 class="review-product-img"
                                 onerror="this.src='${pageContext.request.contextPath}/imgs/iconoImagen.png'">
                            
                            <div>
                                <h3 class="review-product-name">${resena.getNombreVideojuego()}</h3>
                                <div class="review-rating">
                                    <span class="rating-number">⭐ ${resena.getResena().getCalificacion()}</span>
                                </div>
                            </div>
                        </div>
                        <div class="review-date">
                            <span class="text-muted">
                                ${resena.getResena().getFechaResena()}
                            </span>
                        </div>
                    </div>
                    
                    <div class="review-body">
                        <div class="review-author">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Usuario" class="author-avatar">
                            <span class="author-name">${resena.getNombreJugador()}</span>
                        </div>
                        
                        <h4 style="margin: 12px 0 4px 0; font-size: 1.1rem; color: #333;">
                            ${resena.getResena().getTitulo()}
                        </h4>

                        <p class="review-text">
                            ${resena.getResena().getComentario()}
                        </p>
                    </div>
                    
                    <div class="review-actions">
                        <form action="${pageContext.request.contextPath}/admin/moderar" method="post" style="display: inline;" onsubmit="return confirm('¿Estás seguro de eliminar esta reseña?');">
                            <input type="hidden" name="idResena" value="${resena.getResena().getIdResena()}">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</body>
</html>
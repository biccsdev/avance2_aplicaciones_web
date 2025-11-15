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
            <form action="${pageContext.request.contextPath}/admin/resenas/moderar" method="get" class="filter-form">
                <div class="filter-group">
                    <label for="producto">Filtrar por producto:</label>
                    <input type="text" id="producto" name="producto" class="input" placeholder="Nombre del producto...">
                </div>
                <div class="filter-group">
                    <label for="calificacion">Calificación:</label>
                    <select id="calificacion" name="calificacion" class="input">
                        <option value="">Todas</option>
                        <option value="5">5 estrellas</option>
                        <option value="4">4 estrellas</option>
                        <option value="3">3 estrellas</option>
                        <option value="2">2 estrellas</option>
                        <option value="1">1 estrella</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Filtrar</button>
            </form>
        </div>

        <div class="reviews-list">
            <%-- Datos dummy de reseñas --%>
            <div class="review-card">
                <div class="review-header">
                    <div class="review-product-info">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Producto" class="review-product-img">
                        <div>
                            <h3 class="review-product-name">Call of Duty: Ghosts - PlayStation 4</h3>
                            <div class="review-rating">
                                <span class="stars">★★★★★</span>
                                <span class="rating-number">5/5</span>
                            </div>
                        </div>
                    </div>
                    <div class="review-date">
                        <span class="text-muted">15/11/2025</span>
                    </div>
                </div>
                <div class="review-body">
                    <div class="review-author">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Usuario" class="author-avatar">
                        <span class="author-name">Sebastian Borquez</span>
                    </div>
                    <p class="review-text">
                        Excelente juego, la historia es muy buena y los gráficos son impresionantes. 
                        Totalmente recomendado para los fans de la saga. La modalidad multijugador es muy divertida.
                    </p>
                </div>
                <div class="review-actions">
                    <form action="${pageContext.request.contextPath}/admin/resenas/aprobar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="1">
                        <button type="submit" class="btn btn-success">Aprobar</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/resenas/eliminar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="1">
                        <button type="submit" class="btn btn-danger">Eliminar</button>
                    </form>
                </div>
            </div>

            <div class="review-card">
                <div class="review-header">
                    <div class="review-product-info">
                        <img src="${pageContext.request.contextPath}/imgs/elden-ring-ps5.jpg" alt="Producto" class="review-product-img">
                        <div>
                            <h3 class="review-product-name">Elden Ring - PlayStation 5</h3>
                            <div class="review-rating">
                                <span class="stars">★★★★☆</span>
                                <span class="rating-number">4/5</span>
                            </div>
                        </div>
                    </div>
                    <div class="review-date">
                        <span class="text-muted">20/11/2025</span>
                    </div>
                </div>
                <div class="review-body">
                    <div class="review-author">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Usuario" class="author-avatar">
                        <span class="author-name">Ariel Rodriguez</span>
                    </div>
                    <p class="review-text">
                        Gran juego pero muy difícil. La exploración del mundo abierto es increíble y hay mucho contenido. 
                        Solo le bajo una estrella por la dificultad extrema en algunos jefes.
                    </p>
                </div>
                <div class="review-actions">
                    <form action="${pageContext.request.contextPath}/admin/resenas/aprobar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="2">
                        <button type="submit" class="btn btn-success">Aprobar</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/resenas/eliminar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="2">
                        <button type="submit" class="btn btn-danger">Eliminar</button>
                    </form>
                </div>
            </div>

            <div class="review-card review-flagged">
                <div class="review-header">
                    <div class="review-product-info">
                        <img src="${pageContext.request.contextPath}/imgs/gtav-ps4.jpg" alt="Producto" class="review-product-img">
                        <div>
                            <h3 class="review-product-name">Grand Theft Auto V - PlayStation 4</h3>
                            <div class="review-rating">
                                <span class="stars">★☆☆☆☆</span>
                                <span class="rating-number">1/5</span>
                            </div>
                        </div>
                    </div>
                    <div class="review-date">
                        <span class="text-muted">22/11/2025</span>
                        <span class="flag-badge">⚠️ Reportada</span>
                    </div>
                </div>
                <div class="review-body">
                    <div class="review-author">
                        <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png" alt="Usuario" class="author-avatar">
                        <span class="author-name">Usuario Anónimo</span>
                    </div>
                    <p class="review-text">
                        [Contenido inapropiado - Esta reseña contiene lenguaje ofensivo y no relacionado con el producto]
                    </p>
                </div>
                <div class="review-actions">
                    <form action="${pageContext.request.contextPath}/admin/resenas/aprobar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="3">
                        <button type="submit" class="btn btn-success">Aprobar</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/resenas/eliminar" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="3">
                        <button type="submit" class="btn btn-danger">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>

        <c:if test="${not empty mensaje}">
            <div class="success-message">${mensaje}</div>
        </c:if>
    </main>
</body>
</html>


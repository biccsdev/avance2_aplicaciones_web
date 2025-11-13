<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dejar Reseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/resenaPedido.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="review-container">
        <h1 class="review-title brand-title">Dejar Reseña - Pedido #${param.id}</h1>

        <div class="review-content">
            <section class="products-to-review">
                <h2>Productos en este pedido</h2>
                <div class="product-list">
                    <div class="product-preview">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Producto" class="product-img">
                        <span>Call of Duty: Ghosts - PlayStation 4</span>
                    </div>
                    <div class="product-preview">
                        <img src="${pageContext.request.contextPath}/imgs/poly.png" alt="Producto" class="product-img">
                        <span>The Battle of Polytopia - PC</span>
                    </div>
                </div>
            </section>

            <section class="review-form-section">
                <form action="${pageContext.request.contextPath}/pedidos/guardar-resena" method="post" class="review-form">
                    <input type="hidden" name="pedidoId" value="${param.id}">

                    <div class="form-group">
                        <label for="productoId" class="form-label">Producto a reseñar</label>
                        <select id="productoId" name="productoId" class="input" required>
                            <option value="">Selecciona un producto</option>
                            <option value="1">Call of Duty: Ghosts - PlayStation 4</option>
                            <option value="2">The Battle of Polytopia - PC</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Calificación</label>
                        <div class="rating-selector">
                            <input type="radio" id="star5" name="calificacion" value="5" required>
                            <label for="star5" class="star">★</label>
                            <input type="radio" id="star4" name="calificacion" value="4">
                            <label for="star4" class="star">★</label>
                            <input type="radio" id="star3" name="calificacion" value="3">
                            <label for="star3" class="star">★</label>
                            <input type="radio" id="star2" name="calificacion" value="2">
                            <label for="star2" class="star">★</label>
                            <input type="radio" id="star1" name="calificacion" value="1">
                            <label for="star1" class="star">★</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="comentario" class="form-label">Tu reseña</label>
                        <textarea id="comentario" name="comentario" class="textarea" rows="6" 
                                  placeholder="Cuéntanos tu experiencia con este producto..." required></textarea>
                    </div>

                    <c:if test="${not empty error}">
                        <div class="error-text">${error}</div>
                    </c:if>

                    <c:if test="${not empty success}">
                        <div class="success-text">${success}</div>
                    </c:if>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Enviar Reseña</button>
                        <a href="${pageContext.request.contextPath}/pedidos" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </section>
        </div>
    </main>
</body>
</html>


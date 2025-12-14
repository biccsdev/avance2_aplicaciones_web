<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.usuario}">
    <c:redirect url="/login"/>
</c:if>

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
    
    <script>
        const CONTEXT_PATH = "${pageContext.request.contextPath}";
        const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
        const ID_PEDIDO = "${param.id}"; 
    </script>
    <script src="${pageContext.request.contextPath}/JavaScript/Resenas/dejarResena.js" defer></script>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="review-container">
        <h1 class="review-title brand-title">Dejar Reseña - Pedido #${param.id}</h1>

        <div class="review-content">
            
            <section class="products-to-review">
                <h2>Productos en este pedido</h2>
                <div class="product-list" id="lista-visual-productos">
                    <p>Cargando productos...</p>
                </div>
            </section>

            <section class="review-form-section">
                <form id="form-resena" class="review-form">
                    
                    <div class="form-group">
                        <label for="productoId" class="form-label">Producto a reseñar</label>
                        <select id="productoId" name="productoId" class="input" required>
                            <option value="">Cargando opciones...</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="titulo" class="form-label">Título de la reseña</label>
                        <input type="text" id="titulo" name="titulo" class="input" 
                               placeholder="Ej. ¡Increíble experiencia de juego!" required>
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

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary" id="btn-enviar">Enviar Reseña</button>
                        <a href="${pageContext.request.contextPath}/pedidos/detalle?id=${param.id}" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </section>
        </div>
    </main>
</body>
</html>


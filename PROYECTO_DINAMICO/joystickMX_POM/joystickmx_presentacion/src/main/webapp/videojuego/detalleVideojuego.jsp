<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Juego</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detalleVideojuego.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/logo.png">
        <script src="${pageContext.request.contextPath}/JavaScript/Videojuego/detalleVideojuego.js"></script>
        <script>
            const CONTEXT_PATH = "${pageContext.request.contextPath}";
            const ID_USUARIO_ACTUAL = "${sessionScope.usuario.idUsuario}";
        </script>
    </head>
    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <main class="flex-container">
            <div class="videojuego-detalle">
                <div class="videojuego-imagen">
                    <img id="videojuego-imagen" class="videojuego-imagen" src="">
                </div>
                <div class="videojuego-info">
                    <h3 id="videojuego-nombre" class="videojuego-nombre"></h3>
                    <h4 id="videojuego-existencias" class="videojuego-existencias">Existencias: </h4>
                    <div class="videojuego-precio-carrito">
                        <h2 id="videojuego-precio" class="videojuego-precio">$</h2>
                        <button class="btn-carrito btn-dark">
                            Agregar al carrito
                            <img src="${pageContext.request.contextPath}/imgs/carrito.png">
                        </button>
                    </div>
                    <p id="videojuego-descripcion" class="videojuego-descripcion"></p>
                </div>
                <div class="videojuego-especificaciones">
                    <h3>Especificaciones técnicas</h3>
                    <ul>
                        <li>
                            <label id="videojuego-plataforma">
                                <span class="especificacion">Plataforma: </span>
                            </label>
                        </li>
                        <li>
                            <label id="videojuego-fecha-lanzamiento">
                                <span class="especificacion">Fecha de lanzamiento: </span>
                            </label>
                        </li>
                        <li>
                            <label id="videojuego-categorias">
                                <span class="especificacion">Categorías: </span>
                            </label>
                            <ul id="videojuego-categorias-lista">
                                
                            </ul>
                        </li>
                        <li>
                            <label id="videojuego-desarrollador">
                                <span class="especificacion">Desarrollador: </span>
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="resenas-container">
                <h2 id="titulo-resenas" class="titulo-resenas">Reseñas:</h2>
                <div id="nueva-resena" class="nueva-resena">
                    <h2 id="nueva-resena-label">Escribe una reseña:</h2>
                    <form id="resena-form" name="resena-form" method="post">
                        <div class="nueva-resena-container">
                            <label for="nueva-resena-calificacion">Calificación:</label>
                            <input 
                                type="number" 
                                id="nueva-resena-calificacion" 
                                name="nueva-resena-calificacion" 
                                form="resena-form" 
                                required 
                                max="5" 
                                min="0" 
                                step="0.5"/>
                        </div>
                        <div class="nueva-resena-container">
                            <label id="nueva-resena-titulo-label" for="nueva-resena-titulo">Título:</label>
                            <input 
                                type="text" 
                                id="nueva-resena-titulo" 
                                name="nueva-resena-titulo" 
                                form="resena-form" 
                                maxlength="100" 
                                minlength="1" 
                                placeholder="Escribe un título de tu reseña." 
                                required/>
                        </div>
                        <div class="nueva-resena-container">
                            <label id="nueva-resena-comentario-label" for="nueva-resena-comentario">Comentario:</label>
                            <textarea 
                                id="nueva-resena-comentario" 
                                name="nueva-resena-comentario" 
                                form="resena-form" 
                                maxlength="500" 
                                minlength="10" 
                                rows="10"
                                wrap="hard"
                                placeholder="Escribe qué te gustó o no te gustó del juego." 
                                required></textarea>
                        </div>
                        <div class="nueva-resena-container">
                            <button 
                                type="submit" 
                                id="nueva-resena-button" 
                                class="btn-dark"
                                name="nueva-resena-button" 
                                form="resena-form">Publicar</button>
                        </div>
                    </form>
                </div>
                <ul id="videojuego-resenas" class="videojuego-resenas"></ul>
            </div>
        </main>

    </body>
</html>
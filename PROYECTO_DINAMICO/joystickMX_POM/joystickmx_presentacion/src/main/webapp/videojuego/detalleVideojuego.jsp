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
                    <div class="videojuego-nombre detalle">
                        <h3 id="videojuego-nombre"></h3>
                    </div>
                    <div class="videojuego-existencias detalle">
                        <h4 id="videojuego-existencias">Existencias: </h4>
                    </div>
                    <div class="videojuego-precio-carrito">
                        <h2 id="videojuego-precio">$</h2>
                        <button id="btn-carrito" class="btn-carrito btn-dark">
                            Agregar al carrito
                            <img src="${pageContext.request.contextPath}/imgs/carrito.png">
                        </button>
                    </div>
                    <div class="videojuego-descripcion detalle">
                        <p id="videojuego-descripcion"></p>
                    </div>
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
                <ul id="videojuego-resenas" class="videojuego-resenas"></ul>
            </div>
        </main>

    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crearProducto.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Crear Producto</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main>
        <div class="form-main">
            <div class="sidebar-image">
                <h1>Crear Producto</h1>
                <div class="img-producto">
                    <img src="${pageContext.request.contextPath}/imgs/iconoImagen.png" alt="Icono de imagen de producto" class="img-producto">
                </div>

                <div class="upload-div">
                    <button type="button" class="upload-button">
                        <img src="${pageContext.request.contextPath}/imgs/uploadIcon.png" alt="Icono de subir imagen" class="upload-icon">
                        <span class="upload-label">Subir Imagen</span>
                    </button>
                </div>
            </div>

            <form class="form-producto" action="${pageContext.request.contextPath}/admin/productos/crear" method="post" enctype="multipart/form-data">
                <div class="form-div">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre del videojuego" required>
                </div>

                <div class="form-div">
                    <label for="plataforma">Plataforma</label>
                    <select id="plataforma" name="plataforma" required>
                        <option value="">Selecciona una plataforma</option>
                        <option value="pc">PC</option>
                        <option value="ps4">PlayStation 4</option>
                        <option value="ps5">PlayStation 5</option>
                        <option value="xbox-one">Xbox One</option>
                        <option value="xbox-series">Xbox Series X/S</option>
                        <option value="switch">Nintendo Switch</option>
                        <option value="switch-2">Nintendo Switch 2</option>
                    </select>
                </div>

                <div class="form-div">
                    <label for="desarrollador">Desarrollador</label>
                    <input type="text" id="desarrollador" name="desarrollador" placeholder="Desarrollador del juego" required>
                </div>

                <div class="form-div">
                    <label for="precio">Precio</label>
                    <input type="number" id="precio" name="precio" placeholder="0.00" step="0.01" min="0" required>
                </div>

                <div class="form-div">
                    <label for="existencias">Existencias</label>
                    <input type="number" id="existencias" name="existencias" placeholder="0" min="0" required>
                </div>

                <div class="form-div">
                    <label for="lanzamiento">Fecha de lanzamiento</label>
                    <input type="date" id="lanzamiento" name="lanzamiento" required>
                </div>

                <div class="form-div">
                    <label for="genero">Género</label>
                    <select id="genero" name="genero" required>
                        <option value="">Selecciona un género</option>
                        <option value="shooter">Shooter</option>
                        <option value="rpg">RPG</option>
                        <option value="aventura">Aventura</option>
                        <option value="estrategia">Estrategia</option>
                        <option value="carreras">Carreras</option>
                        <option value="deportes">Deportes</option>
                        <option value="terror">Terror</option>
                        <option value="mundo-abierto">Mundo Abierto</option>
                    </select>
                </div>

                <c:if test="${not empty error}">
                    <div class="error-text">${error}</div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="success-text">${success}</div>
                </c:if>

                <div class="button-div">
                    <button type="submit" class="btn-crear">Crear Producto</button>
                    <a href="${pageContext.request.contextPath}/admin/productos/gestionar">
                        <button type="button" class="cancelar-button">Cancelar</button>
                    </a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>


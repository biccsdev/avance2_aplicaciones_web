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

                    <c:if test="${not empty error}">
                        <h4 style="color: red; text-align: center; background-color: white; padding: 10px; border-radius: 8px;">
                            ${error}
                        </h4>
                    </c:if> 

                    <h1>Crear Producto</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}/imgs/iconoImagen.png" alt="Icono de imagen de producto" class="img-producto">
                    </div>

                </div>

                <form class="form-container" 
                      method="POST" 
                      action="${pageContext.request.contextPath}/admin/productos/crear"
                      enctype="multipart/form-data"
                      >
                    <div class="form-div">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" placeholder="Nombre del videojuego" required>
                    </div>

                    <div class="form-div">
                        <label for="plataforma">Plataforma</label>
                        <select id="plataforma" name="plataforma" required>
                            <option value="">Selecciona una plataforma</option>
                            <option value="PC">PC</option>
                            <option value="Playstation 4">PlayStation 4</option>
                            <option value="Playstation 5">PlayStation 5</option>
                            <option value="Xbox One">Xbox One</option>
                            <option value="Xbox Series X/S">Xbox Series X/S</option>
                            <option value="Nintendo Switch">Nintendo Switch</option>
                            <option value="Nintendo Switch 2">Nintendo Switch 2</option>
                        </select>
                    </div>

                    <div class="form-div">
                        <label for="descripcion">Descripción</label>
                        <textarea id="descripcion" name="descripcion" placeholder="Escribe una breve descripción del videojuego..." rows="4" required></textarea>
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
                        <label for="categoria">Categoría</label>
                        <select id="genero" name="categoria" required>
                            <option value="">Selecciona un género</option>
                            <c:forEach var="categoria" items="${categoriasDisponibles}">
                                <%-- El VALUE es el nombre exacto que el DAO de búsqueda necesita --%>
                                <option value="${categoria.nombre}">${categoria.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-div">
                        <label for="imagenFile">Subir Imagen:</label>
                        <input type="file" id="imagenFile" name="imagenFile" accept="image/png, image/jpeg, image/jpg" required>
                    </div>

                    <c:if test="${not empty success}">
                        <div class="success-text">${success}</div>
                    </c:if>

                    <div class="button-div">
                        <button type="submit" class="btn-crear">Crear Producto</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>
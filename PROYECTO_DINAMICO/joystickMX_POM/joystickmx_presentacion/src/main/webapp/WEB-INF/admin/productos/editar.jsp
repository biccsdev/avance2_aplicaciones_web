<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editandoProducto.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
        <title>Editando Producto</title>
    </head>

    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main>
            <div class="form-main">
                <c:if test="${not empty error}">
                    <div class="alert-container">
                        <h3>Ocurrió un error</h3>
                        <p>${error}</p>
                    </div>
                </c:if>


                <div class="sidebar-image">
                    <h1>Editar Producto</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}${videojuego.urlImagen}" 
                             alt="Portada de ${videojuego.nombre}" 
                             class="img-producto"
                             onerror="this.src='${pageContext.request.contextPath}/imgs/iconoImagen.png'">
                    </div>
                </div>

                <form class="form-producto" 
                      action="${pageContext.request.contextPath}/admin/productos/editar?nombre=${videojuego.nombre}" 
                      method="POST" 
                      enctype="multipart/form-data">

                    <input type="hidden" name="idVideojuego" value="${videojuego.idVideojuego}">

                    <div class="form-div-container">
                        <div id="form-nombre" class="form-div">
                            <label for="nombre">Nombre</label>
                            <input type="text" id="nombre" name="nombre" value="${videojuego.nombre}" required>
                        </div>

                        <div id="form-plataforma" class="form-div">
                            <label for="plataforma">Plataforma</label>
                            <select id="plataforma" name="plataforma">
                                <option value="PC" ${videojuego.plataforma == 'PC' ? 'selected' : ''}>PC</option>
                                <option value="PlayStation" ${videojuego.plataforma == 'PlayStation' ? 'selected' : ''}>PlayStation</option>
                                <option value="Xbox" ${videojuego.plataforma == 'Xbox' ? 'selected' : ''}>Xbox</option>
                                <option value="Nintendo" ${videojuego.plataforma == 'Nintendo' ? 'selected' : ''}>Nintendo</option>
                            </select>
                        </div>

                        <div id="form-descripcion" class="form-div">
                            <label for="descripcion">Descripción</label>
                            <textarea id="descripcion" name="descripcion" rows="4" required>${videojuego.descripcion}</textarea>
                        </div>

                        <div id="form-desarrollador" class="form-div">
                            <label for="desarrollador">Desarrollador</label>
                            <input type="text" id="desarrollador" name="desarrollador" value="${videojuego.desarrollador}" required>
                        </div>

                        <div id="form-precio" class="form-div">
                            <label for="precio">Precio</label>
                            <input type="number" id="precio" name="precio" value="${videojuego.precio}" step="0.01" required>
                        </div>

                        <div id="form-existencias" class="form-div">
                            <label for="existencias">Existencias</label>
                            <input type="number" id="existencias" name="existencias" value="${videojuego.existencias}" required>
                        </div>

                        <div id="form-lanzamiento" class="form-div">
                            <label for="lanzamiento">Fecha de lanzamiento</label>
                            <input type="date" id="lanzamiento" name="lanzamiento" value="${videojuego.fechaLanzamiento}" required>
                        </div>

                        <div id="form-genero" class="form-div">
                            <label for="genero">Género (Categoría)</label>
                            <select id="genero" name="categoria">
                                <c:forEach var="cat" items="${categoriasDisponibles}">
                                    <c:set var="isSelected" value="false"/>
                                    <c:forEach var="catJuego" items="${videojuego.categorias}">
                                        <c:if test="${catJuego.idCategoria == cat.idCategoria}">
                                            <c:set var="isSelected" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <option value="${cat.nombre}" ${isSelected ? 'selected' : ''}>${cat.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div id="form-file" class="form-div">
                            <label for="imagenFile">Cambiar Imagen (Opcional)</label>
                            <input type="file" id="imagenFile" name="imagenFile" accept="image/*">
                        </div>

                        <div id="button-div" class="button-div">
                            <button type="submit" class="btn-crear">Guardar Cambios</button>

                            <a href="${pageContext.request.contextPath}/admin/productos/confirmar-eliminar?nombre=${videojuego.nombre}">
                                <button type="button" class="btn-eliminar">ELIMINAR</button>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </main>  
    </body>
</html>
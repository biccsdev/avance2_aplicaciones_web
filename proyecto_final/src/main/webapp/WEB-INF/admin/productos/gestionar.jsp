<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editarProducto.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editandoProducto.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirmarElminarProducto.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <title>Gestionar Productos</title>
</head>
<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main>
        <%-- Estado: LISTA DE PRODUCTOS --%>
        <c:if test="${empty param.estado or param.estado eq 'lista'}">
            <h1 class="title">Editar Producto</h1>
            
            <div class="main-productos">
                <div class="search-container-productos">
                    <input class="search-input-productos" type="text" placeholder="Buscar producto...">
                </div>

                <div class="productos-lista">
                    <%-- Datos dummy para visualización --%>
                    <c:forEach begin="1" end="5" var="i">
                        <div class="producto-item">
                            <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Producto" class="producto-img">
                            <div class="producto-info">
                                <h3>Call of Duty: Ghosts - PlayStation 4</h3>
                                <p class="producto-precio">$600.00</p>
                                <p class="producto-stock">Stock: 50 unidades</p>
                            </div>
                            <a href="${pageContext.request.contextPath}/admin/productos/gestionar?estado=editar&id=${i}">
                                <button class="btn-editar">Editar</button>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <%-- Estado: EDITANDO PRODUCTO --%>
        <c:if test="${param.estado eq 'editar'}">
            <div class="form-main">
                <div class="sidebar-image">
                    <h1>Editar Producto</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Imagen del producto" class="img-producto">
                    </div>
                    <div class="upload-div">
                        <button type="button" class="upload-button">
                            <img src="${pageContext.request.contextPath}/imgs/uploadIcon.png" alt="Subir" class="upload-icon">
                            <span class="upload-label">Cambiar Imagen</span>
                        </button>
                    </div>
                </div>

                <form class="form-producto" action="${pageContext.request.contextPath}/admin/productos/actualizar" method="post">
                    <input type="hidden" name="id" value="${param.id}">
                    
                    <div class="form-div">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" value="Call of Duty: Ghosts - PlayStation 4">
                    </div>

                    <div class="form-div">
                        <label for="plataforma">Plataforma</label>
                        <select id="plataforma" name="plataforma">
                            <option value="pc">PC</option>
                            <option value="ps4" selected>PlayStation 4</option>
                            <option value="ps5">PlayStation 5</option>
                            <option value="xbox">Xbox Series X</option>
                            <option value="switch">Nintendo Switch</option>
                        </select>
                    </div>

                    <div class="form-div">
                        <label for="desarrollador">Desarrollador</label>
                        <input type="text" id="desarrollador" name="desarrollador" value="Activision">
                    </div>

                    <div class="form-div">
                        <label for="precio">Precio</label>
                        <input type="number" id="precio" name="precio" value="600.00" step="0.01">
                    </div>

                    <div class="form-div">
                        <label for="existencias">Existencias</label>
                        <input type="number" id="existencias" name="existencias" value="50">
                    </div>

                    <div class="form-div">
                        <label for="lanzamiento">Fecha de lanzamiento</label>
                        <input type="date" id="lanzamiento" name="lanzamiento" value="2013-11-05">
                    </div>

                    <div class="form-div">
                        <label for="genero">Género</label>
                        <select id="genero" name="genero">
                            <option value="shooter" selected>Shooter</option>
                            <option value="rpg">RPG</option>
                            <option value="aventura">Aventura</option>
                            <option value="estrategia">Estrategia</option>
                            <option value="carreras">Carreras</option>
                        </select>
                    </div>

                    <div class="button-div">
                        <button type="submit" class="btn-crear">Guardar Cambios</button>
                        <a href="${pageContext.request.contextPath}/admin/productos/gestionar?estado=confirmar&id=${param.id}">
                            <button type="button" class="btn-eliminar">ELIMINAR</button>
                        </a>
                    </div>
                </form>
            </div>
        </c:if>

        <%-- Estado: CONFIRMAR ELIMINACIÓN --%>
        <c:if test="${param.estado eq 'confirmar'}">
            <div class="form-main">
                <div class="sidebar-image">
                    <h1>Editar Producto</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Imagen del producto" class="img-producto">
                    </div>
                </div>

                <form class="form-producto">
                    <div class="form-div">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" value="Call of Duty: Ghosts - PlayStation 4" disabled>
                    </div>

                    <div class="form-div">
                        <label for="plataforma">Plataforma</label>
                        <select id="plataforma" name="plataforma" disabled>
                            <option value="ps4" selected>PlayStation 4</option>
                        </select>
                    </div>

                    <div class="form-div">
                        <label for="desarrollador">Desarrollador</label>
                        <input type="text" id="desarrollador" name="desarrollador" value="Activision" disabled>
                    </div>

                    <div class="form-div">
                        <label for="precio">Precio</label>
                        <input type="number" id="precio" name="precio" value="600.00" disabled>
                    </div>

                    <div class="form-div">
                        <label for="existencias">Existencias</label>
                        <input type="number" id="existencias" name="existencias" value="50" disabled>
                    </div>

                    <div class="form-div">
                        <label for="lanzamiento">Fecha de lanzamiento</label>
                        <input type="date" id="lanzamiento" name="lanzamiento" value="2013-11-05" disabled>
                    </div>

                    <div class="form-div">
                        <label for="genero">Género</label>
                        <select id="genero" name="genero" disabled>
                            <option value="shooter" selected>Shooter</option>
                        </select>
                    </div>
                </form>

                <div class="confirmacion-div">
                    <span>
                        ¿Estás seguro de que deseas eliminar este producto?
                        Dejará de estar disponible para su venta de ahora en adelante
                    </span>
                    <div class="confirmation-buttons">
                        <a href="${pageContext.request.contextPath}/admin/productos/eliminar?id=${param.id}">
                            <button class="eliminar-button">ELIMINAR</button>
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/productos/gestionar?estado=editar&id=${param.id}">
                            <button class="cancelar-button">Regresar</button>
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

        <%-- Estado: PRODUCTO ELIMINADO --%>
        <c:if test="${param.estado eq 'eliminado'}">
            <div class="form-main">
                <div class="sidebar-image">
                    <h1>Producto</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}/imgs/ghosts.png" alt="Imagen del producto" class="img-producto">
                    </div>
                </div>

                <div class="confirmacion-div">
                    <img class="basura-icon" src="${pageContext.request.contextPath}/imgs/basura.png" alt="Producto Eliminado">
                    <span>Producto Eliminado</span>
                    <a href="${pageContext.request.contextPath}/admin/productos/gestionar">
                        <button class="cancelar-button">Volver a la lista</button>
                    </a>
                </div>
            </div>
        </c:if>
    </main>
</body>
</html>


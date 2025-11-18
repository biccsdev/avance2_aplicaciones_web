<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confirmarElminarProducto.css">
    <title>Confirmar Eliminación</title>
</head>

<body class="app-bg-animated">
    <jsp:include page="/WEB-INF/includes/header.jsp"/>
    
    <main>
        <div class="form-main">
            
            <div class="sidebar-image">
                <h1>Eliminar Producto</h1>
                <div class="img-producto">
                    <img src="${pageContext.request.contextPath}${videojuego.urlImagen}" 
                         alt="Portada" 
                         class="img-producto"
                         style="object-fit: cover;">
                </div>
            </div>

            <form class="form-producto">
                <div class="form-div">
                    <label>Nombre</label>
                    <input type="text" value="${videojuego.nombre}" disabled>
                </div>

                <div class="form-div">
                    <label>Plataforma</label>
                    <select disabled>
                        <option selected>${videojuego.plataforma}</option>
                    </select>
                </div>
                
                <div class="form-div">
                    <label>Desarrollador</label>
                    <input type="text" value="${videojuego.desarrollador}" disabled>
                </div>
                
                <div class="form-div">
                    <label>Precio</label>
                    <input type="number" value="${videojuego.precio}" disabled>
                </div>
                
                <div class="form-div">
                    <label>Existencias</label>
                    <input type="number" value="${videojuego.existencias}" disabled>
                </div>

                <div class="form-div">
                    <label>Fecha de lanzamiento</label>
                    <input type="date" value="${videojuego.fechaLanzamiento}" disabled>
                </div>

                <div class="form-div">
                    <label>Categoría</label>
                    <select disabled>
                        <c:forEach var="cat" items="${videojuego.categorias}">
                            <option selected>${cat.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>

            <div class="confirmacion-div">
                <span>
                    ¿Estás seguro de que deseas eliminar este producto?<br>
                    Dejará de estar disponible para su venta de ahora en adelante.
                </span>
                <div class="confirmation-buttons">
                    
                    <form action="${pageContext.request.contextPath}/admin/productos/confirmar-eliminar" method="POST">
                        <input type="hidden" name="idVideojuego" value="${videojuego.idVideojuego}">
                        <button type="submit" class="eliminar-button">ELIMINAR</button>
                    </form>

                    <a href="${pageContext.request.contextPath}/admin/productos/editar?idVideojuego=${videojuego.idVideojuego}">
                        <button class="cancelar-button">Regresar</button>
                    </a>
                </div>
            </div>
        </div>
    </main>  
</body>
</html>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productoEliminado.css">
        <title>Producto Eliminado</title>
    </head>

    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>

        <main>
            <div class="form-main">

                <div class="sidebar-image">
                    <h1>Producto Eliminado</h1>
                    <div class="img-producto">
                        <img src="${pageContext.request.contextPath}${videojuego.urlImagen}" 
                             alt="Portada" 
                             class="img-producto"
                             >
                    </div>
                </div>

                <form class="form-producto">
                    <div class="form-div">
                        <label>Nombre</label>
                        <input type="text" value="${videojuego.nombre}" disabled>
                    </div>
                    <div class="form-div">
                        <label>Plataforma</label>
                        <select disabled><option>${videojuego.plataforma}</option></select>
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
                                <option>${cat.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>

                <div class="confirmacion-div">
                    <img class="basura-icon" src="${pageContext.request.contextPath}/imgs/basura.png" alt="Producto Eliminado">
                    <span>
                        Producto Eliminado Correctamente
                    </span>

                    <a href="${pageContext.request.contextPath}/home">
                        <button class="cancelar-button" style="margin-top: 20px;">Volver al Catálogo</button>
                    </a>
                </div>
            </div>
        </main>  
    </body>
</html>
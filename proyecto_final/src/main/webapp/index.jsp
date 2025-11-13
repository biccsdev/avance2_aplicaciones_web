<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JoystickMX - Tienda de Videojuegos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/icono_app.png">
</head>
<body class="app-bg-animated">
    <%-- Header unificado que se adapta automáticamente --%>
    <jsp:include page="/WEB-INF/includes/header.jsp"/>

    <main class="grid-container">
        <div class="videojuego-container">
            <div class="filtro-busqueda">
                <div class="filtro-seccion-rango">
                    <span class="filtro-titulo">Precio</span>
                    <span class="filtro-cantidad">$0-3000</span>
                </div>

                <input class="filtro-rango" type="range" min="0" max="3000" value="3000" aria-label="Rango de precio">

                <select class="filtro-select" aria-label="Plataforma">
                    <option selected>Plataforma</option>
                    <option>Xbox</option>
                    <option>PlayStation</option>
                    <option>Nintendo</option>
                    <option>PC</option>
                </select>

                <select class="filtro-select" aria-label="Género">
                    <option selected>Género</option>
                    <option>Acción y aventuras</option>
                    <option>Terror</option>
                    <option>Mundo abierto</option>
                    <option>Carreras</option>
                    <option>RPG</option>
                    <option>Shooter</option>
                </select>
            </div>

            <ul class="videojuego-lista">
                <%-- Datos dummy de videojuegos --%>
                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/rdr2-xbox-one.jpg" alt="Red Dead Redemption 2">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Red Dead Redemption 2 (Xbox One)</h3>
                            <h2 class="videojuego-precio">$299</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/rdr2-ps4.jpg" alt="Red Dead Redemption 2">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Red Dead Redemption 2 (Playstation 4)</h3>
                            <h2 class="videojuego-precio">$299</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/gtav-ps4.jpg" alt="GTA V">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Grand Theft Auto V (Playstation 4)</h3>
                            <h2 class="videojuego-precio">$299</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/gtav-xbox-one.jpg" alt="GTA V">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Grand Theft Auto V (Xbox One)</h3>
                            <h2 class="videojuego-precio">$299</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/gow-ps5.jpeg" alt="God of War Ragnarok">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">God of War Ragnarok (Playstation 5)</h3>
                            <h2 class="videojuego-precio">$799</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/fh5-xbox-series-xs.jpg" alt="Forza Horizon 5">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Forza Horizon 5 (Xbox Series X/S)</h3>
                            <h2 class="videojuego-precio">$799</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/aw2-xbox-series-xs.jpg" alt="Alan Wake 2">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Alan Wake 2 (Xbox Series X/S)</h3>
                            <h2 class="videojuego-precio">$799</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/aw2-deluxe-ps5.jpg" alt="Alan Wake 2 Deluxe">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Alan Wake 2 - Deluxe edition (Playstation 5)</h3>
                            <h2 class="videojuego-precio">$1599</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/elden-ring-ps5.jpg" alt="Elden Ring">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Elden Ring (Playstation 5)</h3>
                            <h2 class="videojuego-precio">$499</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>

                <li class="videojuego-item">
                    <div class="videojuego">
                        <div class="videojuego-imagen">
                            <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/elden-ring-xbox-series-xs.jpg" alt="Elden Ring">
                        </div>
                        <div class="videojuego-info">
                            <h3 class="videojuego-nombre">Elden Ring (Xbox Series X/S)</h3>
                            <h2 class="videojuego-precio">$499</h2>
                            <button class="btn-carrito btn-dark">Agregar al carrito</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </main>
</body>
</html>


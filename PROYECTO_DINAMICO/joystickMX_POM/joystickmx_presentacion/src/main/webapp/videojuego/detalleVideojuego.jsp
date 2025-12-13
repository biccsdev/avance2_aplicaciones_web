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
                            <label>
                                <span id="videojuego-plataforma" class="especificacion">Plataforma: </span>
                            </label>
                        </li>
                        <li>
                            <label>
                                <span id="videojuego-fecha-lanzamiento" class="especificacion">Fecha de lanzamiento: </span>
                            </label>
                        </li>
                        <li>
                            <label>
                                <span id="videojuego-categorias" class="especificacion">Categorías: </span>
                            </label>
                        </li>
                        <li>
                            <label>
                                <span id="videojuego-desarrollador" class="especificacion">Desarrollador: </span>
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="resenas-container">
                <h2 class="titulo-resenas">Reseñas:</h2>
                <ul id="videojuego-resenas" class="videojuego-resenas">
                    <li class="videojuego-resena">
                        <div class="icono">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png">
                        </div>
                        <div class="resena">
                            <h3>Sebastián</h3>
                            <h4>Está muy mediocre</h4>
                            <p>
                                Eeee pues esta chilo pero no tanto y ademas la caja me llego toda hecha
                                giras xdd pero el principal problema es que tiene unos controles malisimos
                                y el perrito esta roto en el online arreglen eso.
                            </p>
                        </div>
                        <div class="calificacion">
                            <h3>2.5/5</h3>
                        </div>
                    </li>
                    <li class="videojuego-resena">
                        <div class="icono">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png">
                        </div>
                        <div class="resena">
                            <h3>Ariel</h3>
                            <h4>El mejor juego del mundo</h4>
                            <p>
                                con una mano en el corazon, LEJOS el mejor juego que jugue en mi vida. simplemente maravilloso. el lore es excelente, los graficos son geniales, todo es bueno en este juego. uno simplemente sueñaa ser arthur morgan en esta vida
                            </p>
                        </div>
                        <div class="calificacion">
                            <h3>5/5</h3>
                        </div>
                    </li>
                    <li class="videojuego-resena">
                        <div class="icono">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png">
                        </div>
                        <div class="resena">
                            <h3>Victor</h3>
                            <h4>Muy promedio</h4>
                            <p>
                                Está¡ 2/3, mucho menos divertido que el primero, pero tiene mejores gráficos sin duda.
                            </p>
                        </div>
                        <div class="calificacion">
                            <h3>2.5/5</h3>
                        </div>
                    </li>
                    <li class="videojuego-resena">
                        <div class="icono">
                            <img src="${pageContext.request.contextPath}/imgs/icono_user_super_prime.png">
                        </div>
                        <div class="resena">
                            <h3>GokuSaiyayin69</h3>
                            <h4>GOTY</h4>
                            <p>
                                Sé que llevo reciÃ©n pocas horas jugadas, pero este juego es realmente maravilloso, 
                                la historia es engancha fácilmente. Pero sobre todo, el ambiente, la música y los sonidos
                                son lo mejor por lejos. Me asombra la cantidad de detalles que están bien pensados y los
                                eventos de los npcs te hacen sentir como si el mundo estuviese realmente vivo. Es uno 
                                de los mejores juegos de la historia, merece mucho la pena. :3
                            </p>
                        </div>
                        <div class="calificacion">
                            <h3>5/5</h3>
                        </div>
                    </li>
                </ul>
            </div>
        </main>

    </body>
</html>
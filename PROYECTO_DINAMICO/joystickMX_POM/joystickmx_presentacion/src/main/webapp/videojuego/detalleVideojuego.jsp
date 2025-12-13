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
    </head>
    <body class="app-bg-animated">
        <jsp:include page="/WEB-INF/includes/header.jsp"/>
        <main class="flex-container">
            <div class="videojuego-detalle">
                <div class="videojuego-imagen">
                    <img class="videojuego-imagen" src="${pageContext.request.contextPath}/imgs/rdr2-xbox-one.jpg">
                </div>
                <div class="videojuego-info">
                    <h3 class="videojuego-nombre">Red Dead Redemption 2 (Xbox One)</h3>
                    <h4 class="videojuego-existencias">Existencias: 27</h4>
                    <div class="videojuego-precio-carrito">
                        <h2 class="videojuego-precio">$299</h2>
                        <button class="btn-carrito btn-dark">
                            Agregar al carrito
                            <img src="${pageContext.request.contextPath}/imgs/carrito.png">
                        </button>
                    </div>
                    <p class="videojuego-descripcion">
                        
                        Ambientado en 1899 en un entorno ficticio, sigue las aventuras de Arthur Morgan y sus 
                        compaÃ±eros dentro de la banda de Dutch van der Linde doce aÃ±os antes de la anterior 
                        entrega. En este videojuego regresan personajes como John Marston, el protagonista 
                        del tÃ­tulo previo en una aventura donde el jugador debe enfrentar a otros criminales, 
                        a las fuerzas del orden y ejecutar asaltos en un ambiente del salvaje oeste.<br>

                        <br>El juego es presentado en perspectivas de primera y tercera persona en donde el jugador 
                        podrÃ¡ vagar libremente en un entorno de mundo abierto completamente interactivo. Como 
                        parte de las actividades, el jugador podrÃ¡ montar a caballo, asaltar, cazar, participar
                        en tiroteos e incluso interactuar con personajes no jugables. Asimismo el jugador 
                        podrÃ¡ mantener la calificaciÃ³n de Honor del personaje a travÃ©s de elecciones y actos 
                        morales. Un sistema de recompensas rige la respuesta de las fuerzas del orden y los cazadores 
                        de recompensas a los crÃ­menes cometidos por el jugador.<br>

                        <br>El juego narra los acontecimientos que llevaron a la caÃ­da en desgracia de la Banda de Dutch van
                        der Linde y del cÃ³mo John Marston cambiÃ³ su forma de ser gracias al protagonista de esta entrega, 
                        Arthur Morgan.
                    </p>
                </div>
                <div class="videojuego-especificaciones">
                    <h3>Especificaciones tÃ©cnicas</h3>
                    <ul>
                        <li>
                            <label>
                                <span class="especificacion">Plataforma: </span>
                                Xbox One
                            </label>
                        </li>
                        <li>
                            <label>
                                <span class="especificacion">Fecha de lanzamiento: </span>
                                26 de octubre de 2018
                            </label>
                        </li>
                        <li>
                            <label>
                                <span class="especificacion">GÃ©nero: </span>
                                Mundo abierto, AcciÃ³n y aventuras, Disparos en tercera persona
                            </label>
                        </li>
                        <li>
                            <label>
                                <span class="especificacion">Desarrollador: </span>
                                Rockstar Games
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="resenas-container">
                <h2 class="titulo-resenas">Reseñas:</h2>
                <ul class="videojuego-resenas">
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
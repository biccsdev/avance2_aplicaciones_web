window.onload = () => {
    let idVideojuegoActual = null;

    const imagen = document.getElementById("videojuego-imagen");
    const nombre = document.getElementById("videojuego-nombre");
    const existencias = document.getElementById("videojuego-existencias");
    const precio = document.getElementById("videojuego-precio");
    const descripcion = document.getElementById("videojuego-descripcion");
    const plataforma = document.getElementById("videojuego-plataforma");
    const fechaLanzamiento = document.getElementById("videojuego-fecha-lanzamiento");
    const categorias = document.getElementById("videojuego-categorias-lista");
    const desarrollador = document.getElementById("videojuego-desarrollador");

    const init = () => { 
        obtenerVideojuego();
        obtenerResenas();
        
        const btnCarrito = document.getElementById("btn-carrito");
        if(btnCarrito){
            btnCarrito.addEventListener("click", agregarAlCarrito);
        }
    };

    const agregarAlCarrito = () => {
         if(idVideojuegoActual === null){
             alert("Espere a que cargue la información del producto.");
             return;
         }
         
         if(!ID_USUARIO_ACTUAL || ID_USUARIO_ACTUAL.trim() === ""){
             alert("Debes iniciar sesión para agregar productos.");
             window.location.href = `${CONTEXT_PATH}/login`;
             return;
         }
         
         const payload = {
             idVideojuego: parseInt(idVideojuegoActual),
             cantidad: 1
         };
         
         fetch(`${CONTEXT_PATH}/resources/carrito/usuario/${ID_USUARIO_ACTUAL}`, {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json'
             },
             body: JSON.stringify(payload)
         })
         .then(response => {
             if(response.ok){
                 alert("Producto agregado al carrito.");
             } else {
                 return response.json().then(data => {
                     throw new Error(data.mensaje || data.error || "Error al agregar al carrito");
                 });
             }
         })
         .catch(err => {
             console.error(err);
             alert(err.message);
         });
    };

    const obtenerVideojuego = () => {
        let host = `${CONTEXT_PATH}/resources/api/videojuego`;
        let nombreParam = new URLSearchParams(window.location.search).get("nombre");
        
        if(nombreParam != null){
            fetch(
                host + `/nombre/${encodeURIComponent(nombreParam)}`, 
                {method: "GET"}
            ).then(response => {
                if(!response.ok){
                    throw new Error("Error al intentar obtener el videojuego.");
                }
                return response.json();
            }).then(videojuego => {
                // Guardamos el ID para usarlo en el carrito
                idVideojuegoActual = videojuego.idVideojuego;
                cargarVideojuego(videojuego);
            }).catch(err => {
                console.error(err);
                alert("Ocurrió un error al consultar la información del juego.");
            });
        }
    };

    const obtenerResenas = () => {
        let host = `${CONTEXT_PATH}/resources/api/resena`;
        let nombreParam = new URLSearchParams(window.location.search).get("nombre");
        
        if(nombreParam != null){
            fetch(
                host + `/nombre/${encodeURIComponent(nombreParam)}`, 
                {method: "GET"}
            ).then(response => {
                if(!response.ok){
                    throw new Error("Error al intentar obtener las reseñas del videojuego.");
                }
                return response.json();
            }).then(resenas => {
                cargarResenas(resenas);
            }).catch(err => {
                console.error(err);
                alert("Ocurrió un error al intentar obtener las reseñas del videojuego.");
            });
        }
    };

    const cargarVideojuego = (videojuego) => {
        // Carga cada propiedad del juego obtenido en sus elementos correspondientes
        imagen.src = CONTEXT_PATH + videojuego.urlImagen;
        nombre.innerHTML = `${videojuego.nombre} (${videojuego.plataforma})`;
        existencias.innerHTML += videojuego.existencias;
        precio.innerHTML += videojuego.precio;
        descripcion.innerHTML = videojuego.descripcion;
        plataforma.innerHTML += videojuego.plataforma;
        fechaLanzamiento.innerHTML += videojuego.fechaLanzamiento;
        // Verifica si el juego tiene categorías (normalmente debería tener)
        if(Array.isArray(videojuego.categorias) && videojuego.categorias.length > 0){
            videojuego.categorias.forEach(element => {
                let categoria = document.createElement("li");
                categoria.innerHTML += `${element.nombre} `;
                categorias.appendChild(categoria);
            });
        } else{
            categorias.innerHTML += "Sin categorias";
        }
        desarrollador.innerHTML += `${videojuego.desarrollador}`;
    };

    const cargarResenas = (resenas) => {
        if(Array.isArray(resenas) && resenas.length > 0){
            // Obtiene la lista de reseñas.
            const listaResenas = document.getElementById("videojuego-resenas");

            // Obtiene la reseña del videojuego del usuario actual
            let resenaUsuario = resenas.find(element => element.resena.idCliente == ID_USUARIO_ACTUAL);
            // Verifica si la reseña obtenida realmente existe (no es undefined)
            if(typeof(resenaUsuario) !== "undefined"){
                // Filtra la reseña del usuario actual del arreglo original y guarda el arreglo resultante en la variable
                let resenasSinUsuario = resenas.filter(resena => resena.resena.idCliente != ID_USUARIO_ACTUAL);
                // Añade la reseña del usuario actual y la inserta en un nuevo arreglo como el primer elemento
                let resenasOrdenadas = [resenaUsuario];
                // Anexa el arreglo de las reseñas filtradas al arreglo con la reseña del usuario actual
                resenasOrdenadas = resenasOrdenadas.concat(resenasSinUsuario);
                // El arreglo original ahora es el arreglo re ordenado (reseña del usuario como primer elemento)
                resenas = resenasOrdenadas;
            }

            // Recorre cada reseña obtenida.
            resenas.forEach(resena => {
                // Extrae la reseña (ResenaDTO).
                let resenaDTO = resena.resena;

                // Crea el nuevo elemento de la lista de reseñas.
                let nuevaResena = document.createElement("li");
                nuevaResena.classList.add("videojuego-resena");

                // Crea el ícono del usuario.
                let icono = document.createElement("div");
                icono.classList.add("icono");
                // Crea y obtiene la imagen del ícono.
                let iconImg = document.createElement("img");
                iconImg.src = `${CONTEXT_PATH}/imgs/icono_user_super_prime.png`;
                // Agrega la imagen al contenedor ícono.
                icono.appendChild(iconImg);

                // Crea el contenedor de la información de la reseña.
                let resenaInfo = document.createElement("div");
                resenaInfo.classList.add("resena");

                // Crea el nombre del autor de la reseña.
                let autor = document.createElement("h3");
                autor.innerHTML = resena.nombreJugador;
                // Crea el título de la reseña.
                let titulo = document.createElement("h4");
                titulo.innerHTML = resenaDTO.titulo;
                // Crea el comentario de la reseña.
                let comentario = document.createElement("p");
                comentario.innerHTML = resenaDTO.comentario;

                // Agrega los elementos anteriores al contenedor de la reseña.
                resenaInfo.appendChild(autor);
                resenaInfo.appendChild(titulo);
                resenaInfo.appendChild(comentario);

                // Crea el contenedor de la calificación.
                let scoreInfo = document.createElement("div");
                scoreInfo.classList.add("calificacion");

                // Crea la calificación de la reseña.
                let score = document.createElement("h3");
                score.innerHTML = `${resenaDTO.calificacion}/5`;

                // Añade la calificación a su contenedor.
                scoreInfo.appendChild(score);

                // Añade cada pezado de información al elemento de la lista de reseñas.
                nuevaResena.appendChild(icono);
                nuevaResena.appendChild(resenaInfo);
                nuevaResena.appendChild(scoreInfo);

                // Añade el nuevo elemento a la lista de reseñas.
                listaResenas.appendChild(nuevaResena);
            });
        } else{
            document.getElementById("titulo-resenas").innerHTML = "Sin reseñas. ¡Sé el primero en dejar una reseña del videojuego!";
        }
    };

    init();
};
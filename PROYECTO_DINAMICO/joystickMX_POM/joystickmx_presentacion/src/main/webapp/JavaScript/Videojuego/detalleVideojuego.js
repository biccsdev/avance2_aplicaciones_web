window.onload = () => {
    
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
    }

    const obtenerVideojuego = () => {
        let host = `${CONTEXT_PATH}/resources/api/videojuego`;
        let id = new URLSearchParams(window.location.search).get("idVideojuego");
        if(id != null){
            fetch(
                host + `/${id}`, 
                {method: "GET"}
            ).then(response => {
                if(!response.ok){
                    throw new Error("Error al intentar obtener el videojuego.");
                }
                return response.json();
            }).then(videojuego => {
                cargarVideojuego(videojuego);
            }).catch(err => {
                console.error(err);
            });
        }
    }

    const obtenerResenas = () => {
        // URL host
        let host = `${CONTEXT_PATH}/resources/api/resena`;
        // Obtiene el parámetro del query string que almacena el ID del juego
        let id = new URLSearchParams(window.location.search).get("idVideojuego");
        // Realiza la petición a la API
        if(id != null){
            fetch(
                host + `/${id}`, 
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
            })
        }
    }

    const cargarVideojuego = (videojuego) => {
        // Carga cada propieda del juego obtenido en sus elementos correspondientes
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
    }

    const cargarResenas = (resenas) => {
        if(Array.isArray(resenas) && resenas.length > 0){
            // Obtiene la lista de reseñas.
            const listaResenas = document.getElementById("videojuego-resenas");
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
    }

    init();
}
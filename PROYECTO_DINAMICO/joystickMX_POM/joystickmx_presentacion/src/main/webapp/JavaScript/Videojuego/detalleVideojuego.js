window.onload = () => {
    
    const imagen = document.getElementById("videojuego-imagen");
    const nombre = document.getElementById("videojuego-nombre");
    const existencias = document.getElementById("videojuego-existencias");
    const precio = document.getElementById("videojuego-precio");
    const descripcion = document.getElementById("videojuego-descripcion");
    const plataforma = document.getElementById("videojuego-plataforma");
    const fechaLanzamiento = document.getElementById("videojuego-fecha-lanzamiento");
    const categorias = document.getElementById("videojuego-categorias");
    const desarrollador = document.getElementById("videojuego-desarrollador");

    const init = () => { 
        obtenerVideojuego();
    }

    const obtenerVideojuego = () => {
        const host = `${CONTEXT_PATH}/resources/api/videojuego`;
        const id = new URLSearchParams(window.location.search).get("idVideojuego");
        if(typeof(id) !== null){
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
                console.log("Error");
            });
        }
    }

    const cargarVideojuego = (videojuego) => {
        imagen.src = CONTEXT_PATH + videojuego.urlImagen;
        nombre.innerHTML = `${videojuego.nombre} (${videojuego.plataforma})`;
        existencias.innerHTML += videojuego.existencias;
        precio.innerHTML += videojuego.precio;
        descripcion.innerHTML = videojuego.descripcion;
        plataforma.innerHTML += videojuego.plataforma;
        fechaLanzamiento.innerHTML += videojuego.fechaLanzamiento;
        if(videojuego.categorias instanceof Array){
            videojuego.categorias.forEach(element => {
                categorias.innerHTML += `${element.nombre} `;
            });
        } else{
            categorias.innerHTML += "Sin categorias";
        }
        desarrollador.innerHTML = videojuego.desarrollador;
    }

    init();
}
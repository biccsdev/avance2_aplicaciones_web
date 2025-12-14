package com.mycompany.joystickmx_presentacion;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 * API para obtener los videojuegos
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Path("api/videojuego")
@RequestScoped
public class VideojuegoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VideojuegoResource
     */
    public VideojuegoResource() {}

    /**
     * Retorna una lista con todos los videojuegos registrados.
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideojuegos() {
        try {
            List<VideojuegoDTO> videojuegos;
            videojuegos = FachadaBO.buscarVideojuegosActivos();
            // Obtiene las categorías de cada juego
            for(VideojuegoDTO videojuego : videojuegos){
                if(videojuego.getCategorias() == null)
                    videojuego.setCategorias(FachadaBO.buscarCategoriaPorVideojuego(videojuego.getIdVideojuego()));
            }
            // Si se requieren reseñas, se consultan por separado.
            return Response.ok(videojuegos).build();
        } catch (NegocioException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getVideojuego(@PathParam("id") String id){
        try {
            Long idVideojuego = Long.valueOf(id);
            VideojuegoDTO videojuego = FachadaBO.buscarVideojuegoPorId(idVideojuego);
            // Obtiene las categorías del juego
            if(videojuego.getCategorias() == null)
                videojuego.setCategorias(FachadaBO.buscarCategoriaPorVideojuego(videojuego.getIdVideojuego()));
            // Si se requieren las reseñas del videojuego, se consultan por separado.
            return Response.ok(videojuego).build();
        } catch (NegocioException | NumberFormatException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
}
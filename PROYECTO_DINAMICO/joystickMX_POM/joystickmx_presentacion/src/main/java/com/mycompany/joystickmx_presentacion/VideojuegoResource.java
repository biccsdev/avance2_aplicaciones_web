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
import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 * API para obtener los videojuegos
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Path("videojuego")
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
    public List<VideojuegoDTO> getJson() {
        try {
            List<VideojuegoDTO> videojuegos;
            videojuegos = FactoryBO.buscarVideojuegosActivos();
            // Obtiene las categorías de cada juego
            for(VideojuegoDTO videojuego : videojuegos){
                if(videojuego.getCategorias() == null)
                    videojuego.setCategorias(FactoryBO.buscarCategoriaPorVideojuego(videojuego.getIdVideojuego()));
            }
            // Si se requieren reseñas, se consultan por separado.
            return videojuegos;
        } catch (NegocioException e) {
            // ¿Qué debería devolver realmente?
            return null;
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public VideojuegoDTO getUniqueJson(@PathParam("id") String id){
        try {
            Long idVideojuego = Long.valueOf(id);
            VideojuegoDTO videojuego = FactoryBO.buscarVideojuegoPorId(idVideojuego);
            // Obtiene las categorías del juego
            if(videojuego.getCategorias() == null)
                videojuego.setCategorias(FactoryBO.buscarCategoriaPorVideojuego(videojuego.getIdVideojuego()));
            // Si se requieren las reseñas del videojuego, se consultan por separado.
            return videojuego;
        } catch (NegocioException | NumberFormatException e) {
            // ¿Qué debería devolver realmente?
            return null;
        }
    }
    
}
package com.mycompany.joystickmx_presentacion;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 * REST Web Service
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Path("resena")
@RequestScoped
public class ResenasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ResenasResource
     */
    public ResenasResource() {}

    /**
     * Retrieves representation of an instance of com.mycompany.joystickmx_presentacion.ResenasResource
     * @return an instance of joystickmx.itson.DTO.ResenaDTO
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResenaDTO> getJson() {
        try {
            return FactoryBO.buscarTodasLasResenas();
        } catch (NegocioException e) {
            return null;
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ResenaDTO> getVideogameJson(@PathParam("id") String id){
        try {
            Long idVideojuego = Long.valueOf(id);
            return FactoryBO.buscarResenasPorVideojuego(idVideojuego);
        } catch (NegocioException | NumberFormatException e) {
            return null;
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postJson(ResenaDTO nuevaResena){
        try {
            // Validar máximo 1 reseña por cliente
            FactoryBO.crearResena(nuevaResena);
            return true;
        } catch (NegocioException e) {
            return false;
        }
    }
}
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
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.VideojuegoResenaDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.negocio.exception.NegocioException;

/**
 * REST Web Service
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Path("api/resena")
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
    public Response getResenas() {
        try {
            List<ResenaDTO> resenas = FachadaBO.buscarTodasLasResenas();
            return Response.ok(resenas).build();
        } catch (NegocioException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getResenasVideojuego(@PathParam("id") String id){
        try {
            Long idVideojuego = Long.valueOf(id);
            List<ResenaDTO> resenas = FachadaBO.buscarResenasPorVideojuego(idVideojuego);
            List<VideojuegoResenaDTO> resenasVideojuego = new ArrayList<>();
            if(resenas != null && !resenas.isEmpty()){
                resenasVideojuego = obtenerResenas(resenas);
            }
            return Response.ok(resenasVideojuego).build();
        } catch (NegocioException | NumberFormatException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postResena(ResenaDTO nuevaResena){
        try {
            // Validar máximo 1 reseña por cliente
            FachadaBO.crearResena(nuevaResena);
            
            return Response.ok("{\"mensaje\": \"Reseña publicada con éxito\"}").build();
            
        } catch (NegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\": \"Error interno del servidor.\"}")
                    .build();
        }
    }
    
    private List<VideojuegoResenaDTO> obtenerResenas(List<ResenaDTO> resenas) throws NegocioException{
        List<VideojuegoResenaDTO> resenasVideojuegos = new ArrayList<>();
        for(ResenaDTO resena: resenas){

            VideojuegoResenaDTO resenaVideojuego = new VideojuegoResenaDTO();

            UsuarioDTO cliente = FachadaBO.buscarClientePorId(resena.getIdCliente());

            resenaVideojuego.setResena(resena);
            resenaVideojuego.setNombreJugador(cliente.getNombres());

            resenasVideojuegos.add(resenaVideojuego);
        }
        return resenasVideojuegos;
    }
}
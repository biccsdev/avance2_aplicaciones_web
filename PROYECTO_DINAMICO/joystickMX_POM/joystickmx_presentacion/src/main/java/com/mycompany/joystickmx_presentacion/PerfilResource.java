package com.mycompany.joystickmx_presentacion;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Factory.FactoryBO;
/**
 * REST Web Service
 *
 * @author Usuario
 */
@Path("api/perfil")
@RequestScoped
public class PerfilResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PerfilResource
     */
    public PerfilResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.joystickmx_presentacion.PerfilResource
     * @param idUsuario
     * @return an instance of java.lang.String
     */
    @GET
    @Path("usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerUsuario(@PathParam("idUsuario") Long idUsuario){
        try {
            UsuarioDTO usuario = FactoryBO.buscarClientePorId(idUsuario);
            // Obtiene los nombres del usuario
            if(usuario.getNombres() == null || 
                    usuario.getApellidoPaterno() == null ||
                    usuario.getApellidoMaterno() == null ||
                    usuario.getEmail() == null ||
                    usuario.getEstadoUsuario() == null){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"No se encontró un usuario con los datos agregados\"}")
                        .build();
            }
//            usuario.getApellidoPaterno();
//            usuario.getApellidoMaterno();
//            usuario.getEmail();
//            usuario.getEstadoUsuario();
            
            //if(usuario.getNombres() == null)
            //    videojuego.setCategorias(FactoryBO.buscarCategoriaPorVideojuego(videojuego.getIdVideojuego()));
            // Si se requieren las reseñas del videojuego, se consultan por separado.
            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /**
     * PUT method for updating or creating an instance of PerfilResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}

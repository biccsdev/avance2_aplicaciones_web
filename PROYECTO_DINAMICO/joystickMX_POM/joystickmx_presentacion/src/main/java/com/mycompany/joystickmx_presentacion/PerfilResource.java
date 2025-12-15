package com.mycompany.joystickmx_presentacion;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Fachada.FachadaBO;
/**
 * REST Web Service
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Path("api/perfil")
@RequestScoped
public class PerfilResource {

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of PerfilResource
     */
    public PerfilResource() {}

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
            UsuarioDTO usuario = FachadaBO.buscarClientePorId(idUsuario);
            // Valida si el usuario existe
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"Usuario no encontrado.\"}")
                        .build();
            }
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
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(UsuarioRegistroDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioActualizado = FachadaBO.actualizarUsuario(usuarioDTO);

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("usuario", usuarioActualizado);
            }

            return Response.ok("{\"mensaje\": \"Perfil actualizado correctamente\"}").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
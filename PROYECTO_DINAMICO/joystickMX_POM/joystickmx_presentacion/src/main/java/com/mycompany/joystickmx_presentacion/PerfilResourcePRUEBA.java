/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.joystickmx_presentacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 *
 * @author sonic
 */
@Path("usuarios")
@RequestScoped
public class PerfilResourcePRUEBA {

    @Context
    private HttpServletRequest request;

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(UsuarioRegistroDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioActualizado = FactoryBO.actualizarUsuario(usuarioDTO);

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

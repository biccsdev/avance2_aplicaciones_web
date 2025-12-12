package com.mycompany.joystickmx_presentacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.Factory.FactoryBO;

/**
 * API para gestionar el carrito de compras
 *
 * @author PC Gamer
 */
@Path("carrito")
@RequestScoped
public class CarritoResource {


    public CarritoResource() {
    }


    @GET
    @Path("usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarritoPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {

            CarritoDTO carrito = FactoryBO.buscarCarritoPorCliente(idUsuario);

            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"El usuario no tiene un carrito activo.\"}")
                        .build();
            }

            List<ItemCarritoDTO> items = FactoryBO.obtenerItemsCarrito(carrito.getIdCarrito());
            carrito.setItems(items);

            return Response.ok(carrito).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}

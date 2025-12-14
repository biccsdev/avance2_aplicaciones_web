package com.mycompany.joystickmx_presentacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.Fachada.FachadaBO;

/**
 * API para gestionar el carrito de compras
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Path("carrito")
@RequestScoped
public class CarritoResource {

    @GET
    @Path("usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarritoPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
            CarritoDTO carrito = FachadaBO.buscarCarritoPorCliente(idUsuario);
            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"El usuario no tiene un carrito activo.\"}")
                        .build();
            }
            List<ItemCarritoDTO> items = FachadaBO.obtenerItemsCarrito(carrito.getIdCarrito());
            carrito.setItems(items);
            return Response.ok(carrito).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("usuario/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarItem(@PathParam("idUsuario") Long idUsuario, ItemCarritoDTO item) {
        try {
            CarritoDTO carrito = FachadaBO.buscarCarritoPorCliente(idUsuario);
            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Collections.singletonMap("mensaje", "El usuario no tiene un carrito activo."))
                        .build();
            }

            item.setIdCarrito(carrito.getIdCarrito());
            FachadaBO.agregarItemACarrito(carrito.getIdCarrito(), item);

            return Response.ok(Collections.singletonMap("mensaje", "Producto agregado al carrito")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(Collections.singletonMap("error", e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("item/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCantidad(@PathParam("idItem") Long idItem, @QueryParam("cantidad") int cantidad) {
        try {
            FachadaBO.actualizarCantidadItem(idItem, cantidad);
            return Response.ok("{\"mensaje\": \"Cantidad actualizada\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("item/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarItem(@PathParam("idItem") Long idItem) {
        try {
            FachadaBO.eliminarItemCarrito(idItem);
            return Response.ok("{\"mensaje\": \"Item eliminado\"}").build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("usuario/{idUsuario}/vaciar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vaciarCarritoUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
            CarritoDTO carrito = FachadaBO.buscarCarritoPorCliente(idUsuario);
            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"mensaje\": \"No se encontró el carrito.\"}")
                        .build();
            }

            FachadaBO.vaciarCarrito(carrito.getIdCarrito());

            return Response.ok("{\"mensaje\": \"Carrito vaciado correctamente\"}").build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("item/{idUsuario}/verificar/{idVideojuego}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarVideojuegoEnCarrito(@PathParam("idUsuario") Long idUsuario, @PathParam("idVideojuego") Long idVideojuego) {
        try {
            Long idCarrito = FachadaBO.buscarCarritoPorCliente(idUsuario).getIdCarrito();
            ItemCarritoDTO item = FachadaBO.buscarVideojuegoEnCarrito(idCarrito, idVideojuego);
            return Response.ok(item).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("usuario/{idUsuario}/validar-stock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarStock(@PathParam("idUsuario") Long idUsuario) {
        try {
            List<String> errores = FachadaBO.validarExistenciasVideojuego(idUsuario);

            if (errores.isEmpty()) {
                return Response.ok("{\"valido\": true}").build();
            } else {
                return Response.ok(errores).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\": \"Error al validar stock: " + e.getMessage() + "\"}")
                    .build();
        }
    }

}
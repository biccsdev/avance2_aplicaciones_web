package com.mycompany.joystickmx_presentacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO; 
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.MetodoPago;
/**
 * 
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Path("pedidos")
@RequestScoped
public class PedidosResource {

    @GET
    @Path("usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPedidosPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
            List<PedidoDTO> pedidos = FachadaBO.buscarPedidosPorCliente(idUsuario);
            return Response.ok(pedidos).build();
        } catch (Exception e) {
             return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{idPedido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDetallePedido(@PathParam("idPedido") Long idPedido) {
        try {
            PedidoDTO pedido = FachadaBO.buscarPedidoPorId(idPedido);
            
            if (pedido == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Pedido no encontrado\"}")
                        .build();
            }
            
            return Response.ok(pedido).build();
        } catch (Exception e) {
             return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPedido(PedidoDTO request) {
        try {
            Long idCliente = request.getIdCliente();
            if (idCliente == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"El ID del usuario es requerido\"}")
                        .build();
            }

            PagoDTO pagoDTO = new PagoDTO();
            pagoDTO.setMonto(request.getTotalPagado());

            try {
                pagoDTO.setMetodoPago(request.getMetodoPago().toUpperCase());
            } catch (IllegalArgumentException | NullPointerException e) {
                pagoDTO.setMetodoPago(MetodoPago.CONTRA_PAGO.toString().toUpperCase());
            }


            
            LocalDateTime localdateTime = LocalDateTime.now();
             pagoDTO.setFechaPago( localdateTime); 
            pagoDTO.setEstadoPago(EstadoPago.PENDIENTE.toString().toUpperCase());
            pagoDTO.setMonto(pagoDTO.getMonto() + 100);


            
            UsuarioDTO usuario = FachadaBO.buscarClientePorId(idCliente);
            
            DireccionDTO direccion = FachadaBO.obtenerDireccionUsuario(usuario.getEmail());
            


            PedidoDTO pedidoCreado = FachadaBO.registrarPedido(idCliente, direccion, pagoDTO);

            return Response.ok("{\"mensaje\": \"Pedido creado exitosamente\", \"idPedido\": " + pedidoCreado.getIdPedido()+ "}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\": \"Error al procesar el pedido: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
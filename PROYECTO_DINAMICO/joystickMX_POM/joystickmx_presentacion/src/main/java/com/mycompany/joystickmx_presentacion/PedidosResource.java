package com.mycompany.joystickmx_presentacion;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
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
    
    @Context
    private HttpServletRequest request;
    
    /**
     * Obtiene el historial completo de pedidos de un usuario específico. Valida
     * que el usuario solicitante sea el dueño de la cuenta.
     *
     * @param idUsuario El ID del usuario del cual se buscan los pedidos.
     * @return Response 200 OK con la lista de {@link PedidoDTO}, 403 Forbidden
     * si intenta ver pedidos ajenos, o 500 en error interno.
     */
    @GET
    @Path("usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPedidosPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        try {
            if (!validarAccesoUsuario(idUsuario)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Acceso denegado. No puedes ver los pedidos de otro usuario.\"}")
                        .build();
            }

            List<PedidoDTO> pedidos = FachadaBO.buscarPedidosPorCliente(idUsuario);
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Obtiene los detalles específicos de un pedido por su ID. Verifica que el
     * pedido exista y que pertenezca al usuario en sesión.
     *
     * @param idPedido El ID único del pedido a consultar.
     * @return Response 200 OK con el {@link PedidoDTO}, 404 si no existe, 403
     * si no es dueño del pedido, o 500 si hay error de datos.
     */
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

            if (pedido.getCliente() == null || pedido.getCliente().getIdUsuario() == null) {
                return Response.serverError()
                        .entity("{\"error\": \"Error en los datos del pedido.\"}")
                        .build();
            }

            Long idDuenoPedido = pedido.getCliente().getIdUsuario();

            if (!validarAccesoUsuario(idDuenoPedido)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Acceso denegado. Este pedido no te pertenece.\"}")
                        .build();
            }

            return Response.ok(pedido).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Registra un nuevo pedido en el sistema. Procesa la información de pago,
     * asigna dirección de envío y calcula totales.
     *
     * @param request Objeto {@link PedidoDTO} con la información básica para la
     * orden (idCliente, total, metodoPago).
     * @return Response 200 OK con mensaje de éxito e ID del pedido, 400 Bad
     * Request si faltan datos, o 500 en error de procesamiento.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPedido(PedidoDTO request) {
        try {
            Long idCliente = request.getIdCliente();

            if (!validarAccesoUsuario(idCliente)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"No puedes crear pedidos para otro usuario.\"}")
                        .build();
            }

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
            pagoDTO.setFechaPago(localdateTime);
            pagoDTO.setEstadoPago(EstadoPago.PENDIENTE.toString().toUpperCase());

            pagoDTO.setMonto(pagoDTO.getMonto() + 100);

            UsuarioDTO usuario = FachadaBO.buscarClientePorId(idCliente);

            DireccionDTO direccion = FachadaBO.obtenerDireccionUsuario(usuario.getEmail());

            PedidoDTO pedidoCreado = FachadaBO.registrarPedido(idCliente, direccion, pagoDTO);

            return Response.ok("{\"mensaje\": \"Pedido creado exitosamente\", \"idPedido\": " + pedidoCreado.getIdPedido() + "}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\": \"Error al procesar el pedido: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /**
     * Método auxiliar de seguridad. Compara el ID de usuario solicitado con el
     * ID del usuario guardado en la sesión HTTP.
     *
     * * @param idUsuarioObjetivo El ID que se intenta acceder/modificar.
     * @return true si coinciden (es el dueño), false si no hay sesión o no
     * coinciden.
     */
    private boolean validarAccesoUsuario(Long idUsuarioObjetivo) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            return false;
        }

        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuario");
        return usuarioLogueado.getIdUsuario().equals(idUsuarioObjetivo);
    }
}

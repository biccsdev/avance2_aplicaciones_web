package joystickmx.itson.BO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Pago;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.EstadoPedido;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.itson.interfaces.IClienteDAO;
import joystickmx.itson.interfaces.IPedidoDAO;
import joystickmx.itson.interfaces.IVideojuegoDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IPedidoBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class PedidoBO implements IPedidoBO {
    
    private final IPedidoDAO pedidoDAO;
    private final IClienteDAO clienteDAO;
    private final ICarritoDAO carritoDAO;
    private final IVideojuegoDAO videojuegoDAO;

    public PedidoBO(IPedidoDAO pedidoDAO, IClienteDAO clienteDAO, ICarritoDAO carritoDAO, IVideojuegoDAO videojuegoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.clienteDAO = clienteDAO;
        this.carritoDAO = carritoDAO;
        this.videojuegoDAO = videojuegoDAO;
    }

    @Override
    public PedidoDTO registrarPedido(Long idCliente, DireccionDTO direccionEnvioDTO, PagoDTO pagoDTO) throws NegocioException {
        try {
            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                throw new NegocioException("Cliente no encontrado.");
            }
            Carrito carrito = carritoDAO.buscarPorCliente(cliente);
            if (carrito == null || carrito.getItems().isEmpty()) {
                throw new NegocioException("El carrito está vacío.");
            }

            float totalCalculado = 0;
            List<DetallePedido> detallesDelPedido = new ArrayList<>();
            List<Videojuego> videojuegosParaActualizar = new ArrayList<>();

            for (ItemCarrito item : carrito.getItems()) {
                Videojuego videojuego = videojuegoDAO.buscarPorId(item.getVideojuego().getIdVideojuego());
                
                if (videojuego.getExistencias() < item.getCantidad()) {
                    throw new NegocioException("Stock insuficiente para: " + videojuego.getNombre());
                }
                
                videojuego.setExistencias(videojuego.getExistencias() - item.getCantidad());
                videojuegosParaActualizar.add(videojuego);

                float subtotal = videojuego.getPrecio() * item.getCantidad();
                totalCalculado += subtotal;
                
                DetallePedido detalle = new DetallePedido(
                    null,
                    videojuego, 
                    item.getCantidad(), 
                    videojuego.getPrecio()
                );
                detallesDelPedido.add(detalle);
            }

            Pago pago = DTOMapeadores.toPagoEntity(pagoDTO);
            pago.setMonto(totalCalculado);
            pago.setEstadoPago(EstadoPago.CONFIRMADO); 
            pago.setFechaPago(LocalDateTime.now());

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setFechaPedido(LocalDateTime.now());
            pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
            pedido.setTotalPagado(totalCalculado);
            pedido.setDireccionEnvio(DTOMapeadores.toDireccionEnvioEntity(direccionEnvioDTO));
            pedido.setPago(pago);

            for (DetallePedido detalle : detallesDelPedido) {
                detalle.setPedido(pedido);
            }
            pedido.setDetalles(detallesDelPedido);
            pedidoDAO.crearPedido(pedido);

            for (Videojuego vj : videojuegosParaActualizar) {
                videojuegoDAO.actualizar(vj);
            }

            carritoDAO.vaciarCarrito(carrito.getIdCarrito());
            
            return Mapeadores.toPedidoDTO(pedido);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en la base de datos al registrar el pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidos() throws NegocioException {
        try {
            return this.pedidoDAO.obtenerPedidos().stream()
                    .map(Mapeadores::toPedidoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener lista de pedidos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DetallePedidoDTO> obtenerDetallesPedido(Long idPedido) throws NegocioException {
        try {
            return this.pedidoDAO.obtenerDetallesPedido(idPedido).stream()
                    .map(Mapeadores::toDetallePedidoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener detalles del pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public PedidoDTO buscarPorId(Long idPedido) throws NegocioException {
        try {
            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                return null;
            }
            return Mapeadores.toPedidoDTO(pedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en BO al buscar pedido por ID: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void pedidoEntregado(Long idpedido) throws NegocioException {
        try {
            this.pedidoDAO.pedidoEntregado(idpedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al activar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void pedidoPendiente(Long idpedido) throws NegocioException {
        try {
            this.pedidoDAO.pedidoPendiente(idpedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al desactivar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void pedidoEnviado(Long idpedido) throws NegocioException {
        try {
            this.pedidoDAO.pedidoEnviado(idpedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar (soft delete) usuario: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void pedidoCancelado(Long idpedido) throws NegocioException {
        try {
            this.pedidoDAO.pedidoCancelado(idpedido);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar (soft delete) usuario: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<PedidoDTO> buscarPorNombreClienteParcial(String nombreParcial) throws NegocioException {
        try {
            List<Pedido> pedidosEncontrados = this.pedidoDAO.buscarPorNombreClienteParcial(nombreParcial);
            
            return pedidosEncontrados.stream()
                    .map(Mapeadores::toPedidoDTO)
                    .collect(Collectors.toList());
                    
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar pedidos por nombre parcial de cliente: " + e.getMessage(), e);
        }
    }
}
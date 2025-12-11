
package joystickmx.itson.interfaces;

import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.enums.EstadoPedido;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IPedidoDAO {

    void crearPedido(Pedido pedido) throws PersistenciaException;

    Pedido actualizarPedido(Pedido pedido) throws PersistenciaException;

    Pedido buscarPorId(Long idPedido) throws PersistenciaException;

    List<Pedido> obtenerPedidos() throws PersistenciaException;

    List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException;

    List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException;

    List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

    List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException;
    
    void pedidoCancelado(Long idPedido) throws PersistenciaException;

    void pedidoEntregado(Long idPedido) throws PersistenciaException;

    void pedidoPendiente(Long idPedido) throws PersistenciaException;
    
    void pedidoEnviado(Long idPedido) throws PersistenciaException;
    
    List<Pedido> buscarPorNombreClienteParcial(String nombreParcial) throws PersistenciaException;
}
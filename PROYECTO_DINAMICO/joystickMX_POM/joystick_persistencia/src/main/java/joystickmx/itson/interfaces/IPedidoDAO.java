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
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IPedidoDAO {

    public void crearPedido(Pedido pedido) throws PersistenciaException;

    public Pedido actualizarPedido(Pedido pedido) throws PersistenciaException;

    public Pedido buscarPorId(Long idPedido) throws PersistenciaException;

    public List<Pedido> obtenerPedidos() throws PersistenciaException;

    public List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException;

    public List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException;

    public List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

    public List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException;
    
    public void pedidoCancelado(Long idPedido) throws PersistenciaException;

    public void pedidoEntregado(Long idPedido) throws PersistenciaException;

    public void pedidoPendiente(Long idPedido) throws PersistenciaException;
    
    public void pedidoEnviado(Long idPedido) throws PersistenciaException;
    
    public List<Pedido> buscarPorNombreClienteParcial(String nombreParcial) throws PersistenciaException;
}
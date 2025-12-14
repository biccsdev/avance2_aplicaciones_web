package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IPedidoBO {
    
    /**
     * Lógica de negocio principal para registrar un pedido.
     * Valida stock, calcula total, actualiza inventario y vacía el carrito.
     * @param idCliente
     * @param direccionEnvioDTO
     * @param pagoDTO
     * @return 
     * @throws NegocioException
     */
    public PedidoDTO registrarPedido(Long idCliente, DireccionDTO direccionEnvioDTO, PagoDTO pagoDTO) throws NegocioException;
    
    public List<PedidoDTO> obtenerPedidos() throws NegocioException;
    
    public List<DetallePedidoDTO> obtenerDetallesPedido(Long idPedido) throws NegocioException;
    
    public PedidoDTO buscarPorId(Long idPedido) throws NegocioException;
    
    public void pedidoEntregado(Long idpedido) throws NegocioException;
    
    public void pedidoPendiente(Long idpedido) throws NegocioException;
    
    public void pedidoEnviado(Long idpedido) throws NegocioException;
    
    public void pedidoCancelado(Long idpedido) throws NegocioException;
    
    /**
     * Busca pedidos que coincidan parcialmente con el nombre completo de un cliente.
     *
     * @param nombreParcial El texto a buscar en los nombres y apellidos del cliente.
     * @return Una lista de PedidoDTO que coinciden.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<PedidoDTO> buscarPorNombreClienteParcial(String nombreParcial) throws NegocioException;

    public List<PedidoDTO> buscarPorCliente(Long idCliente) throws NegocioException;
}
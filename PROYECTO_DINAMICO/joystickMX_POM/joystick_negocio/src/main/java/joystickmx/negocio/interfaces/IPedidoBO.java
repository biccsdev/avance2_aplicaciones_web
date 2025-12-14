package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IPedidoBO {
    
    public PedidoDTO registrarPedido(Long idCliente, DireccionDTO direccionEnvioDTO, PagoDTO pagoDTO) throws NegocioException;
    
    public List<PedidoDTO> obtenerPedidos() throws NegocioException;
    
    public List<DetallePedidoDTO> obtenerDetallesPedido(Long idPedido) throws NegocioException;
    
    public PedidoDTO buscarPorId(Long idPedido) throws NegocioException;
    
    public void pedidoEntregado(Long idpedido) throws NegocioException;
    
    public void pedidoPendiente(Long idpedido) throws NegocioException;
    
    public void pedidoEnviado(Long idpedido) throws NegocioException;
    
    public void pedidoCancelado(Long idpedido) throws NegocioException;
    
    public List<PedidoDTO> buscarPorNombreClienteParcial(String nombreParcial) throws NegocioException;

    public List<PedidoDTO> buscarPorCliente(Long idCliente) throws NegocioException;
}
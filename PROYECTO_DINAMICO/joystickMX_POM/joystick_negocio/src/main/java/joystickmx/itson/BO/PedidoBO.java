/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DAOS.PedidoDAO;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Pedido;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
public class PedidoBO {
    
    private final PedidoDAO pedidoDAO;

    public PedidoBO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public List<PedidoDTO> obtenerPedidos() throws NegocioException {
        try {
            return this.pedidoDAO.obtenerPedidos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener lista de pedidos: " + e.getMessage(), e);
        }
    }

    public List<DetallePedidoDTO> obtenerDetallesPedido(Long idPedido) throws NegocioException {
        try {
            
            Pedido pedidoTemporal = new Pedido(); //   "una entidad de mentis para que acceda al dao como entidad"
            pedidoTemporal.setIdPedido(idPedido);
            
            return this.pedidoDAO.obtenerDetallesPedido(pedidoTemporal).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener detalles del pedido: " + e.getMessage(), e);
        }
    }
}

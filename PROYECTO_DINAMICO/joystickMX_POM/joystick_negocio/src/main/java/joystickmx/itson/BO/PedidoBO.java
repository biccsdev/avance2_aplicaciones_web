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
 * @author biccs
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
            return this.pedidoDAO.obtenerDetallesPedido(idPedido).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener detalles del pedido: " + e.getMessage(), e);
        }
    }

    public void crearPedido(PedidoDTO dto) throws NegocioException {
        try {
            this.pedidoDAO.crearPedido(Mapeadores.toEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear pedido: " + e.getMessage(), e);
        }
    }

    public PedidoDTO actualizarPedido(PedidoDTO dto) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.pedidoDAO.actualizarPedido(Mapeadores.toEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar pedido: " + e.getMessage(), e);
        }
    }

    public PedidoDTO buscarPorId(Long idPedido) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.pedidoDAO.buscarPorId(idPedido));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar pedido por ID: " + e.getMessage(), e);
        }
    }
}

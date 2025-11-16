/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
 * @author PC Gamer
 */
public interface IPedidoDAO {

    void crearPedido(Pedido pedido) throws PersistenciaException;

    Pedido actualizarPedido(Pedido pedido) throws PersistenciaException;

    void actualizarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) throws PersistenciaException;

    Pedido buscarPorId(Long idPedido) throws PersistenciaException;

    List<Pedido> obtenerPedidos() throws PersistenciaException;

    List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException;

    List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException;

    List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

    List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException;
}

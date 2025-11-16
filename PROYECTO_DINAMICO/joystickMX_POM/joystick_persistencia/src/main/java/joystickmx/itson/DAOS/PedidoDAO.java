package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.enums.EstadoPedido;
import joystickmx.itson.interfaces.IPedidoDAO;

/**
 * 
* @author PC Gamer
* @author biccs
 */
public class PedidoDAO extends BaseDAO implements IPedidoDAO {

    public PedidoDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearPedido(Pedido pedido) throws PersistenciaException {
        try {
            em.persist(pedido);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear el pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) throws PersistenciaException {
        try {
            return em.merge(pedido);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    @Override
    public void actualizarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) throws PersistenciaException {
        try {
            Pedido pedido = em.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new PersistenciaException("No se encontró el pedido con ID: " + idPedido);
            }
            pedido.setEstadoPedido(nuevoEstado);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al actualizar estado del pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) throws PersistenciaException {
        try {
            return em.find(Pedido.class, idPedido);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar pedido por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> obtenerPedidos() throws PersistenciaException {
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p ORDER BY p.fechaPedido DESC", Pedido.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al obtener la lista de pedidos: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException {
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.cliente.idUsuario = :clienteId ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("clienteId", cliente.getIdUsuario());
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar pedidos por cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException {
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.estadoPedido = :estado ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar pedidos por estado: " + e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :inicio AND :fin ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("inicio", fechaInicio.atStartOfDay());
            query.setParameter("fin", fechaFin.plusDays(1).atStartOfDay());
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar pedidos por rango de fecha: " + e.getMessage());
        }
    }

    @Override
    public List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException {
        try {
            TypedQuery<DetallePedido> query = em.createQuery(
                    "SELECT d FROM DetallePedido d WHERE d.pedido.idPedido = :pid",
                    DetallePedido.class
            );
            query.setParameter("pid", idPedido);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido: " + e.getMessage());
        }
    }
}
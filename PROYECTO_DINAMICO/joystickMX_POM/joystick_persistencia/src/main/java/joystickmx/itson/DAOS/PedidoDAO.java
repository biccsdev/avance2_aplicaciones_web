package joystickmx.itson.DAOS;

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

    @Override
    public void crearPedido(Pedido pedido) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear el pedido: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Pedido pedidoActualizado = em.merge(pedido);
            em.getTransaction().commit();
            return pedidoActualizado;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar el pedido: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void actualizarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, idPedido);
            if (pedido == null) 
                throw new PersistenciaException("No se encontró el pedido con ID: " + idPedido);
            pedido.setEstadoPedido(nuevoEstado);
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar estado del pedido: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Pedido.class, idPedido);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar pedido por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Pedido> obtenerPedidos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p ORDER BY p.fechaPedido DESC", Pedido.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al obtener la lista de pedidos: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.cliente.idUsuario = :clienteId ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("clienteId", cliente.getIdUsuario());
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar pedidos por cliente: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.estadoPedido = :estado ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar pedidos por estado: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        iniciarConexion();
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
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<DetallePedido> query = em.createQuery(
                    "SELECT d FROM DetallePedido d WHERE d.pedido.idPedido = :pid",
                    DetallePedido.class
            );
            query.setParameter("pid", idPedido);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }
}
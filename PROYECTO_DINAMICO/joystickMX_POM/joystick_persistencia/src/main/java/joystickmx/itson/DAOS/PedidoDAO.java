package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.enums.EstadoPedido;

/**
 * 
* @author PC Gamer
* @author biccs
 */
public class PedidoDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearPedido(Pedido pedido) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Pedido actualizarPedido(Pedido pedido) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido managedPedido = em.merge(pedido);
            em.getTransaction().commit();
            return managedPedido;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void actualizarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new PersistenciaException("No se encontró el pedido con ID: " + idPedido);
            }
            pedido.setEstadoPedido(nuevoEstado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al actualizar estado del pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Pedido buscarPorId(Long idPedido) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, idPedido);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedido por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Pedido> obtenerPedidos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p ORDER BY p.fechaPedido DESC", Pedido.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de pedidos: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Pedido> buscarPorCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.cliente.idUsuario = :clienteId ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("clienteId", cliente.getIdUsuario());
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.estadoPedido = :estado ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("estado", estado);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por estado: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Pedido> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :inicio AND :fin ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );
            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por rango de fecha: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<DetallePedido> obtenerDetallesPedido(Long idPedido) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<DetallePedido> query = em.createQuery(
                    "SELECT d FROM DetallePedido d WHERE d.pedido.idPedido = :pid",
                    DetallePedido.class
            );
            query.setParameter("pid", idPedido);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
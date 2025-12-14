package joystickmx.itson.DAOS;

import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.EstadoPedido;
import joystickmx.itson.enums.MetodoPago;
import joystickmx.itson.interfaces.IPedidoDAO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class PedidoDAO extends BaseDAO implements IPedidoDAO {

    @Override
    public void crearPedido(Pedido pedido) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear el pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar el pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    public void actualizarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new PersistenciaException("No se encontró el pedido con ID: " + idPedido);
            }
            if (pedido.getPago().getMetodoPago() == MetodoPago.CONTRA_PAGO) {

                if (nuevoEstado == EstadoPedido.PENDIENTE || nuevoEstado == EstadoPedido.ENVIADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.PENDIENTE);
                }

                if (nuevoEstado == EstadoPedido.ENTREGADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.CONFIRMADO);
                }

                if (nuevoEstado == EstadoPedido.CANCELADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.RECHAZADO);
                }

            }

            if (pedido.getPago().getMetodoPago() == MetodoPago.PAYPAL || pedido.getPago().getMetodoPago() == MetodoPago.TARJETA) {

                if (nuevoEstado == EstadoPedido.CANCELADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.RECHAZADO);
                }

                if (nuevoEstado == EstadoPedido.ENTREGADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.CONFIRMADO);
                }

                if (nuevoEstado == EstadoPedido.PENDIENTE || nuevoEstado == EstadoPedido.ENVIADO) {
                    pedido.getPago().setEstadoPago(EstadoPago.CONFIRMADO);
                }

            }

            pedido.setEstadoPedido(nuevoEstado);
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar estado del pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) throws PersistenciaException {
        iniciarConexion();
        try {

            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p "
                    + "LEFT JOIN FETCH p.cliente "
                    + "LEFT JOIN FETCH p.direccionEnvio "
                    + "LEFT JOIN FETCH p.pago "
                    + "LEFT JOIN FETCH p.detalles d "
                    + "LEFT JOIN FETCH d.videojuego "
                    + "WHERE p.idPedido = :pid", Pedido.class);
            query.setParameter("pid", idPedido);
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedido por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    @Override
    public List<Pedido> obtenerPedidos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p LEFT JOIN FETCH p.cliente ORDER BY p.fechaPedido DESC", Pedido.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de pedidos: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
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
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
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
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por estado: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
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
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por rango de fecha: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
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
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    @Override
    public void pedidoEntregado(Long idPedido) throws PersistenciaException {
        actualizarEstadoPedido(idPedido, EstadoPedido.ENTREGADO);
    }

    @Override
    public void pedidoPendiente(Long idPedido) throws PersistenciaException {
        actualizarEstadoPedido(idPedido, EstadoPedido.PENDIENTE);
    }

    @Override
    public void pedidoEnviado(Long idPedido) throws PersistenciaException {
        actualizarEstadoPedido(idPedido, EstadoPedido.ENVIADO);
    }

    @Override
    public void pedidoCancelado(Long idPedido) throws PersistenciaException {
        actualizarEstadoPedido(idPedido, EstadoPedido.CANCELADO);
    }

    /**
     * Busca pedidos que coincidan parcialmente con el nombre completo de un
     * cliente.
     *
     * @param nombreParcial El texto a buscar en los nombres y apellidos del
     * cliente.
     * @return Una lista de pedidos que coinciden.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Pedido> buscarPorNombreClienteParcial(String nombreParcial) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p "
                    + "WHERE CONCAT(p.cliente.nombres, ' ', p.cliente.apellidoPaterno, ' ', p.cliente.apellidoMaterno) LIKE :nombreParcial "
                    + "ORDER BY p.fechaPedido DESC",
                    Pedido.class
            );

            query.setParameter("nombreParcial", "%" + nombreParcial + "%");

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar pedidos por nombre parcial de cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }
}
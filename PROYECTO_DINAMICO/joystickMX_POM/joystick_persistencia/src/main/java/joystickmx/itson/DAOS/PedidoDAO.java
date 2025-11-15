
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Pedido;

/**
 * 
* @author PC Gamer
 */
public class PedidoDAO {


    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }


    public List<Pedido> obtenerPedidos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p", Pedido.class
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

    public List<DetallePedido> obtenerDetallesPedido(Pedido pedido) throws PersistenciaException {
        if (pedido == null || pedido.getIdPedido() == null) {
            throw new PersistenciaException("El pedido proporcionado es nulo o no tiene ID.");
        }

        EntityManager em = getEntityManager();
        try {
            TypedQuery<DetallePedido> query = em.createQuery(
                    "SELECT d FROM DetallePedido d WHERE d.pedido.idPedido = :pid",
                    DetallePedido.class
            );
            query.setParameter("pid", pedido.getIdPedido());
            return query.getResultList();
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los detalles del pedido ID " + pedido.getIdPedido() + ": " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
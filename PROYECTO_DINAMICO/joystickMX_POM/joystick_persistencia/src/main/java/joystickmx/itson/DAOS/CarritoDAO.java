package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.interfaces.ICarritoDAO;

/**
 * @author biccs
 */
public class CarritoDAO extends BaseDAO implements ICarritoDAO {

    public CarritoDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearCarrito(Carrito carrito) throws PersistenciaException {
        try {
            em.persist(carrito);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear el carrito: " + e.getMessage());
        }
    }

    @Override
    public Carrito actualizarCarrito(Carrito carrito) throws PersistenciaException {
        try {
            return em.merge(carrito);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el carrito: " + e.getMessage());
        }
    }

    @Override
    public Carrito buscarPorId(Long idCarrito) throws PersistenciaException {
        try {
            return em.find(Carrito.class, idCarrito);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar carrito por ID: " + e.getMessage());
        }
    }

    @Override
    public Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException {
        try {
            TypedQuery<Carrito> query = em.createQuery(
                    "SELECT c FROM Carrito c LEFT JOIN FETCH c.items WHERE c IN (SELECT cl.carrito FROM Cliente cl WHERE cl.idUsuario = :clienteId)",
                    Carrito.class
            );
            query.setParameter("clienteId", cliente.getIdUsuario());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar carrito por cliente: " + e.getMessage());
        }
    }

    @Override
    public void agregarItem(Carrito carrito, ItemCarrito item) throws PersistenciaException {
        try {
            item.setCarrito(carrito);
            em.persist(item);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al agregar item al carrito: " + e.getMessage());
        }
    }

    @Override
    public void eliminarItem(Long idItemCarrito) throws PersistenciaException {
        try {
            ItemCarrito item = em.find(ItemCarrito.class, idItemCarrito);
            if (item == null) {
                throw new PersistenciaException("No se encontró el item con ID: " + idItemCarrito);
            }
            em.remove(item);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al eliminar item del carrito: " + e.getMessage());
        }
    }

    @Override
    public void vaciarCarrito(Long idCarrito) throws PersistenciaException {
        try {
            em.createQuery("DELETE FROM ItemCarrito ic WHERE ic.carrito.idCarrito = :carritoId")
                    .setParameter("carritoId", idCarrito)
                    .executeUpdate();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al vaciar el carrito: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCarrito(Long idCarrito) throws PersistenciaException {
        try {
            Carrito carrito = em.find(Carrito.class, idCarrito);
            if (carrito == null) {
                throw new PersistenciaException("No se encontró el carrito con ID: " + idCarrito);
            }
            em.remove(carrito);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al eliminar el carrito: " + e.getMessage());
        }
    }
}

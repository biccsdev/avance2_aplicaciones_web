package joystickmx.itson.DAOS;

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

    @Override
    public void crearCarrito(Carrito carrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(carrito);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear el carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Carrito actualizarCarrito(Carrito carrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Carrito carritoActualizado = em.merge(carrito);
            em.getTransaction().commit();
            return carritoActualizado;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar el carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Carrito buscarPorId(Long idCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Carrito.class, idCarrito);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar carrito por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException {
        iniciarConexion();
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
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void agregarItem(Carrito carrito, ItemCarrito item) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            item.setCarrito(carrito);
            em.persist(item);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al agregar item al carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void eliminarItem(Long idItemCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            ItemCarrito item = em.find(ItemCarrito.class, idItemCarrito);
            if (item == null)
                throw new PersistenciaException("No se encontró el item con ID: " + idItemCarrito);
            em.remove(item);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al eliminar item del carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void vaciarCarrito(Long idCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ItemCarrito ic WHERE ic.carrito.idCarrito = :carritoId")
                    .setParameter("carritoId", idCarrito)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al vaciar el carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void eliminarCarrito(Long idCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Carrito carrito = em.find(Carrito.class, idCarrito);
            if (carrito == null) {
                throw new PersistenciaException("No se encontró el carrito con ID: " + idCarrito);
            }
            em.remove(carrito);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al eliminar el carrito: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }
}
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;

/**
 * @author biccs
 */
public class CarritoDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearCarrito(Carrito carrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(carrito);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Carrito actualizarCarrito(Carrito carrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Carrito managedCarrito = em.merge(carrito);
            em.getTransaction().commit();
            return managedCarrito;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Carrito buscarPorId(Long idCarrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrito.class, idCarrito);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar carrito por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Carrito> query = em.createQuery(
                    "SELECT c FROM Carrito c JOIN FETCH c.items WHERE c IN (SELECT cl.carrito FROM Cliente cl WHERE cl.idUsuario = :clienteId)",
                    Carrito.class
            );
            query.setParameter("clienteId", cliente.getIdUsuario());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar carrito por cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void agregarItem(Carrito carrito, ItemCarrito item) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Carrito managedCarrito = em.merge(carrito);
            item.setCarrito(managedCarrito);
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al agregar item al carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarItem(Long idItemCarrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            ItemCarrito item = em.find(ItemCarrito.class, idItemCarrito);
            if (item == null) {
                throw new PersistenciaException("No se encontró el item con ID: " + idItemCarrito);
            }
            em.remove(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al eliminar item del carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void vaciarCarrito(Long idCarrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ItemCarrito ic WHERE ic.carrito.idCarrito = :carritoId")
                    .setParameter("carritoId", idCarrito)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al vaciar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarCarrito(Long idCarrito) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Carrito carrito = em.find(Carrito.class, idCarrito);
            if (carrito == null) {
                throw new PersistenciaException("No se encontró el carrito con ID: " + idCarrito);
            }
            em.remove(carrito);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al eliminar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}


package joystickmx.itson.DAOS;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Videojuego;
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
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al crear el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
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
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al actualizar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Carrito buscarPorId(Long idCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Carrito.class, idCarrito);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar carrito por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Carrito> query = em.createQuery(
                    "SELECT cr FROM Cliente c JOIN c.carrito cr LEFT JOIN FETCH cr.items WHERE c = :cliente",
                    Carrito.class
            );

            query.setParameter("cliente", cliente);
            return query.getSingleResult();

        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar carrito por cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void agregarItem(Carrito carritoProxy, ItemCarrito item) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();

            Carrito carritoManaged = em.find(Carrito.class, carritoProxy.getIdCarrito());
            if (carritoManaged == null) {
                throw new PersistenciaException("No se encontró el carrito con ID: " + carritoProxy.getIdCarrito());
            }

            Videojuego videojuegoManaged = em.find(Videojuego.class, item.getVideojuego().getIdVideojuego());
            if (videojuegoManaged == null) {
                throw new PersistenciaException("No se encontró el videojuego con ID: " + item.getVideojuego().getIdVideojuego());
            }

            item.setVideojuego(videojuegoManaged);
            item.setCarrito(carritoManaged);        
            carritoManaged.getItems().add(item);        

            em.merge(carritoManaged);

            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al agregar item al carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarItem(Long idItemCarrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            ItemCarrito item = em.find(ItemCarrito.class, idItemCarrito);
            if (item == null) {
                throw new PersistenciaException("No se encontró el item con ID: " + idItemCarrito);
            }
            em.remove(item);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al eliminar item del carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
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
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al vaciar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
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
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al eliminar el carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

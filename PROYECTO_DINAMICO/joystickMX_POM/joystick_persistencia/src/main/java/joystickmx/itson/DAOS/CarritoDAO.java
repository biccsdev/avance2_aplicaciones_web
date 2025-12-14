package joystickmx.itson.DAOS;

import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.interfaces.ICarritoDAO;

/**
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class CarritoDAO extends BaseDAO implements ICarritoDAO {

    @Override
    public void crearCarrito(Carrito carrito) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(carrito);
            em.getTransaction().commit();
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
            // CORRECCIÓN: Agregamos 'JOIN FETCH i.videojuego'
            TypedQuery<Carrito> query = em.createQuery(
                    "SELECT cr FROM Cliente c "
                    + "JOIN c.carrito cr "
                    + "LEFT JOIN FETCH cr.items i "
                    + "LEFT JOIN FETCH i.videojuego "
                    +
                    "WHERE c = :cliente",
                    Carrito.class
            );

            query.setParameter("cliente", cliente);
            return query.getSingleResult();

        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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

    @Override
    public List<ItemCarrito> obtenerItemsCarrito(Carrito carrito) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<ItemCarrito> query = em.createQuery(
                    "SELECT i FROM ItemCarrito i JOIN FETCH i.videojuego WHERE i.carrito.idCarrito = :idCarrito",
                    ItemCarrito.class
            );

            query.setParameter("idCarrito", carrito.getIdCarrito());

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los items del carrito: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public void actualizarCantidadItem(Long idItemCarrito, Integer nuevaCantidad) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            ItemCarrito item = em.find(ItemCarrito.class, idItemCarrito);
            if (item == null) {
                throw new PersistenciaException("El item no existe.");
            }
            item.setCantidad(nuevaCantidad);
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar cantidad: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }
    
    @Override
    public ItemCarrito buscarItemPorId(Long idItemCarrito) throws PersistenciaException{
        iniciarConexion();
        try {
            return em.find(ItemCarrito.class, idItemCarrito);
        } catch (Exception e) {
            throw new PersistenciaException("Error obtener el item: " + e.getMessage());
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }
    
    @Override
    public ItemCarrito buscarVideojuegoEnCarrito(Long idCarrito, Long idVideojuego) throws PersistenciaException{
        iniciarConexion();
        try {
            TypedQuery<ItemCarrito> query = em.createQuery(
                    "SELECT i FROM ItemCarrito i WHERE i.carrito.idCarrito = :idCarrito AND i.videojuego.idVideojuego = :idVideojuego",
                    ItemCarrito.class
            );

            query.setParameter("idCarrito", idCarrito);
            query.setParameter("idVideojuego", idVideojuego);
            
            List<ItemCarrito> items = query.getResultList();
            
            return items != null && !items.isEmpty() ? items.getFirst() : null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el item: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

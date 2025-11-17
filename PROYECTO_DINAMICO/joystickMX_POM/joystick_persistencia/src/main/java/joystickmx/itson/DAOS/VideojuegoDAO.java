
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.interfaces.IVideojuegoDAO;

/**
 *
 * @author sonic
 */
public class VideojuegoDAO extends BaseDAO implements IVideojuegoDAO {

    @Override
    public void persistir(Videojuego videojuego) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(videojuego);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al persistir el videojuego: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Videojuego actualizar(Videojuego videojuego) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Videojuego videojuegoActualizado = em.merge(videojuego);
            em.getTransaction().commit();
            return videojuegoActualizado;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar el videojuego: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    private void cambiarEstado(Long idVideojuego, boolean habilitado) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Videojuego videojuego = em.find(Videojuego.class, idVideojuego);
            if (videojuego == null) 
                throw new PersistenciaException("No se encontró el videojuego con ID: " + idVideojuego);
            videojuego.setHabilitado(habilitado);
            em.merge(videojuego);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al cambiar estado del videojuego: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void habilitarVideojuego(Long idVideojuego) throws PersistenciaException {
        cambiarEstado(idVideojuego, true);
    }

    @Override
    public void deshabilitarVideojuego(Long idVideojuego) throws PersistenciaException {
        cambiarEstado(idVideojuego, false);
    }

    @Override
    public List<Videojuego> buscarTodosLosVideojuegos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v", Videojuego.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todos los videojuegos: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Videojuego> buscarVideojuegosActivos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true", Videojuego.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar videojuegos activos: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Videojuego> buscarPorRangoDePrecio(Float min, Float max) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.precio BETWEEN :min AND :max",
                    Videojuego.class
            );
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por rango de precio: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Videojuego> buscarPorCategoria(Long idCategoria) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v JOIN v.categorias c WHERE v.habilitado = true AND c.idCategoria = :idCategoria",
                    Videojuego.class
            );
            query.setParameter("idCategoria", idCategoria);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por categoría: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Videojuego> buscarPorNombre(String nombre) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.nombre LIKE :nombre",
                    Videojuego.class
            );
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por nombre: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }
    
    @Override
    public Videojuego buscarPorNombreExacto(String nombre) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.nombre = :nombre",
                    Videojuego.class
            );
            query.setParameter("nombre",nombre);
            return query.getSingleResult();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por nombre: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Videojuego buscarPorId(Long idVideojuego) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Videojuego.class, idVideojuego);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar videojuego por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }
}

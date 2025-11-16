
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

    public VideojuegoDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void persistir(Videojuego videojuego) throws PersistenciaException {
        try {
            em.persist(videojuego);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al persistir el videojuego: " + e.getMessage());
        }
    }

    @Override
    public Videojuego actualizar(Videojuego videojuego) throws PersistenciaException {
        try {
            return em.merge(videojuego);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el videojuego: " + e.getMessage());
        }
    }

    private void cambiarEstado(Long idVideojuego, boolean habilitado) throws PersistenciaException {
        try {
            Videojuego videojuego = em.find(Videojuego.class, idVideojuego);
            if (videojuego == null) {
                throw new PersistenciaException("No se encontró el videojuego con ID: " + idVideojuego);
            }
            videojuego.setHabilitado(habilitado);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al cambiar estado del videojuego: " + e.getMessage());
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
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v", Videojuego.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todos los videojuegos: " + e.getMessage());
        }
    }

    @Override
    public List<Videojuego> buscarVideojuegosActivos() throws PersistenciaException {
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true", Videojuego.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar videojuegos activos: " + e.getMessage());
        }
    }

    @Override
    public List<Videojuego> buscarPorRangoDePrecio(Float min, Float max) throws PersistenciaException {
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
        }
    }

    @Override
    public List<Videojuego> buscarPorCategoria(Long idCategoria) throws PersistenciaException {
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v JOIN v.categorias c WHERE v.habilitado = true AND c.idCategoria = :idCategoria",
                    Videojuego.class
            );
            query.setParameter("idCategoria", idCategoria);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por categoría: " + e.getMessage());
        }
    }

    @Override
    public List<Videojuego> buscarPorNombre(String nombre) throws PersistenciaException {
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.nombre LIKE :nombre",
                    Videojuego.class
            );
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar por nombre: " + e.getMessage());
        }
    }

    @Override
    public Videojuego buscarPorId(Long idVideojuego) throws PersistenciaException {
        try {
            return em.find(Videojuego.class, idVideojuego);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar videojuego por ID: " + e.getMessage());
        }
    }
}

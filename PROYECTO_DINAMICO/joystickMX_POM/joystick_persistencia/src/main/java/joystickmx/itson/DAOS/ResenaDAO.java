package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Resena;
import joystickmx.itson.interfaces.IResenaDAO;

/**
 *
 * @author sonic
 * @author biccs
 */
public class ResenaDAO extends BaseDAO implements IResenaDAO {

    public ResenaDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearResena(Resena resena) throws PersistenciaException {
        try {
            em.persist(resena);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear la reseña: " + e.getMessage());
        }
    }

    @Override
    public Resena actualizarResena(Resena resena) throws PersistenciaException {
        try {
            return em.merge(resena);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar la reseña: " + e.getMessage());
        }
    }

    @Override
    public void eliminarResena(Long idResena) throws PersistenciaException {
        try {
            Resena resena = em.find(Resena.class, idResena);
            if (resena == null) {
                throw new PersistenciaException("No se encontró la reseña con ID: " + idResena);
            }
            em.remove(resena);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al eliminar la reseña: " + e.getMessage());
        }
    }

    @Override
    public Resena buscarPorId(Long idResena) throws PersistenciaException {
        try {
            return em.find(Resena.class, idResena);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar reseña por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Resena> buscarPorVideojuego(Long idVideojuego) throws PersistenciaException {
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.videojuego.idVideojuego = :idVideojuego ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("idVideojuego", idVideojuego);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar reseñas por videojuego: " + e.getMessage());
        }
    }

    @Override
    public List<Resena> buscarPorCliente(Long idCliente) throws PersistenciaException {
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.cliente.idUsuario = :idCliente ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar reseñas por cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Resena> buscarPorCalificacion(Integer calificacion) throws PersistenciaException {
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.calificacion = :calificacion ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("calificacion", calificacion.floatValue());
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar reseñas por calificación: " + e.getMessage());
        }
    }

    @Override
    public List<Resena> buscarTodas() throws PersistenciaException {
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todas las reseñas: " + e.getMessage());
        }
    }
}

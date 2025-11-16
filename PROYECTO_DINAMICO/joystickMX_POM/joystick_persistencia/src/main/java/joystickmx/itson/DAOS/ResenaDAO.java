package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Resena;

/**
 *
 * @author sonic
 * @author biccs
 */
public class ResenaDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearResena(Resena resena) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(resena);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear la reseña: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Resena actualizarResena(Resena resena) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Resena managedResena = em.merge(resena);
            em.getTransaction().commit();
            return managedResena;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar la reseña: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarResena(Long idResena) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Resena resena = em.find(Resena.class, idResena);
            if (resena == null) {
                throw new PersistenciaException("No se encontró la reseña con ID: " + idResena);
            }
            em.remove(resena);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al eliminar la reseña: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Resena buscarPorId(Long idResena) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resena.class, idResena);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseña por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Resena> buscarPorVideojuego(Long idVideojuego) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.videojuego.idVideojuego = :idVideojuego ORDER BY r.fecha DESC",
                    Resena.class
            );
            query.setParameter("idVideojuego", idVideojuego);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por videojuego: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Resena> buscarPorCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.cliente.idUsuario = :idCliente ORDER BY r.fecha DESC",
                    Resena.class
            );
            query.setParameter("idCliente", idCliente);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Resena> buscarPorCalificacion(Integer calificacion) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.calificacion = :calificacion ORDER BY r.fecha DESC",
                    Resena.class
            );
            query.setParameter("calificacion", calificacion);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por calificación: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Resena> buscarTodas() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r ORDER BY r.fecha DESC",
                    Resena.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todas las reseñas: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

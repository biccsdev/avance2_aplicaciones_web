package joystickmx.itson.DAOS;

import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Resena;
import joystickmx.itson.interfaces.IResenaDAO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class ResenaDAO extends BaseDAO implements IResenaDAO {

    @Override
    public void crearResena(Resena resena) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(resena);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear la reseña: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Resena actualizarResena(Resena resena) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Resena resenaActualizada = em.merge(resena);
            em.getTransaction().commit();
            return resenaActualizada;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar la reseña: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public void eliminarResena(Long idResena) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Resena resena = em.find(Resena.class, idResena);
            if (resena == null) 
                throw new PersistenciaException("No se encontró la reseña con ID: " + idResena);
            em.remove(resena);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al eliminar la reseña: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Resena buscarPorId(Long idResena) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Resena.class, idResena);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseña por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public List<Resena> buscarPorVideojuego(Long idVideojuego) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.videojuego.idVideojuego = :idVideojuego ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("idVideojuego", idVideojuego);
            List<Resena> resenas = query.getResultList();
            resenas.forEach(r -> {
                r.getCliente(); // Obtiene el cliente de cada reseña
                r.getVideojuego(); // Obtiene el videojuego de cada reseña
            });
            return resenas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por videojuego: " + e.getMessage());
        } finally{
            if (em.isOpen()) { em.close(); }
        }
    }
    
    @Override
    public List<Resena> buscarPorNombreVideojuego(String nombreVideojuego) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.videojuego.nombre = :nombreVideojuego ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("nombreVideojuego", nombreVideojuego);
            List<Resena> resenas = query.getResultList();
            resenas.forEach(r -> {
                r.getCliente(); // Obtiene el cliente de cada reseña
                r.getVideojuego(); // Obtiene el videojuego de cada reseña
            });
            return resenas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por videojuego: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public List<Resena> buscarPorCliente(Long idCliente) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.cliente.idUsuario = :idCliente ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("idCliente", idCliente);
            List<Resena> resenas = query.getResultList();
            resenas.forEach(r -> {
                r.getCliente(); // Obtiene el cliente de cada reseña
                r.getVideojuego(); // Obtiene el videojuego de cada reseña
            });
            return resenas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por cliente: " + e.getMessage());
        } finally{
            if (em.isOpen()) { em.close();}
        }
    }
    
    @Override
    public Resena buscarPorVideojuegoCliente(Long idCliente, Long idVideojuego) throws PersistenciaException{
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.cliente.idUsuario = :idCliente AND r.videojuego.idVideojuego = :idVideojuego",
                    Resena.class
            );
            query.setParameter("idCliente", idCliente);
            query.setParameter("idVideojuego", idVideojuego);
            
            return query.getResultList().getFirst();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por cliente: " + e.getMessage());
        } finally{
            if (em.isOpen()) { em.close();}
        }
    }
    
    @Override
    public List<Resena> buscarPorCalificacion(Float calificacion) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r WHERE r.calificacion = :calificacion ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            query.setParameter("calificacion", calificacion);
            
            List<Resena> resenas = query.getResultList();
            resenas.forEach(r -> {
                r.getCliente(); // Obtiene el cliente de cada reseña
                r.getVideojuego(); // Obtiene el videojuego de cada reseña
            });
            return resenas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por calificación: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public List<Resena> buscarTodas() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Resena> query = em.createQuery(
                    "SELECT r FROM Resena r ORDER BY r.fechaResena DESC",
                    Resena.class
            );
            List<Resena> resenas = query.getResultList();
            resenas.forEach(r -> {
                r.getCliente(); // Obtiene el cliente de cada reseña
                r.getVideojuego(); // Obtiene el videojuego de cada reseña
            });
            return resenas;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todas las reseñas: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 */
public class ResenaDAO {

    /**
     * Obtiene un EntityManager de la clase de conexión.
     */
    protected EntityManager getEntityManager() {
        return Conexion.crearConexion(); //
    }

    /**
     * Guarda una nueva Reseña en la base de datos.
     * 
     * @param resena La reseña a persistir.
     * @throws PersistenciaException Si ocurre un error de JPA.
     */
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
            throw new PersistenciaException("Error al persistir la reseña: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Elimina físicamente una reseña de la base de datos.
     * 
     * @param idResena El ID de la reseña a eliminar.
     * @throws PersistenciaException Si la reseña no se encuentra o hay un error.
     */
    public void eliminarResena(Long idResena) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            
            Resena resena = em.find(Resena.class, idResena); //
            
            if (resena == null) {
                throw new PersistenciaException("No se encontró la reseña con ID: " + idResena);
            }

            em.remove(resena);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) throw (PersistenciaException) e;
            throw new PersistenciaException("Error al eliminar la reseña: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca todas las reseñas asociadas a un videojuego por su nombre.
     * @param nombreVideojuego El nombre (completo o parcial) del videojuego.
     * @return Una lista de reseñas, o una lista vacía si no hay.
     * @throws PersistenciaException Si ocurre un error de consulta.
     */
    public List<Resena> buscarPorVideojuego(String nombreVideojuego) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Resena> query = em.createQuery(
                // Se cambió 'LIKE' por '=' para una coincidencia exacta
                "SELECT r FROM Resena r WHERE r.videojuego.nombre = :nombreVideojuego", 
                Resena.class
            );
            
            query.setParameter("nombreVideojuego", nombreVideojuego);
            
            return query.getResultList();
            
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar reseñas por nombre de videojuego: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

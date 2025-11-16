package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Direccion;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class DireccionDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearDireccion(Direccion direccion) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear la dirección: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Direccion managedDireccion = em.merge(direccion);
            em.getTransaction().commit();
            return managedDireccion;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar la dirección: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Direccion buscarPorId(Long idDireccion) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Direccion.class, idDireccion);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar dirección por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarDireccion(Long idDireccion) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Direccion direccion = em.find(Direccion.class, idDireccion);
            if (direccion == null) {
                throw new PersistenciaException("No se encontró la dirección con ID: " + idDireccion);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al eliminar la dirección: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

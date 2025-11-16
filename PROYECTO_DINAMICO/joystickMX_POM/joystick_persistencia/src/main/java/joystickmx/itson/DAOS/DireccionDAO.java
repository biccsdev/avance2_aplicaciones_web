package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.interfaces.IDireccionDAO;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class DireccionDAO extends BaseDAO implements IDireccionDAO {

    public DireccionDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearDireccion(Direccion direccion) throws PersistenciaException {
        try {
            em.persist(direccion);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear la dirección: " + e.getMessage());
        }
    }

    @Override
    public Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException {
        try {
            return em.merge(direccion);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar la dirección: " + e.getMessage());
        }
    }

    @Override
    public Direccion buscarPorId(Long idDireccion) throws PersistenciaException {
        try {
            return em.find(Direccion.class, idDireccion);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar dirección por ID: " + e.getMessage());
        }
    }

    @Override
    public void eliminarDireccion(Long idDireccion) throws PersistenciaException {
        try {
            Direccion direccion = em.find(Direccion.class, idDireccion);
            if (direccion == null) {
                throw new PersistenciaException("No se encontró la dirección con ID: " + idDireccion);
            }
            em.remove(direccion);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al eliminar la dirección: " + e.getMessage());
        }
    }
}
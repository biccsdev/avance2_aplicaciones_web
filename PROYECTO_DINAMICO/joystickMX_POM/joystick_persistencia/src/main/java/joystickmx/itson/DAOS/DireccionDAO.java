package joystickmx.itson.DAOS;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.interfaces.IDireccionDAO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class DireccionDAO extends BaseDAO implements IDireccionDAO {

    @Override
    public void crearDireccion(Direccion direccion) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear la dirección: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Direccion direccionActualizada = em.merge(direccion);
            em.getTransaction().commit();
            return direccionActualizada;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar la dirección: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Direccion buscarPorId(Long idDireccion) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Direccion.class, idDireccion);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar dirección por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public void eliminarDireccion(Long idDireccion) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Direccion direccion = em.find(Direccion.class, idDireccion);
            if (direccion == null) {
                throw new PersistenciaException("No se encontró la dirección con ID: " + idDireccion);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al eliminar la dirección: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }
}
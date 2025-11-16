
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IUsuarioDAO;

/**
 *
 * @author sonic
 * @author biccs
 */
public class UsuarioDAO extends BaseDAO implements IUsuarioDAO {

    public UsuarioDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearUsuario(Usuario usuario) throws PersistenciaException {
        try {
            em.persist(usuario);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws PersistenciaException {
        try {
            return em.merge(usuario);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(Long idUsuario) throws PersistenciaException {
        try {
            return em.find(Usuario.class, idUsuario);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar usuario por ID: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) throws PersistenciaException {
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email",
                    Usuario.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar usuario por email: " + e.getMessage());
        }
    }

    private void actualizarEstadoUsuario(String email, EstadoUsuario nuevoEstado) throws PersistenciaException {
        try {
            Usuario usuario = this.buscarPorEmail(email);
            if (usuario == null) {
                 throw new PersistenciaException("No se encontró el usuario con email: " + email);
            }
            usuario.setEstadoUsuario(nuevoEstado);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar estado del usuario: " + e.getMessage());
        }
    }

    @Override
    public void activarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.ACTIVO);
    }

    @Override
    public void desactivarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.INACTIVO);
    }

    @Override
    public void eliminarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.ELIMINADO);
    }

    @Override
    public Usuario modificarDireccion(String email, Direccion datosNuevos) throws PersistenciaException {
        try {
            Usuario usuario = this.buscarPorEmail(email);
            if (usuario == null) {
                throw new PersistenciaException("Error: No se encontró usuario con el email: " + email);
            }

            Direccion direccionExistente = usuario.getDireccion();
            if (direccionExistente == null) {
                direccionExistente = new Direccion();
                usuario.setDireccion(direccionExistente);
            }

            direccionExistente.setCalle(datosNuevos.getCalle());
            direccionExistente.setNumero(datosNuevos.getNumero());
            direccionExistente.setColonia(datosNuevos.getColonia());
            
            return usuario; 
            
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al modificar la dirección: " + e.getMessage());
        }
    }
}
package joystickmx.itson.DAOS;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IUsuarioDAO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class UsuarioDAO extends BaseDAO implements IUsuarioDAO {

    @Override
    public void crearUsuario(Usuario usuario) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al crear el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Usuario usuarioActualizado = em.merge(usuario);
            em.getTransaction().commit();
            return usuarioActualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al actualizar el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Usuario buscarPorId(Long idUsuario) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Usuario.class, idUsuario);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuario por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email",
                    Usuario.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuario por email: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Método auxiliar que reutilizan eliminar, activar y desactivar.
     */
    private void actualizarEstadoUsuario(String email, EstadoUsuario nuevoEstado) throws PersistenciaException {
        iniciarConexion(); 
        try {
            em.getTransaction().begin();

            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);

            Usuario usuario = query.getSingleResult(); 

            usuario.setEstadoUsuario(nuevoEstado);

            em.merge(usuario);

            em.getTransaction().commit();

        } catch (NoResultException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se encontró el usuario con email: " + email);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                try {
                    em.getTransaction().rollback();
                } catch (Exception ignored) {
                }
            }
            e.printStackTrace();
            throw new PersistenciaException("Error al actualizar estado del usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
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
        iniciarConexion();
        try {
            em.getTransaction().begin();
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

            em.merge(usuario);
            em.getTransaction().commit();
            return usuario;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try {
                em.getTransaction().rollback();
            } catch (Exception ignored) {
            }
            throw new PersistenciaException("Error al modificar la dirección: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

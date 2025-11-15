/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Usuario;
<<<<<<< Updated upstream
=======
import joystickmx.itson.enums.EstadoUsuario;
>>>>>>> Stashed changes

/**
 *
 * @author sonic
 */
public class UsuarioDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public Usuario actualizar(Usuario usuario) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario managedUsuario = em.merge(usuario);
            em.getTransaction().commit();
            return managedUsuario;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Usuario buscarPorId(Long idUsuario) throws PersistenciaException {
        EntityManager em = getEntityManager();
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

    public List<Usuario> buscarTodos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
            Root<Usuario> root = cq.from(Usuario.class);
            cq.select(root);
            TypedQuery<Usuario> query = em.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los usuarios: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca un usuario por su email.
     * @param email El email a buscar.
     * @return El Usuario encontrado, o null si el email no existe.
     * @throws PersistenciaException Si ocurre un error de JPA.
     */
    public Usuario buscarPorEmail(String email) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM usuarios u WHERE u.EMAIL = :EMAIL",
                    Usuario.class
            );
            query.setParameter("EMAIL", email);

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
     * Método privado reutilizable para cambiar el estado de un usuario.
     *
     * @param email El email del usuario a modificar.
     * @param nuevoEstado El nuevo estado a asignar (ACTIVO, INACTIVO, ELIMINADO).
     * @throws PersistenciaException Si no se encuentra el usuario o falla la BD.
     */
    private void actualizarEstadoUsuario(String email, EstadoUsuario nuevoEstado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            Usuario usuario;
            try {
                TypedQuery<Usuario> query = em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.email = :email",
                        Usuario.class
                );
                query.setParameter("email", email);
                usuario = query.getSingleResult();
            } catch (NoResultException e) {
                throw new PersistenciaException("No se encontró el usuario con email: " + email);
            }

            usuario.setEstadoUsuario(nuevoEstado);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al actualizar estado del usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Cambia el estado de un usuario a ACTIVO.
     * @param email El email del usuario a activar.
     * @throws PersistenciaException Si ocurre un error.
     */
    public void activarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.ACTIVO);
    }

    /**
     * Cambia el estado de un usuario a INACTIVO.
     * @param email El email del usuario a desactivar.
     * @throws PersistenciaException Si ocurre un error.
     */
    public void desactivarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.INACTIVO);
    }

    /**
     * Realiza un "soft delete" cambiando el estado a ELIMINADO.
     * @param email El email del usuario a eliminar.
     * @throws PersistenciaException Si ocurre un error.
     */
    public void eliminarUsuario(String email) throws PersistenciaException {
        actualizarEstadoUsuario(email, EstadoUsuario.ELIMINADO);
    }

    /**
     * Método privado genérico para buscar usuarios según su estado.
     *
     * @param estado El EstadoUsuario (ACTIVO, INACTIVO, ELIMINADO) a buscar.
     * @return Una lista de usuarios que coinciden con ese estado.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    private List<Usuario> buscarPorEstado(EstadoUsuario estado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.estadoUsuario = :estado",
                    Usuario.class
            );
            query.setParameter("estado", estado);
            return query.getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuarios por estado: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca y retorna todos los usuarios cuyo estado es HABILITADO.
     *
     * @return Lista de usuarios activos.
     * @throws PersistenciaException Si ocurre un error.
     */
    public List<Usuario> buscarUsuariosActivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.ACTIVO);
    }

    /**
     * Busca y retorna todos los usuarios cuyo estado es INHABILITADO.
     *
     * @return Lista de usuarios inactivos.
     * @throws PersistenciaException Si ocurre un error.
     */
    public List<Usuario> buscarUsuariosInactivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.INACTIVO);
    }

}

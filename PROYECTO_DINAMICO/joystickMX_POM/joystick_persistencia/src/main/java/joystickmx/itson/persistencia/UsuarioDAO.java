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

/**
 *
 * @author sonic
 */
public class UsuarioDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearUsuario(Usuario usuario) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
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

    public void habilitarUsuario(String email) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Buscar el usuario usando una consulta por email
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

            // 2. Cambiar el estado
            usuario.setIsActive(true); //

            // 3. JPA guarda el cambio al hacer commit.
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Propagar la excepción (ya sea la nuestra o una nueva)
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al habilitar el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void deshabilitarUsuario(String email) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // 1. Buscar el usuario usando una consulta por email
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

            // 2. Cambiar el estado
            usuario.setIsActive(false); //

            // 3. JPA guarda el cambio al hacer commit.
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al deshabilitar el usuario: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

}

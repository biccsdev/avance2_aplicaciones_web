package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.enums.EstadoUsuario;

/**
 *
 * @author sonic
 * @author biccs
 */
public class AdministradorDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearAdministrador(Administrador administrador) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(administrador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el administrador: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Administrador managedAdmin = em.merge(administrador);
            em.getTransaction().commit();
            return managedAdmin;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el administrador: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Administrador buscarPorId(Long idAdministrador) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, idAdministrador);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar administrador por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Administrador buscarPorEmail(String email) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.email = :email",
                    Administrador.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar administrador por email: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Administrador> buscarTodos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a",
                    Administrador.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los administradores: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Administrador> buscarActivos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.estadoUsuario = :estado",
                    Administrador.class
            );
            query.setParameter("estado", EstadoUsuario.ACTIVO);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar administradores activos: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

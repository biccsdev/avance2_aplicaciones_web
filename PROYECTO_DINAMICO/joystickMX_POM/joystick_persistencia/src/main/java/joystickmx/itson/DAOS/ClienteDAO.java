package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.enums.EstadoUsuario;

/**
 *
 * @author sonic
 * @author biccs
 */
public class ClienteDAO {
    
    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }
    
    public void crearCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir el cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente managedCliente = em.merge(cliente);
            em.getTransaction().commit();
            return managedCliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Cliente buscarPorId(Long idCliente) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, idCliente);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Cliente buscarPorEmail(String email) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por email: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Cliente> buscarTodos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c",
                    Cliente.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los clientes: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    private List<Cliente> buscarPorEstado(EstadoUsuario estado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.estadoUsuario = :estadoUsuario",
                    Cliente.class
            );
            query.setParameter("estadoUsuario", estado);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar clientes por estado: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Cliente> buscarClientesActivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.ACTIVO);
    }

    public List<Cliente> buscarClientesInactivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.INACTIVO);
    }

    public List<Cliente> buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.nombres LIKE :nombre OR c.apellidoPaterno LIKE :nombre OR c.apellidoMaterno LIKE :nombre",
                    Cliente.class
            );
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar clientes por nombre: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

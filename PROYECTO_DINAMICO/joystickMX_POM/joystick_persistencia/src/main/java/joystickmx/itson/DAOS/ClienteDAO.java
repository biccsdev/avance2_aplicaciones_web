package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IClienteDAO;

/**
 *
 * @author sonic
 * @author biccs
 */
public class ClienteDAO extends BaseDAO implements IClienteDAO {
    
    public ClienteDAO(EntityManager em) {
        super(em);
    }
    
    @Override
    public void crearCliente(Cliente cliente) throws PersistenciaException {
        try {
            em.persist(cliente);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al persistir el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException {
        try {
            return em.merge(cliente);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorId(Long idCliente) throws PersistenciaException {
        try {
            return em.find(Cliente.class, idCliente);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar cliente por ID: " + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorEmail(String email) throws PersistenciaException {
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email",
                    Cliente.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar cliente por email: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarTodos() throws PersistenciaException {
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c",
                    Cliente.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todos los clientes: " + e.getMessage());
        }
    }

    private List<Cliente> buscarPorEstado(EstadoUsuario estado) throws PersistenciaException {
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.estadoUsuario = :estadoUsuario",
                    Cliente.class
            );
            query.setParameter("estadoUsuario", estado);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar clientes por estado: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarClientesActivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.ACTIVO);
    }

    @Override
    public List<Cliente> buscarClientesInactivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.INACTIVO);
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombre) throws PersistenciaException {
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.nombres LIKE :nombre OR c.apellidoPaterno LIKE :nombre OR c.apellidoMaterno LIKE :nombre",
                    Cliente.class
            );
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar clientes por nombre: " + e.getMessage());
        }
    }
}

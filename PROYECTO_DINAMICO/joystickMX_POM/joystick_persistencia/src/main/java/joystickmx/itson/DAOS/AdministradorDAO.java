package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IAdministradorDAO;

/**
 *
 * @author sonic
 * @author biccs
 */
public class AdministradorDAO extends BaseDAO implements IAdministradorDAO {

        public AdministradorDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearAdministrador(Administrador administrador) throws PersistenciaException {
        try {
            em.persist(administrador);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear el administrador: " + e.getMessage());
        }
    }

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException {
        try {
            return em.merge(administrador);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar el administrador: " + e.getMessage());
        }
    }

    @Override
    public Administrador buscarPorId(Long idAdministrador) throws PersistenciaException {
        try {
            return em.find(Administrador.class, idAdministrador);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar administrador por ID: " + e.getMessage());
        }
    }

    @Override
    public Administrador buscarPorEmail(String email) throws PersistenciaException {
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.email = :email",
                    Administrador.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar administrador por email: " + e.getMessage());
        }
    }

    @Override
    public List<Administrador> buscarTodos() throws PersistenciaException {
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a",
                    Administrador.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todos los administradores: " + e.getMessage());
        }
    }

    @Override
    public List<Administrador> buscarActivos() throws PersistenciaException {
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.estadoUsuario = :estado",
                    Administrador.class
            );
            query.setParameter("estado", EstadoUsuario.ACTIVO);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar administradores activos: " + e.getMessage());
        }
    }
}
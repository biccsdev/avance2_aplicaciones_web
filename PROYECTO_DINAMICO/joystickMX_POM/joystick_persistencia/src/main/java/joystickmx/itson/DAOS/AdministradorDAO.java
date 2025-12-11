package joystickmx.itson.DAOS;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IAdministradorDAO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class AdministradorDAO extends BaseDAO implements IAdministradorDAO {

    @Override
    public void crearAdministrador(Administrador administrador) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(administrador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear el administrador: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }
    

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Administrador administradorRegistrado = em.merge(administrador);
            em.getTransaction().commit();
            return administradorRegistrado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar el administrador: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Administrador buscarPorId(Long idAdministrador) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Administrador.class, idAdministrador);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar administrador por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public Administrador buscarPorEmail(String email) throws PersistenciaException {
        iniciarConexion();
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
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public List<Administrador> buscarTodos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a",
                    Administrador.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los administradores: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }

    @Override
    public List<Administrador> buscarActivos() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.estadoUsuario = :estado",
                    Administrador.class
            );
            query.setParameter("estado", EstadoUsuario.ACTIVO);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar administradores activos: " + e.getMessage());
        } finally{
            if (em.isOpen()){ em.close(); }
        }
    }
}
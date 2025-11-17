package joystickmx.itson.DAOS;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.interfaces.ICategoriaDAO;

/**
 * @author biccs
 */
public class CategoriaDAO extends BaseDAO implements ICategoriaDAO {

    @Override
    public void crearCategoria(Categoria categoria) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al crear la categoría: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Categoria categoriaActualizada = em.merge(categoria);
            em.getTransaction().commit();
            return categoriaActualizada;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al actualizar la categoría: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public void eliminarCategoria(Long idCategoria) throws PersistenciaException {
        iniciarConexion();
        try {
            em.getTransaction().begin();
            Categoria categoria = em.find(Categoria.class, idCategoria);
            if (categoria == null) 
                throw new PersistenciaException("No se encontró la categoría con ID: " + idCategoria);
            em.remove(categoria);
            em.getTransaction().commit();
        } catch (IllegalArgumentException | PersistenceException e) {
            if (em.getTransaction().isActive()) 
                try { em.getTransaction().rollback(); } catch (Exception ignored) {}
            throw new PersistenciaException("Error al eliminar la categoría: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Categoria buscarPorId(Long idCategoria) throws PersistenciaException {
        iniciarConexion();
        try {
            return em.find(Categoria.class, idCategoria);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar categoría por ID: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public Categoria buscarPorNombre(String nombre) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c WHERE c.nombre = :nombre",
                    Categoria.class
            );
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar categoría por nombre: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Categoria> buscarTodas() throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c ORDER BY c.nombre",
                    Categoria.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todas las categorías: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }

    @Override
    public List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException {
        iniciarConexion();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c WHERE c.nombre LIKE :nombre ORDER BY c.nombre",
                    Categoria.class
            );
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar categorías por nombre parcial: " + e.getMessage());
        } finally{
            if (em.isOpen()) 
                em.close();
        }
    }
}
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
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

    public CategoriaDAO(EntityManager em) {
        super(em);
    }

    @Override
    public void crearCategoria(Categoria categoria) throws PersistenciaException {
        try {
            em.persist(categoria);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al crear la categoría: " + e.getMessage());
        }
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException {
        try {
            return em.merge(categoria);
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al actualizar la categoría: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCategoria(Long idCategoria) throws PersistenciaException {
        try {
            Categoria categoria = em.find(Categoria.class, idCategoria);
            if (categoria == null) {
                throw new PersistenciaException("No se encontró la categoría con ID: " + idCategoria);
            }
            em.remove(categoria);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new PersistenciaException("Error al eliminar la categoría: " + e.getMessage());
        }
    }

    @Override
    public Categoria buscarPorId(Long idCategoria) throws PersistenciaException {
        try {
            return em.find(Categoria.class, idCategoria);
        } catch (IllegalArgumentException e) {
            throw new PersistenciaException("Error al buscar categoría por ID: " + e.getMessage());
        }
    }

    @Override
    public Categoria buscarPorNombre(String nombre) throws PersistenciaException {
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
        }
    }

    @Override
    public List<Categoria> buscarTodas() throws PersistenciaException {
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c ORDER BY c.nombre",
                    Categoria.class
            );
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar todas las categorías: " + e.getMessage());
        }
    }

    @Override
    public List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException {
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c WHERE c.nombre LIKE :nombre ORDER BY c.nombre",
                    Categoria.class
            );
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new PersistenciaException("Error al buscar categorías por nombre parcial: " + e.getMessage());
        }
    }
}


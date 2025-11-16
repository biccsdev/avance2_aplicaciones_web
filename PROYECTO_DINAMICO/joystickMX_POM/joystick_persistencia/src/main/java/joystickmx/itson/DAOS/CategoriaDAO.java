package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Categoria;

/**
 * @author biccs
 */

public class CategoriaDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearCategoria(Categoria categoria) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear la categoría: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Categoria managedCategoria = em.merge(categoria);
            em.getTransaction().commit();
            return managedCategoria;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar la categoría: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void eliminarCategoria(Long idCategoria) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Categoria categoria = em.find(Categoria.class, idCategoria);
            if (categoria == null) {
                throw new PersistenciaException("No se encontró la categoría con ID: " + idCategoria);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al eliminar la categoría: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Categoria buscarPorId(Long idCategoria) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, idCategoria);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar categoría por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public Categoria buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c WHERE c.nombre = :nombre",
                    Categoria.class
            );
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar categoría por nombre: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Categoria> buscarTodas() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c ORDER BY c.nombre",
                    Categoria.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todas las categorías: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                    "SELECT c FROM Categoria c WHERE c.nombre LIKE :nombre ORDER BY c.nombre",
                    Categoria.class
            );
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar categorías por nombre parcial: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Videojuego;

/**
 *
 * @author sonic
 */
public class VideojuegoDAO {

    /**
     * Obtiene un EntityManager de la clase de conexión.
     */
    protected EntityManager getEntityManager() {
        return Conexion.crearConexion(); //
    }

    /**
     * Guarda un nuevo Videojuego en la base de datos.
     *
     * @param videojuego El videojuego a persistir.
     * @throws PersistenciaException Si ocurre un error de JPA.
     */
    public void persistir(Videojuego videojuego) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(videojuego);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir el videojuego: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Actualiza un Videojuego existente.
     *
     * @param videojuego El videojuego con los datos actualizados.
     * @return El videojuego actualizado (managed).
     * @throws PersistenciaException Si ocurre un error de JPA.
     */
    public Videojuego actualizar(Videojuego videojuego) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Videojuego managedVideojuego = em.merge(videojuego);
            em.getTransaction().commit();
            return managedVideojuego;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el videojuego: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Método privado para habilitar o deshabilitar un videojuego.
     *
     * @param idVideojuego El ID del videojuego a modificar.
     * @param habilitado El nuevo estado (true para habilitar, false para
     * deshabilitar).
     * @throws PersistenciaException Si no se encuentra el videojuego o falla la
     * BD.
     */
    private void cambiarEstado(Long idVideojuego, boolean habilitado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Videojuego videojuego = em.find(Videojuego.class, idVideojuego);
            if (videojuego == null) {
                throw new PersistenciaException("No se encontró el videojuego con ID: " + idVideojuego);
            }
            videojuego.setHabilitado(habilitado); //
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (e instanceof PersistenciaException) {
                throw (PersistenciaException) e;
            }
            throw new PersistenciaException("Error al cambiar estado del videojuego: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Habilita un videojuego (hace que aparezca en las búsquedas de clientes).
     *
     * @param idVideojuego El ID del videojuego a habilitar.
     * @throws PersistenciaException
     */
    public void habilitarVideojuego(Long idVideojuego) throws PersistenciaException {
        cambiarEstado(idVideojuego, true);
    }

    /**
     * Deshabilita un videojuego (lo oculta de las búsquedas de clientes).
     *
     * @param idVideojuego El ID del videojuego a deshabilitar.
     * @throws PersistenciaException
     */
    public void deshabilitarVideojuego(Long idVideojuego) throws PersistenciaException {
        cambiarEstado(idVideojuego, false);
    }

    /**
     * Busca todos los videojuegos sin importar su estado (para el panel de
     * admin).
     *
     * @return Lista de todos los videojuegos.
     * @throws PersistenciaException
     */
    public List<Videojuego> buscarTodosLosVideojuegos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v", Videojuego.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar todos los videojuegos: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca solo los videojuegos ACTIVOS (habilitado = true).
     *
     * @return Lista de videojuegos activos.
     * @throws PersistenciaException
     */
    public List<Videojuego> buscarVideojuegosActivos() throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true", Videojuego.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar videojuegos activos: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca videojuegos ACTIVOS por un rango de precio.
     *
     * @param min Precio mínimo.
     * @param max Precio máximo.
     * @return Lista de videojuegos filtrados.
     * @throws PersistenciaException
     */
    public List<Videojuego> buscarPorRangoDePrecio(Float min, Float max) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.precio BETWEEN :min AND :max",
                    Videojuego.class
            );
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar por rango de precio: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca videojuegos ACTIVOS por categoría.
     *
     * @param idCategoria El ID de la categoría a buscar.
     * @return Lista de videojuegos filtrados.
     * @throws PersistenciaException
     */
    public List<Videojuego> buscarPorCategoria(Long idCategoria) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    // Hacemos JOIN con la lista de categorías del videojuego
                    "SELECT v FROM Videojuego v JOIN v.categorias c WHERE v.habilitado = true AND c.idCategoria = :idCategoria",
                    Videojuego.class
            );
            query.setParameter("idCategoria", idCategoria); //
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar por categoría: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca videojuegos ACTIVOS por coincidencia de nombre (parcial o
     * completa).
     *
     * @param nombre El texto a buscar en el nombre.
     * @return Lista de videojuegos filtrados.
     * @throws PersistenciaException
     */
    public List<Videojuego> buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createQuery(
                    "SELECT v FROM Videojuego v WHERE v.habilitado = true AND v.nombre LIKE :nombre",
                    Videojuego.class
            );
            query.setParameter("nombre", "%" + nombre + "%"); // Los % permiten la búsqueda parcial
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar por nombre: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca un videojuego por su ID.
     *
     * @param idVideojuego El ID a buscar.
     * @return El videojuego, o null si no se encuentra.
     * @throws PersistenciaException
     */
    public Videojuego buscarPorId(Long idVideojuego) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Videojuego.class, idVideojuego);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar videojuego por ID: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;

/**
 *
 * @author sonic
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
    
    
    /**
     * Método privado genérico para buscar usuarios según su estado.
     *
     * @param estado El EstadoUsuario (ACTIVO, INACTIVO, ELIMINADO) a buscar.
     * @return Una lista de usuarios que coinciden con ese estado.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    private List<Cliente> buscarPorEstado(EstadoUsuario estado) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT u FROM clientes u WHERE u.estadoUsuario = :estadoUsuario",
                    Cliente.class
            );
            query.setParameter("estadoUsuario", estado);
            return query.getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar usuarios por estado: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca y retorna todos los usuarios cuyo estado es HABILITADO.
     *
     * @return Lista de usuarios activos.
     * @throws PersistenciaException Si ocurre un error.
     */
    public List<Cliente> buscarUsuariosActivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.ACTIVO);
    }

    /**
     * Busca y retorna todos los usuarios cuyo estado es INHABILITADO.
     *
     * @return Lista de usuarios inactivos.
     * @throws PersistenciaException Si ocurre un error.
     */
    public List<Cliente> buscarUsuariosInactivos() throws PersistenciaException {
        return buscarPorEstado(EstadoUsuario.INACTIVO);
    }
    
}

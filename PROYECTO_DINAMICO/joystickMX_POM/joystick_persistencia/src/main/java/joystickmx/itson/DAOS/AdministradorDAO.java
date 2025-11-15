/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Usuario;

/**
 *
 * @author sonic
 */
public class AdministradorDAO {

    protected EntityManager getEntityManager() {
        return Conexion.crearConexion();
    }

    public void crearAdmin(Administrador administrador) throws PersistenciaException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(administrador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir el administrador: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

}

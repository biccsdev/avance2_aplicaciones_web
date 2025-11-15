/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;

/**
 *
 * @author PC Gamer
 */
public class DireccionDAO {
    
    public boolean modificarDireccion(String email, Direccion datosNuevos) {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            Usuario usuario = query.getSingleResult();

            Direccion direccionExistente = usuario.getDireccion();

            direccionExistente.setCalle(datosNuevos.getCalle());
            direccionExistente.setNumero(datosNuevos.getNumero());
            direccionExistente.setColonia(datosNuevos.getColonia());

            em.getTransaction().commit();
            return true;
            
        } catch (NoResultException e) {
            System.err.println("Error: No se encontró usuario con el email: " + email);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al modificar la dirección: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
    
    
    
    
}

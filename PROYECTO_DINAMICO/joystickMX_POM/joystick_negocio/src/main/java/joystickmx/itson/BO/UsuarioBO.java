/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import joystickmx.itson.DAOS.UsuarioDAO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
public class UsuarioBO {
    
    private final UsuarioDAO usuarioDAO;

    public UsuarioBO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    //pendiente actualizar y Actualizar Estado Usuario
    
    
    public UsuarioDTO buscarPorId(Long idUsuario) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.usuarioDAO.buscarPorId(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            
            return Mapeadores.toDTO(this.usuarioDAO.buscarPorEmail(email));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por email: " + e.getMessage(), e);
        }
    }

    public void activarUsuario(String email) throws NegocioException {
        try {
            this.usuarioDAO.activarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al activar usuario: " + e.getMessage(), e);
        }
    }

    public void desactivarUsuario(String email) throws NegocioException {
        try {
            this.usuarioDAO.desactivarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al desactivar usuario: " + e.getMessage(), e);
        }
    }

    public void eliminarUsuario(String email) throws NegocioException {
        try {
            this.usuarioDAO.eliminarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar (soft delete) usuario: " + e.getMessage(), e);
        }
    }
}

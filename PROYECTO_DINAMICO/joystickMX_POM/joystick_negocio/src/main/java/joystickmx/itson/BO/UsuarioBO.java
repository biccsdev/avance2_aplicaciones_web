package joystickmx.itson.BO;

import joystickmx.itson.BO.Utils.PasswordUtil;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IUsuarioDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class UsuarioBO {
    
    private final IUsuarioDAO usuarioDAO;

    public UsuarioBO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    
    
    public void crearUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            this.usuarioDAO.crearUsuario(DTOMapeadores.toClienteEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear usuario: " + e.getMessage(), e);
        }
    }
    public UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            return Mapeadores.toUsuarioDTO(this.usuarioDAO.actualizar(DTOMapeadores.toClienteEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorId(Long idUsuario) throws NegocioException {
        try {
            return Mapeadores.toUsuarioDTO(this.usuarioDAO.buscarPorId(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            
            return Mapeadores.toUsuarioDTO(this.usuarioDAO.buscarPorEmail(email));
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
    

    
    
    
    /**
     * Actualiza la dirección de un usuario basado en su email.
     * Reemplaza la lógica incorrecta que estaba en DireccionBO.
     * * @param email Email del usuario a modificar.
     * @param email
     * @param dto El DTO con la nueva información de dirección.
     * @return El UsuarioDTO actualizado.
     * @throws NegocioException Si el usuario no se encuentra o falla la BD.
     */
        public UsuarioDTO modificarDireccion(String email, DireccionDTO dto) throws NegocioException {
        try {
            Direccion datosNuevos = DTOMapeadores.toDireccionEntity(dto);
            
            Usuario usuarioActualizado = this.usuarioDAO.modificarDireccion(email, datosNuevos);
            
            return Mapeadores.toUsuarioDTO(usuarioActualizado);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al modificar la dirección: " + e.getMessage(), e);
        }
    }
    
    
    /**
     * Valida las credenciales de un usuario.
     * @param email El email del usuario.
     * @param passwordPlano La contraseña en texto plano.
     * @return El UsuarioDTO si las credenciales son correctas y el usuario está activo.
     * @throws NegocioException Si el usuario no existe, la contraseña es incorrecta o el usuario está inactivo.
     */
    public UsuarioDTO validarCredenciales(String email, String passwordPlano) throws NegocioException {
        try {
            Usuario usuario = this.usuarioDAO.buscarPorEmail(email);
            
            if (usuario == null) {
                throw new NegocioException("Credenciales incorrectas.");
            }
            
            if (usuario.getEstadoUsuario() != EstadoUsuario.ACTIVO) {
                throw new NegocioException("La cuenta de usuario no está activa.");
            }
            
            boolean passwordValida = PasswordUtil.verificarPassword(passwordPlano, usuario.getContrasenia());
            
            if (!passwordValida) {
                throw new NegocioException("Credenciales incorrectas.");
            }
            
            return Mapeadores.toUsuarioDTO(usuario);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar credenciales: " + e.getMessage(), e);
        }
    }
    
    
    
}
    
    
    
    

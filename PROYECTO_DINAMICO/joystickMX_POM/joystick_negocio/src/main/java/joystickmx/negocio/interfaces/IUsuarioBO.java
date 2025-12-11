package joystickmx.negocio.interfaces;

import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IUsuarioBO {
    
    public void crearUsuario(UsuarioRegistroDTO dto) throws NegocioException;
    
    public UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException;
    
    public UsuarioDTO buscarPorId(Long idUsuario) throws NegocioException;
    
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException;
    
    public void activarUsuario(String email) throws NegocioException;
    
    public void desactivarUsuario(String email) throws NegocioException;
    
    public void eliminarUsuario(String email) throws NegocioException;
    
    /**
     * Actualiza la dirección de un usuario basado en su email. Reemplaza la
     * lógica incorrecta que estaba en DireccionBO.
     *
     * * @param email Email del usuario a modificar.
     * @param email
     * @param dto El DTO con la nueva información de dirección.
     * @return El UsuarioDTO actualizado.
     * @throws NegocioException Si el usuario no se encuentra o falla la BD.
     */
    public UsuarioDTO modificarDireccion(String email, DireccionDTO dto) throws NegocioException;
    
    /**
     * Valida las credenciales de un usuario.
     *
     * @param email El email del usuario.
     * @param passwordPlano La contraseña en texto plano.
     * @return El UsuarioDTO si las credenciales son correctas y el usuario está
     * activo.
     * @throws NegocioException Si el usuario no existe, la contraseña es
     * incorrecta o el usuario está inactivo.
     */
    public UsuarioDTO validarCredenciales(String email, String passwordPlano) throws NegocioException;
}
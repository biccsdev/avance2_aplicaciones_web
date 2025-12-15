package joystickmx.negocio.interfaces;

import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IUsuarioBO {
    
    public void crearUsuario(UsuarioRegistroDTO dto) throws NegocioException;
    
    public UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException;
    
    public UsuarioDTO buscarPorId(Long idUsuario) throws NegocioException;
    
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException;
    
    public void activarUsuario(String email) throws NegocioException;
    
    public void desactivarUsuario(String email) throws NegocioException;
    
    public void eliminarUsuario(String email) throws NegocioException;
    
    public UsuarioDTO modificarDireccion(String email, DireccionDTO dto) throws NegocioException;
    
    public UsuarioDTO validarCredenciales(String email, String passwordPlano) throws NegocioException;
    
    public DireccionDTO obtenerDireccionPorUsuario(String email) throws NegocioException;
}
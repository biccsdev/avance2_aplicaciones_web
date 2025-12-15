package joystickmx.negocio.interfaces;

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
public interface IAdministradorBO {
    
    public void crearAdmin(UsuarioRegistroDTO dto) throws NegocioException;
    
    public UsuarioDTO actualizarAdministrador(UsuarioDTO dto) throws NegocioException;
    
    public UsuarioDTO buscarPorId(Long idAdmin) throws NegocioException;
    
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException;
}
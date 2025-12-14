package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.ClienteDTO;
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
public interface IClienteBO {
    
    public void crearCliente(UsuarioRegistroDTO dto) throws NegocioException;
    
    public List<UsuarioDTO> buscarUsuariosActivos() throws NegocioException;
    
    public List<UsuarioDTO> buscarUsuariosInactivos() throws NegocioException;
    
    public List<UsuarioDTO> buscarClientesExistentes() throws NegocioException;
    
    public UsuarioDTO actualizarCliente(ClienteDTO dto) throws NegocioException;
    
    public UsuarioDTO buscarPorId(Long idCliente) throws NegocioException;
    
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException;
    
    public List<UsuarioDTO> buscarTodos() throws NegocioException;
    
    public List<UsuarioDTO> buscarPorNombre(String nombre) throws NegocioException;
    
    public List<UsuarioDTO> buscarClientesNoEliminadosPorNombre(String nombre) throws NegocioException;
}
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.Usuario;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IClienteDAO {

    public void crearCliente(Cliente cliente) throws PersistenciaException;

    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException;

    public Cliente buscarPorId(Long idCliente) throws PersistenciaException;

    public Cliente buscarPorEmail(String email) throws PersistenciaException;

    public List<Cliente> buscarTodos() throws PersistenciaException;

    public List<Cliente> buscarClientesExistentes() throws PersistenciaException;
    
    public List<Cliente> buscarClientesActivos() throws PersistenciaException;

    public List<Cliente> buscarClientesInactivos() throws PersistenciaException;

    public List<Cliente> buscarPorNombre(String nombre) throws PersistenciaException;
    
    public List<Usuario> buscarPorNombreNoEliminados(String nombre) throws PersistenciaException;
}
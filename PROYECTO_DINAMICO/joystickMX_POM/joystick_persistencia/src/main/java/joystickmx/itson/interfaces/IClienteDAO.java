
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Cliente;

/**
 *
 * @author PC Gamer
 */
public interface IClienteDAO {

    void crearCliente(Cliente cliente) throws PersistenciaException;

    Cliente actualizarCliente(Cliente cliente) throws PersistenciaException;

    Cliente buscarPorId(Long idCliente) throws PersistenciaException;

    Cliente buscarPorEmail(String email) throws PersistenciaException;

    List<Cliente> buscarTodos() throws PersistenciaException;

    List<Cliente> buscarClientesActivos() throws PersistenciaException;

    List<Cliente> buscarClientesInactivos() throws PersistenciaException;

    List<Cliente> buscarPorNombre(String nombre) throws PersistenciaException;
}


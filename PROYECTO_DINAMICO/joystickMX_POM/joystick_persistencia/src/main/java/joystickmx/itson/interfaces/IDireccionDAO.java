
package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;

/**
 *
 * @author PC Gamer
 */
public interface IDireccionDAO {

    void crearDireccion(Direccion direccion) throws PersistenciaException;

    Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException;

    Direccion buscarPorId(Long idDireccion) throws PersistenciaException;

    void eliminarDireccion(Long idDireccion) throws PersistenciaException;
}



package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IDireccionDAO {

    void crearDireccion(Direccion direccion) throws PersistenciaException;

    Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException;

    Direccion buscarPorId(Long idDireccion) throws PersistenciaException;

    void eliminarDireccion(Long idDireccion) throws PersistenciaException;
    
    public Direccion buscarPorEmailUsuario(String email) throws PersistenciaException;
}
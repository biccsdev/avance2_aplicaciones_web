
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Resena;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IResenaDAO {

    void crearResena(Resena resena) throws PersistenciaException;

    Resena actualizarResena(Resena resena) throws PersistenciaException;

    void eliminarResena(Long idResena) throws PersistenciaException;

    Resena buscarPorId(Long idResena) throws PersistenciaException;

    List<Resena> buscarPorVideojuego(Long idVideojuego) throws PersistenciaException;
    
    List<Resena> buscarPorNombreVideojuego(String nombreVideojuego) throws PersistenciaException;

    List<Resena> buscarPorCliente(Long idCliente) throws PersistenciaException;

    List<Resena> buscarPorCalificacion(Float calificacion) throws PersistenciaException;

    List<Resena> buscarTodas() throws PersistenciaException;
}
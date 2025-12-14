package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Resena;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IResenaDAO {

    public void crearResena(Resena resena) throws PersistenciaException;

    public Resena actualizarResena(Resena resena) throws PersistenciaException;

    public void eliminarResena(Long idResena) throws PersistenciaException;

    public Resena buscarPorId(Long idResena) throws PersistenciaException;

    public List<Resena> buscarPorVideojuego(Long idVideojuego) throws PersistenciaException;
    
    public List<Resena> buscarPorNombreVideojuego(String nombreVideojuego) throws PersistenciaException;

    public List<Resena> buscarPorCliente(Long idCliente) throws PersistenciaException;
    
    public Resena buscarPorVideojuegoCliente(Long idCliente, Long idVideojuego) throws PersistenciaException;
    
    public List<Resena> buscarPorCalificacion(Float calificacion) throws PersistenciaException;

    public List<Resena> buscarTodas() throws PersistenciaException;
}
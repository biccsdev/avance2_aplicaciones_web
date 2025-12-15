package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Videojuego;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IVideojuegoDAO {

    public void persistir(Videojuego videojuego) throws PersistenciaException;

    public Videojuego actualizar(Videojuego videojuego) throws PersistenciaException;

    public void habilitarVideojuego(Long idVideojuego) throws PersistenciaException;

    public void deshabilitarVideojuego(Long idVideojuego) throws PersistenciaException;

    public List<Videojuego> buscarTodosLosVideojuegos() throws PersistenciaException;

    public List<Videojuego> buscarVideojuegosActivos() throws PersistenciaException;

    public List<Videojuego> buscarPorRangoDePrecio(Float min, Float max) throws PersistenciaException;

    public List<Videojuego> buscarPorCategoria(Long idCategoria) throws PersistenciaException;

    public List<Videojuego> buscarPorNombre(String nombre) throws PersistenciaException;

    public Videojuego buscarPorId(Long idVideojuego) throws PersistenciaException;
    
    public Videojuego buscarPorNombreExacto(String nombre) throws PersistenciaException;
    
    public List<Videojuego> buscarConFiltros(
            String nombre, 
            Float precioMin, 
            Float precioMax, 
            Long idCategoria, 
            String plataforma
    ) throws PersistenciaException;
    
    
    public Videojuego obtenerParaValidacion(Long idVideojuego) throws PersistenciaException;
}
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Videojuego;

/**
 *
 * @author PC WHITE WOLF
 */
public interface IVideojuegoDAO {

    void persistir(Videojuego videojuego) throws PersistenciaException;

    Videojuego actualizar(Videojuego videojuego) throws PersistenciaException;

    void habilitarVideojuego(Long idVideojuego) throws PersistenciaException;

    void deshabilitarVideojuego(Long idVideojuego) throws PersistenciaException;

    List<Videojuego> buscarTodosLosVideojuegos() throws PersistenciaException;

    List<Videojuego> buscarVideojuegosActivos() throws PersistenciaException;

    List<Videojuego> buscarPorRangoDePrecio(Float min, Float max) throws PersistenciaException;

    List<Videojuego> buscarPorCategoria(Long idCategoria) throws PersistenciaException;

    List<Videojuego> buscarPorNombre(String nombre) throws PersistenciaException;

    Videojuego buscarPorId(Long idVideojuego) throws PersistenciaException;
    
    Videojuego buscarPorNombreExacto(String nombre) throws PersistenciaException;
    
    List<Videojuego> buscarConFiltros(String nombre, Float precioMin, Float precioMax, Long idCategoria, String plataforma) throws PersistenciaException;
}

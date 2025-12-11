package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IVideojuegoBO {
    
    public void crearVideojuego(VideojuegoDTO dto) throws NegocioException;
    
    public VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException;
    
    public void habilitarVideojuego(Long idVideojuego) throws NegocioException;
    
    public void deshabilitarVideojuego(Long idVideojuego) throws NegocioException;
    
    public List<VideojuegoDTO> buscarTodosLosVideojuegos() throws NegocioException;
    
    public List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException;
    
    public List<VideojuegoDTO> buscarPorRangoDePrecio(Float min, Float max) throws NegocioException;
    
    public List<VideojuegoDTO> buscarPorCategoria(Long idCategoria) throws NegocioException;
    
    public List<VideojuegoDTO> buscarPorNombre(String nombre) throws NegocioException;
    
    public VideojuegoDTO buscarPorNombreExacto(String nombre) throws NegocioException;
    
    public VideojuegoDTO buscarPorId(Long idVideojuego) throws NegocioException;
    
    /**
     * Busca videojuegos aplicando filtros combinados.
     *
     * @param nombre Parte del nombre del videojuego (opcional).
     * @param precioMin Precio mínimo (opcional).
     * @param precioMax Precio máximo (opcional).
     * @param idCategoria ID de la categoría (opcional).
     * @param plataforma Nombre de la plataforma (opcional).
     * @return Lista de VideojuegoDTO que cumplen con los criterios.
     * @throws NegocioException Si ocurre un error en la persistencia.
     */
    public List<VideojuegoDTO> buscarVideojuegosConFiltros(
            String nombre, 
            Float precioMin, 
            Float precioMax, 
            Long idCategoria, 
            String plataforma
    ) throws NegocioException;
}
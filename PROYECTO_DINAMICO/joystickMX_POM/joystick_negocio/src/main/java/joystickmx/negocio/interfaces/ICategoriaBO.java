package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface ICategoriaBO {
    
    public void crearCategoria(CategoriaDTO dto) throws NegocioException;
    
    public CategoriaDTO actualizarCategoria(CategoriaDTO dto) throws NegocioException;
    
    public void eliminarCategoria(Long idCategoria) throws NegocioException;
    
    public CategoriaDTO buscarPorId(Long idCategoria) throws NegocioException;
    
    public CategoriaDTO buscarPorNombre(String nombre) throws NegocioException;
    
    public List<CategoriaDTO> buscarTodas() throws NegocioException;
    
    public List<CategoriaDTO> buscarPorNombreParcial(String nombreParcial) throws NegocioException;
    
    public List<CategoriaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException;
}
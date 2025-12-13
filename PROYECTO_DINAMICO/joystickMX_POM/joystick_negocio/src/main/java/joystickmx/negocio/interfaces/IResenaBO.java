package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IResenaBO {
    
    public void crearResena(ResenaDTO dto) throws NegocioException;
    
    public ResenaDTO actualizarResena(ResenaDTO dto) throws NegocioException;
    
    public void eliminarResena(Long idResena) throws NegocioException;
    
    public List<ResenaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException;
    
    public List<ResenaDTO> buscarPorNombreVideojuego(String nombreVideojuego) throws NegocioException;
    
    public List<ResenaDTO> buscarPorCliente(Long idCliente) throws NegocioException;
    
    public List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException;
    
    public List<ResenaDTO> buscarTodas() throws NegocioException;
    
    public ResenaDTO buscarPorVideojuegoCliente(Long idCliente, Long idVideojuego) throws NegocioException;
}
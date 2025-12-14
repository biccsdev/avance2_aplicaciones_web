package joystickmx.negocio.interfaces;

import java.util.List;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface ICarritoBO {
    
    public void crearCarrito(CarritoDTO dto) throws NegocioException;
    
    public CarritoDTO actualizarCarrito(CarritoDTO dto) throws NegocioException;
    
    public CarritoDTO buscarPorId(Long idCarrito) throws NegocioException;
    
    public CarritoDTO buscarPorCliente(Long idCliente) throws NegocioException;
    
    public void agregarItem(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException;
    
    public void eliminarItem(Long idItemCarrito) throws NegocioException;
    
    public void vaciarCarrito(Long idCarrito) throws NegocioException;
    
    public void eliminarCarrito(Long idCarrito) throws NegocioException;
    
    public List<ItemCarritoDTO> obtenerItemsCarrito(Long idCarrito) throws NegocioException;
    
    public void actualizarCantidadItem(Long idItemCarrito, Integer cantidad) throws NegocioException;
    
    public List<String> validarExistenciasVideojuego(Long idCliente) throws NegocioException;
    
    public ItemCarritoDTO buscarItemPorId(Long idItemCarrito) throws NegocioException;
    
    public ItemCarritoDTO buscarVideojuegoEnCarrito(Long idCarrito, Long idVideojuego) throws NegocioException;
}
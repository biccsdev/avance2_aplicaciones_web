package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface ICarritoDAO {

    public void crearCarrito(Carrito carrito) throws PersistenciaException;

    public Carrito actualizarCarrito(Carrito carrito) throws PersistenciaException;

    public Carrito buscarPorId(Long idCarrito) throws PersistenciaException;

    public Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException;

    public void agregarItem(Carrito carrito, ItemCarrito item) throws PersistenciaException;

    public void eliminarItem(Long idItemCarrito) throws PersistenciaException;

    public void vaciarCarrito(Long idCarrito) throws PersistenciaException;

    public void eliminarCarrito(Long idCarrito) throws PersistenciaException;
    
    public List<ItemCarrito> obtenerItemsCarrito(Carrito carrito) throws PersistenciaException;
    
    public void actualizarCantidadItem(Long idItemCarrito, Integer nuevaCantidad) throws PersistenciaException;
    
    public ItemCarrito buscarItemPorId(Long idItemCarrito) throws PersistenciaException;
    
    public ItemCarrito buscarVideojuegoEnCarrito(Long idCarrito, Long idVideojuego) throws PersistenciaException;
}
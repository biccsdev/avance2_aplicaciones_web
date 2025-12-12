package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface ICarritoDAO {

    void crearCarrito(Carrito carrito) throws PersistenciaException;

    Carrito actualizarCarrito(Carrito carrito) throws PersistenciaException;

    Carrito buscarPorId(Long idCarrito) throws PersistenciaException;

    Carrito buscarPorCliente(Cliente cliente) throws PersistenciaException;

    void agregarItem(Carrito carrito, ItemCarrito item) throws PersistenciaException;

    void eliminarItem(Long idItemCarrito) throws PersistenciaException;

    void vaciarCarrito(Long idCarrito) throws PersistenciaException;

    void eliminarCarrito(Long idCarrito) throws PersistenciaException;
    
    public List<ItemCarrito> obtenerItemsCarrito(Carrito carrito) throws PersistenciaException;
    
    public void actualizarCantidadItem(Long idItemCarrito, Integer nuevaCantidad) throws PersistenciaException;
}
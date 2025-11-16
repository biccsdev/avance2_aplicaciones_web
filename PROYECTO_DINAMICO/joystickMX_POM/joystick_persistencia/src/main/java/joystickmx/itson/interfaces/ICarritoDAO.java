
package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;

/**
 *
 * @author PC Gamer
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
}


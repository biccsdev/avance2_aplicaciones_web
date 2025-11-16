package joystickmx.itson.BO;

import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author biccs
 */
public class CarritoBO {

    private final ICarritoDAO carritoDAO;

    public CarritoBO(ICarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public void crearCarrito(CarritoDTO dto) throws NegocioException {
        try {
            this.carritoDAO.crearCarrito(DTOMapeadores.toCarritoEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear carrito: " + e.getMessage(), e);
        }
    }

    public CarritoDTO actualizarCarrito(CarritoDTO dto) throws NegocioException {
        try {
            Carrito entidad = DTOMapeadores.toCarritoEntity(dto);
            Carrito actualizado = this.carritoDAO.actualizarCarrito(entidad);
            return Mapeadores.toCarritoDTO(actualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar carrito: " + e.getMessage(), e);
        }
    }

    public CarritoDTO buscarPorId(Long idCarrito) throws NegocioException {
        try {
            return Mapeadores.toCarritoDTO(this.carritoDAO.buscarPorId(idCarrito));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar carrito por ID: " + e.getMessage(), e);
        }
    }

    public CarritoDTO buscarPorCliente(Long idCliente) throws NegocioException {
        try {
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(idCliente);
            Carrito carrito = this.carritoDAO.buscarPorCliente(cliente);
            
            return carrito != null ? Mapeadores.toCarritoDTO(carrito) : null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar carrito por cliente: " + e.getMessage(), e);
        }
    }

    public void agregarItem(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException {
        try {
            ItemCarrito item = DTOMapeadores.toItemCarritoEntity(itemDTO);
            
            Carrito carrito = new Carrito();
            carrito.setIdCarrito(idCarrito);
            
            this.carritoDAO.agregarItem(carrito, item);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar item al carrito: " + e.getMessage(), e);
        }
    }

    public void eliminarItem(Long idItemCarrito) throws NegocioException {
        try {
            this.carritoDAO.eliminarItem(idItemCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar item del carrito: " + e.getMessage(), e);
        }
    }

    public void vaciarCarrito(Long idCarrito) throws NegocioException {
        try {
            this.carritoDAO.vaciarCarrito(idCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al vaciar el carrito: " + e.getMessage(), e);
        }
    }

    public void eliminarCarrito(Long idCarrito) throws NegocioException {
        try {
            this.carritoDAO.eliminarCarrito(idCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar el carrito: " + e.getMessage(), e);
        }
    }
}
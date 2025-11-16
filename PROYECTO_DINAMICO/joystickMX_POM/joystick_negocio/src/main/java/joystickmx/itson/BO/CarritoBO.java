package joystickmx.itson.BO;

import joystickmx.itson.DAOS.CarritoDAO;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author biccs
 */
public class CarritoBO {

    private final CarritoDAO carritoDAO;

    public CarritoBO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    public void crearCarrito(CarritoDTO dto) throws NegocioException {
        try {
            this.carritoDAO.crearCarrito(Mapeadores.toEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear carrito: " + e.getMessage(), e);
        }
    }

    public CarritoDTO actualizarCarrito(CarritoDTO dto) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.carritoDAO.actualizarCarrito(Mapeadores.toEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar carrito: " + e.getMessage(), e);
        }
    }

    public CarritoDTO buscarPorId(Long idCarrito) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.carritoDAO.buscarPorId(idCarrito));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar carrito por ID: " + e.getMessage(), e);
        }
    }

    public CarritoDTO buscarPorCliente(Long idCliente) throws NegocioException {
        try {
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(idCliente);
            Carrito carrito = this.carritoDAO.buscarPorCliente(cliente);
            return carrito != null ? Mapeadores.toDTO(carrito) : null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar carrito por cliente: " + e.getMessage(), e);
        }
    }

    public void agregarItem(CarritoDTO carritoDTO, ItemCarrito item) throws NegocioException {
        try {
            this.carritoDAO.agregarItem(Mapeadores.toEntity(carritoDTO), item);
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


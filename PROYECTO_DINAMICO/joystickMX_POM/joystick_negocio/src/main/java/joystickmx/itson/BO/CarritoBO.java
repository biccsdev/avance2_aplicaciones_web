package joystickmx.itson.BO;

import java.util.List;
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
import joystickmx.negocio.interfaces.ICarritoBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class CarritoBO implements ICarritoBO {

    private final ICarritoDAO carritoDAO;

    public CarritoBO(ICarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }

    @Override
    public void crearCarrito(CarritoDTO dto) throws NegocioException {
        try {
            this.carritoDAO.crearCarrito(DTOMapeadores.toCarritoEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO actualizarCarrito(CarritoDTO dto) throws NegocioException {
        try {
            Carrito entidad = DTOMapeadores.toCarritoEntity(dto);
            Carrito actualizado = this.carritoDAO.actualizarCarrito(entidad);
            return Mapeadores.toCarritoDTO(actualizado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO buscarPorId(Long idCarrito) throws NegocioException {
        try {
            return Mapeadores.toCarritoDTO(this.carritoDAO.buscarPorId(idCarrito));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar carrito por ID: " + e.getMessage(), e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void eliminarItem(Long idItemCarrito) throws NegocioException {
        try {
            this.carritoDAO.eliminarItem(idItemCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar item del carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public void vaciarCarrito(Long idCarrito) throws NegocioException {
        try {
            this.carritoDAO.vaciarCarrito(idCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al vaciar el carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarCarrito(Long idCarrito) throws NegocioException {
        try {
            this.carritoDAO.eliminarCarrito(idCarrito);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar el carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ItemCarritoDTO> obtenerItemsCarrito(Long idCarrito) throws NegocioException {
        try {
            if (idCarrito == null) {
                throw new NegocioException("El ID del carrito no puede ser nulo.");
            }

            Carrito carritoEntidad = new Carrito();
            carritoEntidad.setIdCarrito(idCarrito);

            List<ItemCarrito> itemsEntidad = this.carritoDAO.obtenerItemsCarrito(carritoEntidad);

            return Mapeadores.toItemCarritoDTOList(itemsEntidad);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al recuperar los items del carrito: " + e.getMessage(), e);
        }
    }

}
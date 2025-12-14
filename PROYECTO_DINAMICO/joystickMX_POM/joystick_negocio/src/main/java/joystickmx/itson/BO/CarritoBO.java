package joystickmx.itson.BO;

import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DAOS.VideojuegoDAO;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.DependencyInjectorBO.InjectorBO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Videojuego;
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

    @Override
    public void actualizarCantidadItem(Long idItemCarrito, Integer cantidad) throws NegocioException {
        try {
            // Valida el id del item
            if(idItemCarrito == null)
                throw new NegocioException("Sin item del carrito asociado.");
            
            // Valida que el id corresponda con un item existente en la BD
            ItemCarritoDTO item = this.buscarItemPorId(idItemCarrito);
            if(item == null)
                throw new NegocioException("No se encontró el item asociado.");
            
            // Valida que la cantidad no sea menor o igual a cero
            if (cantidad <= 0)
                throw new NegocioException("La cantidad debe ser mayor a 0.");
            
            // Valida que la cantidad recibida no supere el stock disponible
            if(cantidad > InjectorBO.buildVideojuegoBO().buscarPorId(item.getIdVideojuego()).getExistencias())
                throw new NegocioException("La cantidad recibida supera el stock disponible.");
            
            this.carritoDAO.actualizarCantidadItem(idItemCarrito, cantidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar la cantidad del item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> validarExistenciasVideojuego(Long idCliente) throws NegocioException {
        List<String> errores = new ArrayList<>();

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                throw new NegocioException("Cliente no encontrado");
            }

            Carrito carrito = carritoDAO.buscarPorCliente(cliente);
            if (carrito == null || carrito.getItems().isEmpty()) {
                errores.add("El carrito está vacío.");
                return errores;
            }

            for (ItemCarrito item : carrito.getItems()) {
                Videojuego videojuegoReal = videojuegoDAO.obtenerParaValidacion(item.getVideojuego().getIdVideojuego());

                if (videojuegoReal != null) {
                    int cantidadSolicitada = item.getCantidad();
                    int stockDisponible = videojuegoReal.getExistencias();

                    if (cantidadSolicitada > stockDisponible) {
                        String mensaje = String.format(
                                "No hay existencias suficientes para el videojuego: %s. Seleccionadas: %d, Disponibles: %d",
                                videojuegoReal.getNombre(),
                                cantidadSolicitada,
                                stockDisponible
                        );
                        errores.add(mensaje);
                    }
                } else {
                    errores.add("El videojuego con ID " + item.getVideojuego().getIdVideojuego() + " ya no existe.");
                }
            }

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar existencias: " + e.getMessage(), e);
        }

        return errores;
    }
    
    @Override
    public ItemCarritoDTO buscarItemPorId(Long idItemCarrito) throws NegocioException{
        try {
            return Mapeadores.toItemCarritoDTO(this.carritoDAO.buscarItemPorId(idItemCarrito));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar el item: " + e.getMessage(), e);
        }
    }

}

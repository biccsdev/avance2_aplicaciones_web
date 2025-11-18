package joystickmx.itson.Factory;

import java.util.List;
import joystickmx.itson.BO.AdministradorBO;
import joystickmx.itson.BO.CarritoBO;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.BO.ClienteBO;
import joystickmx.itson.BO.PedidoBO;
import joystickmx.itson.BO.ResenaBO;
import joystickmx.itson.BO.UsuarioBO;
import joystickmx.itson.BO.VideojuegoBO;
import joystickmx.itson.DAOS.Factory.FactoryDAO;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC WHITE WOLF
 * @author biccs
 */
public class FactoryBO {

    /**
     * Valida las credenciales de un usuario. Es un método de LECTURA, por lo
     * que no necesita transacción (begin/commit).
     *
     * @param email El email del usuario.
     * @param password La contraseña en texto plano.
     * @return El UsuarioDTO con los datos de sesión.
     * @throws NegocioException Si la validación falla.
     */
    public static UsuarioDTO login(String email, String password) throws NegocioException {
        try {
            return new UsuarioBO(FactoryDAO.crearUsuarioDAO()).validarCredenciales(email, password);

        } catch (NegocioException e) {
            throw new NegocioException("Error de persistencia en login: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarUsuarioPorEmail(String email) throws NegocioException {
        try {
            return new UsuarioBO(FactoryDAO.crearUsuarioDAO()).buscarPorEmail(email);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarClientePorId(Long idCliente) throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarPorId(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesActivos() throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarUsuariosActivos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    //  pendientes metodos de buscar clientes activos inactivos por nombre etc
    public static List<UsuarioDTO> buscarClientesExistentes() throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarClientesExistentes();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void registrarCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            new ClienteBO(FactoryDAO.crearClienteDAO(), FactoryDAO.crearCarritoDAO()).crearCliente(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al registrar cliente: " + e.getMessage(), e);
        }
    }

    public static void registrarAdministrador(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            new AdministradorBO(FactoryDAO.crearAdministradorDAO()).crearAdmin(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO modificarDireccionUsuario(String email, DireccionDTO dto) throws NegocioException {
        try {
            UsuarioDTO usuarioActualizado = new UsuarioBO(FactoryDAO.crearUsuarioDAO()).modificarDireccion(email, dto);
            return usuarioActualizado;
        } catch (NegocioException e) {
            throw new NegocioException("Error al modificar dirección: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO buscarVideojuegoPorId(Long idVideojuego) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).buscarPorId(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static VideojuegoDTO buscarVideojuegoPorNombeExacto(String nombre) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).buscarPorNombreExacto(nombre);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static CategoriaDTO buscarCategoriaPorNombre(String nombre) throws NegocioException {
        try {
            return new CategoriaBO(FactoryDAO.crearCategoriaDAO()).buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            VideojuegoBO videojuegoBO = new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());
            return videojuegoBO.buscarVideojuegosActivos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    // pendiente bussquedas de juegos por nombre etc
    public static void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).crearVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al crear videojuego: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).actualizarVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        }
    }
    
    public static List<ResenaDTO> buscarResenasPorNombreVideojuego(String nombreVideojuego) throws NegocioException{
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorNombreVideojuego(nombreVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al consultar las resenas: " + e.getMessage(), e);
        }
    }
    
    public static List<ResenaDTO> buscarResenasPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCliente(Long idCliente) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException{
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarResenasPorCalificacion(calificacion);
        } catch (NegocioException e) {
            throw new NegocioException("Error al consultar las resenas: " + e.getMessage(), e);
        }
    }
    
    public static List<ResenaDTO> buscarTodasLasResenas() throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException("Error al consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static CarritoDTO buscarCarritoPorCliente(Long idCliente) throws NegocioException {
        try {
            return new CarritoBO(FactoryDAO.crearCarritoDAO()).buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void agregarItemACarrito(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException {
        try {
            CarritoBO carritoBO = new CarritoBO(
                    FactoryDAO.crearCarritoDAO()
            );

            carritoBO.agregarItem(idCarrito, itemDTO);

        } catch (NegocioException e) {
            throw e;
        } catch (Exception e) {
            throw new NegocioException("Error en FactoryBO al agregar item: " + e.getMessage(), e);
        }
    }

    // PENDIENTE ELIMINAR ITEM Y VACIAR CARRITO
    public static List<PedidoDTO> obtenerPedidos() throws NegocioException {
        try {
            return new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).obtenerPedidos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void crearCarrito(CarritoDTO carrito) throws NegocioException {
        try {
            new CarritoBO(FactoryDAO.crearCarritoDAO()).crearCarrito(carrito);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void pedidoEntregado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoEntregado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al marcar pedido como entregado: " + e.getMessage(), e);
        }
    }

    public static void pedidoEnviado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoEnviado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al marcar pedido como enviado: " + e.getMessage(), e);
        }
    }

    public static void pedidoPendiente(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoPendiente(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al marcar pedido como pendiente: " + e.getMessage(), e);
        }
    }

    public static void pedidoCancelado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoCancelado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al marcar pedido como cancelado: " + e.getMessage(), e);
        }
    }
    
    
    
    public static List<VideojuegoDTO> buscarVideojuegosPorNombreParcial(String nombre) throws NegocioException {
        try {
            VideojuegoBO videojuegoBO = new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());
            return videojuegoBO.buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al buscar videojuegos por nombre: " + e.getMessage(), e);
        }
    }
    
    
    
    public static List<UsuarioDTO> buscarClientesPorNombre(String nombre) throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al buscar clientes por nombre: " + e.getMessage(), e);
        }
    }

    /**
     * Esta es la operación de negocio más compleja. Requiere múltiples DAOs
     * para validar stock, calcular total y limpiar el carrito.
     * @param idCliente
     * @param direccionEnvioDTO
     * @param pagoDTO
     * @return 
     * @throws joystickmx.negocio.exception.NegocioException
     */
    public static PedidoDTO registrarPedido(Long idCliente, DireccionDTO direccionEnvioDTO, PagoDTO pagoDTO) throws NegocioException {
        try {

            PedidoBO pedidoBO = new PedidoBO(
                    FactoryDAO.crearPedidoDAO(),
                    FactoryDAO.crearClienteDAO(),
                    FactoryDAO.crearCarritoDAO(),
                    FactoryDAO.crearVideojuegoDAO()
            );
            return pedidoBO.registrarPedido(idCliente, direccionEnvioDTO, pagoDTO);

        } catch (NegocioException e) {
            throw new NegocioException("Error al registrar el pedido: " + e.getMessage(), e);
        }
    }

    public static PedidoDTO buscarPedidoPorId(Long idPedido) throws NegocioException {
        try {
            PedidoBO pedidoBO = new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null);
            return pedidoBO.buscarPorId(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al buscar pedido por ID: " + e.getMessage(), e);
        }
    }

    public static List<CategoriaDTO> buscarTodasCategorias() throws NegocioException {
        try {
            return new CategoriaBO(FactoryDAO.crearCategoriaDAO()).buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            // VALIDACIONES NEGOCIO IF EL CLIENTE SI LO COMPRO ENTONCES PROCEDER
            new ResenaBO(FactoryDAO.crearResenaDAO()).crearResena(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al crear reseña: " + e.getMessage(), e);
        }
    }

    public static void activarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).activarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void desactivarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).desactivarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public static void eliminarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).eliminarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}

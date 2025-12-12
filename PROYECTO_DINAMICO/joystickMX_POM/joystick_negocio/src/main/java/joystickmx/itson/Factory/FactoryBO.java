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
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
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
            throw new NegocioException("Error al intentar iniciar sesión: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarUsuarioPorEmail(String email) throws NegocioException {
        try {
            return new UsuarioBO(FactoryDAO.crearUsuarioDAO()).buscarPorEmail(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el usuario: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarClientePorId(Long idCliente) throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarPorId(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el cliente: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesActivos() throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarUsuariosActivos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes activos: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesExistentes() throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarClientesExistentes();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes existentes: " + e.getMessage(), e);
        }
    }

    public static void registrarCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            new ClienteBO(FactoryDAO.crearClienteDAO(), FactoryDAO.crearCarritoDAO()).crearCliente(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar registrar cliente: " + e.getMessage(), e);
        }
    }

    public static void registrarAdministrador(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            new AdministradorBO(FactoryDAO.crearAdministradorDAO()).crearAdmin(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar registrar administrador: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO modificarDireccionUsuario(String email, DireccionDTO dto) throws NegocioException {
        try {
            UsuarioDTO usuarioActualizado = new UsuarioBO(FactoryDAO.crearUsuarioDAO()).modificarDireccion(email, dto);
            return usuarioActualizado;
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar modificar dirección: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO buscarVideojuegoPorId(Long idVideojuego) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).buscarPorId(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el videojuego: " + e.getMessage(), e);
        }
    }
    
    // Esta debería devolver una lista, ya que hay muchos videojuegos con el mismo nombre (corrección sugerida desde DAO)
    public static VideojuegoDTO buscarVideojuegoPorNombeExacto(String nombre) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).buscarPorNombreExacto(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el videojuego: " + e.getMessage(), e);
        }
    }

    public static CategoriaDTO buscarCategoriaPorNombre(String nombre) throws NegocioException {
        try {
            return new CategoriaBO(FactoryDAO.crearCategoriaDAO()).buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar la categoria: " + e.getMessage(), e);
        }
    }
    
    public static List<CategoriaDTO> buscarCategoriaPorVideojuego(Long idVideojuego) throws NegocioException{
        try {
            return new CategoriaBO(FactoryDAO.crearCategoriaDAO()).buscarPorVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar la categoria: " + e.getMessage(), e);
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            VideojuegoBO videojuegoBO = new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());
            return videojuegoBO.buscarVideojuegosActivos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar los videojuegos: " + e.getMessage(), e);
        }
    }

    public static void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).crearVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear videojuego: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).actualizarVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar actualizar videojuego: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            return new UsuarioBO(FactoryDAO.crearUsuarioDAO()).actualizarUsuario(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar actualizar videojuego: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorNombreVideojuego(String nombreVideojuego) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorNombreVideojuego(nombreVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCliente(Long idCliente) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarResenasPorCalificacion(calificacion);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarTodasLasResenas() throws NegocioException {
        try {
            return new ResenaBO(FactoryDAO.crearResenaDAO()).buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static CarritoDTO buscarCarritoPorCliente(Long idCliente) throws NegocioException {
        try {
            return new CarritoBO(FactoryDAO.crearCarritoDAO()).buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el carrito: " + e.getMessage(), e);
        }
    }

    public static void agregarItemACarrito(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException {
        try {
            CarritoBO carritoBO = new CarritoBO(
                    FactoryDAO.crearCarritoDAO()
            );

            carritoBO.agregarItem(idCarrito, itemDTO);

        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar agregar el item: " + e.getMessage(), e);
        }
    }

    // PENDIENTE ELIMINAR ITEM Y VACIAR CARRITO
    public static List<PedidoDTO> obtenerPedidos() throws NegocioException {
        try {
            return new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).obtenerPedidos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar obtener los pedidos: " + e.getMessage(), e);
        }
    }

    public static void crearCarrito(CarritoDTO carrito) throws NegocioException {
        try {
            new CarritoBO(FactoryDAO.crearCarritoDAO()).crearCarrito(carrito);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear el carrito: " + e.getMessage(), e);
        }
    }

    public static void pedidoEntregado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoEntregado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como entregado: " + e.getMessage(), e);
        }
    }

    public static void pedidoEnviado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoEnviado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como enviado: " + e.getMessage(), e);
        }
    }

    public static void pedidoPendiente(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoPendiente(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como pendiente: " + e.getMessage(), e);
        }
    }

    public static void pedidoCancelado(Long idPedido) throws NegocioException {
        try {
            new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null).pedidoCancelado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como cancelado: " + e.getMessage(), e);
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosPorNombreParcial(String nombre) throws NegocioException {
        try {
            VideojuegoBO videojuegoBO = new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());
            return videojuegoBO.buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar videojuegos por nombre: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesPorNombre(String nombre) throws NegocioException {
        try {
            return new ClienteBO(FactoryDAO.crearClienteDAO(), null).buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes por nombre: " + e.getMessage(), e);
        }
    }

    /**
     * Esta es la operación de negocio más compleja. Requiere múltiples DAOs
     * para validar stock, calcular total y limpiar el carrito.
     *
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
            throw new NegocioException("Error al intentar registrar el pedido: " + e.getMessage(), e);
        }
    }

    public static PedidoDTO buscarPedidoPorId(Long idPedido) throws NegocioException {
        try {
            PedidoBO pedidoBO = new PedidoBO(FactoryDAO.crearPedidoDAO(), null, null, null);
            return pedidoBO.buscarPorId(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar un pedido por ID: " + e.getMessage(), e);
        }
    }

    public static List<CategoriaDTO> buscarTodasCategorias() throws NegocioException {
        try {
            return new CategoriaBO(FactoryDAO.crearCategoriaDAO()).buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar las categorías: " + e.getMessage(), e);
        }
    }

    public static void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            // VALIDACIONES NEGOCIO IF EL CLIENTE SI LO COMPRO ENTONCES PROCEDER
            new ResenaBO(FactoryDAO.crearResenaDAO()).crearResena(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear la reseña: " + e.getMessage(), e);
        }
    }

    public static void eliminarResenaPorId(Long idResena) throws NegocioException {
        try {
            // VALIDACIONES NEGOCIO IF EL CLIENTE SI LO COMPRO ENTONCES PROCEDER
            new ResenaBO(FactoryDAO.crearResenaDAO()).eliminarResena(idResena);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar eliminar la reseña: " + e.getMessage(), e);
        }
    }

    public static void activarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).activarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar activar el usuario: " + e.getMessage(), e);
        }
    }

    public static void desactivarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).desactivarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar desactivar el usuario: " + e.getMessage(), e);
        }
    }

    public static void eliminarUsuario(String email) throws NegocioException {
        try {
            new UsuarioBO(FactoryDAO.crearUsuarioDAO()).eliminarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar eliminar el usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Busca pedidos que coincidan parcialmente con el nombre completo de un
     * cliente.
     *
     * @param nombre El texto a buscar en los nombres y apellidos del cliente.
     * @return Una lista de PedidoDTO que coinciden.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public static List<PedidoDTO> buscarPedidosPorNombreClienteParcial(String nombre) throws NegocioException {
        try {
            PedidoBO pedidoBO = new PedidoBO(
                    FactoryDAO.crearPedidoDAO(),
                    null,
                    null,
                    null
            );

            return pedidoBO.buscarPorNombreClienteParcial(nombre);

        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar pedidos por nombre de cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Filtra videojuegos por múltiples criterios de forma dinámica. Los
     * parámetros nulos o vacíos serán ignorados en la búsqueda.
     *
     * * @param nombre Nombre parcial del juego.
     * @param nombre
     * @param precioMin Rango inferior de precio.
     * @param precioMax Rango superior de precio.
     * @param idCategoria ID de la categoría.
     * @param plataforma Nombre de la plataforma.
     * @return Lista de videojuegos filtrados.
     * @throws NegocioException
     */
    public static List<VideojuegoDTO> filtrarVideojuegos(String nombre, Float precioMin, Float precioMax, Long idCategoria, String plataforma) throws NegocioException {
        try {
            VideojuegoBO videojuegoBO = new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());

            return videojuegoBO.buscarVideojuegosConFiltros(nombre, precioMin, precioMax, idCategoria, plataforma);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar filtrar la búsqueda de videojuegos: " + e.getMessage(), e);
        }
    }

    public static void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            new VideojuegoBO(FactoryDAO.crearVideojuegoDAO()).deshabilitarVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar deshabilitar el videojuego: " + e.getMessage(), e);
        }
    }

    public static List<ItemCarritoDTO> obtenerItemsCarrito(Long idCarrito) throws NegocioException {
        try {
            CarritoBO carritoBO = new CarritoBO(FactoryDAO.crearCarritoDAO());

            return carritoBO.obtenerItemsCarrito(idCarrito);

        } catch (NegocioException e) {
            throw e;
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al intentar obtener items del carrito: " + e.getMessage(), e);
        }
    }
    
    public static void actualizarCantidadItem(Long idItemCarrito, Integer cantidad) throws NegocioException {
        try {
            new CarritoBO(FactoryDAO.crearCarritoDAO()).actualizarCantidadItem(idItemCarrito, cantidad);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public static void eliminarItemCarrito(Long idItemCarrito) throws NegocioException {
        try {
            new CarritoBO(FactoryDAO.crearCarritoDAO()).eliminarItem(idItemCarrito);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    public static void vaciarCarrito(Long idCarrito) throws NegocioException {
        try {
            new CarritoBO(FactoryDAO.crearCarritoDAO()).vaciarCarrito(idCarrito);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

}
package joystickmx.itson.Fachada;

import java.util.List;
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
import joystickmx.itson.DependencyInjectorBO.InjectorBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class FachadaBO {

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
            return InjectorBO.buildUsuarioBO().validarCredenciales(email, password);

        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar iniciar sesión: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarUsuarioPorEmail(String email) throws NegocioException {
        try {
            return InjectorBO.buildUsuarioBO().buscarPorEmail(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el usuario: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO buscarClientePorId(Long idCliente) throws NegocioException {
        try {
            return InjectorBO.buildClienteSinCarritoBO().buscarPorId(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el cliente: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesActivos() throws NegocioException {
        try {
            return InjectorBO.buildClienteSinCarritoBO().buscarUsuariosActivos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes activos: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesExistentes() throws NegocioException {
        try {
            return InjectorBO.buildClienteSinCarritoBO().buscarClientesExistentes();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes existentes: " + e.getMessage(), e);
        }
    }

    public static void registrarCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            InjectorBO.buildClienteBO().crearCliente(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar registrar cliente: " + e.getMessage(), e);
        }
    }

    public static void registrarAdministrador(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            InjectorBO.buildAdiministradorBO().crearAdmin(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar registrar administrador: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO modificarDireccionUsuario(String email, DireccionDTO dto) throws NegocioException {
        try {
            return InjectorBO.buildUsuarioBO().modificarDireccion(email, dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar modificar dirección: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO buscarVideojuegoPorId(Long idVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().buscarPorId(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el videojuego: " + e.getMessage(), e);
        }
    }

    // Esta debería devolver una lista, ya que hay muchos videojuegos con el mismo nombre (corrección sugerida desde DAO)
    public static VideojuegoDTO buscarVideojuegoPorNombeExacto(String nombre) throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().buscarPorNombreExacto(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el videojuego: " + e.getMessage(), e);
        }
    }

    public static CategoriaDTO buscarCategoriaPorNombre(String nombre) throws NegocioException {
        try {
            return InjectorBO.buildCategoriaBO().buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar la categoria: " + e.getMessage(), e);
        }
    }

    public static List<CategoriaDTO> buscarCategoriaPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildCategoriaBO().buscarPorVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar la categoria: " + e.getMessage(), e);
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().buscarVideojuegosActivos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar los videojuegos: " + e.getMessage(), e);
        }
    }

    public static void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            InjectorBO.buildVideojuegoBO().crearVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear videojuego: " + e.getMessage(), e);
        }
    }

    public static VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().actualizarVideojuego(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar actualizar videojuego: " + e.getMessage(), e);
        }
    }

    public static UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            return InjectorBO.buildUsuarioBO().actualizarUsuario(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar actualizar usuario: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorNombreVideojuego(String nombreVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarPorNombreVideojuego(nombreVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarPorVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCliente(Long idCliente) throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static ResenaDTO buscarPorVideojuegoCliente(Long idCliente, Long idVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarPorVideojuegoCliente(idCliente, idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar la reseña: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarResenasPorCalificacion(calificacion);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static List<ResenaDTO> buscarTodasLasResenas() throws NegocioException {
        try {
            return InjectorBO.buildResenaBO().buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar consultar las resenas: " + e.getMessage(), e);
        }
    }

    public static CarritoDTO buscarCarritoPorCliente(Long idCliente) throws NegocioException {
        try {
            return InjectorBO.buildCarritoBO().buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar el carrito: " + e.getMessage(), e);
        }
    }

    public static void agregarItemACarrito(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException {
        try {
            InjectorBO.buildCarritoBO().agregarItem(idCarrito, itemDTO);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public static ItemCarritoDTO buscarVideojuegoEnCarrito(Long idCarrito, Long idVideojuego) throws NegocioException {
        try {
            return InjectorBO.buildCarritoBO().buscarVideojuegoEnCarrito(idCarrito, idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar verificar la existencia del videojuego: " + e.getMessage(), e);
        }
    }

    // PENDIENTE ELIMINAR ITEM Y VACIAR CARRITO
    public static List<PedidoDTO> obtenerPedidos() throws NegocioException {
        try {
            return InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().obtenerPedidos();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar obtener los pedidos: " + e.getMessage(), e);
        }
    }

    public static void crearCarrito(CarritoDTO carrito) throws NegocioException {
        try {
            InjectorBO.buildCarritoBO().crearCarrito(carrito);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear el carrito: " + e.getMessage(), e);
        }
    }

    public static void pedidoEntregado(Long idPedido) throws NegocioException {
        try {
            InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().pedidoEntregado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como entregado: " + e.getMessage(), e);
        }
    }

    public static void pedidoEnviado(Long idPedido) throws NegocioException {
        try {
            InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().pedidoEnviado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como enviado: " + e.getMessage(), e);
        }
    }

    public static void pedidoPendiente(Long idPedido) throws NegocioException {
        try {
            InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().pedidoPendiente(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como pendiente: " + e.getMessage(), e);
        }
    }

    public static void pedidoCancelado(Long idPedido) throws NegocioException {
        try {
            InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().pedidoCancelado(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar marcar pedido como cancelado: " + e.getMessage(), e);
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosPorNombreParcial(String nombre) throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().buscarPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar videojuegos por nombre: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesPorNombre(String nombre) throws NegocioException {
        try {
            return InjectorBO.buildClienteSinCarritoBO().buscarPorNombre(nombre);
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
            return InjectorBO.buildPedidoBO().registrarPedido(idCliente, direccionEnvioDTO, pagoDTO);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar registrar el pedido: " + e.getMessage(), e);
        }
    }

    public static PedidoDTO buscarPedidoPorId(Long idPedido) throws NegocioException {
        try {
            return InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().buscarPorId(idPedido);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar un pedido por ID: " + e.getMessage(), e);
        }
    }

    public static List<CategoriaDTO> buscarTodasCategorias() throws NegocioException {
        try {
            return InjectorBO.buildCategoriaBO().buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar las categorías: " + e.getMessage(), e);
        }
    }

    public static void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            InjectorBO.buildResenaBO().crearResena(dto);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar crear la reseña: " + e.getMessage(), e);
        }
    }

    public static void eliminarResenaPorId(Long idResena) throws NegocioException {
        try {
            InjectorBO.buildResenaBO().eliminarResena(idResena);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar eliminar la reseña: " + e.getMessage(), e);
        }
    }

    public static void activarUsuario(String email) throws NegocioException {
        try {
            InjectorBO.buildUsuarioBO().activarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar activar el usuario: " + e.getMessage(), e);
        }
    }

    public static void desactivarUsuario(String email) throws NegocioException {
        try {
            InjectorBO.buildUsuarioBO().desactivarUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar desactivar el usuario: " + e.getMessage(), e);
        }
    }

    public static void eliminarUsuario(String email) throws NegocioException {
        try {
            InjectorBO.buildUsuarioBO().eliminarUsuario(email);
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
            return InjectorBO.buildPedidoSinClienteCarritoVideojuegoBO().buscarPorNombreClienteParcial(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar pedidos por nombre de cliente: " + e.getMessage(), e);
        }
    }

    public static List<PedidoDTO> buscarPedidosPorCliente(Long idCliente) throws NegocioException {
        try {
            return InjectorBO.buildPedidoClienteBO().buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar pedidos por cliente: " + e.getMessage(), e);
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
    public static List<VideojuegoDTO> filtrarVideojuegos(
            String nombre,
            Float precioMin,
            Float precioMax,
            Long idCategoria,
            String plataforma
    ) throws NegocioException {
        try {
            return InjectorBO.buildVideojuegoBO().buscarVideojuegosConFiltros(
                    nombre, 
                    precioMin, 
                    precioMax, 
                    idCategoria, 
                    plataforma
            );
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar filtrar la búsqueda de videojuegos: " + e.getMessage(), e);
        }
    }

    public static void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            InjectorBO.buildVideojuegoBO().deshabilitarVideojuego(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar deshabilitar el videojuego: " + e.getMessage(), e);
        }
    }

    public static List<ItemCarritoDTO> obtenerItemsCarrito(Long idCarrito) throws NegocioException {
        try {
            return InjectorBO.buildCarritoBO().obtenerItemsCarrito(idCarrito);
        } catch (NegocioException e) {
            throw e;
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al intentar obtener items del carrito: " + e.getMessage(), e);
        }
    }

    public static void actualizarCantidadItem(Long idItemCarrito, Integer cantidad) throws NegocioException {
        try {
            InjectorBO.buildCarritoBO().actualizarCantidadItem(idItemCarrito, cantidad);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public static void eliminarItemCarrito(Long idItemCarrito) throws NegocioException {
        try {
            InjectorBO.buildCarritoBO().eliminarItem(idItemCarrito);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public static void vaciarCarrito(Long idCarrito) throws NegocioException {
        try {
            InjectorBO.buildCarritoBO().vaciarCarrito(idCarrito);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public static DireccionDTO obtenerDireccionUsuario(String email) throws NegocioException {
        try {
            return InjectorBO.buildUsuarioBO().obtenerDireccionPorUsuario(email);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar obtener direccion de usuario: " + e.getMessage(), e);
        }
    }

    public static List<String> validarExistenciasVideojuego(Long idUsuario) throws NegocioException {
        try {
            return InjectorBO.buildCarritoBO().validarExistenciasVideojuego(idUsuario);
        } catch (NegocioException e) {
            throw new NegocioException("Error al validar existencias: " + e.getMessage(), e);
        }
    }

    public static List<UsuarioDTO> buscarClientesPorNombreNoEliminados(String nombre) throws NegocioException {
        try {
            return InjectorBO.buildClienteSinCarritoBO().buscarClientesNoEliminadosPorNombre(nombre);
        } catch (NegocioException e) {
            throw new NegocioException("Error al intentar buscar clientes por nombre: " + e.getMessage(), e);
        }
    }
}
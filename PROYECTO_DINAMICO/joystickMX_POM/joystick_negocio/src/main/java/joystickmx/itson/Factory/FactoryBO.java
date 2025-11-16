
package joystickmx.itson.Factory;

import jakarta.persistence.EntityManager;
import java.util.List;
import joystickmx.itson.BO.AdministradorBO;
import joystickmx.itson.BO.CarritoBO;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.BO.ClienteBO;
import joystickmx.itson.BO.PedidoBO;
import joystickmx.itson.BO.ResenaBO;
import joystickmx.itson.BO.UsuarioBO;
import joystickmx.itson.BO.VideojuegoBO;
import joystickmx.itson.DAOS.AdministradorDAO;
import joystickmx.itson.DAOS.CarritoDAO;
import joystickmx.itson.DAOS.CategoriaDAO;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DAOS.PedidoDAO;
import joystickmx.itson.DAOS.ResenaDAO;
import joystickmx.itson.DAOS.UsuarioDAO;
import joystickmx.itson.DAOS.VideojuegoDAO;
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
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.interfaces.IAdministradorDAO;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.itson.interfaces.ICategoriaDAO;
import joystickmx.itson.interfaces.IClienteDAO;
import joystickmx.itson.interfaces.IPedidoDAO;
import joystickmx.itson.interfaces.IResenaDAO;
import joystickmx.itson.interfaces.IUsuarioDAO;
import joystickmx.itson.interfaces.IVideojuegoDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC WHITE WOLF
 * @author biccs
 */
public class FactoryBO {
    
    
    
    
    
    
    /**
     * Valida las credenciales de un usuario.
     * Es un método de LECTURA, por lo que no necesita transacción (begin/commit).
     * @param email El email del usuario.
     * @param password La contraseña en texto plano.
     * @return El UsuarioDTO con los datos de sesión.
     * @throws NegocioException Si la validación falla.
     */
    public static UsuarioDTO login(String email, String password) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            UsuarioBO usuarioBO = new UsuarioBO(usuarioDAO);
            
            return usuarioBO.validarCredenciales(email, password);
            
        } catch (NegocioException e) {
            throw new NegocioException("Error de persistencia en login: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static UsuarioDTO buscarUsuarioPorEmail(String email) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            UsuarioBO usuarioBO = new UsuarioBO(usuarioDAO);
            return usuarioBO.buscarPorEmail(email);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static UsuarioDTO buscarClientePorId(Long idCliente) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IClienteDAO clienteDAO = new ClienteDAO(em);
            ClienteBO clienteBO = new ClienteBO(clienteDAO, null);
            return clienteBO.buscarPorId(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static List<UsuarioDTO> buscarClientesActivos() throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IClienteDAO clienteDAO = new ClienteDAO(em);
            ClienteBO clienteBO = new ClienteBO(clienteDAO, null);
            return clienteBO.buscarUsuariosActivos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    //  pendientes metodos de buscar clientes activos inactivos por nombre etc


    public static void registrarCliente(UsuarioRegistroDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();

            IClienteDAO clienteDAO = new ClienteDAO(em);
            ICarritoDAO carritoDAO = new CarritoDAO(em);
            ClienteBO clienteBO = new ClienteBO(clienteDAO, carritoDAO);
            
            clienteBO.crearCliente(dto);

            em.getTransaction().commit();
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al registrar cliente: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static void registrarAdministrador(UsuarioRegistroDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();

            IAdministradorDAO adminDAO = new AdministradorDAO(em);
            AdministradorBO adminBO = new AdministradorBO(adminDAO);
            
            adminBO.crearAdmin(dto);

            em.getTransaction().commit();
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static UsuarioDTO modificarDireccionUsuario(String email, DireccionDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            UsuarioBO usuarioBO = new UsuarioBO(usuarioDAO);
            
            UsuarioDTO usuarioActualizado = usuarioBO.modificarDireccion(email, dto);
            
            em.getTransaction().commit();
            return usuarioActualizado;
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al modificar dirección: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    // pendiente activar desactivar eliminar usuario


    public static VideojuegoDTO buscarVideojuegoPorId(Long idVideojuego) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IVideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
            VideojuegoBO videojuegoBO = new VideojuegoBO(videojuegoDAO);
            return videojuegoBO.buscarPorId(idVideojuego);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IVideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
            VideojuegoBO videojuegoBO = new VideojuegoBO(videojuegoDAO);
            return videojuegoBO.buscarVideojuegosActivos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    // pendiente bussquedas de juegos por nombre etc

    public static void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            IVideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
            VideojuegoBO videojuegoBO = new VideojuegoBO(videojuegoDAO);
            videojuegoBO.crearVideojuego(dto);
            
            em.getTransaction().commit();
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al crear videojuego: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            IVideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
            VideojuegoBO videojuegoBO = new VideojuegoBO(videojuegoDAO);
            VideojuegoDTO actualizado = videojuegoBO.actualizarVideojuego(dto);
            
            em.getTransaction().commit();
            return actualizado;
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }


    public static CarritoDTO buscarCarritoPorCliente(Long idCliente) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            ICarritoDAO carritoDAO = new CarritoDAO(em);
            CarritoBO carritoBO = new CarritoBO(carritoDAO);
            return carritoBO.buscarPorCliente(idCliente);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public static void agregarItemACarrito(Long idCarrito, ItemCarritoDTO itemDTO) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            ICarritoDAO carritoDAO = new CarritoDAO(em);
            CarritoBO carritoBO = new CarritoBO(carritoDAO);
            
            // PENDIENTE MAS LOGICA DE NEGOCIO
            //CHECAR SI YA EXISTE O SI HAY STOCK
            carritoBO.agregarItem(idCarrito, itemDTO);
            
            em.getTransaction().commit();
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al agregar item: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    // PENDIENTE ELIMINAR ITEM Y VACIAR CARRITO

    
    public static List<PedidoDTO> obtenerPedidos() throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            IPedidoDAO pedidoDAO = new PedidoDAO(em);
            PedidoBO pedidoBO = new PedidoBO(pedidoDAO, null, null, null); // Ajustar dependencias
            return pedidoBO.obtenerPedidos();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    /**
     * Esta es la operación de negocio más compleja.
     * Requiere múltiples DAOs para validar stock, calcular total y limpiar el carrito.
     */
    public static PedidoDTO registrarPedido(Long idCliente, DireccionDTO direccionEnvioDTO, PagoDTO pagoDTO) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            IPedidoDAO pedidoDAO = new PedidoDAO(em);
            IClienteDAO clienteDAO = new ClienteDAO(em);
            ICarritoDAO carritoDAO = new CarritoDAO(em);
            IVideojuegoDAO videojuegoDAO = new VideojuegoDAO(em);
            
            PedidoBO pedidoBO = new PedidoBO(pedidoDAO, clienteDAO, carritoDAO, videojuegoDAO);
            
            PedidoDTO nuevoPedido = pedidoBO.registrarPedido(idCliente, direccionEnvioDTO, pagoDTO);
            
            em.getTransaction().commit();
            return nuevoPedido;
            
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al registrar el pedido: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    
    public static List<CategoriaDTO> buscarTodasCategorias() throws NegocioException {
         EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            ICategoriaDAO categoriaDAO = new CategoriaDAO(em);
            CategoriaBO categoriaBO = new CategoriaBO(categoriaDAO);
            return categoriaBO.buscarTodas();
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    
    public static void crearResena(ResenaDTO dto) throws NegocioException {
        EntityManager em = null;
        try {
            em = Conexion.crearConexion();
            em.getTransaction().begin();
            
            IResenaDAO resenaDAO = new ResenaDAO(em);
            ResenaBO resenaBO = new ResenaBO(resenaDAO);
            // VALIDACIONES NEGOCIO IF EL CLIENTE SI LO COMPRO ENTONCES PROCEDER
            resenaBO.crearResena(dto);
            
            em.getTransaction().commit();
        } catch (NegocioException e) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new NegocioException("Error al crear reseña: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.BO.Utils.PasswordUtil;
import joystickmx.itson.DTO.ClienteDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.itson.interfaces.IClienteDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class ClienteBO {
    
    private final IClienteDAO clienteDAO;
    private final ICarritoDAO carritoDAO; 

    public ClienteBO(IClienteDAO clienteDAO, ICarritoDAO carritoDAO) {
        this.clienteDAO = clienteDAO;
        this.carritoDAO = carritoDAO;
    }

    public void crearCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            if (this.clienteDAO.buscarPorEmail(dto.getEmail()) != null) {
                throw new NegocioException("El correo ya se encuentra registrado.");
            }
            
            Cliente nuevoCliente = DTOMapeadores.toClienteEntity(dto);
            
            nuevoCliente.setContrasenia(PasswordUtil.hashPassword(dto.getContrasenia()));
            
            nuevoCliente.setEstadoUsuario(EstadoUsuario.ACTIVO);
            
            Carrito nuevoCarrito = new Carrito();
            this.carritoDAO.crearCarrito(nuevoCarrito);
            nuevoCliente.setCarrito(nuevoCarrito); 
            
            this.clienteDAO.crearCliente(nuevoCliente);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar cliente: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarUsuariosActivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesActivos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes activos: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarUsuariosInactivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesInactivos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes inactivos: " + e.getMessage(), e);
        }
    }
    
    public List<UsuarioDTO> buscarClientesExistentes() throws NegocioException{
        try {
            return this.clienteDAO.buscarClientesExistentes().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes inactivos: " + e.getMessage(), e);
        }
    }
    
    public UsuarioDTO actualizarCliente(ClienteDTO dto) throws NegocioException {
        try {
            Cliente cliente = this.clienteDAO.buscarPorId(dto.getIdUsuario());
            if (cliente == null) {
                throw new NegocioException("Cliente no encontrado.");
            }
            
            cliente.setNombres(dto.getNombres());
            cliente.setApellidoPaterno(dto.getApellidoPaterno());
            cliente.setApellidoMaterno(dto.getApellidoMaterno());
            cliente.setEmail(dto.getEmail());
            cliente.setTelefono(dto.getTelefono());
            
            return Mapeadores.toUsuarioDTO(this.clienteDAO.actualizarCliente(cliente)); 
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar cliente: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorId(Long idCliente) throws NegocioException {
        try {
            Cliente cliente = this.clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                throw new NegocioException("Cliente no encontrado.");
            }
            return Mapeadores.toUsuarioDTO(cliente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            Cliente cliente = this.clienteDAO.buscarPorEmail(email);
            if (cliente == null) {
                return null;
            }
            return Mapeadores.toUsuarioDTO(cliente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente por email: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarTodos() throws NegocioException {
        try {
            return this.clienteDAO.buscarTodos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los clientes: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return this.clienteDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes por nombre: " + e.getMessage(), e);
        }
    }
}
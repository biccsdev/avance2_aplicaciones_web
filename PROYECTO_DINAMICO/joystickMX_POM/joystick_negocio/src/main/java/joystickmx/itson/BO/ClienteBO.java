package joystickmx.itson.BO;

import java.util.ArrayList;
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
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.itson.interfaces.IClienteDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IClienteBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class ClienteBO implements IClienteBO {

    private final IClienteDAO clienteDAO;
    private final ICarritoDAO carritoDAO;

    public ClienteBO(IClienteDAO clienteDAO, ICarritoDAO carritoDAO) {
        this.clienteDAO = clienteDAO;
        this.carritoDAO = carritoDAO;
    }

    @Override
    public void crearCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            if (this.clienteDAO.buscarPorEmail(dto.getEmail()) != null) {
                throw new NegocioException("El correo ya se encuentra registrado.");
            }

            Cliente nuevoCliente = DTOMapeadores.toClienteEntity(dto);

            nuevoCliente.setContrasenia(PasswordUtil.hashPassword(dto.getContrasenia()));

            nuevoCliente.setEstadoUsuario(EstadoUsuario.ACTIVO);

            Carrito nuevoCarrito = new Carrito();
            nuevoCliente.setCarrito(nuevoCarrito);

            this.clienteDAO.crearCliente(nuevoCliente);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosActivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesActivos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes activos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosInactivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesInactivos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes inactivos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarClientesExistentes() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesExistentes().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes inactivos: " + e.getMessage(), e);
        }
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<UsuarioDTO> buscarTodos() throws NegocioException {
        try {
            return this.clienteDAO.buscarTodos().stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los clientes: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return this.clienteDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toUsuarioDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes por nombre: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarClientesNoEliminadosPorNombre(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return this.buscarClientesExistentes();
            }

            List<Usuario> usuarios = this.clienteDAO.buscarPorNombreNoEliminados(nombre.trim());
            List<UsuarioDTO> usuariosDTO = new ArrayList<>();
            for (Usuario u : usuarios) {
                usuariosDTO.add(Mapeadores.toUsuarioDTO(u));
            }

            return usuariosDTO;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en negocio al buscar clientes: " + e.getMessage(), e);
        }
    }

}

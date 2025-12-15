package joystickmx.itson.BO;

import joystickmx.itson.BO.Utils.PasswordUtil;
import joystickmx.itson.DAOS.DireccionDAO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IUsuarioDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IUsuarioBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class UsuarioBO implements IUsuarioBO {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioBO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void crearUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {

            if (dto == null) {
                throw new NegocioException("Los datos del usuario no pueden ser nulos.");
            }

            validarDatosObligatorios(dto.getNombres(), dto.getApellidoPaterno(), dto.getEmail(), dto.getTelefono());

            if (dto.getContrasenia() == null || dto.getContrasenia().trim().isEmpty()) {
                throw new NegocioException("La contraseña es obligatoria para el registro.");
            }

            if (this.usuarioDAO.buscarPorEmail(dto.getEmail()) != null) {
                throw new NegocioException("El correo electrónico ya está registrado.");
            }

            String hash = PasswordUtil.hashPassword(dto.getContrasenia());
            dto.setContrasenia(hash);

            this.usuarioDAO.crearUsuario(DTOMapeadores.toClienteEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            Usuario usuarioExistente = this.usuarioDAO.buscarPorEmail(dto.getEmail());

            if (usuarioExistente == null) {
                throw new NegocioException("El usuario a actualizar no existe.");
            }

            validarDatosObligatorios(dto.getNombres(), dto.getApellidoPaterno(), dto.getEmail(), dto.getTelefono());

            usuarioExistente.setNombres(dto.getNombres());
            usuarioExistente.setApellidoPaterno(dto.getApellidoPaterno());
            usuarioExistente.setApellidoMaterno(dto.getApellidoMaterno());
            usuarioExistente.setTelefono(dto.getTelefono());

            if (dto.getDireccion() != null) {
                if (usuarioExistente.getDireccion() != null) {
                    usuarioExistente.getDireccion().setCalle(dto.getDireccion().getCalle());
                    usuarioExistente.getDireccion().setNumero(dto.getDireccion().getNumero());
                    usuarioExistente.getDireccion().setColonia(dto.getDireccion().getColonia());
                } else {
                    usuarioExistente.setDireccion(DTOMapeadores.toDireccionEntity(dto.getDireccion()));
                }
            }

            if (dto.getContrasenia() != null && !dto.getContrasenia().trim().isEmpty()) {
                String hash = PasswordUtil.hashPassword(dto.getContrasenia());
                usuarioExistente.setContrasenia(hash);
            }
            return Mapeadores.toUsuarioDTO(this.usuarioDAO.actualizar(usuarioExistente));

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO buscarPorId(Long idUsuario) throws NegocioException {
        try {

            if (idUsuario == null) {
                throw new NegocioException("ID requerido.");
            }

            return Mapeadores.toUsuarioDTO(this.usuarioDAO.buscarPorId(idUsuario));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {

            if (email == null || email.trim().isEmpty()) {
                return null;
            }

            return Mapeadores.toUsuarioDTO(this.usuarioDAO.buscarPorEmail(email));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar usuario por email: " + e.getMessage(), e);
        }
    }

    @Override
    public void activarUsuario(String email) throws NegocioException {
        try {

            if (email == null) {
                throw new NegocioException("Email requerido para activar usuario.");
            }

            this.usuarioDAO.activarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al activar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void desactivarUsuario(String email) throws NegocioException {
        try {

            if (email == null) {
                throw new NegocioException("Email requerido para activar usuario.");
            }

            this.usuarioDAO.desactivarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al desactivar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarUsuario(String email) throws NegocioException {
        try {

            if (email == null) {
                throw new NegocioException("Email requerido para activar usuario.");
            }

            this.usuarioDAO.eliminarUsuario(email);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar (soft delete) usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO modificarDireccion(String email, DireccionDTO dto) throws NegocioException {
        try {

            if (email == null) {
                throw new NegocioException("Email de usuario requerido.");
            }
            if (dto == null) {
                throw new NegocioException("Datos de dirección requeridos.");
            }

            if (this.usuarioDAO.buscarPorEmail(email) == null) {
                throw new NegocioException("Usuario no encontrado.");
            }

            Direccion datosNuevos = DTOMapeadores.toDireccionEntity(dto);

            Usuario usuarioActualizado = this.usuarioDAO.modificarDireccion(email, datosNuevos);

            return Mapeadores.toUsuarioDTO(usuarioActualizado);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al modificar la dirección: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO validarCredenciales(String email, String passwordPlano) throws NegocioException {
        try {
            Usuario usuario = this.usuarioDAO.buscarPorEmail(email);

            if (usuario == null) {
                throw new NegocioException("Credenciales incorrectas.");
            }

            if (usuario.getEstadoUsuario() != EstadoUsuario.ACTIVO) {
                throw new NegocioException("La cuenta de usuario no está activa.");
            }

            boolean passwordValida = PasswordUtil.verificarPassword(passwordPlano, usuario.getContrasenia());

            if (!passwordValida) {
                throw new NegocioException("Credenciales incorrectas.");
            }

            return Mapeadores.toUsuarioDTO(usuario);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al validar credenciales: " + e.getMessage(), e);
        }
    }

    @Override
    public DireccionDTO obtenerDireccionPorUsuario(String email) throws NegocioException {
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new NegocioException("El Email de usuario es obligatorio");
            }

            DireccionDAO direccionDAO = new DireccionDAO();
            Direccion direccionEntidad = direccionDAO.buscarPorEmailUsuario(email);

            if (direccionEntidad == null) {
                return null;
            }

            return Mapeadores.toDireccionDTO(direccionEntidad);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en Negocio al recuperar la direccion  : " + e.getMessage(), e);
        }
    }

    private void validarDatosObligatorios(String nombres, String apellidoPaterno, String email, String telefono) throws NegocioException {
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new NegocioException("El nombre es obligatorio.");
        }
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new NegocioException("El correo es obligatorio.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono es obligatorio.");
        }
    }
}

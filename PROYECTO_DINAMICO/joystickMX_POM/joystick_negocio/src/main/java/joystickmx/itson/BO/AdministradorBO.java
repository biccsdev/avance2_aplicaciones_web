package joystickmx.itson.BO;

import joystickmx.itson.BO.Utils.PasswordUtil;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IAdministradorDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IAdministradorBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class AdministradorBO implements IAdministradorBO{
    
    private final IAdministradorDAO adminDAO;

    public AdministradorBO(IAdministradorDAO adminDAO) { this.adminDAO = adminDAO; }

    @Override
    public void crearAdmin(UsuarioRegistroDTO dto) throws NegocioException {
        try {

            validarDatosObligatorios(dto.getNombres(), dto.getApellidoPaterno(), dto.getEmail(), dto.getTelefono());
            
            if (dto.getContrasenia() == null || dto.getContrasenia().trim().isEmpty()) {
                throw new NegocioException("La contraseña es obligatoria.");
            }

            if (this.adminDAO.buscarPorEmail(dto.getEmail()) != null) {
                throw new NegocioException("El correo ya se encuentra registrado.");
            }
            
            
            Administrador admin = DTOMapeadores.toAdministradorEntity(dto);
            
            admin.setContrasenia(PasswordUtil.hashPassword(dto.getContrasenia()));
            
            admin.setEstadoUsuario(EstadoUsuario.ACTIVO);
            
            this.adminDAO.crearAdministrador(admin);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO actualizarAdministrador(UsuarioDTO dto) throws NegocioException {
        try {
            Administrador admin = this.adminDAO.buscarPorId(dto.getIdUsuario());
            if (admin == null) {
                throw new NegocioException("No se encontró el administrador con ID: " + dto.getIdUsuario());
            }
            
            if (admin == null) {
                throw new NegocioException("No se encontró el administrador con ID: " + dto.getIdUsuario());
            }
            
            validarDatosObligatorios(dto.getNombres(), dto.getApellidoPaterno(), dto.getEmail(), dto.getTelefono());

            
            
            admin.setNombres(dto.getNombres());
            admin.setApellidoPaterno(dto.getApellidoPaterno());
            admin.setApellidoMaterno(dto.getApellidoMaterno());
            admin.setEmail(dto.getEmail()); // Se debe validar si el email nuevo ya existe
            admin.setTelefono(dto.getTelefono());
            
            Administrador actualizado = this.adminDAO.actualizarAdministrador(admin);
            
            return Mapeadores.toUsuarioDTO(actualizado); 
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar administrador: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO buscarPorId(Long idAdmin) throws NegocioException {
        try {
            
            if (idAdmin == null) {
                throw new NegocioException("El ID es requerido.");
            }
            
            Administrador admin = this.adminDAO.buscarPorId(idAdmin);
            if(admin == null) {
                throw new NegocioException("Administrador no encontrado.");
            }
            return Mapeadores.toUsuarioDTO(admin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            
            if (email == null || email.trim().isEmpty()) {
                throw new NegocioException("El email es requerido para la búsqueda.");
            }
            
            Administrador admin = this.adminDAO.buscarPorEmail(email);
            if(admin == null) {
                return null;
            }
            return Mapeadores.toUsuarioDTO(admin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por email: " + e.getMessage(), e);
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
            throw new NegocioException("El correo electrónico es obligatorio.");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
             throw new NegocioException("El formato del correo electronico no es valido.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono es obligatorio.");
        }
    }
}
package joystickmx.itson.BO;

import joystickmx.itson.BO.Utils.PasswordUtil;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.interfaces.IAdministradorDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class AdministradorBO {
    
    private final IAdministradorDAO adminDAO;

    public AdministradorBO(IAdministradorDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void crearAdmin(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            
            if (this.adminDAO.buscarPorEmail(dto.getEmail()) != null) 
                throw new NegocioException("El correo ya se encuentra registrado.");
            
            
            Administrador admin = DTOMapeadores.toAdministradorEntity(dto);
            
            admin.setContrasenia(PasswordUtil.hashPassword(dto.getContrasenia()));
            
            admin.setEstadoUsuario(EstadoUsuario.ACTIVO);
            
            this.adminDAO.crearAdministrador(admin);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO actualizarAdministrador(UsuarioDTO dto) throws NegocioException {
        try {
            Administrador admin = this.adminDAO.buscarPorId(dto.getIdUsuario());
            if (admin == null) {
                throw new NegocioException("No se encontró el administrador con ID: " + dto.getIdUsuario());
            }
            
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

    public UsuarioDTO buscarPorId(Long idAdmin) throws NegocioException {
        try {
            Administrador admin = this.adminDAO.buscarPorId(idAdmin);
            if(admin == null) {
                throw new NegocioException("Administrador no encontrado.");
            }
            return Mapeadores.toUsuarioDTO(admin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            Administrador admin = this.adminDAO.buscarPorEmail(email);
            if(admin == null) {
                return null;
            }
            return Mapeadores.toUsuarioDTO(admin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por email: " + e.getMessage(), e);
        }
    }
}
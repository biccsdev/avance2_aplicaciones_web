/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import joystickmx.itson.DAOS.AdministradorDAO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class AdministradorBO {
    
    private final AdministradorDAO adminDAO;

    public AdministradorBO(AdministradorDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void crearAdmin(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            Administrador admin = new Administrador();
            admin.setNombres(dto.getNombres());
            admin.setApellidoPaterno(dto.getApellidoPaterno());
            admin.setApellidoMaterno(dto.getApellidoMaterno());
            admin.setEmail(dto.getEmail());
            admin.setTelefono(dto.getTelefono());
            admin.setContrasenia(dto.getContrasenia()); //PENDIENTE ENCRIPTAR AQUI
            admin.setEstadoUsuario(EstadoUsuario.ACTIVO);

            if (dto.getDireccion() != null) {
                Direccion dir = DTOMapeadores.toEntity(dto.getDireccion());
                admin.setDireccion(dir);
            }
            
            this.adminDAO.crearAdministrador(admin);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO actualizarAdministrador(UsuarioDTO dto) throws NegocioException {
        try {
            Administrador admin = new Administrador();
            admin.setIdUsuario(Long.parseLong(dto.getIdUsuario()));
            admin.setNombres(dto.getNombres());
            admin.setApellidoPaterno(dto.getApellidoPaterno());
            admin.setApellidoMaterno(dto.getApellidoMaterno());
            admin.setEmail(dto.getEmail());
            admin.setTelefono(dto.getTelefono());
            
            return Mapeadores.toDTO(this.adminDAO.actualizarAdministrador(admin));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar administrador: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorId(Long idAdmin) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.adminDAO.buscarPorId(idAdmin));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.adminDAO.buscarPorEmail(email));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar administrador por email: " + e.getMessage(), e);
        }
    }
}

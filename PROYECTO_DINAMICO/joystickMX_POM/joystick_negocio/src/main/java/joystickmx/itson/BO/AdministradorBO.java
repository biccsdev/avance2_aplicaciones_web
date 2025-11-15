/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import joystickmx.itson.DAOS.AdministradorDAO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
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
            
            this.adminDAO.crearAdmin(admin);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar administrador: " + e.getMessage(), e);
        }
    }
}

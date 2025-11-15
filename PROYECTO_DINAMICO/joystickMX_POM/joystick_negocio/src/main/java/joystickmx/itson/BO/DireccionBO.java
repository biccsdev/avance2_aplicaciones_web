/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import joystickmx.itson.DAOS.DireccionDAO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.entidades.Direccion;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
public class DireccionBO {
    
    private final DireccionDAO direccionDAO;

    public DireccionBO(DireccionDAO direccionDAO) {
        this.direccionDAO = direccionDAO;
    }

    public boolean modificarDireccion(String email, DireccionDTO dto) throws NegocioException {
        try {
            Direccion datosNuevos = DTOMapeadores.toEntity(dto);
            return this.direccionDAO.modificarDireccion(email, datosNuevos);
        } catch (Exception e) {
            throw new NegocioException("Error al modificar la dirección: " + e.getMessage(), e);
        }
    }
}

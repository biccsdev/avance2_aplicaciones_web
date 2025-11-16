package joystickmx.itson.BO;

import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.interfaces.IDireccionDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 */
public class DireccionBO {
    
    private final IDireccionDAO direccionDAO;

    public DireccionBO(IDireccionDAO direccionDAO) {
        this.direccionDAO = direccionDAO;
    }

    public boolean modificarDireccion(String email, DireccionDTO dto) throws NegocioException {
        try {
            Direccion datosNuevos = DTOMapeadores.toDireccionEntity(dto);
            return this.direccionDAO.actualizarDireccion(datosNuevos) != null;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al modificar la dirección: " + e.getMessage(), e);
        }
    }
}

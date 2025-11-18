
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.interfaces.IResenaDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class ResenaBO {
    
    private final IResenaDAO resenaDAO;

    public ResenaBO(IResenaDAO resenaDAO) {
        this.resenaDAO = resenaDAO;
    }

    public void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            this.resenaDAO.crearResena(DTOMapeadores.toResenaEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear reseña: " + e.getMessage(), e);
        }
    }

    public ResenaDTO actualizarResena(ResenaDTO dto) throws NegocioException {
        try {
            return Mapeadores.toResenaDTO(this.resenaDAO.actualizarResena(DTOMapeadores.toResenaEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar reseña: " + e.getMessage(), e);
        }
    }

    public void eliminarResena(Long idResena) throws NegocioException {
        try {
            this.resenaDAO.eliminarResena(idResena);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar reseña: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorVideojuego(idVideojuego).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }
    
    public List<ResenaDTO> buscarPorNombreVideojuego(String nombreVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorNombreVideojuego(nombreVideojuego).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarPorCliente(Long idCliente) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorCliente(idCliente).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por cliente: " + e.getMessage(), e);
        }
    }
    
    public List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException{
        try {
            return this.resenaDAO.buscarPorCalificacion(calificacion).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por cliente: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarTodas() throws NegocioException {
        try {
            return this.resenaDAO.buscarTodas().stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todas las reseñas: " + e.getMessage(), e);
        }
    }
}
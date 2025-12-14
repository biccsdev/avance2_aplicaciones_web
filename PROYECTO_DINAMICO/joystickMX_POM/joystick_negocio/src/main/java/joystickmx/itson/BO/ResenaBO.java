package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.BO.Utils.ReviewValidationUtil;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DependencyInjectorBO.InjectorBO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.interfaces.IResenaDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IResenaBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class ResenaBO implements IResenaBO {
    
    private final IResenaDAO resenaDAO;

    public ResenaBO(IResenaDAO resenaDAO) { this.resenaDAO = resenaDAO; }

    @Override
    public void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            // Valida que el dto no sea null
            if(dto == null)
                throw new NegocioException("La reseña está vacía.");
            
            // Obtiene el id del cliente y crea un mensaje de error
            Long idCliente = dto.getIdCliente();
            String clienteError = "La reseña no tiene un cliente asociado.";
            
            // Valida que el id del cliente no sea null
            if(idCliente == null)
                throw new NegocioException(clienteError);
            
            // Valida que el id del cliente pertenezca a un cliente real en la BD
            try {
                InjectorBO.buildClienteBO().buscarPorId(idCliente);
            } catch (NegocioException e) {
                // Curiosamente ya se valida en la clase ClienteBO (arroja excepción si el cliente no existe)
                throw new NegocioException(clienteError, e);
            }
            
            // Obtiene el id del videojuego y crea un mensaje de error
            Long idVideojuego = dto.getIdVideojuego();
            String juegoError = "La reseña no tiene un videojuego asociado";
            
            // Valida que el id del videojuego no sea null
            if(idVideojuego == null)
                throw new NegocioException(juegoError);
            // Valida que el id del videojuego pertenezca a un videojuego real en la BD
            if(InjectorBO.buildVideojuegoBO().buscarPorId(idVideojuego) == null)
                throw new NegocioException(juegoError); // En esta ocasión la clase VideojuegoBO no valida esta parte
            
            // Valida si el cliente ya tiene una reseña del videojuego
            if(buscarPorVideojuegoCliente(idCliente, idVideojuego) != null)
                throw new NegocioException("Ya existe un una reseña del cliente asociada al videojuego");
            
            // Extra la información de la reseña
            float calificacion = dto.getCalificacion();
            String titulo = dto.getTitulo();
            String comentario = dto.getComentario();
            
            // Valida la información de la reseña.
            ReviewValidationUtil.validarCalificacion(calificacion);
            ReviewValidationUtil.validarTitulo(titulo);
            ReviewValidationUtil.validarComentario(comentario);
            
            // Si se pasaron todas las validaciones, se procede a almacenar la reseña en la BD.
            this.resenaDAO.crearResena(DTOMapeadores.toResenaEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear reseña: " + e.getMessage(), e);
        }
    }

    @Override
    public ResenaDTO actualizarResena(ResenaDTO dto) throws NegocioException {
        try {
            return Mapeadores.toResenaDTO(this.resenaDAO.actualizarResena(DTOMapeadores.toResenaEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar reseña: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarResena(Long idResena) throws NegocioException {
        try {
            this.resenaDAO.eliminarResena(idResena);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar reseña: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ResenaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorVideojuego(idVideojuego).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<ResenaDTO> buscarPorNombreVideojuego(String nombreVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorNombreVideojuego(nombreVideojuego).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ResenaDTO> buscarPorCliente(Long idCliente) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorCliente(idCliente).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por cliente: " + e.getMessage(), e);
        }
    }
    
    @Override
    public ResenaDTO buscarPorVideojuegoCliente(Long idCliente, Long idVideojuego) throws NegocioException{
        try {
            return Mapeadores.toResenaDTO(this.resenaDAO.buscarPorVideojuegoCliente(idCliente, idVideojuego));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar la reseña asociado al videojuego y al cliente: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<ResenaDTO> buscarResenasPorCalificacion(Float calificacion) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorCalificacion(calificacion).stream()
                    .map(Mapeadores::toResenaDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por cliente: " + e.getMessage(), e);
        }
    }

    @Override
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
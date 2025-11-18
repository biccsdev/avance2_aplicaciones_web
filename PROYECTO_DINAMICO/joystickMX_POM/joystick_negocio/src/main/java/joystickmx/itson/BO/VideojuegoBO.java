package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.interfaces.IVideojuegoDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class VideojuegoBO {

    private final IVideojuegoDAO videojuegoDAO;

    public VideojuegoBO(IVideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    public void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            this.videojuegoDAO.persistir(DTOMapeadores.toVideojuegoEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear videojuego: " + e.getMessage(), e);
        }
    }

    public VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.actualizar(DTOMapeadores.toVideojuegoEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        }
    }

    public void habilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            this.videojuegoDAO.habilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar videojuego: " + e.getMessage(), e);
        }
    }

    public void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            this.videojuegoDAO.deshabilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar videojuego: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarTodosLosVideojuegos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarTodosLosVideojuegos().stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los videojuegos: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarVideojuegosActivos().stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuegos activos: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorRangoDePrecio(Float min, Float max) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorRangoDePrecio(min, max).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por rango de precio: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorCategoria(Long idCategoria) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorCategoria(idCategoria).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por categoría: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    public VideojuegoDTO buscarPorNombreExacto(String nombre) throws NegocioException {
        try {
            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.buscarPorNombreExacto(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    public VideojuegoDTO buscarPorId(Long idVideojuego) throws NegocioException {
        try {
            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.buscarPorId(idVideojuego));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuego por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Busca videojuegos aplicando filtros combinados.
     *
     * @param nombre Parte del nombre del videojuego (opcional).
     * @param precioMin Precio mínimo (opcional).
     * @param precioMax Precio máximo (opcional).
     * @param idCategoria ID de la categoría (opcional).
     * @param plataforma Nombre de la plataforma (opcional).
     * @return Lista de VideojuegoDTO que cumplen con los criterios.
     * @throws NegocioException Si ocurre un error en la persistencia.
     */
    
    public List<VideojuegoDTO> buscarVideojuegosConFiltros(String nombre, Float precioMin, Float precioMax, Long idCategoria, String plataforma) throws NegocioException {
        try {
            List<Videojuego> videojuegos = this.videojuegoDAO.buscarConFiltros(nombre, precioMin, precioMax, idCategoria, plataforma);

            return videojuegos.stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al filtrar videojuegos: " + e.getMessage(), e);
        }
    }

}

package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Fachada.FachadaBO;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.interfaces.IVideojuegoDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IVideojuegoBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class VideojuegoBO implements IVideojuegoBO {

    private final IVideojuegoDAO videojuegoDAO;

    public VideojuegoBO(IVideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    @Override
    public void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {

            if (dto == null) {
                throw new NegocioException("La información del videojuego no puede ser nula.");
            }

            validarDatosObligatorios(dto);

            List<VideojuegoDTO> juegosActivos = FachadaBO.buscarVideojuegosActivos();
            for (VideojuegoDTO juegoExistente : juegosActivos) {

                if (juegoExistente.getNombre().equalsIgnoreCase(dto.getNombre())) {
                    throw new NegocioException("Ya existe un videojuego activo con el nombre: " + dto.getNombre());
                }
            }
            this.videojuegoDAO.persistir(DTOMapeadores.toVideojuegoEntity(dto));

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            if (dto == null || dto.getIdVideojuego() == null) {
                throw new NegocioException("Se requiere un ID válido para actualizar.");
            }

            Videojuego juegoActual = this.videojuegoDAO.buscarPorId(dto.getIdVideojuego());
            if (juegoActual == null) {
                throw new NegocioException("El videojuego a actualizar no existe.");
            }

            validarDatosObligatorios(dto);

            if (!juegoActual.getNombre().equalsIgnoreCase(dto.getNombre())) {
                Videojuego posibleDuplicado = this.videojuegoDAO.buscarPorNombreExacto(dto.getNombre());
                if (posibleDuplicado != null) {
                    throw new NegocioException("El nombre '" + dto.getNombre() + "' ya está ocupado por otro videojuego.");
                }
            }

            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.actualizar(DTOMapeadores.toVideojuegoEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public void habilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {

            if (idVideojuego == null) {
                throw new NegocioException("ID requerido.");
            }

            if (this.videojuegoDAO.buscarPorId(idVideojuego) == null) {
                throw new NegocioException("El videojuego no existe.");
            }

            this.videojuegoDAO.habilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {

            if (idVideojuego == null) {
                throw new NegocioException("ID requerido.");
            }

            if (this.videojuegoDAO.buscarPorId(idVideojuego) == null) {
                throw new NegocioException("El videojuego no existe.");
            }

            this.videojuegoDAO.deshabilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarTodosLosVideojuegos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarTodosLosVideojuegos().stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los videojuegos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarVideojuegosActivos().stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuegos activos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarPorRangoDePrecio(Float min, Float max) throws NegocioException {
        try {
            if (min != null && min < 0) {
                throw new NegocioException("El precio mínimo no puede ser negativo.");
            }
            if (max != null && max < 0) {
                throw new NegocioException("El precio máximo no puede ser negativo.");
            }
            if (min != null && max != null && min > max) {
                throw new NegocioException("El rango de precios es inválido.");
            }

            return this.videojuegoDAO.buscarPorRangoDePrecio(min, max).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por rango de precio: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarPorCategoria(Long idCategoria) throws NegocioException {
        try {
            if (idCategoria == null) {
                throw new NegocioException("ID de categoría requerido.");
            }

            return this.videojuegoDAO.buscarPorCategoria(idCategoria).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por categoría: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {

            if (nombre == null) {
                nombre = "";
            }

            return this.videojuegoDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    @Override
    public VideojuegoDTO buscarPorNombreExacto(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("El nombre es requerido.");
            }

            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.buscarPorNombreExacto(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    @Override
    public VideojuegoDTO buscarPorId(Long idVideojuego) throws NegocioException {
        try {

            if (idVideojuego == null) {
                throw new NegocioException("ID requerido.");
            }

            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.buscarPorId(idVideojuego));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuego por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideojuegoDTO> buscarVideojuegosConFiltros(
            String nombre,
            Float precioMin,
            Float precioMax,
            Long idCategoria,
            String plataforma
    ) throws NegocioException {
        try {

            List<Videojuego> videojuegos = this.videojuegoDAO.buscarConFiltros(nombre, precioMin, precioMax, idCategoria, plataforma);

            return videojuegos.stream()
                    .map(Mapeadores::toVideojuegoDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al filtrar videojuegos: " + e.getMessage(), e);
        }
    }

    private void validarDatosObligatorios(VideojuegoDTO dto) throws NegocioException {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del videojuego es obligatorio.");
        }
        if (dto.getPrecio() == null || dto.getPrecio() < 0) {
            throw new NegocioException("El precio es obligatorio y no puede ser negativo.");
        }
        if (dto.getExistencias() == null || dto.getExistencias() < 0) {
            throw new NegocioException("Las existencias son obligatorias y no pueden ser negativas.");
        }
        if (dto.getPlataforma() == null || dto.getPlataforma().trim().isEmpty()) {
            throw new NegocioException("La plataforma es obligatoria.");
        }
        if (dto.getDesarrollador() == null || dto.getDesarrollador().trim().isEmpty()) {
            throw new NegocioException("El desarrollador es obligatorio.");
        }
        if (dto.getFechaLanzamiento() == null) {
            throw new NegocioException("La fecha de lanzamiento es obligatoria.");
        }
    }
}

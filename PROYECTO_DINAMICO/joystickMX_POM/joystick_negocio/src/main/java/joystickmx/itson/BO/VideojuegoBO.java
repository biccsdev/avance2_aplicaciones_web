package joystickmx.itson.BO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DAOS.CarritoDAO;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.interfaces.IVideojuegoDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.IVideojuegoBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class VideojuegoBO implements IVideojuegoBO {

    private final IVideojuegoDAO videojuegoDAO;

    public VideojuegoBO(IVideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    @Override
    public void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {

            List<VideojuegoDTO> juegosActivos = FactoryBO.buscarVideojuegosActivos();
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
            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.actualizar(DTOMapeadores.toVideojuegoEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public void habilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            this.videojuegoDAO.habilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar videojuego: " + e.getMessage(), e);
        }
    }

    @Override
    public void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
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
            return Mapeadores.toVideojuegoDTO(this.videojuegoDAO.buscarPorNombreExacto(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    @Override
    public VideojuegoDTO buscarPorId(Long idVideojuego) throws NegocioException {
        try {
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



}

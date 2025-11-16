package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.interfaces.ICategoriaDAO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author biccs
 */
public class CategoriaBO {

    private final ICategoriaDAO categoriaDAO;

    public CategoriaBO(ICategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public void crearCategoria(CategoriaDTO dto) throws NegocioException {
        try {
            this.categoriaDAO.crearCategoria(DTOMapeadores.toCategoriaEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear categoría: " + e.getMessage(), e);
        }
    }

    public CategoriaDTO actualizarCategoria(CategoriaDTO dto) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.actualizarCategoria(DTOMapeadores.toCategoriaEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar categoría: " + e.getMessage(), e);
        }
    }

    public void eliminarCategoria(Long idCategoria) throws NegocioException {
        try {
            this.categoriaDAO.eliminarCategoria(idCategoria);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar categoría: " + e.getMessage(), e);
        }
    }

    public CategoriaDTO buscarPorId(Long idCategoria) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.buscarPorId(idCategoria));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categoría por ID: " + e.getMessage(), e);
        }
    }

    public CategoriaDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.buscarPorNombre(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categoría por nombre: " + e.getMessage(), e);
        }
    }

    public List<CategoriaDTO> buscarTodas() throws NegocioException {
        try {
            return this.categoriaDAO.buscarTodas().stream()
                    .map(Mapeadores::toCategoriaDTO) 
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todas las categorías: " + e.getMessage(), e);
        }
    }

    public List<CategoriaDTO> buscarPorNombreParcial(String nombreParcial) throws NegocioException {
        try {
            return this.categoriaDAO.buscarPorNombreParcial(nombreParcial).stream()
                    .map(Mapeadores::toCategoriaDTO) 
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categorías por nombre parcial: " + e.getMessage(), e);
        }
    }
}


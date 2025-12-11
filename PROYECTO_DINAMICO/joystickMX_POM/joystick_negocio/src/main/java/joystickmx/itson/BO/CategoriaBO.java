package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.interfaces.ICategoriaDAO;
import joystickmx.negocio.exception.NegocioException;
import joystickmx.negocio.interfaces.ICategoriaBO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class CategoriaBO implements ICategoriaBO {

    private final ICategoriaDAO categoriaDAO;

    public CategoriaBO(ICategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }
    
    @Override
    public void crearCategoria(CategoriaDTO dto) throws NegocioException {
        try {
            this.categoriaDAO.crearCategoria(DTOMapeadores.toCategoriaEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear categoría: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CategoriaDTO actualizarCategoria(CategoriaDTO dto) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.actualizarCategoria(DTOMapeadores.toCategoriaEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar categoría: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarCategoria(Long idCategoria) throws NegocioException {
        try {
            this.categoriaDAO.eliminarCategoria(idCategoria);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar categoría: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CategoriaDTO buscarPorId(Long idCategoria) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.buscarPorId(idCategoria));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categoría por ID: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CategoriaDTO buscarPorNombre(String nombre) throws NegocioException {
        try {
            return Mapeadores.toCategoriaDTO(this.categoriaDAO.buscarPorNombre(nombre));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categoría por nombre: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CategoriaDTO> buscarTodas() throws NegocioException {
        try {
            return this.categoriaDAO.buscarTodas().stream()
                    .map(Mapeadores::toCategoriaDTO) 
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todas las categorías: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CategoriaDTO> buscarPorNombreParcial(String nombreParcial) throws NegocioException {
        try {
            return this.categoriaDAO.buscarPorNombreParcial(nombreParcial).stream()
                    .map(Mapeadores::toCategoriaDTO) 
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar categorías por nombre parcial: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CategoriaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return this.categoriaDAO.buscarPorVideojuego(idVideojuego).stream().
                    map(Mapeadores::toCategoriaDTO).
                    collect(Collectors.toList());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar categorías por videojuego: " + ex.getMessage(), ex);
        }
    }
}
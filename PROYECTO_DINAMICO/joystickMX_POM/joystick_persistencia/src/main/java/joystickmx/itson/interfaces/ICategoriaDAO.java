
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;

/**
 *
 * @author PC Gamer
 */
public interface ICategoriaDAO {

    void crearCategoria(Categoria categoria) throws PersistenciaException;

    Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException;

    void eliminarCategoria(Long idCategoria) throws PersistenciaException;

    Categoria buscarPorId(Long idCategoria) throws PersistenciaException;

    Categoria buscarPorNombre(String nombre) throws PersistenciaException;

    List<Categoria> buscarTodas() throws PersistenciaException;

    List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException;
}


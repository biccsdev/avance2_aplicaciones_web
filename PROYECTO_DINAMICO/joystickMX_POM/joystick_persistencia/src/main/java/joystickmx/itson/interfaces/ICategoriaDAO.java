package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface ICategoriaDAO {

    void crearCategoria(Categoria categoria) throws PersistenciaException;

    Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException;

    void eliminarCategoria(Long idCategoria) throws PersistenciaException;

    Categoria buscarPorId(Long idCategoria) throws PersistenciaException;

    Categoria buscarPorNombre(String nombre) throws PersistenciaException;

    List<Categoria> buscarTodas() throws PersistenciaException;

    List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException;
    
    List<Categoria> buscarPorVideojuego (Long idVideojuego) throws PersistenciaException;
}
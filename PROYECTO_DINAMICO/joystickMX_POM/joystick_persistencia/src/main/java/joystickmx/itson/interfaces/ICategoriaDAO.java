package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface ICategoriaDAO {

    public void crearCategoria(Categoria categoria) throws PersistenciaException;

    public Categoria actualizarCategoria(Categoria categoria) throws PersistenciaException;

    public void eliminarCategoria(Long idCategoria) throws PersistenciaException;

    public Categoria buscarPorId(Long idCategoria) throws PersistenciaException;

    public Categoria buscarPorNombre(String nombre) throws PersistenciaException;

    public List<Categoria> buscarTodas() throws PersistenciaException;

    public List<Categoria> buscarPorNombreParcial(String nombreParcial) throws PersistenciaException;
    
    public List<Categoria> buscarPorVideojuego (Long idVideojuego) throws PersistenciaException;
}
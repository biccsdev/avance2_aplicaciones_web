package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IUsuarioDAO {

    public void crearUsuario(Usuario usuario) throws PersistenciaException;

    public Usuario actualizar(Usuario usuario) throws PersistenciaException;

    public Usuario buscarPorId(Long idUsuario) throws PersistenciaException;

    public Usuario buscarPorEmail(String email) throws PersistenciaException;

    public void activarUsuario(String email) throws PersistenciaException;

    public void desactivarUsuario(String email) throws PersistenciaException;

    public void eliminarUsuario(String email) throws PersistenciaException;

    public Usuario modificarDireccion(String email, Direccion datosNuevos) throws PersistenciaException;
}
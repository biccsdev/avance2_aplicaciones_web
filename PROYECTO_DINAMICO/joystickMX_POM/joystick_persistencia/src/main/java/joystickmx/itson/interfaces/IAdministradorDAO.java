package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Administrador;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public interface IAdministradorDAO {

    void crearAdministrador(Administrador administrador) throws PersistenciaException;

    Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException;

    Administrador buscarPorId(Long idAdministrador) throws PersistenciaException;

    Administrador buscarPorEmail(String email) throws PersistenciaException;

    List<Administrador> buscarTodos() throws PersistenciaException;

    List<Administrador> buscarActivos() throws PersistenciaException;
}
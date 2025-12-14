package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Administrador;

/**
 *
 * @@author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public interface IAdministradorDAO {

    public void crearAdministrador(Administrador administrador) throws PersistenciaException;

    public Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException;

    public Administrador buscarPorId(Long idAdministrador) throws PersistenciaException;

    public Administrador buscarPorEmail(String email) throws PersistenciaException;

    public List<Administrador> buscarTodos() throws PersistenciaException;

    public List<Administrador> buscarActivos() throws PersistenciaException;
}
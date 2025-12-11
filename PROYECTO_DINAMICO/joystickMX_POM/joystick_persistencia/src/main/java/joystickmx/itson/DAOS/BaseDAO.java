package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import joystickmx.itson.conexion.Conexion;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class BaseDAO {

    /**
     * El EntityManager es final y protegido, proporcionado por la capa de negocio.
     */
    protected EntityManager em;

    /**
     * Constructor que recibe el EntityManager.
     */
    public BaseDAO() {}
    
    protected void iniciarConexion(){em = Conexion.crearConexion();}
}
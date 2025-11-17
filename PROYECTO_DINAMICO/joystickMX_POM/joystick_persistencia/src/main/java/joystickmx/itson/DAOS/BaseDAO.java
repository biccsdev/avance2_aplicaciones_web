
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;
import joystickmx.itson.conexion.Conexion;

/**
 *
 * @author PC Gamer
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
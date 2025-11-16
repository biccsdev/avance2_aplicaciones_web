
package joystickmx.itson.DAOS;

import jakarta.persistence.EntityManager;

/**
 *
 * @author PC Gamer
 */
public class BaseDAO {

    /**
     * El EntityManager es final y protegido, proporcionado por la capa de negocio.
     */
    protected final EntityManager em;

    /**
     * Constructor que recibe el EntityManager.
     * @param em El EntityManager activo para esta transacción.
     */
    public BaseDAO(EntityManager em) {
        this.em = em;
    }
}

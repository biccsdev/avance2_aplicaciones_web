package joystickmx.itson.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class Conexion {
     /**
     * Clase EntityManagerFactory para crear objetos
     * EntityManager cuando se requiera, para el manejo de las
     * operaciones CRUD con la base de datos.
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU"); // solo un factory en toda la aplicación
    
    /**
     * Crea una nueva conexión con la base de datos.
     * @return Objeto EntityManager.
     */
    public static EntityManager crearConexion() {
        return emf.createEntityManager(); // Se reutiliza el factory y se obtiene un nuevo EntityManager
    }
    
    /**
     * Cierra la conexión con la base de datos.
     */
    public static void cerrar() {
        if (emf.isOpen()) 
            emf.close();
    }
}
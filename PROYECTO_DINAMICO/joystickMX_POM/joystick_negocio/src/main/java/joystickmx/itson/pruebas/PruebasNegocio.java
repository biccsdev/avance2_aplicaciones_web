package joystickmx.itson.pruebas;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.DAOS.CategoriaDAO;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.itson.conexion.Conexion;

/**
 *
 * @author PC WHITE WOLF
 */
public class PruebasNegocio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            
            // ============================
            //   CREAR DIRECCIONES
            // ============================
            DireccionDTO dir1 = new DireccionDTO("Av. Siempre Viva", "742", "Springfield");
            DireccionDTO dir2 = new DireccionDTO("Calle Luna", "99", "CDMX");
            DireccionDTO dirAdmin = new DireccionDTO("Av. Tecnológica", "500", "Guadalajara");

            

            // ============================
            //   CREAR CLIENTE 1
            // ============================
            UsuarioRegistroDTO cliente1 = new UsuarioRegistroDTO();
            cliente1.setNombres("Sebastián");
            cliente1.setApellidoPaterno("Martínez");
            cliente1.setApellidoMaterno("Lopez");
            cliente1.setEmail("cliente1@example.com");
            cliente1.setContrasenia("12345");
            cliente1.setTelefono("5511223344");
            cliente1.setDireccion(dir1);

            FactoryBO.registrarCliente(cliente1);
            System.out.println("Cliente persistido: " + cliente1.getEmail());

            // ============================
            //   CREAR CLIENTE 2
            // ============================
            UsuarioRegistroDTO cliente2 = new UsuarioRegistroDTO();
            cliente2.setNombres("Andrea");
            cliente2.setApellidoPaterno("García");
            cliente2.setApellidoMaterno("Hernández");
            cliente2.setEmail("cliente2@example.com");
            cliente2.setContrasenia("abcd1234");
            cliente2.setTelefono("5588776655");
            cliente2.setDireccion(dir2);

            FactoryBO.registrarCliente(cliente2);
            System.out.println("Cliente persistido: " + cliente2.getEmail());

            // ============================
            //   CREAR ADMINISTRADOR
            // ============================
            UsuarioRegistroDTO admin = new UsuarioRegistroDTO();
            admin.setNombres("Juan Carlos");
            admin.setApellidoPaterno("Pérez");
            admin.setApellidoMaterno("Santos");
            admin.setEmail("admin1@example.com");
            admin.setContrasenia("adminpass");
            admin.setTelefono("5544332211");
            admin.setDireccion(dirAdmin);

            FactoryBO.registrarAdministrador(admin);
            System.out.println("Administrador persistido: " + admin.getEmail());

            System.out.println("\n¡ÉXITO! Se insertaron categoría, videojuego, clientes y administrador.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("¡ERROR! La transacción falló:");
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

}
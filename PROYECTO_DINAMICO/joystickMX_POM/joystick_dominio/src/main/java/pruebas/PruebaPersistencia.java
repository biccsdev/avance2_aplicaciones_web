package pruebas;

import jakarta.persistence.EntityManager;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
import joystickmx.itson.conexion.Conexion;
//import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Categoria;
//import joystickmx.itson.entidades.Cliente;
//import joystickmx.itson.entidades.Direccion;
//import joystickmx.itson.entidades.Videojuego;
//import joystickmx.itson.enums.EstadoUsuario;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class PruebaPersistencia {

    public static void main(String[] args) {
        
        // OBSOLETO: No recomendable ejecutar debido a que no se encripta la contraseña
        
        EntityManager em = Conexion.crearConexion();

        System.out.println("Iniciando prueba de persistencia...");

        try {
            em.getTransaction().begin();

            // ============================
            //   CREAR UNA CATEGORÍA
            // ============================
            Categoria categoriaAccion = new Categoria();
            categoriaAccion.setNombre("Acción");
            categoriaAccion.setDescripcion("Juegos de ritmo rápido y combate.");

            em.persist(categoriaAccion);
            System.out.println("Categoría persistida: " + categoriaAccion.getNombre());

            // ============================
            //   CREAR UN VIDEOJUEGO
            // ============================
//            Videojuego juego = new Videojuego();
//            juego.setNombre("God of War Ragnarok");
//            juego.setDescripcion("La épica saga nórdica de Kratos y Atreus.");
//            juego.setPrecio(1299.50f);
//            juego.setExistencias(100);
//            juego.setDesarrollador("Santa Monica Studio");
//            juego.setFechaLanzamiento(LocalDate.of(2022, 11, 9));
//            juego.setPlataforma("PlayStation 5");
//            juego.setHabilitado(true);
//            juego.setUrlImagen("wasawasa");
//
//            List<Categoria> categoriasParaJuego = new ArrayList<>();
//            categoriasParaJuego.add(categoriaAccion);
//            juego.setCategorias(categoriasParaJuego);
//
//            em.persist(juego);
//            System.out.println("Videojuego persistido: " + juego.getNombre());
//
//            // ============================
//            //   CREAR DIRECCIONES
//            // ============================
//            Direccion dir1 = new Direccion("Av. Siempre Viva", "742", "Springfield");
//            Direccion dir2 = new Direccion("Calle Luna", "99", "CDMX");
//            Direccion dirAdmin = new Direccion("Av. Tecnológica", "500", "Guadalajara");
//
//            em.persist(dir1);
//            em.persist(dir2);
//            em.persist(dirAdmin);
//
//            // ============================
//            //   CREAR CLIENTE 1
//            // ============================
//            Cliente cliente1 = new Cliente();
//            cliente1.setNombres("Sebastián");
//            cliente1.setApellidoPaterno("Martínez");
//            cliente1.setApellidoMaterno("Lopez");
//            cliente1.setEmail("cliente1@example.com");
//            cliente1.setContrasenia("12345");
//            cliente1.setTelefono("5511223344");
//            cliente1.setEstadoUsuario(EstadoUsuario.ACTIVO);
//            cliente1.setDireccion(dir1);
//
//            em.persist(cliente1);
//            System.out.println("Cliente persistido: " + cliente1.getEmail());
//
//            // ============================
//            //   CREAR CLIENTE 2
//            // ============================
//            Cliente cliente2 = new Cliente();
//            cliente2.setNombres("Andrea");
//            cliente2.setApellidoPaterno("García");
//            cliente2.setApellidoMaterno("Hernández");
//            cliente2.setEmail("cliente2@example.com");
//            cliente2.setContrasenia("abcd1234");
//            cliente2.setTelefono("5588776655");
//            cliente2.setEstadoUsuario(EstadoUsuario.ACTIVO);
//            cliente2.setDireccion(dir2);
//
//            em.persist(cliente2);
//            System.out.println("Cliente persistido: " + cliente2.getEmail());
//
//            // ============================
//            //   CREAR ADMINISTRADOR
//            // ============================
//            Administrador admin = new Administrador();
//            admin.setNombres("Juan Carlos");
//            admin.setApellidoPaterno("Pérez");
//            admin.setApellidoMaterno("Santos");
//            admin.setEmail("admin1@example.com");
//            admin.setContrasenia("adminpass");
//            admin.setTelefono("5544332211");
//            admin.setEstadoUsuario(EstadoUsuario.ACTIVO);
//            admin.setDireccion(dirAdmin);
//
//            em.persist(admin);
//            System.out.println("Administrador persistido: " + admin.getEmail());

            // ============================
            //   CONFIRMAR TRANSACCIÓN
            // ============================
            em.getTransaction().commit();

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
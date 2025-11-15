/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import joystickmx.itson.conexion.Conexion;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.entidades.Videojuego;

/**
 *
 * @author PC Gamer
 */
public class PruebaPersistencia {

    public static void main(String[] args) {
        EntityManager em = Conexion.crearConexion();

        System.out.println("Iniciando prueba de persistencia...");

        try {
            em.getTransaction().begin();

            Categoria categoriaAccion = new Categoria();
            categoriaAccion.setNombre("Acción");
            categoriaAccion.setDescripcion("Juegos de ritmo rápido y combate.");
            
            em.persist(categoriaAccion);
            System.out.println("Categoría persistida: " + categoriaAccion.getNombre());


            Videojuego juego = new Videojuego();
            juego.setNombre("God of War Ragnarok");
            juego.setDescripcion("La épica saga nórdica de Kratos y Atreus.");
            juego.setPrecio(1299.50f);
            juego.setExistencias(100);
            juego.setDesarrollador("Santa Monica Studio");
            juego.setFechaLanzamiento(LocalDate.of(2022, 11, 9));
            juego.setPlataforma("PlayStation 5");
            

            em.persist(juego);
            System.out.println("Videojuego persistido: " + juego.getNombre());

            em.getTransaction().commit();
            
            System.out.println("\n¡ÉXITO! Transacción completada.");
            System.out.println("Las tablas fueron generadas y los datos insertados.");

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
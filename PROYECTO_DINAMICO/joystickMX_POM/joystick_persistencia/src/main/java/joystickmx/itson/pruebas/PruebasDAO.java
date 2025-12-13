package joystickmx.itson.pruebas;

import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DAOS.ResenaDAO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.entidades.Resena;

/**
 * Pruebas de clases DAO.
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class PruebasDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        List<Categoria> categorias = new ArrayList<>();
        try {
            // Prueba el método buscarPorVideojuego de CategoriasDAO.
//            categorias = new CategoriaDAO().buscarPorVideojuego(1L);
//            if(categorias != null && !categorias.isEmpty()){
//                categorias.forEach(c -> {
//                    System.out.println("Categoría: " + c.getNombre());
//                });
//            }
//            List<Resena> resenas = new ResenaDAO().buscarPorVideojuego(3L);
//            if(resenas != null && !resenas.isEmpty()){
//                resenas.forEach(r -> {
//                    System.out.println(r.getTitulo());
//                    System.out.println(r.getComentario());
//                    System.out.println("");
//                });
//            }
            Resena resena = new ResenaDAO().buscarPorVideojuegoCliente(1L, 1L);
            System.out.println("Calificación de la reseña: " + resena.getCalificacion().toString());
            System.out.println("Titulo de la reseña: " + resena.getTitulo());
            System.out.println("Comentario de la reseña: " + resena.getComentario());

        } catch (PersistenciaException e) {
            System.out.println("Algo salió mal: " + e.getMessage());
        }
    }
    
}
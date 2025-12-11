package joystickmx.itson.pruebas;

import java.util.ArrayList;
import java.util.List;
import joystickmx.itson.DAOS.CategoriaDAO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Categoria;

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
        List<Categoria> categorias = new ArrayList<>();
        try {
            // Prueba el método buscarPorVideojuego de CategoriasDAO.
            categorias = new CategoriaDAO().buscarPorVideojuego(1L);
            if(categorias != null && !categorias.isEmpty()){
                categorias.forEach(c -> {
                    System.out.println("Categoría: " + c.getNombre());
                });
            }
        } catch (PersistenciaException e) {
            System.out.println("Algo salió mal: " + e.getMessage());
        }
    }
    
}
package joystickmx.itson.DTO;
/**
 * CategoriaDTO - Data Transfer Object para Categoria
 *
 * Se usa para transferir información sobre una categoría.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class CategoriaDTO {
    
    /**
     * Declaración de atributos de categoría
     */
    private String idCategoria;
    private String nombre;
    private String descripcion;

    public CategoriaDTO() {
    }

    
    
    
    /**
     * Método constructor para instanciar la clase CategoríaDTO
     * @param idCategoria Representa el identificador de la categoría
     * @param nombre Representa el nombre de la categoría
     * @param descripcion Representa la descripción de la categoría 
     */
    public CategoriaDTO(String idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    /**
     * Getters para cada atributo de la clase 
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
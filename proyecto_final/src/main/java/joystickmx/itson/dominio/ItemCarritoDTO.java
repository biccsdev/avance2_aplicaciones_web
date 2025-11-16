package joystickmx.itson.dominio;
/**
 * ItemCarritoDTO - Data Transfer Object para ItemCarrito
 *
 * Se usa para transferir información sobre un item del carrito.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class ItemCarritoDTO {
    
    /**
     * Declaración de atributos de item carrito
     */
    private String idItemCarrito;
    private int cantidad;

    /**
     * Método constructor para instanciar la clase ItemCarritoDTO
     * @param idItemCarrito Representa el identificador del item carrito
     * @param cantidad Representa la cantidad de un item del carrito
     */
    public ItemCarritoDTO(String idItemCarrito, int cantidad) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
    }

    /**
     * Getters para cada atributo de la clase 
     */
    public String getIdItemCarrito() {
        return idItemCarrito;
    }

    public int getCantidad() {
        return cantidad;
    }
    
}
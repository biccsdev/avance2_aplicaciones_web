package joystickmx.itson.dominio;
import java.time.LocalDate;
/**
 * CarritoDTO - Data Transfer Object para Carrito
 *
 * Se usa para transferir información sobre un carrito.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class CarritoDTO {
    
    /**
     * Declaración de atributos de carrito
     */
    private String idCarrito;
    private LocalDate fechaCreacion;

    /**
     * Método constructor para instanciar la clase CarritoDTO
     * @param idCarrito Representa el identificador del carrito
     * @param fechaCreacion Representa la decha de creación del carrito
     */
    public CarritoDTO(String idCarrito, LocalDate fechaCreacion) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Getters para cada atributo de la clase
     */
    public String getIdCarrito() {
        return idCarrito;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
  
}
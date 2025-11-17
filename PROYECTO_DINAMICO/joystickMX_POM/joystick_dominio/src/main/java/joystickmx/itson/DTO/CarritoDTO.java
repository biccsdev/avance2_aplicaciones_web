package joystickmx.itson.DTO;
import java.time.LocalDate;
import java.util.List;
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

    private Long idCarrito;
    private LocalDate fechaCreacion;
    private List<ItemCarritoDTO> items;

    public CarritoDTO() {}

    public CarritoDTO(Long idCarrito, LocalDate fechaCreacion, List<ItemCarritoDTO> items) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.items = items;
    }

    public CarritoDTO(LocalDate fechaCreacion, List<ItemCarritoDTO> items) {
        this.fechaCreacion = fechaCreacion;
        this.items = items;
    }
    
    public Long getIdCarrito() {return idCarrito;}

    public void setIdCarrito(Long idCarrito) {this.idCarrito = idCarrito;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public List<ItemCarritoDTO> getItems() {return items;}

    public void setItems(List<ItemCarritoDTO> items) {this.items = items;}
}
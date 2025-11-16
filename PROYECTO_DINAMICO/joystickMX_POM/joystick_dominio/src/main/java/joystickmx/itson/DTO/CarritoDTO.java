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

    private String idCarrito;
    private LocalDate fechaCreacion;
    private List<ItemCarritoDTO> items;
    private Float total; 

    public CarritoDTO() {}

    public CarritoDTO(String idCarrito, LocalDate fechaCreacion, List<ItemCarritoDTO> items, Float total) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.items = items;
        this.total = total;
    }

    public CarritoDTO(LocalDate fechaCreacion, List<ItemCarritoDTO> items, Float total) {
        this.fechaCreacion = fechaCreacion;
        this.items = items;
        this.total = total;
    }
    
    public String getIdCarrito() {return idCarrito;}

    public void setIdCarrito(String idCarrito) {this.idCarrito = idCarrito;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public List<ItemCarritoDTO> getItems() {return items;}
    
    public void setItems(List<ItemCarritoDTO> items) {
        this.items = items;
        items.stream().forEach(i -> {
            total += i.getSubtotal();
        });
    }
    
    public void agregarItem(ItemCarritoDTO item){
        if(!items.contains(item)){
            items.add(item);
            total += item.getSubtotal();
        }
    }
    
    public void eliminarItem(ItemCarritoDTO item){
        if(items.contains(item)){
            items.remove(item);
            total -= item.getCantidad();
        }
    }
    
    public Float getTotal() {return total;}

    public void setTotal(Float total) {this.total = total;}    
}
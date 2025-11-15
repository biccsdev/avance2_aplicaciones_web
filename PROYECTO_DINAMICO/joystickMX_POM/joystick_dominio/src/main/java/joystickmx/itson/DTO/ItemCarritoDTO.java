package joystickmx.itson.DTO;
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

    private String idItemCarrito;
    private Integer cantidad;
    private Float subtotal; 

    private String idVideojuego;
    private String nombreVideojuego;
    private Float precioUnitario; 

    public ItemCarritoDTO() {
    }

    public ItemCarritoDTO(String idItemCarrito, Integer cantidad, Float subtotal, String idVideojuego, String nombreVideojuego, Float precioUnitario) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.idVideojuego = idVideojuego;
        this.nombreVideojuego = nombreVideojuego;
        this.precioUnitario = precioUnitario;
    }

    public String getIdItemCarrito() {
        return idItemCarrito;
    }

    public void setIdItemCarrito(String idItemCarrito) {
        this.idItemCarrito = idItemCarrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public String getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(String idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


    
}
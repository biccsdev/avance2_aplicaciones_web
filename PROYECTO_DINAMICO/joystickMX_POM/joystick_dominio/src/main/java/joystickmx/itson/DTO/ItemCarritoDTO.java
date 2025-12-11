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

    private Long idItemCarrito;
    private Integer cantidad;
    private Long idVideojuego;
    private Long idCarrito;
    
    private VideojuegoDTO videojuego; // pruebas
    
    public ItemCarritoDTO() {}

    public ItemCarritoDTO(
            Long idItemCarrito, 
            Integer cantidad, 
            Long idVideojuego, 
            Long idCarrito
    ) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
        this.idVideojuego = idVideojuego;
        this.idCarrito = idCarrito;
    }

    public ItemCarritoDTO(Long idItemCarrito, Integer cantidad, Long idVideojuego, Long idCarrito, VideojuegoDTO videojuego) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
        this.idVideojuego = idVideojuego;
        this.idCarrito = idCarrito;
        this.videojuego = videojuego;
    }
    
    public VideojuegoDTO getVideojuego() {return videojuego;}

    public void setVideojuego(VideojuegoDTO videojuego) {this.videojuego = videojuego;}
    
    public Long getIdItemCarrito() {return idItemCarrito;}

    public void setIdItemCarrito(Long idItemCarrito) {this.idItemCarrito = idItemCarrito;}

    public Long getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(Long idVideojuego) {this.idVideojuego = idVideojuego;}

    public Long getIdCarrito() {return idCarrito;}

    public void setIdCarrito(Long idCarrito) {this.idCarrito = idCarrito;}
    
    public Integer getCantidad() {return cantidad;}

    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
}
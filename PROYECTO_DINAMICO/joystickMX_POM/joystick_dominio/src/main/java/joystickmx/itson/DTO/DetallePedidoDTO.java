package joystickmx.itson.DTO;
/**
 * DetallePedidoDTO - Data Transfer Object para DetallePedido
 *
 * Se usa para transferir información sobre los detalles de un pedido.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class DetallePedidoDTO {
    
    private Long idDetallePedido;
    private Integer cantidad;
    private Float precioUnitario;
    private Long idVideojuego;
    private Long idPedido;

    public DetallePedidoDTO() {}

    public DetallePedidoDTO(
            Long idDetallePedido, 
            Integer cantidad, 
            Float precioUnitario, 
            Long idVideojuego, 
            Long idPedido
    ) {
        this.idDetallePedido = idDetallePedido;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idVideojuego = idVideojuego;
        this.idPedido = idPedido;
    }

    public DetallePedidoDTO(
            Integer cantidad, 
            Float precioUnitario, 
            Long idVideojuego, 
            Long idPedido
    ) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idVideojuego = idVideojuego;
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {return cantidad;}

    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}

    public Float getPrecioUnitario() {return precioUnitario;}

    public void setPrecioUnitario(Float precioUnitario) {this.precioUnitario = precioUnitario;}

    public Long getIdDetallePedido() {return idDetallePedido;}

    public void setIdDetallePedido(Long idDetallePedido) {this.idDetallePedido = idDetallePedido;}

    public Long getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(Long idVideojuego) {this.idVideojuego = idVideojuego;}

    public Long getIdPedido() {return idPedido;}

    public void setIdPedido(Long idPedido) {this.idPedido = idPedido;}
}
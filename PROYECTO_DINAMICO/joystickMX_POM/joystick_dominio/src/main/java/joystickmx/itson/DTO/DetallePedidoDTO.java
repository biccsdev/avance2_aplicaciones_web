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
    
    private String idDetallePedido;
    private Integer cantidad;
    private Float precioUnitario;
    private Float importe; 
    private String idVideojuego;
    private String nombreVideojuego;
    private String idPedido;

    public DetallePedidoDTO() {}

    public DetallePedidoDTO(
            String idDetallePedido, 
            Integer cantidad, 
            Float precioUnitario, 
            Float importe, 
            String idVideojuego, 
            String nombreVideojuego, 
            String idPedido
    ) {
        this.idDetallePedido = idDetallePedido;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.idVideojuego = idVideojuego;
        this.nombreVideojuego = nombreVideojuego;
        this.idPedido = idPedido;
    }

    public DetallePedidoDTO(
            Integer cantidad, 
            Float precioUnitario, 
            Float importe, 
            String idVideojuego, 
            String nombreVideojuego, 
            String idPedido
    ) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.idVideojuego = idVideojuego;
        this.nombreVideojuego = nombreVideojuego;
        this.idPedido = idPedido;
    }

    public String getIdDetallePedido() {return idDetallePedido;}

    public void setNombreVideojuego(String nombreVideojuego) {this.nombreVideojuego = nombreVideojuego;}

    public Integer getCantidad() {return cantidad;}

    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}

    public Float getPrecioUnitario() {return precioUnitario;}

    public void setPrecioUnitario(Float precioUnitario) {this.precioUnitario = precioUnitario;}

    public Float getImporte() {return importe;}

    public void setImporte(Float importe) {this.importe = importe;}
    
    public String getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(String idVideojuego) {this.idVideojuego = idVideojuego;}

    public String getNombreVideojuego() {return nombreVideojuego;}

    public String getIdPedido() {return idPedido;}

    public void setIdPedido(String idPedido) {this.idPedido = idPedido;}
}
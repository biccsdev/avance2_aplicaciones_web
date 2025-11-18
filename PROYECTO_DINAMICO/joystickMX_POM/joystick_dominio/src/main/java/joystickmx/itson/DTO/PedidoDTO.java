package joystickmx.itson.DTO;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * PedidoDTO - Data Transfer Object para Pedido
 *
 * Se usa para transferir información de pedidos.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class PedidoDTO {
    
    private Long idPedido;
    private String estadoPedido; // String del Enum
    private Float totalPagado;
    private LocalDateTime fechaPedido;
    private DireccionDTO direccionEnvio;
    private List<DetallePedidoDTO> detalles;
    private PagoDTO pago;
    private Long idCliente;
    private UsuarioDTO cliente;

    public PedidoDTO() {}

    public PedidoDTO(
            Long idPedido, 
            String estadoPedido, 
            Float totalPagado, 
            LocalDateTime fechaPedido, 
            DireccionDTO direccionEnvio, 
            List<DetallePedidoDTO> detalles, 
            PagoDTO pago,
            Long idCliente
    ) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.pago = pago;
        this.idCliente = idCliente;
    }

    public PedidoDTO(Long idPedido, String estadoPedido, Float totalPagado, LocalDateTime fechaPedido, DireccionDTO direccionEnvio, List<DetallePedidoDTO> detalles, PagoDTO pago, UsuarioDTO cliente) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.pago = pago;
        this.cliente = cliente;
    }
    
    

    public PedidoDTO(
            String estadoPedido, 
            Float totalPagado, 
            LocalDateTime fechaPedido, 
            DireccionDTO direccionEnvio, 
            List<DetallePedidoDTO> detalles, 
            PagoDTO pago,
            Long idCliente
    ) {
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.pago = pago;
        this.idCliente = idCliente;
    }

    public UsuarioDTO getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioDTO cliente) {
        this.cliente = cliente;
    }
    
    
    
    public Long getIdPedido() {return idPedido;}

    public void setIdPedido(Long idPedido) {this.idPedido = idPedido;}

    public String getEstadoPedido() {return estadoPedido;}

    public void setEstadoPedido(String estadoPedido) {this.estadoPedido = estadoPedido;}

    public Float getTotalPagado() {return totalPagado;}
    
    public Date getFechaPedidoAsDate() {
        if (this.fechaPedido == null) {
            return null;
        }
        return java.sql.Timestamp.valueOf(this.fechaPedido);
    }

    public void setTotalPagado(Float totalPagado) {this.totalPagado = totalPagado;}

    public LocalDateTime getFechaPedido() {return fechaPedido;}

    public void setFechaPedido(LocalDateTime fechaPedido) {this.fechaPedido = fechaPedido;}

    public DireccionDTO getDireccionEnvio() {return direccionEnvio;}

    public void setDireccionEnvio(DireccionDTO direccionEnvio) {this.direccionEnvio = direccionEnvio;}

    public List<DetallePedidoDTO> getDetalles() {return detalles;}

    public void setDetalles(List<DetallePedidoDTO> detalles) {this.detalles = detalles;}

    public PagoDTO getPago() {return pago;}

    public void setPago(PagoDTO pago) {this.pago = pago;}

    public Long getIdCliente() {return idCliente;}

    public void setIdCliente(Long idCliente) {this.idCliente = idCliente;}
}
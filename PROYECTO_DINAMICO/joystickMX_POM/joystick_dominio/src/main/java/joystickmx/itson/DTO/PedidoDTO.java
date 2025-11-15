package joystickmx.itson.DTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    
    private String idPedido;
    private String estadoPedido; // String del Enum
    private Float totalPagado;
    private LocalDateTime fechaPedido;
    private DireccionDTO direccionEnvio;
    private List<DetallePedidoDTO> detalles;
    private PagoDTO pago;

    public PedidoDTO() {
    }

    public PedidoDTO(String idPedido, String estadoPedido, Float totalPagado, LocalDateTime fechaPedido, DireccionDTO direccionEnvio, List<DetallePedidoDTO> detalles, PagoDTO pago) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.pago = pago;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Float getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(Float totalPagado) {
        this.totalPagado = totalPagado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public DireccionDTO getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(DireccionDTO direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    public PagoDTO getPago() {
        return pago;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }
    
    
    
}
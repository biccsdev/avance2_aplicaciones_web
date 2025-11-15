package joystickmx.itson.dominio;
import java.time.LocalDate;
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
    
    /**
     * Declaración de atributos de pedido
     */
    private String idPedido;
    private String estadoPedido;
    private float totalPagado;
    private LocalDate fechaPedido;
    private String direccionEnvio;
    private String calle;
    private int numero;
    private String colonia;
    
    
    
    private List<DetallePedidoDTO> detalles;

    /**
     * Método constructor para instanciar la clase PedidoDTO
     * @param idPedido Representa el identificador del pedido
     * @param estadoPedido Representa el estado del pedido
     * @param totalPagado Representa el total pagado por el pedido
     * @param fechaPedido Representa la fecha del pedido
     * @param direccionEnvio Representa la dirección completa de envío
     * @param calle Representa la calle de la dirección de envío que indicó el usuario
     * @param numero Representa el número de la dirección de envío que indicó el usuario
     * @param colonia Representa la colonia de la dirección de envío que indicó el usuario
     */
    public PedidoDTO(String idPedido, String estadoPedido, float totalPagado, LocalDate fechaPedido, String direccionEnvio, String calle, int numero, String colonia) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public PedidoDTO(String idPedido, String estadoPedido, float totalPagado, LocalDate fechaPedido, String direccionEnvio, String calle, int numero, String colonia, List<DetallePedidoDTO> detalles) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.fechaPedido = fechaPedido;
        this.direccionEnvio = direccionEnvio;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.detalles = detalles;
    }

    
    
    
    
    
    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }
    
    
    

    /**
     * Getters para cada atributo de la clase 
     * @return 
     */
    public String getIdPedido(){
        return idPedido;
    }
    
    public String getEstadoPedido() {
        return estadoPedido;
    }

    public float getTotalPagado() {
        return totalPagado;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }
    
}
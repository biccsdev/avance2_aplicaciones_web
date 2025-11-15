package joystickmx.itson.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import joystickmx.itson.entidades.DireccionEnvio;
import joystickmx.itson.enums.EstadoPedido;

/**
 *
 * @author sonic
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDate fechaPedido;

    private EstadoPedido estadoPedido;

    private Float totalPagado;

    @Embedded
    private DireccionEnvio direccionEnvio;
    
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pago pago;

    public Pedido() {
    }

    public Pedido(Long idPedido, LocalDate fechaPedido, EstadoPedido estadoPedido, Float totalPagado, DireccionEnvio direccionEnvio, Cliente cliente, Pago pago) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.direccionEnvio = direccionEnvio;
        this.cliente = cliente;
        this.pago = pago;
    }

    public Pedido(Long idPedido, LocalDate fechaPedido, EstadoPedido estadoPedido, Float totalPagado, DireccionEnvio direccionEnvio, List<DetallePedido> detalles, Cliente cliente, Pago pago) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.cliente = cliente;
        this.pago = pago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
    
    
    
    
    

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Float getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(Float totalPagado) {
        this.totalPagado = totalPagado;
    }

    public DireccionEnvio getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(DireccionEnvio direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

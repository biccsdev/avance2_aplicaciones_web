package joystickmx.itson.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.MetodoPago;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Pagos")
public class Pago implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;
    
    @Column(nullable = false)
    @PositiveOrZero(message = "No se permiten montos negativos.")
    private Float monto;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    private EstadoPago estadoPago;
    
    @Column(name = "fecha_hora_pago", nullable = false)
    @FutureOrPresent(message = "La fecha del pago no puede ser menor que la actual.")
    private LocalDateTime fechaPago;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false, unique = true)
    private Pedido pedido;

    public Pago() {}

    public Pago(
            Long idPago, 
            Float monto, 
            MetodoPago metodoPago, 
            EstadoPago estadoPago, 
            LocalDateTime fechaPago, 
            Pedido pedido
    ) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
        this.pedido = pedido;
    }
    
    public Long getIdPago() {return idPago;}

    public void setIdPago(Long idPago) {this.idPago = idPago;}

    public Float getMonto() {return monto;}

    public void setMonto(Float monto) {this.monto = monto;}

    public MetodoPago getMetodoPago() {return metodoPago;}

    public void setMetodoPago(MetodoPago metodoPago) {this.metodoPago = metodoPago;}

    public EstadoPago getEstadoPago() {return estadoPago;}

    public void setEstadoPago(EstadoPago estadoPago) {this.estadoPago = estadoPago;}

    public LocalDateTime getFechaPago() {return fechaPago;}

    public void setFechaPago(LocalDateTime fechaPago) {this.fechaPago = fechaPago;}

    public Pedido getPedido() {return pedido;}

    public void setPedido(Pedido pedido) {this.pedido = pedido;}
}
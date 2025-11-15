package joystickmx.itson.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import joystickmx.itson.enums.EstadoPedido;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;
    
    @Column(name = "fecha_pedido", nullable = false)
    @FutureOrPresent(message = "La fecha del pedido no puede ser menor que la actual.")
    private LocalDateTime fechaPedido;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;
    
    @Column(name = "total_pagado", nullable = false)
    @PositiveOrZero(message = "No se aceptan valores negativos.")
    private Float totalPagado;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "direccion_envio", nullable = false)
    private DireccionEnvio direccionEnvio;
        
    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<DetallePedido> detalles;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Pago pago;

    public Pedido() {}

    public Pedido(
            Long idPedido, 
            LocalDateTime fechaPedido, 
            EstadoPedido estadoPedido, 
            Float totalPagado, 
            DireccionEnvio direccionEnvio, 
            Cliente cliente, 
            Pago pago
    ) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.direccionEnvio = direccionEnvio;
        this.cliente = cliente;
        this.pago = pago;
    }

    public Pedido(
            Long idPedido, 
            LocalDateTime fechaPedido, 
            EstadoPedido estadoPedido, 
            Float totalPagado, 
            DireccionEnvio direccionEnvio, 
            List<DetallePedido> detalles, 
            Cliente cliente, 
            Pago pago
    ) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.totalPagado = totalPagado;
        this.direccionEnvio = direccionEnvio;
        this.detalles = detalles;
        this.cliente = cliente;
        this.pago = pago;
    }

    public List<DetallePedido> getDetalles() {return detalles;}

    public void setDetalles(List<DetallePedido> detalles) {this.detalles = detalles;}
    
    public Pago getPago() {return pago;}

    public void setPago(Pago pago) {this.pago = pago;}

    public Long getIdPedido() {return idPedido;}

    public void setIdPedido(Long idPedido) {this.idPedido = idPedido;}

    public LocalDateTime getFechaPedido() {return fechaPedido;}

    public void setFechaPedido(LocalDateTime fechaPedido) {this.fechaPedido = fechaPedido;}

    public EstadoPedido getEstadoPedido() {return estadoPedido;}

    public void setEstadoPedido(EstadoPedido estadoPedido) {this.estadoPedido = estadoPedido;}

    public Float getTotalPagado() {return totalPagado;}

    public void setTotalPagado(Float totalPagado) {this.totalPagado = totalPagado;}

    public DireccionEnvio getDireccionEnvio() {return direccionEnvio;}

    public void setDireccionEnvio(DireccionEnvio direccionEnvio) {this.direccionEnvio = direccionEnvio;}

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

}
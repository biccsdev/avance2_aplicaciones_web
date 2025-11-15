package joystickmx.itson.entidades;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "DetallesPedido")
public class DetallePedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Long idDetallePedido;

    @Column(nullable = false)
    @PositiveOrZero(message = "No se permiten cantidades negativas.")
    @Max(value = Integer.MAX_VALUE, message = "Límite de cantidad superado.")
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    @PositiveOrZero(message = "No se permiten precios negativos.")
    private Float precioUnitario;

    @Column(insertable = false, updatable = false, nullable = false)
    @PositiveOrZero(message = "No se permiten importes negativos.")
    private Float importe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_videojuego", nullable = false)
    private Videojuego videojuego;

    public DetallePedido() {}
    
    public DetallePedido(
            Pedido pedido, 
            Videojuego videojuego, 
            Integer cantidad, 
            Float precioUnitario) 
    {
        this.pedido = pedido;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    public Long getIdDetallePedido() {return idDetallePedido;}

    public void setIdDetallePedido(Long idDetallePedido) {this.idDetallePedido = idDetallePedido;}

    public Integer getCantidad() {return cantidad;}

    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}

    public Float getPrecioUnitario() {return precioUnitario;}

    public void setPrecioUnitario(Float precioUnitario) {this.precioUnitario = precioUnitario;}

    public Float getImporte() {
        if (importe == null && cantidad != null && precioUnitario != null) {
            return precioUnitario * cantidad;
        }
        return importe;
    }

    public Pedido getPedido() {return pedido;}

    public void setPedido(Pedido pedido) {this.pedido = pedido;}

    public Videojuego getVideojuego() {return videojuego;}

    public void setVideojuego(Videojuego videojuego) {this.videojuego = videojuego;}
    
    @Override
    public String toString() {return "DetallePedido[ id=" + idDetallePedido + " ]";}   
}
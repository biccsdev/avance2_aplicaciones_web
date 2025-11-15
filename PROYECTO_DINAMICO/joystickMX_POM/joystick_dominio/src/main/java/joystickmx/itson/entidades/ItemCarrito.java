package joystickmx.itson.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "items_carrito", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_carrito", "id_videojuego"})
})
public class ItemCarrito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_carrito")
    private Long idItemCarrito;

    @Column(nullable = false)
    @PositiveOrZero(message = "No se permiten cantidades negativas.")
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_videojuego", nullable = false)
    private Videojuego videojuego;

    public ItemCarrito() {}

    public ItemCarrito(Carrito carrito, Videojuego videojuego, Integer cantidad) {
        this.carrito = carrito;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }


    public Long getIdItemCarrito() {return idItemCarrito;}

    public void setIdItemCarrito(Long idItemCarrito) {this.idItemCarrito = idItemCarrito;}

    public Integer getCantidad() {return cantidad;}

    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}

    public Carrito getCarrito() {return carrito;}

    public void setCarrito(Carrito carrito) {this.carrito = carrito;}

    public Videojuego getVideojuego() {return videojuego;}

    public void setVideojuego(Videojuego videojuego) {this.videojuego = videojuego;}
    
    @Transient
    public Float getSubtotal() {
        if (videojuego != null && cantidad != null) {
            return videojuego.getPrecio() * cantidad;
        }
        return 0.0f;
    }
    
    @Override
    public String toString() {
        return "ItemCarrito[ id=" + idItemCarrito + ", cantidad=" + cantidad + " ]";
    }
}
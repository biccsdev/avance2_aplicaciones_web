/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.persistencia;

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
import java.io.Serializable;

/**
 *
 * @author sonic
 */
@Entity
@Table(name = "ItemsCarrito", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idCarrito", "idVideojuego"})
})
public class ItemCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCarrito;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCarrito", nullable = false)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVideojuego", nullable = false)
    private Videojuego videojuego;

    public ItemCarrito() {
    }

    public ItemCarrito(Carrito carrito, Videojuego videojuego, Integer cantidad) {
        this.carrito = carrito;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }


    public Long getIdItemCarrito() {
        return idItemCarrito;
    }

    public void setIdItemCarrito(Long idItemCarrito) {
        this.idItemCarrito = idItemCarrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }
    
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

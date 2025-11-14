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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author sonic
 */
@Entity
@Table(name = "Resenas")
public class Resena implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;

    @Column(nullable = false)
    @Min(value = 0, message = "La calificación no puede ser menor a 0")
    @Max(value = 5, message = "La calificación no puede ser mayor a 5")
    private Float calificacion;

    @Column(length = 300)
    private String comentario;

    @Column(nullable = false)
    private LocalDate fechaResena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVideojuego", nullable = false)
    private Videojuego videojuego;

    public Resena() {
        this.fechaResena = LocalDate.now();
    }

    public Resena(Cliente cliente, Videojuego videojuego, Float calificacion, String comentario) {
        this.cliente = cliente;
        this.videojuego = videojuego;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResena = LocalDate.now();
    }

    // --- Getters y Setters ---

    public Long getIdResena() {
        return idResena;
    }

    public void setIdResena(Long idResena) {
        this.idResena = idResena;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDate fechaResena) {
        this.fechaResena = fechaResena;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
    }
    
    @Override
    public String toString() {
        return "Resena[ id=" + idResena + ", stars=" + calificacion + " ]";
    }
}

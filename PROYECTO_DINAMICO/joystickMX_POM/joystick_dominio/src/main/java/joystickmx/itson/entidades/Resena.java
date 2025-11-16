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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Resenas")
public class Resena implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long idResena;

    @Column(nullable = false)
    @Min(value = 0, message = "La calificación no puede ser menor a 0")
    @Max(value = 5, message = "La calificación no puede ser mayor a 5")
    @Digits(integer = 2, fraction = 1, message = "La calificación debe ser un número con máximo 1 decimal y 2 enteros.")
    private Float calificacion;

    @Column(length = 500, nullable = true)
    private String comentario;

    @Column(name = "fecha_resena", nullable = false)
    @FutureOrPresent(message = "La fecha de la reseña no puede ser menor a la actual.")
    private LocalDate fechaResena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_videojuego", nullable = false)
    private Videojuego videojuego;

    public Resena() {this.fechaResena = LocalDate.now();}

    public Resena(Cliente cliente, Videojuego videojuego, Float calificacion, String comentario) {
        this.cliente = cliente;
        this.videojuego = videojuego;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResena = LocalDate.now();
    }
    
    public Long getIdResena() {return idResena;}

    public void setIdResena(Long idResena) {this.idResena = idResena;}

    public Float getCalificacion() {return calificacion;}

    public void setCalificacion(Float calificacion) {this.calificacion = calificacion;}

    public String getComentario() {return comentario;}

    public void setComentario(String comentario) {this.comentario = comentario;}

    public LocalDate getFechaResena() {return fechaResena;}

    public void setFechaResena(LocalDate fechaResena) {this.fechaResena = fechaResena;}

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public Videojuego getVideojuego() {return videojuego;}

    public void setVideojuego(Videojuego videojuego) {this.videojuego = videojuego;}
    
    @Override
    public String toString() {return "Resena[ id=" + idResena + ", stars=" + calificacion + " ]";}
}
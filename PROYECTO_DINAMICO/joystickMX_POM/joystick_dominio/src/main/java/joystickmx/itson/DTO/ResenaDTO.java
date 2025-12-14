package joystickmx.itson.DTO;
import java.time.LocalDate;
/**
 * ResenaDTO - Data Transfer Object para Resena
 *
 * Se usa para transferir información sobre una reseña.
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class ResenaDTO {
    
    private Long idResena;
    private float calificacion;
    private String titulo;
    private String comentario;
    private LocalDate fechaResena;
    // Pienso que a lo mejor se debería referenciar a las clases tal cual, pero no sé...
    private Long idCliente;
    private Long idVideojuego; // Pienso que la reseña también debería referenciar al videojuego

    public ResenaDTO() {}

    public ResenaDTO(
            Long idResena, 
            float calificacion,
            String titulo,
            String comentario, 
            LocalDate fechaResena, 
            Long idCliente, 
            Long idVideojuego
    ) {
        this.idResena = idResena;
        this.calificacion = calificacion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
        this.idCliente = idCliente;
        this.idVideojuego = idVideojuego;
    }

    public ResenaDTO(
            float calificacion, 
            String titulo,
            String comentario, 
            LocalDate fechaResena, 
            Long idCliente, 
            String nombreCliente
    ) {
        this.calificacion = calificacion;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
        this.idCliente = idCliente;
    }
    
    public float getCalificacion() {return calificacion;}

    public void setCalificacion(float calificacion) {this.calificacion = calificacion;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}
    
    public String getComentario() {return comentario;}

    public void setComentario(String comentario) {this.comentario = comentario;}

    public LocalDate getFechaResena() {return fechaResena;}

    public void setFechaResena(LocalDate fechaResena) {this.fechaResena = fechaResena;}
    
    public Long getIdResena() {return idResena;}

    public void setIdResena(Long idResena) {this.idResena = idResena;}

    public Long getIdCliente() {return idCliente;}

    public void setIdCliente(Long idCliente) {this.idCliente = idCliente;}

    public Long getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(Long idVideojuego) {this.idVideojuego = idVideojuego;}
}
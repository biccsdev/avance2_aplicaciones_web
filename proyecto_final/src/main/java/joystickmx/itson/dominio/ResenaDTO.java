package joystickmx.itson.dominio;
import java.time.LocalDate;
/**
 * ResenaDTO - Data Transfer Object para Resena
 *
 * Se usa para transferir información sobre una reseña.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class ResenaDTO {
    
    /**
     * Declaración de atributos de reseña
     */
    private String idResena;
    private float calificacion;
    private String comentario;
    private LocalDate fechaResena;

    /**
     * Método constructor para instanciar la clase ResenaDTO
     * @param idResena Representa el identificador de la reseña
     * @param calificacion Representa la calificación de la reseña
     * @param comentario Representa el comentario de la reseña
     * @param fechaResena Representa la fecha de publicación de la reseña
     */
    public ResenaDTO(String idResena, float calificacion, String comentario, LocalDate fechaResena) {
        this.idResena = idResena;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
    }

    /**
     * Getters para csada atributo de la clase 
     */
    public String getIdResena() {
        return idResena;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaResena() {
        return fechaResena;
    }

}
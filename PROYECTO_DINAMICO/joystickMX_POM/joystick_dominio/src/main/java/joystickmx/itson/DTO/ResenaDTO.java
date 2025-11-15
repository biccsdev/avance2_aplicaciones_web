package joystickmx.itson.DTO;
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
    
    private String idResena;
    private float calificacion;
    private String comentario;
    private LocalDate fechaResena;
    
    private String idCliente;
    private String nombreCliente;

    public ResenaDTO() {
    }

    public ResenaDTO(String idResena, float calificacion, String comentario, LocalDate fechaResena, String idCliente, String nombreCliente) {
        this.idResena = idResena;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }

    public String getIdResena() {
        return idResena;
    }

    public void setIdResena(String idResena) {
        this.idResena = idResena;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    
    
    
    
    
}
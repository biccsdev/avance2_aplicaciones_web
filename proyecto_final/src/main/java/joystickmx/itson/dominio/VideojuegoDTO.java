package joystickmx.itson.dominio;
import java.time.LocalDate;
/**
 * VideojuegoDTO - Data Transfer Object para Videojuego
 *
 * Se usa para transferir información sobre un videojuego.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class VideojuegoDTO {
    
    /**
     * Declaración de atributos de videojuego
     */
    private String idVideojuego;
    private String plataforma;
    private LocalDate fechaLanzamiento;

    /**
     * Método constructor para instanciar la clase VideojuegoDTO
     * @param idVideojuego Representa el identificador del videojuego
     * @param plataforma Representa la plataforma a la que pertenece el videojuego
     * @param fechaLanzamiento Representa la fecha de lanzamiento del videojuego
     */
    public VideojuegoDTO(String idVideojuego, String plataforma, LocalDate fechaLanzamiento) {
        this.idVideojuego = idVideojuego;
        this.plataforma = plataforma;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Getters para cada atributo de la clase
     */
    public String getIdVideojuego() {
        return idVideojuego;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

}
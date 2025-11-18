package joystickmx.itson.DTO;

/**
 *
 * @author PC WHITE WOLF
 */
public class VideojuegoResenaDTO {
    private ResenaDTO resena;
    private String nombreVideojuego;
    private String urlImagen;
    private String nombreJugador;

    public VideojuegoResenaDTO() {
    }

    public VideojuegoResenaDTO(ResenaDTO resena, String nombreVideojuego, String urlImagen, String nombreJugador) {
        this.resena = resena;
        this.nombreVideojuego = nombreVideojuego;
        this.urlImagen = urlImagen;
        this.nombreJugador = nombreJugador;
    }

    public ResenaDTO getResena() {
        return resena;
    }

    public void setResena(ResenaDTO resena) {
        this.resena = resena;
    }

    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
    
}
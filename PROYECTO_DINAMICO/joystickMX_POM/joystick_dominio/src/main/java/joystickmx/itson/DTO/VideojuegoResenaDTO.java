package joystickmx.itson.DTO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class VideojuegoResenaDTO {
    private ResenaDTO resena;
    private String nombreVideojuego;
    private String urlImagen;
    private String nombreJugador;

    public VideojuegoResenaDTO() {}

    public VideojuegoResenaDTO(
            ResenaDTO resena, 
            String nombreVideojuego, 
            String urlImagen, 
            String nombreJugador
    ) {
        this.resena = resena;
        this.nombreVideojuego = nombreVideojuego;
        this.urlImagen = urlImagen;
        this.nombreJugador = nombreJugador;
    }

    public ResenaDTO getResena() {return resena;}

    public void setResena(ResenaDTO resena) {this.resena = resena;}

    public String getNombreVideojuego() {return nombreVideojuego;}

    public void setNombreVideojuego(String nombreVideojuego) {this.nombreVideojuego = nombreVideojuego;}

    public String getUrlImagen() {return urlImagen;}

    public void setUrlImagen(String urlImagen) {this.urlImagen = urlImagen;}

    public String getNombreJugador() {return nombreJugador;}

    public void setNombreJugador(String nombreJugador) {this.nombreJugador = nombreJugador;}
}
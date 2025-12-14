package joystickmx.itson.DTO;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
/**
 * VideojuegoDTO - Data Transfer Object para Videojuego
 *
 * Se usa para transferir información sobre un videojuego.
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class VideojuegoDTO {
    
    private Long idVideojuego;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer existencias;
    private boolean habilitado; 
    private String desarrollador;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;
    private String plataforma;
    private String urlImagen; 
    private List<CategoriaDTO> categorias;
    private List<ResenaDTO> resenas;

    public VideojuegoDTO() {}

    public VideojuegoDTO(
            Long idVideojuego, 
            String nombre, 
            String descripcion, 
            Float precio, 
            Integer existencias, 
            boolean habilitado, 
            String desarrollador, 
            LocalDate fechaLanzamiento, 
            String plataforma, 
            String urlImagen, 
            List<CategoriaDTO> categorias,
            List<ResenaDTO> resenas
    ) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.habilitado = habilitado;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
        this.urlImagen = urlImagen;
        this.categorias = categorias;
        this.resenas = resenas;
    }

    public VideojuegoDTO(
            String nombre, 
            String descripcion, 
            Float precio, 
            Integer existencias, 
            boolean habilitado, 
            String desarrollador, 
            LocalDate fechaLanzamiento, 
            String plataforma, 
            String urlImagen, 
            List<CategoriaDTO> categorias,
            List<ResenaDTO> resenas
    ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.habilitado = habilitado;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
        this.urlImagen = urlImagen;
        this.categorias = categorias;
        this.resenas = resenas;
    }
    
    /**
     * Getters para cada atributo de la clase
     * @return 
     */
    public Long getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(Long idVideojuego) {this.idVideojuego = idVideojuego;}

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {this.fechaLanzamiento = fechaLanzamiento;}

    public void setPlataforma(String plataforma) {this.plataforma = plataforma;}
    
    public String getPlataforma() {return plataforma;}

    public LocalDate getFechaLanzamiento() {return fechaLanzamiento;}
    
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Float getPrecio() {return precio;}

    public void setPrecio(Float precio) {this.precio = precio;}

    public Integer getExistencias() {return existencias;}

    public void setExistencias(Integer existencias) {this.existencias = existencias;}

    public boolean isHabilitado() {return habilitado;}

    public void setHabilitado(boolean habilitado) {this.habilitado = habilitado;}

    public String getDesarrollador() {return desarrollador;}

    public void setDesarrollador(String desarrollador) {this.desarrollador = desarrollador;}

    public String getUrlImagen() {return urlImagen;}

    public void setUrlImagen(String urlImagen) {this.urlImagen = urlImagen;}

    public List<CategoriaDTO> getCategorias() {return categorias;}

    public void setCategorias(List<CategoriaDTO> categorias) {this.categorias = categorias;}

    public List<ResenaDTO> getResenas() {return resenas;}

    public void setResenas(List<ResenaDTO> resenas) {this.resenas = resenas;}
}
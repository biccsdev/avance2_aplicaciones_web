package joystickmx.itson.DTO;
import java.time.LocalDate;
import java.util.List;
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
    
    private String idVideojuego;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer existencias;
    private boolean habilitado; 
    private String desarrollador;
    private LocalDate fechaLanzamiento;
    private String plataforma;
    private byte[] imagen; 
    private List<CategoriaDTO> categorias; 

    public VideojuegoDTO() {
    }

    public VideojuegoDTO(String idVideojuego, String nombre, String descripcion, Float precio, Integer existencias, boolean habilitado, String desarrollador, LocalDate fechaLanzamiento, String plataforma, byte[] imagen, List<CategoriaDTO> categorias) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.habilitado = habilitado;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
        this.imagen = imagen;
        this.categorias = categorias;
    }

    public VideojuegoDTO(String idVideojuego, String nombre, String descripcion, Float precio, Integer existencias, boolean habilitado, String desarrollador, LocalDate fechaLanzamiento, String plataforma) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.habilitado = habilitado;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
    }

    
    
    
    
    

    
    
    


    /**
     * Getters para cada atributo de la clase
     * @return 
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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    
    
    
}
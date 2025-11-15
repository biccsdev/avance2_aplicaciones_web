package joystickmx.itson.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Videojuegos")
public class Videojuego implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videojuego")
    private Long idVideojuego;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;
    
    @Column(name = "habilitado", nullable = false)
    private boolean habilitado = true; 

    @Column(length = 300)
    private String descripcion;

    @Column(nullable = false)
    @PositiveOrZero(message = "No se permiten precios negativos.")
    private Float precio;

    @Column(nullable = false)
    @PositiveOrZero(message = "No se permiten existencias negativas.")
    @Max(value = Integer.MAX_VALUE, message = "Límite de existencias superado.")
    private Integer existencias;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB", name = "imagen", nullable = false)
    private byte[] imagen;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El desarrollador no puede estar vacío.")
    private String desarrollador;

    @Column(nullable = false)
    private LocalDate fechaLanzamiento;

    @Column(nullable = false, length = 50)
    private String plataforma;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videojuego_categoria",
            joinColumns = @JoinColumn(name = "id_videojuego"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "videojuego", fetch = FetchType.LAZY)
    private List<DetallePedido> detallesPedidos;

    @OneToMany(mappedBy = "videojuego", fetch = FetchType.LAZY)
    private List<ItemCarrito> itemsCarrito;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Resena> resenas;

    public Videojuego() {}

    public Videojuego(Long idVideojuego, String nombre, boolean habilitado, String descripcion, Float precio, Integer existencias, byte[] imagen, String desarrollador, LocalDate fechaLanzamiento, String plataforma, List<Categoria> categorias, List<DetallePedido> detallesPedidos, List<ItemCarrito> itemsCarrito, List<Resena> resenas) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.habilitado = habilitado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
        this.categorias = categorias;
        this.detallesPedidos = detallesPedidos;
        this.itemsCarrito = itemsCarrito;
        this.resenas = resenas;
    }

    public Videojuego(Long idVideojuego, String nombre, boolean habilitado, String descripcion, Float precio, Integer existencias, byte[] imagen, String desarrollador, LocalDate fechaLanzamiento, String plataforma, List<DetallePedido> detallesPedidos) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.habilitado = habilitado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.plataforma = plataforma;
        this.detallesPedidos = detallesPedidos;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    

    

    public Long getIdVideojuego() {return idVideojuego;}

    public void setIdVideojuego(Long idVideojuego) {this.idVideojuego = idVideojuego;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Float getPrecio() {return precio;}

    public void setPrecio(Float precio) {this.precio = precio;}

    public Integer getExistencias() {return existencias;}

    public void setExistencias(Integer existencias) {this.existencias = existencias;}

    public byte[] getUrlImagen() {return imagen;}

    public void setUrlImagen(byte[] imagen) {this.imagen = imagen;}

    public String getDesarrollador() {return desarrollador;}

    public void setDesarrollador(String desarrollador) {this.desarrollador = desarrollador;}

    public LocalDate getFechaLanzamiento() {return fechaLanzamiento;}

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {this.fechaLanzamiento = fechaLanzamiento;}

    public String getPlataforma() {return plataforma;}

    public void setPlataforma(String plataforma) {this.plataforma = plataforma;}

    public List<Categoria> getCategorias() {return categorias;}

    public void setCategorias(List<Categoria> categorias) {this.categorias = categorias;}

    public List<DetallePedido> getDetallesPedidos() {return detallesPedidos;}

    public void setDetallesPedidos(List<DetallePedido> detallesPedidos) {this.detallesPedidos = detallesPedidos;}

    public byte[] getImagen() {return imagen;}

    public void setImagen(byte[] imagen) {this.imagen = imagen;}

    public List<ItemCarrito> getItemsCarrito() {return itemsCarrito;}

    public void setItemsCarrito(List<ItemCarrito> itemsCarrito) {this.itemsCarrito = itemsCarrito;}

    public List<Resena> getResenas() {return resenas;}

    public void setResenas(List<Resena> resenas) {this.resenas = resenas;}
}
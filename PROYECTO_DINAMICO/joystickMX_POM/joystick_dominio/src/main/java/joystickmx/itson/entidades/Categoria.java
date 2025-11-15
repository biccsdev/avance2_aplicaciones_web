package joystickmx.itson.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Categorias")
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;
    
    @Column(length = 200, nullable = true)
    private String descripcion;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private List<Videojuego> videojuegos;

    public Long getIdCategoria() {return idCategoria;}

    public void setIdCategoria(Long idCategoria) {this.idCategoria = idCategoria;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public List<Videojuego> getVideojuegos() {return videojuegos;}

    public void setVideojuegos(List<Videojuego> videojuegos) {this.videojuegos = videojuegos;}
}
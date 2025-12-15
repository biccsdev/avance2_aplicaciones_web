package joystickmx.itson.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import java.util.ArrayList;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
@Entity
@Table(name = "Carritos")
public class Carrito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @Column(name = "fecha_creacion", nullable = false)
    @PastOrPresent(message = "La fecha de creación no puede ser futura.") 
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemCarrito> items = new ArrayList<>();    ;

    public Carrito() {this.fechaCreacion = LocalDate.now();}

    public Long getIdCarrito() {return idCarrito;}

    public void setIdCarrito(Long idCarrito) {this.idCarrito = idCarrito;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public List<ItemCarrito> getItems() {return items;}

    public void setItems(List<ItemCarrito> items) {this.items = items;}
}
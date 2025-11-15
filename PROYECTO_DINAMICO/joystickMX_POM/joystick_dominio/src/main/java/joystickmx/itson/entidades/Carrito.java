package joystickmx.itson.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Carritos")
public class Carrito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @Column(name = "fecha_creacion", nullable = false)
    @FutureOrPresent(message = "La fecha no puede ser menor a la actual.")
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "carrito", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ItemCarrito> items;

    public Carrito() {this.fechaCreacion = LocalDate.now();}

    public Long getIdCarrito() {return idCarrito;}

    public void setIdCarrito(Long idCarrito) {this.idCarrito = idCarrito;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}

    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public List<ItemCarrito> getItems() {return items;}

    public void setItems(List<ItemCarrito> items) {this.items = items;}
}
package joystickmx.itson.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Direcciones")
public class Direccion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "La calle no puede estar vacía.")
    private String calle;
    
    @Column(nullable = false, length = 15)
    @NotBlank(message = "El número no puede estar vacío.")
    private String numero;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "La colonia no puede estar vacía.")
    private String colonia;

    public Direccion() {}

    public Direccion(String calle, String numero, String colonia) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public Long getIdDireccion() {return idDireccion;}

    public void setIdDireccion(Long idDireccion) {this.idDireccion = idDireccion;}
    
    public String getCalle() {return calle;}

    public void setCalle(String calle) {this.calle = calle;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getColonia() {return colonia;}

    public void setColonia(String colonia) {this.colonia = colonia;}
}
package joystickmx.itson.entidades;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author sonic
 */
@Embeddable
public class Direccion implements Serializable {

    private String calle;
    private String numero;
    private String colonia;

    public Direccion() {}

    public Direccion(String calle, String numero, String colonia) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public String getCalle() {return calle;}

    public void setCalle(String calle) {this.calle = calle;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getColonia() {return colonia;}

    public void setColonia(String colonia) {this.colonia = colonia;}
}
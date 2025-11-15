package joystickmx.itson.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;


/**
 *
 * @author sonic
 */
@Entity
@Table(name="administradores")
public class Administrador extends Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final long serialVersionUID = 1L;
    
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
}
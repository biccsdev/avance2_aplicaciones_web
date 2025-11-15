package joystickmx.itson.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import joystickmx.itson.enums.EstadoUsuario;


/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombres;
    
    @Column(name = "apellido_paterno", nullable = false, length = 100)
    @NotBlank(message = "El apellido paterno no puede estar vacío.")
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", nullable = false, length = 100)
    @NotBlank(message = "El apellido materno no puede estar vacío.")
    private String apellidoMaterno;

    @Column(unique = true, nullable = false, length = 200)
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "Ingrese un correo válido.")
    private String email;
    
    @Column(name = "password", nullable = false, length = 200)
    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String contrasenia;
    
    @Column(nullable = false, length = 20)
    @NotBlank(message = "El telefono no puede estar vacío.")
    private String telefono;
    
    @Column(name = "estadoUsuario", nullable = false)
    private EstadoUsuario estadoUsuario;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Direccion direccion;

    public Usuario() {}

    public Usuario(
            Long idUsuario, 
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String contrasenia, 
            String telefono, 
            EstadoUsuario isActive, 
            Direccion direccion
    ) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.estadoUsuario = estadoUsuario;
        this.direccion = direccion;
    }
    
    public Long getIdUsuario() {return idUsuario;}

    public void setIdUsuario(Long idUsuario) {this.idUsuario = idUsuario;}

    public String getNombres() {return nombres;}

    public void setNombres(String nombres) {this.nombres = nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getContrasenia() {return contrasenia;}

    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
    
    

    public Direccion getDireccion() {return direccion;}

    public void setDireccion(Direccion direccion) {this.direccion = direccion;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || 
            (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario[ idUsuario=" + nombres + " ]";
    }
}
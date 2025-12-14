package joystickmx.itson.DTO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class UsuarioRegistroDTO {

    private String nombres;
    private String apellidoPaterno;   
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String contrasenia;
    private String EstadoUsuario;
    private DireccionDTO direccion;

    public UsuarioRegistroDTO() {}

    public UsuarioRegistroDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono, 
            String contrasenia, 
            String EstadoUsuario, 
            DireccionDTO direccion
    ) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.EstadoUsuario = EstadoUsuario;
        this.direccion = direccion;
    }

    public UsuarioRegistroDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono, 
            String contrasenia, 
            DireccionDTO direccion
    ) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
    }

    public String getNombres() {return nombres;}

    public void setNombres(String nombres) {this.nombres = nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getContrasenia() {return contrasenia;}

    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    public DireccionDTO getDireccion() {return direccion;}

    public String getEstadoUsuario() {return EstadoUsuario;}

    public void setEstadoUsuario(String EstadoUsuario) {this.EstadoUsuario = EstadoUsuario;}

    public void setDireccion(DireccionDTO direccion) {this.direccion = direccion;}
}
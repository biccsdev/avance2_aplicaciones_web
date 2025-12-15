package joystickmx.itson.DTO;

/**
 * UsuarioDTO - Data Transfer Object para Usuario
 *
 * Se usa para transferir información de usuarios.
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class UsuarioDTO {

    private Long idUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String estadoUsuario;
    private DireccionDTO direccion;
    private String rol; 

    public UsuarioDTO() {}
    
    public UsuarioDTO(
            Long idUsuario, 
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono, 
            String estadoUsuario,
            DireccionDTO direccion,
            String rol
    ) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.estadoUsuario = estadoUsuario;
        this.direccion = direccion;
        this.rol = rol;
    }
    
    public UsuarioDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono, 
            String estadoUsuario,
            DireccionDTO direccion
    ) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.estadoUsuario = estadoUsuario;
        this.direccion = direccion;
    }
    
    /**
     * Getters para cada atributo de la clase
     * @return
     */
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

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getEstadoUsuario() {return estadoUsuario;}

    public void setEstadoUsuario(String estadoUsuario) {this.estadoUsuario = estadoUsuario;}

    public DireccionDTO getDireccion() {return direccion;}

    public void setDireccion(DireccionDTO direccion) {this.direccion = direccion;}
    
    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}
}
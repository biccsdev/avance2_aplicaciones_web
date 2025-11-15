package joystickmx.itson.DTO;
/**
 * UsuarioDTO - Data Transfer Object para Usuario
 *
 * Se usa para transferir información de usuarios.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class UsuarioDTO {

    private String idUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private boolean isActive;
    private String rol;
    private DireccionDTO direccion;

    
    
    
        /**
     * Método constructor para instanciar la clase UsuarioDTO
     * @param idUsuario Representa el identificador del usuario
     * @param nombres
     * @param apellidoPaterno Representa el apellido paterno del usuario
     * @param apellidoMaterno Representa el apellido materno del usuario
     * @param email Representa el email del usuario
     * @param telefono Representa el telédono del usuario
     * @param isActive Representa si es usuario esta activo o no
     * @param rol
     * @param direccion Representa la dirección del usuario
     */


    
    
    public UsuarioDTO(String idUsuario, String nombres, String apellidoPaterno, String apellidoMaterno, String email, String telefono, boolean isActive, String rol, DireccionDTO direccion) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.isActive = isActive;
        this.rol = rol;
        this.direccion = direccion;
    }

    public UsuarioDTO(String idUsuario, String nombres, String apellidoPaterno, String apellidoMaterno, String email, String telefono, boolean isActive, String rol) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.isActive = isActive;
        this.rol = rol;
    }
    
        /**
     * Getters para cada atributo de la clase
     * @return 
     */
    

    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }


    

}
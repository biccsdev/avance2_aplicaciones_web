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
    
    /**
     * Declaración de atributos de usuario
     */
    private String idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String contrasenia;
    private boolean isActive;
    private String direccion;
    private String calle;
    private int numero;
    private String colonia;

    /**
     * Método constructor para instanciar la clase UsuarioDTO
     * @param idUsuario Representa el identificador del usuario
     * @param nombre Representa el/los nombre del usuario
     * @param apellidoPaterno Representa el apellido paterno del usuario
     * @param apellidoMaterno Representa el apellido materno del usuario
     * @param email Representa el email del usuario
     * @param telefono Representa el telédono del usuario
     * @param contrasenia Representa la contraseña del usuario
     * @param isActive Representa si es usuario esta activo o no
     * @param direccion Representa la dirección del usuario
     * @param calle Representa la calle de la dirección del usuario
     * @param numero Representa el número de la dirección del usuario
     * @param colonia Representa la colonia de la dirección del usuario
     */
    public UsuarioDTO(String idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String contrasenia, boolean isActive, String direccion, String calle, int numero, String colonia) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.isActive = isActive;
        this.direccion = direccion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    /**
     * Getters para cada atributo de la clase
     */
    
    public String getIdUsuario(){
        return idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

}
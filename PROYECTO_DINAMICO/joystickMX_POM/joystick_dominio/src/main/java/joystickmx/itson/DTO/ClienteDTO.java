package joystickmx.itson.DTO;

/**
 *
 * @author PC WHITE WOLF
 */
public class ClienteDTO extends UsuarioDTO {
    
    private CarritoDTO carrito;

    public ClienteDTO() {}

    public ClienteDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono,
            String estadoUsuario,
            String contrasenia, 
            DireccionDTO direccion,
            CarritoDTO carrito
    ) {
        super(nombres, apellidoPaterno, apellidoMaterno, email, telefono, estadoUsuario, direccion);
        this.carrito = carrito;
    }

    public CarritoDTO getCarrito() {return carrito;}

    public void setCarrito(CarritoDTO carrito) {this.carrito = carrito;}
}
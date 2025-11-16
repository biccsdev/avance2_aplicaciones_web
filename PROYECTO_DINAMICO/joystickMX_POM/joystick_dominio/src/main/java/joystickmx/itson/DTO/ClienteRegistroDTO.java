package joystickmx.itson.DTO;

/**
 *
 * @author PC WHITE WOLF
 */
public class ClienteRegistroDTO extends UsuarioRegistroDTO {
    
    private CarritoDTO carrito;

    public ClienteRegistroDTO() {}

    public ClienteRegistroDTO(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String email, 
            String telefono, 
            String contrasenia, 
            DireccionDTO direccion,
            CarritoDTO carrito
    ) {
        super(nombres, apellidoPaterno, apellidoMaterno, email, telefono, contrasenia, direccion);
        this.carrito = carrito;
    }

    public CarritoDTO getCarrito() {return carrito;}

    public void setCarrito(CarritoDTO carrito) {this.carrito = carrito;}
}
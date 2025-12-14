package joystickmx.itson.DTO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
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
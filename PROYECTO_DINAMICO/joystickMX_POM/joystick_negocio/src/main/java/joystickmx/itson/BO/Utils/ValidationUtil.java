package joystickmx.itson.BO.Utils;

import java.time.LocalDate;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.EstadoPedido;
import joystickmx.itson.enums.MetodoPago;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class ValidationUtil {
    
    public static final int NOMBRE_LENGTH = 100;
    public static final int APELLIDO_P_LENGTH = 100;
    public static final int APELLIDO_M__LENGTH = 100;    
    public static final int EMAIL__LENGTH = 200;
    public static final int PASSWORD__LENGTH = 200;    
    public static final int TELEFONO__LENGTH = 200;
    
    public static final int CALLE__LENGTH = 100;
    public static final int NUMERO__LENGTH = 15;
    public static final int COLONIA__LENGTH = 100;
    
    public static final float LIMITE__CALIF = 5.0f;
    public static final int COMENTARIO_LENGTH = 500;
    
    public static final int NOMBRE_VID_LENGTH = 100;
    public static final int DESCRIPCION_LENGTH = 500;
    public static final int URL_IMG_LENGTH = 200;
    public static final int DESARROLLADOR_LENGTH = 100;
    public static final int PLATAFORMA_LENGTH = 50;
    
    public static final int NOMBRE_CATG_LENGTH = 100;
    public static final int DESCRIPCION_CATG_LENGTH = 200;
    
    public static void validarNombreUsuario(String nombre) throws NegocioException{
        if(nombre == null || nombre.isBlank())
            throw new NegocioException("El nombre está vacío.");
        
        if(nombre.length() > NOMBRE_LENGTH)
            throw new NegocioException("El nombre es demasiado largo.");
    }
    
    public static void validarApellidoPaterno(String apeliidoPaterno) throws NegocioException{
        if(apeliidoPaterno == null || apeliidoPaterno.isBlank())
            throw new NegocioException("El apeliido paterno está vacío.");
        
        if(apeliidoPaterno.length() > APELLIDO_P_LENGTH)
            throw new NegocioException("El apeliido paterno es demasiado largo.");
    }
    
    public static void validarApellidoMaterno(String apeliidoMaterno) throws NegocioException{
        if(apeliidoMaterno == null || apeliidoMaterno.isBlank())
            throw new NegocioException("El apeliido materno está vacío.");
        
        if(apeliidoMaterno.length() > APELLIDO_M__LENGTH)
            throw new NegocioException("El apeliido materno es demasiado largo.");
    }
    
    public static void validarEmail(String email) throws NegocioException{
        if(email == null || email.isBlank())
            throw new NegocioException("El correo está vacío.");
        
        if(email.length() > EMAIL__LENGTH)
            throw new NegocioException("El correo es demasiado largo.");
        
        if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$"))
            throw new NegocioException("El correo ingresado no es válido.");
    }
    
    // Quizás falta que sea una contraseña más segura
    public static void validarPassword(String password) throws NegocioException{
        if(password == null || password.isBlank())
            throw new NegocioException("La contraseña está vacía.");
        
        if(password.length() > PASSWORD__LENGTH)
            throw new NegocioException("La contraseña es demasiado larga.");
    }
    
    public static void validarTelefono(String telefono) throws NegocioException{
        if(telefono == null || telefono.isBlank())
            throw new NegocioException("El teléfono está vacío.");
        
        if(telefono.length() > TELEFONO__LENGTH)
            throw new NegocioException("El teléfono es demasiado largo.");
        
        if(!telefono.matches("^\\+?[1-9]\\d{1,14}$"))
            throw new NegocioException("El telefono ingresado no es válido.");
    }
    
    public static void validarDireccion(String calle, String numero, String colonia) throws NegocioException{
        
        if(calle == null || calle.isBlank())
            throw new NegocioException("La calle de la dirección está vacía.");
        
        if(numero == null || numero.isBlank())
            throw new NegocioException("El numero de la dirección está vacío.");
        
        if(colonia == null || colonia.isBlank())
            throw new NegocioException("La colonia de la dirección está vacía.");
        
        if(calle.length() > CALLE__LENGTH)
            throw new NegocioException("La calle de la dirección es demasiado largo.");
        
        if(numero.length() > NUMERO__LENGTH)
            throw new NegocioException("El teléfono de la dirección es demasiado largo.");
        
        if(colonia.length() > COLONIA__LENGTH)
            throw new NegocioException("La colonia de la dirección es demasiado largo.");
    }
    
    public static void validarCalificacion(Float calificacion) throws NegocioException{
        if(calificacion < 0.0)
            throw new NegocioException("La calificación es menor a cero.");
        
        if(calificacion > LIMITE__CALIF)
            throw new NegocioException("La calificacion es mayor que el límite permitido.");
        
        if((calificacion * 2) % 1 != 0)
            throw new NegocioException("Solo se permiten calificaciones múltiplos de 0.5.");
    }
    
    public static void validarComentario(String comentario) throws NegocioException{
        if(comentario == null || comentario.isBlank())
            throw new NegocioException("El comentario está vacío.");
        
        if(comentario.length() > COMENTARIO_LENGTH)
            throw new NegocioException("El comentario es demasiado largo.");
    }
    
    public static void validarFechaPublicacionCreacion(LocalDate fecha) throws NegocioException{
        if(fecha == null)
            throw new NegocioException("La fecha está vacía.");
        
        if(fecha.isBefore(LocalDate.now()))
            throw new NegocioException("La fecha no puede ser anterior a la actual.");
    }
    
    public static void validarFechaLanzamiento(LocalDate fecha) throws NegocioException{
        if(fecha == null)
            throw new NegocioException("La fecha está vacía.");
        
        if(fecha.isAfter(LocalDate.now()))
            throw new NegocioException("La fecha no puede ser posterior a la actual.");
    }
    
    public static void validarCantidad(int cantidad) throws NegocioException{
        if(cantidad < 0)
            throw new NegocioException("La cantidad no puede ser negativa.");
        
        if(cantidad > Integer.MAX_VALUE)
            throw new NegocioException("La cantidad es demasiado grande.");
    }
    
    public static void validarPrecio(Float precio) throws NegocioException{
        if(precio < 0.0)
            throw new NegocioException("El precio no puede ser negativo.");
        
        if(precio > Float.MAX_VALUE)
            throw new NegocioException("El precio es demasiado grande.");
    }
    
    public static void validarEstadoPedido(String estadoPedido) throws NegocioException{
        if(estadoPedido == null || estadoPedido.isBlank())
            throw new NegocioException("El estado del pedido está vacío.");
        
        if(
                !estadoPedido.toUpperCase().equals(EstadoPedido.CANCELADO.toString())
                                                ||
                !estadoPedido.toUpperCase().equals(EstadoPedido.ENTREGADO.toString())
                                                ||
                !estadoPedido.toUpperCase().equals(EstadoPedido.ENVIADO.toString())
                                                ||
                !estadoPedido.toUpperCase().equals(EstadoPedido.PENDIENTE.toString())
        )
            throw new NegocioException("El estado del pedido no es válido.");
    }
    
    public static void validarMetodoPago(String metodoPago) throws NegocioException{
        if(metodoPago == null || metodoPago.isBlank())
            throw new NegocioException("El método de pago está vacío.");
        
        if(
                !metodoPago.toUpperCase().equals(MetodoPago.CONTRA_PAGO.toString())
                                                ||
                !metodoPago.toUpperCase().equals(MetodoPago.TARJETA.toString())
                                                ||
                !metodoPago.toUpperCase().equals(MetodoPago.TRANSFERENCIA.toString())
        )
            throw new NegocioException("El método de pago no es válido.");
    }
    
    public static void validarEstadoPago(String estadoPago) throws NegocioException{
        if(estadoPago == null || estadoPago.isBlank())
            throw new NegocioException("El estado del pago está vacío.");
        
        if(
                !estadoPago.toUpperCase().equals(EstadoPago.CONFIRMADO.toString())
                                                ||
                !estadoPago.toUpperCase().equals(EstadoPago.RECHAZADO.toString())
        )
            throw new NegocioException("El estado del pago no es válido.");
    }
    
    public static void validarNombreVideojuego(String nombreVideojuego) throws NegocioException{
        if(nombreVideojuego == null || nombreVideojuego.isBlank())
            throw new NegocioException("El nombre está vacío.");
        
        if(nombreVideojuego.length() > NOMBRE_VID_LENGTH)
            throw new NegocioException("El nombre es demasiado largo.");
    }
    
    public static void validarDescripcion(String descripcion) throws NegocioException{
        if(descripcion == null || descripcion.isBlank())
            throw new NegocioException("El comentario está vacío.");
        
        if(descripcion.length() > DESCRIPCION_LENGTH)
            throw new NegocioException("El comentario es demasiado largo.");
    }
    
    public static void validarUrlImagen(String urlImagen) throws NegocioException{
        if(urlImagen == null || urlImagen.isBlank())
            throw new NegocioException("La url de la iimagen está vacía.");
        
        if(urlImagen.length() > URL_IMG_LENGTH)
            throw new NegocioException("La url de la imagen es demasiada larga.");
        
        if(!urlImagen.matches("(?i).*\\.(png|jpe?g|gif|bmp|webp|svg)$"))
            throw new NegocioException("La url de la imagen no es válida.");
    }
    
    public static void validarDesarrollador(String desarrollador) throws NegocioException{
        if(desarrollador == null || desarrollador.isBlank())
            throw new NegocioException("El desarrollador está vacío.");
        
        if(desarrollador.length() > DESARROLLADOR_LENGTH)
            throw new NegocioException("El desarrollador es demasiado largo.");
    }
    
    public static void validarPlataforma(String plataforma) throws NegocioException{
        if(plataforma == null || plataforma.isBlank())
            throw new NegocioException("La plataforma está vacía.");
        
        if(plataforma.length() > PLATAFORMA_LENGTH)
            throw new NegocioException("La plataforma es demasiado larga.");
    }
    
    public static void validarNombreCategoria(String nombreCategoria) throws NegocioException{
        if(nombreCategoria == null || nombreCategoria.isBlank())
            throw new NegocioException("El nombre de la categoría está vacío.");
        
        if(nombreCategoria.length() > NOMBRE_CATG_LENGTH)
            throw new NegocioException("El nombre de la categoría es demasiado largo.");
    }
    
    public static void validarDescripcionCategoria(String descripcionCategoria) throws NegocioException{
        if(descripcionCategoria == null || descripcionCategoria.isBlank())
            throw new NegocioException("La descripción de la categoría está vacía.");
        
        if(descripcionCategoria.length() > DESCRIPCION_CATG_LENGTH)
            throw new NegocioException("La descripción de la categoría es demasiado larga.");
    }
}
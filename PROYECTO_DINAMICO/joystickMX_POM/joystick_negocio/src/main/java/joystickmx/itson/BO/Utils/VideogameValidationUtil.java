package joystickmx.itson.BO.Utils;

import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class VideogameValidationUtil {
    
    private static final int NOMBRE_VID_LENGTH = 100;
    private static final int DESCRIPCION_LENGTH = 500;
    private static final int URL_IMG_LENGTH = 200;
    private static final int DESARROLLADOR_LENGTH = 100;
    private static final int PLATAFORMA_LENGTH = 50;
    
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
    
}
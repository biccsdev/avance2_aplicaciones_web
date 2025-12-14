package joystickmx.itson.BO.Utils;

import java.math.BigDecimal;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class ReviewValidationUtil {
    // Restricciones de la calificación
    private static final float MIN_SCORE = 0.0f;
    private static final float MAX_SCORE = 5.0f;
    private static final int SCORE_MAX_INTEGERS = 2;
    private static final int SCORE_MAX_DECIMALS = 1;
    // Restricción del título
    private static final int TITULO_MAX_LENGTH = 100;
    // Restricción del comentario
    private static final int COMENTARIO_MAX_LENGTH = 500;
    
    public static void validarCalificacion(Float calificacion) throws NegocioException{
        if(calificacion < MIN_SCORE)
            throw new NegocioException("La calificación es menor a cero.");
        
        if(calificacion > MAX_SCORE)
            throw new NegocioException("La calificacion es mayor que el límite permitido.");
        
        if((calificacion * 2) % 1 != 0)
            throw new NegocioException("Solo se permiten calificaciones múltiplos de 0.5.");
        
        BigDecimal bd = new BigDecimal(calificacion.toString()).stripTrailingZeros();
        int enteros = bd.precision() - bd.scale();
        int decimales = Math.max(bd.scale(), 0);
        
        if(enteros > SCORE_MAX_INTEGERS)
            throw new NegocioException("La calificación tiene demasiados números enteros.");
        
        if(decimales > SCORE_MAX_DECIMALS)
            throw new NegocioException("La calificación tiene demasiados decimales.");
    }
    
    public static void validarTitulo(String titulo) throws NegocioException{
        if(titulo == null || titulo.isBlank())
            throw new NegocioException("El título está vacío.");
        
        if(titulo.length() > TITULO_MAX_LENGTH)
            throw new NegocioException("El título es demasiado largo.");
    }
    
    public static void validarComentario(String comentario) throws NegocioException{
        if(comentario == null || comentario.isBlank())
            throw new NegocioException("El comentario está vacío.");
        
        if(comentario.length() > COMENTARIO_MAX_LENGTH)
            throw new NegocioException("El comentario es demasiado largo.");
    }
}
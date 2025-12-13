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
public class CategoryValidationUtil {
    
    private static final int NOMBRE_CATG_LENGTH = 100;
    private static final int DESCRIPCION_CATG_LENGTH = 200;
    
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
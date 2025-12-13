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
public class AddressValidationUtil {
    
    private static final int CALLE__LENGTH = 100;
    private static final int NUMERO__LENGTH = 15;
    private static final int COLONIA__LENGTH = 100;
    
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
}
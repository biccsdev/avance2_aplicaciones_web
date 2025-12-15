package joystickmx.negocio.exception;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class NegocioException extends Exception {

    public NegocioException() {}

    public NegocioException(String message) {super(message);}

    public NegocioException(String message, Throwable cause) {super(message, cause);}

    public NegocioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
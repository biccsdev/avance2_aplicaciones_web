package joystickmx.itson.BO.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class PasswordUtil {

    /**
     * Hashea una contraseña usando SHA-256.
     *
     * @param password La contraseña en texto plano.
     * @return El hash SHA-256 como un String hexadecimal.
     * @throws NegocioException Si el algoritmo SHA-256 no está disponible.
     */
    public static String hashPassword(String password) throws NegocioException {
        if (password == null || password.isEmpty()) {
            throw new NegocioException("La contraseña no puede ser nula o vacía.");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new NegocioException("No se pudo encontrar el algoritmo de hashing SHA-256", e);
        }
    }

    /**
     * Compara una contraseña en texto plano con un hash existente.
     *
     * @param passwordPlano La contraseña ingresada por el usuario.
     * @param hashAlmacenado El hash guardado en la base de datos.
     * @return true si las contraseñas coinciden, false en caso contrario.
     * @throws NegocioException Si ocurre un error de hashing.
     */
    public static boolean verificarPassword(String passwordPlano, String hashAlmacenado) throws NegocioException {
        if (passwordPlano == null || hashAlmacenado == null) {
            return false;
        }
        String hashNuevo = hashPassword(passwordPlano);
        return hashNuevo.equals(hashAlmacenado);
    }
}
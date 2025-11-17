package joystickmx.itson.pruebas;

import java.util.List;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.Factory.FactoryBO;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC WHITE WOLF
 */
public class PruebasNegocio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<UsuarioDTO> clientesExistentes = FactoryBO.buscarClientesExistentes();
            System.out.println(clientesExistentes.size());
        } catch (Exception ex) {
            System.getLogger(PruebasNegocio2.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
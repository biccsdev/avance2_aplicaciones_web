
package joystickmx.itson.Mappers;

import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.DireccionEnvio;

/**
 *
 * @author PC Gamer
 */
public class DTOMapeadores {


    public static Direccion toEntity(DireccionDTO dto) {
        if (dto == null) return null;
        
        Direccion entity = new Direccion();
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setColonia(dto.getColonia());
        return entity;
    }


    public static DireccionEnvio toEntityEnvio(DireccionDTO dto) {
        if (dto == null) return null;
        
        DireccionEnvio entity = new DireccionEnvio();
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setColonia(dto.getColonia());
        return entity;
    }


    public static Cliente toEntity(UsuarioRegistroDTO dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        cliente.setNombres(dto.getNombres());
        cliente.setApellidoPaterno(dto.getApellidoPaterno());
        cliente.setApellidoMaterno(dto.getApellidoMaterno());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setContrasenia(dto.getContrasenia());
        


        cliente.setIsActive(true); 
        cliente.setCarrito(new Carrito());


        if (dto.getDireccion() != null) {
            cliente.setDireccion(toEntity(dto.getDireccion()));
        }
        
        return cliente;
    }

//pendiente los demas mappers    
}

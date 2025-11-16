
package joystickmx.itson.Mappers;

import java.util.stream.Collectors;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.DireccionEnvio;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.enums.EstadoUsuario;

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
        cliente.setEstadoUsuario(EstadoUsuario.ACTIVO); 
        cliente.setCarrito(new Carrito());
        
        if (dto.getDireccion() != null) 
            cliente.setDireccion(toEntity(dto.getDireccion()));
        
        return cliente;
    }

    //pendiente los demas mappers
    public static Videojuego toEntityVideojuego(VideojuegoDTO dto){
        if(dto == null) return null;
        
        Videojuego entity = new Videojuego();
        
        // Valida si tiene ID, en caso de ser un videojuego nuevo a ser registrado
        if(dto.getIdVideojuego() != null)
            entity.setIdVideojuego(Long.valueOf(dto.getIdVideojuego()));
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setExistencias(dto.getExistencias());
        entity.setHabilitado(dto.isHabilitado());
        entity.setDesarrollador(dto.getDesarrollador());
        entity.setFechaLanzamiento(dto.getFechaLanzamiento());
        entity.setPlataforma(dto.getPlataforma());
        entity.setUrlImagen(dto.getUrlImagen());
//        List<Categoria> categorias = new ArrayList<>();
//        for(CategoriaDTO categoriaDTO : dto.getCategorias())
//            categorias.add(toEntityCategoria(categoriaDTO));
        entity.setCategorias(dto.
                getCategorias().
                stream().
                map(DTOMapeadores::toEntityCategoria).
                collect(Collectors.toList())
        );
        return entity;
    }
    
    public static Categoria toEntityCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(Long.valueOf(categoriaDTO.getIdCategoria()));
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        // Quizás falte mapear los videojuegos de la categoría, aunque podría derivar en un mapeo infinito.
        return categoria;
    }
}
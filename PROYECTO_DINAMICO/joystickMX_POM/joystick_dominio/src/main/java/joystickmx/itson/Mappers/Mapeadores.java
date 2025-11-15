
package joystickmx.itson.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.CarritoDTO;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.DireccionDTO;
import joystickmx.itson.DTO.ItemCarritoDTO;
import joystickmx.itson.DTO.PagoDTO;
import joystickmx.itson.DTO.PedidoDTO;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.entidades.Administrador;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.DireccionEnvio;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Pago;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.entidades.Resena;
import joystickmx.itson.entidades.Usuario;
import joystickmx.itson.entidades.Videojuego;

/**
 *
 * @author PC Gamer
 */
public class Mapeadores {


    public static DireccionDTO toDTO(Direccion entity) {
        if (entity == null) return null;
        return new DireccionDTO(
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }


    public static DireccionDTO toDTO(DireccionEnvio entity) {
        if (entity == null) return null;
        return new DireccionDTO(
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }


    
    
    
    
    
    public static CategoriaDTO toDTO(Categoria entity) {
        if (entity == null) return null;
        return new CategoriaDTO(
                String.valueOf(entity.getIdCategoria()),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }


    public static List<CategoriaDTO> toCategoriaDTOList(List<Categoria> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }


    
    
    
    
    
    
    
    public static UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) return null;

        String rol = "cliente"; 
        if (entity instanceof Administrador) {
            rol = "admin";
        }

        return new UsuarioDTO(
                String.valueOf(entity.getIdUsuario()),
                entity.getNombres(),
                entity.getApellidoPaterno(),
                entity.getApellidoMaterno(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getEstadoUsuario(),
                rol,
                toDTO(entity.getDireccion()) 
        );
    }


    
    
    
    
    
    
    
    
    public static VideojuegoDTO toDTO(Videojuego entity) {
        if (entity == null) return null;
        return new VideojuegoDTO(
                String.valueOf(entity.getIdVideojuego()),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getExistencias(),
                entity.isHabilitado(), 
                entity.getDesarrollador(),
                entity.getFechaLanzamiento(),
                entity.getPlataforma(),
                entity.getImagen(),
                toCategoriaDTOList(entity.getCategorias())
        );
    }


    
    
    
    
    
    
    
    public static ItemCarritoDTO toDTO(ItemCarrito entity) {
        if (entity == null) return null;

        String vjId = null;
        String vjNombre = null;
        Float vjPrecio = null;
        
        if (entity.getVideojuego() != null) {
            vjId = String.valueOf(entity.getVideojuego().getIdVideojuego());
            vjNombre = entity.getVideojuego().getNombre();
            vjPrecio = entity.getVideojuego().getPrecio();
        }

        return new ItemCarritoDTO(
                String.valueOf(entity.getIdItemCarrito()),
                entity.getCantidad(),
                entity.getSubtotal(), 
                vjId,
                vjNombre,
                vjPrecio
        );
    }


    
    
    
    
    
    
    
    public static List<ItemCarritoDTO> toItemCarritoDTOList(List<ItemCarrito> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }


    
    
    
    
    
    
    
    public static CarritoDTO toDTO(Carrito entity) {
        if (entity == null) return null;

        List<ItemCarritoDTO> items = toItemCarritoDTOList(entity.getItems());
        
        Float total = (float) items.stream()
                .mapToDouble(item -> (item.getSubtotal() != null ? item.getSubtotal() : 0.0))
                .sum();

        return new CarritoDTO(
                String.valueOf(entity.getIdCarrito()),
                entity.getFechaCreacion(),
                items,
                total
        );
    }


    

    
    
    
    
    public static PagoDTO toDTO(Pago entity) {
        if (entity == null) return null;
        return new PagoDTO(
                String.valueOf(entity.getIdPago()),
                entity.getMonto(),
                entity.getMetodoPago().name(), 
                entity.getEstadoPago().name(),
                entity.getFechaPago()
        );
    }


    
    
    
    
    
    
    
    public static DetallePedidoDTO toDTO(DetallePedido entity) {
        if (entity == null) return null;
        
        String vjId = null;
        String vjNombre = null;

        if (entity.getVideojuego() != null) {
            vjId = String.valueOf(entity.getVideojuego().getIdVideojuego());
            vjNombre = entity.getVideojuego().getNombre();
        }

        return new DetallePedidoDTO(
                vjId,
                vjNombre,
                entity.getCantidad(),
                entity.getPrecioUnitario(), 
                entity.getImporte()       
        );
    }


    
    
    
    
    
    
    
    
    
    
    public static List<DetallePedidoDTO> toDetallePedidoDTOList(List<DetallePedido> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }


    public static PedidoDTO toDTO(Pedido entity) {
        if (entity == null) return null;
        return new PedidoDTO(
                String.valueOf(entity.getIdPedido()),
                entity.getEstadoPedido().name(),
                entity.getTotalPagado(),
                entity.getFechaPedido(),
                toDTO(entity.getDireccionEnvio()), 
                toDetallePedidoDTOList(entity.getDetalles()), 
                toDTO(entity.getPago())
        );
    }

    
    
    
    
    
    
    
    
    
    
    
    
    public static ResenaDTO toDTO(Resena entity) {
        if (entity == null) return null;

        String clienteId = null;
        String clienteNombre = null;

        if (entity.getCliente() != null) {
            clienteId = String.valueOf(entity.getCliente().getIdUsuario());
            clienteNombre = entity.getCliente().getNombres();
        }

        return new ResenaDTO(
                String.valueOf(entity.getIdResena()),
                entity.getCalificacion(),
                entity.getComentario(),
                entity.getFechaResena(),
                clienteId,
                clienteNombre
        );
    }
    
    
    
    
}

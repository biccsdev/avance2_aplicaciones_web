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


    public static DireccionDTO toDireccionDTO(Direccion entity) {
        if (entity == null) return null;
        return new DireccionDTO(
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }
    
    public static DireccionDTO toDireccionEnvioDTO(DireccionEnvio entity) {
        if (entity == null) return null;
        return new DireccionDTO(
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }
    
    public static CategoriaDTO toCategoriaDTO(Categoria entity) {
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
                .map(Mapeadores::toCategoriaDTO)
                .collect(Collectors.toList());
    }
    
    public static UsuarioDTO toUsuarioDTO(Usuario entity) {
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
                entity.getEstadoUsuario().toString(),
                toDireccionDTO(entity.getDireccion()) 
        );
    }
    
    public static VideojuegoDTO toVideojuegoDTO(Videojuego entity) {
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
                entity.getUrlImagen(),
                toCategoriaDTOList(entity.getCategorias()),
                toResenasDTOList(entity.getResenas())
        );
    }
    
    public static ItemCarritoDTO toItemCarritoDTO(ItemCarrito entity) {
        if (entity == null) return null;

        String vjId = null;
        String vjNombre = null;
        Float vjPrecio = null;
        
        if (entity.getVideojuego() != null) {
            vjId = String.valueOf(entity.getVideojuego().getIdVideojuego());
            vjNombre = entity.getVideojuego().getNombre();
            vjPrecio = entity.getVideojuego().getPrecio();
        }
        
        String idCarrito = null;
        if(entity.getCarrito() != null)
            idCarrito = entity.getCarrito().getIdCarrito().toString();
        
        return new ItemCarritoDTO(
                String.valueOf(entity.getIdItemCarrito()),
                entity.getCantidad(),
                entity.getSubtotal(), 
                vjId,
                vjNombre,
                vjPrecio,
                idCarrito
        );
    }
    
    public static List<ItemCarritoDTO> toItemCarritoDTOList(List<ItemCarrito> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(Mapeadores::toItemCarritoDTO)
                .collect(Collectors.toList());
    }
    
    public static CarritoDTO toCarritoDTO(Carrito entity) {
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
    
    public static DetallePedidoDTO toDetallePedidoDTO(DetallePedido entity) {
        if (entity == null) return null;
        
        String vjId = null;
        String vjNombre = null;

        if (entity.getVideojuego() != null) {
            vjId = String.valueOf(entity.getVideojuego().getIdVideojuego());
            vjNombre = entity.getVideojuego().getNombre();
        }
        
        String idPedido = null;
        if(entity.getPedido() != null)
            idPedido = entity.getPedido().getIdPedido().toString();

        return new DetallePedidoDTO(
                entity.getCantidad(),
                entity.getPrecioUnitario(), 
                entity.getImporte(),
                vjId,
                vjNombre,
                idPedido
        );
    }
    
    public static List<DetallePedidoDTO> toDetallePedidoDTOList(List<DetallePedido> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
                .map(Mapeadores::toDetallePedidoDTO)
                .collect(Collectors.toList());
    }
    
    public static PedidoDTO toPedidoDTO(Pedido entity) {
        if (entity == null) return null;
        
        String idCliente = null;
        if(entity.getCliente() != null)
            idCliente = entity.getCliente().getIdUsuario().toString();
        
        return new PedidoDTO(
                String.valueOf(entity.getIdPedido()),
                entity.getEstadoPedido().name(),
                entity.getTotalPagado(),
                entity.getFechaPedido(),
                toDireccionEnvioDTO(entity.getDireccionEnvio()), 
                toDetallePedidoDTOList(entity.getDetalles()), 
                toDTO(entity.getPago()),
                idCliente
        );
    }
    
    public static ResenaDTO toResenaDTO(Resena entity) {
        if (entity == null) return null;

        String clienteId = null;
        String clienteNombre = null;

        if (entity.getCliente() != null) {
            clienteId = String.valueOf(entity.getCliente().getIdUsuario());
            clienteNombre = entity.getCliente().getNombres();
        }
        String idVideojuego = null;
        if(entity.getVideojuego() != null) 
            idVideojuego = entity.getVideojuego().getIdVideojuego().toString();

        return new ResenaDTO(
                String.valueOf(entity.getIdResena()),
                entity.getCalificacion(),
                entity.getComentario(),
                entity.getFechaResena(),
                clienteId,
                clienteNombre,
                idVideojuego
        );
    }
    
    public static List<ResenaDTO> toResenasDTOList(List<Resena> entities){
        if(entities == null) return new ArrayList<>();
        return entities.stream().map(Mapeadores::toResenaDTO).collect(Collectors.toList());
    }
    
}
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
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.entidades.Carrito;
import joystickmx.itson.entidades.Categoria;
import joystickmx.itson.entidades.Cliente;
import joystickmx.itson.entidades.DetallePedido;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.DireccionEnvio;
import joystickmx.itson.entidades.ItemCarrito;
import joystickmx.itson.entidades.Pago;
import joystickmx.itson.entidades.Pedido;
import joystickmx.itson.entidades.Resena;
import joystickmx.itson.entidades.Videojuego;
import joystickmx.itson.enums.EstadoPago;
import joystickmx.itson.enums.EstadoPedido;
import joystickmx.itson.enums.EstadoUsuario;
import joystickmx.itson.enums.MetodoPago;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class DTOMapeadores {
    
    public static Direccion toDireccionEntity(DireccionDTO dto) {
        if (dto == null) return null;
        
        Direccion entity = new Direccion();
        
        if (dto.getIdDireccion() != null) {
            entity.setIdDireccion(dto.getIdDireccion());
        }
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setColonia(dto.getColonia());
        
        return entity;
    }
    
    public static DireccionEnvio toDireccionEnvioEntity(DireccionDTO dto) {
        if (dto == null) return null;
        
        DireccionEnvio entity = new DireccionEnvio();
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setColonia(dto.getColonia());
        
        return entity;
    }

    public static Cliente toClienteEntity(UsuarioRegistroDTO dto) {
        if (dto == null) return null;

        Cliente cliente = new Cliente();
        
        cliente.setNombres(dto.getNombres());
        cliente.setApellidoPaterno(dto.getApellidoPaterno());
        cliente.setApellidoMaterno(dto.getApellidoMaterno());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setContrasenia(dto.getContrasenia());
        cliente.setEstadoUsuario(EstadoUsuario.ACTIVO);
        
        
        if (dto.getDireccion() != null) 
            cliente.setDireccion(toDireccionEntity(dto.getDireccion()));
        
        return cliente;
    }

    public static Videojuego toVideojuegoEntity(VideojuegoDTO dto){
        if(dto == null) return null;
        
        Videojuego entity = new Videojuego();
        
        if(dto.getIdVideojuego() != null)
            entity.setIdVideojuego(dto.getIdVideojuego());
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setExistencias(dto.getExistencias());
        entity.setHabilitado(dto.isHabilitado());
        entity.setDesarrollador(dto.getDesarrollador());
        entity.setFechaLanzamiento(dto.getFechaLanzamiento());
        entity.setPlataforma(dto.getPlataforma());
        entity.setUrlImagen(dto.getUrlImagen());
        
        if (dto.getCategorias() != null) {
            entity.setCategorias(dto.
                    getCategorias().
                    stream().
                    map(DTOMapeadores::toCategoriaEntity).
                    collect(Collectors.toList())
            );
        }
        
        if(dto.getResenas() != null)
            entity.setResenas(dto.
                    getResenas().
                    stream().
                    map(DTOMapeadores::toResenaEntity).
                    collect(Collectors.toList())
            );
        
        return entity;
    }
    
    public static Categoria toCategoriaEntity(CategoriaDTO dto){
        if (dto == null) return null;
        Categoria entity = new Categoria();
        
        if(dto.getIdCategoria() != null)
            entity.setIdCategoria(dto.getIdCategoria());
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
    
    public static Resena toResenaEntity(ResenaDTO dto){
        if (dto == null) return null;
        Resena entity = new Resena();
        
        if(dto.getIdResena() != null)
            entity.setIdResena(dto.getIdResena());
        
        entity.setCalificacion(dto.getCalificacion());
        entity.setComentario(dto.getComentario());
        entity.setFechaResena(dto.getFechaResena());
        
        if(dto.getIdCliente() != null){
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(dto.getIdCliente());
            entity.setCliente(cliente);
        }
        
        if(dto.getIdVideojuego() != null){
            Videojuego videojuego = new Videojuego();
            videojuego.setIdVideojuego(dto.getIdVideojuego());
            entity.setVideojuego(videojuego);
        }
        
        return entity;
    }
    
    public static Pedido toPedidoEntity(PedidoDTO dto){
        if (dto == null) return null;
        Pedido entity = new Pedido();
        
        if(dto.getIdPedido() != null)
            entity.setIdPedido(dto.getIdPedido());
        
        entity.setFechaPedido(dto.getFechaPedido());
        entity.setEstadoPedido(EstadoPedido.valueOf(dto.getEstadoPedido()));
        entity.setTotalPagado(dto.getTotalPagado());
        
        entity.setDireccionEnvio(toDireccionEnvioEntity(dto.getDireccionEnvio()));
        
        if(dto.getDetalles() != null)
            entity.setDetalles(toDetallePedidoEntityList(dto.getDetalles()));
        
        if(dto.getIdCliente() != null){
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(dto.getIdCliente());
            entity.setCliente(cliente);
        }
        
        if(dto.getPago() != null)
            entity.setPago(toPagoEntity(dto.getPago()));
        
        return entity;
    }
    
    public static DetallePedido toDetallePedidoEntity(DetallePedidoDTO dto){
        if (dto == null) return null;
        DetallePedido entity = new DetallePedido();
        
        if(dto.getIdDetallePedido() != null)
            entity.setIdDetallePedido(dto.getIdDetallePedido());
        
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());
        
        if(dto.getIdVideojuego() != null){
            Videojuego videojuego = new Videojuego();
            videojuego.setIdVideojuego(dto.getIdVideojuego());
            entity.setVideojuego(videojuego);
        }
        
        if(dto.getIdPedido() != null){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(dto.getIdPedido());
            entity.setPedido(pedido);
        }
        
        return entity;
    }
    
    public static List<DetallePedido> toDetallePedidoEntityList(List<DetallePedidoDTO> dtos) {
        if (dtos == null) return new ArrayList<>();
        return dtos.stream()
                .map(DTOMapeadores::toDetallePedidoEntity)
                .collect(Collectors.toList());
    }
    
    public static Pago toPagoEntity(PagoDTO dto){
        if (dto == null) return null;
        Pago entity = new Pago();
        
        if(dto.getIdPago() != null)
            entity.setIdPago(dto.getIdPago());
        
        entity.setMonto(dto.getMonto());
        entity.setMetodoPago(MetodoPago.valueOf(dto.getMetodoPago()));
        entity.setEstadoPago(EstadoPago.valueOf(dto.getEstadoPago()));
        entity.setFechaPago(dto.getFechaPago());
        
        return entity;
    }
    
    public static Carrito toCarritoEntity(CarritoDTO dto) {
        if (dto == null) return null;
        Carrito entity = new Carrito();
        
        if (dto.getIdCarrito() != null) {
            entity.setIdCarrito(dto.getIdCarrito());
        }
        entity.setFechaCreacion(dto.getFechaCreacion());
        if (dto.getItems() != null) {
            entity.setItems(dto.getItems().stream()
                    .map(DTOMapeadores::toItemCarritoEntity)
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    public static ItemCarrito toItemCarritoEntity(ItemCarritoDTO dto) {
        if (dto == null) return null;
        ItemCarrito entity = new ItemCarrito();

        if (dto.getIdItemCarrito() != null) {
            entity.setIdItemCarrito(dto.getIdItemCarrito());
        }
        entity.setCantidad(dto.getCantidad());

        if (dto.getIdCarrito() != null) {
            Carrito carrito = new Carrito();
            carrito.setIdCarrito(dto.getIdCarrito());
            entity.setCarrito(carrito);
        }

        if (dto.getIdVideojuego() != null) {
            Videojuego videojuego = new Videojuego();
            videojuego.setIdVideojuego(dto.getIdVideojuego());
            entity.setVideojuego(videojuego);
        }
        return entity;
    }
}
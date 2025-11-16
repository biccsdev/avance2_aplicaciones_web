
package joystickmx.itson.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DTO.CategoriaDTO;
import joystickmx.itson.DTO.DetallePedidoDTO;
import joystickmx.itson.DTO.DireccionDTO;
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
 * @author PC Gamer
 */
public class DTOMapeadores {
    
    
    

    public static Direccion toDireccionEntity(DireccionDTO dto) {
        if (dto == null) return null;
        
        Direccion entity = new Direccion();
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
        cliente.setCarrito(new Carrito());
        
        if (dto.getDireccion() != null) 
            cliente.setDireccion(toDireccionEntity(dto.getDireccion()));
        
        return cliente;
    }

    //pendiente los demas mappers
    public static Videojuego toVideojuegoEntity(VideojuegoDTO dto){
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
//        for(CategoriaDTO dto : dto.getCategorias())
//            categorias.add(toEntityCategoria(dto));
        entity.setCategorias(dto.
                getCategorias().
                stream().
                map(DTOMapeadores::toCategoriaEntity).
                collect(Collectors.toList())
        );
        
        if(dto.getResenas() != null)
            entity.setResenas(dto.getResenas().stream().map(DTOMapeadores::toResenaEntity).collect(Collectors.toList()));
        
        return entity;
    }
    
    public static Categoria toCategoriaEntity(CategoriaDTO dto){
        Categoria entity = new Categoria();
        
        if(dto.getIdCategoria() != null)
            entity.setIdCategoria(Long.valueOf(dto.getIdCategoria()));
        
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        // Quizás falte mapear los videojuegos de la categoría, aunque podría derivar en un mapeo infinito.
        return entity;
    }
    
    public static Resena toResenaEntity(ResenaDTO dto){
        Resena entity = new Resena();
        
        if(dto.getIdResena() != null)
            entity.setIdResena(Long.valueOf(dto.getIdResena()));
        
        entity.setCalificacion(dto.getCalificacion());
        entity.setComentario(dto.getComentario());
        entity.setFechaResena(dto.getFechaResena());
        
        // Si tiene el id del cliente
        if(dto.getIdCliente() != null){
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(Long.valueOf(dto.getIdCliente()));
            entity.setCliente(cliente);
        }
        
        // Si tiene el id del videojuego
        if(dto.getIdVideojuego() != null){
            Videojuego videojuego = new Videojuego();
            videojuego.setIdVideojuego(Long.valueOf(dto.getIdVideojuego()));
            entity.setVideojuego(videojuego);
        }
        
        return entity;
    }
    
    public static Pedido toPedidoEntity(PedidoDTO dto){
        Pedido entity = new Pedido();
        
        if(dto.getIdPedido() != null)
            entity.setIdPedido(Long.valueOf(dto.getIdPedido()));
        
        entity.setFechaPedido(dto.getFechaPedido());
        entity.setEstadoPedido(EstadoPedido.valueOf(dto.getEstadoPedido()));
        entity.setTotalPagado(dto.getTotalPagado());
        entity.setDireccionEnvio(toDireccionEnvioEntity(dto.getDireccionEnvio()));
        
        if(dto.getDetalles() != null)
            entity.setDetalles(toDetallePedidoEntityList(dto.getDetalles()));
        
        if(dto.getIdCliente() != null){
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(Long.valueOf(dto.getIdCliente()));
            entity.setCliente(cliente);
        }
        
        if(dto.getPago() != null)
            entity.setPago(toPagoEntity(dto.getPago()));
        
        return entity;
    }
    
    public static DetallePedido toDetallePedidoEntity(DetallePedidoDTO dto){
        DetallePedido entity = new DetallePedido();
        
        if(entity.getIdDetallePedido() != null)
            entity.setIdDetallePedido(Long.valueOf(dto.getIdDetallePedido()));
        
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());
        
        if(dto.getIdVideojuego() != null){
            Videojuego videojuego = new Videojuego();
            videojuego.setIdVideojuego(Long.valueOf(dto.getIdVideojuego()));
            entity.setVideojuego(videojuego);
        }
        
        if(dto.getIdPedido() != null){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(Long.valueOf(dto.getIdPedido()));
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
        Pago entity = new Pago();
        
        if(dto.getIdPago() != null)
            entity.setIdPago(Long.valueOf(dto.getIdPago()));
        
        entity.setMonto(dto.getMonto());
        entity.setMetodoPago(MetodoPago.valueOf(dto.getMetodoPago()));
        entity.setEstadoPago(EstadoPago.valueOf(dto.getEstadoPago()));
        entity.setFechaPago(dto.getFechaPago());
        
        return entity;
    }
}
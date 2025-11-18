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
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class Mapeadores {

    public static DireccionDTO toDireccionDTO(Direccion entity) {
        if (entity == null) {
            return null;
        }
        return new DireccionDTO(
                entity.getIdDireccion(),
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }

    public static DireccionDTO toDireccionEnvioDTO(DireccionEnvio entity) {
        if (entity == null) {
            return null;
        }
        return new DireccionDTO(
                null, // El ID de DireccionEnvio no pertenece aquí
                entity.getCalle(),
                entity.getNumero(),
                entity.getColonia()
        );
    }

    public static CategoriaDTO toCategoriaDTO(Categoria entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriaDTO(
                entity.getIdCategoria(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }

    public static List<CategoriaDTO> toCategoriaDTOList(List<Categoria> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(Mapeadores::toCategoriaDTO)
                .collect(Collectors.toList());
    }

    public static UsuarioDTO toUsuarioDTO(Usuario entity) {
        if (entity == null) {
            return null;
        }

        String rol = "cliente";
        if (entity instanceof Administrador) {
            rol = "admin";
        }

        return new UsuarioDTO(
                entity.getIdUsuario(),
                entity.getNombres(),
                entity.getApellidoPaterno(),
                entity.getApellidoMaterno(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getEstadoUsuario().toString(),
                toDireccionDTO(entity.getDireccion()),
                rol
        );
    }

    public static VideojuegoDTO toVideojuegoDTO(Videojuego entity) {
        if (entity == null) {
            return null;
        }
        return new VideojuegoDTO(
                entity.getIdVideojuego(),
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
        if (entity == null) {
            return null;
        }

        Long vjId = null;
        if (entity.getVideojuego() != null) {
            vjId = entity.getVideojuego().getIdVideojuego();
        }

        Long idCarrito = null;
        if (entity.getCarrito() != null) {
            idCarrito = entity.getCarrito().getIdCarrito();
        }

        return new ItemCarritoDTO(
                entity.getIdItemCarrito(),
                entity.getCantidad(),
                vjId,
                idCarrito
        );
    }

    public static List<ItemCarritoDTO> toItemCarritoDTOList(List<ItemCarrito> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(Mapeadores::toItemCarritoDTO)
                .collect(Collectors.toList());
    }

    public static CarritoDTO toCarritoDTO(Carrito entity) {
        if (entity == null) {
            return null;
        }

        List<ItemCarritoDTO> items = toItemCarritoDTOList(entity.getItems());

        return new CarritoDTO(
                entity.getIdCarrito(),
                entity.getFechaCreacion(),
                items
        );
    }

    public static PagoDTO toDTO(Pago entity) {
        if (entity == null) {
            return null;
        }
        return new PagoDTO(
                entity.getIdPago(),
                entity.getMonto(),
                entity.getMetodoPago().name(),
                entity.getEstadoPago().name(),
                entity.getFechaPago()
        );
    }

    public static DetallePedidoDTO toDetallePedidoDTO(DetallePedido entity) {
        if (entity == null) {
            return null;
        }

        Long vjId = null;
        if (entity.getVideojuego() != null) {
            vjId = entity.getVideojuego().getIdVideojuego();
        }

        Long idPedido = null;
        if (entity.getPedido() != null) {
            idPedido = entity.getPedido().getIdPedido();
        }

        return new DetallePedidoDTO(
                entity.getIdDetallePedido(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                vjId,
                idPedido
        );
    }

    public static List<DetallePedidoDTO> toDetallePedidoDTOList(List<DetallePedido> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(Mapeadores::toDetallePedidoDTO)
                .collect(Collectors.toList());
    }

    public static PedidoDTO toPedidoDTO(Pedido entity) {
        UsuarioDTO clienteDTO = toUsuarioDTO(entity.getCliente());

        return new PedidoDTO(
                entity.getIdPedido(),
                entity.getEstadoPedido().name(),
                entity.getTotalPagado(),
                entity.getFechaPedido(),
                toDireccionEnvioDTO(entity.getDireccionEnvio()), 
                toDetallePedidoDTOList(entity.getDetalles()), 
                toDTO(entity.getPago()),
                clienteDTO
        );
    }

    public static ResenaDTO toResenaDTO(Resena entity) {
        if (entity == null) {
            return null;
        }

        Long clienteId = null;
        if (entity.getCliente() != null) {
            clienteId = entity.getCliente().getIdUsuario();
        }

        Long idVideojuego = null;
        if (entity.getVideojuego() != null) {
            idVideojuego = entity.getVideojuego().getIdVideojuego();
        }

        return new ResenaDTO(
                entity.getIdResena(),
                entity.getCalificacion(),
                entity.getComentario(),
                entity.getFechaResena(),
                clienteId,
                idVideojuego
        );
    }

    public static List<ResenaDTO> toResenasDTOList(List<Resena> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(Mapeadores::toResenaDTO).collect(Collectors.toList());
    }
}

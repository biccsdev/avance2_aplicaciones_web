package joystickmx.itson.DAOS.Factory;

import joystickmx.itson.DAOS.AdministradorDAO;
import joystickmx.itson.DAOS.CarritoDAO;
import joystickmx.itson.DAOS.CategoriaDAO;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DAOS.DireccionDAO;
import joystickmx.itson.DAOS.PedidoDAO;
import joystickmx.itson.DAOS.ResenaDAO;
import joystickmx.itson.DAOS.UsuarioDAO;
import joystickmx.itson.DAOS.VideojuegoDAO;
import joystickmx.itson.interfaces.IAdministradorDAO;
import joystickmx.itson.interfaces.ICarritoDAO;
import joystickmx.itson.interfaces.ICategoriaDAO;
import joystickmx.itson.interfaces.IClienteDAO;
import joystickmx.itson.interfaces.IDireccionDAO;
import joystickmx.itson.interfaces.IPedidoDAO;
import joystickmx.itson.interfaces.IResenaDAO;
import joystickmx.itson.interfaces.IUsuarioDAO;
import joystickmx.itson.interfaces.IVideojuegoDAO;

/**
 *
 * @author PC WHITE WOLF
 */
public class FactoryDAO {
    
    public static IUsuarioDAO crearUsuarioDAO(){
        return new UsuarioDAO();
    }
    
    public static IAdministradorDAO crearAdministradorDAO(){
        return new AdministradorDAO();
    }
    
    public static IClienteDAO crearClienteDAO(){
        return new ClienteDAO();
    }
    
    public static IVideojuegoDAO crearVideojuegoDAO(){
        return new VideojuegoDAO();
    }
    
    public static IPedidoDAO crearPedidoDAO(){
        return new PedidoDAO();
    }
    
    public static ICarritoDAO crearCarritoDAO(){
        return new CarritoDAO();
    }
    
    public static ICategoriaDAO crearCategoriaDAO(){
        return new CategoriaDAO();
    }
    
    public static IDireccionDAO crearDireccionDAO(){
        return new DireccionDAO();
    }
    
    public static IResenaDAO crearResenaDAO(){
        return new ResenaDAO();
    }
}
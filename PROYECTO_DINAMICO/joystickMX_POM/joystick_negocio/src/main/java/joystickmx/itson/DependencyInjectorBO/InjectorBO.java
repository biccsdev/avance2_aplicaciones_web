package joystickmx.itson.DependencyInjectorBO;

import joystickmx.itson.BO.AdministradorBO;
import joystickmx.itson.BO.CarritoBO;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.BO.ClienteBO;
import joystickmx.itson.BO.PedidoBO;
import joystickmx.itson.BO.ResenaBO;
import joystickmx.itson.BO.UsuarioBO;
import joystickmx.itson.BO.VideojuegoBO;
import joystickmx.itson.DAOS.DependencyInjectorDAO.InjectorDAO;
import joystickmx.negocio.interfaces.IAdministradorBO;
import joystickmx.negocio.interfaces.ICarritoBO;
import joystickmx.negocio.interfaces.ICategoriaBO;
import joystickmx.negocio.interfaces.IClienteBO;
import joystickmx.negocio.interfaces.IPedidoBO;
import joystickmx.negocio.interfaces.IResenaBO;
import joystickmx.negocio.interfaces.IUsuarioBO;
import joystickmx.negocio.interfaces.IVideojuegoBO;

/**
 *
 * @author Ariel Eduardo Borbón Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class InjectorBO {
    
    public static IAdministradorBO buildAdiministradorBO(){
        return new AdministradorBO(InjectorDAO.crearAdministradorDAO());
    }
    
    public static ICarritoBO buildCarritoBO(){
        return  new CarritoBO(InjectorDAO.crearCarritoDAO());
    }
    
    public static ICategoriaBO buildCategoriaBO(){
        return new CategoriaBO(InjectorDAO.crearCategoriaDAO());
    }
    
    public static IClienteBO buildClienteBO(){
        return new ClienteBO(InjectorDAO.crearClienteDAO(), InjectorDAO.crearCarritoDAO());
    }
    
    public static IClienteBO buildClienteSinCarritoBO(){
        return new ClienteBO(InjectorDAO.crearClienteDAO(), null);
    }
    
    public static IPedidoBO buildPedidoBO(){
        return new PedidoBO(
                InjectorDAO.crearPedidoDAO(), 
                InjectorDAO.crearClienteDAO(), 
                InjectorDAO.crearCarritoDAO(), 
                InjectorDAO.crearVideojuegoDAO()
        );
    }
    
    public static IPedidoBO buildPedidoClienteBO(){
        return new PedidoBO(
                InjectorDAO.crearPedidoDAO(), 
                InjectorDAO.crearClienteDAO(), 
                null, 
                null
        );
    }
    
    public static IPedidoBO buildPedidoSinClienteCarritoVideojuegoBO(){
        return new PedidoBO(InjectorDAO.crearPedidoDAO(), null, null, null);
    }
    
    public static IResenaBO buildResenaBO(){
        return new ResenaBO(InjectorDAO.crearResenaDAO());
    }
    
    public static IUsuarioBO buildUsuarioBO(){
        return new UsuarioBO(InjectorDAO.crearUsuarioDAO());
    }
    
    public static IVideojuegoBO buildVideojuegoBO(){
        return new VideojuegoBO(InjectorDAO.crearVideojuegoDAO());
    }
}
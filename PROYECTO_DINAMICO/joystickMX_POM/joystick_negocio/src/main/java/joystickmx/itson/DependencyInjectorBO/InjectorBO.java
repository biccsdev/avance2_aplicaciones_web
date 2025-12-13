package joystickmx.itson.DependencyInjectorBO;

import joystickmx.itson.BO.AdministradorBO;
import joystickmx.itson.BO.CarritoBO;
import joystickmx.itson.BO.CategoriaBO;
import joystickmx.itson.BO.ClienteBO;
import joystickmx.itson.BO.PedidoBO;
import joystickmx.itson.BO.ResenaBO;
import joystickmx.itson.BO.UsuarioBO;
import joystickmx.itson.BO.VideojuegoBO;
import joystickmx.itson.DAOS.Factory.FactoryDAO;
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
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 * @author Victor Gerardo Torres García ID: 205869
 */
public class InjectorBO {
    
    public static IAdministradorBO buildAdiministradorBO(){
        return new AdministradorBO(FactoryDAO.crearAdministradorDAO());
    }
    
    public static ICarritoBO buildCarritoBO(){
        return  new CarritoBO(FactoryDAO.crearCarritoDAO());
    }
    
    public static ICategoriaBO buildCategoriaBO(){
        return new CategoriaBO(FactoryDAO.crearCategoriaDAO());
    }
    
    public static IClienteBO buildClienteBO(){
        return new ClienteBO(FactoryDAO.crearClienteDAO(), FactoryDAO.crearCarritoDAO());
    }
    
    public static IPedidoBO buildPedidoBO(){
        return new PedidoBO(FactoryDAO.crearPedidoDAO(), FactoryDAO.crearClienteDAO(), FactoryDAO.crearCarritoDAO(), FactoryDAO.crearVideojuegoDAO());
    }
    
    public static IResenaBO buildResenaBO(){
        return new ResenaBO(FactoryDAO.crearResenaDAO());
    }
    
    public static IUsuarioBO buildUsuarioBO(){
        return new UsuarioBO(FactoryDAO.crearUsuarioDAO());
    }
    
    public static IVideojuegoBO buildVideojuegoBO(){
        return new VideojuegoBO(FactoryDAO.crearVideojuegoDAO());
    }
}
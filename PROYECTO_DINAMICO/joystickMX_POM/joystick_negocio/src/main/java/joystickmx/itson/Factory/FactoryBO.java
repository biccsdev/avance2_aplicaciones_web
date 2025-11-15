/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.Factory;

import joystickmx.itson.BO.AdministradorBO;
import joystickmx.itson.BO.ClienteBO;
import joystickmx.itson.BO.DireccionBO;
import joystickmx.itson.BO.PedidoBO;
import joystickmx.itson.BO.ResenaBO;
import joystickmx.itson.BO.UsuarioBO;
import joystickmx.itson.BO.VideojuegoBO;
import joystickmx.itson.DAOS.AdministradorDAO;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DAOS.DireccionDAO;
import joystickmx.itson.DAOS.PedidoDAO;
import joystickmx.itson.DAOS.ResenaDAO;
import joystickmx.itson.DAOS.UsuarioDAO;
import joystickmx.itson.DAOS.VideojuegoDAO;

/**
 *
 * @author PC WHITE WOLF
 */
public class FactoryBO {

public static VideojuegoBO CrearVideojuegoBO() {
        return new VideojuegoBO(new VideojuegoDAO());
    }

    public static UsuarioBO CrearUsuarioBO() {
        return new UsuarioBO(new UsuarioDAO());
    }

    public static ClienteBO CrearClienteBO() {
        return new ClienteBO(new ClienteDAO());
    }
    
    public static AdministradorBO CrearAdministradorBO() {
        return new AdministradorBO(new AdministradorDAO());
    }

    public static DireccionBO CrearDireccionBO() {
        return new DireccionBO(new DireccionDAO());
    }
    
    public static ResenaBO CrearResenaBO() {
        return new ResenaBO(new ResenaDAO());
    }
    
    public static PedidoBO CrearPedidoBO() {
        return new PedidoBO(new PedidoDAO());
    }


}

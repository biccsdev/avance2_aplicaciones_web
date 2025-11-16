/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;
import joystickmx.itson.entidades.Usuario;

/**
 *
 * @author PC Gamer
 */
public interface IUsuarioDAO {

    void crearUsuario(Usuario usuario) throws PersistenciaException;

    Usuario actualizar(Usuario usuario) throws PersistenciaException;

    Usuario buscarPorId(Long idUsuario) throws PersistenciaException;

    Usuario buscarPorEmail(String email) throws PersistenciaException;

    void activarUsuario(String email) throws PersistenciaException;

    void desactivarUsuario(String email) throws PersistenciaException;

    void eliminarUsuario(String email) throws PersistenciaException;

    Usuario modificarDireccion(String email, Direccion datosNuevos) throws PersistenciaException;
}

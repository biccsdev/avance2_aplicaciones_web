/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package joystickmx.itson.interfaces;

import java.util.List;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Administrador;

/**
 *
 * @author PC Gamer
 */
public interface IAdministradorDAO {

    void crearAdministrador(Administrador administrador) throws PersistenciaException;

    Administrador actualizarAdministrador(Administrador administrador) throws PersistenciaException;

    Administrador buscarPorId(Long idAdministrador) throws PersistenciaException;

    Administrador buscarPorEmail(String email) throws PersistenciaException;

    List<Administrador> buscarTodos() throws PersistenciaException;

    List<Administrador> buscarActivos() throws PersistenciaException;
}
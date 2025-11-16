/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package joystickmx.itson.interfaces;

import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.entidades.Direccion;

/**
 *
 * @author PC Gamer
 */
public interface IDireccionDAO {

    void crearDireccion(Direccion direccion) throws PersistenciaException;

    Direccion actualizarDireccion(Direccion direccion) throws PersistenciaException;

    Direccion buscarPorId(Long idDireccion) throws PersistenciaException;

    void eliminarDireccion(Long idDireccion) throws PersistenciaException;
}


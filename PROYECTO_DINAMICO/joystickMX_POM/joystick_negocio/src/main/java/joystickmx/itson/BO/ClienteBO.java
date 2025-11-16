/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DAOS.ClienteDAO;
import joystickmx.itson.DTO.UsuarioDTO;
import joystickmx.itson.DTO.UsuarioRegistroDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.DTOMapeadores;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.itson.entidades.Cliente;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class ClienteBO {
    
    private final ClienteDAO clienteDAO;

    public ClienteBO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void crearCliente(UsuarioRegistroDTO dto) throws NegocioException {
        try {
            Cliente nuevoCliente = DTOMapeadores.toEntity(dto);
            this.clienteDAO.crearCliente(nuevoCliente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar cliente: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarUsuariosActivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesActivos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes activos: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarUsuariosInactivos() throws NegocioException {
        try {
            return this.clienteDAO.buscarClientesInactivos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes inactivos: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO actualizarCliente(UsuarioDTO dto) throws NegocioException {
        try {
            Cliente cliente = Mapeadores.toEntity(dto);
            return Mapeadores.toDTO(this.clienteDAO.actualizarCliente(cliente));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar cliente: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorId(Long idCliente) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.clienteDAO.buscarPorId(idCliente));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente por ID: " + e.getMessage(), e);
        }
    }

    public UsuarioDTO buscarPorEmail(String email) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.clienteDAO.buscarPorEmail(email));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar cliente por email: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarTodos() throws NegocioException {
        try {
            return this.clienteDAO.buscarTodos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los clientes: " + e.getMessage(), e);
        }
    }

    public List<UsuarioDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return this.clienteDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar clientes por nombre: " + e.getMessage(), e);
        }
    }
}

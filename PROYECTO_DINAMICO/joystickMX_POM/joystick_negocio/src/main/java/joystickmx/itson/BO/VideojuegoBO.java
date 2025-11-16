/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DAOS.VideojuegoDAO;
import joystickmx.itson.DTO.VideojuegoDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class VideojuegoBO {
    
    private final VideojuegoDAO videojuegoDAO;

    public VideojuegoBO(VideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    public void crearVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            this.videojuegoDAO.persistir(Mapeadores.toEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear videojuego: " + e.getMessage(), e);
        }
    }

    public VideojuegoDTO actualizarVideojuego(VideojuegoDTO dto) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.videojuegoDAO.actualizar(Mapeadores.toEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar videojuego: " + e.getMessage(), e);
        }
    }

    public void habilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            this.videojuegoDAO.habilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar videojuego: " + e.getMessage(), e);
        }
    }

    public void deshabilitarVideojuego(Long idVideojuego) throws NegocioException {
        try {
            this.videojuegoDAO.deshabilitarVideojuego(idVideojuego);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar videojuego: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarTodosLosVideojuegos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarTodosLosVideojuegos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todos los videojuegos: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarVideojuegosActivos() throws NegocioException {
        try {
            return this.videojuegoDAO.buscarVideojuegosActivos().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuegos activos: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorRangoDePrecio(Float min, Float max) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorRangoDePrecio(min, max).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por rango de precio: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorCategoria(Long idCategoria) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorCategoria(idCategoria).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por categoría: " + e.getMessage(), e);
        }
    }

    public List<VideojuegoDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            return this.videojuegoDAO.buscarPorNombre(nombre).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar por nombre: " + e.getMessage(), e);
        }
    }

    public VideojuegoDTO buscarPorId(Long idVideojuego) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.videojuegoDAO.buscarPorId(idVideojuego));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar videojuego por ID: " + e.getMessage(), e);
        }
    }
    
    
    
    
    
    
}

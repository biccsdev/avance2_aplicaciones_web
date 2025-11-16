/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.BO;

import java.util.List;
import java.util.stream.Collectors;
import joystickmx.itson.DAOS.ResenaDAO;
import joystickmx.itson.DTO.ResenaDTO;
import joystickmx.itson.Excepciones.PersistenciaException;
import joystickmx.itson.Mappers.Mapeadores;
import joystickmx.negocio.exception.NegocioException;

/**
 *
 * @author PC Gamer
 * @author biccs
 */
public class ResenaBO {
    
    private final ResenaDAO resenaDAO;

    public ResenaBO(ResenaDAO resenaDAO) {
        this.resenaDAO = resenaDAO;
    }

    public void crearResena(ResenaDTO dto) throws NegocioException {
        try {
            this.resenaDAO.crearResena(Mapeadores.toEntity(dto));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al crear reseña: " + e.getMessage(), e);
        }
    }

    public ResenaDTO actualizarResena(ResenaDTO dto) throws NegocioException {
        try {
            return Mapeadores.toDTO(this.resenaDAO.actualizarResena(Mapeadores.toEntity(dto)));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar reseña: " + e.getMessage(), e);
        }
    }

    public void eliminarResena(Long idResena) throws NegocioException {
        try {
            this.resenaDAO.eliminarResena(idResena);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar reseña: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarPorVideojuego(Long idVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorVideojuego(idVideojuego).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarPorCliente(Long idCliente) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorCliente(idCliente).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por cliente: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarTodas() throws NegocioException {
        try {
            return this.resenaDAO.buscarTodas().stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar todas las reseñas: " + e.getMessage(), e);
        }
    }
}
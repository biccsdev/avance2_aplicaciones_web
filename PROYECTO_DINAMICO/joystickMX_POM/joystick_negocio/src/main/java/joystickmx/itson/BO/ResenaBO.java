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
 */
public class ResenaBO {
    
    private final ResenaDAO resenaDAO;

    public ResenaBO(ResenaDAO resenaDAO) {
        this.resenaDAO = resenaDAO;
    }

    //CREAR RESEÑA PENDIENTE
    

    public void eliminarResena(Long idResena) throws NegocioException {
        try {
            this.resenaDAO.eliminarResena(idResena);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar reseña: " + e.getMessage(), e);
        }
    }

    public List<ResenaDTO> buscarPorVideojuego(String nombreVideojuego) throws NegocioException {
        try {
            return this.resenaDAO.buscarPorVideojuego(nombreVideojuego).stream()
                    .map(Mapeadores::toDTO)
                    .collect(Collectors.toList());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar reseñas por videojuego: " + e.getMessage(), e);
        }
    }
}
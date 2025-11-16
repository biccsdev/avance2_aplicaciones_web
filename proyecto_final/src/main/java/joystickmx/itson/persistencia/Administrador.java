/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joystickmx.itson.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author sonic
 */
@Entity
public class Administrador extends Usuario {
    
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idAdministrador;
//
//    public Long getIdAdministrador() {
//        return idAdministrador;
//    }
//
//    public void setIdAdministrador(Long idAdministrador) {
//        this.idAdministrador = idAdministrador;
//    }

    

}

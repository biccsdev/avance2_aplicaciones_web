package joystickmx.itson.DTO;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class DireccionDTO {
    // Agregué el ID porque es una tabla en la bd también
    private Long idDireccion;
    private String calle;
    private String numero;
    private String colonia;

    public DireccionDTO() {}

    public DireccionDTO(
            Long idDireccion, 
            String calle, 
            String numero, 
            String colonia
    ) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }
    
    public DireccionDTO(String calle, String numero, String colonia) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public Long getIdDireccion() {return idDireccion;}

    public void setIdDireccion(Long idDireccion) {this.idDireccion = idDireccion;}
    
    public String getCalle() {return calle;}

    public void setCalle(String calle) {this.calle = calle;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getColonia() {return colonia;}

    public void setColonia(String colonia) {this.colonia = colonia;}
}
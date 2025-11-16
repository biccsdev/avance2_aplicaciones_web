package joystickmx.itson.DTO;

/**
 *
 * @author PC Gamer
 */
public class DireccionDTO {
    // Agregué el ID porque es una tabla en la bd también
    private String idDireccion;
    private String calle;
    private String numero;
    private String colonia;

    public DireccionDTO() {}

    public DireccionDTO(
            String idDireccion, 
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

    public String getIdDireccion() {return idDireccion;}

    public void setIdDireccion(String idDireccion) {this.idDireccion = idDireccion;}
    
    public String getCalle() {return calle;}

    public void setCalle(String calle) {this.calle = calle;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getColonia() {return colonia;}

    public void setColonia(String colonia) {this.colonia = colonia;}
}
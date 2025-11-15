package joystickmx.itson.DTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * PagoDTO - Data Transfer Object para Pago
 *
 * Se usa para transferir información de pagos.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class PagoDTO {
    
    private String idPago;
    private Float monto;
    private String metodoPago; // String del enum
    private String estadoPago; 
    private LocalDateTime fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(String idPago, Float monto, String metodoPago, String estadoPago, LocalDateTime fechaPago) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }


    
    
    
    
    
    
}
package joystickmx.itson.DTO;
import java.time.LocalDate;
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
    
    /**
     * Declaración de atributos de pago
     */
    private float monto;
    private String metodoPago;
    private LocalDate fechaPago;
    private String estadoPago;

    /**
     * Método constructor para instanciar la clase PagoDTO
     * @param monto Representa el monto del pago
     * @param metodoPago Representa el método de pago
     * @param fechaPago Representa la fecha en que se realizó el pago
     * @param estadoPago Representa el estado del pago
     */
    public PagoDTO(float monto, String metodoPago, LocalDate fechaPago, String estadoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
    }

    /**
     * Getters para cada atributo de la clase
     */
    public float getMonto() {
        return monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }
    
}
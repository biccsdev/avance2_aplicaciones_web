package joystickmx.itson.DTO;
/**
 * DetallePedidoDTO - Data Transfer Object para DetallePedido
 *
 * Se usa para transferir información sobre los detalles de un pedido.
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
public class DetallePedidoDTO {
    
    /**
     * Declaración de atributos de detalle de pedido
     */
    private int cantidad;
    private float importe;
    private float precioUnitario;

    /**
     * Método constructor para instanciar la clase DetallePedidoDTO
     * @param cantidad Representa la cantidad de productos en el pedido
     * @param importe Representa el importe del pedido
     * @param precioUnitario Representa el precio unitario de cada producto del pedido
     */
    public DetallePedidoDTO(int cantidad, float importe, float precioUnitario) {
        this.cantidad = cantidad;
        this.importe = importe;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Getters para cada atributo de la clase
     */
    public int getCantidad() {
        return cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }
    
}
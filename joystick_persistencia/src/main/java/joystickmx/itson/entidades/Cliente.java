package joystickmx.itson.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ariel Eduardo Borbon Izaguirre ID: 00000252116
 * @author Sebastián Bórquez Huerta ID: 00000252115
 * @author Leonardo Flores Leyva ID: 00000252390
 * @author Yuri Germán García López ID: 00000252583
 */
@Entity
@Table(name = "Clientes")
@DiscriminatorValue("cliente")
public class Cliente extends Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Carrito carrito;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Resena> resenas;

    public Long getIdCliente() {return idCliente;}

    public void setIdCliente(Long idCliente) {this.idCliente = idCliente;}

    public List<Pedido> getPedidos() {return pedidos;}

    public void setPedidos(List<Pedido> pedidos) {this.pedidos = pedidos;}

    public Carrito getCarrito() {return carrito;}

    public void setCarrito(Carrito carrito) {this.carrito = carrito;}

    public List<Resena> getResenas() {return resenas;}

    public void setResenas(List<Resena> resenas) {this.resenas = resenas;}
}
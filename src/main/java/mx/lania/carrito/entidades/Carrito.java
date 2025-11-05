package mx.lania.carrito.entidades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @Column(name = "id_carrito")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCarrito;

    @Column(name = "id_cliente", nullable = false)
    private UUID idCliente;

    @Column(name = "fecha_creacion")
    private LocalDateTime creacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime actualizacion;
    
    @OneToMany(mappedBy = "carrito")
    private List<DetalleCarrito> items;

    public UUID getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(UUID idCarrito) {
        this.idCarrito = idCarrito;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idUsuario) {
        this.idCliente = idUsuario;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    public LocalDateTime getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(LocalDateTime actualizacion) {
        this.actualizacion = actualizacion;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }

    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }
}

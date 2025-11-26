package mx.lania.carrito.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_carritos")
public class DetalleCarrito {
    @Id
    @Column(name = "id_detalle_carrito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCarrito;
    @ManyToOne()
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    public Long getIdDetalleCarrito() {
        return idDetalleCarrito;
    }

    public void setIdDetalleCarrito(Long idDetalleCarrito) {
        this.idDetalleCarrito = idDetalleCarrito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setIdCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    
}

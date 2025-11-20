package mx.lania.carrito.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre es una propiedad requerida")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;
    @Column(name = "descripcion", length = 255)
    @NotBlank(message = "La descripcion es una propiedad requerida")
    @Size(max = 255, message = "La descripcion no puede exceder 255 caracteres")
    private String descripcion;
    @Column(name = "precio")
    @Positive(message = "El precio debe ser un valor positivo")
    private BigDecimal precio;
    @Column(name = "cantidad_disponible", nullable = false)
    @Positive(message = "La cantidad disponible debe ser un valor positivo")
    private Integer cantidadDisponible;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long iProducto) {
        this.idProducto = iProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
}

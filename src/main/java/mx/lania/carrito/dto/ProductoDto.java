package mx.lania.carrito.dto;

import java.math.BigDecimal;

public class ProductoDto {
    private Long iProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidadDisponible;

    public Long getiProducto() {
        return iProducto;
    }

    public void setiProducto(Long iProducto) {
        this.iProducto = iProducto;
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
    
    
}

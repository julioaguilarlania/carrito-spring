package mx.lania.carrito.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CarritoDto {
    private UUID idCarrito;
    private UUID idUsuario;
    private LocalDateTime creacion;
    private LocalDateTime actualizacion;
    private List<DetalleCarritoDto> items;

    public UUID getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(UUID idCarrito) {
        this.idCarrito = idCarrito;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<DetalleCarritoDto> getItems() {
        return items;
    }

    public void setItems(List<DetalleCarritoDto> items) {
        this.items = items;
    }
}

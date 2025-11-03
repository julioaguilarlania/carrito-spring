package mx.lania.carrito.entidades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Carrito {
    UUID idCarrito;
    UUID idUsuario;
    LocalDateTime creacion;
    LocalDateTime actualizacion;
    List<DetalleCarrito> items;
}

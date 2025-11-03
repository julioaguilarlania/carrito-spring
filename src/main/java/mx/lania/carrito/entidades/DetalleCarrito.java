package mx.lania.carrito.entidades;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleCarrito {
    Long idDetalleCarrito;
    UUID idCarrito;
    Long idProducto;
    Integer cantidad;
    BigDecimal precio;
}

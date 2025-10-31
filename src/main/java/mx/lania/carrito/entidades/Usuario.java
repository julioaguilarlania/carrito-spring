package mx.lania.carrito.entidades;

import java.time.Instant;
import java.util.UUID;

public class Usuario {
    UUID idUsuario;
    String email;
    String passwordHash;
    Boolean activo;
    Instant ultimoAcceso;
}

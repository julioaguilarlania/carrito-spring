package mx.lania.carrito.entidades;

import java.time.Instant;
import java.util.UUID;

public class Usuario {
    private UUID idUsuario;
    private String email;
    private String passwordHash;
    private Boolean activo;
    private Instant ultimoAcceso;

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Instant getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Instant ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }
    
    
}

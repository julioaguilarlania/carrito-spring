package mx.lania.carrito.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.lania.carrito.entidades.Usuario;

public interface RepositorioUsuarios extends JpaRepository<Usuario, UUID> {
    
}

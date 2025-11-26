package mx.lania.carrito.repositorios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.lania.carrito.entidades.Carrito;

public interface RepositorioCarritos extends JpaRepository<Carrito, UUID> {

    Optional<Carrito> findByIdCliente(UUID idCliente);
    
}
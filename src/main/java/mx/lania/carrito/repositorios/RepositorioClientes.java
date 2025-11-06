package mx.lania.carrito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.lania.carrito.entidades.Cliente;

public interface RepositorioClientes extends JpaRepository<Cliente, Long> {
    
}

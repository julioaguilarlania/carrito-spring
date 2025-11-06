package mx.lania.carrito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.lania.carrito.entidades.Producto;

public interface RepositorioProductos extends JpaRepository<Producto, Long> {
    
}
package mx.lania.carrito.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.lania.carrito.entidades.DetalleCarrito;

public interface RepositorioDetallesCarrito extends JpaRepository<DetalleCarrito, Long>{
    
}

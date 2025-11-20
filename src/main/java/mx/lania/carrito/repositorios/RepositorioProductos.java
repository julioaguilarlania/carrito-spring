package mx.lania.carrito.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.lania.carrito.entidades.Producto;

public interface RepositorioProductos extends JpaRepository<Producto, Long> {
    
    @Query(value = "SELECT * FROM PRODUCTOS WHERE DESCRIPCION ILIKE :termino", nativeQuery = true)
    public List<Producto> buscarPorDescripcion(@Param("termino") String termino);

    public List<Producto> findByDescripcionContaining(String termino);
}
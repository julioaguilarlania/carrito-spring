package mx.lania.carrito.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.lania.carrito.dto.ProductoDto;
import mx.lania.carrito.entidades.Producto;
import mx.lania.carrito.mappers.MapperProducto;
import mx.lania.carrito.repositorios.RepositorioProductos;

@Service
public class ServicioProductos {

    private final MapperProducto mapper;
    private final RepositorioProductos repo;

    public ServicioProductos(RepositorioProductos repo, MapperProducto mapper) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public List<ProductoDto> findAll() {
        return repo.findAll().stream()
            .map(mapper::toDto)
            .toList();
    }

    public Optional<ProductoDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    public ProductoDto create(ProductoDto productoDto) {
        Producto producto = mapper.toEntity(productoDto);
        Producto guardado = repo.save(producto);
        return mapper.toDto(guardado);
    }

    public ProductoDto update(Long id, ProductoDto productoDto) {
        Optional<Producto> productoExistente = repo.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = mapper.toEntity(productoDto);
            producto.setIdProducto(id);
            Producto actualizado = repo.save(producto);
            return mapper.toDto(actualizado);
        } else {
            throw new IllegalArgumentException("Id de producto no encontrado");
        }
    }

    public boolean delete(Long id) {
        Optional<Producto> productoExistente = repo.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            repo.save(producto);
            return true;
        } else {
            return false;
        }
    }
}

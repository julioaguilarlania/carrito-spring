package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;

import mx.lania.carrito.dto.ProductoDto;
import mx.lania.carrito.entidades.Producto;

@Mapper
public interface MapperProducto {
    
    ProductoDto toDto(Producto producto);
    Producto toEntity(ProductoDto productoDto);
}

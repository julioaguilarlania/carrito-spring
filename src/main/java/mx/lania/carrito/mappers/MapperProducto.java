package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.lania.carrito.dto.ProductoDto;
import mx.lania.carrito.entidades.Producto;

@Mapper
public interface MapperProducto {
    
    ProductoDto toDto(Producto producto);
    
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Producto toEntity(ProductoDto productoDto);
}

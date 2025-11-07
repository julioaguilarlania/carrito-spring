package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.lania.carrito.dto.DetalleCarritoDto;
import mx.lania.carrito.entidades.DetalleCarrito;

@Mapper
public interface MapperDetalleCarrito {
    
    DetalleCarritoDto toDto(DetalleCarrito detalle);
    @Mapping(target = "idCarrito", ignore = true)
    DetalleCarrito toEntity(DetalleCarritoDto detalleDto);
}

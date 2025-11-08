package mx.lania.carrito.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import mx.lania.carrito.dto.DetalleCarritoDto;
import mx.lania.carrito.entidades.DetalleCarrito;

@Mapper
public interface MapperDetalleCarrito {
    
    MapperDetalleCarrito INSTANCE = Mappers.getMapper(MapperDetalleCarrito.class);

    DetalleCarritoDto toDto(DetalleCarrito detalle);

    @Mapping(target = "idCarrito", ignore = true)
    DetalleCarrito toEntity(DetalleCarritoDto detalleDto);

    List<DetalleCarritoDto> toDtoList(List<DetalleCarrito> detalles);
    List<DetalleCarrito> toEntityList(List<DetalleCarritoDto> detallesDto);
}

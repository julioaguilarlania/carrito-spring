package mx.lania.carrito.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import mx.lania.carrito.dto.CarritoDto;
import mx.lania.carrito.dto.DetalleCarritoDto;
import mx.lania.carrito.entidades.Carrito;
import mx.lania.carrito.entidades.DetalleCarrito;

@Mapper
public interface MapperCarrito {
    
    @Mapping(source = "items", target = "items", qualifiedByName = "detallesToDto")
    CarritoDto toDto(Carrito Carrito);

    @Mapping(target = "idCliente", ignore = true)
    @Mapping(source = "items", target = "items", qualifiedByName = "detallesToEntity")
    Carrito toEntity(CarritoDto CarritoDto);

    @Named("detallesToDto")
    default List<DetalleCarritoDto> detallesToDto(List<DetalleCarrito> detalles) {
        MapperDetalleCarrito mapper = MapperDetalleCarrito.INSTANCE;
        return mapper.toDtoList(detalles);
    }

    @Named("detallesToEntity")
    default List<DetalleCarrito> detallesToEntity(List<DetalleCarritoDto> detallesDto) {
        MapperDetalleCarrito mapper = MapperDetalleCarrito.INSTANCE;
        return mapper.toEntityList(detallesDto);
    }

}

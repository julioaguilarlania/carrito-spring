package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.lania.carrito.dto.CarritoDto;
import mx.lania.carrito.entidades.Carrito;

@Mapper
public interface MapperCarrito {
    
    CarritoDto toDto(Carrito Carrito);

    @Mapping(target = "idCliente", ignore = true)
    Carrito toEntity(CarritoDto CarritoDto);

}

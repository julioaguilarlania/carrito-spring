package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.lania.carrito.dto.ClienteDto;
import mx.lania.carrito.entidades.Cliente;

@Mapper
public interface MapperCliente {

    ClienteDto toDto(Cliente entidad);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Cliente toEntity(ClienteDto dto);
}

package mx.lania.carrito.mappers;

import org.mapstruct.Mapper;

import mx.lania.carrito.dto.ClienteDto;
import mx.lania.carrito.entidades.Cliente;

@Mapper
public interface MapperCliente {

    ClienteDto toDto(Cliente entidad);

    Cliente toEntity(ClienteDto dto);
}

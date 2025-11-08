package mx.lania.carrito.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import mx.lania.carrito.dto.ClienteDto;
import mx.lania.carrito.entidades.Cliente;
import mx.lania.carrito.mappers.MapperCliente;
import mx.lania.carrito.repositorios.RepositorioClientes;

@Service
public class ServicioClientes {
    
    private final RepositorioClientes repo;
    private final MapperCliente mapper;

    public ServicioClientes(RepositorioClientes repo, MapperCliente mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ClienteDto> findAll() {
        return repo.findAll()
                   .stream()
                   .map(mapper::toDto)
                   .collect(Collectors.toList());
    }

    public Optional<ClienteDto> findById(UUID id) {
        return repo.findById(id).map(mapper::toDto);
    }

    public ClienteDto create(ClienteDto dto) {
        Cliente cliente = mapper.toEntity(dto);
        Cliente guardado = repo.save(cliente); 
        return mapper.toDto(guardado);
    }

    public ClienteDto update(UUID id, ClienteDto clienteDto) {
        Optional<Cliente> clienteExistente = repo.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = mapper.toEntity(clienteDto);
            cliente.setIdCliente(clienteDto.getIdCliente());
            Cliente actualizado = repo.save(cliente);
            return mapper.toDto(actualizado);
        } else {
            throw new IllegalArgumentException("Id de cliente no encontrado");
        }
    }

    public boolean delete(UUID id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

}

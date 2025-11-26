package mx.lania.carrito.servicios;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mx.lania.carrito.dto.CarritoDto;
import mx.lania.carrito.dto.DetalleCarritoDto;
import mx.lania.carrito.entidades.Carrito;
import mx.lania.carrito.entidades.DetalleCarrito;
import mx.lania.carrito.entidades.Producto;
import mx.lania.carrito.entidades.Usuario;
import mx.lania.carrito.mappers.MapperCarrito;
import mx.lania.carrito.mappers.MapperDetalleCarrito;
import mx.lania.carrito.repositorios.RepositorioCarritos;
import mx.lania.carrito.repositorios.RepositorioDetallesCarrito;
import mx.lania.carrito.repositorios.RepositorioProductos;
import mx.lania.carrito.repositorios.RepositorioUsuarios;

@Service
public class ServicioCarritos {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioCarritos.class);

    private RepositorioCarritos repositorioCarritos;
    private RepositorioDetallesCarrito repositorioDetallesCarrito;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioProductos repositorioProductos;

    private MapperCarrito mapper;
    private MapperDetalleCarrito mapperDetalle;

    public ServicioCarritos(
        RepositorioCarritos repositorioCarritos,
        RepositorioDetallesCarrito repoDetalles,
        RepositorioUsuarios repositorioUsuarios,
        RepositorioProductos repositorioProductos,
        MapperCarrito mapper,
        MapperDetalleCarrito mapperDetalle
    , RepositorioDetallesCarrito repositorioDetallesCarrito) {
        this.repositorioCarritos = repositorioCarritos;
        this.repositorioDetallesCarrito = repositorioDetallesCarrito;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioProductos = repositorioProductos;
        this.mapper = mapper;
        this.mapperDetalle = mapperDetalle;
    }

    @PreAuthorize("hasAuthority('CLIENTE')")
    public Optional<CarritoDto> obtenerCarrito() {
        LOGGER.debug("obtenerCarrito");
        return obtenerCarritoEntity().map(mapper::toDto);
    }

    private Optional<Carrito> obtenerCarritoEntity() {
        LOGGER.debug("obtenerCarritoEntity()");
        Optional<String> emailUsuario = obtenerUsuarioAutenticado();
        if (emailUsuario.isPresent()) {
            Optional<Usuario> usuario = repositorioUsuarios.findByEmail(emailUsuario.get());
            if (usuario.isPresent()) {
                UUID idUsuario = usuario.get().getIdUsuario();
                return repositorioCarritos.findByIdCliente(idUsuario);
            }
            LOGGER.warn("No se encontro el usuario con nombre {}", emailUsuario.get());
        }
        return Optional.empty();
    }

    @PreAuthorize("hasAuthority('CLIENTE')")
    public CarritoDto crearCarrito(CarritoDto carritoDto) {
        LOGGER.debug("guardarCarrito");
        Optional<String> emailUsuario = obtenerUsuarioAutenticado();
        Carrito carrito = mapper.toEntity(carritoDto);
        if (emailUsuario.isPresent()) {
            Optional<Usuario> usuario = repositorioUsuarios.findByEmail(emailUsuario.get());
            if (usuario.isPresent()) {
                LOGGER.debug("Usuario encontrado: {} {}", usuario.get().getIdUsuario(), usuario.get().getEmail());
                UUID idUsuario = usuario.get().getIdUsuario();
                carrito.setIdCliente(idUsuario);
                carrito.setCreacion(LocalDateTime.now());
                carrito.setActualizacion(LocalDateTime.now());
                for (var item : carrito.getItems()) {
                    LOGGER.trace("producto {}", item.getIdProducto());
                    Optional<Producto> opProducto = repositorioProductos.findById(item.getIdProducto());
                    if (opProducto.isPresent()) {
                        item.setPrecio(opProducto.get().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
                    }
                }
            } else {
                LOGGER.warn("No se encontro el usuario con nombre {}", emailUsuario.get());
                throw new IllegalArgumentException("Usuario no encontrado");
            }
        }
        return mapper.toDto(repositorioCarritos.save(carrito));
    }

    public DetalleCarritoDto agregarProducto(DetalleCarritoDto itemDto) {
        Optional<Carrito> carrito = obtenerCarritoEntity();
        if (carrito.isPresent()) {
            DetalleCarrito item = mapperDetalle.toEntity(itemDto);
            LOGGER.trace("producto {}", item.getIdProducto());
            Optional<Producto> opProducto = repositorioProductos.findById(item.getIdProducto());
            if (opProducto.isPresent()) {
                item.setPrecio(opProducto.get().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
            }
            item.setIdCarrito(carrito.get());
            return mapperDetalle.toDto(repositorioDetallesCarrito.save(item));
        }
        else {
            throw new IllegalStateException("Este usuario no tiene un carrito en este momento");
        }
    }

    public boolean quitarProducto(DetalleCarritoDto itemDto) {
        if (itemDto.getIdDetalleCarrito() != null && itemDto.getIdDetalleCarrito() > 0) {
            repositorioDetallesCarrito.deleteById(itemDto.getIdDetalleCarrito());
            return true;
        }
        else {
            // TODO: quitar item de producto del carrito actual
            return false;
        }
    }

    private Optional<String> obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() ) {
            return Optional.of(auth.getName());
        }
        LOGGER.warn("No hay usuario autenticado");
        return Optional.empty();
    }

}

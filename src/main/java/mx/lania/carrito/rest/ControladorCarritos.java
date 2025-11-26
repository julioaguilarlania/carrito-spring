package mx.lania.carrito.rest;


import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mx.lania.carrito.dto.CarritoDto;
import mx.lania.carrito.dto.DetalleCarritoDto;
import mx.lania.carrito.servicios.ServicioCarritos;

@RestController
@RequestMapping("/api/carritos")
public class ControladorCarritos {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ControladorCarritos.class);
    private ServicioCarritos servicioCarritos;

    public ControladorCarritos(ServicioCarritos servicioCarritos) {
        this.servicioCarritos = servicioCarritos;
    }

    @PostMapping
    public ResponseEntity<CarritoDto> crearCarrito(@RequestBody CarritoDto carritoDto) {
        LOGGER.debug("crearCarrito()");
        try {
            CarritoDto creado = servicioCarritos.crearCarrito(carritoDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getIdCarrito())
                .toUri();
            return ResponseEntity.created(location).body(creado);
        }
        catch(Exception ex) {
            LOGGER.error("Error al crear el carrito", ex);
            return ResponseEntity.badRequest()
                .header("ERROR", ex.getMessage().replaceAll("\\R", " "))
                .build();
        }
        
    }

    @PostMapping("/detalles")
    public ResponseEntity<DetalleCarritoDto> agregarProducto(@RequestBody DetalleCarritoDto detalle) {
        try {
            DetalleCarritoDto detalleGuardado = servicioCarritos.agregarProducto(detalle);
            return ResponseEntity.ok(detalleGuardado);
        }
        catch(Exception ex) {
            LOGGER.error("Error al agregar producto al carrito", ex);
            return ResponseEntity.badRequest()
                .build();
        }
    }

    @DeleteMapping("/detalles/{id}")
    public ResponseEntity quitarProducto(@PathVariable Long idDetalle, @RequestBody DetalleCarritoDto detalle) {
        try {
            if (idDetalle != null && idDetalle > 0) {
                detalle.setIdDetalleCarrito(idDetalle);
            }
            boolean eliminado = servicioCarritos.quitarProducto(detalle);
            if (eliminado) {
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch(Exception ex) {
            LOGGER.error("Error al agregar producto al carrito", ex);
            return ResponseEntity.badRequest()
                .build();
        }
    }
    
}

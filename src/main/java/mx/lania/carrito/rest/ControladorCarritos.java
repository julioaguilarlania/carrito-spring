package mx.lania.carrito.rest;


import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mx.lania.carrito.dto.CarritoDto;
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
        catch(Exception e) {
            LOGGER.error("Error al crear el carrito", e);
            return ResponseEntity.badRequest()
                .header("ERROR", e.getMessage().replaceAll("\\R", " "))
                .build();
        }
        
    }
}

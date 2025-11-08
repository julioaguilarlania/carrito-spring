package mx.lania.carrito.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mx.lania.carrito.dto.ProductoDto;
import mx.lania.carrito.servicios.ServicioProductos;

@RestController
@RequestMapping("/api/productos")
public class ControladorProductos {

    private final ServicioProductos productoService;

    public ControladorProductos(ServicioProductos productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDto> listarTodos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable Long id) {
        Optional<ProductoDto> opt = productoService.findById(id);
        return ResponseEntity.of(opt);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crear(@RequestBody ProductoDto productoDto) {
        try {
            ProductoDto creado = productoService.create(productoDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getIdProducto())
                .toUri();
            return ResponseEntity.created(location).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .header("ERROR", e.getMessage())
                .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(@PathVariable Long id, @RequestBody ProductoDto productoDto) {
        try {
            ProductoDto actualizado = productoService.update(id, productoDto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .header("ERROR", e.getMessage())
                .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean borrado = productoService.delete(id);
        if (borrado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

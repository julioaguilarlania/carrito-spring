package mx.lania.carrito.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/admin")
public class ControladorAdministracion {
    
    @GetMapping("/saludar")
    public String saludarAdministrador() {
        return "Hola administrador";
    }
    
}

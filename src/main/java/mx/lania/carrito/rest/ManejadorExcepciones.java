package mx.lania.carrito.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ManejadorExcepciones {
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manejarConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder mensajes = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> {
            mensajes.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
        });
        return ResponseEntity.badRequest().body(mensajes.toString());
    }
}

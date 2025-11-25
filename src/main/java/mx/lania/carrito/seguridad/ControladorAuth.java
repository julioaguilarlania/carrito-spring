package mx.lania.carrito.seguridad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorAuth {
    
    private JwtUtil jwtUtil;
    private ServicioUsuarios servUsuarios;

    private static final Logger LOGGER = LoggerFactory.getLogger(ControladorAuth.class);

    public ControladorAuth(JwtUtil util, ServicioUsuarios serv) {
        this.jwtUtil = util;
        this.servUsuarios = serv;
    }

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> obtenerAuthToken(@RequestBody AuthRequest request) {
        LOGGER.debug("POST /auth {{ {} }}", request.getUsuario());
        try {
            if (this.servUsuarios.usuarioValido(request.getUsuario(), request.getPassword())) {
                String tokenJwt = jwtUtil.generarToken(request.getUsuario());
                return ResponseEntity.ok(new AuthResponse(tokenJwt));
            }
            else throw new UsernameNotFoundException("");
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("ERROR", "Usuario o password incorrectos")
                .build();
        }
    }
}

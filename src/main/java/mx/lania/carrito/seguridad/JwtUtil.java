package mx.lania.carrito.seguridad;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.llave}")
    private String llaveSecreta;

    @Value("${jwt.minutos-duracion}")
    private long duracion;

    public String generarToken(String usuario) {
        LOGGER.debug("generarToken({})", usuario);
        Instant expiracion = new Date().toInstant().plus(duracion,ChronoUnit.MINUTES);
        return Jwts.builder()
            .setSubject(usuario)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(expiracion))
            .signWith(SignatureAlgorithm.HS256, llaveSecreta)
            .compact();
    }

    public Claims extraerClaims(String token) {
        return Jwts.parser()
            .setSigningKey(llaveSecreta)
            .parseClaimsJws(token)
            .getBody();
    }

    public String extraerUsuario(String token) {
        return extraerClaims(token).getSubject();
    }

    public boolean tokenExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }

    public boolean tokenValido(String token, String username) {
        return
            username.equals(extraerUsuario(token))
            && !tokenExpirado(token);
    }
}

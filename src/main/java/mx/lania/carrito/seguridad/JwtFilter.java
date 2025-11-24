package mx.lania.carrito.seguridad;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private JwtUtil jwtUtil;
    private ServicioUsuarios servicioUsuarios;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtUtil util, ServicioUsuarios serv) {
        this.jwtUtil = util;
        this.servicioUsuarios = serv;
    }

    private final String AUTH_HEADER_NAME = "Authorization";
    private final String BEARER_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                LOGGER.debug("doFilter");

        String authHeader = request.getHeader(AUTH_HEADER_NAME);

        String username = null;
        String tokenJwt = null;
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            tokenJwt = authHeader.substring(BEARER_PREFIX.length()+1);
            username = jwtUtil.extraerUsuario(tokenJwt);
        }
        LOGGER.trace("Token {} con usuario {}", tokenJwt, username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails usuario = servicioUsuarios.loadUserByUsername(username);

            if (jwtUtil.tokenValido(tokenJwt, username)) {
                LOGGER.trace("Token valido");
                UsernamePasswordAuthenticationToken tokenAuth = 
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            }
        }

        filterChain.doFilter(request, response);

    }
    
}

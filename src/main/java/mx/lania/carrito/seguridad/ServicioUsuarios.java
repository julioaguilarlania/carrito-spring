package mx.lania.carrito.seguridad;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mx.lania.carrito.entidades.Usuario;
import mx.lania.carrito.repositorios.RepositorioUsuarios;

@Component
public class ServicioUsuarios implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServicioUsuarios.class);
    private RepositorioUsuarios repoUsuarios;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public ServicioUsuarios(RepositorioUsuarios repo) {
        this.repoUsuarios = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repoUsuarios.findByEmail(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        else {
            throw new UsernameNotFoundException(username);
        }

    }

    

    public boolean usuarioValido(String login, String password) {
        LOGGER.debug("usuarioValido({})", login);
        UserDetails usuario = loadUserByUsername(login);
        return encoder.matches(password, usuario.getPassword());
    }
    
}

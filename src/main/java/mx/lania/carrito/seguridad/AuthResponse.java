package mx.lania.carrito.seguridad;

public class AuthResponse {
    private String jwt;

    public AuthResponse() {}
    public AuthResponse(String token) {
        this.jwt = token;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}

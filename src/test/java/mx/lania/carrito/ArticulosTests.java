package mx.lania.carrito;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticulosTests {
    
    @Autowired
    MockMvc mockMvc;

    /*
    // Este constructor seria necesario si queremos quitar Autowired de la propiedad
    public ArticulosTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    */

    @Test
    void listarArticulos() throws Exception {
        mockMvc.perform(get("/api/productos"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(containsString("Italian Sausage and Peppers")));
    }

    @Test
    void obtenerArticuloPorId() throws Exception {
        mockMvc.perform(get("/api/productos/5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(containsString("Frozen mango chunks for smoothies or snacking.")));
    }

    @Test
    void obtenerArticuloPorIdNoEncontrado() throws Exception {
        mockMvc.perform(get("/api/productos/12345"))
            .andExpect(status().isNotFound());
    }

    @Test
    void buscarArticulos() throws Exception {
        mockMvc.perform(get("/api/productos?desc=crispy"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(containsString("Maple Bacon")));
    }

    @Test
    void buscarArticulosSinResultados() throws Exception {
        mockMvc.perform(get("/api/productos?desc=xyzw"))
            .andExpect(status().isOk())
            .andExpect(content().string(is("[]")));
    }
}

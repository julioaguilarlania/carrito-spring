package mx.lania.carrito;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.lania.carrito.dto.ProductoDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc()
@ActiveProfiles("test")
@WithMockUser(username = "julio.aguilar@lania.edu.mx", authorities = { "ADMINISTRADOR" })
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

    @Test
    void guardarArticuloSimple() throws Exception {
        String nuevoProductoJson = """
            {
                "nombre": "Producto prueba",
                "descripcion": "Este es un producto de prueba",
                "precio": 9.99,
                "cantidadDisponible": 10
            }
            """;

        MvcResult mvcResult = mockMvc.perform(post("/api/productos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(nuevoProductoJson)
            )
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(containsString("Producto prueba")))
            .andExpect(content().string(containsString("Este es un producto de prueba")))
            .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        
        ProductoDto producto = mapper.readValue(mvcResult.getResponse().getContentAsString(), ProductoDto.class);
        assertNotNull(producto.getIdProducto());
        mockMvc.perform(get("/api/productos/" + producto.getIdProducto()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(containsString("Producto prueba")))
            .andExpect(content().string(containsString("Este es un producto de prueba")));
        
    }

    @Test
    void guardarArticuloIncompleto() throws Exception {   
        String nuevoProductoJson = """
            {
                "nombre": "",
                "descripcion": "",
                "precio": 5.99,
            }
            """;

        mockMvc.perform(post("/api/productos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(nuevoProductoJson)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void guardarArticuloSinNombre() throws Exception {   
        String nuevoProductoJson = """
            {
                "nombre": "",
                "descripcion": "Este es un producto sin nombre",
                "precio": 5,
                "cantidadDisponible": 10
            }
            """;

        mockMvc.perform(post("/api/productos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(nuevoProductoJson)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void guardarArticuloSinDescripcion() throws Exception {   
        String nuevoProductoJson = """
            {
                "nombre": "Producto sin descripcion",
                "descripcion": " ",
                "precio": 5,
                "cantidadDisponible": 10
            }
            """;

        mockMvc.perform(post("/api/productos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(nuevoProductoJson)
            )
            .andExpect(status().isBadRequest());
    }    
}

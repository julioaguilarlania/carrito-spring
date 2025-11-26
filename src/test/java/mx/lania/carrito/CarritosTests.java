package mx.lania.carrito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import mx.lania.carrito.dto.CarritoDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarritosTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "cliente01@lania.edu.mx", authorities = { "CLIENTE" })
    void crearCarrito() throws Exception {
        String carrito = """
        {
        "items": [
            {"idProducto": 1, "cantidad": 2},
            {"idProducto": 3, "cantidad": 1}
            ]
        }
        """;
        MvcResult resultado = mockMvc.perform(post("/api/carritos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(carrito))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        CarritoDto carritoDto = mapper.readValue(resultado.getResponse().getContentAsString(), CarritoDto.class);
        assertNotNull(carritoDto.getIdCarrito());
    }

}

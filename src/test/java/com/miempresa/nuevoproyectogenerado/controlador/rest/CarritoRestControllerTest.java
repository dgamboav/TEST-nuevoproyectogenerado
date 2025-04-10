package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.CarritoService;

@SpringBootTest
@AutoConfigureMockMvc
class CarritoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockCarritoService")
    private CarritoService carritoService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockCarritoService")
        @Primary
        public CarritoService carritoService() {
            return Mockito.mock(CarritoService.class);
        }
    }

    @Test
    void testCrearCarrito() throws Exception {
		CarritoDTO carritoDTO = CarritoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaCreacion(null).build();

        when(carritoService.crearCarrito(any(CarritoDTO.class))).thenReturn(carritoDTO);

        mockMvc.perform(post("/carrito/api")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(carritoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerCarritoPorId() throws Exception {

		CarritoDTO carritoDTO = CarritoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaCreacion(null).build();

        when(carritoService.obtenerCarritoPorId(1L)).thenReturn(Optional.of(carritoDTO));

        mockMvc.perform(get("/carrito/api/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarCarrito() throws Exception {
		CarritoDTO carritoDTO = CarritoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaCreacion(null).build();

        when(carritoService.actualizarCarrito(eq(1L), any(CarritoDTO.class))).thenReturn(Optional.of(carritoDTO));

        mockMvc.perform(put("/carrito/api/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(carritoDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarCarrito() throws Exception {
        mockMvc.perform(delete("/carrito/api/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosCarrito() throws Exception {
        List<CarritoDTO> carritoDTOs = List.of(
            CarritoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaCreacion(null).build(),
         CarritoDTO.builder()
		.id(2L)
		
		.clienteId(1L)
		
		 .fechaCreacion(null).build()
        );

        when(carritoService.obtenerTodosCarrito()).thenReturn(carritoDTOs);

        mockMvc.perform(get("/carrito/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
}
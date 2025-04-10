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

import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;
import com.miempresa.nuevoproyectogenerado.servicio.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockClienteService")
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockClienteService")
        @Primary
        public ClienteService clienteService() {
            return Mockito.mock(ClienteService.class);
        }
    }

    @Test
    void testCrearCliente() throws Exception {
		ClienteDTO clienteDTO = ClienteDTO.builder()
		.id(1L)
		
		.nombre("test")
		.direccion("test")
		.telefono("test").build();

        when(clienteService.crearCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(post("/cliente/api")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clienteDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerClientePorId() throws Exception {

		ClienteDTO clienteDTO = ClienteDTO.builder()
		.id(1L)
		
		.nombre("test")
		.direccion("test")
		.telefono("test").build();

        when(clienteService.obtenerClientePorId(1L)).thenReturn(Optional.of(clienteDTO));

        mockMvc.perform(get("/cliente/api/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarCliente() throws Exception {
		ClienteDTO clienteDTO = ClienteDTO.builder()
		.id(1L)
		
		.nombre("test")
		.direccion("test")
		.telefono("test").build();

        when(clienteService.actualizarCliente(eq(1L), any(ClienteDTO.class))).thenReturn(Optional.of(clienteDTO));

        mockMvc.perform(put("/cliente/api/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clienteDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarCliente() throws Exception {
        mockMvc.perform(delete("/cliente/api/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosCliente() throws Exception {
        List<ClienteDTO> clienteDTOs = List.of(
            ClienteDTO.builder()
		.id(1L)
		
		.nombre("test")
		.direccion("test")
		.telefono("test").build(),
         ClienteDTO.builder()
		.id(2L)
		
		.nombre("test")
		.direccion("test")
		.telefono("test").build()
        );

        when(clienteService.obtenerTodosCliente()).thenReturn(clienteDTOs);

        mockMvc.perform(get("/cliente/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
}
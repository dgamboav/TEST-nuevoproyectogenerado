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

import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.PedidoService;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockPedidoService")
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockPedidoService")
        @Primary
        public PedidoService pedidoService() {
            return Mockito.mock(PedidoService.class);
        }
    }

    @Test
    void testCrearPedido() throws Exception {
		PedidoDTO pedidoDTO = PedidoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaPedido(null)
		.total(1.0)
		.build();

        when(pedidoService.crearPedido(any(PedidoDTO.class))).thenReturn(pedidoDTO);

        mockMvc.perform(post("/pedido/api")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pedidoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerPedidoPorId() throws Exception {

		PedidoDTO pedidoDTO = PedidoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaPedido(null)
		.total(1.0)
		.build();

        when(pedidoService.obtenerPedidoPorId(1L)).thenReturn(Optional.of(pedidoDTO));

        mockMvc.perform(get("/pedido/api/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarPedido() throws Exception {
		PedidoDTO pedidoDTO = PedidoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaPedido(null)
		.total(1.0)
		.build();

        when(pedidoService.actualizarPedido(eq(1L), any(PedidoDTO.class))).thenReturn(Optional.of(pedidoDTO));

        mockMvc.perform(put("/pedido/api/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pedidoDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarPedido() throws Exception {
        mockMvc.perform(delete("/pedido/api/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosPedido() throws Exception {
        List<PedidoDTO> pedidoDTOs = List.of(
            PedidoDTO.builder()
		.id(1L)
		
		.clienteId(1L)
		
		 .fechaPedido(null)
		.total(1.0)
		.build(),
         PedidoDTO.builder()
		.id(2L)
		
		.clienteId(1L)
		
		 .fechaPedido(null)
		.total(1.0)
		.build()
        );

        when(pedidoService.obtenerTodosPedido()).thenReturn(pedidoDTOs);

        mockMvc.perform(get("/pedido/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
}
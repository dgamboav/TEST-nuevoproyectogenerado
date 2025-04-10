package com.miempresa.nuevoproyectogenerado.controlador;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.PedidoService;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockPedidoService")
    private PedidoService pedidoService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockPedidoService")
        @Primary
        public PedidoService pedidoService() {
            return Mockito.mock(PedidoService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/pedido/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("pedido/crear"))
                .andExpect(model().attributeExists("pedido"));
    }

    @Test
    void testCrearPedido() throws Exception {
        PedidoDTO pedidoDTO = PedidoDTO.builder().clienteId(1L).fechaPedido(null).total(1.0).build();

        mockMvc.perform(post("/pedido/crear")
                .flashAttr("pedido", pedidoDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pedido"));

        verify(pedidoService, times(1)).crearPedido(ArgumentMatchers.any(PedidoDTO.class));
    }

    @Test
    void testMostrarPedidoExistente() throws Exception {
        Long id = 1L;
        PedidoDTO pedidoDTO = PedidoDTO.builder().id(id).build();

        when(pedidoService.obtenerPedidoPorId(id)).thenReturn(Optional.of(pedidoDTO));

        mockMvc.perform(get("/pedido/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("pedido/ver"))
                .andExpect(model().attributeExists("pedido"))
                .andExpect(model().attribute("pedido", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarPedidoNoExistente() throws Exception {
        Long id = 1L;
        when(pedidoService.obtenerPedidoPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pedido/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Pedido no encontrado"));
    }

    @Test
    void testListarPedidos() throws Exception {
        List<PedidoDTO> pedidos = Collections.singletonList(
                PedidoDTO.builder().id(1L).build()
        );
        when(pedidoService.obtenerTodosPedido()).thenReturn(pedidos);

        mockMvc.perform(get("/pedido"))
                .andExpect(status().isOk())
                .andExpect(view().name("pedido/lista"))
                .andExpect(model().attributeExists("pedidos"))
                .andExpect(model().attribute("pedidos", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        PedidoDTO pedidoDTO = PedidoDTO.builder().id(id).build();
        when(pedidoService.obtenerPedidoPorId(id)).thenReturn(Optional.of(pedidoDTO));

        mockMvc.perform(get("/pedido/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("pedido/editar"))
                .andExpect(model().attributeExists("pedido"));
    }

    @Test
    void testActualizarPedido() throws Exception {
        Long id = 1L;
        PedidoDTO pedidoDTO = PedidoDTO.builder().id(id).build();

        mockMvc.perform(post("/pedido/editar/{id}", id)
                .flashAttr("pedido", pedidoDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pedido"));

        verify(pedidoService, times(1)).actualizarPedido(eq(id), ArgumentMatchers.any(PedidoDTO.class));
    }

    @Test
    void testEliminarPedido() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/pedido/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pedido"));

        verify(pedidoService, times(1)).eliminarPedido(id);
    }
}
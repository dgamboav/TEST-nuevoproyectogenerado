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

import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;
import com.miempresa.nuevoproyectogenerado.servicio.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockClienteService")
    private ClienteService clienteService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockClienteService")
        @Primary
        public ClienteService clienteService() {
            return Mockito.mock(ClienteService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/cliente/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/crear"))
                .andExpect(model().attributeExists("cliente"));
    }

    @Test
    void testCrearCliente() throws Exception {
        ClienteDTO clienteDTO = ClienteDTO.builder().nombre("test").direccion("test").telefono("test").build();

        mockMvc.perform(post("/cliente/crear")
                .flashAttr("cliente", clienteDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cliente"));

        verify(clienteService, times(1)).crearCliente(ArgumentMatchers.any(ClienteDTO.class));
    }

    @Test
    void testMostrarClienteExistente() throws Exception {
        Long id = 1L;
        ClienteDTO clienteDTO = ClienteDTO.builder().id(id).nombre("test").direccion("test").telefono("test").build();

        when(clienteService.obtenerClientePorId(id)).thenReturn(Optional.of(clienteDTO));

        mockMvc.perform(get("/cliente/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/ver"))
                .andExpect(model().attributeExists("cliente"))
                .andExpect(model().attribute("cliente", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarClienteNoExistente() throws Exception {
        Long id = 1L;
        when(clienteService.obtenerClientePorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cliente/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Cliente no encontrado"));
    }

    @Test
    void testListarClientes() throws Exception {
        List<ClienteDTO> clientes = Collections.singletonList(
                ClienteDTO.builder().id(1L).nombre("test").direccion("test").telefono("test").build()
        );
        when(clienteService.obtenerTodosCliente()).thenReturn(clientes);

        mockMvc.perform(get("/cliente"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/lista"))
                .andExpect(model().attributeExists("clientes"))
                .andExpect(model().attribute("clientes", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        ClienteDTO clienteDTO = ClienteDTO.builder().id(id).nombre("test").direccion("test").telefono("test").build();
        when(clienteService.obtenerClientePorId(id)).thenReturn(Optional.of(clienteDTO));

        mockMvc.perform(get("/cliente/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente/editar"))
                .andExpect(model().attributeExists("cliente"));
    }

    @Test
    void testActualizarCliente() throws Exception {
        Long id = 1L;
        ClienteDTO clienteDTO = ClienteDTO.builder().id(id).nombre("updated").direccion("updated").telefono("updated").build();

        mockMvc.perform(post("/cliente/editar/{id}", id)
                .flashAttr("cliente", clienteDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cliente"));

        verify(clienteService, times(1)).actualizarCliente(eq(id), ArgumentMatchers.any(ClienteDTO.class));
    }

    @Test
    void testEliminarCliente() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/cliente/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cliente"));

        verify(clienteService, times(1)).eliminarCliente(id);
    }
}
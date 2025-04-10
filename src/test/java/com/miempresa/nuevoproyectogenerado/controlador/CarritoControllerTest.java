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

import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.CarritoService;

@SpringBootTest
@AutoConfigureMockMvc
class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockCarritoService")
    private CarritoService carritoService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockCarritoService")
        @Primary
        public CarritoService carritoService() {
            return Mockito.mock(CarritoService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/carrito/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("carrito/crear"))
                .andExpect(model().attributeExists("carrito"));
    }

    @Test
    void testCrearCarrito() throws Exception {
        CarritoDTO carritoDTO = CarritoDTO.builder().clienteId(1L).fechaCreacion(null).build();

        mockMvc.perform(post("/carrito/crear")
                .flashAttr("carrito", carritoDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/carrito"));

        verify(carritoService, times(1)).crearCarrito(ArgumentMatchers.any(CarritoDTO.class));
    }

    @Test
    void testMostrarCarritoExistente() throws Exception {
        Long id = 1L;
        CarritoDTO carritoDTO = CarritoDTO.builder().id(id).build();

        when(carritoService.obtenerCarritoPorId(id)).thenReturn(Optional.of(carritoDTO));

        mockMvc.perform(get("/carrito/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("carrito/ver"))
                .andExpect(model().attributeExists("carrito"))
                .andExpect(model().attribute("carrito", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarCarritoNoExistente() throws Exception {
        Long id = 1L;
        when(carritoService.obtenerCarritoPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/carrito/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Carrito no encontrado"));
    }

    @Test
    void testListarCarritos() throws Exception {
        List<CarritoDTO> carritos = Collections.singletonList(
                CarritoDTO.builder().id(1L).build()
        );
        when(carritoService.obtenerTodosCarrito()).thenReturn(carritos);

        mockMvc.perform(get("/carrito"))
                .andExpect(status().isOk())
                .andExpect(view().name("carrito/lista"))
                .andExpect(model().attributeExists("carritos"))
                .andExpect(model().attribute("carritos", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        CarritoDTO carritoDTO = CarritoDTO.builder().id(id).build();
        when(carritoService.obtenerCarritoPorId(id)).thenReturn(Optional.of(carritoDTO));

        mockMvc.perform(get("/carrito/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("carrito/editar"))
                .andExpect(model().attributeExists("carrito"));
    }

    @Test
    void testActualizarCarrito() throws Exception {
        Long id = 1L;
        CarritoDTO carritoDTO = CarritoDTO.builder().id(id).build();

        mockMvc.perform(post("/carrito/editar/{id}", id)
                .flashAttr("carrito", carritoDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/carrito"));

        verify(carritoService, times(1)).actualizarCarrito(eq(id), ArgumentMatchers.any(CarritoDTO.class));
    }

    @Test
    void testEliminarCarrito() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/carrito/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/carrito"));

        verify(carritoService, times(1)).eliminarCarrito(id);
    }
}
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

import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.EmpleadoService;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockEmpleadoService")
    private EmpleadoService empleadoService;

	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockEmpleadoService")
        @Primary
        public EmpleadoService empleadoService() {
            return Mockito.mock(EmpleadoService.class);
        }
    }

    @Test
    void testMostrarFormularioCrear() throws Exception {
        mockMvc.perform(get("/empleado/crear"))
                .andExpect(status().isOk())
                .andExpect(view().name("empleado/crear"))
                .andExpect(model().attributeExists("empleado"));
    }

    @Test
    void testCrearEmpleado() throws Exception {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder().nombre("test").departamento("test").salario(1.0).build();

        mockMvc.perform(post("/empleado/crear")
                .flashAttr("empleado", empleadoDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/empleado"));

        verify(empleadoService, times(1)).crearEmpleado(ArgumentMatchers.any(EmpleadoDTO.class));
    }

    @Test
    void testMostrarEmpleadoExistente() throws Exception {
        Long id = 1L;
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder().id(id).nombre("test").departamento("test").build();

        when(empleadoService.obtenerEmpleadoPorId(id)).thenReturn(Optional.of(empleadoDTO));

        mockMvc.perform(get("/empleado/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("empleado/ver"))
                .andExpect(model().attributeExists("empleado"))
                .andExpect(model().attribute("empleado", hasProperty("id", is(1L))));
    }

    @Test
    void testMostrarEmpleadoNoExistente() throws Exception {
        Long id = 1L;
        when(empleadoService.obtenerEmpleadoPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/empleado/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("mensajeError"))
                .andExpect(model().attribute("mensajeError", "Empleado no encontrado"));
    }

    @Test
    void testListarEmpleados() throws Exception {
        List<EmpleadoDTO> empleados = Collections.singletonList(
                EmpleadoDTO.builder().id(1L).nombre("test").departamento("test").build()
        );
        when(empleadoService.obtenerTodosEmpleado()).thenReturn(empleados);

        mockMvc.perform(get("/empleado"))
                .andExpect(status().isOk())
                .andExpect(view().name("empleado/lista"))
                .andExpect(model().attributeExists("empleados"))
                .andExpect(model().attribute("empleados", hasSize(1)));
    }

    @Test
    void testMostrarFormularioEditarExistente() throws Exception {
        Long id = 1L;
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder().id(id).nombre("test").departamento("test").build();
        when(empleadoService.obtenerEmpleadoPorId(id)).thenReturn(Optional.of(empleadoDTO));

        mockMvc.perform(get("/empleado/editar/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("empleado/editar"))
                .andExpect(model().attributeExists("empleado"));
    }

    @Test
    void testActualizarEmpleado() throws Exception {
        Long id = 1L;
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder().id(id).nombre("updated").departamento("updated").build();

        mockMvc.perform(post("/empleado/editar/{id}", id)
                .flashAttr("empleado", empleadoDTO)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/empleado"));

        verify(empleadoService, times(1)).actualizarEmpleado(eq(id), ArgumentMatchers.any(EmpleadoDTO.class));
    }

    @Test
    void testEliminarEmpleado() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/empleado/eliminar/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/empleado"));

        verify(empleadoService, times(1)).eliminarEmpleado(id);
    }
}
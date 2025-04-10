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

import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.EmpleadoService;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockEmpleadoService")
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockEmpleadoService")
        @Primary
        public EmpleadoService empleadoService() {
            return Mockito.mock(EmpleadoService.class);
        }
    }

    @Test
    void testCrearEmpleado() throws Exception {
		EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.departamento("test")
		.salario(1.0)
		.build();

        when(empleadoService.crearEmpleado(any(EmpleadoDTO.class))).thenReturn(empleadoDTO);

        mockMvc.perform(post("/empleado/api")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(empleadoDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerEmpleadoPorId() throws Exception {

		EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.departamento("test")
		.salario(1.0)
		.build();

        when(empleadoService.obtenerEmpleadoPorId(1L)).thenReturn(Optional.of(empleadoDTO));

        mockMvc.perform(get("/empleado/api/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarEmpleado() throws Exception {
		EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.departamento("test")
		.salario(1.0)
		.build();

        when(empleadoService.actualizarEmpleado(eq(1L), any(EmpleadoDTO.class))).thenReturn(Optional.of(empleadoDTO));

        mockMvc.perform(put("/empleado/api/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(empleadoDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarEmpleado() throws Exception {
        mockMvc.perform(delete("/empleado/api/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosEmpleado() throws Exception {
        List<EmpleadoDTO> empleadoDTOs = List.of(
            EmpleadoDTO.builder()
		.id(1L)
		
		.nombre("test")
		.departamento("test")
		.salario(1.0)
		.build(),
         EmpleadoDTO.builder()
		.id(2L)
		
		.nombre("test")
		.departamento("test")
		.salario(1.0)
		.build()
        );

        when(empleadoService.obtenerTodosEmpleado()).thenReturn(empleadoDTOs);

        mockMvc.perform(get("/empleado/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
}
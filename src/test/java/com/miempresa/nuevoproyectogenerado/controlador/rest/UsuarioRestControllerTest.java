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

import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;
import com.miempresa.nuevoproyectogenerado.servicio.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mockUsuarioService")
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;
	
	@org.springframework.boot.test.context.TestConfiguration
    static class TestConfig {
        @Bean("mockUsuarioService")
        @Primary
        public UsuarioService usuarioService() {
            return Mockito.mock(UsuarioService.class);
        }
    }

    @Test
    void testCrearUsuario() throws Exception {
		UsuarioDTO usuarioDTO = UsuarioDTO.builder()
		.id(1L)
		
		.nombre("test")
		.correo("test")
		.contrasena("test").build();

        when(usuarioService.crearUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post("/usuario/api")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(usuarioDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testObtenerUsuarioPorId() throws Exception {

		UsuarioDTO usuarioDTO = UsuarioDTO.builder()
		.id(1L)
		
		.nombre("test")
		.correo("test")
		.contrasena("test").build();

        when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(get("/usuario/api/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testActualizarUsuario() throws Exception {
		UsuarioDTO usuarioDTO = UsuarioDTO.builder()
		.id(1L)
		
		.nombre("test")
		.correo("test")
		.contrasena("test").build();

        when(usuarioService.actualizarUsuario(eq(1L), any(UsuarioDTO.class))).thenReturn(Optional.of(usuarioDTO));

        mockMvc.perform(put("/usuario/api/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(usuarioDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }

    @Test
    void testEliminarUsuario() throws Exception {
        mockMvc.perform(delete("/usuario/api/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerTodosUsuario() throws Exception {
        List<UsuarioDTO> usuarioDTOs = List.of(
            UsuarioDTO.builder()
		.id(1L)
		
		.nombre("test")
		.correo("test")
		.contrasena("test").build(),
         UsuarioDTO.builder()
		.id(2L)
		
		.nombre("test")
		.correo("test")
		.contrasena("test").build()
        );

        when(usuarioService.obtenerTodosUsuario()).thenReturn(usuarioDTOs);

        mockMvc.perform(get("/usuario/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists()); // Reemplaza "id" con el nombre de tu clave primaria
    }
}
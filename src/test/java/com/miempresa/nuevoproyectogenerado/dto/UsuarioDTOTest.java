package com.miempresa.nuevoproyectogenerado.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.entidad.Usuario;

class UsuarioDTOTest {

    @Test
    void testDTO() {
        UsuarioDTO dto = UsuarioDTO.builder()
            .id(1L)
            .nombre("test")
            .correo("test")
            .contrasena("test")
            .build();

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getCorreo());
        assertEquals("test", dto.getContrasena());

        UsuarioDTO dto2 = new UsuarioDTO(
            1L
            , 
            "test"
            , 
            "test"
            , 
            "test"
            
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("UsuarioDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("nombre"));
        assertTrue(toString.contains("correo"));
        assertTrue(toString.contains("contrasena"));
    }

    @Test
    void testToDTO() {
        Usuario usuario = Usuario.builder()
            .id(1L)
            .nombre("test")
            .correo("test")
            .contrasena("test")
            .build();

        UsuarioDTO dto = UsuarioDTO.toDTO(usuario);

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getCorreo());
        assertEquals("test", dto.getContrasena());
    }

    @Test
    void testToDomain() {
        UsuarioDTO dto = UsuarioDTO.builder()
            .id(1L)
            .nombre("test")
            .correo("test")
            .contrasena("test")
            .build();

        Usuario usuario = UsuarioDTO.toDomain(dto);

        assertEquals(1L, usuario.getId());
        assertEquals("test", usuario.getNombre());
        assertEquals("test", usuario.getCorreo());
        assertEquals("test", usuario.getContrasena());
    }
}
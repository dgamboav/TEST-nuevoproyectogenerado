package com.miempresa.nuevoproyectogenerado.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.entidad.Cliente;

class ClienteDTOTest {

    @Test
    void testDTO() {
        ClienteDTO dto = ClienteDTO.builder()
            .id(1L)
            .nombre("test")
            .direccion("test")
            .telefono("test")
            .build();

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getDireccion());
        assertEquals("test", dto.getTelefono());

        ClienteDTO dto2 = new ClienteDTO(
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
        assertTrue(toString.contains("ClienteDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("nombre"));
        assertTrue(toString.contains("direccion"));
        assertTrue(toString.contains("telefono"));
    }

    @Test
    void testToDTO() {
        Cliente cliente = Cliente.builder()
            .id(1L)
            .nombre("test")
            .direccion("test")
            .telefono("test")
            .build();

        ClienteDTO dto = ClienteDTO.toDTO(cliente);

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getDireccion());
        assertEquals("test", dto.getTelefono());
    }

    @Test
    void testToDomain() {
        ClienteDTO dto = ClienteDTO.builder()
            .id(1L)
            .nombre("test")
            .direccion("test")
            .telefono("test")
            .build();

        Cliente cliente = ClienteDTO.toDomain(dto);

        assertEquals(1L, cliente.getId());
        assertEquals("test", cliente.getNombre());
        assertEquals("test", cliente.getDireccion());
        assertEquals("test", cliente.getTelefono());
    }
}
package com.miempresa.nuevoproyectogenerado.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.entidad.Carrito;

class CarritoDTOTest {

    @Test
    void testDTO() {
        CarritoDTO dto = CarritoDTO.builder()
            .id(1L)
            .clienteId(1L)
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getClienteId());
        assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos

        CarritoDTO dto2 = new CarritoDTO(
            1L
            , 
            1L
            , 
            null
            
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("CarritoDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("clienteId"));
        assertTrue(toString.contains("fechaCreacion"));
    }

    @Test
    void testToDTO() {
        Carrito carrito = Carrito.builder()
            .id(1L)
            .clienteId(1L)
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        CarritoDTO dto = CarritoDTO.toDTO(carrito);

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getClienteId());
        assertNull(dto.getFechaCreacion()); // Valor por defecto para otros tipos
    }

    @Test
    void testToDomain() {
        CarritoDTO dto = CarritoDTO.builder()
            .id(1L)
            .clienteId(1L)
            .fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Carrito carrito = CarritoDTO.toDomain(dto);

        assertEquals(1L, carrito.getId());
        assertEquals(1L, carrito.getClienteId());
        assertNull(carrito.getFechaCreacion()); // Valor por defecto para otros tipos
    }
}
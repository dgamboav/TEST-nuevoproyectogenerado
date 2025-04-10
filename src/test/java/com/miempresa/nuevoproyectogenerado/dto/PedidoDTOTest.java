package com.miempresa.nuevoproyectogenerado.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.entidad.Pedido;

class PedidoDTOTest {

    @Test
    void testDTO() {
        PedidoDTO dto = PedidoDTO.builder()
            .id(1L)
            .clienteId(1L)
            .fechaPedido(null) // Valor por defecto para otros tipos
            .total(1.0)
            .build();

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getClienteId());
        assertNull(dto.getFechaPedido()); // Valor por defecto para otros tipos
        assertEquals(1.0, dto.getTotal());

        PedidoDTO dto2 = new PedidoDTO(
            1L
            , 
            1L
            , 
            null
            , 
            1.0
            
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("PedidoDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("clienteId"));
        assertTrue(toString.contains("fechaPedido"));
        assertTrue(toString.contains("total"));
    }

    @Test
    void testToDTO() {
        Pedido pedido = Pedido.builder()
            .id(1L)
            .clienteId(1L)
            .fechaPedido(null) // Valor por defecto para otros tipos
            .total(1.0)
            .build();

        PedidoDTO dto = PedidoDTO.toDTO(pedido);

        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getClienteId());
        assertNull(dto.getFechaPedido()); // Valor por defecto para otros tipos
        assertEquals(1.0, dto.getTotal());
    }

    @Test
    void testToDomain() {
        PedidoDTO dto = PedidoDTO.builder()
            .id(1L)
            .clienteId(1L)
            .fechaPedido(null) // Valor por defecto para otros tipos
            .total(1.0)
            .build();

        Pedido pedido = PedidoDTO.toDomain(dto);

        assertEquals(1L, pedido.getId());
        assertEquals(1L, pedido.getClienteId());
        assertNull(pedido.getFechaPedido()); // Valor por defecto para otros tipos
        assertEquals(1.0, pedido.getTotal());
    }
}
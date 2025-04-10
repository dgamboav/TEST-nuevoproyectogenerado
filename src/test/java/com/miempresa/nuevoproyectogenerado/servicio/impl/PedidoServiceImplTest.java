package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.repositorio.PedidoRepository;
import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Pedido;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void testCrearPedido() {
        PedidoDTO pedidoDTO = PedidoDTO.builder()
				.clienteId(1L)
				.fechaPedido(null) // Valor por defecto para otros tipos
				.total(1.0)
            .build();

        Pedido pedido = PedidoDTO.toDomain(pedidoDTO);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        PedidoDTO resultado = pedidoService.crearPedido(pedidoDTO);

        assertEquals(PedidoDTO.toDTO(pedido), resultado);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void testObtenerPedidoPorId() {
        Pedido pedido = Pedido.builder()
				.clienteId(1L)
				.fechaPedido(null) // Valor por defecto para otros tipos
				.total(1.0)
            .build();

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Optional<PedidoDTO> resultado = pedidoService.obtenerPedidoPorId(1L);

        assertEquals(Optional.of(PedidoDTO.toDTO(pedido)), resultado);
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosPedido() {
        List<Pedido> pedidos = List.of(
            Pedido.builder()
				.clienteId(1L)
				.fechaPedido(null) // Valor por defecto para otros tipos
				.total(1.0)
                .build(),
            Pedido.builder()
				.clienteId(1L)
				.fechaPedido(null) // Valor por defecto para otros tipos
				.total(1.0)
                .build()
        );

        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<PedidoDTO> resultado = pedidoService.obtenerTodosPedido();

        List<PedidoDTO> expected = pedidos.stream().map(PedidoDTO::toDTO).toList();
        assertEquals(expected, resultado);
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testActualizarPedido() {
        PedidoDTO pedidoDTO = PedidoDTO.builder()
				.clienteId(1L)
				.fechaPedido(null) // Valor por defecto para otros tipos
				.total(1.0)
            .build();

        Pedido pedido = PedidoDTO.toDomain(pedidoDTO);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Optional<PedidoDTO> resultado = pedidoService.actualizarPedido(1L, pedidoDTO);

        assertEquals(Optional.of(PedidoDTO.toDTO(pedido)), resultado);
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void testEliminarPedido() {
        pedidoService.eliminarPedido(1L);

        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
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

import com.miempresa.nuevoproyectogenerado.repositorio.CarritoRepository;
import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Carrito;

@ExtendWith(MockitoExtension.class)
class CarritoServiceImplTest {

    @Mock
    private CarritoRepository carritoRepository;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @Test
    void testCrearCarrito() {
        CarritoDTO carritoDTO = CarritoDTO.builder()
				.clienteId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Carrito carrito = CarritoDTO.toDomain(carritoDTO);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CarritoDTO resultado = carritoService.crearCarrito(carritoDTO);

        assertEquals(CarritoDTO.toDTO(carrito), resultado);
        verify(carritoRepository, times(1)).save(any(Carrito.class));
    }

    @Test
    void testObtenerCarritoPorId() {
        Carrito carrito = Carrito.builder()
				.clienteId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));

        Optional<CarritoDTO> resultado = carritoService.obtenerCarritoPorId(1L);

        assertEquals(Optional.of(CarritoDTO.toDTO(carrito)), resultado);
        verify(carritoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosCarrito() {
        List<Carrito> carritos = List.of(
            Carrito.builder()
				.clienteId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
                .build(),
            Carrito.builder()
				.clienteId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
                .build()
        );

        when(carritoRepository.findAll()).thenReturn(carritos);

        List<CarritoDTO> resultado = carritoService.obtenerTodosCarrito();

        List<CarritoDTO> expected = carritos.stream().map(CarritoDTO::toDTO).toList();
        assertEquals(expected, resultado);
        verify(carritoRepository, times(1)).findAll();
    }

    @Test
    void testActualizarCarrito() {
        CarritoDTO carritoDTO = CarritoDTO.builder()
				.clienteId(1L)
				.fechaCreacion(null) // Valor por defecto para otros tipos
            .build();

        Carrito carrito = CarritoDTO.toDomain(carritoDTO);
        when(carritoRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        Optional<CarritoDTO> resultado = carritoService.actualizarCarrito(1L, carritoDTO);

        assertEquals(Optional.of(CarritoDTO.toDTO(carrito)), resultado);
        verify(carritoRepository, times(1)).findById(1L);
        verify(carritoRepository, times(1)).save(any(Carrito.class));
    }

    @Test
    void testEliminarCarrito() {
        carritoService.eliminarCarrito(1L);

        verify(carritoRepository, times(1)).deleteById(1L);
    }
}
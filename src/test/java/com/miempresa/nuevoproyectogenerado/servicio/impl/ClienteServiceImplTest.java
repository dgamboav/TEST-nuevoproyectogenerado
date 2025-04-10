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

import com.miempresa.nuevoproyectogenerado.repositorio.ClienteRepository;
import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Cliente;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void testCrearCliente() {
        ClienteDTO clienteDTO = ClienteDTO.builder()
				.nombre("test")
				.direccion("test")
				.telefono("test")
            .build();

        Cliente cliente = ClienteDTO.toDomain(clienteDTO);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO resultado = clienteService.crearCliente(clienteDTO);

        assertEquals(ClienteDTO.toDTO(cliente), resultado);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testObtenerClientePorId() {
        Cliente cliente = Cliente.builder()
				.nombre("test")
				.direccion("test")
				.telefono("test")
            .build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<ClienteDTO> resultado = clienteService.obtenerClientePorId(1L);

        assertEquals(Optional.of(ClienteDTO.toDTO(cliente)), resultado);
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosCliente() {
        List<Cliente> clientes = List.of(
            Cliente.builder()
				.nombre("test")
				.direccion("test")
				.telefono("test")
                .build(),
            Cliente.builder()
				.nombre("test")
				.direccion("test")
				.telefono("test")
                .build()
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteDTO> resultado = clienteService.obtenerTodosCliente();

        List<ClienteDTO> expected = clientes.stream().map(ClienteDTO::toDTO).toList();
        assertEquals(expected, resultado);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testActualizarCliente() {
        ClienteDTO clienteDTO = ClienteDTO.builder()
				.nombre("test")
				.direccion("test")
				.telefono("test")
            .build();

        Cliente cliente = ClienteDTO.toDomain(clienteDTO);
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Optional<ClienteDTO> resultado = clienteService.actualizarCliente(1L, clienteDTO);

        assertEquals(Optional.of(ClienteDTO.toDTO(cliente)), resultado);
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testEliminarCliente() {
        clienteService.eliminarCliente(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
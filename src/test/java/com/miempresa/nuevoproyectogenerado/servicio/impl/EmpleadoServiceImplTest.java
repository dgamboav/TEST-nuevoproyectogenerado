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

import com.miempresa.nuevoproyectogenerado.repositorio.EmpleadoRepository;
import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Empleado;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    @Test
    void testCrearEmpleado() {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        Empleado empleado = EmpleadoDTO.toDomain(empleadoDTO);
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        EmpleadoDTO resultado = empleadoService.crearEmpleado(empleadoDTO);

        assertEquals(EmpleadoDTO.toDTO(empleado), resultado);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testObtenerEmpleadoPorId() {
        Empleado empleado = Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Optional<EmpleadoDTO> resultado = empleadoService.obtenerEmpleadoPorId(1L);

        assertEquals(Optional.of(EmpleadoDTO.toDTO(empleado)), resultado);
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosEmpleado() {
        List<Empleado> empleados = List.of(
            Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
                .build(),
            Empleado.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
                .build()
        );

        when(empleadoRepository.findAll()).thenReturn(empleados);

        List<EmpleadoDTO> resultado = empleadoService.obtenerTodosEmpleado();

        List<EmpleadoDTO> expected = empleados.stream().map(EmpleadoDTO::toDTO).toList();
        assertEquals(expected, resultado);
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testActualizarEmpleado() {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
				.nombre("test")
				.departamento("test")
				.salario(1.0)
            .build();

        Empleado empleado = EmpleadoDTO.toDomain(empleadoDTO);
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Optional<EmpleadoDTO> resultado = empleadoService.actualizarEmpleado(1L, empleadoDTO);

        assertEquals(Optional.of(EmpleadoDTO.toDTO(empleado)), resultado);
        verify(empleadoRepository, times(1)).findById(1L);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testEliminarEmpleado() {
        empleadoService.eliminarEmpleado(1L);

        verify(empleadoRepository, times(1)).deleteById(1L);
    }
}
package com.miempresa.nuevoproyectogenerado.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.miempresa.nuevoproyectogenerado.entidad.Empleado;

class EmpleadoDTOTest {

    @Test
    void testDTO() {
        EmpleadoDTO dto = EmpleadoDTO.builder()
            .id(1L)
            .nombre("test")
            .departamento("test")
            .salario(1.0)
            .build();

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getDepartamento());
        assertEquals(1.0, dto.getSalario());

        EmpleadoDTO dto2 = new EmpleadoDTO(
            1L
            , 
            "test"
            , 
            "test"
            , 
            1.0
            
        );

        assertEquals(dto, dto2);
        assertEquals(dto.hashCode(), dto2.hashCode());

        String toString = dto.toString();
        assertTrue(toString.contains("EmpleadoDTO"));
        assertTrue(toString.contains("id"));
        assertTrue(toString.contains("nombre"));
        assertTrue(toString.contains("departamento"));
        assertTrue(toString.contains("salario"));
    }

    @Test
    void testToDTO() {
        Empleado empleado = Empleado.builder()
            .id(1L)
            .nombre("test")
            .departamento("test")
            .salario(1.0)
            .build();

        EmpleadoDTO dto = EmpleadoDTO.toDTO(empleado);

        assertEquals(1L, dto.getId());
        assertEquals("test", dto.getNombre());
        assertEquals("test", dto.getDepartamento());
        assertEquals(1.0, dto.getSalario());
    }

    @Test
    void testToDomain() {
        EmpleadoDTO dto = EmpleadoDTO.builder()
            .id(1L)
            .nombre("test")
            .departamento("test")
            .salario(1.0)
            .build();

        Empleado empleado = EmpleadoDTO.toDomain(dto);

        assertEquals(1L, empleado.getId());
        assertEquals("test", empleado.getNombre());
        assertEquals("test", empleado.getDepartamento());
        assertEquals(1.0, empleado.getSalario());
    }
}
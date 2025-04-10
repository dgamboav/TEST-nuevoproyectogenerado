package com.miempresa.nuevoproyectogenerado.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

import com.miempresa.nuevoproyectogenerado.entidad.Empleado;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {

    private Long id;

    private String nombre;

    private String departamento;

    private Double salario;


    public static EmpleadoDTO toDTO(Empleado empleado) {
        return EmpleadoDTO.builder()
            .id(empleado.getId())
            .nombre(empleado.getNombre())
            .departamento(empleado.getDepartamento())
            .salario(empleado.getSalario())
            .build();
    }

    public static Empleado toDomain(EmpleadoDTO empleadoDTO) {
        return Empleado.builder()
            .id(empleadoDTO.getId())
            .nombre(empleadoDTO.getNombre())
            .departamento(empleadoDTO.getDepartamento())
            .salario(empleadoDTO.getSalario())
            .build();
    }
}
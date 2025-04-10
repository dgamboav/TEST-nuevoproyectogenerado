package com.miempresa.nuevoproyectogenerado.servicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id);

    List<EmpleadoDTO> obtenerTodosEmpleado();

    Optional<EmpleadoDTO> actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);

    void eliminarEmpleado(Long id);
}
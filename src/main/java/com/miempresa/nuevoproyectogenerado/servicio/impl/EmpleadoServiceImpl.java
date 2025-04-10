package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.repositorio.EmpleadoRepository;
import com.miempresa.nuevoproyectogenerado.servicio.EmpleadoService;
import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    
    private final EmpleadoRepository empleadoRepository;
	
	EmpleadoServiceImpl(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        return EmpleadoDTO.toDTO(empleadoRepository.save(EmpleadoDTO.toDomain(empleadoDTO)));
    }

    @Override
    public Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).map(EmpleadoDTO::toDTO);
    }

    @Override
    public List<EmpleadoDTO> obtenerTodosEmpleado() {
        return empleadoRepository.findAll().stream()
                .map(EmpleadoDTO::toDTO)
                .toList();
    }

    @Override
    public Optional<EmpleadoDTO> actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        return empleadoRepository.findById(id)
                .map(existingEmpleado -> {
                    Empleado empleado = EmpleadoDTO.toDomain(empleadoDTO);
                    empleado.setId(id);
                    return empleadoRepository.save(empleado);
                })
                .map(EmpleadoDTO::toDTO);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
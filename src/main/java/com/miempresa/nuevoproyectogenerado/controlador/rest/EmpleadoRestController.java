package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.servicio.EmpleadoService;
import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;

@RestController
@RequestMapping("/empleado/api")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO createdEmpleado = empleadoService.crearEmpleado(empleadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpleado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
        Optional<EmpleadoDTO> empleadoDTO = empleadoService.obtenerEmpleadoPorId(id);
        return empleadoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosEmpleado() {
        List<EmpleadoDTO> empleadoDTOs = empleadoService.obtenerTodosEmpleado();
        return ResponseEntity.ok(empleadoDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        Optional<EmpleadoDTO> updatedEmpleadoDTO = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return updatedEmpleadoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
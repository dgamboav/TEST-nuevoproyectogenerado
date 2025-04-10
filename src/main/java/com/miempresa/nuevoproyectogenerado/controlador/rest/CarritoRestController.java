package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.servicio.CarritoService;
import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;

@RestController
@RequestMapping("/carrito/api")
public class CarritoRestController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        CarritoDTO createdCarrito = carritoService.crearCarrito(carritoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerCarritoPorId(@PathVariable Long id) {
        Optional<CarritoDTO> carritoDTO = carritoService.obtenerCarritoPorId(id);
        return carritoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<CarritoDTO>> obtenerTodosCarrito() {
        List<CarritoDTO> carritoDTOs = carritoService.obtenerTodosCarrito();
        return ResponseEntity.ok(carritoDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizarCarrito(@PathVariable Long id, @RequestBody CarritoDTO carritoDTO) {
        Optional<CarritoDTO> updatedCarritoDTO = carritoService.actualizarCarrito(id, carritoDTO);
        return updatedCarritoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Carrito no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
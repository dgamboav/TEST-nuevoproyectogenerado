package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.servicio.ClienteService;
import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;

@RestController
@RequestMapping("/cliente/api")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdCliente = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteDTO> clienteDTO = clienteService.obtenerClientePorId(id);
        return clienteDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosCliente() {
        List<ClienteDTO> clienteDTOs = clienteService.obtenerTodosCliente();
        return ResponseEntity.ok(clienteDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> updatedClienteDTO = clienteService.actualizarCliente(id, clienteDTO);
        return updatedClienteDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
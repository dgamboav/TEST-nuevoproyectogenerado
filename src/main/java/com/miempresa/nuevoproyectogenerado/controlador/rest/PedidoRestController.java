package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.servicio.PedidoService;
import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;

@RestController
@RequestMapping("/pedido/api")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO createdPedido = pedidoService.crearPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<PedidoDTO> pedidoDTO = pedidoService.obtenerPedidoPorId(id);
        return pedidoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosPedido() {
        List<PedidoDTO> pedidoDTOs = pedidoService.obtenerTodosPedido();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        Optional<PedidoDTO> updatedPedidoDTO = pedidoService.actualizarPedido(id, pedidoDTO);
        return updatedPedidoDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
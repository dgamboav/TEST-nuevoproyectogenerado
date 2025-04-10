package com.miempresa.nuevoproyectogenerado.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.servicio.UsuarioService;
import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario/api")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.obtenerUsuarioPorId(id);
        return usuarioDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosUsuario() {
        List<UsuarioDTO> usuarioDTOs = usuarioService.obtenerTodosUsuario();
        return ResponseEntity.ok(usuarioDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<UsuarioDTO> updatedUsuarioDTO = usuarioService.actualizarUsuario(id, usuarioDTO);
        return updatedUsuarioDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
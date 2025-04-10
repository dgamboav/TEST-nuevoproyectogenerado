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

import com.miempresa.nuevoproyectogenerado.repositorio.UsuarioRepository;
import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Usuario;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void testCrearUsuario() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
            .build();

        Usuario usuario = UsuarioDTO.toDomain(usuarioDTO);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO resultado = usuarioService.crearUsuario(usuarioDTO);

        assertEquals(UsuarioDTO.toDTO(usuario), resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testObtenerUsuarioPorId() {
        Usuario usuario = Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
            .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<UsuarioDTO> resultado = usuarioService.obtenerUsuarioPorId(1L);

        assertEquals(Optional.of(UsuarioDTO.toDTO(usuario)), resultado);
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosUsuario() {
        List<Usuario> usuarios = List.of(
            Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
                .build(),
            Usuario.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
                .build()
        );

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioDTO> resultado = usuarioService.obtenerTodosUsuario();

        List<UsuarioDTO> expected = usuarios.stream().map(UsuarioDTO::toDTO).toList();
        assertEquals(expected, resultado);
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testActualizarUsuario() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.nombre("test")
				.correo("test")
				.contrasena("test")
            .build();

        Usuario usuario = UsuarioDTO.toDomain(usuarioDTO);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Optional<UsuarioDTO> resultado = usuarioService.actualizarUsuario(1L, usuarioDTO);

        assertEquals(Optional.of(UsuarioDTO.toDTO(usuario)), resultado);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testEliminarUsuario() {
        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}
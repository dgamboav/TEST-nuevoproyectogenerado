package com.miempresa.nuevoproyectogenerado.servicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);

    List<UsuarioDTO> obtenerTodosUsuario();

    Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);
}
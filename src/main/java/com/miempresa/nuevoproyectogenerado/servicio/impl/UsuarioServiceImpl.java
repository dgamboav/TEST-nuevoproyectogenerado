package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.repositorio.UsuarioRepository;
import com.miempresa.nuevoproyectogenerado.servicio.UsuarioService;
import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    
    private final UsuarioRepository usuarioRepository;
	
	UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        return UsuarioDTO.toDTO(usuarioRepository.save(UsuarioDTO.toDomain(usuarioDTO)));
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::toDTO);
    }

    @Override
    public List<UsuarioDTO> obtenerTodosUsuario() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::toDTO)
                .toList();
    }

    @Override
    public Optional<UsuarioDTO> actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    Usuario usuario = UsuarioDTO.toDomain(usuarioDTO);
                    usuario.setId(id);
                    return usuarioRepository.save(usuario);
                })
                .map(UsuarioDTO::toDTO);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
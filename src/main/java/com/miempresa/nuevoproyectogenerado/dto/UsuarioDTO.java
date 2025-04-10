package com.miempresa.nuevoproyectogenerado.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

import com.miempresa.nuevoproyectogenerado.entidad.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String correo;

    private String contrasena;


    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .correo(usuario.getCorreo())
            .contrasena(usuario.getContrasena())
            .build();
    }

    public static Usuario toDomain(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
            .id(usuarioDTO.getId())
            .nombre(usuarioDTO.getNombre())
            .correo(usuarioDTO.getCorreo())
            .contrasena(usuarioDTO.getContrasena())
            .build();
    }
}
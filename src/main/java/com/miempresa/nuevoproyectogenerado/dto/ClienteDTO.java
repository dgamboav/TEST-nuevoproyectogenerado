package com.miempresa.nuevoproyectogenerado.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

import com.miempresa.nuevoproyectogenerado.entidad.Cliente;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;


    public static ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
            .id(cliente.getId())
            .nombre(cliente.getNombre())
            .direccion(cliente.getDireccion())
            .telefono(cliente.getTelefono())
            .build();
    }

    public static Cliente toDomain(ClienteDTO clienteDTO) {
        return Cliente.builder()
            .id(clienteDTO.getId())
            .nombre(clienteDTO.getNombre())
            .direccion(clienteDTO.getDireccion())
            .telefono(clienteDTO.getTelefono())
            .build();
    }
}